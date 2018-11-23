package tools;

import conn.ConexaoORCL;
import javax.swing.JOptionPane;

public class FolhaPagamentos {

    private final conn.ConexaoORCL orcl = new ConexaoORCL();
    private String sql;
    private double ben = 0.00;
    private double sval = 0.00;
    private double snom = 0.00;
    private double hep = 0.00;
    private double hpd = 0.00;
    private double hep_valor = 0.00;
    private double hpa = 0.00;
    private double fpgto = 0.00;

    public FolhaPagamentos(int empresa, int month, int year) {

        try {
            orcl.conecta();
            sql = " SELECT "
                    + "C.PERREF, FF.NUMEMP AS EMPRESA, C.CODCAL AS CALCULO, "
                    + "F.CODFIL AS FILIAL, FF.NUMCAD AS COD_COLAB, "
                    + "F.NOMFUN AS FUNCIONARIO, E.CODEVE, E.DESEVE AS DESCRICAO, "
                    + "E.TIPEVE AS TIPO, FF.REFEVE AS REFERENCIA, "
                    + "CASE WHEN E.TIPEVE IN (1) THEN FF.VALEVE ELSE 0 END AS PROVENTOS, "
                    + "CASE WHEN E.TIPEVE IN (2) THEN FF.VALEVE ELSE 0 END AS VANTAGENS, "
                    + "CASE WHEN E.TIPEVE IN (3) THEN FF.VALEVE ELSE 0 END AS DESCONTOS, "
                    + "CASE WHEN E.TIPEVE IN (4) THEN FF.VALEVE ELSE 0 END AS INFORMATIVO, "
                    + "FF.VALEVE, "
                    + "CASE WHEN EXISTS (SELECT * FROM VETORH.R008TOB T WHERE T.TABEVE = E.CODTAB AND T.CODEVE = E.CODEVE and T.CODTOT = 7) THEN 1 else 0 end as HEP, "
                    + "CASE WHEN UPPER(E.DESEVE) LIKE '%EXTRA%' or UPPER(E.DESEVE) LIKE '%ESPERA%' THEN 1 ELSE 0 END AS HEP_VALOR, "
                    + "CASE WHEN E.CODEVE = 1 THEN 1 ELSE 0 END AS SNOM, "
                    + "CASE E.CRTEVE WHEN '39I' THEN 1 ELSE 0 END AS SVAR, "
                    + "CASE E.CRTEVE WHEN '49H' THEN 1 ELSE 0 END AS HPD, "
                    + "case when E.TIPEVE = 1 or upper(E.DESEVE) like '%FGTS%' then 1 else 0 end as fpgto "
                    + "FROM R046VER FF /*TABELA FICHA FINANCEIRA*/ "
                    + "INNER JOIN R044CAL C ON FF.NUMEMP=C.NUMEMP AND FF.CODCAL=C.CODCAL AND C.PERREF between to_date('" + year + "/" + month + "/01', 'yyyy/mm/dd') and to_date('" + tools.ManipulaData.lastDayToBR(month + "/" + year) + "', 'dd/mm/yyyy') AND C.TIPCAL NOT IN (91) /*TABELA CADASTRO CALCULO*/ "
                    + "LEFT JOIN R034FUN F ON FF.NUMEMP=F.NUMEMP AND FF.NUMCAD=F.NUMCAD AND FF.TIPCOL=F.TIPCOL /*TABELA CADASTRO FUNCIONARIOS*/ "
                    + "LEFT JOIN R008EVC E ON FF.TABEVE=E.CODTAB AND FF.CODEVE=E.CODEVE /*TABELA CADASTRO EVENTOS*/ "
                    + "WHERE FF.NUMEMP = " + empresa;

            orcl.executeConsulta(sql);
            while (orcl.rs.next()) {

                if (orcl.rs.getBoolean("fpgto")) {
                    fpgto += orcl.rs.getDouble("valeve");
                }

                if (orcl.rs.getBoolean("hep")) {
                    hep += orcl.rs.getDouble("referencia");
                }

                if (orcl.rs.getBoolean("hep_valor")) {
                    hep_valor += orcl.rs.getDouble("proventos");
                }

                if (orcl.rs.getBoolean("snom")) {
                    snom += orcl.rs.getDouble("proventos");
                }

                if (orcl.rs.getBoolean("svar")) {
                    sval += orcl.rs.getDouble("proventos");
                }

                if (orcl.rs.getBoolean("hpd")) {
                    hpd += orcl.rs.getDouble("referencia");
                }

            }
            orcl.rs.close();

            /**
             * CONSULTA INSS PATRONAL.
             */
            sql = "select (A.VALEMP + A.VALTER) as valor "
                    + "from VETORH.R054GRP A "
                    + "where A.COMGRP = to_date('01/" + month + "/" + year + "','dd/mm/yyyy') and "
                    + "a.NUMEMP = " + empresa + " AND A.ORISAL != 16 AND A.ORISAL != 2";
            orcl.executeConsulta(sql);
            while (orcl.rs.next()) {
                fpgto += orcl.rs.getDouble("valor");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            orcl.desconecta();
        }

    }

    public double getBen() {
        return ben;
    }

    public double getSval() {
        return sval;
    }

    public double getSnom() {
        return snom;
    }

    public double getHep() {
        return hep;
    }

    public double getHpd() {
        return hpd;
    }

    public double getHep_valor() {
        return hep_valor;
    }

    public double getHpa() {
        return hpa;
    }

    public double getFpgto() {
        return fpgto;
    }

}
