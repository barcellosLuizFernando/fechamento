package ocepar;

import conn.ConexaoFB;
import conn.ConexaoFBAgil;

public class AjustarAgilBalancoSocial {

    public AjustarAgilBalancoSocial() throws Exception {

        conn.ConexaoFBAgil cnfb = new ConexaoFBAgil();
        cnfb.arquivo = "fbagil.properties";

        cnfb.conecta();

        String sql = "UPDATE INT_CONTAS_DETALHE SET VALOR = NULL WHERE REGISTRO_OCEPAR NOT IN (2987,2988,2989,2090,2991);";

        cnfb.executeAtualizacao(sql);

        sql = "update INT_CONTAS_DETALHE a set a.valor = (coalesce((select z.SALDO_ATUAL from TMP_REL_BALANCETE z where z.EMPRESA = a.EMPRESA and z.CONTA = a.CONTA_EMPRESA) ,0.00));";
        cnfb.executeAtualizacao(sql);
        
        cnfb.commitTransaction();

        cnfb.desconecta();

    }

}
