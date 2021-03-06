/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import conn.ConexaoMySQL;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class IntFrm_RegDepartment extends javax.swing.JInternalFrame {

    private String sql;
    private final ConexaoMySQL cn;

    /**
     * Creates new form IntFrm_schedule_category
     */
    public IntFrm_RegDepartment(final ConexaoMySQL conn) {
        initComponents();
        this.cn = conn;
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
        jLabel1 = new javax.swing.JLabel();
        jTxtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTxtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtPhone = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Departamentos");

        jLabel1.setText("Código");

        jTxtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtIdFocusLost(evt);
            }
        });
        jTxtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtIdKeyPressed(evt);
            }
        });

        jLabel2.setText("Descrição");

        jLabel3.setText("e-Mail");

        jLabel4.setText("Telefone");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTxtEmail)
                    .addComponent(jTxtDescricao)
                    .addComponent(jTxtPhone)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTxtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButton1.setText("Gravar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nome = jTxtDescricao.getText().toUpperCase();
        //String translated = jTxtEmail.getText().toUpperCase();
        String email = jTxtEmail.getText();
        String phone = jTxtPhone.getText();

        try {
            cn.iniciarTransacao();
            if ("".equals(jTxtDescricao.getText())) {
                jTxtDescricao.grabFocus();
                throw new UnsupportedOperationException("Informe uma descrição.");
            } else if ("".equals(jTxtEmail.getText())) {
                jTxtEmail.grabFocus();
                throw new UnsupportedOperationException("Informe um e-Mail.");
            }

            if ("".equals(jTxtId.getText())) {
                sql = "INSERT INTO fechamento.reg_departments (name, email, phone)"
                        + "VALUES ('" + nome + "','" + email + "','" + phone + "')";
            } else {
                    sql = "UPDATE fechamento.schedule_departments "
                        + "SET name = '" + nome + "', email = '" + email + "', "
                        + "phone = '" + phone + "' "
                        + "WHERE id = '" + jTxtId.getText() + "';";
            }
            cn.executeAtualizacao(sql);
            while (cn.rs.next()) {
                jTxtId.setText(cn.rs.getString(1));
            }

            tools.DefaultMsg.saveDataSuccessfull();

            tools.ClearFields.ClearFields(this);

        } catch (Exception e) {
            tools.DefaultMsg.errorMsg(e.getMessage());
        } finally {
            cn.finalizarTransacao();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTxtIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtIdFocusLost
        if (!"".equals(jTxtId.getText())) {

            incluiPesquisa();

        } else {
            tools.ClearFields.ClearFields(this);
        }
    }//GEN-LAST:event_jTxtIdFocusLost

    private void jTxtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtIdKeyPressed

        int id = 0;
        String codigo = jTxtId.getText();

        try {
            id = Integer.parseInt(jTxtId.getText());

            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                id++;
            } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                id--;
            }

            if (id < 1) {
                throw new UnsupportedOperationException();
            }

            codigo = id + "";

            jTxtId.setText(codigo);

            incluiPesquisa();

        } catch (Exception e) {
            tools.ClearFields.ClearFields(this);
            jTxtId.setText("0");
        }

    }//GEN-LAST:event_jTxtIdKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTxtDescricao;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JTextField jTxtId;
    private javax.swing.JTextField jTxtPhone;
    // End of variables declaration//GEN-END:variables

    private void incluiPesquisa() {
        jTxtDescricao.setText("");
        jTxtEmail.setText("");
        jTextField1.setText("");

        sql = "SELECT * FROM fechamento.reg_departments WHERE id = '" + jTxtId.getText() + "';";
        try {
            cn.iniciarTransacao();
            cn.executeConsulta(sql);
            while (cn.rs.next()) {
                jTxtDescricao.setText(cn.rs.getString("name"));
                jTxtEmail.setText(cn.rs.getString("email"));
                jTxtPhone.setText(cn.rs.getString("phone"));
                jTextField1.setText("Criado em " + cn.rs.getString("created_at"));
            }
        } catch (Exception e) {
            tools.DefaultMsg.errorMsg(e.getMessage());
        }

        if ("".equals(jTextField1.getText())) {
            tools.ClearFields.ClearFields(this);
        }
    }
}
