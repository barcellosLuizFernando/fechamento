/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelasMySQL;

import conn.ConexaoMySQL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class FinContratos {

    private Integer id;

    private Integer banco;

    private Integer modalidade;

    private Boolean interveniencia;

    private String sql;

    private String numero;

    private Integer conta;

    private Integer fornecedor;

    private Date liberacao;

    private Date vencimento;

    private Double valor;

    private String recurso;

    private Double taxa;

    private String amortizacao;

    private Boolean autoupd;

    private String obs;

    private String documento;

    private Boolean liquidado;
    private ConexaoMySQL cn;
    private DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    ;
    private double jurosAcum;
    private double amortizAcum;
    private double liberacoesAcum;
    private int subContrato;
    private double descontosAcum;

    private double vlr_longo_prazo;
    private double vlr_curto_prazo;
    private double vlr_total;

    public FinContratos(ConexaoMySQL conn) {
        this.cn = conn;
    }

    /**
     * Soma valor dos contratos registrados.
     *
     * @param conn
     * @param data
     * @throws java.lang.Exception
     */
    public FinContratos(ConexaoMySQL conn, String data) throws Exception {
        
        this.cn = conn;

        if (cn.iniciarTransacao()) {
            try {
                String periodo = dateOut.format(dateIn.parse(data));
                sql = "call fechamento.fin_endividamento('" + periodo + "')";

                cn.executeAtualizacao(sql);

                sql = "select x.grupo, sum(x.saldo) as valor "
                        + "from ("
                        + " select case when datediff(a.vencimento, '" + periodo
                        + "') > 365 then 1 else 0 end as grupo, a.saldo "
                        + "from fechamento.tmp_endividamento a ) x "
                        + "group by x.grupo;";

                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    if (cn.rs.getBoolean("grupo")) {
                        vlr_longo_prazo += cn.rs.getDouble("valor");
                    } else {
                        vlr_curto_prazo += cn.rs.getDouble("valor");
                    }
                }

                vlr_total = vlr_longo_prazo + vlr_curto_prazo;

                System.out.println("\nCurto prazo: R$ " + vlr_curto_prazo);
                System.out.println("Longo prazo: R$ " + vlr_longo_prazo);
                System.out.println("Valor total: R$ " + vlr_total);

            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            } finally {
                cn.finalizarTransacao();
            }
        }

    }

    /**
     * Carrega dados de um único contrato.
     *
     * @param contrato
     * @param banco
     * @param conn
     */
    public FinContratos(String contrato, Integer banco, ConexaoMySQL conn) {
        this.cn = conn;

        sql = "SELECT sum(a.valor) as liberacao FROM fechamento.fin_contratos a "
                + "where a.numero = '" + contrato + "' "
                + "and a.banco = " + banco + ";";
        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    liberacoesAcum = cn.rs.getDouble("liberacao");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }

        sql = "SELECT * FROM fechamento.resumo_fin_contratos a "
                + "where a.numero = '" + contrato + "' "
                + "and a.banco = " + banco + ";";
        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    liberacoesAcum = cn.rs.getDouble("liberacoes");
                    jurosAcum = cn.rs.getDouble("juros");
                    amortizAcum = cn.rs.getDouble("amortizacoes");
                    descontosAcum = cn.rs.getDouble("descontos");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Integer fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public Integer getModalidade() {
        return modalidade;
    }

    public void setModalidade(Integer modalidade) {
        this.modalidade = modalidade;
    }

    public Boolean getInterveniencia() {
        return interveniencia;
    }

    public void setInterveniencia(Boolean interveniencia) {
        this.interveniencia = interveniencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Date getLiberacao() {
        return liberacao;
    }

    public void setLiberacao(Date liberacao) {
        this.liberacao = liberacao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public String getAmortizacao() {
        return amortizacao;
    }

    public void setAmortizacao(String amortizacao) {
        this.amortizacao = amortizacao;
    }

    public Boolean getAutoupd() {
        return autoupd;
    }

    public void setAutoupd(Boolean autoupd) {
        this.autoupd = autoupd;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getLiquidado() {
        return liquidado;
    }

    public void setLiquidado(Boolean liquidado) {
        this.liquidado = liquidado;
    }

    public boolean gravaDados(boolean x) {
        boolean resposta = false;

        if (x) {
            sql = "INSERT INTO `fechamento`.`fin_contratos` (`id`,`banco`,"
                    + "`modalidade`,`interveniencia`,`numero`,`conta`,"
                    + "`liberacao`,`vencimento`,`valor`,`recurso`,`taxa`,"
                    + "`amortizacao`,`autoupd`,`obs`,`liquidado`,`fornecedor`,"
                    + "`subcontrato`,`tipodoc`) VALUES ( "
                    + "'" + id + "','" + banco + "','" + modalidade + "',"
                    + "" + interveniencia + ",'" + numero + "','" + conta + "',"
                    + "'" + dateOut.format(liberacao) + "','" + dateOut.format(vencimento) + "','" + valor + "',"
                    + "'" + recurso + "','" + taxa + "','" + amortizacao + "',"
                    + "" + autoupd + ",'" + obs + "'," + liquidado + ","
                    + "'" + fornecedor + "','" + subContrato + "','" + documento + "');";
        } else {
            sql = "UPDATE `fechamento`.`fin_contratos` SET "
                    + "`banco` = '" + banco + "', "
                    + "`modalidade` = '" + modalidade + "', "
                    + "`interveniencia` = " + interveniencia + ", "
                    + "`numero` = '" + numero + "', `conta` = '" + conta + "',"
                    + "`liberacao` = '" + dateOut.format(liberacao) + "', "
                    + "`vencimento` = '" + dateOut.format(vencimento) + "', "
                    + "`valor` = '" + valor + "', `recurso` = '" + recurso + "', "
                    + "`taxa` = '" + taxa + "', `amortizacao` = '" + amortizacao + "', "
                    + "`autoupd` = " + autoupd + ", `obs` = '" + obs + "', "
                    + "`fornecedor` = '" + fornecedor + "',`subcontrato` = '" + subContrato + "'"
                    + ",`tipodoc` = '" + documento + "' "
                    + "WHERE `id` = '" + id + "';";
        }

        if (cn.iniciarTransacao()) {
            try {
                resposta = cn.executeAtualizacao(sql);
            } catch (Exception e) {
                resposta = false;
            } finally {
                cn.finalizarTransacao();
            }
        }

        return resposta;
    }

    public double getJuros() {

        return jurosAcum;
    }

    public double getAmortizacoes() {

        return amortizAcum;
    }

    public double getLiberacoes() {

        return liberacoesAcum;
    }

    public void setSubcontrato(int x) {
        this.subContrato = x;
    }

    public int getSubContrato() {
        return subContrato;
    }

    public double getVlr_longo_prazo() {
        return vlr_longo_prazo;
    }

    public double getVlr_curto_prazo() {
        return vlr_curto_prazo;
    }

    public double getVlr_total() {
        return vlr_total;
    }

    public static void main(String args[]) {

        ConexaoMySQL cn = new ConexaoMySQL();
        ConexaoMySQL.arquivo = "mysqlcasa.properties";
        
        try {
            
            cn.conecta();
            
            FinContratos fin = new FinContratos(cn, "30/11/2017");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
