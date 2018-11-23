/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import view.TelaPrincipal;
import conn.ConexaoFB;
import conn.ConexaoMySQL;
import javax.swing.JOptionPane;

/**
 *
 * @author ferna
 */
public class AtualizaFornecedores {

    ConexaoMySQL cn;
    ConexaoFB cnfb = new ConexaoFB();

    private String sql, codigo, fornecedor, tipodoc;
    
    public AtualizaFornecedores(boolean x, ConexaoMySQL conn) {
        this.cn = conn;
    
        atualizaTipoDoc();
        
    }

    public AtualizaFornecedores(ConexaoMySQL conn) {
        this.cn = conn;
        sql = "SELECT id FROM fin_contratos WHERE fornecedor = 0 OR fornecedor is null;";
        if (cn.iniciarTransacao()) {
            try {
                cnfb.conecta();

                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    codigo = cn.rs.getString("id");

                    sql = "SELECT fornecedor FROM fin_contas_pagar WHERE empresa = 1 and codigo = '" + codigo + "';";
                    cnfb.executeConsulta(sql);
                    while (cnfb.rs.next()) {
                        fornecedor = cnfb.rs.getString("fornecedor");
                    }
                    cnfb.rs.close();

                    sql = "UPDATE fin_contratos SET fornecedor = '" + fornecedor + "' "
                            + "WHERE id = '" + codigo + "'; ";
                    cn.executeAtualizacao(sql);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
                cnfb.desconecta();

                JOptionPane.showMessageDialog(null, "Fornecedores atualizados com sucesso!");

            }
        }

    }
    
    public void atualizaTipoDoc() {
        sql = "SELECT id FROM fin_contratos WHERE tipodoc = 0 OR tipodoc is null;";
        if (cn.iniciarTransacao()) {
            try {
                cnfb.conecta();

                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    codigo = cn.rs.getString("id");

                    sql = "SELECT tipodoc FROM fin_contas_pagar WHERE empresa = 1 and codigo = '" + codigo + "';";
                    cnfb.executeConsulta(sql);
                    while (cnfb.rs.next()) {
                        tipodoc = cnfb.rs.getString("tipodoc");
                    }
                    cnfb.rs.close();

                    sql = "UPDATE fin_contratos SET tipodoc = '" + tipodoc + "' "
                            + "WHERE id = '" + codigo + "'; ";
                    cn.executeAtualizacao(sql);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
                cnfb.desconecta();

                JOptionPane.showMessageDialog(null, "Tipos de documentos atualizados com sucesso!");

            }
        }

    }

//    public static void main(String[] args) {
//        TelaPrincipal tp = new TelaPrincipal();
//        AtualizaFornecedores atu = new AtualizaFornecedores(false);
//        tp.dispose();
//    }

}
