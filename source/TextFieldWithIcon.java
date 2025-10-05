package source;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class TextFieldWithIcon {

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("TextField with Icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLayout(new FlowLayout());

        // Create an icon and resize it to 10px by 10px
        ImageIcon icon = new ImageIcon("/home/thineshraajan/Downloads/bill.png");  // Replace with your icon path
        Image image = icon.getImage(); // Get the image from the icon
        Image resizedImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Resize it
        ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create new ImageIcon with resized image

        // Create a text field
        JTextField textField = new JTextField(20);

        // Create a panel to hold the icon and the text field
        JPanel panel = new JPanel(new BorderLayout());
        
        // Add resized icon to the left side of the text field
        JLabel iconLabel = new JLabel(resizedIcon);
        panel.add(iconLabel, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);

        // Add the panel to the frame
        frame.add(panel);

        frame.setVisible(true);
    }
}
