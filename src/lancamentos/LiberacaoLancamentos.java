/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lancamentos;

import conn.ConexaoFB;
import javax.swing.JOptionPane;

/**
 * Marca lançamento como liberado. Pode liberar um único lançamento, ou toda uma
 * conta e suas contrapartidas. Executa o estorno da liberação.
 *
 * @author ferna
 */
public class LiberacaoLancamentos {

    private final ConexaoFB cnfb;
    private String sql;

    public LiberacaoLancamentos(ConexaoFB conn) {
        this.cnfb = conn;
    }

    /**
     * Libera um único lançamento. Ideal para quando se tem uma lista. Chamar
     * método para cada linha da lista.
     *
     * @param lancamento Número do Lançamento
     * @return
     */
    public boolean liberar(String lancamento) throws Exception {

        boolean resposta = false;

        sql = "UPDATE ctb_lancamentos SET liberado = 'S', data_liberacao = current_date, "
                + "hora_liberacao = current_time, usu_liberacao = 167 "
                + "WHERE empresa = 1 "
                + "AND numero = '" + lancamento + "'; ";

        if (cnfb.isConnected()) {
            try {
                resposta = cnfb.executeAtualizacao(sql);
            } catch (Exception e) {
                resposta = false;
                throw new UnsupportedOperationException(e);
            }
        } else {
            resposta = false;
            throw new UnsupportedOperationException("Você precisa estar conectado para executar esta ação.");
        }

        return resposta;
    }

    /**
     * Estorna liberação de um único lançamento. Ideal para quando se tem uma
     * lista. Chamar método para cada linha da lista.
     *
     * @param lancamento Número do Lançamento
     * @return
     */
    public boolean estonarLiberar(String lancamento) throws Exception {

        boolean resposta = false;

        sql = "UPDATE ctb_lancamentos SET liberado = 'N', data_est_liberacao = current_date, "
                + "hora_est_liberacao = current_time, usu_est_liberacao = 167 "
                + "WHERE empresa = 1 "
                + "AND numero = '" + lancamento + "'; ";

        if (cnfb.isConnected()) {
            try {
                resposta = cnfb.executeAtualizacao(sql);
            } catch (Exception e) {
                resposta = false;
                throw new UnsupportedOperationException(e);
            }
        } else {
            resposta = false;
            throw new UnsupportedOperationException("Você precisa estar conectado para executar esta ação.");
        }

        return resposta;
    }

    public static void main(String[] args) {

        try {
            ConexaoFB cnfb = new ConexaoFB();
            ConexaoFB.arquivo = "fbcasa.properties";
            cnfb.conecta();
            LiberacaoLancamentos freeLanc = new LiberacaoLancamentos(cnfb);
            freeLanc.estonarLiberar("11502063");
            cnfb.desconecta();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
