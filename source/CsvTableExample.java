package source;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

public class CsvTableExample extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton updateButton;

    public CsvTableExample() {
        // JFrame settings
        setTitle("CSV Data Table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create table model
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Create Update Button
        updateButton = new JButton("Change Status to Closed");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the selected row's status to "Closed"
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.setValueAt("Closed", selectedRow, 16); // Assume status is in the 3rd column (index 2)
                    updateCsvFile("/home/thineshraajan/Downloads/AdaguBill_DB.csv"); // Update the CSV file after modification
                } else {
                    JOptionPane.showMessageDialog(CsvTableExample.this, "Please select a row to change the status.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(updateButton, BorderLayout.SOUTH);

        add(panel);
        loadCsvFile("/home/thineshraajan/Downloads/AdaguBill_DB.csv"); // Load the CSV file

        setVisible(true);
    }

    // Method to load the CSV file into the table
    public void loadCsvFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Vector<String> columnNames = new Vector<>();
            Vector<Vector<Object>> rowData = new Vector<>();

            // Read the header line (first line in CSV)
            if ((line = reader.readLine()) != null) {
                String[] headers = line.split(",");
                for (String header : headers) {
                    columnNames.add(header.trim());
                }
            }

            // Read the data rows
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Vector<Object> row = new Vector<>();
                for (String value : data) {
                    row.add(value.trim());
                }
                rowData.add(row);
            }

            // Set the data to the table model
            tableModel.setDataVector(rowData, columnNames);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading the CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to update the CSV file with the modified data
    public void updateCsvFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) writer.write(",");
            }
            writer.newLine();

            // Write the data rows
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) writer.write(",");
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating the CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CsvTableExample();
    }
}
