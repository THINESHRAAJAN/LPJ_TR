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
        return comp;
    }
}
