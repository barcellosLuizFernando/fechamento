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
public class Reg0150_Produtos_Unidades_Medidas implements Exportacao_DomSis {

    private final String id = "0150";
    private String sigla = "";
    private String descricao = "";

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getLayout() throws IOException {

        String mensagem = "ERRO: ";

        if ("".equals(sigla)) {
            mensagem += "\nÉ necessário informar a unidade de medida para continuar.";
        }
        if ("".equals(descricao)) {
            mensagem += "\nÉ necessário informar o nome da unidade de medida para continuar.";
        }

        if (!"ERRO: ".equals(mensagem)) {
            throw new UnsupportedOperationException(mensagem);
        }

        String linha = "|" + id + "|" + sigla + "|" + descricao + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        
        Reg0150_Produtos_Unidades_Medidas i = new Reg0150_Produtos_Unidades_Medidas();
        
        try {
            i.setDescricao("Unidade");
            i.setSigla("Un");
            i.getLayout();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
