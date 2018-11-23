/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finInvestments;

import conn.ConexaoFB;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class FinInvestContracts {

    private final ConexaoFB cnfb = new ConexaoFB();
    private String sql;
    private ArrayList idBanks;
    private ArrayList nameBank;
    private double outstanding_balance_total;
    private double investment;
    private double withdrawal;
    private double interest;
    private double withholding_tax;
    private double financial_operations_tax;
    private double outstanding_balance;
    private double outstanding_balance_acc;
    private ArrayList numberContracts;
    private int tipoDoc;
    private Date dtVenc;
    private int contaCliente;
    private String historico;
    private String doc;
    private int estabelec;

    public FinInvestContracts(String date) throws Exception {

        idBanks = new ArrayList();
        nameBank = new ArrayList();
        outstanding_balance_total = 0.00;

        sql = "select distinct x.banco, x.cliente, sum(x.valor) as valor "
                + "from ( select a.cliente, a.estabelec, a.codigo, a.tipodoc,"
                + "a.doc, a.historico, a.dt_emissao, a.dt_venc, a.conta_cliente,"
                + "b.planoconta,"
                + "case b.tipo_plano when 'C' then b.valor else b.valor*-1 end as valor,"
                + "b.tipo_plano, t.nome as banco "
                + "from fin_contas_receber a "
                + "inner join fin_contas_receber_detalhe b on (b.empresa = a.empresa and b.codigo = a.codigo) "
                + "left join cad_terceiros t on (t.empresa = a.empresa and t.codigo = a.cliente) "
                + "where a.empresa = 1 "
                + "and a.dt_emissao <= '" + date.replace("/", ".") + "' and "
                + "a.tipodoc in (31,65,60) "
                + "union all "
                + "select c1.cliente, c1.estabelec, c1.codigo, c1.tipodoc, "
                + "c1.doc, c1.historico, c1.dt_emissao, c1.dt_venc, "
                + "c1.conta_cliente, b1.planoconta, "
                + "case b1.tipo_plano when 'C' then b1.valor else b1.valor*-1 end as valor, "
                + "b1.tipo_plano, t1.nome as banco "
                + "from fin_contas_receber_baixas a1 "
                + "inner join fin_contas_receber_baixas_det b1 on (b1.empresa = a1.empresa and b1.codigo_baixa = a1.codigo) "
                + "inner join fin_contas_receber c1 on (c1.empresa = b1.empresa and c1.codigo = b1.codigo_receber "
                + "and c1.dt_emissao <= '" + date.replace("/", ".") + "' and c1.tipodoc in (31,65,60)) "
                + "left join cad_terceiros t1 on (t1.empresa = c1.empresa and t1.codigo = c1.cliente) "
                + "where a1.empresa = 1 "
                + "and a1.data <= '" + date.replace("/", ".") + "' ) x "
                + "group by 1,2 "
                + "having round(sum(x.valor),2) > 0.00;";

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    idBanks.add(cnfb.rs.getString("cliente"));
                    nameBank.add(cnfb.rs.getString("banco"));
                    outstanding_balance_total += cnfb.rs.getDouble("valor");
                }
            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
            }
        }

        System.out.println("Códigos: " + idBanks);
        System.out.println("Nomes: " + nameBank);
        System.out.println("Saldo das Aplicações: " + tools.FormatNumbers.numUsToBr(outstanding_balance_total));
    }

    public void residualValue(String contract, String idBank, String date) throws Exception {

        tipoDoc = 0;
        dtVenc = null;
        contaCliente = 0;
        historico = null;
        doc = null;
        estabelec = 0;

        investment = 0.00;
        withdrawal = 0.00;
        interest = 0.00;
        withholding_tax = 0.00;
        financial_operations_tax = 0.00;
        outstanding_balance = 0.00;

        sql = "select a.cliente, a.estabelec, a.codigo, a.tipodoc,"
                + "a.doc, a.historico, a.dt_emissao, a.dt_venc, a.conta_cliente,"
                + "b.planoconta, "
                + "case when (select x.classificacao from cad_planocontas x where x.empresa = b.empresa and x.codigo = b.planoconta) starting with '1' "
                + "and  b.tipo_plano = 'C' then b.valor else 0.00 end as aplicacao, "
                + "case when (select x.classificacao from cad_planocontas x where x.empresa = b.empresa and x.codigo = b.planoconta) starting with '3' "
                + "and  b.tipo_plano = 'C' then b.valor else 0.00 end as juros, "
                + "b.tipo_plano, t.nome as banco, "
                + "0.00 as iof, "
                + "case when (select x.classificacao from cad_planocontas x where x.empresa = b.empresa and x.codigo = b.planoconta) starting with '1.1.2.5' "
                + "and  b.tipo_plano = 'D' then b.valor else 0.00 end as irrf, "
                + "0.00 as resgate "
                + "from fin_contas_receber a "
                + "inner join fin_contas_receber_detalhe b on (b.empresa = a.empresa and b.codigo = a.codigo) "
                + "left join cad_terceiros t on (t.empresa = a.empresa and t.codigo = a.cliente) "
                + "where a.empresa = 1 and a.cliente = '" + idBank + "' "
                + "and a.dt_emissao <= '" + date.replace("/", ".") + "' and "
                + "a.tipodoc in (31,65,60) and a.doc = '" + contract + "' "
                + "union all "
                + "select c1.cliente, c1.estabelec, c1.codigo, c1.tipodoc, "
                + "c1.doc, c1.historico, c1.dt_emissao, c1.dt_venc, "
                + "c1.conta_cliente, b1.planoconta, 0.00 as aplicacao, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '3' "
                + "and  b1.tipo_plano = 'C' then b1.valor else 0.00 end as juros, "
                + "b1.tipo_plano, t1.nome as banco, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '3' "
                + "and  b1.tipo_plano = 'D' then b1.valor else 0.00 end as iof, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '1.1.2.5' "
                + "and  b1.tipo_plano = 'D' then b1.valor else 0.00 end as irrf, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '1.1.1.2' "
                + "and  b1.tipo_plano = 'D' then b1.valor else 0.00 end as resgate "
                + "from fin_contas_receber_baixas a1 "
                + "inner join fin_contas_receber_baixas_det b1 on (b1.empresa = a1.empresa and b1.codigo_baixa = a1.codigo) "
                + "inner join fin_contas_receber c1 on (c1.empresa = b1.empresa and c1.codigo = b1.codigo_receber "
                + "and c1.dt_emissao <= '" + date.replace("/", ".") + "' and c1.tipodoc in (31,65,60) "
                + "and c1.cliente = '" + idBank + "' and c1.doc = '" + contract + "') "
                + "left join cad_terceiros t1 on (t1.empresa = c1.empresa and t1.codigo = c1.cliente) "
                + "where a1.empresa = 1 "
                + "and a1.data <= '" + date.replace("/", ".") + "';";

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    investment += cnfb.rs.getDouble("aplicacao");
                    withdrawal += cnfb.rs.getDouble("resgate");
                    interest += cnfb.rs.getDouble("juros");
                    financial_operations_tax += cnfb.rs.getDouble("iof");
                    withholding_tax += cnfb.rs.getDouble("irrf");

                    if (dtVenc == null) {
                        tipoDoc = cnfb.rs.getInt("tipodoc");
                        dtVenc = cnfb.rs.getDate("dt_venc");
                        contaCliente = cnfb.rs.getInt("conta_cliente");
                        historico = cnfb.rs.getString("historico");
                        doc = cnfb.rs.getString("doc");
                        estabelec = cnfb.rs.getInt("estabelec");
                    }
                }

                outstanding_balance = investment + interest - withdrawal - withholding_tax - financial_operations_tax;

            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
            }
        }

        System.out.println("Aplicações: " + tools.FormatNumbers.numUsToBr(investment));
        System.out.println("Juros: " + tools.FormatNumbers.numUsToBr(interest));
        System.out.println("IRRF: " + tools.FormatNumbers.numUsToBr(withholding_tax));
        System.out.println("IOF: " + tools.FormatNumbers.numUsToBr(financial_operations_tax));
        System.out.println("Resgate: " + tools.FormatNumbers.numUsToBr(withdrawal));
        System.out.println("Saldo: " + tools.FormatNumbers.numUsToBr(outstanding_balance));

    }

    public Double residualValue(String idBank, String date) throws Exception {

        tipoDoc = 0;
        dtVenc = null;
        contaCliente = 0;
        historico = null;
        doc = null;
        estabelec = 0;

        investment = 0.00;
        withdrawal = 0.00;
        interest = 0.00;
        withholding_tax = 0.00;
        financial_operations_tax = 0.00;
        outstanding_balance = 0.00;

        sql = "select a.cliente, a.estabelec, a.codigo, a.tipodoc,"
                + "a.doc, a.historico, a.dt_emissao, a.dt_venc, a.conta_cliente,"
                + "b.planoconta, "
                + "case when (select x.classificacao from cad_planocontas x where x.empresa = b.empresa and x.codigo = b.planoconta) starting with '1' "
                + "and  b.tipo_plano = 'C' then b.valor else 0.00 end as aplicacao, "
                + "case when (select x.classificacao from cad_planocontas x where x.empresa = b.empresa and x.codigo = b.planoconta) starting with '3' "
                + "and  b.tipo_plano = 'C' then b.valor else 0.00 end as juros, "
                + "b.tipo_plano, t.nome as banco, "
                + "0.00 as iof, "
                + "case when (select x.classificacao from cad_planocontas x where x.empresa = b.empresa and x.codigo = b.planoconta) starting with '1.1.2.5' "
                + "and  b.tipo_plano = 'D' then b.valor else 0.00 end as irrf, "
                + "0.00 as resgate "
                + "from fin_contas_receber a "
                + "inner join fin_contas_receber_detalhe b on (b.empresa = a.empresa and b.codigo = a.codigo) "
                + "left join cad_terceiros t on (t.empresa = a.empresa and t.codigo = a.cliente) "
                + "where a.empresa = 1 and a.cliente = '" + idBank + "' "
                + "and a.dt_emissao <= '" + date.replace("/", ".") + "' and "
                + "a.tipodoc in (31,65,60) "
                + "union all "
                + "select c1.cliente, c1.estabelec, c1.codigo, c1.tipodoc, "
                + "c1.doc, c1.historico, c1.dt_emissao, c1.dt_venc, "
                + "c1.conta_cliente, b1.planoconta, 0.00 as aplicacao, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '3' "
                + "and  b1.tipo_plano = 'C' then b1.valor else 0.00 end as juros, "
                + "b1.tipo_plano, t1.nome as banco, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '3' "
                + "and  b1.tipo_plano = 'D' then b1.valor else 0.00 end as iof, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '1.1.2.5' "
                + "and  b1.tipo_plano = 'D' then b1.valor else 0.00 end as irrf, "
                + "case when (select x1.classificacao from cad_planocontas x1 where x1.empresa = b1.empresa and x1.codigo = b1.planoconta) starting with '1.1.1.2' "
                + "and  b1.tipo_plano = 'D' then b1.valor else 0.00 end as resgate "
                + "from fin_contas_receber_baixas a1 "
                + "inner join fin_contas_receber_baixas_det b1 on (b1.empresa = a1.empresa and b1.codigo_baixa = a1.codigo) "
                + "inner join fin_contas_receber c1 on (c1.empresa = b1.empresa and c1.codigo = b1.codigo_receber "
                + "and c1.dt_emissao <= '" + date.replace("/", ".") + "' and c1.tipodoc in (31,65,60) "
                + "and c1.cliente = '" + idBank + "' ) "
                + "left join cad_terceiros t1 on (t1.empresa = c1.empresa and t1.codigo = c1.cliente) "
                + "where a1.empresa = 1 "
                + "and a1.data <= '" + date.replace("/", ".") + "';";

        Double investment = 0.00;
        Double withdrawal = 0.00;
        Double interest = 0.00;
        Double financial_operations_tax = 0.00;
        Double withholding_tax = 0.00;
        Double outstanding_balance = 0.00;

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    investment += cnfb.rs.getDouble("aplicacao");
                    withdrawal += cnfb.rs.getDouble("resgate");
                    interest += cnfb.rs.getDouble("juros");
                    financial_operations_tax += cnfb.rs.getDouble("iof");
                    withholding_tax += cnfb.rs.getDouble("irrf");
                }

                outstanding_balance = investment + interest - withdrawal - withholding_tax - financial_operations_tax;

            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
            }
        }

        System.out.println("Aplicações: " + tools.FormatNumbers.numUsToBr(investment));
        System.out.println("Juros: " + tools.FormatNumbers.numUsToBr(interest));
        System.out.println("IRRF: " + tools.FormatNumbers.numUsToBr(withholding_tax));
        System.out.println("IOF: " + tools.FormatNumbers.numUsToBr(financial_operations_tax));
        System.out.println("Resgate: " + tools.FormatNumbers.numUsToBr(withdrawal));
        System.out.println("Saldo: " + tools.FormatNumbers.numUsToBr(outstanding_balance));

        return outstanding_balance;
    }

    public ArrayList getNumberContracts(String date, String idBank) throws Exception {

        numberContracts = new ArrayList();

        sql = "select distinct x.doc, x.cliente, sum(x.valor) as valor "
                + "from ( select a.cliente, a.estabelec, a.codigo, a.tipodoc,"
                + "a.doc, a.historico, a.dt_emissao, a.dt_venc, a.conta_cliente,"
                + "b.planoconta,"
                + "case b.tipo_plano when 'C' then b.valor else b.valor*-1 end as valor,"
                + "b.tipo_plano, t.nome as banco "
                + "from fin_contas_receber a "
                + "inner join fin_contas_receber_detalhe b on (b.empresa = a.empresa and b.codigo = a.codigo) "
                + "left join cad_terceiros t on (t.empresa = a.empresa and t.codigo = a.cliente) "
                + "where a.empresa = 1 and a.cliente = '" + idBank + "' "
                + "and a.dt_emissao <= '" + date.replace("/", ".") + "' and "
                + "a.tipodoc in (31,65,60) "
                + "union all "
                + "select c1.cliente, c1.estabelec, c1.codigo, c1.tipodoc, "
                + "c1.doc, c1.historico, c1.dt_emissao, c1.dt_venc, "
                + "c1.conta_cliente, b1.planoconta, "
                + "case b1.tipo_plano when 'C' then b1.valor else b1.valor*-1 end as valor, "
                + "b1.tipo_plano, t1.nome as banco "
                + "from fin_contas_receber_baixas a1 "
                + "inner join fin_contas_receber_baixas_det b1 on (b1.empresa = a1.empresa and b1.codigo_baixa = a1.codigo) "
                + "inner join fin_contas_receber c1 on (c1.empresa = b1.empresa and c1.codigo = b1.codigo_receber "
                + "and c1.dt_emissao <= '" + date.replace("/", ".") + "' and c1.tipodoc in (31,65,60) "
                + "and c1.cliente = '" + idBank + "') "
                + "left join cad_terceiros t1 on (t1.empresa = c1.empresa and t1.codigo = c1.cliente) "
                + "where a1.empresa = 1 "
                + "and a1.data <= '" + date.replace("/", ".") + "' ) x "
                + "group by 1,2 "
                + "having round(sum(x.valor),2) > 0.00;";

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    numberContracts.add(cnfb.rs.getString("doc"));
                }
            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
            }
        }

        System.out.println("Contratos selecionados: " + numberContracts);

        return numberContracts;
    }

    public Double getSumBalanceSheet(String date) throws Exception {

        outstanding_balance_acc = 0.00;

        sql = "SELECT a.VALOR as valor "
                + "from ctb_lancamentos a "
                + "inner join cad_planocontas b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.DEBITO "
                + "and (b.CLASSIFICACAO starting with ('1.1.1.4') or b.CLASSIFICACAO starting with ('1.1.2.7'))) "
                + "WHERE a.EMPRESA = 1 and a.DATA <= '" + date.replace("/", ".") + "' "
                + "union all "
                + "SELECT a.VALOR*-1 as valor "
                + "from ctb_lancamentos a "
                + "inner join cad_planocontas b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.credito "
                + "and (b.CLASSIFICACAO starting with ('1.1.1.4') or b.CLASSIFICACAO starting with ('1.1.2.7'))) "
                + "WHERE a.EMPRESA = 1 and a.DATA <= '" + date.replace("/", ".") + "';";

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    outstanding_balance_acc += cnfb.rs.getDouble("valor");
                }
            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
            }
        }

        System.out.println("Valor do balancete: " + tools.FormatNumbers.numUsToBr(outstanding_balance_acc));

        return outstanding_balance_acc;
    }

    public int getEstabelec() {
        return estabelec;
    }

    public String getDoc() {
        return doc;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public Date getDtVenc() {
        return dtVenc;
    }

    public int getContaCliente() {
        return contaCliente;
    }

    public String getHistorico() {
        return historico;
    }

    public double getInvestment() {
        return investment;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public double getInterest() {
        return interest;
    }

    public double getWithholding_tax() {
        return withholding_tax;
    }

    public double getFinancial_operations_tax() {
        return financial_operations_tax;
    }

    public double getOutstanding_balance() {
        return outstanding_balance;
    }

    public double getOutstanding_balance_total() {
        return outstanding_balance_total;
    }

    public ArrayList getIdBanks() {
        return idBanks;
    }

    public ArrayList getNameBank() {
        return nameBank;
    }

    public String getIdBanks(int x) {
        return idBanks.get(x).toString();
    }

    public String getNameBank(int x) {
        return nameBank.get(x).toString();
    }

    public String getNumberContracts(int i) {
        return numberContracts.get(i).toString();
    }

}
