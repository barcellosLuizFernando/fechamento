package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexao {

    private Connection conexao;
    private Statement st;
    public ResultSet rs;

    public String url = "jdbc:firebirdsql:localhost/3050:F:/OneDrive/Programas/PrjMonitorAtividades/MONITORATIVIDADES.FDB";
    String driver = "org.firebirdsql.jdbc.FBDriver";
    String usuario = "sysdba";
    String senha = "masterkey";

    public void conecta(String url, String driver, String usuario, String senha) {

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível carregar o "
                    + "driver do banco de dados." + ex);
        } catch (SQLException sqlEx) {

            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados. " + sqlEx);
        }
    }

    public void desconecta() {

        try {
            conexao.close();

        } catch (SQLException sqlEx) {

            JOptionPane.showMessageDialog(null, "Não foi possível desconectar o banco " + sqlEx);

        }

    }

    public void executeAtualizacao(String sql) {

        try {
            st = conexao.createStatement();
            st.executeUpdate(sql);

        } catch (SQLException sqlEx) {

            JOptionPane.showMessageDialog(null, "Não foi possível executar o comando sql" + sql + ".Erro " + sqlEx);

        }

    }

    public void executeConsulta(String sql) {

        try {
            st = conexao.createStatement();

            rs = st.executeQuery(sql);

        } catch (SQLException sqlEx) {

            JOptionPane.showMessageDialog(null, "Não foi possível executar o comando sql" + sql + "Erro " + sqlEx);

        }

    }
}
