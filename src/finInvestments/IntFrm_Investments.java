/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finInvestments;

import tools.ColorRender;
import tools.FinIntegraContasReceber;
import tools.ManipulaData;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferna
 */
public class IntFrm_Investments extends javax.swing.JInternalFrame {

    ManipulaData mData = new ManipulaData();

    FinInvestContracts invest;
    private String dateToFind;

    /**
     * Creates new form IntFrm_Investments
     */
    public IntFrm_Investments() {
        initComponents();
        clearFields(true);
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
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTxtOutstandingBalanceAcc = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtOutstandingBalanceFin = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtCompetence = new javax.swing.JFormattedTextField(tools.MascaraTextField.competenceBR());
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTxtInvestiment = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtWithholdingTax = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtWithdrawal = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtInterest = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtFinancialOperationsTax = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtOutstandingBalanceParc = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTxtWithholdingNew = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtOutstandingBalanceNew = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtInterestNew = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jTxtFinancialOperationsTaxNew = new javax.swing.JFormattedTextField(tools.MascaraTextField.numeroBR());
        jLabel14 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Juros de Aplicações");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parâmetros"));

        jLabel1.setText("Competência");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Saldo Financeiro");

        jLabel9.setText("Saldo Balancete");

        jTxtOutstandingBalanceAcc.setEnabled(false);

        jTxtOutstandingBalanceFin.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtCompetence, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtOutstandingBalanceFin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtOutstandingBalanceAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jTxtOutstandingBalanceAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtOutstandingBalanceFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtCompetence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Instituição"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Contratos"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Saldo Financeiro"));

        jLabel2.setText("Aplicação");

        jLabel3.setText("Rendimento");

        jLabel4.setText("IRRF");

        jLabel5.setText("IOF");

        jLabel6.setText("Resgate");

        jLabel7.setText("Saldo");

        jTxtInvestiment.setEnabled(false);

        jTxtWithholdingTax.setEnabled(false);

        jTxtWithdrawal.setEnabled(false);

        jTxtInterest.setEnabled(false);

        jTxtFinancialOperationsTax.setEnabled(false);

        jTxtOutstandingBalanceParc.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTxtWithholdingTax, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTxtWithdrawal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtInvestiment, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTxtFinancialOperationsTax, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jTxtInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTxtOutstandingBalanceParc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jTxtInvestiment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtInterest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTxtWithholdingTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFinancialOperationsTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jTxtWithdrawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtOutstandingBalanceParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Saldo Financeiro"));

        jLabel11.setText("Rendimento");

        jLabel12.setText("IRRF");

        jLabel13.setText("IOF");

        jLabel10.setText("Saldo");

        jButton2.setText("Gravar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTxtWithholdingNew.setNextFocusableComponent(jTxtFinancialOperationsTaxNew);
        jTxtWithholdingNew.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtWithholdingNewFocusLost(evt);
            }
        });
        jTxtWithholdingNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtWithholdingNewKeyPressed(evt);
            }
        });

        jTxtOutstandingBalanceNew.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtOutstandingBalanceNewFocusLost(evt);
            }
        });

        jTxtInterestNew.setNextFocusableComponent(jTxtWithholdingNew);
        jTxtInterestNew.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtInterestNewFocusLost(evt);
            }
        });
        jTxtInterestNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtInterestNewKeyPressed(evt);
            }
        });

        jTxtFinancialOperationsTaxNew.setNextFocusableComponent(jButton2);
        jTxtFinancialOperationsTaxNew.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtFinancialOperationsTaxNewFocusLost(evt);
            }
        });
        jTxtFinancialOperationsTaxNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFinancialOperationsTaxNewKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTxtFinancialOperationsTaxNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtInterestNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTxtOutstandingBalanceNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTxtWithholdingNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jTxtWithholdingNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtInterestNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10)
                    .addComponent(jTxtOutstandingBalanceNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFinancialOperationsTaxNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 4, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearFields(true);

        findData();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        saveData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        findContracts();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        findContractOutstandingBalance();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTxtInterestNewFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtInterestNewFocusLost
        sumNewValues();
    }//GEN-LAST:event_jTxtInterestNewFocusLost

    private void jTxtWithholdingNewFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtWithholdingNewFocusLost
        sumNewValues();
    }//GEN-LAST:event_jTxtWithholdingNewFocusLost

    private void jTxtFinancialOperationsTaxNewFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFinancialOperationsTaxNewFocusLost
        sumNewValues();
    }//GEN-LAST:event_jTxtFinancialOperationsTaxNewFocusLost

    private void jTxtInterestNewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtInterestNewKeyPressed
        tools.AcaoPadrao.acaoPadraoTeclado(evt, jTxtInterestNew, this);
    }//GEN-LAST:event_jTxtInterestNewKeyPressed

    private void jTxtWithholdingNewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtWithholdingNewKeyPressed
        tools.AcaoPadrao.acaoPadraoTeclado(evt, jTxtWithholdingNew, this);
    }//GEN-LAST:event_jTxtWithholdingNewKeyPressed

    private void jTxtFinancialOperationsTaxNewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFinancialOperationsTaxNewKeyPressed
        tools.AcaoPadrao.acaoPadraoTeclado(evt, jTxtFinancialOperationsTaxNew, this);
    }//GEN-LAST:event_jTxtFinancialOperationsTaxNewKeyPressed

    private void jTxtOutstandingBalanceNewFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtOutstandingBalanceNewFocusLost
        String saldo = jTxtOutstandingBalanceNew.getText();

        Double backBalance = Double.parseDouble(jTxtOutstandingBalanceParc.getText().replace(".", "").replace(",", "."));
        Double lastBalance = Double.parseDouble(jTxtOutstandingBalanceNew.getText().replace(".", "").replace(",", "."));

        try {

            if (lastBalance < backBalance) {
                throw new UnsupportedOperationException("Valor final do investimento não pode ser inferior ao valor inicial.");
            }

            jTxtInterestNew.setText(tools.FormatNumbers.numUsToBr(lastBalance - backBalance));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Cálculo de Juros", JOptionPane.ERROR_MESSAGE);
            jTxtInterestNew.setText("0,00");
            if (!"0,00".equals(saldo)) {
                jTxtOutstandingBalanceNew.setText(saldo);
            } else {
                jTxtOutstandingBalanceNew.setText(jTxtOutstandingBalanceParc.getText());
            }
        }

    }//GEN-LAST:event_jTxtOutstandingBalanceNewFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JFormattedTextField jTxtCompetence;
    private javax.swing.JFormattedTextField jTxtFinancialOperationsTax;
    private javax.swing.JFormattedTextField jTxtFinancialOperationsTaxNew;
    private javax.swing.JFormattedTextField jTxtInterest;
    private javax.swing.JFormattedTextField jTxtInterestNew;
    private javax.swing.JFormattedTextField jTxtInvestiment;
    private javax.swing.JFormattedTextField jTxtOutstandingBalanceAcc;
    private javax.swing.JFormattedTextField jTxtOutstandingBalanceFin;
    private javax.swing.JFormattedTextField jTxtOutstandingBalanceNew;
    private javax.swing.JFormattedTextField jTxtOutstandingBalanceParc;
    private javax.swing.JFormattedTextField jTxtWithdrawal;
    private javax.swing.JFormattedTextField jTxtWithholdingNew;
    private javax.swing.JFormattedTextField jTxtWithholdingTax;
    // End of variables declaration//GEN-END:variables

    private void clearFields(boolean all) {

        if (all) {
            jTxtOutstandingBalanceFin.setValue(0.00);
            jTxtOutstandingBalanceAcc.setValue(0.00);

            createStructureTblBank();
            createStructureTblContract();
        }

        jTxtInvestiment.setValue(0.00);
        jTxtInterest.setValue(0.00);
        jTxtWithholdingTax.setValue(0.00);
        jTxtFinancialOperationsTax.setValue(0.00);
        jTxtWithdrawal.setValue(0.00);
        jTxtOutstandingBalanceParc.setValue(0.00);

        jTxtInterestNew.setValue(0.00);
        jTxtWithholdingNew.setValue(0.00);
        jTxtFinancialOperationsTaxNew.setValue(0.00);
        jTxtOutstandingBalanceNew.setValue(0.00);

        jLabel14.setText("");
    }

    private void saveData() {

        try {

            if (jTable2.getSelectedRow() < 0) {
                throw new Exception("Você precisa selecionar um contrato para continuar.");
            }

            if ("0.0".equals(jTxtInterestNew.getValue().toString())) {
                throw new Exception("Você precisa informar um valor de rendimento para continuar.");
            }

            Date dateAdd;

            if (mData.lastDay(jTxtCompetence.getText()).after(invest.getDtVenc())) {
                dateAdd = mData.lastDay(jTxtCompetence.getText());
            } else {
                dateAdd = invest.getDtVenc();
            }

            tools.FinIntegraContasReceber fin = new FinIntegraContasReceber();
            fin.setQueryFirst(Short.parseShort("1"), invest.getDoc(), invest.getHistorico(),
                    mData.lastDay(jTxtCompetence.getText()), dateAdd,
                    1, "S".charAt(0), invest.getContaCliente(),
                    Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()),
                    invest.getEstabelec(), 167, invest.getTipoDoc());

            fin.setQuerySecond(6859, "C",
                    "Juros sobre aplicação " + invest.getDoc() + " "
                    + jTable1.getValueAt(jTable1.getSelectedRow(), 1),
                    Double.parseDouble(jTxtInterestNew.getValue().toString()));

            if (!"0.0".equals(jTxtWithholdingNew.getValue().toString())) {
                fin.setQuerySecond(74, "D",
                        "IRRF sobre aplicação " + invest.getDoc() + " "
                        + jTable1.getValueAt(jTable1.getSelectedRow(), 1),
                        Double.parseDouble(jTxtWithholdingNew.getValue().toString()));
            }

            if (!"0.0".equals(jTxtFinancialOperationsTaxNew.getValue().toString())) {
                fin.setQuerySecond(6258, "D",
                        "I.O.F. sobre aplicação " + invest.getDoc() + " "
                        + jTable1.getValueAt(jTable1.getSelectedRow(), 1),
                        Double.parseDouble(jTxtFinancialOperationsTaxNew.getValue().toString()));
            }

            fin.syncAccountsPayable();

            clearFields(false);

            jTxtOutstandingBalanceAcc.setValue(invest.getSumBalanceSheet(dateToFind));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Gravando dados", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void findData() {

        try {
            DefaultTableModel lista;

            dateToFind = tools.FormatNumbers.dateBrDefault(
                    mData.lastDay(jTxtCompetence.getText()
                    )
            );

            invest = new FinInvestContracts(dateToFind);

            jTxtOutstandingBalanceFin.setText(tools.FormatNumbers.numUsToBr(
                    invest.getOutstanding_balance_total()
            )
            );

            lista = createStructureTblBank();

            for (int i = 0; i < invest.getIdBanks().size(); i++) {

                lista.addRow(new String[]{
                    invest.getIdBanks(i),
                    invest.getNameBank(i)
                });

            }

            jTxtOutstandingBalanceAcc.setValue(invest.getSumBalanceSheet(dateToFind));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private DefaultTableModel createStructureTblBank() {

        DefaultTableModel lista = (DefaultTableModel) jTable1.getModel();

        lista.setRowCount(0);
        lista.setColumnCount(0);

        lista.addColumn("IdBank");
        lista.addColumn("Descrição");

        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.setDefaultRenderer(Object.class, new ColorRender());

        return lista;
    }

    private DefaultTableModel createStructureTblContract() {

        DefaultTableModel lista = (DefaultTableModel) jTable2.getModel();

        lista.setRowCount(0);
        lista.setColumnCount(0);

        lista.addColumn("Número");

        jTable2.setDefaultRenderer(Object.class, new ColorRender());

        return lista;
    }

    private void findContracts() {
        try {

            clearFields(false);

            DefaultTableModel lista = createStructureTblContract();

            int linha = jTable1.getSelectedRow();

            for (int i = 0; i < invest.getNumberContracts(dateToFind, jTable1.getValueAt(linha, 0).toString()).size(); i++) {
                lista.addRow(new String[]{
                    invest.getNumberContracts(i)
                });
            }

            jLabel14.setText(tools.FormatNumbers.numUsToBr(invest.residualValue(jTable1.getValueAt(linha, 0).toString(), tools.FormatNumbers.dateBrDefault(mData.lastDay(jTxtCompetence.getText())))));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }

    private void findContractOutstandingBalance() {
        try {
            int linha = jTable2.getSelectedRow();

            invest.residualValue(jTable2.getValueAt(linha, 0).toString(),
                    jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(), dateToFind);

            jTxtInvestiment.setValue(invest.getInvestment());
            jTxtInterest.setValue(invest.getInterest());
            jTxtWithholdingTax.setValue(invest.getWithholding_tax());
            jTxtFinancialOperationsTax.setValue(invest.getFinancial_operations_tax());
            jTxtWithdrawal.setValue(invest.getWithdrawal());
            jTxtOutstandingBalanceParc.setValue(invest.getOutstanding_balance());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }

    private void sumNewValues() {
        try {
            Double outstandingBalanceOld = Double.parseDouble(jTxtOutstandingBalanceParc.getText().replace(".", "").replace(",", "."));
            Double interestNew = Double.parseDouble(jTxtInterestNew.getText().replace(".", "").replace(",", "."));
            Double withholdingTaxNew = Double.parseDouble(jTxtWithholdingNew.getText().replace(".", "").replace(",", "."));
            Double financialOperationsTaxNew = Double.parseDouble(jTxtFinancialOperationsTaxNew.getText().replace(".", "").replace(",", "."));

            Double outstandingBalanceNew = outstandingBalanceOld + interestNew - withholdingTaxNew - financialOperationsTaxNew;

            jTxtOutstandingBalanceNew.setValue(outstandingBalanceNew);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
