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
public class Reg1040_Notas_Fiscais_Entrada_Município extends Reg1000_Notas_Fiscais_Entrada implements Exportacao_DomSis {

    private final String id = "1040";
    private String cod_tbl_mun = "";
    private String cod_item = "";
    private String descricao = "";

    /**
     * Informar código de cadastro da tabela municipal conforme Domínio Escrita
     * Fiscal.
     *
     * @param cod_tbl_mun
     */
    public void setCod_tbl_mun(String cod_tbl_mun) {
        this.cod_tbl_mun = cod_tbl_mun;
    }

    /**
     * Informar código de cadastro de itens da tabela municipal conforme Domínio
     * Escrita Fiscal. Informar somente quando a tabela municipal utiliza lista
     * de itens.
     *
     * @param cod_item
     */
    public void setCod_item(String cod_item) {
        this.cod_item = cod_item;
    }

    /**
     * Informar somente quando a tabela municipal utiliza valor livre.
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getLayout() throws IOException {
        String linha = "|" + id + "|" + cod_tbl_mun + "|" + cod_item + "|" + descricao + "|";

        System.out.println("Registro: " + linha);
        return linha;
    }

    public static void main(String[] args) {
        Reg1040_Notas_Fiscais_Entrada_Município i = new Reg1040_Notas_Fiscais_Entrada_Município();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
