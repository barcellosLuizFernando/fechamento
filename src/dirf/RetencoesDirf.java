/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dirf;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class RetencoesDirf {

    private final conn.ConexaoFB cnfb = new conn.ConexaoFB();
    private dirf.PersonDirf person;

    //private String cpfcnpj;
    //private String registro;
    private String sql;
    private final ArrayList<dirf.PersonDirf> lancamentos = new ArrayList();
    private String baseDeCalculo;
    private String retencao;

    public void buildLayout(String dti, String dtf) throws Exception {

        sql = " with "
                + "irrf as ( select "
                + "i.empresa,i.codigo,count(i.codigo) as contagem "
                + "from fin_contas_pagar_detalhe i "
                + "where i.empresa = 1 and ( i.planoconta in ( 1563, 1564, 10840, 10841 ) ) "
                + "group by 1,2 ) "
                + "select * from ( "
                + "select "
                + "'RTRT' as tiporegistro, pg.codigo as lancamento, "
                + "p.dt_emissao as data, "
                + "lpad(extract (month from p.dt_emissao),2,0) as mes, "
                + "extract (year from p.dt_emissao) as ano, "
                + "p.doc, "
                + "t.nome as fornecedor, "
                + "t.cpf_cnpj, "
                + "pg.planoconta ||' - '|| pc.nome as conta, "
                + "case when pg.tipo_plano = 'D' then pg.valor else 0 end as debito, "
                + "case when pg.tipo_plano = 'C' then pg.valor else 0 end as credito, "
                + "t.pessoa_fj,"
                + "0 as vinculo,"
                + "pg.planoconta "
                + "from fin_contas_pagar_detalhe pg "
                + "join irrf ir on (pg.codigo in (ir.codigo) and pg.empresa = ir.empresa) "
                + "left join cad_planocontas pc on (pg.empresa=pc.empresa and pg.planoconta=pc.codigo) "
                + "left join fin_contas_pagar p on (p.empresa=pg.empresa and p.codigo=pg.codigo) "
                + "left join cad_terceiros t on (p.empresa=t.empresa and p.fornecedor=t.codigo) "
                + "left join cad_estabelecimentos e on (p.empresa=e.empresa and p.estabelec=e.codigo) "
                + "where p.dt_emissao between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "' and pg.empresa = 1 "
                + "union all "
                + "select "
                + "'RIDAC' as tiporegistro, pgp.codigo, pgbx.data, "
                + "lpad(extract (month from pgbx.data),2,0) as mes, "
                + "extract (year from pgbx.data) as ano, "
                + "pgp.doc, "
                + "t1.nome as fornecedor, "
                + "t1.cpf_cnpj, "
                + "pgbxd.planoconta ||' - '||pc1.nome as conta, "
                + "case when pgbxd.tipo_plano = 'D' then pgbxd.valor else 0 end as debito, "
                + "case when pgbxd.tipo_plano = 'C' then pgbxd.valor else 0 end as credito, "
                + "t1.pessoa_fj,"
                + "case when exists (select * from cad_terceiros_funcionarios f "
                + "     where f.empresa = t1.empresa and f.codigo = t1.codigo "
                + "     and f.data_admissao <= pgbx.data "
                + "     and (f.data_demissao is null or f.data_demissao >= pgbx.data)) "
                + "then 1 else 0 end as vinculo,"
                + "pgbxd.planoconta "
                + "from fin_contas_pagar_baixas pgbx "
                + "left join fin_contas_pagar_baixas_detalhe pgbxd on (pgbxd.empresa = pgbx.empresa and pgbxd.codigo_baixa = pgbx.codigo) "
                + "inner join fin_contas_pagar pgp on (pgp.empresa = pgbxd.empresa and pgp.codigo = pgbxd.codigo_pagar and pgp.tipodoc = 24) "
                + "left join cad_terceiros t1 on (t1.empresa = pgp.empresa and t1.codigo = pgp.fornecedor) "
                + "left join cad_planocontas pc1 on (pc1.empresa = pgbxd.empresa and pc1.codigo = pgbxd.planoconta) "
                + "where pgbx.empresa = 1 and pgbx.data between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "') lancamentos "
                + "order by lancamentos.cpf_cnpj;";

        if (cnfb.conecta()) {
            try {
                cnfb.executeConsulta(sql);

                while (cnfb.rs.next()) {

                    boolean escapeRet = true;

                    String codRec;
                    int planoconta = cnfb.rs.getInt("planoconta");

                    if (cnfb.rs.getBoolean("vinculo") && "F".equals(cnfb.rs.getString("pessoa_fj"))) {
                        codRec = "0561";
                    } else {
                        if ("F".equals(cnfb.rs.getString("pessoa_fj"))) {
                            codRec = "0588";
                        } else {

                            switch (planoconta) {
                                case 1563:
                                    codRec = "1708";
                                    break;
                                case 10841:
                                    codRec = "5952";
                                    break;
                                case 10840:
                                    codRec = "5960";
                                    break;
                                case 1564:
                                    codRec = "5979";
                                    break;
                                case 1569:
                                    escapeRet = false;
                                    codRec = "";
                                case 52473:
                                    escapeRet = false;
                                    codRec = "";
                                    break;
                                case 1579:
                                    escapeRet = false;
                                    codRec = "";
                                    break;
                                default:
                                    codRec = "1708";
                                    break;
                            }

                        }
                    }

                    if (escapeRet) {
                        person = null;

                        if ("RIDAC".equals(cnfb.rs.getString("tiporegistro"))) {
                            baseDeCalculo = tools.FormatNumbers.numUsToBr(cnfb.rs.getDouble("credito")).replace(".", "").replace(",", "");
                            retencao = "0";
                        } else {
                            baseDeCalculo = tools.FormatNumbers.numUsToBr(cnfb.rs.getDouble("debito")).replace(".", "").replace(",", "");
                            retencao = tools.FormatNumbers.numUsToBr(cnfb.rs.getDouble("credito")).replace(".", "").replace(",", "");

                        }

                        for (PersonDirf p : lancamentos) {

                            if (p.getCpfcnpj().equals(cnfb.rs.getString("cpf_cnpj"))
                                    && p.getRegistro().equals(cnfb.rs.getString("tiporegistro").replace(" ", ""))) {
                                person = p;

                                p.setValue(cnfb.rs.getInt("mes"), baseDeCalculo, retencao, codRec);

                                break;
                            }

                        }

                        //SE NÃO EXISTIR OBJETO, CRIA UM PARA INSERIR OS DADOS
                        if (person == null) {

                            person = new PersonDirf(cnfb.rs.getString("fornecedor"),
                                    cnfb.rs.getString("tiporegistro"),
                                    cnfb.rs.getString("cpf_cnpj"), "N", "N",
                                    cnfb.rs.getString("pessoa_fj"),
                                    codRec);

                            person.setValue(cnfb.rs.getInt("mes"), baseDeCalculo, retencao, codRec);

                            lancamentos.add(person);

                        }
                    } else {
                        //System.out.println("Escapou!!!!!! " + planoconta);
                    }
                }
                //FIM DA PESQUISA

                System.out.println("Quantidade de registros: " + lancamentos.size());

                //INÍCIO DA MONTAGEM DO ARQUIVO
                FileWriter arq = new FileWriter("DIRF.txt");
                PrintWriter gravarArq = new PrintWriter(arq);

                for (PersonDirf p : lancamentos) {

                    p.getLayoutDefault();

                    if (p.getLayoutPFVinculada() != null) {

                        System.out.println(p.getLayoutPFVinculada());
                        gravarArq.printf(p.getLayoutPFVinculada()+"%n");

                    }

                }

                for (PersonDirf p : lancamentos) {

                    if (p.getLayoutPFDesvinculada() != null) {

                        System.out.println(p.getLayoutPFDesvinculada());
                        gravarArq.printf(p.getLayoutPFDesvinculada()+"%n");

                    }

                }
                for (PersonDirf p : lancamentos) {

                    if (p.getLayoutIRRF() != null) {

                        System.out.println(p.getLayoutIRRF());
                        gravarArq.printf(p.getLayoutIRRF()+"%n");

                    }

                }

                for (PersonDirf p : lancamentos) {

                    //p.getLayoutDefault();
                    if (p.getLayoutContribuicoes() != null) {
                        System.out.println(p.getLayoutContribuicoes());
                        gravarArq.printf(p.getLayoutContribuicoes()+"%n");
                    }

                }

                arq.close();

            } catch (SQLException e) {
                throw new Exception(e);
            } finally {

                cnfb.desconecta();
            }
        }

    }

    public static void main(String[] args) {
        RetencoesDirf ret = new RetencoesDirf();
        conn.ConexaoFB.arquivo = "fbcasa.properties";

        try {
            ret.buildLayout("01/01/2017", "31/12/2017");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
