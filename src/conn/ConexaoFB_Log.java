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

public class ConexaoFB_Log {

    private Connection conexao;
    private Statement st;
    public ResultSet rs;

    public static int resultadoUpd = 99;
    
    public static String arquivo;

    String url, driver, usuario, senha;

    public static Properties getProp() throws IOException {

        Properties props = new Properties();

        FileInputStream file = new FileInputStream(
                "./src/properties/" + arquivo);
        props.load(file);

        return props;

    }

    public boolean conecta() {

        //JOptionPane.showMessageDialog(null, "vai conectar.");
        boolean isConnected = false;

        try {
            Properties props = getProp();
            driver = props.getProperty("driver");
            url = props.getProperty("url");

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

        } catch (Exception ex) {

            isConnected = false;

            JOptionPane.showMessageDialog(null, "Nãooo foi possível carregar o "
                    + "driver do banco de dados." + ex);
        }

        return isConnected;
    }

    public boolean desconecta() {

        boolean isConnected = false;

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

        } catch (SQLException sqlEx) {

            isConnected = false;

            throw new RuntimeException(sqlEx);

        }

        return isConnected;

    }

    public int executeAtualizacao(String sql) {

        int result = -1;

        try {
            st = this.conexao.createStatement();
            result = st.executeUpdate(sql);
            st.close();

        } catch (SQLException sqlEx) {
            resultadoUpd = 1;
            throw new RuntimeException(sqlEx);

        }

        return result;

    }

    public ResultSet executeConsulta(String sql) {

        rs = null;

        try {
            st = this.conexao.createStatement();

            rs = st.executeQuery(sql);

            return rs;

        } catch (SQLException sqlEx) {

            throw new RuntimeException(sqlEx);

        }

        //return rs;
    }
}
