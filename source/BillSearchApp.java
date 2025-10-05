package source;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BillSearchApp extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField txtBillNo, txtMobileNo;
    private JButton btnSearch, btnEdit, btnDelete, btnCloseBill, btnExportPDF, btnGenerateBill;
    private JComboBox<String> cmbStatus, cmbBillType;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<BillRecord> records;

    // Sample data (replace with database access logic)
    public BillSearchApp() {
        records = new ArrayList<>();
       

        setTitle("Bill Search Application");
        setLayout(new BorderLayout());

        // Header Panel (for search inputs)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        headerPanel.setBackground(Color.CYAN);

        JLabel lblBillNo = new JLabel("Bill No:");
        txtBillNo = new JTextField(10);
        JLabel lblMobileNo = new JLabel("Mobile No:");
        txtMobileNo = new JTextField(10);
        
        JLabel lblStatus = new JLabel("Status:");
        cmbStatus = new JComboBox<>(new String[]{"", "Paid", "Closed", "Pending"});
        
        JLabel lblBillType = new JLabel("Bill Type:");
        cmbBillType = new JComboBox<>(new String[]{"", "Sales Bill", "Adagu Bill"});

        btnSearch = new JButton("Search");

        headerPanel.add(lblBillNo);
        headerPanel.add(txtBillNo);
        headerPanel.add(lblMobileNo);
        headerPanel.add(txtMobileNo);
        headerPanel.add(lblStatus);
        headerPanel.add(cmbStatus);
        headerPanel.add(lblBillType);
        headerPanel.add(cmbBillType);
        headerPanel.add(btnSearch);

        add(headerPanel, BorderLayout.NORTH);

        // Table for displaying results
        tableModel = new DefaultTableModel(new String[] {"Bill No", "Name", "Mobile No", "Amount", "Status", "Bill Type"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        add(tableScroll, BorderLayout.CENTER);

        // Footer Panel (for Edit/Delete buttons)
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        footerPanel.setBackground(Color.LIGHT_GRAY);

        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnCloseBill = new JButton("Close Bill");
        btnExportPDF = new JButton("Export PDF");
        btnGenerateBill = new JButton("Generate Bill");

        footerPanel.add(btnEdit);
        footerPanel.add(btnDelete);
        footerPanel.add(btnCloseBill);
        footerPanel.add(btnGenerateBill);
        footerPanel.add(btnExportPDF);

        add(footerPanel, BorderLayout.SOUTH);

        // Action Listeners
        btnSearch.addActionListener(e -> searchRecords());
        btnEdit.addActionListener(e -> editRecord());
        btnDelete.addActionListener(e -> deleteRecord());
        btnCloseBill.addActionListener(e -> closeBill());
        btnExportPDF.addActionListener(e -> exportToPDF());
        btnGenerateBill.addActionListener(e -> generateBill());

        // Set window properties
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Sample method to search records (replace with real DB query)
    private void searchRecords() {
    	String billNo = txtBillNo.getText().trim();
        String mobileNo = txtMobileNo.getText().trim();
        String status = cmbStatus.getSelectedItem().toString();
        String billType = cmbBillType.getSelectedItem().toString();

        // Clear existing data in the table
        tableModel.setRowCount(0);

        // Logic to filter records based on billNo, mobileNo, status, and billType
        for (BillRecord record : records) {
            // Filter based on Bill No (if not empty)
            boolean matchesBillNo = billNo.isEmpty() || String.valueOf(record.getBillNo()).equals(billNo);

            // Filter based on Mobile No (if not empty)
            boolean matchesMobileNo = mobileNo.isEmpty() || record.getMobileNo().equals(mobileNo);

            // Filter based on Status (if not "All")
            boolean matchesStatus = status.equals("") || record.getStatus().equals(status);

            // Filter based on Bill Type (if not "All")
            boolean matchesBillType = billType.equals("") || record.getBillType().equals(billType);

            // Only add record if all conditions are met
            if (matchesBillNo && matchesMobileNo && matchesStatus && matchesBillType) {
                tableModel.addRow(new Object[] { record.getBillNo(), record.getName(), record.getMobileNo(), record.getAmount(), record.getStatus(), record.getBillType() });
            }
        }
    }

    // Method to handle edit (update the record, placeholder here)
    private void editRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int billNo = (int) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            String mobileNo = (String) tableModel.getValueAt(selectedRow, 2);
            double amount = (double) tableModel.getValueAt(selectedRow, 3);
            String status = (String) tableModel.getValueAt(selectedRow, 4);
            String billType = (String) tableModel.getValueAt(selectedRow, 5);

            // In a real scenario, you would open a dialog to update the record
            JOptionPane.showMessageDialog(this, "Edit Record: Bill No: " + billNo);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record to edit.");
        }
    }

    // Method to handle deletion (remove the record, placeholder here)
    private void deleteRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int billNo = (int) tableModel.getValueAt(selectedRow, 0);
            records.removeIf(record -> record.getBillNo() == billNo);
            tableModel.removeRow(selectedRow);  // Remove from table as well
            JOptionPane.showMessageDialog(this, "Record Deleted: Bill No: " + billNo);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record to delete.");
        }
    }

    // Method to handle closing a bill (change status to "Closed")
    private void closeBill() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int billNo = (int) tableModel.getValueAt(selectedRow, 0);
            BillRecord selectedRecord = null;

            // Find the selected record from the list
            for (BillRecord record : records) {
                if (record.getBillNo() == billNo) {
                    selectedRecord = record;
                    break;
                }
            }

            if (selectedRecord != null && !selectedRecord.getStatus().equals("Closed")) {
                selectedRecord.setStatus("Closed");
                tableModel.setValueAt("Closed", selectedRow, 4);  // Update the table view
                JOptionPane.showMessageDialog(this, "Bill No: " + billNo + " is now Closed.");
            } else {
                JOptionPane.showMessageDialog(this, "This bill is already closed or not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record to close.");
        }
    }
    
    // Export table contents to PDF
    private void exportToPDF() {
        Document document = new Document();
        try {
            // Output stream to save the PDF
            PdfWriter.getInstance(document, new FileOutputStream("/home/dev089/Downloads/BillRecords.pdf"));
            document.open();

            // Create a table with the same number of columns as the JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Add table headers
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }

            // Add data rows
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    pdfTable.addCell(model.getValueAt(i, j).toString());
                }
            }

            // Add the table to the document
            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(this, "Exported to PDF successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting to PDF: " + e.getMessage());
        }
    }
    
 // Export table contents to PDF
    private void generateBill() {
        Document document = new Document();
        try {
            // Output stream to save the PDF
            PdfWriter.getInstance(document, new FileOutputStream("/home/dev089/Downloads/BillRecords.pdf"));
            document.open();

            // Create a table with the same number of columns as the JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Add table headers
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }

            // Add data rows
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    pdfTable.addCell(model.getValueAt(i, j).toString());
                }
            }

            // Add the table to the document
            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(this, "Exported to PDF successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting to PDF: " + e.getMessage());
        }
    }


    // BillRecord class (replace with actual data model and database interaction)
    static class BillRecord {
        private int billNo;
        private String name;
        private String mobileNo;
        private double amount;
        private String status;
        private String billType;
        private String products;
        private Date date;

        public BillRecord(int billNo, String name, String mobileNo, double amount, String products, String billType, String status, Date date) {
            this.billNo = billNo;
            this.name = name;
            this.mobileNo = mobileNo;
            this.amount = amount;
            this.products = products;
            this.billType = billType;
            this.status = status;
            this.date = date;
        }

        public int getBillNo() {
            return billNo;
        }

        public String getName() {
            return name;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public double getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }

        public String getBillType() {
            return billType;
        }

        public void setStatus(String status) {
            this.status = status;
        }

		public String getProducts() {
			return products;
		}

		public void setProducts(String products) {
			this.products = products;
		}
		
		public Date getDate() {
			return date;
		}

		public void setProducts(Date date) {
			this.date = date;
		}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BillSearchApp app = new BillSearchApp();
            app.setVisible(true);
        });
    }
}
