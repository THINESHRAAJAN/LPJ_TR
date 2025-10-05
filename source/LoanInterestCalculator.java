package source;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class LoanInterestCalculator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoanInterestCalculator().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Loan Interest Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // Create table with sample data
        String[] columnNames = {"Name", "Phone Number", "Loan Date", "Product Type", "Loan Amount", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        model.addRow(new Object[]{"Alice", "123-456-7890", "2025-03-14", "Gold", 10000, "Not Paid"});
        model.addRow(new Object[]{"Bob", "987-654-3210", "2023-06-15", "Silver", 15000, "Paid"});
        model.addRow(new Object[]{"Charlie", "555-555-5555", "2022-11-30", "Gold", 20000, "Not Paid"});
        model.addRow(new Object[]{"Alice", "123-456-7890", "2025-03-14", "Gold", 25000, "Not Paid"});
        model.addRow(new Object[]{"Bob", "987-654-3210", "2023-06-15", "Silver", 30000, "Paid"});
        model.addRow(new Object[]{"Charlie", "555-555-5555", "2022-11-30", "Gold", 27000, "Not Paid"});
        model.addRow(new Object[]{"Alice", "123-456-7890", "2025-03-14", "Gold", 43000, "Not Paid"});
        model.addRow(new Object[]{"Bob", "987-654-3210", "2023-06-15", "Silver", 7500, "Paid"});
        model.addRow(new Object[]{"Charlie", "555-555-5555", "2022-11-30", "Gold", 120, "Not Paid"});
        model.addRow(new Object[]{"Alice", "123-456-7890", "2025-03-14", "Gold", 8423, "Not Paid"});
        model.addRow(new Object[]{"Bob", "987-654-3210", "2023-06-15", "Silver", 1256, "Paid"});
        model.addRow(new Object[]{"Charlie", "555-555-5555", "2022-11-30", "Gold", 7, "Not Paid"});
        model.addRow(new Object[]{"Alice", "123-456-7890", "2025-03-14", "Gold", 123, "Not Paid"});
        model.addRow(new Object[]{"Bob", "987-654-3210", "2023-06-15", "Silver", 789456320, "Paid"});
        model.addRow(new Object[]{"Charlie", "555-555-5555", "2022-11-30", "Gold", 4785236, "Not Paid"});

        JTable table = new JTable(model);
        
        // Set custom font for table
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);

        // Set custom table header font and background color
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(51, 153, 255));
        table.getTableHeader().setForeground(Color.WHITE);

        // Set background color for even and odd rows
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.GRAY);

        // Add right-click popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem calculateItem = new JMenuItem("Calculate Monthly Interest");
        calculateItem.setFont(new Font("Arial", Font.PLAIN, 14));
        calculateItem.addActionListener(e -> calculateInterest(table, frame));
        popupMenu.add(calculateItem);

        // Detect right-click on the table to show the popup menu
        table.setComponentPopupMenu(popupMenu);
        
        // Show the table in a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void calculateInterest(JTable table, JFrame frame) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get the loan information from the selected row
            String status = (String) table.getValueAt(selectedRow, 5);  // Getting Status Column value
            if ("Paid".equalsIgnoreCase(status)) {
                // Show alert that the bill is already paid
                JOptionPane.showMessageDialog(frame, "This bill has already been paid.", "Alert", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // If status is not paid, proceed to calculate the interest
            String name = (String) table.getValueAt(selectedRow, 0);
            String phoneNumber = (String) table.getValueAt(selectedRow, 1);
            String loanDateStr = (String) table.getValueAt(selectedRow, 2);
            String productType = (String) table.getValueAt(selectedRow, 3);
            Object value = table.getValueAt(selectedRow, 4);
            
            double loanAmount = 0.0;
            if (value instanceof Number) {
                loanAmount = ((Number) value).doubleValue();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid loan amount.");
                return; // Exit method if the data is not a valid number
            }

            try {
                // Parse loan date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date loanDate = sdf.parse(loanDateStr);
                Date currentDate = new Date();

                // Calculate the difference in months between the loan date and current date
                Calendar start = Calendar.getInstance();
                start.setTime(loanDate);
                Calendar end = Calendar.getInstance();
                end.setTime(currentDate);

                int monthsBetween = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12
                        + end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

                // Calculate monthly interest rate
                double monthlyInterestRate = productType.equals("Gold") ? (loanAmount*0.03) : (loanAmount*0.02);

                // Create a model for the interest breakdown table with the new "From Date" and "To Date" columns
                String[] breakdownColumnNames = { "Period", "From Date", "To Date", "Interest Amount", "Status", "Name", "Loan Date", "Loan Amount" };
                DefaultTableModel breakdownModel = new DefaultTableModel(breakdownColumnNames, 0);

                // Populate the table with monthly interest details
                for (int i = 0; i < monthsBetween; i++) {
                    Calendar monthStart = (Calendar) start.clone();
                    monthStart.add(Calendar.MONTH, i);  // Calculate the current month's start

                    // Calculate the end of the current month (one month later)
                    Calendar monthEnd = (Calendar) monthStart.clone();
                    monthEnd.add(Calendar.MONTH, 1);  // Set the "To Date" to one month after "From Date"

                    // Calculate interest for the month
                    double monthlyInterest =  monthlyInterestRate;

                    // Format the period as "MMM-yyyy" (e.g., Jan-2025)
                    SimpleDateFormat periodFormat = new SimpleDateFormat("MMM-yyyy");
                    String period = periodFormat.format(monthEnd.getTime());

                    // Add a row for this month to the breakdown table
                    breakdownModel.addRow(new Object[]{
                    		period,
                            sdf.format(monthStart.getTime()), // From Date (loan date of this month)
                            sdf.format(monthEnd.getTime()),   // To Date (1 month after From Date)
                            String.format("%.2f", monthlyInterest), // Interest Amount
                            "Not Paid", // Status
                            name, // Name
                            loanDateStr, // Loan Date
                            String.format("%.2f", loanAmount) // Loan Amount
                    });
                }

                // Create a JTable to display the breakdown
                JTable breakdownTable = new JTable(breakdownModel);
                breakdownTable.setFont(new Font("Arial", Font.PLAIN, 14));
                breakdownTable.setRowHeight(30);

                // Set custom table header font and background color
                breakdownTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
                breakdownTable.getTableHeader().setBackground(new Color(51, 153, 255));
                breakdownTable.getTableHeader().setForeground(Color.WHITE);

                // Set background color for even and odd rows
                breakdownTable.setBackground(Color.WHITE);
                breakdownTable.setForeground(Color.BLACK);
                breakdownTable.setGridColor(Color.GRAY);

                JScrollPane scrollPane = new JScrollPane(breakdownTable);
                scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                // Add a button to mark the interest as paid
                JButton markAsPaidButton = new JButton("Mark As Paid");
                markAsPaidButton.setFont(new Font("Arial", Font.BOLD, 14));
                markAsPaidButton.setBackground(new Color(51, 153, 255));
                markAsPaidButton.setForeground(Color.WHITE);
                markAsPaidButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                markAsPaidButton.addActionListener(e -> {
                    // Get the selected row from the breakdown table
                    int selectedRowForPayment = breakdownTable.getSelectedRow();

                    // If no row is selected, show a message
                    if (selectedRowForPayment == -1) {
                        JOptionPane.showMessageDialog(null, "Please select a row to mark as paid.");
                        return;
                    }

                    // Set the "Status" column (index 3) of the selected row to "Paid"
                    breakdownModel.setValueAt("Paid", selectedRowForPayment, 4);

                });

                // Show the new window with the interest breakdown and "Mark As Paid" button
                JFrame breakdownFrame = new JFrame("Interest Breakdown for " + name);
                breakdownFrame.setSize(800, 400);
                breakdownFrame.setLayout(new BoxLayout(breakdownFrame.getContentPane(), BoxLayout.Y_AXIS));
                breakdownFrame.add(scrollPane);
                breakdownFrame.add(markAsPaidButton);
                breakdownFrame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error calculating interest: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to calculate interest.");
        }
    }

	public void calculateInterestWithData(String billNum) {
		try {
			
            // Parse loan date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date loanDate = sdf.parse("2025-01-01");
            Date currentDate = new Date();

            // Calculate the difference in months between the loan date and current date
            Calendar start = Calendar.getInstance();
            start.setTime(loanDate);
            Calendar end = Calendar.getInstance();
            end.setTime(currentDate);

            int monthsBetween = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12
                    + end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

            // Calculate monthly interest rate
            double monthlyInterestRate = "Gold".equals("Gold") ? (10000 * 0.03) : (10000 * 0.02);

            // Create a model for the interest breakdown table with the new "From Date" and "To Date" columns
            String[] breakdownColumnNames = { "Period", "From Date", "To Date", "Interest Amount", "Status", "Name", "Loan Date", "Loan Amount" };
            DefaultTableModel breakdownModel = new DefaultTableModel(breakdownColumnNames, 0);

            // Populate the table with monthly interest details
            for (int i = 0; i < monthsBetween; i++) {
                Calendar monthStart = (Calendar) start.clone();
                monthStart.add(Calendar.MONTH, i);  // Calculate the current month's start

                // Calculate the end of the current month (one month later)
                Calendar monthEnd = (Calendar) monthStart.clone();
                monthEnd.add(Calendar.MONTH, 1);  // Set the "To Date" to one month after "From Date"

                // Calculate interest for the month
                double monthlyInterest = monthlyInterestRate;

                // Format the period as "MMM-yyyy" (e.g., Jan-2025)
                SimpleDateFormat periodFormat = new SimpleDateFormat("MMM-yyyy");
                String period = periodFormat.format(monthEnd.getTime());

                // Add a row for this month to the breakdown table
                breakdownModel.addRow(new Object[]{
                        period,
                        sdf.format(monthStart.getTime()), // From Date (loan date of this month)
                        sdf.format(monthEnd.getTime()),   // To Date (1 month after From Date)
                        String.format("%.2f", monthlyInterest), // Interest Amount
                        "Not Paid", // Status
                        "Thinesh", // Name
                        "2025-01-01", // Loan Date
                        String.format("%.2f", 10000.0) // Loan Amount
                });
            }

         // Create a JTable to display the breakdown
            JTable breakdownTable = new JTable(breakdownModel);
            breakdownTable.setFont(new Font("Arial", Font.PLAIN, 14));
            breakdownTable.setRowHeight(30);

            // Set custom table header font and background color
            breakdownTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            breakdownTable.getTableHeader().setBackground(new Color(51, 153, 255));
            //breakdownTable.getTableHeader().setForeground(Color.WHITE);

            // Set background color for even and odd rows
//            breakdownTable.setBackground(Color.WHITE);
            breakdownTable.setForeground(Color.BLACK);
            breakdownTable.setGridColor(Color.GRAY);

            JScrollPane scrollPane = new JScrollPane(breakdownTable);
            scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            // Add a button to mark the interest as paid
            JButton markAsPaidButton = new JButton("Pay Interest");
            markAsPaidButton.setFont(new Font("Arial", Font.BOLD, 14));
            markAsPaidButton.setBackground(new Color(51, 153, 255));
            markAsPaidButton.setForeground(Color.RED);
            markAsPaidButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Add a button to mark the interest as paid
            markAsPaidButton.addActionListener(e -> {
                // Get the selected row from the breakdown table
                int selectedRowForPayment = breakdownTable.getSelectedRow();

                // If no row is selected, show a message
                if (selectedRowForPayment == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to mark as paid.");
                    return;
                }

                // Set the "Status" column (index 3) of the selected row to "Paid"
                breakdownModel.setValueAt("Paid", selectedRowForPayment, 4);
            });

            // Show the new window with the interest breakdown and "Mark As Paid" button
            JFrame breakdownFrame = new JFrame("Interest Breakdown for " + "Thinesh");
            breakdownFrame.setSize(1000, 500);
            breakdownFrame.setResizable(false);
            breakdownFrame.setLayout(new BoxLayout(breakdownFrame.getContentPane(), BoxLayout.Y_AXIS));
            breakdownFrame.add(scrollPane);
            breakdownFrame.add(markAsPaidButton);
            breakdownFrame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error calculating interest: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
		
	
}
