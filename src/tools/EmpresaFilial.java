/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoMySQL;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Pesquisa código da empresa nos sistemas da Folha, Contabilidade, e
 * Fechamento.
 *
 * @author luiz.barcellos
 */
public class EmpresaFilial {
    
    private final ConexaoMySQL cn;
    private String codFolha;
    private String codCont;
    private String codFech;
    private String sql;
    private String codOcepar;
    private String codDominio;
    private String nome;
    private String filFolha;
    private String filCont;
    private String filDominio;
    private String filTipo;
    private String filNome;
    
    public EmpresaFilial(ConexaoMySQL conn) {
        this.cn = conn;
    }
    
    public void codEmpresa(String id, String origem) {
        codEmpresa(id, origem, "Não Informada");
    }

    /**
     * Recebe solicitação e grava nas variáveis os dados de integração.
     *
     * @param id
     * @param origem Identifica de onde pertence o ID informado. FOLHA,
     * CONTABIL, FECHAMENTO, DOMINIO, OCEPAR
     */
    public void codEmpresa(String id, String origem, String idFilial) {
        
        this.codFolha = null;
        this.codCont = null;
        this.codFech = null;
        this.codOcepar = null;
        this.codDominio = null;
        this.nome = null;
        
        try {
            if ("FOLHA".equals(origem)) {
                sql = "SELECT * FROM fechamento.empresa WHERE id_folha = '" + id + "';";
            } else if ("CONTABIL".equals(origem)) {
                sql = "SELECT * FROM fechamento.empresa WHERE id_erp = '" + id + "';";
            } else if ("FECHAMENTO".equals(origem)) {
                sql = "SELECT * FROM fechamento.empresa WHERE id = '" + id + "';";
            } else if ("DOMINIO".equals(origem)) {
                sql = "SELECT * FROM fechamento.empresa WHERE id_dominio = '" + id + "';";
            } else if ("OCEPAR".equals(origem)) {
                sql = "SELECT * FROM fechamento.empresa WHERE id_ocepar = '" + id + "';";
            } else {
                throw new UnsupportedOperationException("Origem \"" + origem + "\" não está configurada.");
            }
            
            if (cn.iniciarTransacao()) {
                try {
                    cn.executeConsulta(sql);
                    cn.rs.last();
                    int qtd = cn.rs.getRow();
                    System.out.println("Tamanho do ResultSet: " + qtd);
                    cn.rs.beforeFirst();
                    
                    if (qtd < 1) {
                        throw new UnsupportedOperationException("Empresa não encontrada. Código: " + id + ". Origem: " + origem + ". Filial: " + idFilial + ".");
                    }
                    
                    while (cn.rs.next()) {
                        this.codFolha = cn.rs.getString("id_folha");
                        this.codCont = cn.rs.getString("id_erp");
                        this.codFech = cn.rs.getString("id");
                        this.codOcepar = cn.rs.getString("id_ocepar");
                        this.codDominio = cn.rs.getString("id_dominio");
                        this.nome = cn.rs.getString("nome");
                    }
                    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cn.finalizarTransacao();
                }
            }
            
            System.out.println("\nNome da empresa: " + nome);
            System.out.println("Código da Folha: " + codFolha);
            System.out.println("Código da Contabilidade: " + codCont);
            System.out.println("Código do Fechamento: " + codFech);
            System.out.println("Código da Ocepar: " + codOcepar);
            System.out.println("Código da Domínio: " + codDominio);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Consulta Empresa Filial", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    /**
     * Recebe solicitação e grava nas variáveis os dados da filial. Carrega os
     * dados da empresa após a consulta.
     *
     *
     * @param idEmp
     * @param idFilial
     * @param origem Identifica de onde pertence o ID informado. FOLHA,
     * CONTABIL, FOLHA, DOMINIO
     * @param tipo 0 = Filial; 1 = Obra;
     */
    public void codFilial(String idEmp, String idFilial, String origem, String tipo) {
        
        this.filFolha = null;
        this.filCont = null;
        this.filDominio = null;
        this.filTipo = null;
        this.filNome = null;
        this.codCont = null;
        
        try {
            if ("CONTABIL".equals(origem)) {
                sql = "SELECT * FROM cad_estabelecimentos WHERE id_empresa = '" + idEmp + "' and tipo = '" + tipo + "' and id = '" + idFilial + "';";
            } else if ("FOLHA".equals(origem)) {
                sql = "SELECT * FROM cad_estabelecimentos WHERE id_empresa = '" + idEmp + "' and tipo = '" + tipo + "' and id_folha = '" + idFilial + "';";
            } else if ("DOMINIO".equals(origem)) {
                sql = "SELECT * FROM cad_estabelecimentos WHERE id_empresa = '" + idEmp + "' and tipo = '" + tipo + "' and id_dominio = '" + idFilial + "';";
            } else {
                throw new UnsupportedOperationException("Origem \"" + origem + "\" não está configurada.");
            }
            
            if (cn.iniciarTransacao()) {
                try {
                    cn.executeConsulta(sql);
                    while (cn.rs.next()) {
                        this.filFolha = cn.rs.getString("id_folha");
                        this.filCont = cn.rs.getString("id");
                        this.filDominio = cn.rs.getString("id_dominio");
                        this.filTipo = cn.rs.getString("tipo");
                        this.filNome = cn.rs.getString("descricao");
                        this.codCont = cn.rs.getString("id_empresa");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cn.finalizarTransacao();
                }
            }
            
            codEmpresa(codCont, "FECHAMENTO", idFilial);
            
            System.out.println("Filial Nome: " + filNome);
            System.out.println("Filial Folha: " + filFolha);
            System.out.println("Filial Contabil: " + filCont);
            System.out.println("Filial Dominio: " + filDominio);
            System.out.println("Filial Tipo: " + filTipo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void codFilial(String idEmp, String idFilial, String origem) {
        
        this.filFolha = null;
        this.filCont = null;
        this.filDominio = null;
        this.filTipo = null;
        this.filNome = null;
        this.codCont = null;
        
        try {
            if ("CONTABIL".equals(origem)) {
                sql = "SELECT * FROM cad_estabelecimentos WHERE id_empresa = '" + idEmp + "' and id = '" + idFilial + "';";
            } else if ("FOLHA".equals(origem)) {
                sql = "SELECT * FROM cad_estabelecimentos WHERE id_empresa = '" + idEmp + "' and id_folha = '" + idFilial + "';";
            } else if ("DOMINIO".equals(origem)) {
                sql = "SELECT * FROM cad_estabelecimentos WHERE id_empresa = '" + idEmp + "' and id_dominio = '" + idFilial + "';";
            } else {
                throw new UnsupportedOperationException("Origem \"" + origem + "\" não está configurada.");
            }
            
            if (cn.iniciarTransacao()) {
                try {
                    cn.executeConsulta(sql);
                    while (cn.rs.next()) {
                        this.filFolha = cn.rs.getString("id_folha");
                        this.filCont = cn.rs.getString("id");
                        this.filDominio = cn.rs.getString("id_dominio");
                        this.filTipo = cn.rs.getString("tipo");
                        this.filNome = cn.rs.getString("descricao");
                        this.codCont = cn.rs.getString("id_empresa");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally {
                    cn.finalizarTransacao();
                }
            }
            
            codEmpresa(codCont, "FECHAMENTO", idFilial);
            
            System.out.println("Filial Nome: " + filNome);
            System.out.println("Filial Folha: " + filFolha);
            System.out.println("Filial Contabil: " + filCont);
            System.out.println("Filial Dominio: " + filDominio);
            System.out.println("Filial Tipo: " + filTipo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public String getCodFolha() {
        return codFolha;
    }
    
    public String getCodCont() {
        return codCont;
    }
    
    public String getCodFech() {
        return codFech;
    }
    
    public String getCodOcepar() {
        return codOcepar;
    }
    
    public String getCodDominio() {
        return codDominio;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getFilFolha() {
        return filFolha;
    }
    
    public String getFilCont() {
        return filCont;
    }
    
    public String getFilDominio() {
        return filDominio;
    }
    
    public String getFilTipo() {
        return filTipo;
    }
    
    public String getFilNome() {
        return filNome;
    }
    
    public static void main(String[] args) {
        ConexaoMySQL conn = new ConexaoMySQL();
        conn.arquivo = "mysql.properties";
        try {
            conn.conecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        EmpresaFilial emp = new EmpresaFilial(conn);

        //emp.codEmpresa("7", "FOLHA");
        emp.codFilial("1", "28", "FOLHA", "0");
    }
    
}
