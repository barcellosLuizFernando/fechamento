/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoMySQL;
import view.TelaPrincipal;

/**
 *
 * @author luiz.barcellos
 */
public class ExternalLog {

    public ExternalLog(final String sql, final String database) {

        //conn.ConexaoMySQL cn = new ConexaoMySQL();
        //cn.arquivo = "mysql.properties";
        TelaPrincipal tela = new TelaPrincipal();
        try {
        //tela.cn.conecta("root", "01lety04");

            tela.cn.executeAtualizacao("INSERT INTO fechamento.external_log (`query`,`database`) "
                    + "VALUES ('" + sql.replace("\'", "\\'") + "','" + database + "');");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                tela.cn.desconecta();
                tela.dispose();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }
    public static void main(String[] args) {
        ExternalLog log = new ExternalLog("UPDATE com_entradas SET liberado = 'N' WHERE empresa = 1 and codigo = '411725348';", "jsjda");
    }

}

