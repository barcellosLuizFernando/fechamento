/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.util.ArrayList;
import tools.FbGenerators;

/**
 *
 * @author luiz.barcellos
 */
public class ExportLancAntec {

    private final ConexaoMySQL cn;
    private String sql;
    private ConexaoFB cnfb = new ConexaoFB();

    public ExportLancAntec(ConexaoMySQL conn) {

        this.cn = conn;
        ArrayList lancamentos = new ArrayList();
        lancamentos.clear();

        try {
            cnfb.conecta();
            /*sql = "DELETE FROM REL_DESPESAS_ANTECI_CTB_LANC ;";
            cnfb.executeAtualizacao(sql);

            sql = "DELETE FROM CAD_DESPESAS_ANTECIPADAS ;";
            cnfb.executeAtualizacao(sql);
             */
            /*
            sql = "SELECT * FROM CAD_DESPESAS_ANTECIPADAS a where a.empresa = 1";
            cnfb.executeConsulta(sql);
            while (cnfb.rs.next()) {
                lancamentos.add(cnfb.rs.getString("lcto_ctb_vinculado"));
            }
            cnfb.rs.close();

            sql = " select "
                    + "     d.id, "
                    + "	d.data, "
                    + "	d.valor,  "
                    + "     d.status, "
                    + "	round(d.valor - coalesce((select sum(x.valor) from fechamento.desp_antecipada_detalhe x where x.id = d.id "
                    + "			and exists (select * from fechamento.desp_antecipada_lancamentos z where z.id = x.id and z.parcela = x.parcela)),0),2) as saldo, "
                    + "	p.data_inicial, "
                    + "    p.data_final, "
                    + "    l.descricao, "
                    + "    d.estab_debito, "
                    + "    l.cdebpad, "
                    + "    l.ccred "
                    + "from fechamento.desp_antecipada d "
                    + "left join fechamento.desp_antecipada_provisao p on p.id = d.id "
                    + "left join fechamento.lanc_padrao l on l.id = p.id_lanc_padrao  "
                    + "where (d.status is false or d.status is null) and p.data_final >= '2018-05-31';";
            cn.executeConsulta(sql);

            tools.FbGenerators gen = new FbGenerators();

            //int codigo = 1;
            while (cn.rs.next()) {
                if (!lancamentos.contains(cn.rs.getString("id"))) {
                    sql = "INSERT INTO CAD_DESPESAS_ANTECIPADAS (empresa, "
                            + "debito, credito, valor, dia, mes_ano_ini, mes_ano_fim,"
                            + "lcto_ctb_vinculado,id_transacao) "
                            + "VALUES(1, " + cn.rs.getString("cdebpad") + ","
                            + cn.rs.getString("ccred") + ",'" + cn.rs.getDouble("d.valor") + "',"
                            + cn.rs.getDate("data").getDate() + ",'"
                            + tools.ManipulaData.getCompetenceBR(cn.rs.getDate("data_inicial")) + "','"
                            + tools.ManipulaData.getCompetenceBR(cn.rs.getDate("data_final")) + "',"
                            + cn.rs.getString("id") + ","
                            + gen.getTransacao() +  ");";
                    cnfb.executeAtualizacao(sql);
                    //codigo++;
                }
            }
*/
            apropriaDetalhes();

            tools.DefaultMsg.saveDataSuccessfull();

        } catch (Exception e) {
            tools.DefaultMsg.errorMsg(e.getMessage());
        } finally {
            cnfb.desconecta();
            //cn.finalizarTransacao();
        }

    }

    private void apropriaDetalhes() throws Exception {

        int codigo = 0;
        ArrayList lancamentos = new ArrayList();
        ArrayList lancamentosDet = new ArrayList();
        lancamentos.clear();
        lancamentosDet.clear();

        sql = "SELECT * FROM CAD_DESPESAS_ANTECIPADAS a where a.empresa = 1";
        cnfb.executeConsulta(sql);
        while (cnfb.rs.next()) {
            lancamentos.add(cnfb.rs.getString("lcto_ctb_vinculado"));
        }
        cnfb.rs.close();

        sql = "SELECT * from REL_DESPESAS_ANTECI_CTB_LANC a where a.empresa = 1";
        cnfb.executeConsulta(sql);
        while (cnfb.rs.next()) {
            lancamentosDet.add(cnfb.rs.getString("numero_ctb"));
        }
        cnfb.rs.close();

        sql = "SELECT a.id, b.data, a.numero  "
                + "FROM fechamento.desp_antecipada_lancamentos a "
                + "left join fechamento.desp_antecipada_detalhe b on b.id = a.id and b.parcela = a.parcela "
                + "WHERE a.data > '2018-06-01' ;";
        cn.executeConsulta(sql);
        while (cn.rs.next()) {
            if (lancamentos.contains(cn.rs.getString("id"))
                    && !lancamentosDet.contains(cn.rs.getString("numero"))) {
                sql = "SELECT * FROM CAD_DESPESAS_ANTECIPADAS d "
                        + "WHERE d.lcto_ctb_vinculado = " + cn.rs.getString("id") + ";";
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    codigo = cnfb.rs.getInt("codigo");
                }
                cnfb.rs.close();

                sql = "INSERT INTO REL_DESPESAS_ANTECI_CTB_LANC (empresa, "
                        + "cod_despesa, numero_ctb, mes_ano) VALUES (1," + codigo + ","
                        + cn.rs.getString("numero") + ",'"
                        + tools.ManipulaData.getCompetenceBR(cn.rs.getDate("data")) + "');";
                cnfb.executeAtualizacao(sql);

            }
        }

    }

}
