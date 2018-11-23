/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;

/**
 *
 * @author ferna
 */
public class IntegraFinanceiro {

    private ConexaoMySQL cn;
    private final conn.ConexaoFB cnfb = new ConexaoFB();
    private tools.FbGenerators gen = new FbGenerators();
    private DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");

    private String empresa = "1";
    private int codigo;
    private String estabelecimento = "1";
    //private String nome_estabelecimento;
    private String tipodoc;
    private String fornecedor;
    //private String nome_fornecedor;
    private String portador = "1";
    private int usu_inc = 1;
    private String doc;
    private String historico;
    private Date dt_emissao;
    private Date dt_vencimento;
    private Date data_inc;
    private TimeZone hora_inc;
    private int id_transacao;
    private String estabelec_cad = "1";
    private String lote;
    private String parcela = "1";
    private String integra_contabil;
    private String perc_juros_atraso = "0";
    private String perc_multa_atraso = "0";
    private String perc_desc_antecipacao = "0";
    private String perc_pontualidade = "0";
    private String planoconta;
    private Double valor_bruto;

    public IntegraFinanceiro(ConexaoMySQL conn, boolean intCont) {
        this.cn = conn;
        if (intCont) {
            this.integra_contabil = "S";
        } else {
            this.integra_contabil = "N";
        }
    }

    /**
     * Lançamento do Contas a Pagar.
     *
     * @param id
     * @param tipodoc
     * @param fornecedor
     * @param doc
     * @param dt_emissao
     * @param dt_vencimento
     * @param planoconta
     * @param valor_bruto
     * @param historico2 Histórico Contábil
     * @param contaFornecedor
     * @param historico1 Histórico Financeiro
     * @param emprestimos
     * @param estab
     * @param autocommit
     * @return
     */
    public boolean integraFinanceiro(String id, String tipodoc, String fornecedor,
            String doc, Date dt_emissao, Date dt_vencimento, String planoconta,
            String valor_bruto, String historico2, String contaFornecedor, String historico1, boolean emprestimos, String estab, boolean autocommit) {

        if (estab != null) {
            this.estabelecimento = estab;
        }

        boolean resposta = false;

        String sql;
        String query;

        codigo = gen.getCodigo("fin_contas_pagar");
        System.out.println("Código capturado: " + codigo);

        id_transacao = gen.getTransacao();
        System.out.println("Transação capturada: " + id_transacao);

        if (cnfb.conecta()) {
            try {
                usu_inc = Integer.parseInt(cnfb.userlog);

                sql = "INSERT INTO fin_contas_pagar (empresa,codigo,estabelec,"
                        + "tipodoc,fornecedor,portador,usu_inc,doc,historico,"
                        + "dt_emissao,dt_venc,data_inc,hora_inc,id_transacao,"
                        + "estabelec_cad,parcela,integra_contabil,"
                        + "perc_juros_atraso,perc_multa_atraso,"
                        + "perc_desc_antecipacao,perc_pontualidade";

                if (planoconta != null) {
                    sql += ",conta_fornecedor";
                }

                sql += ") VALUES ('"
                        + empresa + "','" + codigo + "','" + estabelecimento + "','"
                        + tipodoc + "','" + fornecedor + "','" + portador + "','"
                        + usu_inc + "','" + doc + "','" + historico1 + "','"
                        + dateIn.format(dt_emissao).replace("/", ".") + "','" + dateIn.format(dt_vencimento).replace("/", ".") + "',current_date,"
                        + "current_time,'" + id_transacao + "','" + estabelec_cad + "','"
                        + parcela + "','" + integra_contabil + "','" + perc_juros_atraso + "','"
                        + perc_multa_atraso + "','" + perc_desc_antecipacao + "','"
                        + perc_pontualidade + "'";

                if (planoconta != null) {
                    sql += "," + contaFornecedor;
                }

                sql += ");";

                if (cnfb.executeAtualizacao(sql)) {
                    sql = "INSERT INTO fin_contas_pagar_detalhe (empresa,codigo,planoconta,valor,id_transacao,tipo_plano,historico,tipo_lcto) VALUES ";
                    query = "('" + empresa + "','" + codigo + "','" + planoconta + "','" + valor_bruto + "','" + id_transacao + "','D','" + historico2 + "','1');";
                    resposta = cnfb.executeAtualizacao(sql + query);

                } else {
                    JOptionPane.showMessageDialog(null, "Este recibo" + doc + " não foi integrado.");
                    resposta = false;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível executar a integração com o Financeiro. " + e);
            } finally {
                if (autocommit) {
                    confirmaIntegracao();
                }
            }
        }

        System.out.println("Resposta da integração: " + resposta);

        if (resposta && emprestimos) {
            if (cn.iniciarTransacao()) {
                try {
                    resposta = cn.executeAtualizacao("UPDATE fin_contratos_movimentos SET lancamento = '" + codigo + "' "
                            + "WHERE id = '" + id + "' "
                            + "AND data = '" + dateOut.format(dt_emissao) + "';");
                    System.out.println("Atualização da tabela local realizada com sucesso.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Não foi possível atualizar a tabela de Movimentação.");
                } finally {
                    cn.finalizarTransacao();
                }
            }
        }

        return resposta;
    }

    public void desfazIntegracao() {
        ConexaoFB.resultadoUpd = 99;
        cnfb.desconecta();
    }

    public void confirmaIntegracao() {
        cnfb.desconecta();
    }

    /**
     * Código gerado pelo Financeiro.
     *
     * @return
     */
    public int getCodigo() {
        return codigo;
    }

    public boolean deletaFinanceiro(String codigo) {

        boolean resposta = false;

        String sql = "DELETE FROM fin_contas_pagar_detalhe WHERE codigo = '" + codigo + "';";

        if (cnfb.conecta()) {
            try {
                resposta = cnfb.executeAtualizacao(sql);
                sql = "DELETE FROM fin_contas_pagar WHERE codigo = '" + codigo + "';";
                resposta = cnfb.executeAtualizacao(sql);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                cnfb.desconecta();
            }
        }

        return resposta;
    }

}
