/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import static tools.RemoveSpecialChar.removeSpecialChar;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Reg0160_Produtos_Grupos implements Exportacao_DomSis {

    private final String id = "0160";
    private String codigo = "";
    private String descricao = "";

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = removeSpecialChar(descricao);
    }

    @Override
    public String getLayout() throws IOException {

        String mensagem = "ERRO: ";

        if ("".equals(codigo)) {
            mensagem += "\nÉ necessário informar o código do grupo de produtos para continuar.";
        }
        if ("".equals(descricao)) {
            mensagem += "\nÉ necessário informar o nome do grupo de produtos para continuar.";
        }

        if (!"ERRO: ".equals(mensagem)) {
            throw new UnsupportedOperationException(mensagem);
        }

        String linha = "|" + id + "|" + codigo + "|" + descricao + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        
        Reg0160_Produtos_Grupos i = new Reg0160_Produtos_Grupos();
        
        try {
            i.setDescricao("Unidade");
            //i.setCodigo("10298");
            i.getLayout();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
