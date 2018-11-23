/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sped_ecd_ecf;

import conn.ConexaoFB;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferna
 */
public class IntFrm_PlanoReferencial extends javax.swing.JInternalFrame {

    private String sql;
    private conn.ConexaoFB cnfb = new ConexaoFB();
    private ArrayList queryList = new ArrayList();
    private DefaultTableModel lista;
    private String classRel;

    /**
     * Creates new form IntFrm_PlanoReferencial
     */
    public IntFrm_PlanoReferencial() {
        initComponents();
        incluiPesquisa();
        incluiPlanoContasReferencial();
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
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setTitle("Manutenção Plano Contas Referencial");

        jCheckBox1.setText("Listar apenas contas não relacionadas");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        jButton1.setText("Gravar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEnabled(false);

        jLabel1.setText("Contas Analíticas");

        jButton2.setText("Relacionamento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCheckBox1)
                .addComponent(jButton1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Plano de Contas"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "*Tipo", "Código", "Classificação", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(1).setMinWidth(60);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(2).setMinWidth(120);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Plano Referencial"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "*Tipo", "Código", "Descrição", "Vinculada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(1).setMinWidth(60);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(60);
            jTable2.getColumnModel().getColumn(3).setMinWidth(60);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gravaDados();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        incluiPesquisa();
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        pesquisaRelacionamento();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        validaSelecaoTabelas();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        validaSelecaoTabelas();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void incluiPesquisa() {
        sql = "select pcr.empresa, pcr.planoconta, pcr.grupo, "
                + "'delete from rel_planocontas_referencial "
                + "where empresa = '||pcr.empresa||' and "
                + "planoconta = '||pcr.planoconta||' and "
                + "grupo = '||grupo||';' as query "
                + "from cad_planocontas pc "
                + "inner join rel_planocontas_referencial pcr "
                + "            on pcr.empresa = pc.empresa and "
                + "            pcr.planoconta = pc.codigo "
                + "where pc.empresa = 1 and pc.tipo = 'T';";

        try {
            queryList.clear();
            cnfb.conecta();

            //VERIFICA SE EXISTEM CONTAS TOTALIZADORAS RELACIONADAS.
            cnfb.executeConsulta(sql);
            while (cnfb.rs.next()) {
                queryList.add(cnfb.rs.getString("query"));
            }

            if (queryList.size() > 0) {

                int r = JOptionPane.showConfirmDialog(this, "Existem contas totalizadoras relacionadas ao Plano Referencial.\nDeseja desfazer o relacionamento?",
                        "Correção de Cadastro", JOptionPane.YES_NO_OPTION);

                if (r == 0) {

                    for (int i = 0; i < queryList.size(); i++) {
                        cnfb.executeAtualizacao(queryList.get(i).toString());
                    }
                    tools.DefaultMsg.saveDataSuccessfull();
                }

            }

            //BUSCA O PLANO DE CONTAS
            lista = (DefaultTableModel) jTable1.getModel();
            lista.setRowCount(0);
            jTable1.setDefaultRenderer(Object.class, new tools.ColorRender());

            sql = "select * from ( "
                    + "select pc.tipo, pc.codigo, pc.classificacao, pc.nome, "
                    + "pc.empresa "
                    + "from cad_planocontas pc "
                    + "where pc.empresa = 1 and pc.tipo = 'A' ";

            if (jCheckBox1.isSelected()) {
                sql += "and not exists (select * from rel_planocontas_referencial pcr "
                        + "where pcr.empresa = pc.empresa and pcr.planoconta = pc.codigo) ";
            }

            sql += "union all "
                    + "select pc.tipo, pc.codigo, pc.classificacao, pc.nome, "
                    + "pc.empresa from cad_planocontas pc "
                    + "where pc.empresa = 1 and pc.tipo = 'T' ";

            if (jCheckBox1.isSelected()) {
                sql += "and exists (select * from cad_planocontas pc1  "
                        + "/*left join rel_planocontas_referencial pcr on "
                        + "     pc1.codigo = pcr.planoconta and "
                        + "     pc1.empresa = pcr.empresa*/ "
                        + "where pc1.tipo = 'A' and pc1.empresa = pc.empresa and /*pcr.planoconta is null and*/ "
                        + "     pc1.classificacao starting with pc.classificacao and "
                        + "     not exists (select * from rel_planocontas_referencial pcr "
                        + "        where pcr.empresa = pc1.empresa and pcr.planoconta = pc1.codigo)) ";
            }

            sql += ") as base "
                    + "where base.classificacao not starting with '1.5' and base.classificacao not starting with '2.5' "
                    + "order by base.classificacao, base.nome;";

            cnfb.executeConsulta(sql);

            while (cnfb.rs.next()) {
                lista.addRow(new Object[]{
                    cnfb.rs.getString("tipo"),
                    cnfb.rs.getString("codigo"),
                    cnfb.rs.getString("classificacao"),
                    cnfb.rs.getString("nome")
                });
            }

        } catch (Exception e) {
            tools.DefaultMsg.errorMsg(e.getMessage());
        } finally {
            cnfb.desconecta();
        }

        int contas = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.getValueAt(i, 0).toString().equals("A")) {
                contas++;
            }
        }

        jTextField1.setText(contas + "");

    }

    private void pesquisaRelacionamento() {
        if (jTable1.getSelectedRowCount() == 1) {

            String codigo = "0";

            try {
                cnfb.conecta();
                sql = "select pcr.class_referencial as classificacao "
                        + "from rel_planocontas_referencial pcr "
                        + "where pcr.empresa = 1 and pcr.planoconta = '" + jTable1.getValueAt(jTable1.getSelectedRow(), 1) + "'; ";
                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    codigo = cnfb.rs.getString("classificacao");
                }
            } catch (Exception e) {
                tools.DefaultMsg.errorMsg(e.getMessage());
            } finally {
                cnfb.desconecta();
            }

            for (int i = 0; i < jTable2.getRowCount(); i++) {
                if (jTable2.getValueAt(i, 1).toString().equals(codigo)) {
                    jTable2.setValueAt(new Boolean(true), i, 3);
                } else {
                    jTable2.setValueAt(new Boolean(false), i, 3);
                }
            }

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void incluiPlanoContasReferencial() {
        lista = (DefaultTableModel) jTable2.getModel();
        jTable2.setDefaultRenderer(Object.class, new tools.ColorRender());
        //jTable2.setDefaultRenderer(Boolean.class, new tools.ColorRender());

        lista.setRowCount(0);

        sql = "select "
                + "case when pcr.tipo = 'S' then 'T' else pcr.tipo end as tipo, "
                + "pcr.classificacao, "
                + "pcr.nome "
                + "from cad_planocontas_referencial pcr "
                + "where pcr.empresa = 1 "
                + "order by pcr.classificacao;";
        try {
            cnfb.conecta();
            cnfb.executeConsulta(sql);
            while (cnfb.rs.next()) {
                lista.addRow(new Object[]{
                    cnfb.rs.getString("tipo"),
                    cnfb.rs.getString("classificacao"),
                    cnfb.rs.getString("nome"),
                    new Boolean(false)
                });
            }

        } catch (Exception e) {
            tools.DefaultMsg.errorMsg(e.getMessage());
        } finally {
            cnfb.desconecta();
        }

    }

    private void validaSelecaoTabelas() {
        boolean rel = false;
        //System.out.println("Validando Tabelas");

        if (jTable1.getSelectedRowCount() > 0 && jCheckBox1.isSelected()) {
            //System.out.println("Validando Plano de Contas");

            try {
                for (int i = 0; i < jTable2.getRowCount(); i++) {
                    //System.out.println("Validando Plano Referencial: " + jTable2.getValueAt(i, 3).toString() == "true");
                    if (jTable2.getValueAt(i, 3).toString() == "true") {

                        if (jTable2.getValueAt(i, 0).toString().equals("T")) {
                            throw new UnsupportedOperationException("Não é possível vincular uma conta Totalizadora do Plano Referencial.");
                        }

                        if (rel) {
                            throw new UnsupportedOperationException("Não é possível vincular duas contas referenciais ao mesmo tempo.");
                        }

                        rel = true;
                        classRel = jTable2.getValueAt(i, 1).toString();

                    }
                }
            } catch (Exception e) {
                rel = false;
                tools.DefaultMsg.errorMsg(e.getMessage());
            }
        }

        if (rel) {
            jButton1.setEnabled(true);
        } else {
            jButton1.setEnabled(false);
        }

    }

    private void gravaDados() {

        ArrayList queryInsert = new ArrayList();

        try {

            for (int i = 0; i < jTable1.getRowCount(); i++) {

                if (jTable1.isRowSelected(i)) {
                    if (jTable1.getValueAt(i, 0).toString().equals("T")) {
                        throw new UnsupportedOperationException("Não é permitido vincular contas analíticas ao Plano Referencial.");
                    }
                    sql = "INSERT INTO rel_planocontas_referencial (empresa,planoconta,"
                            + "grupo,class_referencial) VALUES (1,"
                            + "'" + jTable1.getValueAt(i, 1).toString() + "',"
                            + "1,'" + classRel + "');";
                    queryInsert.add(sql);
                }
            }

            if (queryInsert.size() > 0) {
                try {
                    cnfb.conecta();
                    for (int i = 0; i < queryInsert.size(); i++) {
                        cnfb.executeAtualizacao(queryInsert.get(i).toString());
                    }
                } catch (Exception e) {
                    throw new UnsupportedOperationException(e);
                } finally {
                    cnfb.desconecta();
                }
            }

            incluiPesquisa();
            validaSelecaoTabelas();
        } catch (Exception e) {
            tools.DefaultMsg.errorMsg(e.getMessage());
        }
    }

}