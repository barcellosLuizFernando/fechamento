/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoFB;
import java.util.ArrayList;
import java.util.Date;
import tabelasFB.FinContasReceber;
import tabelasFB.FinContasReceberDetalhe;
import tabelasFB.FinContasReceberDetalhePK;
import tabelasFB.FinContasReceberPK;

/**
 *
 * @author luiz.barcellos
 */
public class FinIntegraContasReceber {

    private final ConexaoFB cnfb = new ConexaoFB();
    private String query_first;
    private ArrayList query_second = new ArrayList();
    private String sql;
    private tabelasFB.FinContasReceberPK finpk;
    private tabelasFB.FinContasReceber fin;
    private tabelasFB.FinContasReceberDetalhePK findpk;
    private tabelasFB.FinContasReceberDetalhe find;
    private final int codigo;
    private final int idTransacao;

    public FinIntegraContasReceber() {
        query_second.clear();
        query_first = null;

        tools.FbGenerators gen = new FbGenerators();

        codigo = gen.getCodigo("fin_contas_receber");
        System.out.println("Código capturado: " + codigo);

        idTransacao = gen.getTransacao();
        System.out.println("Transação capturada: " + idTransacao);

    }
    
    public boolean deleteAccountPayable() throws Exception {
        boolean resposta = false;
        
            
        
        return resposta;
    }

    public boolean syncAccountsPayable() throws Exception {
        boolean resposta = false;

        if (cnfb.conecta()) {
            try {
                sql = query_first;
                cnfb.executeAtualizacao(sql);
                for (int i = 0; i < query_second.size(); i++) {
                    sql = query_second.get(i).toString();
                    cnfb.executeAtualizacao(sql);
                }

            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
            }
        }

        return resposta;
    }

    /**
     *
     * @param empresa
     * @param doc
     * @param historico
     * @param dtEmissao
     * @param dtVenc
     * @param portador
     * @param integraContabil
     * @param contaCliente
     * @param cliente
     * @param estabelec
     * @param usuInc
     * @param tipoDoc
     * @throws Exception
     */
    public void setQueryFirst(Short empresa, String doc,
            String historico, Date dtEmissao, Date dtVenc, int portador,
            Character integraContabil, int contaCliente, int cliente,
            int estabelec, int usuInc, int tipoDoc) throws Exception {

        finpk = new FinContasReceberPK(empresa, codigo);
        fin = new FinContasReceber(finpk, doc, historico,
                dtEmissao, dtVenc, portador, integraContabil, contaCliente, cliente,
                idTransacao, estabelec, usuInc, tipoDoc);

        query_first = fin.getQueryInsert();

    }

    private void viewQuery() {
        System.out.println("Query principal: " + query_first);
        for (int i = 0; i < query_second.size(); i++) {
            System.out.println("Query secundaria, linha " + i + ": " + query_second.get(i));

        }
    }

    /**
     *
     * @param planoconta
     * @param tipoPlano
     * @param historico
     * @throws Exception
     */
    public void setQuerySecond(int planoconta, String tipoPlano, String historico, Double valor) throws Exception {

        findpk = new FinContasReceberDetalhePK(finpk.getEmpresa(), finpk.getCodigo(), planoconta);
        find = new FinContasReceberDetalhe(findpk, valor, tipoPlano, historico, fin.getIdTransacao());

        query_second.add(find.getQueryInsert());

    }

    public int getCodigo() {
        return codigo;
    }
    
    

}
