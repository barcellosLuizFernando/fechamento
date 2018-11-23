/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Reg1010_Notas_Fiscais_Entrada_Inf_Compl extends Reg1000_Notas_Fiscais_Entrada implements Exportacao_DomSis {

    private final String id = "1010";
    private String cod_inf_compl = "";
    private String descricao = "";

    /**
     * Informar: 1-Informação Complementar de Interesse do Fisco ou 2-Informação
     * Complementar de Interesse do Contribuinte.
     *
     * @param cod_inf_compl
     */
    @Override
    public void setCod_inf_compl(String cod_inf_compl) {
        this.cod_inf_compl = cod_inf_compl;
    }

    /**
     * Informar no máximo 300 caracteres.
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getLayout() throws IOException {

        String mensagem = "ERRO: ";

        if ("".equals(cod_inf_compl)) {
            mensagem += "\nInforme um código de Informações Complementares para continuar. Documento " + getNro_doc() + ".";
        }
        if ("".equals(descricao)) {
            mensagem += "\nInforme uma Informação Complementar para continuar. Documento " + getNro_doc() + ".";
        }
        if (!"ERRO: ".equals(mensagem)) {
            throw new UnsupportedOperationException(mensagem);
        }

        String linha = "|" + id + "|" + cod_inf_compl + "|" + descricao + "|";
        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg1010_Notas_Fiscais_Entrada_Inf_Compl i = new Reg1010_Notas_Fiscais_Entrada_Inf_Compl();

        try {
            i.setCod_inf_compl("1102");
            i.setNro_doc("aaaaa2244");
            i.setDescricao("deslkjfoi");
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
