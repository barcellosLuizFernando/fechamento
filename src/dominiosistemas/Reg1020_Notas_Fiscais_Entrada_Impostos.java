/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;

/**
 *
 * @author luiz.barcellos
 */
public class Reg1020_Notas_Fiscais_Entrada_Impostos implements Exportacao_DomSis {

    private final String id = "1020";
    private String cod_imposto = "";
    private String perc_red_bc = "";
    private String bc = "";
    private String aliq = "";
    private String vlr_imposto = "";
    private String vlr_isentas = "";
    private String vlr_outras = "";
    private String vlr_ipi = "";
    private String vlr_st = "";
    private String vlr_contabil = "";
    private String cod_rec_imposto = "";
    private String vlr_nt = "";
    private String vlr_parcela_reduzida = "";

    public void setCod_imposto(String cod_imposto) {
        this.cod_imposto = cod_imposto;
    }

    public void setPerc_red_bc(String perc_red_bc) {
        this.perc_red_bc = perc_red_bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public void setAliq(String aliq) {
        this.aliq = aliq;
    }

    public void setVlr_imposto(String vlr_imposto) {
        this.vlr_imposto = vlr_imposto;
    }

    public void setVlr_isentas(String vlr_isentas) {
        this.vlr_isentas = vlr_isentas;
    }

    public void setVlr_outras(String vlr_outras) {
        this.vlr_outras = vlr_outras;
    }

    public void setVlr_ipi(String vlr_ipi) {
        this.vlr_ipi = vlr_ipi;
    }

    public void setVlr_st(String vlr_st) {
        this.vlr_st = vlr_st;
    }

    public void setVlr_contabil(String vlr_contabil) {
        this.vlr_contabil = vlr_contabil;
    }

    public void setCod_rec_imposto(String cod_rec_imposto) {
        this.cod_rec_imposto = cod_rec_imposto;
    }

    /**
     * Apenas para o Estado de GO.
     *
     * @param vlr_nt
     */
    public void setVlr_nt(String vlr_nt) {
        this.vlr_nt = vlr_nt;
    }

    /**
     * Apenas para o Estado de GO.
     *
     * @param vlr_parcela_reduzida
     */
    public void setVlr_parcela_reduzida(String vlr_parcela_reduzida) {
        this.vlr_parcela_reduzida = vlr_parcela_reduzida;
    }

    @Override
    public String getLayout() throws IOException {
        String linha = "|" + id + "|"
                + cod_imposto + "|"
                + perc_red_bc + "|"
                + bc + "|"
                + aliq + "|"
                + vlr_imposto + "|"
                + vlr_isentas + "|"
                + vlr_outras + "|"
                + vlr_ipi + "|"
                + vlr_st + "|"
                + vlr_contabil + "|"
                + cod_rec_imposto + "|"
                + vlr_nt + "|"
                + vlr_parcela_reduzida + "|";

        return linha;
    }

}
