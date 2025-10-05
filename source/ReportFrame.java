package source;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

public class ReportFrame extends JFrame {

    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JTable resultTable;
    private JButton searchButton, exportButton;

    public ReportFrame() {
        // Frame setup
        setTitle("Report Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for date selection and buttons with a colorful background
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(new Color(255, 223, 186)); // Light peach color

        JLabel startDateLabel = new JLabel("Start Date: ");
        startDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        startDateLabel.setForeground(new Color(55, 55, 55)); // Dark gray text

        startDateChooser = new JDateChooser();
        startDateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
        startDateChooser.setDate(new Date()); // Set the default to the current date
        startDateChooser.setBackground(Color.WHITE);

        JLabel endDateLabel = new JLabel("End Date: ");
        endDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        endDateLabel.setForeground(new Color(55, 55, 55)); // Dark gray text

        endDateChooser = new JDateChooser();
        endDateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
        endDateChooser.setDate(new Date()); // Set the default to the current date
        endDateChooser.setBackground(Color.WHITE);

        // Search Button with colorful style
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(66, 133, 244)); // Blue color
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setPreferredSize(new Dimension(120, 40));

        // Export Button with colorful style
        exportButton = new JButton("Export to PDF");
        exportButton.setFont(new Font("Arial", Font.BOLD, 16));
        exportButton.setForeground(Color.WHITE);
        exportButton.setBackground(new Color(34, 193, 195)); // Aqua color
        exportButton.setFocusPainted(false);
        exportButton.setBorderPainted(false);
        exportButton.setPreferredSize(new Dimension(160, 40));

        // Adding components to the inputPanel
        inputPanel.add(startDateLabel);
        inputPanel.add(startDateChooser);
        inputPanel.add(endDateLabel);
        inputPanel.add(endDateChooser);
        inputPanel.add(searchButton);
        inputPanel.add(exportButton);

        // Table for displaying search results
        resultTable = new JTable();
        resultTable.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTable.setBackground(new Color(245, 245, 245)); // Light gray background for the table
        resultTable.setRowHeight(30);
        resultTable.setGridColor(new Color(200, 200, 200));
        resultTable.setSelectionBackground(new Color(255, 255, 204)); // Light yellow selection

        JScrollPane tableScroll = new JScrollPane(resultTable);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);

        // Action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        // Action listener for the export button
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToPDF();
            }
        });
    }

    private void performSearch() {
        Date startDate = startDateChooser.getDate();
        Date endDate = endDateChooser.getDate();

        if (startDate != null && endDate != null) {
            // Format the dates to strings
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDateStr = sdf.format(startDate);
            String endDateStr = sdf.format(endDate);

            List<String[]> data = loadData(startDateStr, endDateStr);

            // Create the table model with the data
            String[] columnNames = {"ID", "Name", "Date", "Amount"};
            resultTable.setModel(new javax.swing.table.DefaultTableModel(
                    data.toArray(new Object[0][]), columnNames));
        } else {
            JOptionPane.showMessageDialog(this, "Please select both dates.");
        }
    }

    private List<String[]> loadData(String startDate, String endDate) {
        // Simulate data loading based on the date range
        List<String[]> data = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Simulate a few rows of data
            for (int i = 1; i <= 5; i++) {
                String[] row = {String.valueOf(i), "Item " + i, sdf.format(new Date()), "$" + (i * 10)};
                data.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private void exportToPDF() {
        // Generate a simple PDF
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("/home/dev089/Downloads/report.pdf"));
            document.open();

            //document.add(new Paragraph("Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));

            // Create a table in the PDF
            PdfPTable pdfTable = new PdfPTable(4); // 4 columns

            // Add table headers
            pdfTable.addCell("ID");
            pdfTable.addCell("Name");
            pdfTable.addCell("Date");
            pdfTable.addCell("Amount");

            // Add table data
            for (int i = 0; i < resultTable.getRowCount(); i++) {
                pdfTable.addCell(resultTable.getValueAt(i, 0).toString());
                pdfTable.addCell(resultTable.getValueAt(i, 1).toString());
                pdfTable.addCell(resultTable.getValueAt(i, 2).toString());
                pdfTable.addCell(resultTable.getValueAt(i, 3).toString());
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(this, "PDF Exported Successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error exporting PDF: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReportFrame().setVisible(true);
            }
        });
    }
}
