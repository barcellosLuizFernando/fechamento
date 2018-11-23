/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        setFont(new Font("Arial", Font.PLAIN, 11));

        super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);

        String result = null;

        //Se for uma tabela com conta totalizadora, irá encontrar esta linha e formatar conforme;
        try {
            result = table.getModel().getValueAt(row, 0).toString();
            //System.out.print("Resultado do ColorRenderResult: " + result + ". Coluna: " + table.getModel().getValueAt(row, 0));
            //System.out.println(". Valor: " + value);
        } catch (Exception e) {
            result = "A";
            //System.out.println("Coluna não é totalizadora.");
        }
        //se for uma linha selecionada
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
            setFont(new Font("Arial", Font.ITALIC, 11));
        } else {
            //se o tipo de conta for totalizadora
            if ("T".equals(result)) {
                setBackground(Color.DARK_GRAY);
                setForeground(Color.white);
                setFont(new Font("Arial", Font.BOLD, 11));
            } else if ("O".equals(result)) {
                setBackground(Color.BLUE);
                setForeground(Color.white);
                setFont(new Font("Arial", Font.BOLD, 11));
            } else {
                setFont(new Font("Arial", Font.PLAIN, 11));

                // se não , colore alternado as linhas
                if (row % 2 == 0) {
                    setBackground(Color.LIGHT_GRAY);
                    setForeground(Color.black);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.black);
                }
            }
        }

        try {
            Double valor = (Double.parseDouble(value.toString().replace(".", "").replace(",", ".")));
            setHorizontalAlignment(SwingConstants.RIGHT);
            //System.out.println("Converteu: " + value);
        } catch (Exception e) {
            //System.out.println("Não converteu: " + value);
            setHorizontalAlignment(SwingConstants.LEADING);
        }

        return this;
    }
}
