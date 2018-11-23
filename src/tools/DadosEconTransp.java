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
public class DadosEconTransp {

    private final conn.ConexaoFB cnfb = new ConexaoFB();
    private ConexaoMySQL cn;
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private Cooperados coop;

    private static int caminhonete34, toco, truck, carreta, bitrem, rodotrem,
            outroscaminhoes, onibus, vans, passeio, outros, associadostotal,
            associadosativos, funcionarios, admissoes, demissoes, ctrcs;
    private static double graos, madeira, congelados, containeres, liquidos,
            fertilizantes, outrasCargas, combustiveis, pneus, lonas, pecas,
            outrosInsumos, nacionais, internacionais;

    private String sql, caminho;

    private Date competencia;
    
    public DadosEconTransp(ConexaoMySQL conn){
        this.cn = conn;
        this.coop = new Cooperados(conn);
    }

    public int getCaminhonete34() {
        return caminhonete34;
    }

    public int getToco() {
        return toco;
    }

    public int getTruck() {
        return truck;
    }

    public int getCarreta() {
        return carreta;
    }

    public int getBitrem() {
        return bitrem;
    }

    public int getRodotrem() {
        return rodotrem;
    }

    public int getOutroscaminhoes() {
        return outroscaminhoes;
    }

    public int getOnibus() {
        return onibus;
    }

    public int getVans() {
        return vans;
    }

    public int getPasseio() {
        return passeio;
    }

    public int getOutros() {
        return outros;
    }

    public int getAssociadostotal() {
        return associadostotal;
    }

    public int getAssociadosativos() {
        return associadosativos;
    }

    public int getFuncionarios() {
        return funcionarios;
    }

    public int getAdmissoes() {
        return admissoes;
    }

    public int getDemissoes() {
        return demissoes;
    }

    public double getNacionais() {
        return nacionais;
    }

    public double getInternacionais() {
        return internacionais;
    }

    public int getCtrcs() {
        return ctrcs;
    }

    public double getGraos() {
        return graos;
    }

    public double getMadeira() {
        return madeira;
    }

    public double getCongelados() {
        return congelados;
    }

    public double getContaineres() {
        return containeres;
    }

    public double getLiquidos() {
        return liquidos;
    }

    public double getFertilizantes() {
        return fertilizantes;
    }

    public double getOutrasCargas() {
        return outrasCargas;
    }

    public double getCombustiveis() {
        return combustiveis;
    }

    public double getPneus() {
        return pneus;
    }

    public double getLonas() {
        return lonas;
    }

    public double getPecas() {
        return pecas;
    }

    public double getOutrosInsumos() {
        return outrosInsumos;
    }

    public void buscaDados(boolean recalc, String data, String empresa) {

        try {
            competencia = dateIn.parse(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        //SETA O BANCO DA TRANSCOOPER COMO PADRAO
        defineBD(empresa);

        //CAPTURA DADOS DOS ASSOCIADOS NA CLASSE GENÉRICA
        associadosativos = associadostotal = coop.getAssociadostotal(recalc, data, empresa);

        //DEFINE QUANTIDADE DE CONHECIMENTOS EMITIDOS
        somaConhecimentos(recalc, data, empresa);

        //DEFINE QUANTIDADES TRANSPORTADAS
        somaVolumes(recalc, data, empresa);
        
        //DEFINE QUANTIDADE DE FUNCIONÁRIOS
        somaFuncionarios(recalc, data, empresa);

    }

    private void somaConhecimentos(boolean recalc, String data, String empresa) {

        if (recalc) {

            sql = "select count(a.numero) as quantidade, "
                    + "sum(a.VLR_TOTAL_SERV) as valor "
                    + "from fro_conhecimento_transporte a "
                    + "left join cad_operacoes_fisc b on (b.empresa = a.empresa and b.codigo = a.operacao) "
                    + "where a.empresa = 1 and a.hora_emissao <= '" + data.replace("/", ".") + "' and a.status_envio = 'AU' "
                    + "and extract(year from a.hora_emissao) = '" + (competencia.getYear() + 1900) + "';";

            if (cnfb.conecta()) {
                try {
                    cnfb.executeConsulta(sql);
                    while (cnfb.rs.next()) {
                        ctrcs = cnfb.rs.getInt("quantidade");
                        nacionais = cnfb.rs.getDouble("valor");
                        internacionais = 0.00;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    cnfb.desconecta();
                }
            }

            gravaDados("1771", ctrcs + "");
            gravaDados("201", nacionais + "");
            gravaDados("202", internacionais + "");

        } else {

            try {
                ctrcs = Integer.parseInt(pesquisaDados("1771", data));
                nacionais = Double.parseDouble(pesquisaDados("201", data));
                internacionais = Double.parseDouble(pesquisaDados("202", data));
            } catch (Exception e) {
                ctrcs = 0;
                nacionais = 0;
                internacionais = 0;
            }

        }

        System.out.println("Conhecimentos emitidos: " + ctrcs);

    }

    private void somaVolumes(boolean recalc, String data, String empresa) {
        
        graos = 0.00;
        fertilizantes = 0.00;
        outrasCargas = 0.00;

        if (recalc) {
            sql = " with mercadorias as ( select a.prod_predominante as mercadoria, "
                    + "sum(c.quantidade) as quantidade "
                    + "from fro_conhecimento_transporte a "
                    + "left join fro_ct_unidades c on (c.empresa = a.empresa and c.numero = a.numero and c.serie = a.serie and c.tp_medida = 'PESO BRUTO') "
                    + "where a.empresa = 1 and a.hora_emissao <= '" + data.replace("/", ".") + "' and a.status_envio = 'AU' "
                    + "and extract(year from a.hora_emissao) = '" + (competencia.getYear() + 1900) + "' "
                    + "group by 1) "
                    + "select z.mercadoria, z.quantidade "
                    + "from mercadorias z WHERE z.quantidade is not null;";

            if (cnfb.conecta()) {
                try {
                    cnfb.executeConsulta(sql);
                    while (cnfb.rs.next()) {

                        if (cnfb.rs.getString("mercadoria").toUpperCase().contains("FERTILI")
                                | cnfb.rs.getString("mercadoria").toUpperCase().contains("CALCARIO")) {
                            fertilizantes += cnfb.rs.getDouble("quantidade");
                        } else if (cnfb.rs.getString("mercadoria").toUpperCase().contains("SEMENTE")) {
                            outrasCargas += cnfb.rs.getDouble("quantidade");
                        } else if (cnfb.rs.getString("mercadoria").toUpperCase().contains("CEVADA")
                                | cnfb.rs.getString("mercadoria").toUpperCase().contains("MILHO")
                                | cnfb.rs.getString("mercadoria").toUpperCase().contains("SOJA")
                                | cnfb.rs.getString("mercadoria").toUpperCase().contains("TRIGO")
                                | cnfb.rs.getString("mercadoria").toUpperCase().contains("FEIJÃO")
                                | cnfb.rs.getString("mercadoria").toUpperCase().contains("AVEIA")) {
                            graos += cnfb.rs.getDouble("quantidade");
                        } else {
                            outrasCargas += cnfb.rs.getDouble("quantidade");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    cnfb.desconecta();
                }
            }

            gravaDados("1600", fertilizantes + "");
            gravaDados("1770", graos + "");
            gravaDados("1605", outrasCargas + "");

        } else {
            try {
                fertilizantes = Double.parseDouble(pesquisaDados("1600", data));
            } catch (Exception e) {
                fertilizantes = 0.00;
            }

            try {
                graos = Double.parseDouble(pesquisaDados("1770", data));
            } catch (Exception e) {
                graos = 0.00;
            }

            try {
                outrasCargas = Double.parseDouble(pesquisaDados("1605", data));
            } catch (Exception e) {
                outrasCargas = 0.00;
            }
        }
        
        System.out.println("Fertilizantes: " + fertilizantes);
        System.out.println("Grãos: " + graos);
        System.out.println("Outros: " + outrasCargas);

    }

    private void gravaDados(String tipo_inf, String valor) {
        if (cn.iniciarTransacao()) {
            try {
                sql = "DELETE FROM dadoseconomicos "
                        + "WHERE tipo_inf = '" + tipo_inf + "' "
                        + "AND empresa = '2' "
                        + "AND data = '" + dateOut.format(competencia) + "';";
                cn.executeAtualizacao(sql);
                sql = "INSERT INTO dadoseconomicos (empresa,tipo_inf,valor,data)"
                        + "VALUES('2','" + tipo_inf + "','" + valor + "',"
                        + "'" + dateOut.format(competencia) + "');";
                cn.executeAtualizacao(sql);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }
    }

    private String pesquisaDados(String tipo_inf, String data) {
        String valor = null;

        if (cn.iniciarTransacao()) {
            try {
                sql = "SELECT * FROM dadoseconomicos "
                        + "WHERE empresa = '2' "
                        + "AND data = '" + dateOut.format(competencia) + "' "
                        + "AND tipo_inf = '" + tipo_inf + "';";
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    valor = cn.rs.getString("valor");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }
        return valor;
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
    
    private void somaFuncionarios(boolean recalc, String data, String empresa) {
        
        if(recalc){
            
        }
        
    }

//    public static void main(String[] args) {
//        DadosEconTransp dados = new DadosEconTransp();
//        TelaPrincipal tp = new TelaPrincipal();
//        dados.buscaDados(false, "31/12/2016", "2");
//        tp.dispose();
//
//        System.out.println("Quantidade de associados: " + dados.getAssociadosativos());
//    }


}
