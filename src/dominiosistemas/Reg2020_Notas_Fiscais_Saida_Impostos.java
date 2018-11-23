/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import javax.swing.JOptionPane;

/**
 * Registro: 2020 – Notas Fiscais de Saída - Impostos. Pg 1223
 *
 * @author ferna
 */
public class Reg2020_Notas_Fiscais_Saida_Impostos implements Exportacao_DomSis {

    private final String id = "2020";
    private String cod_imp = "";
    private String cod_prec_red_bc = "";
    private String bc = "";
    private String aliq = "";
    private String vlr_imp = "";
    private String vlr_isentas = "";
    private String vlr_outras = "";
    private String vlr_contabil = "";
    private String vlr_nao_trib = "";
    private String vlr_parc_red = "";
    private String vlr_ipi = "";
    private String cod_rec_imp = "";

    public void setCod_imp(String cod_imp) {
        this.cod_imp = cod_imp;
    }

    public void setCod_prec_red_bc(String cod_prec_red_bc) {
        this.cod_prec_red_bc = cod_prec_red_bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public void setAliq(String aliq) {
        this.aliq = aliq;
    }

    public void setVlr_imp(String vlr_imp) {
        this.vlr_imp = vlr_imp;
    }

    public void setVlr_isentas(String vlr_isentas) {
        this.vlr_isentas = vlr_isentas;
    }

    public void setVlr_outras(String vlr_outras) {
        this.vlr_outras = vlr_outras;
    }

    public void setVlr_contabil(String vlr_contabil) {
        this.vlr_contabil = vlr_contabil;
    }

    /**
     * Apenas para o Estado de GO.
     *
     * @param vlr_nao_trib
     */
    public void setVlr_nao_trib(String vlr_nao_trib) {
        this.vlr_nao_trib = vlr_nao_trib;
    }

    /**
     * Apenas para o Estado de GO.
     *
     * @param vlr_parc_red
     */
    public void setVlr_parc_red(String vlr_parc_red) {
        this.vlr_parc_red = vlr_parc_red;
    }

    /**
     * Apenas para o Estado da BA.
     *
     * @param vlr_ipi
     */
    public void setVlr_ipi(String vlr_ipi) {
        this.vlr_ipi = vlr_ipi;
    }

    public void setCod_rec_imp(String cod_rec_imp) {
        this.cod_rec_imp = cod_rec_imp;
    }

    @Override
    public String getLayout() throws Exception {
        String linha = "|" + id + "|"
                + cod_imp + "|"
                + cod_prec_red_bc + "|"
                + bc + "|"
                + aliq + "|"
                + vlr_imp + "|"
                + vlr_isentas + "|"
                + vlr_outras + "|"
                + vlr_contabil + "|"
                + vlr_nao_trib + "|"
                + vlr_parc_red + "|"
                + vlr_ipi + "|"
                + cod_rec_imp + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg2020_Notas_Fiscais_Saida_Impostos i = new Reg2020_Notas_Fiscais_Saida_Impostos();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
