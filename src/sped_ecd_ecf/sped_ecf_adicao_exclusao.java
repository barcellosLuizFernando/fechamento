/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sped_ecd_ecf;

import conn.ConexaoFB;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class sped_ecf_adicao_exclusao {

    private final conn.ConexaoFB cnfb = new ConexaoFB();
    private final int ano;
    private String sql;
    private ArrayList lista = new ArrayList();
    private ArrayList lista_eLacs = new ArrayList();

    public sped_ecf_adicao_exclusao(int ano) {
        this.ano = ano;
        cnfb.arquivo = "fb.properties";
    }

    private String getQuery(int mes) throws Exception {

        sql = "select "
                + "    base.*,"
                + "    trunc(abs(base.valor),2) as valor_ajustado,"
                + "    case when base.valor < 0 then 'D' else 'C' end as tipo "
                + "from ("
                + "Select"
                + "    pc.codigo,"
                + "    pc.nome,"
                + "    pc.classificacao,"
                + "    sum(case when pc.codigo = l.credito then l.valor else l.valor*-1 end) as valor "
                + "from ctb_lancamentos l "
                + "inner join cad_planocontas pc on "
                + "    pc.empresa = l.empresa and "
                + "    (pc.codigo = l.debito or pc.codigo = l.credito) and "
                + "    pc.classificacao starting with '3' "
                + "where "
                + "    l.empresa = 1 and "
                + "    l.data between '01.01." + this.ano + "' and "
                + "'" + tools.ManipulaData.lastDayToBR(mes + "/" + ano).replace("/", ".") + "' and "
                + "    l.origem != 'ZER' "
                + "group by 1,2,3 ) base;";

        return sql;
    }

    private void calcLayout() throws Exception {

        lista.clear();
        Double somaCooperativas;
        Double somaIndeducoes;

        cnfb.conecta();

        try {
            for (int i = 1; i <= 12; i++) {
                lista_eLacs.clear();
                String mes;
                if (i < 10) {
                    mes = "0" + i;
                } else {
                    mes = i + "";
                }
                somaCooperativas = 0.00;
                somaIndeducoes = 0.00;
                lista.add("|M030|0101" + ano + "|" + tools.ManipulaData.lastDayToBR(i + "/" + ano).replace("/", "") + "|A" + mes + "|");

                cnfb.executeConsulta(this.getQuery(i));
                //System.out.println(this.getQuery(i));

                while (cnfb.rs.next()) {

                    if (cnfb.rs.getString("classificacao").startsWith("3.1")) {
                        somaCooperativas += cnfb.rs.getDouble("valor");
                    }

                    if (cnfb.rs.getString("codigo").equals("6852")) {
                        somaIndeducoes += cnfb.rs.getDouble("valor");
                    }
                }

                lista.add("|M300|8.65|(-) Multas impostas por transgressões de leis de natureza não tributária|A|2|"
                        + tools.FormatNumbers.numUsToBr(Math.abs(somaIndeducoes)).replace(".", "")
                        + "|Valor ref. multas indedutíveis|");

                lista_eLacs.add("|M350|8.65|(-) Multas impostas por transgressões de leis de natureza não tributária|A|2|"
                        + tools.FormatNumbers.numUsToBr(Math.abs(somaIndeducoes)).replace(".", "")
                        + "|Valor ref. multas indedutíveis|");

                cnfb.rs.beforeFirst();
                while (cnfb.rs.next()) {
                    if (cnfb.rs.getString("codigo").equals("6852")) {
                        lista.add("|M310|" + cnfb.rs.getString("codigo") + "||"
                                + tools.FormatNumbers.numUsToBr(cnfb.rs.getDouble("valor_ajustado")).replace(".", "")
                                + "|" + cnfb.rs.getString("tipo") + "|");
                        lista_eLacs.add("|M360|" + cnfb.rs.getString("codigo") + "||"
                                + tools.FormatNumbers.numUsToBr(cnfb.rs.getDouble("valor_ajustado")).replace(".", "")
                                + "|" + cnfb.rs.getString("tipo") + "|");
                    }
                }

                lista.add("|M300|111|(-) Cooperativas|E|2|"
                        + tools.FormatNumbers.numUsToBr(Math.abs(somaCooperativas)).replace(".", "")
                        + "|Valor ref. resultado com atos cooperativos|");

                cnfb.rs.beforeFirst();
                while (cnfb.rs.next()) {
                    if (cnfb.rs.getString("classificacao").startsWith("3.1")) {
                        lista.add("|M310|" + cnfb.rs.getString("codigo") + "||"
                                + tools.FormatNumbers.numUsToBr(cnfb.rs.getDouble("valor_ajustado")).replace(".", "")
                                + "|" + cnfb.rs.getString("tipo") + "|");
                        lista_eLacs.add("|M360|" + cnfb.rs.getString("codigo") + "||"
                                + tools.FormatNumbers.numUsToBr(cnfb.rs.getDouble("valor_ajustado")).replace(".", "")
                                + "|" + cnfb.rs.getString("tipo") + "|");
                    }
                }
                cnfb.rs.close();

                for (int j = 0; j < lista_eLacs.size(); j++) {
                    lista.add(lista_eLacs.get(j).toString());
                }

            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            cnfb.desconecta();

            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i).toString());
            }

        }

    }

    public static void main(String[] args) {
        sped_ecf_adicao_exclusao s = new sped_ecf_adicao_exclusao(2017);

        try {
            //System.out.println(s.getQuery(7));
            s.calcLayout();
        } catch (Exception e) {
            tools.DefaultMsg.errorMsg(e.getMessage());
        }
    }

}
