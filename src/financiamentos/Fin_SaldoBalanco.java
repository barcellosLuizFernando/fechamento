/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiamentos;

import conn.ConexaoFB;
import java.text.DecimalFormat;

/**
 *
 * @author luiz.barcellos
 */
public class Fin_SaldoBalanco {

    private final ConexaoFB cnfb = new ConexaoFB();
    private String sql;

    private Double vlr_curto_prazo;
    private Double vlr_longo_prazo;
    private DecimalFormat df = new DecimalFormat("#,##0.00");
    private double vlr_total;

    public Fin_SaldoBalanco(String data) {

        vlr_curto_prazo = 0.00;
        vlr_longo_prazo = 0.00;

        sql = "SELECT ROUND(SUM(X.VALOR),2) AS VALOR, X.CONTA, X.NOME_CONTA, "
                + "X.GRUPO FROM ( "
                + "SELECT round(sum(case when B.CLASSIFICACAO starting with '2.2.1.1.26' "
                + " OR B.CLASSIFICACAO starting with '2.1.1.1.26' then A.VALOR*-1 "
                + "else A.VALOR end),2) as valor, B.CODIGO as CONTA, "
                + "B.NOME as NOME_CONTA, "
                + "case when B.CLASSIFICACAO starting with '2.1' then 0 else 1 end as grupo "
                + "FROM CAD_PLANOCONTAS B "
                + "INNER JOIN CTB_LANCAMENTOS A ON (A.EMPRESA = B.EMPRESA AND (A.DEBITO = B.CODIGO) AND A.DATA <= '" + data.replace("/", ".") + "') "
                + "WHERE B .EMPRESA = 1 "
                + "AND(B.CLASSIFICACAO STARTING WITH '2.1.1.1.26' or B.CLASSIFICACAO STARTING WITH '2.2.1.1.26') "
                + "group by B.CODIGO, B.CLASSIFICACAO, b.nome "
                + "UNION ALL SELECT "
                + "round(sum(case when B.CLASSIFICACAO starting with '2.2.1.1.26' "
                + "OR B.CLASSIFICACAO starting with '2.1.1.1.26' then A.VALOR else A.VALOR*-1 end),2) as valor, "
                + "B.CODIGO as CONTA, "
                + "B.NOME as NOME_CONTA, "
                + "case when B.CLASSIFICACAO starting with '2.1' then 0 else 1 end as grupo "
                + "FROM CAD_PLANOCONTAS B "
                + "INNER JOIN CTB_LANCAMENTOS A ON (A.EMPRESA = B.EMPRESA AND (A.CREDITO = B.CODIGO) AND A.DATA <= '" + data.replace("/", ".") + "') "
                + "WHERE B.EMPRESA = 1 AND (B.CLASSIFICACAO STARTING WITH '2.1.1.1.26' or B.CLASSIFICACAO STARTING WITH '2.2.1.1.26') "
                + "group by B.CODIGO, B.CLASSIFICACAO, b.nome ) AS X "
                + "GROUP BY 2,3,4; ";

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    if (cnfb.rs.getBoolean("grupo")) {
                        vlr_longo_prazo += cnfb.rs.getDouble("valor");
                    } else {
                        vlr_curto_prazo += cnfb.rs.getDouble("valor");
                    }
                }
                
                vlr_total = vlr_curto_prazo + vlr_longo_prazo;
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            } finally {
                cnfb.desconecta();
            }
        }

        System.out.println("\nCurto prazo: R$ " + df.format(vlr_curto_prazo));
        System.out.println("Longo prazo: R$ " + df.format(vlr_longo_prazo));
        System.out.println("Endividamento total: R$ " + df.format(vlr_total));

    }

    public Double getVlr_curto_prazo() {
        return vlr_curto_prazo;
    }

    public Double getVlr_longo_prazo() {
        return vlr_longo_prazo;
    }

    public double getVlr_total() {
        return vlr_total;
    }
    
    

    public static void main(String[] args) {

        ConexaoFB.arquivo = "fbcasa.properties";
        Fin_SaldoBalanco saldo = new Fin_SaldoBalanco("30/11/2017");
    }

}
