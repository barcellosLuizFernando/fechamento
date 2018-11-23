/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lancamentos;

import conn.ConexaoFB;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import tools.Balancete;

/**
 *
 * @author luiz.barcellos
 */
public class BloqueiaPeriodo {

    private final ConexaoFB cnfb = new ConexaoFB();
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private String sql;

    /**
     * RECEBE DATA NO FORMATO DD/MM/AAAA E ENCERRA TODOS OS PERÍODOS. Não altera
     * as filiais 7 e 8;
     *
     * @param data DD/MM/AAAA
     * @throws Exception
     */
    public BloqueiaPeriodo(String data) throws Exception {

        //TESTE PARA VERIFICAR SE VALOR INFORMADO É UMA DATA VÁLIDA
        //SE INFORMAR DATA NO FORMATO INGLÊS, PASSA TAMBÉM, PORÉM ESTÁ ERRADO.
        System.out.println("Data Informada: " + dateIn.parse(data));

        sql = "UPDATE ctb_bloqueia_lctos SET "
                + "data = '" + data.replace("/", ".") + "' "
                + "WHERE empresa = 1 "
                + "AND estabelec NOT IN (7,8);";

        if (cnfb.conecta()) {
            try {
                cnfb.executeAtualizacao(sql);
            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                cnfb.desconecta();
            }
        }
    }
    
    //public BloqueiaPeriodo(){}

    public void multiCMV() throws Exception {
        /*cnfb.conecta();
        cnfb.executeAtualizacao("execute procedure ctb_processa_cmv4(1,'2017',10)");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_processa_cmv4(1,'2017',11)");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_processa_cmv4(1,'2017',12)");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,01,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,02,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,03,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,04,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,05,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,06,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,07,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,08,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,09,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,10,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,11,167,'S',null);");
        cnfb.commitTransaction();
        cnfb.executeAtualizacao("execute procedure ctb_apropria_r_contas_2(1,2017,12,167,'S',null);");
        
        cnfb.desconecta();*/
        
    }
}
