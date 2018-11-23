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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class ExportaMemorandos {

    private int codigo, id_transacao;

    private Date data_registro, data_declaracao, data_exportacao, data_inc,
            data_chc, data_avb;

    private String empresa, estabelec, ind_doc, nro_de,
            nat_exp, nro_re, chc_emb, tp_chc, pais, hora_inc;

    private Double qtde_total;

    private String serie, nro_nota, quantidade;

    private String sql;

    private FbGenerators gen = new FbGenerators();
    private ConexaoMySQL cn = new conn.ConexaoMySQL();
    private ConexaoMySQL cn2 = new conn.ConexaoMySQL();
    private ConexaoFB cnfb = new ConexaoFB();
    private DateFormat dateIn = new SimpleDateFormat("dd.MM.yyyy");
    private DecimalFormat df = new DecimalFormat("#,##0.00");
    private boolean resposta;

    public ExportaMemorandos(ConexaoMySQL conn) {
        this.cn = this.cn2 = conn;

        empresa = "1";
        estabelec = "1";
        ind_doc = "2";
        tp_chc = "99";
        chc_emb = "01";
        nat_exp = "1";

        codigo = gen.getCodigo("FIS_INFO_EXPORTACAO");
        id_transacao = gen.getTransacao();

        sql = "select * from controladoria.cad_memorandos;";

        if (cn.iniciarTransacao()) {
            cn2.iniciarTransacao();
            cnfb.conecta();
            try {
                resposta = cn.executeConsulta(sql);
                while (cn.rs.next()) {

                    resposta = false;

                    String id_exportador = cn.rs.getString("id_exportador");

                    nro_de = cn.rs.getString("numero_memorando");
                    data_declaracao = cn.rs.getDate("data_emissao");
                    nro_re = cn.rs.getString("numero_registro");
                    data_registro = cn.rs.getDate("data_emissao");
                    data_chc = cn.rs.getDate("data_emissao");
                    data_avb = cn.rs.getDate("data_emissao");
                    pais = cn.rs.getString("id_pais_destino");
                    data_exportacao = cn.rs.getDate("data_emissao");
                    qtde_total = cn.rs.getDouble("quantidade");

                    sql = "INSERT INTO FIS_INFO_EXPORTACAO (EMPRESA,CODIGO,ESTABELEC,"
                            + "IND_DOC,NRO_DE,DATA_DECLARACAO,NAT_EXP,NRO_RE,"
                            + "DATA_REGISTRO,CHC_EMB,DATA_CHC,DATA_AVB,TP_CHC,PAIS,"
                            + "DATA_INC,HORA_INC,ID_TRANSACAO,DATA_EXPORTACAO,"
                            + "QTDE_TOTAL) VALUES ('" + empresa + "','" + codigo + "',"
                            + "'" + estabelec + "','" + ind_doc + "','" + nro_de + "',"
                            + "'" + dateIn.format(data_declaracao) + "','" + nat_exp + "',"
                            + "'" + nro_re + "','" + dateIn.format(data_registro) + "',"
                            + "'" + chc_emb + "','" + data_chc + "','" + data_avb + "',"
                            + "'" + tp_chc + "','" + pais + "',current_date,current_time,"
                            + "'" + id_transacao + "','" + dateIn.format(data_exportacao) + "','"
                            + qtde_total + "');";

                    resposta = cnfb.executeAtualizacao(sql);

                    sql = "SELECT id,id_exportador,numero_memorando,numero_nf,sum(quantidade) as quantidade ,natureza,inscricao_federal "
                            + "FROM controladoria.cad_memorandos_detalhe WHERE id_exportador = '" + id_exportador + "' and numero_memorando = '" + nro_de + "' "
                            + "group by numero_nf,inscricao_federal;";
                    cn2.executeConsulta(sql);
                    while (cn2.rs.next()) {
                        String cnpj = cn2.rs.getString("inscricao_federal");
                        switch (cnpj) {
                            case "05528196000105":
                                serie = "1";
                                break;
                            case "05528196001349":
                                serie = "11";
                                break;
                            case "05528196001420":
                                serie = "12";
                                break;
                            case "05528196001500":
                                serie = "13";
                                break;
                            case "05528196001691":
                                serie = "14";
                                break;
                            case "05528196000539":
                                serie = "2";
                                break;
                            case "05528196001004":
                                serie = "3";
                                break;
                            case "05528196000962":
                                serie = "4";
                                break;
                            case "05528196001187":
                                serie = "5";
                                break;
                            case "05528196000881":
                                serie = "6";
                                break;
                            case "05528196001268":
                                serie = "9";
                                break;
                        }

                        nro_nota = cn2.rs.getString("numero_nf");
                        qtde_total = cn2.rs.getDouble("quantidade");

                        sql = "INSERT INTO FIS_INFO_EXPORTACAO_NOTAS_VENDA "
                                + "(EMPRESA,CODIGO,SERIE,NRO_NOTA,ID_TRANSACAO,"
                                + "QUANTIDADE) VALUES ('" + empresa + "','" + codigo + "',"
                                + "'" + serie + "','" + nro_nota + "','" + id_transacao + "',"
                                + "'" + qtde_total + "');";
                        resposta = cnfb.executeAtualizacao(sql);
                    }
                    cn2.rs.close();

                    if (resposta == false) {
                        break;
                    }

                    id_transacao++;
                    codigo++;

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao sincronizar memorandos.\n" + e);
            } finally {
                cn.finalizarTransacao();
                cn2.finalizarTransacao();
                cnfb.desconecta();
            }

        }

    }

//    public static void main(String[] args) {
//        TelaPrincipal tp = new TelaPrincipal();
//        ExportaMemorandos x = new ExportaMemorandos();
//        tp.dispose();
//    }

}
