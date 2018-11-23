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
public class Reg0120_Produtos_Unidades_Comercializadas extends Reg0100_Cadastro_Produtos implements Exportacao_DomSis {

    private final String id = "0120";
    private String sigla = "";
    private String fator = "";

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setFator(String fator) {
        this.fator = fator;
    }

    @Override
    public String getLayout() throws IOException {

        String mensagem = "ERRO: ";

        if ("".equals(sigla)) {
            mensagem += "\nÉ necessário informar a unidade do produto " + getDescricao_produto() + " para continuar.";
        }
        if ("".equals(fator)) {
            mensagem += "\nÉ necessário informar o fator de conversão do produto " + getDescricao_produto() + " para continuar.";
        }

        if (!"ERRO: ".equals(mensagem)) {
            throw new UnsupportedOperationException(mensagem);
        }

        String linha = "|" + id + "|" + sigla + "|" + fator + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        
        Reg0120_Produtos_Unidades_Comercializadas i = new Reg0120_Produtos_Unidades_Comercializadas();
        
        try {
            i.setDescricao_produto("PRODUTO QUALQUER");
            //i.setSigla("Un");
            i.getLayout();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
