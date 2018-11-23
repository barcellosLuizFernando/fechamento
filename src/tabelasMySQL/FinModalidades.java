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
public class FinModalidades {

    private ConexaoMySQL cn;

    private Integer id;

    private String descricao;

    private String obs, sql;

    public FinModalidades(ConexaoMySQL conn) {
        this.cn = conn;
    }

    public FinModalidades(Integer id, ConexaoMySQL conn) {
        this.cn = conn;

        sql = "SELECT * FROM fin_modalidades WHERE id = '" + id + "';";
        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    descricao = cn.rs.getString("descricao");
                    obs = cn.rs.getString("obs");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }

        }

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

//    public static void main(String[] args) {
//        TelaPrincipal tp = new TelaPrincipal();
//        FinModalidades mod = new FinModalidades(3);
//        tp.dispose();
//        System.out.println("Modalidade: " + mod.getDescricao());
//    }

}
