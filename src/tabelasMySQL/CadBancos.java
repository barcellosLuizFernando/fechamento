/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelasMySQL;

import view.TelaPrincipal;
import conn.ConexaoMySQL;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class CadBancos {

    private String sql, id, code, title, document, site;
    private boolean status;
    private ConexaoMySQL cn;

    public CadBancos(boolean b, String id, ConexaoMySQL conn) {
        
        this.cn = conn;

        if (b) {
            sql = "SELECT * FROM cad_bancos WHERE left(replace(document,'.',''),8) = '" + id + "';";
        } else {
            sql = "SELECT * FROM cad_bancos WHERE id = '" + id + "';";
        }

        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    this.id = cn.rs.getString("id");
                    code = cn.rs.getString("code");
                    title = cn.rs.getString("title");
                    document = cn.rs.getString("document");
                    site = cn.rs.getString("site");
                    status = cn.rs.getBoolean("status");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }

    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDocument() {
        return document;
    }

    public String getSite() {
        return site;
    }

    public boolean isStatus() {
        return status;
    }

//    public static void main(String[] args) {
//        TelaPrincipal tp = new TelaPrincipal();
//        CadBancos banco = new CadBancos(false,"18");
//        tp.dispose();
//        System.out.println("Banco Selecionado: " + banco.getTitle());
//    }

}
