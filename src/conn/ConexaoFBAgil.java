package conn;

import static tools.CopyToClipboard.copyToClipboard;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ConexaoFBAgil {

    private Connection conexao;
    private Statement st;
    public ResultSet rs;
    
    boolean isConnected;

    public static int resultadoUpd = 99;
    private String userlog = null;
    public static String arquivo;
    //private TelaPrincipal tela;

    private String url, driver, usuario, senha;

    public static Properties getProp() throws IOException {

        Properties props = new Properties();

        FileInputStream file = new FileInputStream(
                "./src/properties/" + arquivo);
        props.load(file);

        return props;

    }

    public boolean conecta() {

        //JOptionPane.showMessageDialog(null, "vai conectar.");
        isConnected = false;

        try {
            Properties props = getProp();
            driver = props.getProperty("driver");

            if (url == null) {
                url = props.getProperty("url");
            }

            //url = props.getProperty("user");
            //senha = props.getProperty("password");

            /*props.put("user", usuario);
            props.put("password", senha);
            props.put("charset", "UTF8");
            props.put("lc_ctype", "ISO8859_1");*/
            Class.forName(driver);

            conexao = DriverManager.getConnection(url, props);

            isConnected = true;

            conexao.setAutoCommit(false);

            resultadoUpd = 0;

            //tela.setStatus("Conectado", 2);
        } catch (Exception ex) {

            //tela.setStatus("Erro", 2);
            isConnected = false;

            JOptionPane.showMessageDialog(null, "Não foi possível carregar o "
                    + "driver do banco de dados." + ex);
        }

        return isConnected;
    }

    public boolean desconecta() {

        try {

            switch (resultadoUpd) {
                case 0:
                    st = conexao.createStatement();
                    conexao.commit();
                    break;
                default:
                    st = conexao.createStatement();
                    conexao.rollback();
            }

            conexao.close();
            isConnected = true;
            System.out.println("Firebird desconectado com sucesso.");

            //tela.setStatus("Desconectado", 2);
        } catch (SQLException sqlEx) {

            //tela.setStatus("Erro", 2);
            isConnected = false;

            throw new RuntimeException(sqlEx);

        }

        return isConnected;

    }

    public boolean executeAtualizacao(String sql) throws SQLException {

        boolean resposta = true;

        try {
            System.out.println("\nQuery update Firebird: " + sql);
            st = this.conexao.createStatement();
            st.executeUpdate(sql);
            st.close();

        } catch (SQLException sqlEx) {
            resultadoUpd = 1;
            resposta = false;
            throw new SQLException(sqlEx);
        }

        return resposta;

    }

    public ResultSet executeConsulta(String sql) throws SQLException {

        rs = null;

        try {
            System.out.println("\nQuery consulta Firebird: " + sql);
            st = this.conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);
        } catch (SQLException sqlEx) {

            copyToClipboard(sql);

            throw new SQLException(sqlEx);

        }
        return rs;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void commitTransaction() throws Exception {
        conexao.commit();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserlog(String userlog) {
        this.userlog = userlog;
    }
    
    public boolean isConnected(){
        return isConnected;
    }
    
}
