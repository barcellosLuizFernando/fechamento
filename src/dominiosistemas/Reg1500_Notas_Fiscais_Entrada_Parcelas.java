/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Registro: 1500 – Notas Fiscais de Entrada – Parcelas. Pg 1219
 *
 * @author luiz.barcellos
 */
public class Reg1500_Notas_Fiscais_Entrada_Parcelas implements Exportacao_DomSis {

    private final String id = "1500";
    private String vencimento = "";
    private String valor = "";
    private String aliquota_crf = "";
    private String vlr_crf = "";
    private String vlr_irrf = "";
    private String vlr_iss_ret = "";
    private String vlr_inss_ret = "";
    private String vlr_funrural = "";
    private String vlr_pis_ret = "";
    private String vlr_cofis_ret = "";
    private String vlr_csoc_ret = "";
    private String vlr_irrf_pf = "";
    private String nro_titulo = "";

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
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

    public void setVlr_irrf_pf(String vlr_irrf_pf) {
        this.vlr_irrf_pf = vlr_irrf_pf;
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
                + vlr_funrural + "|"
                + vlr_pis_ret + "|"
                + vlr_cofis_ret + "|"
                + vlr_csoc_ret + "|"
                + vlr_irrf_pf + "|"
                + nro_titulo + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg1500_Notas_Fiscais_Entrada_Parcelas i = new Reg1500_Notas_Fiscais_Entrada_Parcelas();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
