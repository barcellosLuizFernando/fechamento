package conn;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
import view.TelaPrincipal;

public class ConexaoMySQL {

    private Connection conexao;
    private Statement st;
    public ResultSet rs;

    public static int resultadoUpd = 99;
    boolean except = true;

    public static String arquivo;

    public static TelaPrincipal tela;

    private String url, driver, usuario, senha;

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    
    
    public static Properties getProp() throws IOException {
        
        System.out.println("Arquivo: " + arquivo);

        Properties props = new Properties();

        FileInputStream file = new FileInputStream(
                "./src/properties/" + arquivo);
        props.load(file);

        return props;

    }

    public boolean conecta(String user, String pwd) {
        
        this.usuario = user;
        this.senha = pwd;
        
        System.out.println("Usuário: " + this.usuario);
        System.out.println("Senha: " + this.senha);

        boolean resposta = true;

        try {

            System.out.println("\nMySQL - Capturando arquivo de propriedades");
            Properties props = getProp();
            System.out.println("MySQL - Arquivo capturado. Extraindo propriedade...");
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            props.setProperty("user", user);
            props.setProperty("password", pwd);

            System.out.println("MySQL - Propriedades extraídas. Definindo Driver..." + driver);

            Class.forName(driver);

            System.out.println("MySQL - Driver Definido. Criando conexão... " + url);
            conexao = DriverManager.getConnection(url, props);

            tela.setStatus("Conectado", 1);

        } catch (ClassNotFoundException | NullPointerException ex) {
            tela.setStatus("Desconectado", 1);
            resposta = false;
            JOptionPane.showMessageDialog(null, "Não foi possível carregar o "
                    + "driver do banco de dados.\n" + ex);
        } catch (SQLException sqlEx) {
            tela.setStatus("Desconectado", 1);
            resposta = false;
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados. \n" + sqlEx);
        } catch (IOException ex) {
            tela.setStatus("Desconectado", 1);
            resposta = false;
            JOptionPane.showMessageDialog(null, "Não possível carregar o arquivo de configurações. \n" + ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return resposta;
    }

    public boolean conecta() throws Exception {

        boolean resposta = true;

        try {
            System.out.println("\nMySQL - Capturando arquivo de propriedades");
            Properties props = getProp();
            System.out.println("MySQL - Arquivo capturado. Extraindo propriedade...");
            driver = props.getProperty("driver");
            url = props.getProperty("url");

            System.out.println("MySQL - Propriedades extraídas. Definindo Driver..." + driver);

            Class.forName(driver);

            System.out.println("MySQL - Driver Definido. Criando conexão... ");
            conexao = DriverManager.getConnection(url, props);

            tela.setStatus("Conectado", 1);

        } catch (ClassNotFoundException | NullPointerException ex) {
            tela.setStatus("Desconectado", 1);
            resposta = false;
            JOptionPane.showMessageDialog(null, "Não foi possível carregar o "
                    + "driver do banco de dados.\n" + ex);
        } catch (SQLException sqlEx) {
            tela.setStatus("Desconectado", 1);
            resposta = false;
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados. \n" + sqlEx);
        } catch (IOException ex) {
            tela.setStatus("Desconectado", 1);
            resposta = false;
            JOptionPane.showMessageDialog(null, "Não possível carregar o arquivo de configurações. \n" + ex);
        }

        return resposta;
    }

    /*public Connection getConnection() {

        boolean resposta = true;

        try {
            System.out.println("MySQL - Capturando arquivo de propriedades");
            Properties props = getProp();
            System.out.println("MySQL - Arquivo capturado. Extraindo propriedade...");
            driver = props.getProperty("driver");
            url = props.getProperty("url");

            System.out.println("MySQL - Propriedades extraídas. Definindo Driver..." + driver);

            Class.forName(driver);

            System.out.println("MySQL - Driver Definido. Criando conexão...");
            conexao = DriverManager.getConnection(url, props);
            st = conexao.createStatement();

            resultadoUpd = 0;

            st.executeUpdate("begin;");

        } catch (ClassNotFoundException | NullPointerException ex) {
            resposta = false;
            JOptionPane.showMessageDialog(null, "Não foi possível carregar o "
                    + "driver do banco de dados." + ex);
        } catch (SQLException sqlEx) {
            resposta = false;
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados. " + sqlEx);
        } catch (IOException ex) {
            resposta = false;
            JOptionPane.showMessageDialog(null, "Não possível carregar o arquivo de configurações. " + ex);
        }

        return conexao;
    }*/
    public void desconecta() throws Exception {

        try {

            conexao.close();

            System.out.println("MySQL - Conexão finalizada com sucesso.");

            tela.setStatus("Desconectado", 1);

        } catch (Exception sqlEx) {

            tela.setStatus("Erro", 1);
            System.out.println("MySQL - Conexão não encerrada.");

            JOptionPane.showMessageDialog(null, "Não foi possível desconectar o banco. \n" + sqlEx);

        }

    }

    public boolean executeAtualizacao(String sql) throws SQLException {

        boolean resposta = true;

        try {
            st = conexao.createStatement();

            System.out.println("\nPreparando para executar Query: \n" + sql);
            st.executeUpdate(sql, st.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();

            resultadoUpd = 0;

        } catch (SQLException sqlEx) {
            resultadoUpd = 1;
            if (except) {
                resposta = false;
                //JOptionPane.showMessageDialog(null, "Não foi possível executar o comando sql\n" + sql + ".\nErro " + sqlEx);
                //JOptionPane.showMessageDialog(null, "Não foi possível executar o comando.\nErro: " + sqlEx.getMessage());
                throw new SQLException(sqlEx);
            }
        }

        System.out.println("Upd " + resultadoUpd + "\nStatus do Except: " + except);
        return resposta;
    }

    public boolean executeConsulta(String sql) throws SQLException {

        boolean resposta = true;

        try {
            st = conexao.createStatement();
            System.out.println("\nMySQL - Realizando consulta ao banco de dados MySQL: " + sql);

            rs = st.executeQuery(sql);

        } catch (SQLException sqlEx) {
            resposta = false;
            System.out.println("MySQL - Consulta não realizada no banco de dados MySQL");
            //JOptionPane.showMessageDialog(null, "Não foi possível executar o comando sql\n" + sql + "\nErro " + sqlEx);
            throw new SQLException("Não foi possível executar o comando sql\n" + sql + "\nErro " + sqlEx);
        }

        System.out.println("Resposta da consulta: " + resposta);
        return resposta;
    }

    public int getResultadoUpd() {
        return resultadoUpd;
    }

    public void setResultadoUpd(int resultadoUpd) {
        this.resultadoUpd = resultadoUpd;
    }

    public boolean isExcept() {
        return except;
    }

    public void setExcept(boolean except) {
        this.except = except;
    }

    public Connection getConexao() {
        return conexao;
    }

    public boolean iniciarTransacao() {
        boolean resposta = false;
        try {
            conexao.setAutoCommit(false);
            resposta = true;
            System.out.println("\nMYSQL - Transação Iniciada.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao setar autocommit:\n" + e);
            resposta = false;
        }
        return resposta;
    }

    public boolean finalizarTransacao() {

        boolean resposta = false;

        try {
            switch (resultadoUpd) {
                case 0:
                    conexao.commit();
                    break;
                default:
                    conexao.rollback();
            }
            resposta = true;
            System.out.println("MYSQL - Transação Finalizada.\n");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar transação:\n" + e);
            resposta = false;
        }

        return resposta;
    }

}
