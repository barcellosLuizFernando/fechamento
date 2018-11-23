/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import view.TelaPrincipal;
import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class FinIntegracao {

    private ConexaoMySQL cn;
    private final ConexaoFB cnfb = new ConexaoFB();
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");

    private String id, lancamento, sql;
    private Date data;
    private double juros, amortizacao, descontos;
    private boolean importado;
    private String origem;
    
    public FinIntegracao(ConexaoMySQL conn){
        this.cn = conn;
    }

    public boolean gravaDados(boolean upd) {
        boolean resposta = false;

        if (upd) {
            sql = "UPDATE fin_contratos_movimentos ";
        } else {
            sql = "INSERT INTO fin_contratos_movimentos (id, lancamento, data, juros, "
                    + "amortizacao, descontos, importado, origem) VALUES ('" + id + "',"
                    + "'" + lancamento + "','" + dateOut.format(data) + "',"
                    + "'" + juros + "','" + amortizacao + "','" + descontos + "',"
                    + importado + ",'" + origem + "')";
        }

        if (cn.iniciarTransacao()) {
            try {
                resposta = cn.executeAtualizacao(sql);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível lançar os movimentos dos contratos.");
            } finally {
                cn.finalizarTransacao();
            }
        }

        return resposta;
    }

    public boolean buscaMovimentos(String inicio, String fim) {
        boolean resposta = false;

        String contrato, fornecedor;

        ArrayList idContrato = new ArrayList();
        ArrayList nroContrato = new ArrayList();
        ArrayList codFornecedor = new ArrayList();
        ArrayList idLancamento = new ArrayList();

        sql = "SELECT DISTINCT MIN(id) AS id, numero, fornecedor FROM fin_contratos WHERE liquidado = 0 GROUP BY numero, fornecedor;";
        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    idContrato.add(cn.rs.getInt("id"));
                    nroContrato.add(cn.rs.getString("numero"));
                    codFornecedor.add(cn.rs.getInt("fornecedor"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível montar as coleções de contratos.");
            } finally {
                cn.finalizarTransacao();
            }
        }

        if (cn.iniciarTransacao()) {
            try {
                sql = "SELECT lancamento FROM fin_contratos_movimentos;";
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    idLancamento.add(cn.rs.getString("lancamento"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }

        if (cnfb.conecta()) {
            try {

                for (int i = 0; i < idContrato.size(); i++) {
                    sql = "with baixas as (select aa.codigo_baixa, aa.codigo_pagar, aaa.data,"
                            + "a.doc as contrato, c.nome as banco, "
                            + "0.00 as liberacoes, "
                            + "case when aa.tipo_plano = 'D' and d.ecd_cod_nat = '04' then aa.valor else 0.00 end as juros, "
                            + "case when aa.tipo_plano = 'C' and d.ecd_cod_nat = '01' then aa.valor else 0.00 end as amortizacoes, "
                            + "case when aa.tipo_plano = 'C' and d.ecd_cod_nat = '04' then aa.valor else 0.00 end as descontos "
                            + "from fin_contas_pagar_baixas aaa "
                            + "left join fin_contas_pagar_baixas_detalhe aa on (aaa.empresa = aa.empresa and aaa.codigo = aa.codigo_baixa) "
                            + "left join fin_contas_pagar a on (a.empresa = aa.empresa and a.codigo = aa.codigo_pagar) "
                            + "left join cad_terceiros c on (c.empresa = a.empresa and c.codigo = a.fornecedor) "
                            + "left join cad_planocontas d on (d.empresa = aa.empresa and d.codigo = aa.planoconta) "
                            + "where aaa.empresa = 1 and a.doc = '" + nroContrato.get(i) + "' and a.fornecedor = '" + codFornecedor.get(i) + "' "
                            + "and aaa.data between '" + inicio.replace("/", ".") + "' and '" + fim.replace("/", ".") + "' ) "
                            + "select codigo_baixa, data, contrato, "
                            + "banco, sum(juros) as juros, sum(amortizacoes) as amortizacoes, "
                            + "sum(descontos) as descontos "
                            + "from baixas "
                            + "group by 1,2,3,4;";
                    cnfb.executeConsulta(sql);
                    while (cnfb.rs.next()) {
                        id = idContrato.get(i).toString();
                        lancamento = cnfb.rs.getString("codigo_baixa");
                        data = cnfb.rs.getDate("data");
                        juros = cnfb.rs.getDouble("juros");
                        amortizacao = cnfb.rs.getDouble("amortizacoes");
                        descontos = cnfb.rs.getDouble("descontos");
                        importado = true;
                        origem = "BAIXAS";

                        if (!idLancamento.contains(lancamento)) {

                            if (gravaDados(false) == false) {
                                break;
                            }
                        } else {
                            System.out.println("Lançamento " + lancamento + " não adicionado. Já existe na base.");
                        }
                    }
                    cnfb.rs.close();

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível capturar os dados do financeiro para as movimentações de contratos.");
            } finally {
                cnfb.desconecta();
            }
        }

        System.out.println("Lançamentos já integrados: " + idLancamento);
        System.out.println("Quantidade de contratos: " + nroContrato.size());
        System.out.println("Quantidade de codigos: " + idContrato.size());
        System.out.println("Quantidade de fornecedores: " + codFornecedor.size());
        System.out.println("Contratos selecionados: " + nroContrato);

        return resposta;
    }

    private void buscaProvisoes() {

        String contrato, fornecedor;

        ArrayList idContrato = new ArrayList();
        ArrayList nroContrato = new ArrayList();
        ArrayList codFornecedor = new ArrayList();

        sql = "SELECT DISTINCT MIN(id) AS id, numero, fornecedor FROM fin_contratos WHERE liquidado = 0 GROUP BY numero, fornecedor;";
        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    idContrato.add(cn.rs.getInt("id"));
                    nroContrato.add(cn.rs.getString("numero"));
                    codFornecedor.add(cn.rs.getInt("fornecedor"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível montar as coleções de contratos.");
            } finally {
                cn.finalizarTransacao();
            }
        }

        if (cnfb.conecta()) {
            try {
                for (int i = 0; i < idContrato.size(); i++) {
                    sql = "select a.codigo, a.dt_emissao, a.doc as contrato, a.fornecedor as banco, "
                            + "case when b.planoconta != '6256' and b.tipo_plano = 'D'then b.valor else 0.00 end as liberacoes, "
                            + "case when b.planoconta = '6256' and b.tipo_plano = 'D' then b.valor else 0.00 end as juros, "
                            + "0.00 as amortizacoes, "
                            + "0.00 as descontos "
                            + "from fin_contas_pagar a "
                            + "left join fin_contas_pagar_detalhe b on (b.empresa = a.empresa and b.codigo = a.codigo) "
                            + "left join cad_terceiros c on (c.empresa = a.empresa and c.codigo = a.fornecedor) "
                            + "where a.empresa = 1 and a.doc = '" + nroContrato.get(i) + "' and a.fornecedor = '" + codFornecedor.get(i) + "' "
                            + "and b.planoconta = '6256' ;";
                    cnfb.executeConsulta(sql);
                    while (cnfb.rs.next()) {
                        id = idContrato.get(i).toString();
                        lancamento = cnfb.rs.getString("codigo");
                        data = cnfb.rs.getDate("dt_emissao");
                        juros = cnfb.rs.getDouble("juros");
                        amortizacao = 0.00;
                        descontos = 0.00;
                        importado = true;
                        origem = "PROVISAO";

                        if (gravaDados(false) == false) {
                            break;
                        }
                    }
                    cnfb.rs.close();

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível capturar as provisões.");
            } finally {
                cnfb.desconecta();
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getAmortizacao() {
        return amortizacao;
    }

    public void setAmortizacao(double amortizacao) {
        this.amortizacao = amortizacao;
    }

    public double getDescontos() {
        return descontos;
    }

    public void setDescontos(double descontos) {
        this.descontos = descontos;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

//    public static void main(String[] args) {
//        TelaPrincipal tp = new TelaPrincipal();
//        FinIntegracao integ = new FinIntegracao();
//        integ.buscaMovimentos("01/05/2017", "31/05/2017");
//        //integ.buscaProvisoes();
//        tp.dispose();
//    }

}
