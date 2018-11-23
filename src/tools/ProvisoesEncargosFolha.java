/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoFB;
import conn.ConexaoMySQL;
import conn.ConexaoORCL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class ProvisoesEncargosFolha {

    private final ConexaoMySQL cn;
    private final ConexaoFB cnfb = new ConexaoFB();
    private final ConexaoORCL cnSen = new ConexaoORCL();
    private final ManipulaData mData = new ManipulaData();
    private final EmpresaFilial empfil;
    private final IntegraFinanceiro integ;

    private String sql;
    private double saldoContabil;
    private String contaContabil;

    public ProvisoesEncargosFolha(ConexaoMySQL conn) {
        this.cn = conn;
        this.empfil = new EmpresaFilial(conn);
        this.integ = new IntegraFinanceiro(conn, false);
    }

    public void consultaFGTS(String competencia) throws Exception {

        sql = "select ff.NUMEMP, ff.CODEVE, z.CODFIL, fil.NOMFIL, E.DESEVE, sum(ff.VALEVE) as VALEVE "
                + "from vetorh.R046VER ff "
                + "JOIN R044CAL C ON FF.NUMEMP=C.NUMEMP AND FF.CODCAL=C.CODCAL "
                + "LEFT JOIN ( "
                + "    select distinct a.NUMEMP, a.TIPCOL, a.NUMCAD, a.DATALT, a.CODFIL, a.TIPADM, a.STAHIS "
                + "    from VETORH.R038HFI a "
                + "    WHERE a.NUMEMP = 1 and a.TIPCOL = 1 "
                + "    and a.DATALT = (select max(b.datalt) from vetorh.R038HFI b where b.NUMEMP = a.NUMEMP and b.TIPCOL = a.TIPCOL and b.NUMCAD = a.NUMCAD and b.DATALT <= '" + tools.FormatNumbers.dateBrDefault(mData.lastDay(competencia)) + "') "
                + ") z on (z.NUMEMP = ff.NUMEMP AND z.NUMCAD = ff.NUMCAD and z.TIPCOL = ff.TIPCOL) "
                + "left join VETORH.R030FIL fil on (fil.NUMEMP = z.NUMEMP and fil.CODFIL = z.CODFIL) "
                + "JOIN vetorh.R008EVC E ON FF.TABEVE=E.CODTAB AND FF.CODEVE=E.CODEVE "
                + "WHERE ff.NUMEMP = 1 and C.PERREF = '01/" + competencia + "' and (ff.CODEVE = 300 or ff.CODEVE = 393) "
                + "GROUP BY ff.NUMEMP, ff.CODEVE, z.CODFIL, fil.NOMFIL, E.DESEVE ";

        gravaDados(sql, competencia, "FGTS");

    }

    public void consultaContabil(String competencia, String conta) throws Exception {

        sql = "select "
                + "p.CODIGO || ' - '||p.NOME as conta, "
                + "abs(round(sum(case when c.DEBITO = p.CODIGO then c.VALOR else c.VALOR*-1 end),2)) as saldo "
                + "from CTB_LANCAMENTOS c "
                + "inner join cad_planocontas p on p.EMPRESA = c.EMPRESA and (p.CODIGO = c.DEBITO or p.CODIGO = c.CREDITO) and p.CODIGO = '" + conta + "' "
                + "where c.EMPRESA = 1 and c.\"DATA\" <= '" + tools.ManipulaData.lastDayToBR(competencia).replace("/", ".") + "' "
                + "group by 1";

        cnfb.conecta();
        cnfb.executeConsulta(sql);
        while (cnfb.rs.next()) {
            saldoContabil = cnfb.rs.getDouble("saldo");
            contaContabil = cnfb.rs.getString("conta");
        }
        cnfb.desconecta();
    }

    public double getSaldoContabil() {
        return saldoContabil;
    }

    public String getContaContabil() {
        return contaContabil;
    }

    public void consultaINSS(String competencia) throws Exception {

        /**
         * COLUNA ORISAL: 16 = FUNRURAL (consulta no método próprio); 02 = 13º
         * SALÁRIO (não consulta porque provisão é feita manual dia 20 de
         * dezembro.
         */
        sql = "select a.NUMEMP, a.CODFIL, fil.NOMFIL, "
                + "CASE WHEN A.ORISAL = 16 THEN '99999' ELSE '99998' END as codeve, "
                + "CASE WHEN A.ORISAL = 16 THEN 'FUNRURAL' ELSE 'INSS' END as deseve, "
                + "sum(a.VALSEG + a.VALEMP + a.VALTER - a.VALDED) as valeve "
                + "from vetorh.R054GRP a "
                + "left join VETORH.R030FIL fil on (fil.NUMEMP = a.NUMEMP and fil.CODFIL = a.CODFIL) "
                + "where a.COMGRP = '01/" + competencia + "' and a.NUMEMP = 1 AND A.ORISAL != 16 AND A.ORISAL != 2 "
                + " GROUP BY fil.NOMFIL, a.numemp, a.codfil, a.ORISAL";

        System.out.println(sql);

        gravaDados(sql, competencia, "INSS");
    }

    public void consultaFUNRURAL(String competencia) throws Exception {

        sql = "select a.NUMEMP, a.CODFIL, fil.NOMFIL, "
                + "CASE WHEN A.ORISAL = 16 THEN '99999' ELSE '99998' END as codeve, "
                + "CASE WHEN A.ORISAL = 16 THEN 'FUNRURAL' ELSE 'INSS' END as deseve, "
                + "sum(a.VALSEG + a.VALEMP + a.VALTER - a.VALDED) as valeve "
                + "from vetorh.R054GRP a "
                + "left join VETORH.R030FIL fil on (fil.NUMEMP = a.NUMEMP and fil.CODFIL = a.CODFIL) "
                + "where a.COMGRP = '01/" + competencia + "' and a.NUMEMP = 1 AND A.ORISAL = 16 "
                + " GROUP BY fil.NOMFIL, a.numemp, a.codfil, a.ORISAL";

        System.out.println(sql);

        gravaDados(sql, competencia, "FUNRURAL");
    }

    public void consultaPIS(String competencia) throws Exception {

        sql = " SELECT B.NUMEMP, 1 AS CODFIL, 'MATRIZ' AS NOMFIL, "
                + "'99997' AS CODEVE, 'PIS' AS DESEVE, "
                + "SUM(CONCAT(C.TIPSIN,A.VALEVE))*0.01 AS VALEVE "
                + "FROM VETORH.R044CAL B "
                + "LEFT JOIN VETORH.R046VER A ON (B.NUMEMP = A.NUMEMP AND B.CODCAL = A.CODCAL) "
                + "INNER JOIN VETORH.R008TOB C ON (C.TABEVE = 941 AND C.CODTOT = 10 AND C.CODEVE = A.CODEVE) "
                + "WHERE B.NUMEMP = 1 AND B.PERREF = '01/" + competencia + "' "
                + "GROUP BY B.NUMEMP";

        System.out.println(sql);

        gravaDados(sql, competencia, "PIS");
    }

    public void consultaProvContabil(String competencia, String tax, String account) throws Exception {

        sql = queryDefault(competencia, account);
        System.out.println(sql);
        gravaDados(sql, competencia, tax, true);

    }

    private String queryDefault(String competencia, String account) throws Exception {

        return "select "
                + "1 as numemp, e.CODIGO as codfil, e.NOME as nomfil, "
                + "pc.CODIGO as codeve, pc.NOME as deseve, "
                + "abs(round(sum(case when pc.CODIGO = l.DEBITO then l.VALOR else l.VALOR *-1 end),2))  as valeve "
                + "from ctb_lancamentos l "
                + "inner join CAD_PLANOCONTAS pc on pc.EMPRESA = l.EMPRESA and (pc.CODIGO = l.DEBITO or pc.CODIGO = l.CREDITO) and pc.CODIGO = " + account + " "
                + "left join CAD_ESTABELECIMENTOS e on e.EMPRESA = l.EMPRESA and (e.CODIGO = l.ESTAB_DEBITO or e.CODIGO = l.ESTAB_CREDITO) "
                + "where l.EMPRESA = 1 and l.\"DATA\" between '01." + competencia.replace("/", ".") + "' and "
                + "'" + tools.ManipulaData.lastDayToBR(competencia).replace("/", ".") + "' and l.ORIGEM not in ('CAP','CAPB') "
                + "group by e.CODIGO, e.NOME, pc.CODIGO, pc.NOME;";

    }

    public void consultaIRRF(String competencia) throws Exception {

        sql = "SELECT B.NUMEMP, 1 AS CODFIL, 'MATRIZ' AS NOMFIL, a.CODEVE, "
                + "e.DESEVE, SUM(A.VALEVE) AS VALEVE "
                + "FROM VETORH.R044CAL B "
                + "LEFT JOIN VETORH.R046VER A ON (B.NUMEMP = A.NUMEMP AND B.CODCAL = A.CODCAL) "
                + "INNER JOIN vetorh.R008EVC E ON a.TABEVE=E.CODTAB AND a.CODEVE=E.CODEVE AND E.DESEVE LIKE '%IRRF%' AND E.TIPEVE = 3 "
                + "WHERE B.NUMEMP = 1 AND B.PERREF = '01/" + competencia + "' "
                + "GROUP BY B.NUMEMP, A.CODEVE, E.DESEVE";

        System.out.println(sql);

        gravaDados(sql, competencia, "IRRF");
    }

    /**
     * CONSULTA DADOS NA CONTABILIDADE. Se ctb for falso, consulta na folha de
     * pagamentos.
     *
     * @param consulta
     * @param competencia
     * @param encargo
     * @param ctb
     */
    private void gravaDados(String consulta, String competencia, String encargo, Boolean ctb) throws Exception {

        if (ctb) {
            String numemp;
            String codfil;
            String codeve;
            String nomfil;
            String valeve;
            String financeiro;
            String deseve;
            String unidade;

            try {
                sql = "DELETE FROM fechamento.folha_provisao WHERE encargo = '" + encargo + "' and competencia = '" + competencia + "';";

                cnfb.conecta();
                cn.iniciarTransacao();
                cn.executeAtualizacao(sql);
                cn.rs.close();

                cnfb.executeConsulta(consulta);
                while (cnfb.rs.next()) {
                    numemp = cnfb.rs.getString("NUMEMP");
                    codfil = unidade = cnfb.rs.getString("CODFIL");
                    codeve = cnfb.rs.getString("CODEVE");
                    nomfil = cnfb.rs.getString("NOMFIL");
                    valeve = cnfb.rs.getString("VALEVE");
                    deseve = cnfb.rs.getString("DESEVE");

                    sql = "INSERT INTO fechamento.folha_provisao (numemp, codfil, "
                            + "codeve, competencia, nomfil, valeve, encargo, deseve, unidade) "
                            + "VALUES ('" + numemp + "','" + codfil + "',"
                            + "'" + codeve + "','" + competencia + "','" + nomfil + "',"
                            + "'" + valeve + "','" + encargo + "','" + deseve + "',"
                            + "'" + unidade + "');";
                    cn.executeAtualizacao(sql);
                    cn.rs.close();
                }

            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
                cn.finalizarTransacao();
            }

        } else {
            gravaDados(consulta, competencia, encargo);
        }

    }

    /**
     * CONSULTA DADOS NA FOLHA DE PAGAMENTOS.
     *
     * @param consulta
     * @param competencia
     * @param encargo
     * @throws Exception
     */
    private void gravaDados(String consulta, String competencia, String encargo) throws Exception {

        String numemp;
        String codfil;
        String codeve;
        String nomfil;
        String valeve;
        String financeiro;
        String deseve;
        String unidade;

        sql = "DELETE FROM fechamento.folha_provisao WHERE encargo = '" + encargo + "' and competencia = '" + competencia + "';";

        if (cnSen.conecta()) {
            try {

                if (cn.iniciarTransacao()) {
                    if (cn.executeAtualizacao(sql) == false) {
                        cn.finalizarTransacao();
                        throw new UnsupportedOperationException("Não foi possível excluir a provisão da competência " + competencia + ".");
                    }
                } else {
                    throw new UnsupportedOperationException("Não foi possível excluir a provisão da competência " + competencia + ".");
                }

                cnSen.executeConsulta(consulta);

                while (cnSen.rs.next()) {

                    numemp = cnSen.rs.getString("NUMEMP");
                    codfil = cnSen.rs.getString("CODFIL");
                    codeve = cnSen.rs.getString("CODEVE");
                    nomfil = cnSen.rs.getString("NOMFIL");
                    valeve = cnSen.rs.getString("VALEVE");
                    deseve = cnSen.rs.getString("DESEVE");

                    empfil.codFilial("1", codfil, "FOLHA");

                    unidade = empfil.getFilCont();

                    sql = "INSERT INTO fechamento.folha_provisao (numemp, codfil, "
                            + "codeve, competencia, nomfil, valeve, encargo, deseve, unidade) "
                            + "VALUES ('" + numemp + "','" + codfil + "',"
                            + "'" + codeve + "','" + competencia + "','" + nomfil + "',"
                            + "'" + valeve + "','" + encargo + "','" + deseve + "',"
                            + "'" + unidade + "');";

                    if (cn.executeAtualizacao(sql) == false) {
                        throw new UnsupportedOperationException("Não foi possível gravar os dados da provisão.");
                    }
                }
            } catch (Exception e) {
                throw new Exception("Não foi possível gravar os dados para o encargo " + encargo + ".\n" + e.getMessage());
            } finally {
                cn.finalizarTransacao();
                cnSen.desconecta();
            }

        }

    }

    public boolean lancaFinanceiro(String unidade, String valor,
            String competencia, String encargo) throws Exception {

        boolean resposta = false;

        int diaVencimento;
        String fornecedor;
        String planoconta;
        String tipodoc;
        String id = null;
        String valor_bruto = valor;
        String historico1 = encargo + " " + competencia;
        String historico2 = historico1;
        String contaFornecedor;
        Date dt_emissao = mData.lastDay(competencia);
        String doc = competencia;

        /**
         * FORNECEDOR: 1684 - CAIXA ECONÔMICA FEDERAL 1203 - Ministerio da
         * Fazenda Federal
         *
         * CONTAS ALTERNATIVAS: 1581 - PIS folha de Pagamentos a Recolher 1580 -
         * FUNRURAL a recolher 1579 - INSS a recolher 56699 - IRRF folha a
         * recolher 1573 - FGTS a recolher
         *
         * CONTAS DESPESA: 6136 - FGTS 6137 - INSS 6138 - PIS FOLHA DE
         * PAGAMENTOS 6132 - ORDENADOS E SALARIOS 1572 - Ordenados e Salários a
         * pagar
         */
        switch (encargo) {
            case "INSS":
                diaVencimento = 20;
                fornecedor = "1203";
                planoconta = "6137";
                contaFornecedor = "1579";
                tipodoc = "19";
                break;
            case "FUNRURAL":
                diaVencimento = 20;
                fornecedor = "1203";
                planoconta = "6137";
                contaFornecedor = "1580";
                tipodoc = "19";
                break;
            case "FGTS":
                diaVencimento = 7;
                fornecedor = "1684";
                planoconta = "6136";
                contaFornecedor = "1573";
                tipodoc = "19";
                break;
            case "IRRF":
                diaVencimento = 20;
                fornecedor = "1203";
                planoconta = "1572";
                contaFornecedor = "56699";
                tipodoc = "15";
                break;
            case "PIS":
                diaVencimento = 25;
                fornecedor = "1203";
                planoconta = "6138";
                contaFornecedor = "1581";
                tipodoc = "15";
                break;
            case "IRRF_T":
                diaVencimento = 25;
                fornecedor = "1203";
                planoconta = "1572";
                contaFornecedor = "1563";
                tipodoc = "15";
                break;
            case "CONTR_T":
                diaVencimento = 25;
                fornecedor = "1203";
                planoconta = "1562";
                contaFornecedor = "10841";
                tipodoc = "15";
                break;
            default:
                diaVencimento = 0;
                fornecedor = null;
                planoconta = null;
                tipodoc = null;
                contaFornecedor = null;
        }

        System.out.println("\nEncargo: " + encargo + ". Dia de vencimento: " + diaVencimento);

        Date dt_vencimento = mData.vencimentoDiaUtil(competencia, diaVencimento, true);
        System.out.println("Número documento: " + doc);

        if (integ.integraFinanceiro(id, tipodoc, fornecedor, doc, dt_emissao, dt_vencimento, planoconta, valor_bruto, historico2, contaFornecedor, historico1, false, unidade, true)) {
            sql = "UPDATE fechamento.folha_provisao SET financeiro = '" + integ.getCodigo() + "' "
                    + "WHERE competencia = '" + competencia + "' "
                    + "AND encargo = '" + encargo + "' "
                    + "AND unidade = '" + unidade + "';";
            cn.iniciarTransacao();
            resposta = cn.executeAtualizacao(sql);
            cn.finalizarTransacao();
        } else {

            resposta = false;

        }

        return resposta;
    }

    public boolean deletaFinanceiro(String codigo) {
        boolean resposta = false;

        if (integ.deletaFinanceiro(codigo)) {
            sql = "UPDATE fechamento.folha_provisao SET financeiro = null "
                    + "WHERE financeiro = '" + codigo + "';";
            try {
                cn.iniciarTransacao();
                resposta = cn.executeAtualizacao(sql);
            } catch (SQLException e) {
                tools.DefaultMsg.errorMsg(e.getMessage());
            } finally {
                cn.finalizarTransacao();
            }
        }

        return resposta;
    }

    public static void main(String[] args) {
        try {

            ConexaoMySQL cn = new ConexaoMySQL();
            cn.arquivo = "mysqlcasa.properties";
            cn.conecta("luiz.barcellos", "Lu!z12345");

            ProvisoesEncargosFolha prov = new ProvisoesEncargosFolha(cn);
            //prov.consultaFGTS("10/2017");
            //prov.consultaINSS("10/2017");
            prov.lancaFinanceiro(null, null, "10/2017", "PIS");
            cn.desconecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
