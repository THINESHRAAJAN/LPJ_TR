package source;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DatabaseExporterApp extends JFrame {

	private static final long serialVersionUID = 715019411937189887L;

	// Constructor to set up the UI
	public DatabaseExporterApp() {
		// Set up the frame
		setTitle("Database Exporter");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Create and add the button
		JButton exportButton = new JButton("Export & Send");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportAndSendData();
			}
		});

		// Layout setup
		JPanel panel = new JPanel();
		panel.add(exportButton);
		add(panel);
	}

	// Export tables to CSV and send via email
	private void exportAndSendData() {
		try {
			// Get the list of tables from the database
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "root",
					""); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

				while (rs.next()) {
					String tableName = rs.getString(1);
					exportTableToCSV(tableName); // Export table data to CSV
					File csvFile = new File(tableName + ".csv");

					// Send the CSV file as email attachment
					sendEmail("recipient@example.com", "Database Export - " + tableName,
							"Please find attached the exported data for " + tableName, csvFile);
				}

				JOptionPane.showMessageDialog(this, "Export and email sent successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error exporting data", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Export a single table to CSV
	private void exportTableToCSV(String tableName) {
		String query = "SELECT * FROM " + tableName;
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "root",
				""); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			try (FileWriter writer = new FileWriter(tableName + ".csv")) {

				// Write column names
				for (int i = 1; i <= columnCount; i++) {
					writer.append(metaData.getColumnName(i));
					if (i < columnCount)
						writer.append(",");
				}
				writer.append("\n");

				// Write data rows
				while (rs.next()) {
					for (int i = 1; i <= columnCount; i++) {
						writer.append(rs.getString(i));
						if (i < columnCount)
							writer.append(",");
					}
					writer.append("\n");
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// Send email with CSV attachment
	private void sendEmail(String to, String subject, String body, File attachment) {
		String from = "your-email@example.com"; // Your email address
		String host = "smtp.example.com"; // SMTP host (e.g., smtp.gmail.com)

		// Set up mail server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Set up session with email credentials
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("your-email@example.com", "your-email-password");
			}
		});

		try {
			// Create a MimeMessage
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);

			// Create a MimeBodyPart for the attachment
			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.attachFile(attachment);

			// Create a Multipart to hold the email body and attachment
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(attachmentPart);

			// Set the content of the message to the multipart
			message.setContent(multipart);

			// Send the email
			Transport.send(message);
			System.out.println("Email sent successfully to " + to);
		} catch (MessagingException | java.io.IOException e) {
			e.printStackTrace();
		}
	}

	// Main method to launch the application
	public static void main(String[] args) {
		// Set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create and show the frame
		SwingUtilities.invokeLater(() -> {
			DatabaseExporterApp frame = new DatabaseExporterApp();
			frame.setVisible(true);
		});
	}
}
