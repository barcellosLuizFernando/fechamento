/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import javax.swing.JOptionPane;

/**
 *
 * @author ferna
 */
public class Reg2010_Notas_Fiscais_Saida_Inf_Compl implements Exportacao_DomSis {

    private final String id = "2010";
    private String cod_inf_compl = "";
    private String descricao = "";

    public void setCod_inf_compl(String cod_inf_compl) {
        this.cod_inf_compl = cod_inf_compl;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getLayout() throws Exception {
        String linha = "|" + id + "|" + cod_inf_compl + "|" + descricao + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }
    
    public static void main(String[] args) {
        Reg2010_Notas_Fiscais_Saida_Inf_Compl i = new Reg2010_Notas_Fiscais_Saida_Inf_Compl();
        
        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
