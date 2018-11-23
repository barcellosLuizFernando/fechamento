/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoMySQL;
import conn.ConexaoORCL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Funcionarios {

    private String sql, nome, status, cargo, instrucao, uf, tipsex, empresaMy;
    private int empresa, codigo, grains, idade, faixa, ativos, demitidos, admitidos;
    private Date admissao, nascimento, competencia;
    private ConexaoORCL orcl = new ConexaoORCL();
    private ConexaoMySQL cn;
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private final EmpresaFilial emp;

    private int deslPed;
    private int desl12;
    private int desl6;
    private int desl3;

    public Funcionarios(ConexaoMySQL conn) {
        this.cn = conn;
        this.emp = new EmpresaFilial(conn);
    }

    /**
     * Pesquisa dados da folha de pagamentos.
     *
     * @param x FALSE = pesquisa na base FECHAMENTO. TRUE = pesquisa na base da
     * FOLHA.
     * @param codigo ID do funcionario na Folha de Pagamentos. Informe "0" para
     * pesquisar TODOS.
     * @param empresa ID da empresa na Folha de Pagamentos
     * @param dataInicial
     * @param dataFinal
     */
    public void buscaDados(boolean x, int codigo, int empresa, String dataInicial, String dataFinal) {

        admitidos = demitidos = ativos = 0;

        emp.codEmpresa(empresa + "", "FOLHA");
        empresaMy = emp.getCodFech();

        try {
            competencia = dateIn.parse(dataFinal);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        if (x) {
            sql = "with ativos as ("
                    + "select 'Trabalhando' as statusAtual,fun.numemp, fun.numcad, fun.nomfun, fun.codcar, car.titred as cargo, fun.datadm, fun.tipsex, fun.grains, inst.DESGRA, fun.DATNAS, "
                    + "TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) AS idade, "
                    + "case when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12)  <= 17 then 17 "
                    + "     when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 17 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 24 then 24 "
                    + "     when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 24 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 45 then 45 "
                    + "     when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 45 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 55 then 55 "
                    + "     when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 55 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 64 then 64 "
                    + "else 99 end as faixa, "
                    + "fil.codest as UF "
                    + "from r034fun fun "
                    + "left join r024car car on (car.codcar = fun.codcar) "
                    + "left join r022gra inst on (inst.grains = fun.grains) "
                    + "left join r030fil fil on (fil.numemp = fun.numemp and fil.codfil = fun.codfil) "
                    + "where fun.numemp = '" + empresa + "' and fun.sitafa != '7' and fun.tipcol = 1 and fun.DATADM <= '" + dataFinal + "' "
                    + "union all "
                    + "select 'Demitido' as statusAtual, fun.numemp, fun.numcad, fun.nomfun, fun.codcar, car.titred as cargo, fun.datadm, fun.tipsex, fun.grains, inst.DESGRA, fun.DATNAS, "
                    + "TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) AS idade, "
                    + "case when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12)  <= 17 then 17 "
                    + "    when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 17 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 24 then 24 "
                    + "    when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 24 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 45 then 45 "
                    + "    when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 45 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 55 then 55 "
                    + "    when TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) > 55 and TRUNC((MONTHS_BETWEEN(TO_DATE('" + dataFinal + "','dd/mm/yyyy'), fun.datnas))/12) <= 64 then 64 "
                    + "else 99 end as faixa, "
                    + "fil.codest as UF "
                    + "from r034fun fun "
                    + "left join r024car car on (car.codcar = fun.codcar) "
                    + "left join r022gra inst on (inst.grains = fun.grains)  "
                    + "left join r030fil fil on (fil.numemp = fun.numemp and fil.codfil = fun.codfil)   "
                    + "where fun.numemp = '" + empresa + "' and fun.sitafa = '7' and fun.tipcol = 1 and fun.DATAFA > '" + dataFinal + "' and fun.DATADM <= '" + dataFinal + "' ) "
                    + "select distinct * from ativos ";

            if (codigo > 0) {
                sql += "WHERE numcad = '" + codigo + "' ";
            }

            sql += "ORDER BY nomfun ";

            if (orcl.conecta()) {
                try {
                    orcl.executeConsulta(sql);
                    while (orcl.rs.next()) {
                        status = orcl.rs.getString("statusatual");
                        nome = orcl.rs.getString("nomfun");
                        cargo = orcl.rs.getString("cargo");
                        instrucao = orcl.rs.getString("desgra");
                        uf = orcl.rs.getString("uf");
                        tipsex = orcl.rs.getString("tipsex");
                        this.empresa = orcl.rs.getInt("numemp");
                        this.codigo = orcl.rs.getInt("numcad");
                        this.grains = orcl.rs.getInt("grains");
                        this.idade = orcl.rs.getInt("idade");
                        this.faixa = orcl.rs.getInt("faixa");
                        admissao = orcl.rs.getDate("datadm");
                        nascimento = orcl.rs.getDate("datnas");
                        ativos++;
                        System.out.println("Funcionário: " + nome);
                    }
                    orcl.rs.close();

                    /**
                     * CONSULTA DOS ADMITIDOS.
                     */
                    System.out.println("Consulta dos Admitidos.");
                    sql = "select * from r034fun a where a.datadm between '" + dataInicial + "' and '" + dataFinal + "' and a.tipcol = 1 and a.numemp = '" + empresa + "' ";

                    orcl.executeConsulta(sql);
                    while (orcl.rs.next()) {
                        admitidos++;
                    }
                    orcl.rs.close();

                    /**
                     * CONSULTA DOS DEMITIDOS.
                     */
                    System.out.println("Consulta dos Demitidos:");
                    sql = "select a.datafa, a.DATADM, "
                            + "round((a.DATAFA - a.DATADM)/30) as dias, "
                            + "case when exists(select * from vetorh.R042RCM b "
                            + "     where b.NUMEMP = a.NUMEMP and "
                            + "         b.NUMCAD = a.NUMCAD and "
                            + "         (b.CODSAQ is null or b.CODSAQ = ' ') and "
                            + "         b.DATDEM = a.DATAFA) then 1 else 0 end as ped_empregado "
                            + "from r034fun a where a.datafa between '" + dataInicial + "' and '" + dataFinal + "' and "
                            + "a.tipcol = 1 and a.numemp = '" + empresa + "' "
                            + "and a.sitafa = 7 ";

                    orcl.executeConsulta(sql);

                    while (orcl.rs.next()) {

                        int dias = orcl.rs.getInt("dias");

                        if (dias <= 3) {
                            desl3++;
                        } else if (dias <= 6) {
                            desl6++;
                        } else if (dias <= 12) {
                            desl12++;
                        }

                        /*
                                listar quem saiu para sair;
                         */
                        if (orcl.rs.getBoolean("ped_empregado")) {
                            deslPed++;
                        }

                        demitidos++;
                    }

                    gravaDados("1086", admitidos + "");
                    gravaDados("1464", demitidos + "");
                    gravaDados("1086", ativos + "");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    orcl.desconecta();
                }

            }

        } else {

            ativos = pesquisaDados("1086", dataFinal);
            admitidos = pesquisaDados("1465", dataFinal);
            demitidos = pesquisaDados("1464", dataFinal);

        }
        System.out.println("Quantidade Ativos: " + ativos);
        System.out.println("Quantidade Admissões: " + admitidos);
        System.out.println("Quantidade Demissões: " + demitidos);
    }

    /**
     * Busca dados na base FECHAMENTO.
     *
     * @param tipo_inf
     * @param data
     * @return
     */
    private int pesquisaDados(String tipo_inf, String data) {
        int valor = 0;

        if (cn.iniciarTransacao()) {
            try {
                sql = "SELECT * FROM dadoseconomicos "
                        + "WHERE empresa = '" + empresaMy + "' "
                        + "AND data = '" + dateOut.format(competencia) + "' "
                        + "AND tipo_inf = '" + tipo_inf + "';";
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    valor = cn.rs.getInt("valor");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }
        return valor;
    }

    private void gravaDados(String tipo_inf, String valor) {
        if (cn.iniciarTransacao()) {
            try {
                sql = "DELETE FROM dadoseconomicos "
                        + "WHERE tipo_inf = '" + tipo_inf + "' "
                        + "AND empresa = '" + empresaMy + "' "
                        + "AND data = '" + dateOut.format(competencia) + "';";
                cn.executeAtualizacao(sql);
                sql = "INSERT INTO dadoseconomicos (empresa,tipo_inf,valor,data)"
                        + "VALUES('" + empresaMy + "','" + tipo_inf + "','" + valor + "',"
                        + "'" + dateOut.format(competencia) + "');";
                cn.executeAtualizacao(sql);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foram gravados os dados dos funcionários.");
        }
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public String getCargo() {
        return cargo;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public String getUf() {
        return uf;
    }

    public String getTipsex() {
        return tipsex;
    }

    public int getDeslPed() {
        return deslPed;
    }

    public int getDesl12() {
        return desl12;
    }

    public int getDesl6() {
        return desl6;
    }

    public int getDesl3() {
        return desl3;
    }

    public int getEmpresa() {
        return empresa;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getGrains() {
        return grains;
    }

    public int getIdade() {
        return idade;
    }

    public int getFaixa() {
        return faixa;
    }

    public int getAtivos() {
        return ativos;
    }

    public int getDemitidos() {
        return demitidos;
    }

    public int getAdmitidos() {
        return admitidos;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public Date getNascimento() {
        return nascimento;
    }

    /*public static void main(String[] args) {
        Funcionarios fun = new Funcionarios();
        fun.buscaDados(false, 0, 7, "01/09/2017", "30/09/2017");
    }*/
}
