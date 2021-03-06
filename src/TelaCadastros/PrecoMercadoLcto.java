package TelaCadastros;

import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ferna
 */
public class PrecoMercadoLcto extends javax.swing.JInternalFrame {

    int lancamento_atual;
    int lancamentos_jpb;

    private final ConexaoFB cnfb = new ConexaoFB();

    private ConexaoMySQL cn;

    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    private final DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form QuebraTecnicaLcto
     */
    public PrecoMercadoLcto(ConexaoMySQL conn) {
        initComponents();
        this.cn = conn;
        montaTabela();
        montaCompetencias();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBtnGravar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTxtQuebra = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jBtnPesquisar = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jBtnEstornar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jpb = new javax.swing.JProgressBar();

        setClosable(true);
        setTitle("Lançamento de Quebra Técnica");

        jBtnGravar.setText("Gravar");
        jBtnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGravarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Quantidade");

        jTxtQuebra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtQuebra.setEnabled(false);

        jButton4.setText("Limpar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtQuebra, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnGravar)
                    .addComponent(jButton2)
                    .addComponent(jLabel2)
                    .addComponent(jTxtQuebra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setText("Competência");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Estornar");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        jBtnEstornar.setText("Estornar");
        jBtnEstornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEstornarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jBtnEstornar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(jBtnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnPesquisar)
                    .addComponent(jCheckBox1)
                    .addComponent(jBtnEstornar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jpb.setOrientation(1);
        jpb.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGravarActionPerformed
        insereDados();


    }//GEN-LAST:event_jBtnGravarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        if (!"-1".equals(jComboBox1.getSelectedIndex() + "")) {

            jComboBox1.setEnabled(false);

            montaTabela();
            incluiPesquisa();

            jBtnGravar.setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(this, "Sem dados para consultar.");
        }

    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        montaTabela();
        montaCompetencias();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        if (jCheckBox1.isSelected()) {

            jBtnPesquisar.setEnabled(false);
            jBtnEstornar.setEnabled(true);

            jComboBox1.setEnabled(true);

            String data;

            jComboBox1.removeAllItems();

            String sql = "SELECT DISTINCT competencia "
                    + "FROM rel_provisao_estoque "
                    + "WHERE competencia IN (SELECT DISTINCT competencia FROM preco_mercado_lcto) "
                    + "ORDER BY competencia;";

            try {
                cn.iniciarTransacao();
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    data = dateIn.format(cn.rs.getDate("competencia"));
                    jComboBox1.addItem(data);
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Não foi possível capturar as competências.");
            }
            cn.finalizarTransacao();

        } else {
            montaCompetencias();
            montaTabela();
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jBtnEstornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEstornarActionPerformed
        lancamento_atual = 0;

        estornaDados();

    }//GEN-LAST:event_jBtnEstornarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEstornar;
    private javax.swing.JButton jBtnGravar;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTxtQuebra;
    private javax.swing.JProgressBar jpb;
    // End of variables declaration//GEN-END:variables

    private void montaTabela() {
        jCheckBox1.setSelected(false);
        jBtnPesquisar.setEnabled(true);
        jBtnEstornar.setEnabled(false);

        DefaultTableModel lista = (DefaultTableModel) jTable1.getModel();

        jBtnGravar.setEnabled(false);

        lista.setColumnCount(0);
        lista.setRowCount(0);

        lista.addColumn("Status");
        lista.addColumn("Competencia");
        lista.addColumn("Codigo");
        lista.addColumn("Mercadoria");
        lista.addColumn("Valor");

        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(direita);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(direita);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable1.getColumnModel().getColumn(0).setMinWidth(100);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(100);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(100);

        //jpb.setValue(0);
    }

    private void incluiPesquisa() {

        DefaultTableModel lista = (DefaultTableModel) jTable1.getModel();

        Double quebra = 0.00;

        String data = (String) jComboBox1.getSelectedItem();

        lancamentos_jpb = 0;

        try {
            //AJUSTA DATA
            Date comp = (Date) dateIn.parse(data);
            String competencia = dateOut.format(comp);

            String sql = "SELECT * from rel_provisao_estoque "
                    + "WHERE competencia = '" + competencia + "' "
                    + "ORDER BY mercadoria;";
            //JOptionPane.showMessageDialog(this, competencia);

            cn.iniciarTransacao();
            cn.executeConsulta(sql);

            while (cn.rs.next()) {
                lista.addRow(new String[]{
                    cn.rs.getString("status"),
                    dateIn.format(cn.rs.getDate("competencia")),
                    cn.rs.getString("cod_mercadoria"),
                    cn.rs.getString("mercadoria"),
                    df.format(cn.rs.getDouble("valor"))
                });

                quebra++;

                lancamentos_jpb++;

            }
            cn.finalizarTransacao();

        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível processar os lançamentos: " + ex);
        }

        jTxtQuebra.setText(df.format(quebra));

    }

    private void montaCompetencias() {
        jComboBox1.setEnabled(true);

        String data;

        jComboBox1.removeAllItems();

        String sql = "SELECT DISTINCT competencia "
                + "FROM rel_provisao_estoque "
                + "WHERE competencia NOT IN (SELECT DISTINCT competencia FROM preco_mercado_lcto) "
                + "ORDER BY competencia;";

        try {
            cn.iniciarTransacao();
            cn.executeConsulta(sql);
            while (cn.rs.next()) {
                data = dateIn.format(cn.rs.getDate("competencia"));
                jComboBox1.addItem(data);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível capturar as competências.");
        }
        cn.finalizarTransacao();
    }

    private void insereDados() {
        new Thread() {
            public void run() {

                lancamento_atual = 0;

                String numero = null;
                String transacoes = null;

                String empresa = "1";
                String data = (String) jComboBox1.getSelectedItem();
                String produto;
                Double valor;
                
                ArrayList sqlList = new ArrayList();

                try {

                    //DEFINE DATA PARA O MYSQL
                    Date comp = (Date) dateIn.parse(data);
                    String competencia = dateOut.format(comp);

                    int validacao = 0;

                    String sqlmy = "SELECT * from rel_provisao_estoque "
                            + "WHERE competencia = '" + competencia + "' "
                            + "ORDER BY mercadoria;";
                    String sqlfb;

                    cn.iniciarTransacao();
                    cn.executeConsulta(sqlmy);

                    while (cn.rs.next()) {

                        valor = cn.rs.getDouble("valor");

                        if (valor == 0.00) {
                            validacao = 1;
                        }
                    }
                    cn.rs.close();

                    cnfb.conecta();

                    if (validacao == 0) {
                        cn.executeConsulta(sqlmy);
                        while (cn.rs.next()) {

                            produto = cn.rs.getString("cod_mercadoria");
                            valor = cn.rs.getDouble("valor");

                            sqlfb = "INSERT INTO CAD_PRODUTOS_PRC_MERCADO (empresa,"
                                    + "codigo,data,preco) VALUES ('" + empresa + "',"
                                    + "'" + produto + "','" + data.replace("/", ".") + "',"
                                    + "'" + valor + "');";
                            cnfb.executeAtualizacao(sqlfb);

                            
                            sqlmy = "INSERT INTO preco_mercado_lcto (competencia,"
                                    + "dt_integracao,cod_mercadoria,valor) "
                                    + "VALUES ('" + competencia + "', current_date(), "
                                    + "'" + produto + "','" + valor + "');";
                            
                            sqlList.add(sqlmy);

                            lancamento_atual++;
                            preencheBarra();

                        }
                        cn.rs.close();
                        
                        for (int i = 0; i < sqlList.size(); i++) {
                            
                            cn.executeAtualizacao(sqlList.get(i).toString());
                            
                        }

                        JOptionPane.showMessageDialog(null, "Lançamentos efetuados com sucesso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Você precisa definir o preço de mercado para todos os produtos.");
                        ConexaoFB.resultadoUpd = 1;
                    }

                } catch (Exception ex) {

                    ConexaoFB.resultadoUpd = 1;
                    ConexaoMySQL.resultadoUpd = 1;

                    JOptionPane.showMessageDialog(null, ex);
                }

                if (ConexaoFB.resultadoUpd < 1) {

                    cn.finalizarTransacao();
                    cnfb.desconecta();

                    montaCompetencias();
                    montaTabela();
                }
            }
        }.start();
    }

    private void estornaDados() {
        new Thread() {
            public void run() {

                String data = (String) jComboBox1.getSelectedItem();
                Date comp;
                int lancamentos = 0;

                //AJUSTA DATA
                try {
                    comp = (Date) dateIn.parse(data);
                    String competencia = dateOut.format(comp);

                    cnfb.conecta();

                    cn.iniciarTransacao();

                    //DEFINE NÚMERO DE LANÇAMENTOS PARA A BARRA DE PROGRESSO
                    cn.executeConsulta("SELECT count(competencia) FROM preco_mercado_lcto "
                            + "WHERE competencia = '" + competencia + "';");
                    while (cn.rs.next()) {
                        lancamentos_jpb = cn.rs.getInt(1);
                    }
                    cn.rs.close();

                    cn.executeConsulta("SELECT cod_mercadoria FROM preco_mercado_lcto "
                            + "WHERE competencia = '" + competencia + "';");

                    while (cn.rs.next()) {
                        lancamentos = cn.rs.getInt(1);

                        //JOptionPane.showMessageDialog(this, lancamentos);
                        cnfb.executeAtualizacao("DELETE FROM cad_produtos_prc_mercado "
                                + "WHERE codigo = " + lancamentos + " "
                                + "AND empresa = 1 "
                                + "AND data = '" + data.replace("/", ".") + "';");
                        cn.executeAtualizacao("DELETE FROM preco_mercado_lcto "
                                + "WHERE cod_mercadoria = '" + lancamentos + "' "
                                + "AND competencia = '" + competencia + "';");

                        lancamento_atual++;

                        preencheBarra();

                    }
                    JOptionPane.showMessageDialog(null, "Estorno efetuado com sucesso.");

                } catch (Exception ex) {

                    ConexaoFB.resultadoUpd = 1;
                    ConexaoMySQL.resultadoUpd = 1;

                    JOptionPane.showMessageDialog(null, "Não foi possível estornar os lançamentos: " + ex);
                }

                cnfb.desconecta();
                cn.finalizarTransacao();

                montaCompetencias();
                montaTabela();

                jpb.setValue(0);

            }
        }.start();
    }

    private void preencheBarra() {
        new Thread() {
            public void run() {
                //recebe dados
                jpb.setMinimum(0);
                jpb.setMaximum(lancamentos_jpb);

                jpb.setValue(lancamento_atual);

                jpb.setToolTipText((lancamento_atual + "/" + lancamentos_jpb));

            }
        }.start();
    }

}
