/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import view.TelaPrincipal;
import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ferna
 */
public class Inventario {
    
    private final conn.ConexaoFB cnfb = new ConexaoFB();
    private ConexaoMySQL cn;
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private final DecimalFormat df = new DecimalFormat("#,##0.00");
    private int empresa, produto, setor, tabelatrib, deposito,
            locacao, marca, aplicacao;
    private double quantidade, custo, valor, qtd_peso_item;
    private Date competencia;
    private String nomeproduto, nome_setor, nomeunidade, classfiscal, sql,
            referencia;
    
    private boolean resposta;
    private String caminho;
    private int importar;
    
    public Inventario(ConexaoMySQL conn) {
        this.cn = conn;
    }

    /**
     * Pesquisa inventário no Firebird. Após pesquisa, salva os dados no MySQL.
     * Inventário é importado por completo, incluindo imobilizado e materiais de
     * uso e consumo.
     *
     * @param data No formato 'dd/MM/aaaa'
     * @param empresa Informar o código interno
     * @return Informa se sincronização foi realizada.
     */
    public boolean importaInventario(String data, int empresa) {

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
        
        sql = "select * from "
                + "est_rel_inventario(1,'1,2,3,4,5,6,9,10,11,12,13',null,null,"
                + "'M','" + data.replace("/", ".") + "',2,'S',null,null,null,"
                + "null,null,null,null,'A',null,'S');";
        try {
            competencia = dateIn.parse(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if (cn.iniciarTransacao()) {
            try {
                cn.executeAtualizacao("DELETE FROM inventario "
                        + "WHERE empresa = '" + empresa + "' AND "
                        + "data = '" + dateOut.format(competencia) + "';");
                if (cnfb.conecta()) {
                    try {
                        cnfb.executeConsulta(sql);
                        while (cnfb.rs.next()) {
                            this.empresa = empresa;
                            this.produto = cnfb.rs.getInt("produto");
                            this.nomeproduto = cnfb.rs.getString("nomeproduto");
                            this.quantidade = cnfb.rs.getDouble("quantidade");
                            this.custo = cnfb.rs.getDouble("custo");
                            this.valor = cnfb.rs.getDouble("valor");
                            this.setor = cnfb.rs.getInt("setor");
                            this.nome_setor = cnfb.rs.getString("nome_setor");
                            this.nomeunidade = cnfb.rs.getString("nomeunidade");
                            this.referencia = null;//cnfb.rs.getString("referencia");
                            this.classfiscal = cnfb.rs.getString("classfiscal");
                            this.tabelatrib = cnfb.rs.getInt("tabelatrib");
                            this.deposito = cnfb.rs.getInt("deposito");
                            this.locacao = cnfb.rs.getInt("locacao");
                            this.marca = cnfb.rs.getInt("marca");
                            this.aplicacao = cnfb.rs.getInt("aplicacao");
                            this.qtd_peso_item = cnfb.rs.getDouble("qtd_peso_item");
                            
                            sql = "INSERT INTO `fechamento`.`inventario` (`empresa`, "
                                    + "`data`, `produto`, `nomeproduto`, `quantidade`, "
                                    + "`custo`, `valor`, `setor`, `nome_setor`, "
                                    + "`nomeunidade`, `referencia`, `classfiscal`, "
                                    + "`tabelatrib`, `deposito`, `locacao`, `marca`, "
                                    + "`aplicacao`, `qtd_peso_item`) VALUES (" + this.empresa + ", "
                                    + "'" + dateOut.format(competencia) + "','" + produto + "','" + nomeproduto + "',"
                                    + "'" + quantidade + "','" + custo + "','" + valor + "',"
                                    + "'" + setor + "','" + nome_setor + "','" + nomeunidade + "',"
                                    + " " + referencia + ",'" + classfiscal + "'," + tabelatrib + ","
                                    + " " + deposito + "," + locacao + "," + marca + ","
                                    + " " + aplicacao + ",'" + qtd_peso_item + "');";
                            
                            resposta = cn.executeAtualizacao(sql);
                            
                        }
                    } catch (Exception e) {
                        resposta = false;
                        JOptionPane.showMessageDialog(null, e);
                    } finally {
                        cnfb.desconecta();
                        cn.finalizarTransacao();
                    }
                } else {
                    resposta = false;
                }
            } catch (SQLException e) {
                tools.DefaultMsg.errorMsg(e.getMessage());
            }
        } else {
            resposta = false;
        }
        
        return resposta;
    }
    
    public double carregaDados(String data, int empresa, String setor) {
        
        if (cn.iniciarTransacao()) {
            try {
                competencia = dateIn.parse(data);
                
                sql = "SELECT sum(valor) as valor FROM inventario WHERE data = '" + dateOut.format(competencia) + "' "
                        + "AND empresa = '" + empresa + "' AND setor = '" + setor + "';";
                
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    valor = cn.rs.getDouble("valor");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }
        
        System.out.println("Valor do Setor: " + df.format(valor));
        
        return valor;
    }
    
    public boolean carregaDados(String data, int empresa, int produto) {
        
        try {
            competencia = dateIn.parse(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        sql = "SELECT * FROM inventario WHERE data = '" + dateOut.format(competencia) + "' "
                + "AND empresa = '" + empresa + "' AND produto = '" + produto + "';";
        if (cn.iniciarTransacao()) {
            try {
                resposta = cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    this.empresa = empresa;
                    this.produto = cn.rs.getInt("produto");
                    this.nomeproduto = cn.rs.getString("nomeproduto");
                    this.quantidade = cn.rs.getDouble("quantidade");
                    this.custo = cn.rs.getDouble("custo");
                    this.valor = cn.rs.getDouble("valor");
                    this.setor = cn.rs.getInt("setor");
                    this.nome_setor = cn.rs.getString("nome_setor");
                    this.nomeunidade = cn.rs.getString("nomeunidade");
                    this.referencia = cn.rs.getString("referencia");
                    this.classfiscal = cn.rs.getString("classfiscal");
                    this.tabelatrib = cn.rs.getInt("tabelatrib");
                    this.deposito = cn.rs.getInt("deposito");
                    this.locacao = cn.rs.getInt("locacao");
                    this.marca = cn.rs.getInt("marca");
                    this.aplicacao = cn.rs.getInt("aplicacao");
                    this.qtd_peso_item = cn.rs.getDouble("qtd_peso_item");
                    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }
        
        System.out.print("Produto: " + nomeproduto);
        System.out.println(". Valor: " + valor);
        
        return resposta;
    }
    
    public int getEmpresa() {
        return empresa;
    }
    
    public int getProduto() {
        return produto;
    }
    
    public int getSetor() {
        return setor;
    }
    
    public int getTabelatrib() {
        return tabelatrib;
    }
    
    public int getDeposito() {
        return deposito;
    }
    
    public int getLocacao() {
        return locacao;
    }
    
    public int getMarca() {
        return marca;
    }
    
    public int getAplicacao() {
        return aplicacao;
    }
    
    public double getQuantidade() {
        return quantidade;
    }
    
    public double getCusto() {
        return custo;
    }
    
    public double getValor() {
        return valor;
    }
    
    public double getQtd_peso_item() {
        return qtd_peso_item;
    }
    
    public String getNomeproduto() {
        return nomeproduto;
    }
    
    public String getNome_setor() {
        return nome_setor;
    }
    
    public String getNomeunidade() {
        return nomeunidade;
    }
    
    public String getClassfiscal() {
        return classfiscal;
    }
    
    public String getReferencia() {
        return referencia;
    }
    
}
