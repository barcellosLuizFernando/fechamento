/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import view.TelaPrincipal;
import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Luiz Fernando Dill Barcellos
 */
public class Balancete {

    private final conn.ConexaoFB cnfb = new ConexaoFB();
    private ConexaoMySQL cn; 
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");

    private int empresa;
    private int conta;
    private String classificacao;
    private String nomeconta;
    private Date data_ult_mov, data_inicial, data_final;
    private Double saldo_ant;
    private Double debitos;
    private Double creditos;
    private Double saldo_atual;
    private String tipo;
    private String tiposaldoant;
    private String tiposaldo;
    private int num_niveis;

    private String sql;
    
    public Balancete(ConexaoMySQL conn){
        this.cn = conn;
    }

    public boolean importaBalancete(int empresa, String data_inicial, String data_final) {
        boolean resposta;

        int importar;
        String caminho = null;

        //DEFINE QUAL O CAMINHO DO BANCO DA EMPRESA
        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta("SELECT local FROM empresa WHERE id = '" + empresa + "';");
                while (cn.rs.next()) {
                    caminho = cn.rs.getString("local");
                }
            } catch (Exception e) {
                importar = 1;
            } finally {
                cn.finalizarTransacao();
            }
        }

        switch (empresa) {
            case 1:
                cnfb.setUrl(null);
                break;
            case 2:
                cnfb.setUrl(caminho);
                break;
            case 3:
                cnfb.setUrl(caminho);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Não há configuração para importar esta empresa.");
                break;
        }
        System.out.println("URL Selecionada: " + cnfb.getUrl());

        if (empresa == 1 | empresa == 2 | empresa == 3) {
            importar = JOptionPane.showConfirmDialog(null, "Importar um balancete irá excluir todos os balancetes já importados"
                    + "\npara esta empresa após o período informado. Deseja continuar?", "Importar Balancete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } else {
            importar = 1;
        }

        if (importar == 0) {
            resposta = true;
            sql = "select * from ctb_rel_balancete(1,'" + data_inicial.replace("/", ".") + "',"
                    + "'" + data_final.replace("/", ".") + "',NULL,NULL,NULL,NULL,'N',NULL,'S');";

            if (cnfb.conecta()) {
                try {
                    cnfb.executeConsulta(sql);

                    this.data_inicial = dateIn.parse(data_inicial);
                    this.data_final = dateIn.parse(data_final);
                    this.empresa = empresa;

                    cn.iniciarTransacao();
                    sql = "DELETE FROM balancete WHERE data_inicial >= '" + dateOut.format(this.data_inicial) + "' "
                            + "AND empresa = '" + empresa + "';";
                    cn.executeAtualizacao(sql);

                    System.out.println("Data Inicial: " + this.data_inicial);
                    System.out.println("Data Final: " + this.data_final);

                    while (cnfb.rs.next()) {
                        System.out.println("Atribuindo valores às variáveis.");
                        conta = cnfb.rs.getInt("conta");
                        classificacao = cnfb.rs.getString("class");
                        nomeconta = cnfb.rs.getString("nomeconta").replace("'", " ");
                        if (cnfb.rs.getString("data_ult_mov") == null) {
                            data_ult_mov = this.data_final;
                            System.out.println("Executou VERDADE");
                        } else {
                            System.out.println("Executou FALSO");
                            data_ult_mov = cnfb.rs.getDate("data_ult_mov");
                        }
                        saldo_ant = cnfb.rs.getDouble("saldo_ant");
                        debitos = cnfb.rs.getDouble("debitos");
                        creditos = cnfb.rs.getDouble("creditos");
                        saldo_atual = cnfb.rs.getDouble("saldo_atual");
                        tipo = cnfb.rs.getString("tipo");
                        tiposaldoant = cnfb.rs.getString("tiposaldoant");
                        tiposaldo = cnfb.rs.getString("tiposaldo");
                        num_niveis = cnfb.rs.getInt("num_niveis");

                        sql = "INSERT INTO balancete (empresa,data_inicial,"
                                + "data_final,conta,class,nomeconta,data_ult_mov,"
                                + "saldo_ant,debitos,creditos,saldo_atual,"
                                + "tipo,tiposaldoant,tiposaldo,num_niveis) VALUES ("
                                + "'" + empresa + "','" + dateOut.format(this.data_inicial) + "',"
                                + "'" + dateOut.format(this.data_final) + "',"
                                + "'" + conta + "','" + classificacao + "',"
                                + "'" + nomeconta + "','" + dateOut.format(data_ult_mov) + "',"
                                + "'" + saldo_ant + "','" + debitos + "',"
                                + "'" + creditos + "','" + saldo_atual + "',"
                                + "'" + tipo + "','" + tiposaldoant + "',"
                                + "'" + tiposaldo + "','" + num_niveis + "');";

                        System.out.println("Executando atualização:");
                        if (cn.executeAtualizacao(sql) == false) {
                            resposta = false;
                            break;
                        }

                    }
                } catch (Exception e) {
                    resposta = false;
                    JOptionPane.showMessageDialog(null, "Não foi possível importar o balancete.\n" + e, "Importação de balancete", JOptionPane.ERROR_MESSAGE);
                } finally {
                    cnfb.desconecta();
                    cn.finalizarTransacao();
                }
            }

        } else {
            resposta = false;
        }

        System.out.println("Resposta do método 'importaBalancete': " + resposta);
        return resposta;
    }

//    public static void main(String[] args) {
//        Balancete bal = new Balancete();
//        TelaPrincipal tp = new TelaPrincipal();
//        bal.importaBalancete(1, "01/01/2016", "31/01/2016");
//        tp.dispose();
//    }

}
