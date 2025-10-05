package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPopup {

    public static void main(String[] args) {
        // Create a frame
        JFrame frame = new JFrame("Payment Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        // Create a button to show the pop-up
        JButton showPopupButton = new JButton("Show Payment Popup");
        frame.add(showPopupButton, BorderLayout.CENTER);
        
        // Add action listener to show the popup dialog
        showPopupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentPopup();
            }
        });
        
        frame.setVisible(true);
    }
    
    public static void showPaymentPopup() {
        // Create a JDialog to act as a pop-up
        JDialog dialog = new JDialog();
        dialog.setTitle("Payment Details");
        dialog.setSize(400, 300);
        dialog.setModal(true);
        
        // Create panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        
        // Labels and TextFields
        JLabel lblActualAmount = new JLabel("Actual Amount:");
        JLabel lblPaidAmount = new JLabel("Paid Amount:");
        JLabel lblRemainingAmount = new JLabel("Remaining Amount:");
        JLabel lblPayingAmount = new JLabel("Paying Amount:");

        JTextField tfActualAmount = new JTextField();
        JTextField tfPaidAmount = new JTextField();
        JTextField tfRemainingAmount = new JTextField();
        JTextField tfPayingAmount = new JTextField();
        
        // Set initial values
        tfActualAmount.setText("1000");
        tfPaidAmount.setText("500");
        tfRemainingAmount.setText("500");
        tfActualAmount.setEditable(false);
        tfPaidAmount.setEditable(false);
        tfRemainingAmount.setEditable(false);
        
        // Calculate button to update values
        tfPayingAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double actualAmount = Double.parseDouble(tfActualAmount.getText());
                    double paidAmount = Double.parseDouble(tfPaidAmount.getText());
                    double payingAmount = Double.parseDouble(tfPayingAmount.getText());
                    
                    // Calculate remaining amount
                    double remainingAmount = actualAmount - paidAmount - payingAmount;
                    
                    // Update the remaining amount text field
                    tfRemainingAmount.setText(String.format("%.2f", remainingAmount));
                    
                    // Update the paid amount after payment
                    tfPaidAmount.setText(String.format("%.2f", paidAmount + payingAmount));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Add components to the panel
        panel.add(lblActualAmount);
        panel.add(tfActualAmount);
        panel.add(lblPaidAmount);
        panel.add(tfPaidAmount);
        panel.add(lblRemainingAmount);
        panel.add(tfRemainingAmount);
        panel.add(lblPayingAmount);
        panel.add(tfPayingAmount);
        
        // Add the panel to the dialog
        dialog.add(panel);
        
        // Show the dialog
        dialog.setVisible(true);
    }
}
