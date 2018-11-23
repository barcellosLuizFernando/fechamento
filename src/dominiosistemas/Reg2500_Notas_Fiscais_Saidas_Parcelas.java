/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Registro: 2500 – Notas Fiscais de Saída – Parcelas. Pg 1259.
 *
 * @author luiz.barcellos
 */
public class Reg2500_Notas_Fiscais_Saidas_Parcelas implements Exportacao_DomSis {

    private final String id = "2500";
    private String vencimento = "";
    private String valor = "";
    private String aliquota_crf = "";
    private String vlr_crf = "";
    private String vlr_irrf = "";
    private String vlr_iss_ret = "";
    private String vlr_inss_ret = "";
    private String aliq_pis_cosirf = "";
    private String vlr_pis_cosirf = "";
    private String aliq_cofins_cosirf = "";
    private String vlr_cofins_cosirf = "";
    private String aliq_csll_cosirf = "";
    private String vlr_csll_cosirf = "";
    private String aliq_irpj_cosirf = "";
    private String vlr_irpj_cosirf = "";
    private String vlr_irrfp = "";
    private String vlr_pis_ret = "";
    private String vlr_cofis_ret = "";
    private String vlr_csoc_ret = "";
    private String vlr_funrural = "";
    private String nro_titulo = "";

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public void setAliq_pis_cosirf(String aliq_pis_cosirf) {
        this.aliq_pis_cosirf = aliq_pis_cosirf;
    }

    public void setVlr_pis_cosirf(String vlr_pis_cosirf) {
        this.vlr_pis_cosirf = vlr_pis_cosirf;
    }

    public void setAliq_cofins_cosirf(String aliq_cofins_cosirf) {
        this.aliq_cofins_cosirf = aliq_cofins_cosirf;
    }

    public void setVlr_cofins_cosirf(String vlr_cofins_cosirf) {
        this.vlr_cofins_cosirf = vlr_cofins_cosirf;
    }

    public void setAliq_csll_cosirf(String aliq_csll_cosirf) {
        this.aliq_csll_cosirf = aliq_csll_cosirf;
    }

    public void setVlr_csll_cosirf(String vlr_csll_cosirf) {
        this.vlr_csll_cosirf = vlr_csll_cosirf;
    }

    public void setAliq_irpj_cosirf(String aliq_irpj_cosirf) {
        this.aliq_irpj_cosirf = aliq_irpj_cosirf;
    }

    public void setVlr_irpj_cosirf(String vlr_irpj_cosirf) {
        this.vlr_irpj_cosirf = vlr_irpj_cosirf;
    }

    public void setVlr_irrfp(String vlr_irrfp) {
        this.vlr_irrfp = vlr_irrfp;
    }

    
    
    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setAliquota_crf(String aliquota_crf) {
        this.aliquota_crf = aliquota_crf;
    }

    public void setVlr_crf(String vlr_crf) {
        this.vlr_crf = vlr_crf;
    }

    public void setVlr_irrf(String vlr_irrf) {
        this.vlr_irrf = vlr_irrf;
    }

    public void setVlr_iss_ret(String vlr_iss_ret) {
        this.vlr_iss_ret = vlr_iss_ret;
    }

    public void setVlr_inss_ret(String vlr_inss_ret) {
        this.vlr_inss_ret = vlr_inss_ret;
    }

    public void setVlr_funrural(String vlr_funrural) {
        this.vlr_funrural = vlr_funrural;
    }

    public void setVlr_pis_ret(String vlr_pis_ret) {
        this.vlr_pis_ret = vlr_pis_ret;
    }

    public void setVlr_cofis_ret(String vlr_cofis_ret) {
        this.vlr_cofis_ret = vlr_cofis_ret;
    }

    public void setVlr_csoc_ret(String vlr_csoc_ret) {
        this.vlr_csoc_ret = vlr_csoc_ret;
    }

    public void setNro_titulo(String nro_titulo) {
        this.nro_titulo = nro_titulo;
    }

    @Override
    public String getLayout() throws IOException {
        String linha = "|" + id + "|"
                + vencimento + "|"
                + valor + "|"
                + aliquota_crf + "|"
                + vlr_crf + "|"
                + vlr_irrf + "|"
                + vlr_iss_ret + "|"
                + vlr_inss_ret + "|"
                + aliq_pis_cosirf + "|"
                + vlr_pis_cosirf + "|"
                + aliq_cofins_cosirf + "|"
                + vlr_cofins_cosirf + "|"
                + aliq_csll_cosirf + "|"
                + vlr_csll_cosirf + "|"
                + aliq_irpj_cosirf + "|"
                + vlr_irpj_cosirf + "|"
                + vlr_irrfp + "|"
                + vlr_pis_ret + "|"
                + vlr_cofis_ret + "|"
                + vlr_csoc_ret + "|"
                + vlr_funrural + "|"
                + nro_titulo + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg2500_Notas_Fiscais_Saidas_Parcelas i = new Reg2500_Notas_Fiscais_Saidas_Parcelas();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
