package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ExportAllTablesToExcelApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExportAllTablesToExcelApp().createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Export All Tables to CSV");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JButton exportButton = new JButton("Export All Tables to CSV");
        exportButton.addActionListener(e -> exportAllTablesToCSV());

        JPanel panel = new JPanel();
        panel.add(exportButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void exportAllTablesToCSV() {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/LPJ";
        String username = "root";
        String password = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            // Get all table names
            rs = stmt.executeQuery("SHOW TABLES");

            while (rs.next()) {
                String tableName = rs.getString(1);
                System.out.println("Exporting table: " + tableName);

                Statement tableStmt = conn.createStatement();
                ResultSet tableRs = tableStmt.executeQuery("SELECT * FROM " + tableName);
                ResultSetMetaData metaData = tableRs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Define CSV file path
                String csvFilePath = "/home/thineshraajan/Downloads/" + tableName + ".csv";
                FileWriter csvWriter = new FileWriter(csvFilePath);

                // Write header
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(metaData.getColumnLabel(i));
                    if (i < columnCount) csvWriter.append(",");
                }
                csvWriter.append("\n");

                // Write data rows
                while (tableRs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String value = tableRs.getString(i);
                        csvWriter.append(value != null ? value.replaceAll("\"", "\"\"") : "");
                        if (i < columnCount) csvWriter.append(",");
                    }
                    csvWriter.append("\n");
                }

                // Clean up
                csvWriter.flush();
                csvWriter.close();
                tableRs.close();
                tableStmt.close();
            }

            JOptionPane.showMessageDialog(null, "All tables exported successfully as CSV files!");

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
