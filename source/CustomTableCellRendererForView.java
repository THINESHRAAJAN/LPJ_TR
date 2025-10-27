package source;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

class CustomTableCellRendererForView extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Call parent method to get the default renderer
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Set the custom font and size
        comp.setFont(new Font("Serif", Font.BOLD, 16));  // Example: "Serif" font, plain style, size 18
        
        //24Oct2025
        // Detect if this is the "cancel" row (first if condition row)
        boolean isCancelRow = false;
        Object col0 = table.getValueAt(row, 0);
        Object col1 = table.getValueAt(row, 1);
        Object col2 = table.getValueAt(row, 2);
        //Object col3 = table.getValueAt(row, 3); //27Oct2025

        // Identify by pattern ("--" in first few columns)
        if ("--".equals(col0) && "--".equals(col1) && "--".equals(col2)) {
            isCancelRow = true;
        }

        if (isCancelRow) {
        	comp.setBackground(Color.WHITE);
        	comp.setForeground(Color.RED);
        	comp.setFont(new Font("Serif", Font.BOLD, 16));
        } else {
        	comp.setBackground(Color.WHITE);
        	comp.setForeground(Color.BLACK);
        	comp.setFont(new Font("Serif", Font.BOLD, 16));
        }
        
        if (value != null && value.toString().equalsIgnoreCase("Paid")) {
        	comp.setForeground(Color.RED.darker());
        }
        else if(value != null && value.toString().equalsIgnoreCase("Pending"))
        {
        	comp.setForeground(Color.GREEN.darker());
        }
        //24Oct2025
        
        return comp;
    }
}
