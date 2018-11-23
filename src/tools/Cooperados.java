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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ferna
 */
public class Cooperados {

    private final conn.ConexaoFB cnfb = new ConexaoFB();
    private ConexaoMySQL cn;
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");

    private String sql, caminho;
    private Date competencia;

    private int associadosativos, associadostotal;
    
    public Cooperados(ConexaoMySQL conn){
        this.cn = conn;
    }

    public int getAssociadosativos() {
        return associadosativos;
    }

    public int getAssociadostotal(boolean x, String data, String empresa) {
        try {
            competencia = dateIn.parse(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        if (x) {
            contaCooperados(data, empresa);
        } else {
            sql = "SELECT * FROM dadoseconomicos "
                    + "WHERE empresa = '" + empresa + "' "
                    + "AND data = '" + dateOut.format(competencia) + "' "
                    + "AND tipo_inf IN (1466,1085);";
            if (cn.iniciarTransacao()) {
                try {
                    cn.executeConsulta(sql);
                    while (cn.rs.next()) {
                        if ("1466".equals(cn.rs.getString("tipo_inf"))) {
                            associadostotal = cn.rs.getInt("valor");
                        } else {
                            associadosativos = cn.rs.getInt("valor");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    cn.finalizarTransacao();
                }
            }
        }

        return associadostotal;
    }

    private void contaCooperados(String data, String empresa) {

        defineBD(empresa);

        sql = "with cooperados as ( select distinct b.pessoa_fj, "
                + "case when b.pessoa_fj = 'J' then left(b.cpf_cnpj,8) else b.cpf_cnpj end as inscricao, "
                + "b.sexo "
                + "from cad_terceiros_cooperados_hist a "
                + "left join cad_terceiros_cooperados aa on (aa.empresa = a.empresa and aa.codigo = a.codigo) "
                + "left join cad_terceiros b on (b.empresa = a.empresa and b.codigo = a.codigo) "
                + "where a.empresa = 1 and 'A' = (select first 1 c.tipo from cad_terceiros_cooperados_hist c "
                + "where c.empresa = 1 and c.codigo = b.codigo and c.data <= '" + data.replace("/", ".") + "' "
                + "order by c.data desc) AND (aa.id_cooperado is not null) ) "
                + "select count(z.inscricao) as quantidade "
                + "from cooperados z;";

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    associadosativos = associadostotal = cnfb.rs.getInt("quantidade");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cnfb.desconecta();
            }

            if (cn.iniciarTransacao()) {
                try {
                    sql = "DELETE FROM dadoseconomicos "
                            + "WHERE tipo_inf in (1466,1085) "
                            + "AND empresa = '" + empresa + "' "
                            + "AND data = '" + dateOut.format(competencia) + "';";
                    cn.executeAtualizacao(sql);
                    sql = "INSERT INTO dadoseconomicos (empresa,tipo_inf,valor,data)"
                            + "VALUES('" + empresa + "','1085','" + associadosativos + "',"
                            + "'" + dateOut.format(competencia) + "'),"
                            + "('" + empresa + "','1466','" + associadostotal + "',"
                            + "'" + dateOut.format(competencia) + "');";
                    cn.executeAtualizacao(sql);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    cn.finalizarTransacao();
                }
            }
        }
    }

    private String defineBD(String empresa) {
        if ("2".equals(empresa)) {
            if (cn.iniciarTransacao()) {
                try {
                    cn.executeConsulta("SELECT local FROM empresa WHERE id = '" + empresa + "';");
                    while (cn.rs.next()) {
                        caminho = cn.rs.getString("local");
                    }
                } catch (Exception e) {
                } finally {
                    cn.finalizarTransacao();
                }
            }
            cnfb.setUrl(caminho);
        }

        return caminho;
    }

//    public static void main(String[] args) {
//        TelaPrincipal ti = new TelaPrincipal();
//        Cooperados coop = new Cooperados();
//        System.out.println("Quantidade de associados: " + coop.getAssociadostotal(true, "31/12/2016", "2"));
//        ti.dispose();
//    }

}
