/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class AjustaArquivoPlanoContas {

    private LerLinhasArquivo ler;

    public AjustaArquivoPlanoContas(String caminho, String separador) {

        try {
            String linha = "";
            String[] x;
            ler = new LerLinhasArquivo(caminho);
            ArrayList xy = new ArrayList();

            xy = (ler.getLista());

            for (int i = 0; i < xy.size(); i++) {

                x = xy.get(i).toString().split(separador);

                System.out.print("Registro 01: " + x[0]);
                System.out.println(". Registro 02: " + x[1]);
            }

            //System.out.print("Registro 01: " + x);
            System.out.print("Tamanho registro: " + xy.size());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void main(String[] args) {

        AjustaArquivoPlanoContas a = new AjustaArquivoPlanoContas("C:\\Users\\luiz.barcellos\\Google Drive\\Contabilidade\\PlanoContasAtividadeRural.txt", ". ");

    }

}
