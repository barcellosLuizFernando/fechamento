/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import javax.swing.JOptionPane;

/**
 * Registro: 5200 – Recebimentos – Notas Fiscais de Saída. Pg 1299
 *
 * @author ferna
 */
public class Reg5200_Recebimentos_Notas_Fiscais_Saida implements Exportacao_DomSis {

    private final String id = "5200";
    private String cod_especie = "";
    private String serie = "";
    private String sub_serie = "";
    private String nro_doc = "";
    private String dt_venc_parcela = "";
    private String dt_pgto = "";
    private String vlr_recebido_total = "";
    private String vlr_juros = "";
    private String vlr_multa = "";
    private String vlr_desconto = "";
    private String vlr_devolucao = "";
    private String vlr_pis = "";
    private String vlr_cofins = "";
    private String vlr_csoc = "";
    private String vlr_irrf = "";
    private String vlr_pis_cosirf = "";
    private String vlr_cofins_cosirf = "";
    private String vlr_csoc_cosirf = "";
    private String vlr_irpj_cosirf = "";
    private String vlr_irrfp = "";
    private String vlr_outras = "";
    private String segmento = "";

    public void setCod_especie(String cod_especie) {
        this.cod_especie = cod_especie;
    }

    public void setVlr_pis_cosirf(String vlr_pis_cosirf) {
        this.vlr_pis_cosirf = vlr_pis_cosirf;
    }

    public void setVlr_cofins_cosirf(String vlr_cofins_cosirf) {
        this.vlr_cofins_cosirf = vlr_cofins_cosirf;
    }

    public void setVlr_csoc_cosirf(String vlr_csoc_cosirf) {
        this.vlr_csoc_cosirf = vlr_csoc_cosirf;
    }

    public void setVlr_irpj_cosirf(String vlr_irpj_cosirf) {
        this.vlr_irpj_cosirf = vlr_irpj_cosirf;
    }

    
    
    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setSub_serie(String sub_serie) {
        this.sub_serie = sub_serie;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public void setDt_venc_parcela(String dt_venc_parcela) {
        this.dt_venc_parcela = dt_venc_parcela;
    }

    public void setDt_pgto(String dt_pgto) {
        this.dt_pgto = dt_pgto;
    }

    public void setVlr_recebido_total(String vlr_recebido_total) {
        this.vlr_recebido_total = vlr_recebido_total;
    }

    public void setVlr_juros(String vlr_juros) {
        this.vlr_juros = vlr_juros;
    }

    public void setVlr_multa(String vlr_multa) {
        this.vlr_multa = vlr_multa;
    }

    public void setVlr_desconto(String vlr_desconto) {
        this.vlr_desconto = vlr_desconto;
    }

    public void setVlr_devolucao(String vlr_devolucao) {
        this.vlr_devolucao = vlr_devolucao;
    }

    public void setVlr_pis(String vlr_pis) {
        this.vlr_pis = vlr_pis;
    }

    public void setVlr_cofins(String vlr_cofins) {
        this.vlr_cofins = vlr_cofins;
    }

    public void setVlr_csoc(String vlr_csoc) {
        this.vlr_csoc = vlr_csoc;
    }

    public void setVlr_irrf(String vlr_irrf) {
        this.vlr_irrf = vlr_irrf;
    }

    public void setVlr_irrfp(String vlr_irrfp) {
        this.vlr_irrfp = vlr_irrfp;
    }

    public void setVlr_outras(String vlr_outras) {
        this.vlr_outras = vlr_outras;
    }

    /**
     * Informar o segmento que se refere a baixa da parcela
     *
     * @param segmento
     */
    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    @Override
    public String getLayout() throws Exception {
        String linha = "|" + id + "|"
                + cod_especie + "|"
                + serie + "|"
                + sub_serie + "|"
                + nro_doc + "|"
                + dt_venc_parcela + "|"
                + dt_pgto + "|"
                + vlr_recebido_total + "|"
                + vlr_juros + "|"
                + vlr_multa + "|"
                + vlr_desconto + "|"
                + vlr_devolucao + "|"
                + vlr_pis + "|"
                + vlr_cofins + "|"
                + vlr_csoc + "|"
                + vlr_irrf + "|"
                + vlr_pis_cosirf + "|"
                + vlr_cofins_cosirf + "|"
                + vlr_csoc_cosirf + "|"
                + vlr_irpj_cosirf + "|"
                + vlr_irrfp + "|"
                + vlr_outras + "|"
                + segmento + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg5200_Recebimentos_Notas_Fiscais_Saida i = new Reg5200_Recebimentos_Notas_Fiscais_Saida();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
