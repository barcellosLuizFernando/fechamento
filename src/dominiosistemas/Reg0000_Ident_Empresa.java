/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Reg0000_Ident_Empresa implements Exportacao_DomSis {

    String id = "0000";
    String inscricao;

    @Override
    public String getLayout() throws Exception {

        if (!inscricao.equals("")) {

            String linha = "|" + id + "|" + inscricao + "|";
            System.out.println("Registro: " + linha);

            return linha;
        } else {
            throw new UnsupportedOperationException("Registro 0000 - Inscrição Federal não informada.");
        }
    }

    /**
     * CNPJ, CPF ou CEI
     *
     * @param inscricao Somente Números
     */
    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public static void main(String[] args) {

        try {
            Reg0000_Ident_Empresa i = new Reg0000_Ident_Empresa();
            i.setInscricao("05528196000105");
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
