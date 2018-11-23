/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelaCadastros;

import conn.ConexaoMySQL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class CadUsuarios2 extends javax.swing.JInternalFrame {

    public CadUsuarios2(ConexaoMySQL conn) {
        
        initComponents();
        this.cn = conn;
    }

    

    private ConexaoMySQL cn;

    DefaultTableModel lista = new DefaultTableModel();

    public void MontaLista() {
        if (!"".equals(txtPesquisar.getText())) {
            lista.setColumnCount(0);  //limpa a tabela
            lista.setRowCount(0);     //limpa a tabela
            cn.iniciarTransacao();
            String sql = "SELECT * FROM cad_usuario "
                    + "WHERE UPPER(nome) "
                    + "LIKE '%" + txtPesquisar.getText().toUpperCase() + "%' OR departamento "
                    + "LIKE '%" + txtPesquisar.getText() + "%'"
                    + " ORDER BY nome";

            try {
            cn.executeConsulta(sql);

            lista.addColumn("Código");
            lista.addColumn("Nome");
            lista.addColumn("Departamento");
            lista.addColumn("Função");
            lista.addColumn("Login");
                while (cn.rs.next()) {

                    lista.addRow(new String[]{cn.rs.getString("codigo"), cn.rs.getString("nome"), cn.rs.getString("departamento"), cn.rs.getString("funcao"), cn.rs.getString("login")});
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados: " + ex);
            }
            cn.finalizarTransacao();
            tabCadUser.setModel(lista);
        } else {
            lista.setColumnCount(0);
            lista.setRowCount(0);
            tabCadUser.setModel(lista);
            btnEditarUser.setEnabled(false);
            btnCadastrarUser.setEnabled(true);
        }
    }

    public void Selecao() {

        int linha = tabCadUser.getSelectedRow();

        txtCodUser.setText(tabCadUser.getValueAt(linha, 0).toString());
        txtNomeUser.setText(tabCadUser.getValueAt(linha, 1).toString());
        txtDptoUser.setText(tabCadUser.getValueAt(linha, 2).toString());
        txtFuncUser.setText(tabCadUser.getValueAt(linha, 3).toString());
        txtLoginUser.setText(tabCadUser.getValueAt(linha, 4).toString());
    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDptoUser = new javax.swing.JTextField();
        txtFuncUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNomeUser = new javax.swing.JTextField();
        txtCodUser = new javax.swing.JLabel();
        txtLoginUser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        btnCadastrarUser = new javax.swing.JButton();
        btnGravarUser = new javax.swing.JButton();
        btnCancelarUser = new javax.swing.JButton();
        btnEditarUser = new javax.swing.JButton();
        txtSenhaUser = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtAtivoUser = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabCadUser = new javax.swing.JTable();

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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Usuários");
        setVisible(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel1.setText("Nome");

        jLabel2.setText("Departamento");

        jLabel3.setText("Função");

        txtDptoUser.setEnabled(false);

        txtFuncUser.setEnabled(false);

        jLabel4.setText("Código");

        txtNomeUser.setEnabled(false);

        txtCodUser.setText(" ");

        txtLoginUser.setEnabled(false);

        jLabel6.setText("Login");

        jLabel7.setText("Senha");

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        btnCadastrarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cadastrar.png"))); // NOI18N
        btnCadastrarUser.setText("Cadastrar");
        btnCadastrarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarUserActionPerformed(evt);
            }
        });

        btnGravarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Gravar.png"))); // NOI18N
        btnGravarUser.setText("Gravar");
        btnGravarUser.setEnabled(false);
        btnGravarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarUserActionPerformed(evt);
            }
        });

        btnCancelarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cancelar.png"))); // NOI18N
        btnCancelarUser.setText("Cancelar");
        btnCancelarUser.setEnabled(false);
        btnCancelarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUserActionPerformed(evt);
            }
        });

        btnEditarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Editar.png"))); // NOI18N
        btnEditarUser.setText("Editar");
        btnEditarUser.setEnabled(false);
        btnEditarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUserActionPerformed(evt);
            }
        });

        txtSenhaUser.setEnabled(false);

        jLabel8.setText("Ativo");

        txtAtivoUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "S", "N" }));
        txtAtivoUser.setEnabled(false);
        txtAtivoUser.setFocusable(false);

        jLabel5.setText("Status");

        txtStatus.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomeUser))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodUser)
                                .addGap(80, 80, 80)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAtivoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtLoginUser))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(19, 19, 19)
                                        .addComponent(txtDptoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtFuncUser, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtSenhaUser)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCadastrarUser))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEditarUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGravarUser)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCadastrarUser)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCodUser)
                    .addComponent(jLabel8)
                    .addComponent(txtAtivoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtStatus))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDptoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtFuncUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtLoginUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtSenhaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravarUser)
                    .addComponent(btnCancelarUser)
                    .addComponent(btnEditarUser))
                .addContainerGap())
        );

        tabCadUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabCadUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCadUserMouseClicked(evt);
            }
        });
        tabCadUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabCadUserKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabCadUser);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarUserActionPerformed
        if (!"".equals(txtDptoUser.getText())
                && !"".equals(txtFuncUser.getText())
                && !"".equals(txtLoginUser.getText())
                && !"".equals(txtNomeUser.getText())) {

            String dptoUser = txtDptoUser.getText();
            String funcaoUser = txtFuncUser.getText();
            String loginUser = txtLoginUser.getText();
            String nomeUser = txtNomeUser.getText();
            String senhaUser = new String(this.txtSenhaUser.getPassword());
            String ativoUser = txtAtivoUser.getSelectedItem().toString();
            
            cn.iniciarTransacao();
            if (txtStatus.getText() == "CADASTRANDO") {
                String sql = "INSERT INTO CAD_USUARIO (NOME, DEPARTAMENTO, FUNCAO, ATIVO, LOGIN, SENHA) VALUES ('" + nomeUser + "','" + dptoUser + "','" + funcaoUser + "','" + ativoUser + "','" + loginUser + "','" + senhaUser + "')";
                //cn.executeAtualizacao(sql);
            } else {
                String sql = "UPDATE CAD_USUARIO SET NOME = '" + nomeUser + "', DEPARTAMENTO = '" + dptoUser + "', FUNCAO = '" + funcaoUser + "', ATIVO = '" + ativoUser + "' WHERE codigo = " + Integer.parseInt(txtCodUser.getText());
                //        + "(NOME, DEPARTAMENTO, FUNCAO, ATIVO) VALUES ('" + nomeUser
                //cn.executeAtualizacao(sql);
            }
            cn.finalizarTransacao();
            txtDptoUser.setText("");
            txtFuncUser.setText("");
            txtLoginUser.setText("");
            txtNomeUser.setText("");
            txtSenhaUser.setText("");
            txtAtivoUser.setToolTipText("");
            txtCodUser.setText(" ");
            txtStatus.setText("");
            txtNomeUser.setEnabled(false);
            txtAtivoUser.setEnabled(false);
            txtDptoUser.setEnabled(false);
            txtLoginUser.setEnabled(false);
            txtFuncUser.setEnabled(false);
            txtNomeUser.setEnabled(false);
            txtPesquisar.setEnabled(true);
            btnEditarUser.setEnabled(false);
            btnGravarUser.setEnabled(false);
            btnCancelarUser.setEnabled(false);
            btnCadastrarUser.setEnabled(true);
            MontaLista();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Algum campo está em Branco.");
        }
    }//GEN-LAST:event_btnGravarUserActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        MontaLista();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tabCadUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCadUserMouseClicked
        btnEditarUser.setEnabled(true);
        Selecao();
    }//GEN-LAST:event_tabCadUserMouseClicked

    private void tabCadUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabCadUserKeyReleased
        Selecao();
    }//GEN-LAST:event_tabCadUserKeyReleased

    private void btnEditarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUserActionPerformed
        txtNomeUser.setEnabled(true);
        txtAtivoUser.setEnabled(true);
        txtDptoUser.setEnabled(true);
        txtLoginUser.setEnabled(true);
        txtFuncUser.setEnabled(true);
        txtPesquisar.setEnabled(false);
        btnGravarUser.setEnabled(true);
        btnEditarUser.setEnabled(false);
        btnCancelarUser.setEnabled(true);
        btnCadastrarUser.setEnabled(false);
        txtStatus.setText("EDITANDO");
    }//GEN-LAST:event_btnEditarUserActionPerformed

    private void btnCancelarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUserActionPerformed
        txtNomeUser.setEnabled(false);
        txtCodUser.setText(" ");
        txtAtivoUser.setEnabled(false);
        txtDptoUser.setEnabled(false);
        txtLoginUser.setEnabled(false);
        txtFuncUser.setEnabled(false);
        txtSenhaUser.setEnabled(false);
        txtStatus.setText(" ");
        txtPesquisar.setEnabled(true);
        btnGravarUser.setEnabled(false);
        btnCancelarUser.setEnabled(false);
        btnEditarUser.setEnabled(true);
        btnCadastrarUser.setEnabled(true);
        MontaLista();
    }//GEN-LAST:event_btnCancelarUserActionPerformed

    private void btnCadastrarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarUserActionPerformed
        txtNomeUser.setText("");
        txtCodUser.setText("NOVO");
        txtDptoUser.setText("");
        txtLoginUser.setText("");
        txtFuncUser.setText("");
        txtPesquisar.setText("");
        txtPesquisar.setEnabled(false);
        txtNomeUser.setEnabled(true);
        txtAtivoUser.setEnabled(true);
        txtDptoUser.setEnabled(true);
        txtLoginUser.setEnabled(true);
        txtFuncUser.setEnabled(true);
        txtSenhaUser.setEnabled(true);
        txtStatus.setText("CADASTRANDO");
        btnGravarUser.setEnabled(true);
        btnEditarUser.setEnabled(false);
        btnCancelarUser.setEnabled(true);
        btnCadastrarUser.setEnabled(false);
    }//GEN-LAST:event_btnCadastrarUserActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased

    }//GEN-LAST:event_formKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrarUser;
    private javax.swing.JButton btnCancelarUser;
    private javax.swing.JButton btnEditarUser;
    private javax.swing.JButton btnGravarUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tabCadUser;
    private javax.swing.JComboBox txtAtivoUser;
    private javax.swing.JLabel txtCodUser;
    private javax.swing.JTextField txtDptoUser;
    private javax.swing.JTextField txtFuncUser;
    private javax.swing.JTextField txtLoginUser;
    private javax.swing.JTextField txtNomeUser;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JPasswordField txtSenhaUser;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
