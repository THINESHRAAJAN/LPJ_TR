package source;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

public class MainApp {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LoginPage();
			}
		});
	}
}

// Login Page
class LoginPage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField sysDate;
	public static String SYSTEMDATE = "";
	public boolean isAdmin = false;
	public final String Developer = "Thinesh Raajan";

	public LoginPage() {
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		setSize(400, 300);
		setLocationRelativeTo(null); // Center the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		URL appIcon = getClass().getResource("/images/jewelry.png");
		ImageIcon APPICON = new ImageIcon(appIcon);
		Image APPLOGO = APPICON.getImage();
		setIconImage(APPLOGO);

		JPanel panel = new BackgroundPanel(); // Use the custom BackgroundPanel
		panel.setLayout(null);

		// Set Font and Color for Username Label
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(50, 50, 100, 30);
		usernameLabel.setForeground(new Color(10, 2, 0)); // Colorful blue
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); // Bold and larger font
		panel.add(usernameLabel);

		// Set Font and Color for Username Field
		usernameField = new JTextField();
		usernameField.setBounds(150, 50, 150, 30);
		usernameField.setForeground(new Color(245, 42, 59));
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 16)); // Slightly larger text
		panel.add(usernameField);

		// Set Font and Color for Password Label
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(50, 100, 100, 30);
		passwordLabel.setForeground(new Color(10, 2, 0)); // Colorful red
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); // Bold and larger font
		panel.add(passwordLabel);

		// Set Font and Color for Password Field
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 100, 150, 30);
		passwordField.setForeground(new Color(245, 42, 59));
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 16)); // Slightly larger text
		panel.add(passwordField);

		// Set Font and Color for Date Label
		JLabel sysdateLabel = new JLabel("Date:");
		sysdateLabel.setBounds(50, 150, 100, 30);
		sysdateLabel.setForeground(new Color(10, 2, 0)); // Colorful green
		sysdateLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); // Bold and larger font
		panel.add(sysdateLabel);

		// Set Font and Color for Date Field
		sysDate = new JTextField();
		sysDate.setBounds(150, 150, 150, 30);
		sysDate.setEditable(false);
		sysDate.setForeground(new Color(245, 42, 59));
		sysDate.setFont(new Font("Tahoma", Font.BOLD, 16)); // Slightly larger text
		sysDate.setBackground(new Color(240, 255, 240)); // Light green background for the text field
		String currentDate = getCurrentDate();
		sysDate.setText(currentDate);
		SYSTEMDATE = currentDate;
		panel.add(sysDate);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(150, 200, 100, 30);
		loginButton.setForeground(new Color(29, 104, 224)); // Green button
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 16)); // Bold and larger font
		loginButton.setBackground(Color.yellow);
		panel.add(loginButton);

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Simulate successful login
				DBConnect login = new DBConnect();
				boolean isValidLogin = login.authenticateUser(usernameField.getText().trim(),
						new String(passwordField.getPassword()).trim());
				isAdmin = login.isAdmin(usernameField.getText().trim(), new String(passwordField.getPassword()).trim());

				// TR Licence Check Logic Start
				String endDateStr = "";
				endDateStr = login.getLicenseEndDate(Developer);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate endDate = LocalDate.parse(endDateStr, formatter);
				LocalDate currentDate = LocalDate.now();
				boolean isValidLicense = true;
				if (currentDate.isAfter(endDate)) {
					isValidLicense = true; //Personal Check
				} else if (ChronoUnit.DAYS.between(currentDate, endDate) <= 5) {
					int daysLeft = (int) ChronoUnit.DAYS.between(currentDate, endDate);
					isValidLicense = true;
					JOptionPane.showMessageDialog(null,
							"Licence will expire in " + daysLeft + " days. Proceeding with caution.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				// TR Licence Check Logic End

				if (isAdmin && isValidLogin /* && isValidLicense */) { // 26July2025 : Commented For Admin No License
																		// Check
					dispose(); // Close the login page
					try {
						new AdminHomePage();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // Open the home page

				} else if (!isAdmin && isValidLogin && isValidLicense) {
					dispose(); // Close the login page
					try {
						new HomePage();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // Open the home page
				} else if (!isValidLicense) {
					JOptionPane.showMessageDialog(null, "Licence Expired! Cannot Proceed. Contact @Thinesh Raajan",
							"Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid login credentials.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(panel);
		setVisible(true);
	}

	private static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(); // Get the current system date
		return dateFormat.format(date); // Return formatted date as a string
	}
}

//Custom JPanel class to paint the background image
class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	// Constructor to load the background image
	public BackgroundPanel() {
		// Get the image from the "images" folder inside the project
		URL imageUrl = getClass().getResource("/images/bg1.jpg"); // Adjust the path to your image
		if (imageUrl != null) {
			backgroundImage = new ImageIcon(imageUrl).getImage();
		} else {
			System.out.println("Image not found!");
		}
	}

	// Override paintComponent to draw the background image
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Scale the image to fit the panel
	}
}

//Dashboard
class HomePage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel; // Content panel to dynamically update
	private CardLayout cardLayout; // CardLayout to switch between pages
	ArrayList<AdaguBill> AdaguBillListViewPanel;
	ArrayList<AdaguBill> AdaguBillListInterestPanel;
	ArrayList<AdaguBill> AdaguBillListAdaguPanel;
	ArrayList<AdaguBill> AdaguBillListGLPanel;
	ArrayList<String> productGoldList;
	ArrayList<String> productSilverList;
	ArrayList<String> masterCustomerList;
	ArrayList<String> masterAddressList;
	ArrayList<String> masterHeirList;
	ArrayList<String> masterLockerList;
	ArrayList<AdaguBill> AdaguBillListCancelViewPanel;
	ArrayList<AdaguBill> AdaguBillListUnpaidViewPanel;
	ArrayList<SalesBill> SalesBillListViewPanel;
	ArrayList<SalesBill> DetailsViewPanel;
	ArrayList<String> csvLocation = new ArrayList<String>();
	boolean firstTimeEmail = false;
	ArrayList<String> closingBillNums; // 28Apr2025
	ArrayList<GSRate> GSRatesViewPanel; //24Oct2025
	ArrayList<String> openOldBalanceAmt; //27Oct2025

	public HomePage() throws ParseException {
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to full screen
		// setSize(1100, 800);
		setResizable(false);
		setLocationRelativeTo(null); // Center the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL appIcon = getClass().getResource("/images/jewelry.png");
		ImageIcon APPICON = new ImageIcon(appIcon);
		Image APPLOGO = APPICON.getImage();
		setIconImage(APPLOGO);

		// Main container panel
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		// Sidebar panel
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.setBackground(new Color(72, 61, 139)); // Dark Purple Background

		String[] options = { "Home", "Gold & Silver Rates", "Details", "Search", "Add Sales Bill", "Add Adagu Bill", 
				"View Sales Bills", "View Adagu Bills", "Interest Calculation(%)", "Cancel Ledger", "UnPaid Bills", "Synch To Mobile", "Dummy", "Logout" };
		JButton[] buttons = new JButton[options.length];

		// Content Panel with CardLayout
		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout); // Set CardLayout to the content panel

		// Add panels for each content page
		contentPanel.add(createHomePanel(), "Home");
		contentPanel.add(createGSRatePanel(), "Gold & Silver Rates"); //24Oct2025
		contentPanel.add(createDetailsPanel(), "Details");
		contentPanel.add(createSearchPanel(), "Search");
		//24Oct2025
		contentPanel.add(createAddSalesBillPanel(), "Add Sales Bill");
		contentPanel.add(createAddAdaguBillPanel(), "Add Adagu Bill");
		contentPanel.add(createViewSalesBillPanel(), "View Sales Bills");
		contentPanel.add(createViewAdaguBillPanel(), "View Adagu Bills");
		//24Oct2025
		contentPanel.add(createInterestCalculationPanel(), "Interest Calculation(%)");
		contentPanel.add(createCancelLedgerPanel(), "Cancel Ledger");
		contentPanel.add(createUnPaidBillsPanel(), "UnPaid Bills");
		contentPanel.add(createSynchToMobilePanel(), "Synch To Mobile");
		contentPanel.add(createDummyAddAdaguBillPanel(), "Dummy");

		for (int i = 0; i < options.length; i++) {
			buttons[i] = new JButton(options[i]);
			buttons[i].setBackground(new Color(144, 238, 144)); // Light Green
			buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			buttons[i].setFocusPainted(false);
			buttons[i].setFont(new Font("Tahoma", Font.BOLD, 16));
			buttons[i].setMaximumSize(new Dimension(200, 40));
			buttons[i].setPreferredSize(new Dimension(200, 40));
			buttons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String buttonText = ((JButton) e.getSource()).getText();
					if (buttonText.equals("Logout")) {
						System.exit(0); // Close the application on logout
					} else {
						// Update the content panel based on button click
						showContent(buttonText);
					}
				}
			});
			sidebar.add(buttons[i]);
			sidebar.add(Box.createVerticalStrut(10)); // Add space between buttons
		}
		// Add Sidebar and Content Panel to container
		// Add Sidebar and Content Panel to container
		JPanel calClock = new JPanel();
		calClock.setBackground(new Color(72, 61, 139));

		// Set BoxLayout for vertical arrangement
		calClock.setLayout(new BoxLayout(calClock, BoxLayout.Y_AXIS));

		// Create the CalculatorApp and AnalogClock
		CalculatorApp cal = new CalculatorApp();
		cal.setPreferredSize(new Dimension(80, 80));
		cal.setBackground(new Color(72, 61, 139));

		AnalogClock clock = new AnalogClock();
		clock.setPreferredSize(new Dimension(200, 200));
		clock.setBackground(new Color(72, 61, 139));

		// Add CalculatorApp and AnalogClock to the JPanel
		calClock.add(clock);
		// calClock.add(cal);

		// Add calClock to the sidebar (positioned at the south)
		sidebar.add(calClock, BorderLayout.SOUTH);
		container.add(sidebar, BorderLayout.WEST);
		container.add(contentPanel, BorderLayout.CENTER);

		add(container);
		setVisible(true);
	}

	// Method to show the content based on selected option
	private void showContent(String pageName) {
		cardLayout.show(contentPanel, pageName); // Show the corresponding panel
	}

	// Panel for Home
	private JPanel createHomePanel() {
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(245, 245, 245)); // Light gray background
		JPanel pieChartPanel = createBarChartIncomePanel();
		JPanel barChartPanel = createBarChartExpensePanel();

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pieChartPanel, barChartPanel);
		splitPane.setDividerLocation(300);
		homePanel.add(splitPane, BorderLayout.CENTER);
		homePanel.setVisible(true);
		return homePanel;
	}

	// Create Pie Chart Panel
	private JPanel createBarChartIncomePanel() {

		Double b1 = 0.0, b2 = 0.0, b3 = 0.0, b4 = 0.0, b5 = 0.0, b6 = 0.0, b7 = 0.0, b8 = 0.0, b9 = 0.0, b10 = 0.0,
				b11 = 0.0, b12 = 0.0;

		DBConnect barGraph = new DBConnect();
		b1 = barGraph.getBarData("B1");
		b2 = barGraph.getBarData("B2");
		b3 = barGraph.getBarData("B3");
		b4 = barGraph.getBarData("B4");
		b5 = barGraph.getBarData("B5");
		b6 = barGraph.getBarData("B6");
		b7 = barGraph.getBarData("B7");
		b8 = barGraph.getBarData("B8");
		b9 = barGraph.getBarData("B9");
		b10 = barGraph.getBarData("B10");
		b11 = barGraph.getBarData("B11");
		b12 = barGraph.getBarData("B12");

		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
		barDataset.addValue(b1, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "9-10");
		barDataset.addValue(b2, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "10-11");
		barDataset.addValue(b3, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "11-12");
		barDataset.addValue(b4, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "12-1");
		barDataset.addValue(b5, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "1-2");
		barDataset.addValue(b6, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "2-3");
		barDataset.addValue(b7, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "3-4");
		barDataset.addValue(b8, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "4-5");
		barDataset.addValue(b9, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "5-6");
		barDataset.addValue(b10, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "6-7");
		barDataset.addValue(b11, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "7-8");
		barDataset.addValue(b12, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "8-9");

		JFreeChart barChart = ChartFactory.createBarChart(" Hourly Money Transit Income ", "Time", "Amount (\u20B9)",
				barDataset, PlotOrientation.VERTICAL, true, true, true);

		// Create the panel and add the bar chart
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(900, 400));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel, BorderLayout.CENTER);
		panel.setVisible(true);
		return panel;
	}

	// Create Bar Chart Panel
	private JPanel createBarChartExpensePanel() {

		Double b1 = 0.0, b2 = 0.0, b3 = 0.0, b4 = 0.0, b5 = 0.0, b6 = 0.0, b7 = 0.0, b8 = 0.0, b9 = 0.0, b10 = 0.0,
				b11 = 0.0, b12 = 0.0;

		DBConnect barGraph = new DBConnect();
		b1 = barGraph.getBarData("B1");
		b2 = barGraph.getBarData("B2");
		b3 = barGraph.getBarData("B3");
		b4 = barGraph.getBarData("B4");
		b5 = barGraph.getBarData("B5");
		b6 = barGraph.getBarData("B6");
		b7 = barGraph.getBarData("B7");
		b8 = barGraph.getBarData("B8");
		b9 = barGraph.getBarData("B9");
		b10 = barGraph.getBarData("B10");
		b11 = barGraph.getBarData("B11");
		b12 = barGraph.getBarData("B12");

		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
		barDataset.addValue(b1, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "9-10");
		barDataset.addValue(b2, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "10-11");
		barDataset.addValue(b3, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "11-12");
		barDataset.addValue(b4, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "12-1");
		barDataset.addValue(b5, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "1-2");
		barDataset.addValue(b6, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "2-3");
		barDataset.addValue(b7, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "3-4");
		barDataset.addValue(b8, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "4-5");
		barDataset.addValue(b9, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "5-6");
		barDataset.addValue(b10, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "6-7");
		barDataset.addValue(b11, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "7-8");
		barDataset.addValue(b12, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "8-9");

		JFreeChart barChart = ChartFactory.createBarChart(" Hourly Money Transit Expense", "Time", "Amount (\u20B9)",
				barDataset, PlotOrientation.VERTICAL, true, true, true);

		// Create the panel and add the bar chart
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(800, 400));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel, BorderLayout.CENTER);
		panel.setVisible(true);
		return panel;
	}

	private JPanel createDummyAddAdaguBillPanel() {
		JPanel panel;
		JTextField billNum, adaguAmt, custName, custPhone, custHeir, adaguActualAmt, productDetails, productWeight,
				productValue, custAddress;
		JLabel shopName;
		JButton saveBill, notifyBill, printBill, clearBill;
		JDateChooser adaguDate, redemptionDate, cancelDate;
		DefaultListModel<String> selectedProductsModel;
		JList<String> selectedProductsList;
		JTextField LICENCENO; // = " 16/21-22 "

		JTextField lockerName, lockerBillNumber; // Locker Detail New Fix
		JDateChooser lockerDate; // Locker Detail New Fix

		JDateChooser balanceDate; // Balance Detail New Fix
		JTextField balanceBillNum, balanceproductDetails; // Balance Detail New Fix

		// Simulating a product list (in a real-world scenario, this could be from a
		// database)
		/*
		 * ArrayList<String> productList = new ArrayList<>(Arrays.asList("2 LINE CHINE",
		 * "ADDAGAI ,STONE TEEKA", "BABY RING", "BANGLES", "BASER", "BOX RING",
		 * "BRACELET", "BROCH", "BUTTON TOPS", "BUTTON TOPS THONGAL", "CAST DROPS",
		 * "CASTING RING", "CASTING TOPS", "CHAIN", "CHAIN WITH DOLLAR", "DELHI CHAIN",
		 * "DELHI SUTTU MATTLE", "DIE COIN", "DOLLAR", "FANCE HARAM", "FANCE NECKLES",
		 * "FANCY AARAM", "FANCY DROPS", "FANCY JUMKI", "FANCY MATTLE", "FANCY NECKLES",
		 * "FANCY RING", "FANCY TOPS", "FANCY TOPS JIMUKI", "GALSER", "GENTS RING",
		 * "GUNDU WT LOCK", "HARAM", "JABKA", "JUMIKI", "JUMIKI TOPS", "K TONGAL",
		 * "KALAN BASEAR", "KAMBI", "KAPPU", "KARI MANI CHAIN", "KASA DROPS",
		 * "KASA HARAM", "KASA JUMIKI", "KASA NC", "KASA NECKLES", "KASA RING",
		 * "KASA T0PS", "KASA THONGAL", "KASA TOPS JIMKI", "KASA VAALI", "KASU", "KOLA",
		 * "KUTHU STAR", "LADIES BRACELET", "LADIES RING", "LAKSHMI BOTTU",
		 * "LAKSHMI KASU", "LAKSHMI POTTU", "LAXMI", "LEAF BESAR", "LEAF TOPS",
		 * "MANG TIKKA", "MANGA", "MANGA KASU", "MANGA NECKLES", "MANGA TOPS", "MATTLE",
		 * "MOHAN MALA", "MOPE CHAIN", "MOTTU", "MOTTU TONGAL", "NECKLACE",
		 * "NELLORE STONE DROPS", "NELLORE STONE TOPS", "NELLORE STONE TOPS JUMKI",
		 * "NER MATTAL", "NETTI CHUTTI", "OWAL RING", "RETTAI SARAM CHAIN", "RING",
		 * "ROUND RING", "S CHAIN", "S CHAIN ATTIKAI STONE TIKA", "SALANGI MATTAL",
		 * "SET JUMIKI", "SIDE BESAR", "side ma", "side mattale", "SIDE MATTLE",
		 * "SILVER CAL CHAIN", "STONE ADDIGAI", "STONE BASER", "STONE BASER",
		 * "STONE BROUCH", "STONE CHITTI MOPE CHAIN", "STONE DOLLAR", "STONE DROPS",
		 * "STONE FANCY CHAIN", "STONE HARAM", "STONE JUMIKI", "STONE JUMIKI TOPS",
		 * "STONE KASU", "STONE MATTLE", "STONE MOPE", "STONE MOPE CHAIN",
		 * "STONE NALY RING", "STONE NECKLES", "STONE NETTI CHUTTI", "STONE PURAI",
		 * "STONE PURAI TOPS", "STONE RING", "STONE SET JUMKI", "STONE THONGAL",
		 * "STONE TOPS", "SUTTU MATTLE", "TALI", "THALI", "THONGAL", "TOKYO CHAIN",
		 * "TONDU MATTLE", "TONGAL", "TOPS", "TV RING", "URU", "VAALI", "VAALI THONGAL",
		 * "VALAISEP", "VALAIYAM", "VISIRI STONE DROPS", "VISIRI STONE TOPS",
		 * "YANAI MUDI RING"));
		 * 
		 * ArrayList<String> productSilverList = new
		 * ArrayList<>(Arrays.asList("MUTHU KODI","ARUNA KODI","S CHAIN","KUSHBOO CHAIN"
		 * ,"KUSHBOO JAALAR CHAIN","KUSHBOO SALANGAI CHAIN","OTIYANAM","KAI KAAPU"
		 * ,"KAAL KAAPU","THANDAI",
		 * "21/2 MUTHU CHAIN","KUSHBOO THANIYA CHAIN","S CHAIN THANIYA CHAIN"
		 * ,"SMS CHAIN","M.S KOKKI CHAIN","S CHAIN PATTANI CHAIN"
		 * ,"KUSHBOO PATTANI CHAIN", "KAI CHAIN","KAL CHAIN","VELLI CHAIN"));
		 */

		String[] heirRelationList = { "", "S/O", "W/O", "H/O", "D/O" };
		JComboBox<String> heirRelation = new JComboBox<>(heirRelationList);

		String[] statusList = { "", "Paid", "Pending" };
		JComboBox<String> status = new JComboBox<>(statusList);

		String[] productType = { "", "Gold", "Silver", "Gold & Silver" };
		JComboBox<String> productTypes = new JComboBox<>(productType);

		productTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				productGoldList = loadData.loadGoldData();
				productSilverList = loadData.loadSilverData();
			}
		});

		// Set up the JFrame
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		// setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to full screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());

		// Set a custom look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialize components with a larger font and bold text
		Font inputFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);

		shopName = new JLabel(" Sri Lalith Prasanna Jewellery - ADAGU BILL ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		billNum = new JTextFieldWithPlaceholder("Bill Num");
		LICENCENO = new JTextFieldWithPlaceholder("*****");
		adaguAmt = new JTextFieldWithPlaceholder("Amount");
		custName = new JTextFieldWithPlaceholder("Name");
		custPhone = new JTextFieldWithPlaceholder("Phone");
		custHeir = new JTextFieldWithPlaceholder("Heir");
		adaguActualAmt = new JTextFieldWithPlaceholder("Actual Amount");
		productDetails = new JTextFieldWithPlaceholder("Products");
		productWeight = new JTextFieldWithPlaceholder("Weight");
		productValue = new JTextFieldWithPlaceholder("Value");
		custAddress = new JTextFieldWithPlaceholder("Address");
		adaguDate = new JDateChooser();
		redemptionDate = new JDateChooser();
		cancelDate = new JDateChooser();

		// Locker Detail New Fix
		lockerName = new JTextFieldWithPlaceholder("Locker Name");
		lockerBillNumber = new JTextFieldWithPlaceholder("Locker Bill Num");
		lockerDate = new JDateChooser();

		// Balance Detail New Fix
		balanceBillNum = new JTextFieldWithPlaceholder("Balance Bill Num");
		balanceproductDetails = new JTextFieldWithPlaceholder("Balance Product");
		balanceDate = new JDateChooser();

		billNum.setEditable(true);
		adaguDate.setEnabled(true);
		redemptionDate.setEnabled(true);
		cancelDate.setEnabled(true);
		adaguActualAmt.setEditable(true);
		productValue.setEditable(true);
		LineBorder border = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
		billNum.setBorder(border);
		billNum.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguAmt.setBorder(border);
		adaguAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custName.setBorder(border);
		custName.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custPhone.setBorder(border);
		custPhone.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custHeir.setBorder(border);
		custHeir.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguActualAmt.setBorder(border);
		adaguActualAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productDetails.setBorder(border);
		productDetails.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productWeight.setBorder(border);
		productWeight.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productValue.setBorder(border);
		productValue.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguDate.setBorder(border);
		redemptionDate.setBorder(border);
		cancelDate.setBorder(border);
		custAddress.setBorder(border);
		custAddress.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		heirRelation.setBorder(border);
		heirRelation.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		status.setBorder(border);
		status.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productTypes.setBorder(border);
		productTypes.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));

		// Locker Detail New Fix
		lockerName.setBorder(border);
		lockerName.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		lockerBillNumber.setBorder(border);
		lockerBillNumber.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		lockerDate.setBorder(border);

		// Balance Detail New Fix
		balanceBillNum.setBorder(border);
		balanceBillNum.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		balanceproductDetails.setBorder(border);
		balanceproductDetails.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		balanceDate.setBorder(border);

		// Set custom font for input fields and text area
		billNum.setFont(inputFont);
		adaguAmt.setFont(inputFont);
		custName.setFont(inputFont);
		custPhone.setFont(inputFont);
		custHeir.setFont(inputFont);
		adaguActualAmt.setFont(inputFont);
		productDetails.setFont(inputFont);
		productWeight.setFont(inputFont);
		productValue.setFont(inputFont);
		custAddress.setFont(inputFont);
		heirRelation.setFont(inputFont);
		status.setFont(inputFont);
		productTypes.setFont(inputFont);
		heirRelation.setPreferredSize(new Dimension(150, 50));
		status.setPreferredSize(new Dimension(150, 50));
		productTypes.setPreferredSize(new Dimension(150, 50));

		// Locker Detail New Fix
		lockerName.setFont(inputFont);
		lockerBillNumber.setFont(inputFont);
		lockerDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		lockerDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		lockerDate.setFont(inputFont);

		// Balance Detail New Fix
		balanceBillNum.setFont(inputFont);
		balanceproductDetails.setFont(inputFont);
		balanceDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		balanceDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		balanceDate.setFont(inputFont);

		// Set button colors and styles
		saveBill = new JButton("Save");

		saveBill.setBackground(new Color(56, 142, 60)); // Green
		saveBill.setForeground(Color.RED);

		saveBill.setFont(labelFont);

		clearBill = new JButton("New");
		clearBill.setBackground(new Color(56, 142, 60)); // Green
		clearBill.setForeground(Color.RED);
		clearBill.setFont(labelFont);

		adaguDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		adaguDate.setDate(new java.util.Date()); // Set default system date
		adaguDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		adaguDate.setFont(inputFont);

		redemptionDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(adaguDate.getDate());
		calendar.add(Calendar.DAY_OF_YEAR, 365);
		redemptionDate.setDate(calendar.getTime());
		redemptionDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		redemptionDate.setFont(inputFont);

		cancelDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		cancelDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		cancelDate.setFont(inputFont);

		adaguDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = (Date) evt.getNewValue();
				if (selectedDate != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(selectedDate);
					calendar.add(Calendar.DAY_OF_YEAR, 365);
					redemptionDate.setDate(calendar.getTime());
				}
			}
		});

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem newItem = new JMenuItem("New");
		popupMenu.add(newItem);

		productDetails.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productString = JOptionPane.showInputDialog(null, "Product Name : ", "Quantity Input",
						JOptionPane.QUESTION_MESSAGE);
				if (productString != null && !productString.trim().isEmpty()) {
					try {
						String productType = productTypes.getSelectedItem().toString();
						if (productType.length() < 1) {
							JOptionPane.showMessageDialog(null, "Choose Either Gold or Silver", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);
						} else {
							DBConnect saveProd = new DBConnect();
							boolean isSaved = saveProd.saveNewProduct(productType, productString);
							if (isSaved) {
								JOptionPane.showMessageDialog(null, "Product Saved Succesfully!", "Success",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
										JOptionPane.ERROR_MESSAGE);

							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In product Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupAddressMenu = new JPopupMenu();
		JMenuItem newAddressItem = new JMenuItem("New");
		popupAddressMenu.add(newAddressItem);

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterAddressList = loadData.loadAddressData();
			}
		});

		custAddress.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newAddressItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custAddressString = JOptionPane.showInputDialog(null, "Customer Address", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custAddressString != null && !custAddressString.trim().isEmpty()) {
					try {
						DBConnect saveAddress = new DBConnect();
						boolean isSaved = saveAddress.saveNewAddress(custAddressString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Address Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupNameMenu = new JPopupMenu();
		JMenuItem newNameItem = new JMenuItem("New");
		popupNameMenu.add(newNameItem);

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterCustomerList = loadData.loadCustomerData();
			}
		});

		custName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newNameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Name", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewName(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Name Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupHeirMenu = new JPopupMenu();
		JMenuItem newHeirItem = new JMenuItem("New");
		popupHeirMenu.add(newHeirItem);

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterHeirList = loadData.loadCustomerHeirData();
			}
		});

		custHeir.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newHeirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Heir", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewHeir(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Heir Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Locker Detail New Fix
		JPopupMenu popupLockerMenu = new JPopupMenu();
		JMenuItem newLockerItem = new JMenuItem("New");
		popupLockerMenu.add(newLockerItem);

		lockerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterLockerList = loadData.loadLockerData();
			}
		});

		lockerName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupLockerMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupLockerMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newLockerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Locker Name", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewLockerDetails(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Locker Details Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Locker Details Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Locker Details Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Locker Details Saving!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();
				if (custPhone.getText().length() >= 10) {
					String phoneNumber = custPhone.getText().trim();
					AdaguBill latestBill = null;
					for (int i = AdaguBillListAdaguPanel.size() - 1; i >= 0; i--) {
						AdaguBill bill = AdaguBillListAdaguPanel.get(i);
						if (bill.getCUSTOMER_PHONE().equals(phoneNumber)) {
							latestBill = bill;
							break;
						}
					}
					if (latestBill != null) {
						custName.setText(latestBill.getCUSTOMER_NAME());
						custName.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custAddress.setText(latestBill.getCUSTOMER_ADDRESS());
						custAddress.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custHeir.setText(latestBill.getHEIR());
						custHeir.setForeground(new Color(247, 25, 92)); // Change text color when typing
						heirRelation.setSelectedItem(latestBill.getHEIR_RELATION());
						heirRelation.setForeground(new Color(247, 25, 92));

						// Balance Detail New Fix
						balanceBillNum.setText(latestBill.getBALANCE_BILLNUM());
						balanceBillNum.setForeground(new Color(247, 25, 92)); // Change text color when typing
						balanceproductDetails.setText(latestBill.getBALANCE_PRODUCT());
						balanceproductDetails.setForeground(new Color(247, 25, 92)); // Change text color when typing
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String BALANCEDATEFORMATTED = "";
						if (null != latestBill.getBALANCE_DATE()) {
							BALANCEDATEFORMATTED = sdf.format(latestBill.getBALANCE_DATE());
						}
						if (BALANCEDATEFORMATTED != null && !BALANCEDATEFORMATTED.isEmpty()) {
							try {
								Date parsedDate = sdf.parse(BALANCEDATEFORMATTED);
								balanceDate.setDate(parsedDate);
							} catch (ParseException e1) {
								e1.printStackTrace(); // Or handle the error as needed
							}
						}
					} else {

					}
				}
			}
		});

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (custName.getText().length() >= 1) {
						String custNAME = custName.getText().trim();
						AdaguBill latestBill = null;
						for (int i = AdaguBillListAdaguPanel.size() - 1; i >= 0; i--) {
							AdaguBill bill = AdaguBillListAdaguPanel.get(i);
							if (bill.getCUSTOMER_NAME().equals(custNAME)) {
								latestBill = bill;
								break;
							}
						}
						if (latestBill != null) {
							custPhone.setText(latestBill.getCUSTOMER_PHONE());
							custPhone.setForeground(new Color(247, 25, 92)); // Change text color when typing
							custAddress.setText(latestBill.getCUSTOMER_ADDRESS());
							custAddress.setForeground(new Color(247, 25, 92)); // Change text color when typing
							custHeir.setText(latestBill.getHEIR());
							custHeir.setForeground(new Color(247, 25, 92)); // Change text color when typing
							heirRelation.setSelectedItem(latestBill.getHEIR_RELATION());
							heirRelation.setForeground(new Color(247, 25, 92));
						} else {

						}
					}
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				String LICENCENUM = LICENCENO.getText().toString().trim();
				BigDecimal maxValue = loadData.getMAXValueDummy(LICENCENUM);
				maxValue = maxValue.add(BigDecimal.ONE);
				if (adaguAmt.getText().length() > 0) {
					int adaguamt = Integer.parseInt(adaguAmt.getText().toString());
					int actvalue = adaguamt + 100;
					if (adaguamt > 0) {
						productValue.setText("" + actvalue);
						productValue.setForeground(new Color(247, 25, 92)); // Change text color when typing
						adaguActualAmt.setText("" + actvalue);
						adaguActualAmt.setForeground(new Color(247, 25, 92)); // Change text color when typing
					}
				}
				billNum.setText("" + maxValue.setScale(0, RoundingMode.HALF_UP));
				billNum.setForeground(new Color(247, 25, 92)); // Change text color when
				lockerName.setText("");
				lockerBillNumber.setText("");

				// Balance Detail New Fix
				balanceBillNum.setText("");
				balanceproductDetails.setText("");
			}
		});

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		selectedProductsModel = new DefaultListModel<>();
		selectedProductsList = new JList<>(selectedProductsModel);
		selectedProductsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedProductsList.setFont(new Font("Arial", Font.PLAIN, 14));
		selectedProductsList.setVisibleRowCount(5);
		selectedProductsList.setForeground(new Color(247, 25, 92)); // Change text color when typing

		JScrollPane scrollPane = new JScrollPane(selectedProductsList);
		scrollPane.setPreferredSize(new Dimension(200, 50));
		scrollPane.setBorder(border);
		scrollPane.setSize(100, 50);
		scrollPane.setPreferredSize(getMaximumSize());
		// panel.add(scrollPane);
		selectedProductsList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					String selectedProduct = selectedProductsList.getSelectedValue();
					if (selectedProduct != null) {
						selectedProductsModel.removeElement(selectedProduct);
					}
				}
			}
		});

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);

		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		shopNamePanel.add(shopName, BorderLayout.CENTER);
		shopNamePanel.add(licNoUrlLabel, BorderLayout.WEST);

		URL notifyUrl = TextFieldWithIcon.class.getResource("/images/mail.png");
		ImageIcon notifyicon = new ImageIcon(notifyUrl);
		Image notifyimage = notifyicon.getImage();
		Image notifyImage = notifyimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon notifyIcon = new ImageIcon(notifyImage);
		JLabel notifyLabel = new JLabel(notifyIcon);

		URL printUrl = TextFieldWithIcon.class.getResource("/images/printer.png");
		ImageIcon printicon = new ImageIcon(printUrl);
		Image printimage = printicon.getImage();
		Image printImage = printimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon printIcon = new ImageIcon(printImage);
		JLabel printLabel = new JLabel(printIcon);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 2));
		rightPanel.add(notifyLabel);
		rightPanel.add(printLabel);
		shopNamePanel.add(rightPanel, BorderLayout.LINE_END);

		panel.add(shopNamePanel, gbc);

		notifyLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				StringBuilder reminderMessage = new StringBuilder();
				reminderMessage.append(" அறிவிப்பு: ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
						+ " அடகு வைத்துளிர்கள்,  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" Bill Number  : " + BILLNUM);
				reminderMessage.append(" \n ");
				reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
				reminderMessage.append(" \n ");
				reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
						+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage
						.append(" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நன்றி! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
				reminderMessage.append(" \n ");

				StringBuilder displayMessage = new StringBuilder();
				displayMessage.append(" Bill Number  : " + BILLNUM);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Name : " + CUSTNAME);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Phone : " + CUSTPHONE);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Address : " + CUSTADDRESS);
				displayMessage.append(" \n");
				displayMessage.append(
						" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : " + REDEMPTIONDATEFORMATTED);
				displayMessage.append(" \n");
				displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
				displayMessage.append(" \n");

				// Create JTextArea for custom popup display
				JTextArea reminderArea = new JTextArea(displayMessage.toString());
				reminderArea.setEditable(false); // Make text area non-editable
				reminderArea.setWrapStyleWord(true); // Word wrapping
				reminderArea.setLineWrap(true);
				reminderArea.setCaretPosition(0); // Set cursor at the top
				reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
				reminderArea.setPreferredSize(new Dimension(400, 200));

				int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Copy" },
						"Copy");

				if (choice == 0) {
					String message = "" + reminderMessage;
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

			}
		});

		printLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				if (LICENCENUM.equals("*****")) {
					PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
							CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					//27Oct2025
					String PRODUCTTYPE = "";
					PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
					print.setPRODUCT_TYPE(PRODUCTTYPE);
					//27Oct2025
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printForm(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE,
							CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					//27Oct2025
					String PRODUCTTYPE = "";
					PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
					print.setPRODUCT_TYPE(PRODUCTTYPE);
					//27Oct2025
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printFormLic(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					LICENCENO.setText(" 16/21-22 ");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					LICENCENO.setText("*****");
				}
				isFirstImage = !isFirstImage;
			}
		});

		URL custPhoneUrl = TextFieldWithIcon.class.getResource("/images/telephone.png");
		ImageIcon custPhoneicon = new ImageIcon(custPhoneUrl);
		Image custPhoneimage = custPhoneicon.getImage();
		Image custPhoneImage = custPhoneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custPhoneIcon = new ImageIcon(custPhoneImage);
		JPanel custPhonePanel = new JPanel(new BorderLayout());
		JLabel custPhoneLabel = new JLabel(custPhoneIcon);
		custPhonePanel.add(custPhoneLabel, BorderLayout.WEST);
		JPanel Panel4 = new JPanel(new GridLayout(1, 2));
		Panel4.add(custPhone);
		Panel4.add(new JLabel(""));
		custPhonePanel.add(Panel4, BorderLayout.CENTER);
		panel.add(custPhonePanel, gbc);

		URL billUrl = TextFieldWithIcon.class.getResource("/images/bill.png");
		ImageIcon billicon = new ImageIcon(billUrl);
		Image billimage = billicon.getImage();
		Image billImage = billimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon billIcon = new ImageIcon(billImage);
		JPanel billPanel = new JPanel(new BorderLayout());
		JLabel billLabel = new JLabel(billIcon);
		billPanel.add(billLabel, BorderLayout.WEST);
		JPanel Panel1 = new JPanel(new GridLayout(1, 2));
		Panel1.add(billNum);
		Panel1.add(new JLabel(""));
		billPanel.add(Panel1, BorderLayout.CENTER);
		panel.add(billPanel, gbc);

		URL adagudateUrl = TextFieldWithIcon.class.getResource("/images/adaguDate.png");
		ImageIcon adagudateicon = new ImageIcon(adagudateUrl);
		Image adagudateimage = adagudateicon.getImage();
		Image adagudateImage = adagudateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaudateIcon = new ImageIcon(adagudateImage);
		JPanel adagudatePanel = new JPanel(new BorderLayout());
		JLabel adagudateLabel = new JLabel(adaudateIcon);
		adagudatePanel.add(adagudateLabel, BorderLayout.WEST);
		JPanel Panel2 = new JPanel(new GridLayout(1, 2));
		Panel2.add(adaguDate);
		Panel2.add(new JLabel(""));
		adagudatePanel.add(Panel2, BorderLayout.CENTER);
		panel.add(adagudatePanel, gbc);

		URL adaguAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguAmticon = new ImageIcon(adaguAmtUrl);
		Image adaguAmtimage = adaguAmticon.getImage();
		Image adaguAmtImage = adaguAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguAmtIcon = new ImageIcon(adaguAmtImage);
		JPanel adaguAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguAmtLabel = new JLabel(adaguAmtIcon);
		adaguAmtPanel.add(adaguAmtLabel, BorderLayout.WEST);
		JPanel Panel3 = new JPanel(new GridLayout(1, 2));
		Panel3.add(adaguAmt);
		Panel3.add(new JLabel(""));
		adaguAmtPanel.add(Panel3, BorderLayout.CENTER);
		panel.add(adaguAmtPanel, gbc);

		URL custNameUrl = TextFieldWithIcon.class.getResource("/images/label.png");
		ImageIcon custNameicon = new ImageIcon(custNameUrl);
		Image custNameimage = custNameicon.getImage();
		Image custNameImage = custNameimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custNameIcon = new ImageIcon(custNameImage);
		JPanel custNamePanel = new JPanel(new BorderLayout());
		JLabel custNameLabel = new JLabel(custNameIcon);
		custNamePanel.add(custNameLabel, BorderLayout.WEST);
		JPanel Panel5 = new JPanel(new GridLayout(1, 2));
		Panel5.add(custName);
		Panel5.add(new JLabel(""));
		custNamePanel.add(Panel5, BorderLayout.CENTER);
		panel.add(custNamePanel, gbc);

		heirRelation.setSelectedIndex(0);
		URL custHeirUrl = TextFieldWithIcon.class.getResource("/images/parent.png");
		ImageIcon custHeiricon = new ImageIcon(custHeirUrl);
		Image custHeirimage = custHeiricon.getImage();
		Image custHeirImage = custHeirimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custHeirIcon = new ImageIcon(custHeirImage);
		JPanel heirRelationPanel = new JPanel(new GridLayout(1, 3));
		heirRelationPanel.add(heirRelation);
		heirRelationPanel.add(custHeir);
		heirRelationPanel.add(new JLabel(""));
		heirRelationPanel.add(new JLabel(""));
		JPanel custHeirPanel = new JPanel(new BorderLayout());
		JLabel custHeirLabel = new JLabel(custHeirIcon);
		custHeirPanel.add(custHeirLabel, BorderLayout.WEST);
		custHeirPanel.add(heirRelationPanel, BorderLayout.CENTER);
		panel.add(custHeirPanel, gbc);

		URL custAddressUrl = TextFieldWithIcon.class.getResource("/images/gps.png");
		ImageIcon custAddressicon = new ImageIcon(custAddressUrl);
		Image custAddressimage = custAddressicon.getImage();
		Image custAddressImage = custAddressimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custAddressIcon = new ImageIcon(custAddressImage);
		JPanel custAddressPanel = new JPanel(new BorderLayout());
		JLabel custAddressLabel = new JLabel(custAddressIcon);
		custAddressPanel.add(custAddressLabel, BorderLayout.WEST);
		JPanel Panel6 = new JPanel(new GridLayout(1, 2));
		Panel6.add(custAddress);
		Panel6.add(new JLabel(""));
		custAddressPanel.add(Panel6, BorderLayout.CENTER);
		panel.add(custAddressPanel, gbc);

		/*
		 * URL custHeirUrl = TextFieldWithIcon.class.getResource("/images/parent.png");
		 * ImageIcon custHeiricon = new ImageIcon(custHeirUrl); Image custHeirimage =
		 * custHeiricon.getImage(); Image custHeirImage =
		 * custHeirimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH); ImageIcon
		 * custHeirIcon = new ImageIcon(custHeirImage); JPanel custHeirPanel = new
		 * JPanel(new BorderLayout()); JLabel custHeirLabel = new JLabel(custHeirIcon);
		 * custHeirPanel.add(custHeirLabel, BorderLayout.WEST);
		 * custHeirPanel.add(heirRelation, BorderLayout.EAST);
		 * custHeirPanel.add(custHeir, BorderLayout.CENTER); panel.add(custHeirPanel,
		 * gbc);
		 */

		URL adaguActualAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguActualAmticon = new ImageIcon(adaguActualAmtUrl);
		Image adaguActualAmtimage = adaguActualAmticon.getImage();
		Image adaguActualAmtImage = adaguActualAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguActualAmtIcon = new ImageIcon(adaguActualAmtImage);
		JPanel adaguActualAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguActualAmtLabel = new JLabel(adaguActualAmtIcon);
		adaguActualAmtPanel.add(adaguActualAmtLabel, BorderLayout.WEST);
		JPanel Panel7 = new JPanel(new GridLayout(1, 2));
		Panel7.add(adaguActualAmt);
		Panel7.add(new JLabel(""));
		adaguActualAmtPanel.add(Panel7, BorderLayout.CENTER);
		panel.add(adaguActualAmtPanel, gbc);

		/*
		 * URL productDetailsUrl =
		 * TextFieldWithIcon.class.getResource("/images/product.png"); ImageIcon
		 * productDetailsicon = new ImageIcon(productDetailsUrl); Image
		 * productDetailsimage = productDetailsicon.getImage(); Image
		 * productDetailsImage = productDetailsimage.getScaledInstance(40, 40,
		 * Image.SCALE_SMOOTH); ImageIcon productDetailsIcon = new
		 * ImageIcon(productDetailsImage); JPanel productDetailsPanel = new JPanel(new
		 * BorderLayout()); JLabel productDetailsLabel = new JLabel(productDetailsIcon);
		 * productDetailsPanel.add(productDetailsLabel, BorderLayout.WEST);
		 * productTypes.setSelectedIndex(0); productDetailsPanel.add(productTypes,
		 * BorderLayout.EAST); productDetailsPanel.add(productDetails,
		 * BorderLayout.CENTER); panel.add(productDetailsPanel, gbc);
		 */

		URL productDetailsUrl = TextFieldWithIcon.class.getResource("/images/product.png");
		ImageIcon productDetailsicon = new ImageIcon(productDetailsUrl);
		Image productDetailsimage = productDetailsicon.getImage();
		Image productDetailsImage = productDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productDetailsIcon = new ImageIcon(productDetailsImage);
		JPanel productDetailsPanel = new JPanel(new GridLayout(1, 3));
		JLabel productDetailsLabel = new JLabel(productDetailsIcon);
		productDetailsPanel.add(productTypes);
		productDetailsPanel.add(productDetails);
		productDetailsPanel.add(new JLabel(""));
		productDetailsPanel.add(new JLabel(""));
		JPanel productDetailsPanel1 = new JPanel(new BorderLayout());
		productDetailsPanel1.add(productDetailsLabel, BorderLayout.WEST);
		productDetailsPanel1.add(productDetailsPanel, BorderLayout.CENTER);
		panel.add(productDetailsPanel1, gbc);

		URL scrollPaneUrl = TextFieldWithIcon.class.getResource("/images/scroll.png");
		ImageIcon scrollPaneicon = new ImageIcon(scrollPaneUrl);
		Image scrollPaneimage = scrollPaneicon.getImage();
		Image scrollPaneImage = scrollPaneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon scrollPaneIcon = new ImageIcon(scrollPaneImage);
		JPanel scrollPanePanel = new JPanel(new BorderLayout());
		JLabel scrollPaneLabel = new JLabel(scrollPaneIcon);
		scrollPanePanel.add(scrollPaneLabel, BorderLayout.WEST);
		JPanel Panel8 = new JPanel(new GridLayout(1, 2));
		Panel8.add(scrollPane);
		Panel8.add(new JLabel(""));
		scrollPanePanel.add(Panel8, BorderLayout.CENTER);
		panel.add(scrollPanePanel, gbc);

		URL productWeightUrl = TextFieldWithIcon.class.getResource("/images/weight.png");
		ImageIcon productWeighticon = new ImageIcon(productWeightUrl);
		Image productWeightimage = productWeighticon.getImage();
		Image productWeightImage = productWeightimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productWeightIcon = new ImageIcon(productWeightImage);
		JPanel productWeightPanel = new JPanel(new BorderLayout());
		JLabel productWeightLabel = new JLabel(productWeightIcon);
		productWeightPanel.add(productWeightLabel, BorderLayout.WEST);
		JPanel Panel9 = new JPanel(new GridLayout(1, 2));
		Panel9.add(productWeight);
		Panel9.add(new JLabel(""));
		productWeightPanel.add(Panel9, BorderLayout.CENTER);
		panel.add(productWeightPanel, gbc);

		URL productValueUrl = TextFieldWithIcon.class.getResource("/images/value.png");
		ImageIcon productValueicon = new ImageIcon(productValueUrl);
		Image productValueimage = productValueicon.getImage();
		Image productValueImage = productValueimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productValueIcon = new ImageIcon(productValueImage);
		JPanel productValuePanel = new JPanel(new BorderLayout());
		JLabel productValueLabel = new JLabel(productValueIcon);
		productValuePanel.add(productValueLabel, BorderLayout.WEST);
		JPanel Panel10 = new JPanel(new GridLayout(1, 2));
		Panel10.add(productValue);
		Panel10.add(new JLabel(""));
		productValuePanel.add(Panel10, BorderLayout.CENTER);
		panel.add(productValuePanel, gbc);

		URL redemptionDateUrl = TextFieldWithIcon.class.getResource("/images/expiry.png");
		ImageIcon redemptionDateicon = new ImageIcon(redemptionDateUrl);
		Image redemptionDateimage = redemptionDateicon.getImage();
		Image redemptionDateImage = redemptionDateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon redemptionDateIcon = new ImageIcon(redemptionDateImage);
		JPanel redemptionDatePanel = new JPanel(new BorderLayout());
		JLabel redemptionDateLabel = new JLabel(redemptionDateIcon);
		redemptionDatePanel.add(redemptionDateLabel, BorderLayout.WEST);
		status.setSelectedIndex(0);
		redemptionDatePanel.add(status, BorderLayout.EAST);
		JPanel redemptionDatesPanel = new JPanel(new GridLayout(1, 3));
		redemptionDatesPanel.add(redemptionDate);
		redemptionDatesPanel.add(status);
		redemptionDatesPanel.add(cancelDate);
		redemptionDatesPanel.add(new JLabel(""));
		redemptionDatesPanel.add(new JLabel(""));
		redemptionDatesPanel.add(new JLabel(""));
		redemptionDatePanel.add(redemptionDatesPanel, BorderLayout.CENTER);
		panel.add(redemptionDatePanel, gbc);

		// Locker Detail New Fix
		URL lockerDetailsUrl = TextFieldWithIcon.class.getResource("/images/lock.png");
		ImageIcon lockerDetailsicon = new ImageIcon(lockerDetailsUrl);
		Image lockerDetailsimage = lockerDetailsicon.getImage();
		Image lockerDetailsImage = lockerDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon lockerDetailsIcon = new ImageIcon(lockerDetailsImage);
		JPanel lockerDetailsPanel = new JPanel(new BorderLayout());
		JLabel lockerDetailsLabel = new JLabel(lockerDetailsIcon);
		lockerDetailsPanel.add(lockerDetailsLabel, BorderLayout.WEST);
		JPanel lockerDetailSPanel = new JPanel(new GridLayout(1, 3));
		lockerDetailSPanel.add(lockerName);
		lockerDetailSPanel.add(lockerBillNumber);
		lockerDetailSPanel.add(lockerDate);
		lockerDetailSPanel.add(new JLabel(""));
		lockerDetailSPanel.add(new JLabel(""));
		lockerDetailSPanel.add(new JLabel(""));
		lockerDetailsPanel.add(lockerDetailSPanel, BorderLayout.CENTER);
		panel.add(lockerDetailsPanel, gbc);

		// Balance Detail New Fix
		URL balanceDetailsUrl = TextFieldWithIcon.class.getResource("/images/balance.png");
		ImageIcon balanceDetailsicon = new ImageIcon(balanceDetailsUrl);
		Image balanceDetailsimage = balanceDetailsicon.getImage();
		Image balanceDetailsImage = balanceDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon balanceDetailsIcon = new ImageIcon(balanceDetailsImage);
		JPanel balanceDetailsPanel = new JPanel(new BorderLayout());
		JLabel balanceDetailsLabel = new JLabel(balanceDetailsIcon);
		balanceDetailsPanel.add(balanceDetailsLabel, BorderLayout.WEST);
		JPanel balanceDetailSPanel = new JPanel(new GridLayout(1, 3));
		balanceDetailSPanel.add(balanceBillNum);
		balanceDetailSPanel.add(balanceproductDetails);
		balanceDetailSPanel.add(balanceDate);
		balanceDetailSPanel.add(new JLabel(""));
		balanceDetailSPanel.add(new JLabel(""));
		balanceDetailSPanel.add(new JLabel(""));
		balanceDetailsPanel.add(balanceDetailSPanel, BorderLayout.CENTER);
		panel.add(balanceDetailsPanel, gbc);

		// panel.add(saveBill);
		// panel.add(printBill);
		// panel.add(notifyBill);
		// panel.add(clearBill);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));
		bottomPanel.add(saveBill);
		bottomPanel.add(clearBill);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		JPanel bottomPanel1 = new JPanel(new BorderLayout());
		bottomPanel1.add(bottomPanel, BorderLayout.CENTER);
		panel.add(bottomPanel1, gbc);

		clearBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBill.setEnabled(true);
				billNum.setEditable(true);
				billNum.setText("Bill Num");
				billNum.setForeground(new Color(157, 161, 250));
				adaguAmt.setEditable(true);
				adaguAmt.setText("Amount");
				adaguAmt.setForeground(new Color(157, 161, 250));
				custPhone.setEditable(true);
				custPhone.setText("Phone");
				custPhone.setForeground(new Color(157, 161, 250));
				custName.setEditable(true);
				custName.setText("Name");
				custName.setForeground(new Color(157, 161, 250));
				custAddress.setEditable(true);
				custAddress.setText("Address");
				custAddress.setForeground(new Color(157, 161, 250));
				custHeir.setEditable(true);
				custHeir.setText("Heir");
				custHeir.setForeground(new Color(157, 161, 250));
				heirRelation.setSelectedIndex(0);
				heirRelation.setEditable(true);
				productTypes.setSelectedIndex(0);
				productTypes.setEditable(true);
				adaguActualAmt.setEditable(true);
				adaguActualAmt.setText("Actual Amount");
				adaguActualAmt.setForeground(new Color(157, 161, 250));
				productWeight.setEditable(true);
				productWeight.setText("Weight");
				productWeight.setForeground(new Color(157, 161, 250));
				productValue.setEditable(true);
				productValue.setText("Value");
				productValue.setForeground(new Color(157, 161, 250));
				productDetails.setEditable(true);
				productDetails.setText("Products");
				productDetails.setForeground(new Color(157, 161, 250));
				selectedProductsModel.clear();
				adaguDate.setDate(new java.util.Date()); // Set default system date
				cancelDate.setDate(null);

				// Locker Detail New Fix
				lockerName.setEditable(true);
				lockerName.setText("Locker Name");
				lockerName.setForeground(new Color(157, 161, 250));
				lockerBillNumber.setEditable(true);
				lockerBillNumber.setText("Locker Bill Num");
				lockerBillNumber.setForeground(new Color(157, 161, 250));
				lockerDate.setDate(null);

				// Balance Detail New Fix
				balanceBillNum.setEditable(true);
				balanceBillNum.setText("Balance Bill Num");
				balanceBillNum.setForeground(new Color(157, 161, 250));
				balanceproductDetails.setEditable(true);
				balanceproductDetails.setText("Balance Product");
				balanceproductDetails.setForeground(new Color(157, 161, 250));
				balanceDate.setDate(null);
			}
		});

		productDetails.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = productDetails.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				String PRODUCTTYPE = productTypes.getSelectedItem().toString();
				if (PRODUCTTYPE.equals("Gold")) {
					int i = 0;
					for (String product : productGoldList) {
						if (product.toLowerCase().startsWith(typedText) && i <= 15) {
							suggestions.add(product);
							i++;
						}
					}
				} else if (PRODUCTTYPE.equals("Silver")) {
					int j = 0;
					for (String product : productSilverList) {
						if (product.toLowerCase().startsWith(typedText) && j <= 15) {
							suggestions.add(product);
							j++;
						}
					}
				}

				// Show suggestions in a combo box if any
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									int qty = 0;
									String qtyString = JOptionPane.showInputDialog(null, "" + suggestion + " : ",
											"Quantity Input", JOptionPane.QUESTION_MESSAGE);
									if (qtyString != null && !qtyString.trim().isEmpty()) {
										try {
											int quantity = Integer.parseInt(qtyString);
											if (quantity > 0) {
												qty = quantity;
											} else {
												JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.",
														"Error", JOptionPane.ERROR_MESSAGE);
												qty = 0;
											}
										} catch (NumberFormatException ex) {
											JOptionPane.showMessageDialog(null, "Please enter a valid number.",
													"Invalid Input", JOptionPane.ERROR_MESSAGE);
											qty = 0;
										}
									} else {
										JOptionPane.showMessageDialog(null, "Quantity is required.", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
//									selectedProductsModel.addElement(suggestion + " - " + qty);
									//selectedProductsModel.addElement(PRODUCTTYPE + " - " + suggestion + " - " + qty); // 23July2025
									selectedProductsModel.addElement(suggestion + "-" + qty); //27Oct2025
									productDetails.setText(""); // Clear text field after selection
								}
								// Hide the popup after selection
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(productDetails, 0, productDetails.getHeight());

				}
			}

		});

		billNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

				DBConnect billNumCheck = new DBConnect();
				boolean isDuplicateBillNum = billNumCheck.checkbillNum(billNum.getText().trim().toString());
				if (isDuplicateBillNum) {
					billNum.setText("0");
					JOptionPane.showMessageDialog(null, "Bill Num Already Exists Kindly Check", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		adaguActualAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});

		productWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = productWeight.getText().toString().trim();
				/*
				 * if (!Character.isDigit(c) && c != '.' || (c == '.' && text.contains("."))) {
				 * e.consume(); }
				 */
				if ((!Character.isDigit(c) && c != '.' && c != '/') || (c == '/' && text.split("/+").length > 1)) {
					e.consume();
				}
			}

			// 28Apr2025 - Start
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String proWgt = productWeight.getText().trim();
					try {
						double weight = Double.parseDouble(proWgt);
						String formattedWeight = String.format("%.3f", weight);
						productWeight.setText(formattedWeight);
					} catch (NumberFormatException ex) {
						if (proWgt.isEmpty()) {
							productWeight.setText("");
						}
					}
				}
			}
			// 28Apr2025 - End
		});

		productValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custName.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterCustomerList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custName.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custName, 0, custName.getHeight());
				}
			}
		});

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custAddress.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterAddressList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custAddress.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custAddress, 0, custAddress.getHeight());
				}
			}
		});

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custHeir.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterHeirList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custHeir.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custHeir, 0, custHeir.getHeight());
				}
			}
		});

		// Locker Detail New Fix
		lockerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = lockerName.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterLockerList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									lockerName.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(lockerName, 0, lockerName.getHeight());
				}
			}
		});

		saveBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				String PRODUCTTYPE = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "", CANCELDATEFORMATTED = "";
				String STATUS = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				// Locker Detail New Fix
				String LOCKERNAME = "";
				String LOCKERBILLNUMBER = "";
				String LOCKERDATEFORMATTED = "";

				// Balance Detail New Fix
				String BALANCEPRODUCT = "";
				String BALANCEBILLNUMBER = "";
				String BALANCEDATEFORMATTED = "";

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}
				//16Sep2025
				if (null != billNum.getText().toString().trim()) {
					DBConnect billNumCheck = new DBConnect();
					boolean isDuplicateBillNum = billNumCheck.checkbillNum(billNum.getText().trim().toString());
					if (isDuplicateBillNum) {
						billNum.setText("0");
						JOptionPane.showConfirmDialog(panel, " Bill Num Already Exists Kindly Check !!! ");
						error = true;
						return;
					}
				}
				//16Sep2025

				LICENCENUM = LICENCENO.getText().toString().trim();
				BILLNUM = billNum.getText().toString().trim();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString().trim());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString().trim());
				CUSTNAME = custName.getText().toString().trim();
				CUSTADDRESS = custAddress.getText().toString().trim();
				CUSTHEIR = custHeir.getText().toString().trim();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString().trim();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString().trim());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString().trim());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				if (null != cancelDate.getDate()) {
					CANCELDATEFORMATTED = sdf.format(cancelDate.getDate());
				}
				PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
				STATUS = status.getSelectedItem().toString().trim();
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + "~");
				}

				// Locker Detail New Fix
				LOCKERNAME = lockerName.getText().toString().trim().toUpperCase();
				LOCKERBILLNUMBER = lockerBillNumber.getText().toString().trim().toUpperCase();
				if (null != lockerDate.getDate()) {
					LOCKERDATEFORMATTED = sdf.format(lockerDate.getDate());
				}

				// Balance Detail New Fix
				BALANCEPRODUCT = balanceproductDetails.getText().toString().trim().toUpperCase();
				BALANCEBILLNUMBER = balanceBillNum.getText().toString().trim().toUpperCase();
				if (null != balanceDate.getDate()) {
					BALANCEDATEFORMATTED = sdf.format(balanceDate.getDate());
				}

				AdaguBill adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(LICENCENUM);
				adaguData.setBILL_NUMBER(BILLNUM);
				adaguData.setCUSTOMER_NAME(CUSTNAME);
				adaguData.setCUSTOMER_PHONE("" + CUSTPHONE);
				adaguData.setHEIR_RELATION(CUSTHEIRRELATION);
				adaguData.setHEIR(CUSTHEIR);
				adaguData.setCUSTOMER_ADDRESS(CUSTADDRESS);
				adaguData.setPRODUCTS(PRODUCT.toString().trim());
				adaguData.setPRODUCT_WEIGHT("" + PRODUCTWEIGTH);
				adaguData.setADAGU_AMOUNT("" + ADAGUAMT);
				adaguData.setACTUAL_ADAGU_AMOUNT("" + ADAGUACTUALAMT);
				adaguData.setPRODUCT_VALUE("" + PRODUCTVALUE);
				adaguData.setADAGU_DATE(ADAGUDATEFORMATTED);
				adaguData.setREDEMPTION_DATE(REDEMPTIONDATEFORMATTED);
				adaguData.setCANCEL_DATE(CANCELDATEFORMATTED);
				adaguData.setPRODUCT_TYPE(PRODUCTTYPE);
				adaguData.setBILL_TYPE("Adagu Bill");

				// Locker Detail New Fix
				adaguData.setLOCKER_NAME(LOCKERNAME);
				adaguData.setLOCKER_BILLNUM(LOCKERBILLNUMBER);
				adaguData.setLOCKER_DATE(LOCKERDATEFORMATTED);

				// Balance Detail New Fix
				adaguData.setBALANCE_BILLNUM(BALANCEBILLNUMBER);
				adaguData.setBALANCE_PRODUCT(BALANCEPRODUCT);
				adaguData.setBALANCE_DATE(BALANCEDATEFORMATTED);

				if (STATUS.equals("Paid")) {
					// adaguData.setREDEMPTION_DATE(""+new SimpleDateFormat("dd-MM-yyyy").format(new
					// Date()));
					adaguData.setSTATUS("Paid");
				} else {
					adaguData.setSTATUS("Pending");
				}

				DBConnect saveAdagu = new DBConnect();
				boolean isSaved = false;
				isSaved = saveAdagu.saveAdaguBill(adaguData);

				if (isSaved) {
					JOptionPane.showConfirmDialog(panel, " Bill Saved Successfully !!! ");
					saveBill.setEnabled(false);
				} else {
					JOptionPane.showConfirmDialog(panel, " Error : Save Bill !!! ");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				// Locker Detail New Fix
				lockerName.setEditable(false);
				lockerName.setBackground(Color.LIGHT_GRAY);
				lockerName.setBorder(correctborder);
				lockerName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				lockerBillNumber.setEditable(false);
				lockerBillNumber.setBackground(Color.LIGHT_GRAY);
				lockerBillNumber.setBorder(correctborder);
				lockerBillNumber
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);

				// Balance Detail New Fix
				balanceBillNum.setEditable(false);
				balanceBillNum.setBackground(Color.LIGHT_GRAY);
				balanceBillNum.setBorder(correctborder);
				balanceBillNum
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				balanceproductDetails.setEditable(false);
				balanceproductDetails.setBackground(Color.LIGHT_GRAY);
				balanceproductDetails.setBorder(correctborder);
				balanceproductDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

			}
		});

		setVisible(true);

		return panel;
	}

	// Panel for Add Adagu Bill
	private JPanel createAddAdaguBillPanel() {
		JPanel panel;
		JTextField billNum, adaguAmt, custName, custPhone, custHeir, adaguActualAmt, productDetails, productWeight,
				productValue, custAddress;
		JLabel shopName;
		JButton saveBill, notifyBill, printBill, clearBill;
		JDateChooser adaguDate, redemptionDate;
		DefaultListModel<String> selectedProductsModel;
		JList<String> selectedProductsList;
		JTextField LICENCENO; // = " 16/21-22 "

		// Simulating a product list (in a real-world scenario, this could be from a
		// database)
		/*
		 * ArrayList<String> productList = new ArrayList<>(Arrays.asList("2 LINE CHINE",
		 * "ADDAGAI ,STONE TEEKA", "BABY RING", "BANGLES", "BASER", "BOX RING",
		 * "BRACELET", "BROCH", "BUTTON TOPS", "BUTTON TOPS THONGAL", "CAST DROPS",
		 * "CASTING RING", "CASTING TOPS", "CHAIN", "CHAIN WITH DOLLAR", "DELHI CHAIN",
		 * "DELHI SUTTU MATTLE", "DIE COIN", "DOLLAR", "FANCE HARAM", "FANCE NECKLES",
		 * "FANCY AARAM", "FANCY DROPS", "FANCY JUMKI", "FANCY MATTLE", "FANCY NECKLES",
		 * "FANCY RING", "FANCY TOPS", "FANCY TOPS JIMUKI", "GALSER", "GENTS RING",
		 * "GUNDU WT LOCK", "HARAM", "JABKA", "JUMIKI", "JUMIKI TOPS", "K TONGAL",
		 * "KALAN BASEAR", "KAMBI", "KAPPU", "KARI MANI CHAIN", "KASA DROPS",
		 * "KASA HARAM", "KASA JUMIKI", "KASA NC", "KASA NECKLES", "KASA RING",
		 * "KASA T0PS", "KASA THONGAL", "KASA TOPS JIMKI", "KASA VAALI", "KASU", "KOLA",
		 * "KUTHU STAR", "LADIES BRACELET", "LADIES RING", "LAKSHMI BOTTU",
		 * "LAKSHMI KASU", "LAKSHMI POTTU", "LAXMI", "LEAF BESAR", "LEAF TOPS",
		 * "MANG TIKKA", "MANGA", "MANGA KASU", "MANGA NECKLES", "MANGA TOPS", "MATTLE",
		 * "MOHAN MALA", "MOPE CHAIN", "MOTTU", "MOTTU TONGAL", "NECKLACE",
		 * "NELLORE STONE DROPS", "NELLORE STONE TOPS", "NELLORE STONE TOPS JUMKI",
		 * "NER MATTAL", "NETTI CHUTTI", "OWAL RING", "RETTAI SARAM CHAIN", "RING",
		 * "ROUND RING", "S CHAIN", "S CHAIN ATTIKAI STONE TIKA", "SALANGI MATTAL",
		 * "SET JUMIKI", "SIDE BESAR", "side ma", "side mattale", "SIDE MATTLE",
		 * "SILVER CAL CHAIN", "STONE ADDIGAI", "STONE BASER", "STONE BASER",
		 * "STONE BROUCH", "STONE CHITTI MOPE CHAIN", "STONE DOLLAR", "STONE DROPS",
		 * "STONE FANCY CHAIN", "STONE HARAM", "STONE JUMIKI", "STONE JUMIKI TOPS",
		 * "STONE KASU", "STONE MATTLE", "STONE MOPE", "STONE MOPE CHAIN",
		 * "STONE NALY RING", "STONE NECKLES", "STONE NETTI CHUTTI", "STONE PURAI",
		 * "STONE PURAI TOPS", "STONE RING", "STONE SET JUMKI", "STONE THONGAL",
		 * "STONE TOPS", "SUTTU MATTLE", "TALI", "THALI", "THONGAL", "TOKYO CHAIN",
		 * "TONDU MATTLE", "TONGAL", "TOPS", "TV RING", "URU", "VAALI", "VAALI THONGAL",
		 * "VALAISEP", "VALAIYAM", "VISIRI STONE DROPS", "VISIRI STONE TOPS",
		 * "YANAI MUDI RING"));
		 */

		String[] heirRelationList = { "", "S/O", "W/O", "H/O", "D/O" };
		JComboBox<String> heirRelation = new JComboBox<>(heirRelationList);

		String[] statusList = { "", "Paid", "Pending" };
		JComboBox<String> status = new JComboBox<>(statusList);

		String[] productType = { "", "Gold", "Silver", "Gold & Silver" };
		JComboBox<String> productTypes = new JComboBox<>(productType);

		productTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				productGoldList = loadData.loadGoldData();
				productSilverList = loadData.loadSilverData();
			}
		});

		// Set up the JFrame
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		// setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to full screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());

		// Set a custom look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialize components with a larger font and bold text
		Font inputFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);

		shopName = new JLabel(" Sri Lalith Prasanna Jewellery - ADAGU BILL ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		billNum = new JTextFieldWithPlaceholder("Bill Num");
		LICENCENO = new JTextFieldWithPlaceholder("*****");
		adaguAmt = new JTextFieldWithPlaceholder("Amount");
		custName = new JTextFieldWithPlaceholder("Name");
		custPhone = new JTextFieldWithPlaceholder("Phone");
		custHeir = new JTextFieldWithPlaceholder("Heir");
		adaguActualAmt = new JTextFieldWithPlaceholder("Actual Amount");
		productDetails = new JTextFieldWithPlaceholder("Products");
		productWeight = new JTextFieldWithPlaceholder("Weight");
		productValue = new JTextFieldWithPlaceholder("Value");
		custAddress = new JTextFieldWithPlaceholder("Address");
		adaguDate = new JDateChooser();
		redemptionDate = new JDateChooser();

		billNum.setEditable(false);
		adaguDate.setEnabled(true);
		redemptionDate.setEnabled(true);
		adaguActualAmt.setEditable(false);
		productValue.setEditable(false);
		LineBorder border = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
		billNum.setBorder(border);
		billNum.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguAmt.setBorder(border);
		adaguAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custName.setBorder(border);
		custName.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custPhone.setBorder(border);
		custPhone.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custHeir.setBorder(border);
		custHeir.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguActualAmt.setBorder(border);
		adaguActualAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productDetails.setBorder(border);
		productDetails.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productWeight.setBorder(border);
		productWeight.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productValue.setBorder(border);
		productValue.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguDate.setBorder(border);
		redemptionDate.setBorder(border);
		custAddress.setBorder(border);
		custAddress.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		heirRelation.setBorder(border);
		heirRelation.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		status.setBorder(border);
		status.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productTypes.setBorder(border);
		productTypes.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));

		// Set custom font for input fields and text area
		billNum.setFont(inputFont);
		adaguAmt.setFont(inputFont);
		custName.setFont(inputFont);
		custPhone.setFont(inputFont);
		custHeir.setFont(inputFont);
		adaguActualAmt.setFont(inputFont);
		productDetails.setFont(inputFont);
		productWeight.setFont(inputFont);
		productValue.setFont(inputFont);
		custAddress.setFont(inputFont);
		heirRelation.setFont(inputFont);
		heirRelation.setPreferredSize(new Dimension(150, 50));
		status.setFont(inputFont);
		heirRelation.setPreferredSize(new Dimension(150, 50));
		status.setPreferredSize(new Dimension(150, 50));

		productTypes.setFont(inputFont);
		productTypes.setPreferredSize(new Dimension(150, 50));

		// Set button colors and styles
		saveBill = new JButton("Save");
		notifyBill = new JButton("Notify");
		printBill = new JButton("Print");
		clearBill = new JButton("New");

		saveBill.setBackground(new Color(56, 142, 60)); // Green
		saveBill.setForeground(Color.RED);
		notifyBill.setBackground(new Color(244, 67, 54)); // Red
		notifyBill.setForeground(Color.RED);
		printBill.setBackground(new Color(33, 150, 243)); // Blue
		printBill.setForeground(Color.RED);
		clearBill.setBackground(new Color(33, 150, 243)); // Blue
		clearBill.setForeground(Color.RED);

		saveBill.setFont(labelFont);
		notifyBill.setFont(labelFont);
		printBill.setFont(labelFont);
		clearBill.setFont(labelFont);

		adaguDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		adaguDate.setDate(new java.util.Date()); // Set default system date
		adaguDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		adaguDate.setFont(inputFont);

		redemptionDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 365);
		redemptionDate.setDate(calendar.getTime());
		redemptionDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		redemptionDate.setFont(inputFont);

		adaguDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = (Date) evt.getNewValue();
				if (selectedDate != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(selectedDate);
					calendar.add(Calendar.DAY_OF_YEAR, 365);
					redemptionDate.setDate(calendar.getTime());
				}
			}
		});

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem newItem = new JMenuItem("New");
		popupMenu.add(newItem);

		productDetails.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productString = JOptionPane.showInputDialog(null, "Product Name : ", "Quantity Input",
						JOptionPane.QUESTION_MESSAGE);
				if (productString != null && !productString.trim().isEmpty()) {
					try {
						String productType = productTypes.getSelectedItem().toString();
						if (productType.length() < 1) {
							JOptionPane.showMessageDialog(null, "Choose Either Gold or Silver", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);
						} else {
							DBConnect saveProd = new DBConnect();
							boolean isSaved = saveProd.saveNewProduct(productType, productString);
							if (isSaved) {
								JOptionPane.showMessageDialog(null, "Product Saved Succesfully!", "Success",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
										JOptionPane.ERROR_MESSAGE);

							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In product Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupAddressMenu = new JPopupMenu();
		JMenuItem newAddressItem = new JMenuItem("New");
		popupAddressMenu.add(newAddressItem);

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterAddressList = loadData.loadAddressData();
			}
		});

		custAddress.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newAddressItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custAddressString = JOptionPane.showInputDialog(null, "Customer Address", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custAddressString != null && !custAddressString.trim().isEmpty()) {
					try {
						DBConnect saveAddress = new DBConnect();
						boolean isSaved = saveAddress.saveNewAddress(custAddressString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Address Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupNameMenu = new JPopupMenu();
		JMenuItem newNameItem = new JMenuItem("New");
		popupNameMenu.add(newNameItem);

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterCustomerList = loadData.loadCustomerData();
			}
		});

		custName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newNameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Name", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewName(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Name Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupHeirMenu = new JPopupMenu();
		JMenuItem newHeirItem = new JMenuItem("New");
		popupHeirMenu.add(newHeirItem);

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterHeirList = loadData.loadCustomerHeirData();
			}
		});

		custHeir.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newHeirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Heir", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewHeir(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Heir Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();
				if (custPhone.getText().length() >= 10) {
					String phoneNumber = custPhone.getText().trim();
					AdaguBill latestBill = null;
					for (int i = AdaguBillListAdaguPanel.size() - 1; i >= 0; i--) {
						AdaguBill bill = AdaguBillListAdaguPanel.get(i);
						if (bill.getCUSTOMER_PHONE().equals(phoneNumber)) {
							latestBill = bill;
							break;
						}
					}
					if (latestBill != null) {
						custName.setText(latestBill.getCUSTOMER_NAME());
						custName.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custAddress.setText(latestBill.getCUSTOMER_ADDRESS());
						custAddress.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custHeir.setText(latestBill.getHEIR());
						custHeir.setForeground(new Color(247, 25, 92)); // Change text color when typing
						heirRelation.setSelectedItem(latestBill.getHEIR_RELATION());
						heirRelation.setForeground(new Color(247, 25, 92));
					} else {

					}
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();
				String LICENCENUM = LICENCENO.getText().trim().toString();
				BigDecimal maxValue = loadData.getMAXValue(LICENCENUM);
				maxValue = maxValue.add(BigDecimal.ONE);
				if (adaguAmt.getText().length() > 0) {
					int adaguamt = Integer.parseInt(adaguAmt.getText().toString());
					int actvalue = adaguamt + 100;
					if (adaguamt > 0) {
						productValue.setText("" + actvalue);
						productValue.setForeground(new Color(247, 25, 92)); // Change text color when typing
						adaguActualAmt.setText("" + actvalue);
						adaguActualAmt.setForeground(new Color(247, 25, 92)); // Change text color when typing
					}
				}
				billNum.setText("" + maxValue.setScale(0, RoundingMode.HALF_UP));
				billNum.setForeground(new Color(247, 25, 92)); // Change text color when typing
			}
		});

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		selectedProductsModel = new DefaultListModel<>();
		selectedProductsList = new JList<>(selectedProductsModel);
		selectedProductsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedProductsList.setFont(new Font("Arial", Font.PLAIN, 14));
		selectedProductsList.setVisibleRowCount(5);
		selectedProductsList.setForeground(new Color(247, 25, 92)); // Change text color when typing

		JScrollPane scrollPane = new JScrollPane(selectedProductsList);
		scrollPane.setPreferredSize(new Dimension(200, 50));
		scrollPane.setBorder(border);
		scrollPane.setSize(100, 50);
		scrollPane.setPreferredSize(getMaximumSize());
		// panel.add(scrollPane);
		selectedProductsList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					String selectedProduct = selectedProductsList.getSelectedValue();
					if (selectedProduct != null) {
						selectedProductsModel.removeElement(selectedProduct);
					}
				}
			}
		});

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);

		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		shopNamePanel.add(shopName, BorderLayout.CENTER);
		shopNamePanel.add(licNoUrlLabel, BorderLayout.WEST);

		URL notifyUrl = TextFieldWithIcon.class.getResource("/images/mail.png");
		ImageIcon notifyicon = new ImageIcon(notifyUrl);
		Image notifyimage = notifyicon.getImage();
		Image notifyImage = notifyimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon notifyIcon = new ImageIcon(notifyImage);
		JLabel notifyLabel = new JLabel(notifyIcon);

		URL printUrl = TextFieldWithIcon.class.getResource("/images/printer.png");
		ImageIcon printicon = new ImageIcon(printUrl);
		Image printimage = printicon.getImage();
		Image printImage = printimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon printIcon = new ImageIcon(printImage);
		JLabel printLabel = new JLabel(printIcon);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 2));
		rightPanel.add(notifyLabel);
		rightPanel.add(printLabel);
		shopNamePanel.add(rightPanel, BorderLayout.LINE_END);

		panel.add(shopNamePanel, gbc);

		notifyLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				StringBuilder reminderMessage = new StringBuilder();
				reminderMessage.append(" அறிவிப்பு: ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
						+ " அடகு வைத்துளிர்கள்,  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" Bill Number  : " + BILLNUM);
				reminderMessage.append(" \n ");
				reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
				reminderMessage.append(" \n ");
				reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
						+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage
						.append(" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நன்றி! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
				reminderMessage.append(" \n ");

				StringBuilder displayMessage = new StringBuilder();
				displayMessage.append(" Bill Number  : " + BILLNUM);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Name : " + CUSTNAME);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Phone : " + CUSTPHONE);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Address : " + CUSTADDRESS);
				displayMessage.append(" \n");
				displayMessage.append(
						" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : " + REDEMPTIONDATEFORMATTED);
				displayMessage.append(" \n");
				displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
				displayMessage.append(" \n");

				// Create JTextArea for custom popup display
				JTextArea reminderArea = new JTextArea(displayMessage.toString());
				reminderArea.setEditable(false); // Make text area non-editable
				reminderArea.setWrapStyleWord(true); // Word wrapping
				reminderArea.setLineWrap(true);
				reminderArea.setCaretPosition(0); // Set cursor at the top
				reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
				reminderArea.setPreferredSize(new Dimension(400, 200));

				int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Copy" },
						"Copy");

				if (choice == 0) {
					String message = "" + reminderMessage;
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				productTypes.setEditable(false);
				productTypes.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

			}
		});

		printLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				if (LICENCENUM.equals("*****")) {
					PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
							CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					//27Oct2025
					String PRODUCTTYPE = "";
					PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
					print.setPRODUCT_TYPE(PRODUCTTYPE);
					//27Oct2025
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printForm(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE,
							CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					//27Oct2025
					String PRODUCTTYPE = "";
					PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
					print.setPRODUCT_TYPE(PRODUCTTYPE);
					//27Oct2025
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printFormLic(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					LICENCENO.setText(" 16/21-22 ");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					LICENCENO.setText("*****");
				}
				isFirstImage = !isFirstImage;
			}
		});

		URL custPhoneUrl = TextFieldWithIcon.class.getResource("/images/telephone.png");
		ImageIcon custPhoneicon = new ImageIcon(custPhoneUrl);
		Image custPhoneimage = custPhoneicon.getImage();
		Image custPhoneImage = custPhoneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custPhoneIcon = new ImageIcon(custPhoneImage);
		JPanel custPhonePanel = new JPanel(new BorderLayout());
		JLabel custPhoneLabel = new JLabel(custPhoneIcon);
		custPhonePanel.add(custPhoneLabel, BorderLayout.WEST);
		JPanel Panel1 = new JPanel(new GridLayout(1, 2));
		Panel1.add(custPhone);
		Panel1.add(new JLabel(""));
		custPhonePanel.add(Panel1, BorderLayout.CENTER);
		panel.add(custPhonePanel, gbc);

		URL billUrl = TextFieldWithIcon.class.getResource("/images/bill.png");
		ImageIcon billicon = new ImageIcon(billUrl);
		Image billimage = billicon.getImage();
		Image billImage = billimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon billIcon = new ImageIcon(billImage);
		JPanel billPanel = new JPanel(new BorderLayout());
		JLabel billLabel = new JLabel(billIcon);
		billPanel.add(billLabel, BorderLayout.WEST);
		JPanel Panel2 = new JPanel(new GridLayout(1, 2));
		Panel2.add(billNum);
		Panel2.add(new JLabel(""));
		billPanel.add(Panel2, BorderLayout.CENTER);
		panel.add(billPanel, gbc);

		URL adagudateUrl = TextFieldWithIcon.class.getResource("/images/adaguDate.png");
		ImageIcon adagudateicon = new ImageIcon(adagudateUrl);
		Image adagudateimage = adagudateicon.getImage();
		Image adagudateImage = adagudateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaudateIcon = new ImageIcon(adagudateImage);
		JPanel adagudatePanel = new JPanel(new BorderLayout());
		JLabel adagudateLabel = new JLabel(adaudateIcon);
		adagudatePanel.add(adagudateLabel, BorderLayout.WEST);
		JPanel Panel3 = new JPanel(new GridLayout(1, 2));
		Panel3.add(adaguDate);
		Panel3.add(new JLabel(""));
		adagudatePanel.add(Panel3, BorderLayout.CENTER);
		panel.add(adagudatePanel, gbc);

		URL adaguAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguAmticon = new ImageIcon(adaguAmtUrl);
		Image adaguAmtimage = adaguAmticon.getImage();
		Image adaguAmtImage = adaguAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguAmtIcon = new ImageIcon(adaguAmtImage);
		JPanel adaguAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguAmtLabel = new JLabel(adaguAmtIcon);
		adaguAmtPanel.add(adaguAmtLabel, BorderLayout.WEST);
		JPanel Panel4 = new JPanel(new GridLayout(1, 2));
		Panel4.add(adaguAmt);
		Panel4.add(new JLabel(""));
		adaguAmtPanel.add(Panel4, BorderLayout.CENTER);
		panel.add(adaguAmtPanel, gbc);

		URL custNameUrl = TextFieldWithIcon.class.getResource("/images/label.png");
		ImageIcon custNameicon = new ImageIcon(custNameUrl);
		Image custNameimage = custNameicon.getImage();
		Image custNameImage = custNameimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custNameIcon = new ImageIcon(custNameImage);
		JPanel custNamePanel = new JPanel(new BorderLayout());
		JLabel custNameLabel = new JLabel(custNameIcon);
		custNamePanel.add(custNameLabel, BorderLayout.WEST);
		JPanel Panel5 = new JPanel(new GridLayout(1, 2));
		Panel5.add(custName);
		Panel5.add(new JLabel(""));
		custNamePanel.add(Panel5, BorderLayout.CENTER);
		panel.add(custNamePanel, gbc);

		heirRelation.setSelectedIndex(0);

		URL custHeirUrl = TextFieldWithIcon.class.getResource("/images/parent.png");
		ImageIcon custHeiricon = new ImageIcon(custHeirUrl);
		Image custHeirimage = custHeiricon.getImage();
		Image custHeirImage = custHeirimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custHeirIcon = new ImageIcon(custHeirImage);
		JPanel custHeirPanel = new JPanel(new BorderLayout());
		JLabel custHeirLabel = new JLabel(custHeirIcon);
		custHeirPanel.add(custHeirLabel, BorderLayout.WEST);
		// custHeirPanel.add(heirRelation, BorderLayout.EAST);
		JPanel Panel6 = new JPanel(new GridLayout(1, 3));
		Panel6.add(heirRelation);
		Panel6.add(custHeir);
		Panel6.add(new JLabel(""));
		Panel6.add(new JLabel(""));
		custHeirPanel.add(Panel6, BorderLayout.CENTER);
		panel.add(custHeirPanel, gbc);

		URL custAddressUrl = TextFieldWithIcon.class.getResource("/images/gps.png");
		ImageIcon custAddressicon = new ImageIcon(custAddressUrl);
		Image custAddressimage = custAddressicon.getImage();
		Image custAddressImage = custAddressimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custAddressIcon = new ImageIcon(custAddressImage);
		JPanel custAddressPanel = new JPanel(new BorderLayout());
		JLabel custAddressLabel = new JLabel(custAddressIcon);
		custAddressPanel.add(custAddressLabel, BorderLayout.WEST);
		JPanel Panel7 = new JPanel(new GridLayout(1, 2));
		Panel7.add(custAddress);
		Panel7.add(new JLabel(""));
		custAddressPanel.add(Panel7, BorderLayout.CENTER);
		panel.add(custAddressPanel, gbc);

		URL adaguActualAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguActualAmticon = new ImageIcon(adaguActualAmtUrl);
		Image adaguActualAmtimage = adaguActualAmticon.getImage();
		Image adaguActualAmtImage = adaguActualAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguActualAmtIcon = new ImageIcon(adaguActualAmtImage);
		JPanel adaguActualAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguActualAmtLabel = new JLabel(adaguActualAmtIcon);
		adaguActualAmtPanel.add(adaguActualAmtLabel, BorderLayout.WEST);
		JPanel Panel8 = new JPanel(new GridLayout(1, 2));
		Panel8.add(adaguActualAmt);
		Panel8.add(new JLabel(""));
		adaguActualAmtPanel.add(Panel8, BorderLayout.CENTER);
		panel.add(adaguActualAmtPanel, gbc);

		URL productDetailsUrl = TextFieldWithIcon.class.getResource("/images/product.png");
		ImageIcon productDetailsicon = new ImageIcon(productDetailsUrl);
		Image productDetailsimage = productDetailsicon.getImage();
		Image productDetailsImage = productDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productDetailsIcon = new ImageIcon(productDetailsImage);
		JPanel productDetailsPanel = new JPanel(new BorderLayout());
		JLabel productDetailsLabel = new JLabel(productDetailsIcon);
		productDetailsPanel.add(productDetailsLabel, BorderLayout.WEST);
		productTypes.setSelectedIndex(0);
		// productDetailsPanel.add(productTypes, BorderLayout.EAST);
		JPanel Panel9 = new JPanel(new GridLayout(1, 2));
		Panel9.add(productTypes);
		Panel9.add(productDetails);
		Panel9.add(new JLabel(""));
		Panel9.add(new JLabel(""));
		productDetailsPanel.add(Panel9, BorderLayout.CENTER);
		panel.add(productDetailsPanel, gbc);

		URL scrollPaneUrl = TextFieldWithIcon.class.getResource("/images/scroll.png");
		ImageIcon scrollPaneicon = new ImageIcon(scrollPaneUrl);
		Image scrollPaneimage = scrollPaneicon.getImage();
		Image scrollPaneImage = scrollPaneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon scrollPaneIcon = new ImageIcon(scrollPaneImage);
		JPanel scrollPanePanel = new JPanel(new BorderLayout());
		JLabel scrollPaneLabel = new JLabel(scrollPaneIcon);
		scrollPanePanel.add(scrollPaneLabel, BorderLayout.WEST);
		JPanel Panel10 = new JPanel(new GridLayout(1, 2));
		Panel10.add(scrollPane);
		Panel10.add(new JLabel(""));
		scrollPanePanel.add(Panel10, BorderLayout.CENTER);
		panel.add(scrollPanePanel, gbc);

		URL productWeightUrl = TextFieldWithIcon.class.getResource("/images/weight.png");
		ImageIcon productWeighticon = new ImageIcon(productWeightUrl);
		Image productWeightimage = productWeighticon.getImage();
		Image productWeightImage = productWeightimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productWeightIcon = new ImageIcon(productWeightImage);
		JPanel productWeightPanel = new JPanel(new BorderLayout());
		JLabel productWeightLabel = new JLabel(productWeightIcon);
		productWeightPanel.add(productWeightLabel, BorderLayout.WEST);
		JPanel Panel11 = new JPanel(new GridLayout(1, 2));
		Panel11.add(productWeight);
		Panel11.add(new JLabel(""));
		productWeightPanel.add(Panel11, BorderLayout.CENTER);
		panel.add(productWeightPanel, gbc);

		URL productValueUrl = TextFieldWithIcon.class.getResource("/images/value.png");
		ImageIcon productValueicon = new ImageIcon(productValueUrl);
		Image productValueimage = productValueicon.getImage();
		Image productValueImage = productValueimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productValueIcon = new ImageIcon(productValueImage);
		JPanel productValuePanel = new JPanel(new BorderLayout());
		JLabel productValueLabel = new JLabel(productValueIcon);
		productValuePanel.add(productValueLabel, BorderLayout.WEST);
		JPanel Panel12 = new JPanel(new GridLayout(1, 2));
		Panel12.add(productValue);
		Panel12.add(new JLabel(""));
		productValuePanel.add(Panel12, BorderLayout.CENTER);
		panel.add(productValuePanel, gbc);

		URL redemptionDateUrl = TextFieldWithIcon.class.getResource("/images/expiry.png");
		ImageIcon redemptionDateicon = new ImageIcon(redemptionDateUrl);
		Image redemptionDateimage = redemptionDateicon.getImage();
		Image redemptionDateImage = redemptionDateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon redemptionDateIcon = new ImageIcon(redemptionDateImage);
		JPanel redemptionDatePanel = new JPanel(new BorderLayout());
		JLabel redemptionDateLabel = new JLabel(redemptionDateIcon);
		redemptionDatePanel.add(redemptionDateLabel, BorderLayout.WEST);
		JPanel Panel13 = new JPanel(new GridLayout(1, 2));
		Panel13.add(redemptionDate);
		Panel13.add(new JLabel(""));
		redemptionDatePanel.add(Panel13, BorderLayout.CENTER);
		panel.add(redemptionDatePanel, gbc);

		// panel.add(saveBill);
		// panel.add(printBill);
		// panel.add(notifyBill);
		// panel.add(clearBill);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));
		// 28Apr2025 -Start
		bottomPanel.add(saveBill);
		bottomPanel.add(clearBill);
		// 28Apr2025 -End
		JPanel bottomPanel1 = new JPanel(new BorderLayout());
		JPanel Panel14 = new JPanel(new GridLayout(1, 2));
		Panel14.add(bottomPanel);
		Panel14.add(new JLabel(""));
		bottomPanel1.add(Panel14, BorderLayout.CENTER);
		panel.add(bottomPanel1, gbc);

		clearBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBill.setEnabled(true);
				billNum.setEditable(false); //23Sep2025
				billNum.setText("Bill Num");
				billNum.setForeground(new Color(157, 161, 250));
				adaguAmt.setEditable(true);
				adaguAmt.setText("Amount");
				adaguAmt.setForeground(new Color(157, 161, 250));
				custPhone.setEditable(true);
				custPhone.setText("Phone");
				custPhone.setForeground(new Color(157, 161, 250));
				custName.setEditable(true);
				custName.setText("Name");
				custName.setForeground(new Color(157, 161, 250));
				custAddress.setEditable(true);
				custAddress.setText("Address");
				custAddress.setForeground(new Color(157, 161, 250));
				custHeir.setEditable(true);
				custHeir.setText("Heir");
				custHeir.setForeground(new Color(157, 161, 250));
				heirRelation.setSelectedIndex(0);
				heirRelation.setEditable(true);
				productTypes.setSelectedIndex(0);
				productTypes.setEditable(true);
				adaguActualAmt.setEditable(true);
				adaguActualAmt.setText("Actual Amount");
				adaguActualAmt.setForeground(new Color(157, 161, 250));
				productWeight.setEditable(true);
				productWeight.setText("Weight");
				productWeight.setForeground(new Color(157, 161, 250));
				productValue.setEditable(true);
				productValue.setText("Value");
				productValue.setForeground(new Color(157, 161, 250));
				productDetails.setEditable(true);
				productDetails.setText("Products");
				productDetails.setForeground(new Color(157, 161, 250));
				selectedProductsModel.clear();
				adaguDate.setDate(new java.util.Date()); // Set default system date
			}
		});

		productDetails.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = productDetails.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				String PRODUCTTYPE = productTypes.getSelectedItem().toString();
				if (PRODUCTTYPE.equals("Gold")) {
					int i = 0;
					for (String product : productGoldList) {
						if (product.toLowerCase().startsWith(typedText) && i <= 15) {
							suggestions.add(product);
							i++;
						}
					}
				} else if (PRODUCTTYPE.equals("Silver")) {
					int j = 0;
					for (String product : productSilverList) {
						if (product.toLowerCase().startsWith(typedText) && j <= 15) {
							suggestions.add(product);
							j++;
						}
					}
				}

				// Show suggestions in a combo box if any
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									int qty = 0;
									String qtyString = JOptionPane.showInputDialog(null, "" + suggestion + " : ",
											"Quantity Input", JOptionPane.QUESTION_MESSAGE);
									if (qtyString != null && !qtyString.trim().isEmpty()) {
										try {
											int quantity = Integer.parseInt(qtyString);
											if (quantity > 0) {
												qty = quantity;
											} else {
												JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.",
														"Error", JOptionPane.ERROR_MESSAGE);
												qty = 0;
											}
										} catch (NumberFormatException ex) {
											JOptionPane.showMessageDialog(null, "Please enter a valid number.",
													"Invalid Input", JOptionPane.ERROR_MESSAGE);
											qty = 0;
										}
									} else {
										JOptionPane.showMessageDialog(null, "Quantity is required.", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
//									selectedProductsModel.addElement(suggestion + " - " + qty);
									//selectedProductsModel.addElement(PRODUCTTYPE + " - " + suggestion + " - " + qty); // 23July2025
									selectedProductsModel.addElement(suggestion + "-" + qty); //27Oct2025
									productDetails.setText(""); // Clear text field after selection
								}
								// Hide the popup after selection
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(productDetails, 0, productDetails.getHeight());

				}
			}

		});

		billNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		adaguActualAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		productWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = productWeight.getText().toString().trim();
				/*
				 * if (!Character.isDigit(c) && c != '.' || (c == '.' && text.contains("."))) {
				 * e.consume(); }
				 */
				if ((!Character.isDigit(c) && c != '.' && c != '/') || (c == '/' && text.split("/+").length > 1)) {
					e.consume();
				}
			}

			// 30Apr2025 - Start
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String proWgt = productWeight.getText().trim();
					try {
						double weight = Double.parseDouble(proWgt);
						String formattedWeight = String.format("%.3f", weight);
						productWeight.setText(formattedWeight);
					} catch (NumberFormatException ex) {
						if (proWgt.isEmpty()) {
							productWeight.setText("");
						}
					}
				}
			}
			// 30Apr2025 - End
		});

		productValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custName.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterCustomerList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custName.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custName, 0, custName.getHeight());
				}
			}
		});

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custAddress.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterAddressList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custAddress.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custAddress, 0, custAddress.getHeight());
				}
			}
		});

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custHeir.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterHeirList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custHeir.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custHeir, 0, custHeir.getHeight());
				}
			}
		});

		notifyBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				StringBuilder reminderMessage = new StringBuilder();
				reminderMessage.append(" அறிவிப்பு: ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
						+ " அடகு வைத்துளிர்கள்,  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" Bill Number  : " + BILLNUM);
				reminderMessage.append(" \n ");
				reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
				reminderMessage.append(" \n ");
				reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
						+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage
						.append(" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நன்றி! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
				reminderMessage.append(" \n ");

				StringBuilder displayMessage = new StringBuilder();
				displayMessage.append(" Bill Number  : " + BILLNUM);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Name : " + CUSTNAME);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Phone : " + CUSTPHONE);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Address : " + CUSTADDRESS);
				displayMessage.append(" \n");
				displayMessage.append(
						" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : " + REDEMPTIONDATEFORMATTED);
				displayMessage.append(" \n");
				displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
				displayMessage.append(" \n");

				// Create JTextArea for custom popup display
				JTextArea reminderArea = new JTextArea(displayMessage.toString());
				reminderArea.setEditable(false); // Make text area non-editable
				reminderArea.setWrapStyleWord(true); // Word wrapping
				reminderArea.setLineWrap(true);
				reminderArea.setCaretPosition(0); // Set cursor at the top
				reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
				reminderArea.setPreferredSize(new Dimension(400, 200));

				int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Copy" },
						"Copy");

				if (choice == 0) {
					String message = "" + reminderMessage;
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
			}
		});

		saveBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				String PRODUCTTYPE = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;
				String STATUS = "";

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}
				//16Sep2025
				if (null != billNum.getText().toString().trim()) {
					DBConnect billNumCheck = new DBConnect();
					boolean isDuplicateBillNum = billNumCheck.checkbillNum(billNum.getText().trim().toString());
					if (isDuplicateBillNum) {
						billNum.setText("0");
						JOptionPane.showConfirmDialog(panel, " Bill Num Already Exists Kindly Check !!! ");
						error = true;
						return;
					}
				}
				//16Sep2025

				LICENCENUM = LICENCENO.getText().toString().trim();
				BILLNUM = billNum.getText().toString().trim();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString().trim());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString().trim());
				CUSTNAME = custName.getText().toString().trim();
				CUSTADDRESS = custAddress.getText().toString().trim();
				CUSTHEIR = custHeir.getText().toString().trim();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString().trim();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString().trim());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString().trim());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
				STATUS = status.getSelectedItem().toString().trim();
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + "~");
				}

				AdaguBill adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(LICENCENUM);
				adaguData.setBILL_NUMBER(BILLNUM);
				adaguData.setCUSTOMER_NAME(CUSTNAME);
				adaguData.setCUSTOMER_PHONE("" + CUSTPHONE);
				adaguData.setHEIR_RELATION(CUSTHEIRRELATION);
				adaguData.setHEIR(CUSTHEIR);
				adaguData.setCUSTOMER_ADDRESS(CUSTADDRESS);
				adaguData.setPRODUCTS(PRODUCT.toString().trim());
				adaguData.setPRODUCT_WEIGHT("" + PRODUCTWEIGTH);
				adaguData.setADAGU_AMOUNT("" + ADAGUAMT);
				adaguData.setACTUAL_ADAGU_AMOUNT("" + ADAGUACTUALAMT);
				adaguData.setPRODUCT_VALUE("" + PRODUCTVALUE);
				adaguData.setADAGU_DATE(ADAGUDATEFORMATTED);
				adaguData.setREDEMPTION_DATE(REDEMPTIONDATEFORMATTED);
				adaguData.setPRODUCT_TYPE(PRODUCTTYPE);
				adaguData.setBILL_TYPE("Adagu Bill");
				if (STATUS.equals("Paid")) {
					adaguData.setSTATUS("Paid");
				} else {
					adaguData.setSTATUS("Pending");
				}

				DBConnect saveAdagu = new DBConnect();
				boolean isSaved = false;
				isSaved = saveAdagu.saveAdaguBill(adaguData);

				if (isSaved) {
					JOptionPane.showConfirmDialog(panel, " Bill Saved Successfully !!! ");
					saveBill.setEnabled(false);
				} else {
					JOptionPane.showConfirmDialog(panel, " Error : Save Bill !!! ");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
			}
		});

		printBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				if (LICENCENUM.equals("*****")) {
					PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
							CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					//27Oct2025
					String PRODUCTTYPE = "";
					PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
					print.setPRODUCT_TYPE(PRODUCTTYPE);
					//27Oct2025
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printForm(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE,
							CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					//27Oct2025
					String PRODUCTTYPE = "";
					PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
					print.setPRODUCT_TYPE(PRODUCTTYPE);
					//27Oct2025
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printFormLic(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		// Set window visibility
		setVisible(true);

		return panel;
	}

	// Custom JPanel class to paint the background image
	class AdaguBackgroundPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Image backgroundImage;

		// Constructor to load the background image
		public AdaguBackgroundPanel() {
			// Get the image from the "images" folder inside the project
			URL imageUrl = getClass().getResource("/images/back.jpeg"); // Adjust the path to your image
			if (imageUrl != null) {
				backgroundImage = new ImageIcon(imageUrl).getImage();
			} else {
				System.out.println("Image not found!");
			}
		}

		// Override paintComponent to draw the background image
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Scale the image to fit the panel
		}
	}

	// Panel for View Adagu Bill
	public JPanel createViewAdaguBillPanel() {
		JPanel panel = new JPanel();
		JTextField txtBillNo, txtMobileNo, txtLicenceNo, txtName;
		JButton btnSearch, btnEdit, btnDelete, btnCloseBill, btnExportPDF, btnGenerateBill, btnNotifyBill,
				btnCloseBillMany; // Apr2025
		JComboBox<String> cmbStatus, cmbProductType; //16Sep2025
		JTable table;
		JDateChooser adaguDate;
		DefaultTableModel tableModel;

		panel.setLayout(new BorderLayout());

		// Header Panel (for search inputs)
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());

		URL dPrintUrl = TextFieldWithIcon.class.getResource("/images/dummyprint.png");
		ImageIcon dPrintUrlicon = new ImageIcon(dPrintUrl);
		Image dPrintUrlimage = dPrintUrlicon.getImage();
		Image dPrintUrlImage = dPrintUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon dPrintUrlIcon = new ImageIcon(dPrintUrlImage);
		JLabel dPrintUrlLabel = new JLabel(dPrintUrlIcon);

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);
		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		txtLicenceNo = new JTextField(10);
		txtLicenceNo.setText("*****");
		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					txtLicenceNo.setText("*****");
				}
				isFirstImage = !isFirstImage;
			}
		});

		JLabel lblBillNo = new JLabel("Bill No:");
		txtBillNo = new JTextField(10);
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(10);

		JLabel lblStatus = new JLabel("Status:");
		cmbStatus = new JComboBox<>(new String[] { "", "Paid", "Pending" });
		
		// 16Sep2025
		JLabel lblProductType = new JLabel("Type:");
		cmbProductType = new JComboBox<>(new String[] { "", "Gold", "Silver", "Gold & Silver" });
		// 16Sep2025

		JLabel adagudateLabel = new JLabel("Adagu Date:");
		adaguDate = new JDateChooser();
		adaguDate.setDateFormatString("dd-MM-yyyy");

		btnSearch = new JButton("Search");

		headerPanel.add(licNoUrlLabel);
		headerPanel.add(lblBillNo);
		headerPanel.add(txtBillNo);
		headerPanel.add(lblName);
		headerPanel.add(txtName);
		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(lblStatus);
		headerPanel.add(cmbStatus);
		// 16Sep2025
		headerPanel.add(lblProductType);
		headerPanel.add(cmbProductType);
		// 16Sep2025
		headerPanel.add(adagudateLabel);
		headerPanel.add(adaguDate);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		// Table for displaying results
		/*
		 * tableModel = new DefaultTableModel(new String[] { "LICENCE NO", "BILL NO",
		 * "NAME", "PHONE", "ADDRESS", "PRODUCTS", "WEIGHT", "AMOUNT", "DATE",
		 * "REDEMPTION", "STATUS" }, 0);
		 */

		tableModel = new DefaultTableModel(new String[] { "PHONE", "LIC", "BILL", "NAME", "ADDRESS", "PRODUCT",
				"WEIGHT", "AMT", "DATE", "R.DATE", "STATUS", "C.DATE" }, 0); // 29Apr2025

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem editItem = new JMenuItem("Edit");
		popupMenu.add(editItem);
		JMenuItem calculateItem = new JMenuItem("Calculate %");
		// popupMenu.add(calculateItem);
		// Locker Detail New Fix
		JMenuItem lockerItem = new JMenuItem("Locker");
		popupMenu.add(lockerItem);

		// Balance Detail New Fix
		JMenuItem balanceItem = new JMenuItem("Balance");
		popupMenu.add(balanceItem);

		// Payment Detail New Fix
		JMenuItem paymentItem = new JMenuItem("Pay Bill");
		popupMenu.add(paymentItem);

		JMenuItem deleteItem = new JMenuItem("Delete");
		popupMenu.add(deleteItem);
		table.setComponentPopupMenu(popupMenu);

		// Locker Detail New Fix
		lockerItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField LICENCE_NUMBER = new JTextField(selectedRecord.getLICENCE_NUMBER());
						LICENCE_NUMBER.setEditable(false);
						JTextField BILL_NUMBER = new JTextField(selectedRecord.getBILL_NUMBER());
						BILL_NUMBER.setEditable(false);
						JTextField LOCKER_NAME = new JTextField(selectedRecord.getLOCKER_NAME());
						LOCKER_NAME.setEditable(true);
						JTextField LOCKER_BILLNUM = new JTextField(selectedRecord.getLOCKER_BILLNUM());
						LOCKER_BILLNUM.setEditable(true);
						JTextField LOCKER_DATE = new JTextField(selectedRecord.getLOCKER_DATE());
						LOCKER_DATE.setEditable(true);

						Object[] message = { "LICENCE_NUMBER ", LICENCE_NUMBER, "BILL_NUMBER ", BILL_NUMBER,
								"LOCKER_NAME ", LOCKER_NAME, "LOCKER_BILLNUM ", LOCKER_BILLNUM, "LOCKER_DATE ",
								LOCKER_DATE };
						int option = JOptionPane.showConfirmDialog(null, message, "Locker Details",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							selectedRecord.setLOCKER_NAME(LOCKER_NAME.getText().toUpperCase().trim());
							selectedRecord.setLOCKER_BILLNUM(LOCKER_BILLNUM.getText().toUpperCase().trim());
							selectedRecord.setLOCKER_DATE(LOCKER_DATE.getText().toUpperCase().trim());
							DBConnect updateRecord = new DBConnect();
							boolean isUpdated = updateRecord.updateLockerData(selectedRecord);
							if (isUpdated) {
								JOptionPane.showMessageDialog(panel, "Locker Details Saved Succesfully.");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Updating Locker Details");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Loading Locker Details");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Load");
				}
			}
		});

		// Balance Detail New Fix
		balanceItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField LICENCE_NUMBER = new JTextField(selectedRecord.getLICENCE_NUMBER());
						LICENCE_NUMBER.setEditable(false);
						JTextField BILL_NUMBER = new JTextField(selectedRecord.getBILL_NUMBER());
						BILL_NUMBER.setEditable(false);
						JTextField BALANCE_BILLNUM = new JTextField(selectedRecord.getBALANCE_BILLNUM());
						BALANCE_BILLNUM.setEditable(true);
						JTextField BALANCE_PRODUCT = new JTextField(selectedRecord.getBALANCE_PRODUCT());
						BALANCE_PRODUCT.setEditable(true);
						JTextField BALANCE_DATE = new JTextField(selectedRecord.getBALANCE_DATE());
						BALANCE_DATE.setEditable(true);

						Object[] message = { "LICENCE_NUMBER ", LICENCE_NUMBER, "BILL_NUMBER ", BILL_NUMBER,
								"BALANCE_BILLNUM ", BALANCE_BILLNUM, "BALANCE_PRODUCT ", BALANCE_PRODUCT,
								"BALANCE_DATE ", BALANCE_DATE };
						int option = JOptionPane.showConfirmDialog(null, message, "Locker Details",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							selectedRecord.setBALANCE_BILLNUM(BALANCE_BILLNUM.getText().toUpperCase().trim());
							selectedRecord.setBALANCE_PRODUCT(BALANCE_PRODUCT.getText().toUpperCase().trim());
							selectedRecord.setBALANCE_DATE(BALANCE_DATE.getText().toUpperCase().trim());
							DBConnect updateRecord = new DBConnect();
							boolean isUpdated = updateRecord.updateBalanceData(selectedRecord);
							if (isUpdated) {
								JOptionPane.showMessageDialog(panel, "Balance Details Saved Succesfully.");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Updating Balance Details");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Loading Balance Details");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Load");
				}
			}
		});

		// Payment Detail New Fix
		paymentItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField LICENCE_NUMBER = new JTextField(selectedRecord.getLICENCE_NUMBER());
						LICENCE_NUMBER.setEditable(false);
						JTextField BILL_NUMBER = new JTextField(selectedRecord.getBILL_NUMBER());
						BILL_NUMBER.setEditable(false);
						JTextField ADAGU_AMOUNT = new JTextField(selectedRecord.getADAGU_AMOUNT());
						ADAGU_AMOUNT.setEditable(false);
						JTextField PAID_AMOUNT = new JTextField(selectedRecord.getPAID_AMOUNT());
						PAID_AMOUNT.setEditable(false);
						JTextField REMAINING_AMOUNT = new JTextField(selectedRecord.getREMAINING_AMOUNT());
						REMAINING_AMOUNT.setEditable(false);
						JTextField PAYABLE_AMOUNT = new JTextField("");

						Object[] message = { "LICENCE_NUMBER ", LICENCE_NUMBER, "BILL_NUMBER ", BILL_NUMBER,
								"ADAGU_AMOUNT ", ADAGU_AMOUNT, "PAID_AMOUNT ", PAID_AMOUNT, "REMAINING_AMOUNT ",
								REMAINING_AMOUNT, "PAYABLE_AMOUNT ", PAYABLE_AMOUNT };

						int option = JOptionPane.showConfirmDialog(null, message, "Payment Details",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							String adaguAmt = "", paidAmt = "", remainingAmt = "", payableAmt = "";
							if (ADAGU_AMOUNT.getText().toString().trim().equals("")) {
								adaguAmt = "0";
							} else {
								adaguAmt = ADAGU_AMOUNT.getText().toString().trim();
							}
							if (PAID_AMOUNT.getText().toString().trim().equals("")) {
								paidAmt = "0";
							} else {
								paidAmt = PAID_AMOUNT.getText().toString().trim();
							}
							if (REMAINING_AMOUNT.getText().toString().trim().equals("")) {
								remainingAmt = "0";
							} else {
								remainingAmt = REMAINING_AMOUNT.getText().toString().trim();
							}
							if (PAYABLE_AMOUNT.getText().toString().trim().equals("")) {
								payableAmt = "0";
							} else {
								payableAmt = PAYABLE_AMOUNT.getText().toString().trim();
							}
							double actualAmount = Double.parseDouble(adaguAmt);
							double paidAmount = Double.parseDouble(paidAmt);
							double payingAmount = Double.parseDouble(payableAmt);
							double remainingAmount = actualAmount - paidAmount - payingAmount;
							REMAINING_AMOUNT.setText(String.format("%.2f", remainingAmount));
							PAID_AMOUNT.setText(String.format("%.2f", paidAmount + payingAmount));

							selectedRecord.setPAID_AMOUNT(PAID_AMOUNT.getText().trim().toString()); // Update the table
																									// view
							selectedRecord.setREMAINING_AMOUNT(REMAINING_AMOUNT.getText().trim().toString()); // Update
																												// the
																												// table
																												// view
							DBConnect updateRecord = new DBConnect();
							boolean isUpdated = updateRecord.updatePaymentData(selectedRecord);
							if (isUpdated) {
								JOptionPane.showMessageDialog(panel, "Record Updated Successfully!");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Record Updation");
							}

						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Loading Payment Details");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Load");
				}
			}
		});

		calculateItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null && !selectedRecord.getSTATUS().equals("Paid")) {
						SwingUtilities.invokeLater(() -> {
							LoanInterestCalculator interestCalculator = new LoanInterestCalculator();
							interestCalculator.calculateInterestWithData(BILLNO);
						});
					} else {
						JOptionPane.showMessageDialog(panel, "Record Already Paid");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Calculate Interest.");
				}
			}
		});

		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						int option = JOptionPane.showConfirmDialog(null, "Sure Need To Delete This Record ?",
								"Delete Record", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.removeRow(selectedRow);
							DBConnect deleteRecord = new DBConnect();
							boolean isDeleted = deleteRecord.deleteAdaguData(selectedRecord.getBILL_NUMBER());
							if (isDeleted) {
								JOptionPane.showMessageDialog(panel, "Record Deleted Successfully!");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Record Deletion");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record Deletion");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Delete.");
				}
			}
		});

		editItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField LICENCE_NUMBER = new JTextField(selectedRecord.getLICENCE_NUMBER());
						LICENCE_NUMBER.setEditable(false);
						JTextField BILL_NUMBER = new JTextField(selectedRecord.getBILL_NUMBER());
						BILL_NUMBER.setEditable(false);
						JTextField CUSTOMER_NAME = new JTextField(selectedRecord.getCUSTOMER_NAME());
						JTextField CUSTOMER_PHONE = new JTextField(selectedRecord.getCUSTOMER_PHONE());
						JTextField CUSTOMER_HEIR = new JTextField(selectedRecord.getHEIR()); // 23July2025
						JTextField CUSTOMER_ADDRESS = new JTextField(selectedRecord.getCUSTOMER_ADDRESS());
						JTextField ADAGU_AMOUNT = new JTextField(selectedRecord.getADAGU_AMOUNT());
						ADAGU_AMOUNT.setEditable(false);
						JTextField PRODUCT_WEIGHT = new JTextField(selectedRecord.getPRODUCT_WEIGHT());
						JTextField ADAGU_DATE = new JTextField(selectedRecord.getADAGU_DATE());
						JTextField REDEMPTION_DATE = new JTextField(selectedRecord.getREDEMPTION_DATE());
						JTextField STATUS = new JTextField(selectedRecord.getSTATUS());
						
						JTextField CANCEL_DATE = new JTextField(selectedRecord.getCANCEL_DATE()); //08Aug2025


						AdaguBill oldData = new AdaguBill();
						oldData.setCUSTOMER_NAME(selectedRecord.getCUSTOMER_NAME());
						oldData.setCUSTOMER_ADDRESS(selectedRecord.getCUSTOMER_ADDRESS());
						oldData.setHEIR(selectedRecord.getHEIR()); // 23July2025

						STATUS.setEditable(false);
						Object[] message = { "LICENCE_NUMBER ", LICENCE_NUMBER, "BILL_NUMBER :", BILL_NUMBER,
								"CUSTOMER_NAME ", CUSTOMER_NAME, "CUSTOMER_PHONE ", CUSTOMER_PHONE, "CUSTOMER_ADDRESS ",
								CUSTOMER_ADDRESS, "CUSTOMER_HEIR", CUSTOMER_HEIR, "ADAGU_AMOUNT ", ADAGU_AMOUNT,
								"PRODUCT_WEIGHT", PRODUCT_WEIGHT, "ADAGU_DATE ", ADAGU_DATE, "REDEMPTION_DATE ",
								REDEMPTION_DATE, "CANCEL_DATE", CANCEL_DATE, "STATUS ", STATUS }; //08Aug2025
						int option = JOptionPane.showConfirmDialog(null, message, "Edit Record",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							tableModel.setValueAt(CUSTOMER_NAME.getText(), selectedRow, 3); // Update the table view
							tableModel.setValueAt(CUSTOMER_PHONE.getText(), selectedRow, 3); // Update the table view
							tableModel.setValueAt(CUSTOMER_ADDRESS.getText(), selectedRow, 4); // Update the table view
							tableModel.setValueAt(ADAGU_DATE.getText(), selectedRow, 7); // Update the table view
							tableModel.setValueAt(REDEMPTION_DATE.getText(), selectedRow, 8); // Update the table view
							tableModel.setValueAt(PRODUCT_WEIGHT.getText(), selectedRow, 6); // Update the table view
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							for (int i = 0; i < table.getColumnCount(); i++) {
								if (table.getColumnName(i).equals("NAME")) {
									model.setValueAt(CUSTOMER_NAME.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("PHONE")) {
									model.setValueAt(CUSTOMER_PHONE.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("ADDRESS")) {
									model.setValueAt(CUSTOMER_ADDRESS.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("DATE")) {
									model.setValueAt(ADAGU_DATE.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("R.DATE")) {
									model.setValueAt(REDEMPTION_DATE.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("WEIGHT")) {
									model.setValueAt(PRODUCT_WEIGHT.getText(), selectedRow, i);
								}
							}

							selectedRecord.setCUSTOMER_NAME(CUSTOMER_NAME.getText()); // Update the table view
							selectedRecord.setCUSTOMER_PHONE(CUSTOMER_PHONE.getText()); // Update the table view
							selectedRecord.setCUSTOMER_ADDRESS(CUSTOMER_ADDRESS.getText()); // Update the table view
							selectedRecord.setADAGU_DATE(ADAGU_DATE.getText()); // Update the table view
							selectedRecord.setREDEMPTION_DATE(REDEMPTION_DATE.getText()); // Update the table view
							selectedRecord.setPRODUCT_WEIGHT(PRODUCT_WEIGHT.getText()); // Update the table view
							selectedRecord.setHEIR(CUSTOMER_HEIR.getText()); // Update the table view //23July2025
							selectedRecord.setCANCEL_DATE(CANCEL_DATE.getText()); //08Aug2025
							DBConnect updateRecord = new DBConnect();
							boolean isUpdated = updateRecord.updateAdaguData(selectedRecord, oldData);
							if (isUpdated) {
								JOptionPane.showMessageDialog(panel, "Record Updated Successfully!");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Record Updation");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record Updation");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Update");
				}
			}
		});

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());

		btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.RED);
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnCloseBill = new JButton("Close Bill");
		btnCloseBill.setForeground(Color.RED);
		// 28Apr2025 - Start
		btnCloseBillMany = new JButton("Close Multiple Bills");
		btnCloseBillMany.setForeground(Color.RED);
		// 28Apr2025 - End
		btnExportPDF = new JButton("Export PDF");
		btnExportPDF.setForeground(Color.RED);
		btnGenerateBill = new JButton("Re-Print Bill");
		btnGenerateBill.setForeground(Color.RED);
		btnNotifyBill = new JButton("Re-Notify Bill");
		btnNotifyBill.setForeground(Color.RED);

		footerPanel.add(btnCloseBill);
		footerPanel.add(btnCloseBillMany); // 28Apr2025
		footerPanel.add(btnGenerateBill);
		footerPanel.add(btnNotifyBill);
		footerPanel.add(dPrintUrlLabel);

		// footerPanel.add(btnExportPDF);

		panel.add(footerPanel, BorderLayout.SOUTH);

		btnCloseBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;

					// Find the selected record from the list
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}

					if (selectedRecord != null && !selectedRecord.getSTATUS().equals("Paid")) {
						selectedRecord.setSTATUS("Paid");
						selectedRecord.setREDEMPTION_DATE("" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
						tableModel.setValueAt("Paid", selectedRow, 10); // Update the table view
						tableModel.setValueAt("" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), selectedRow,
								9); // Update the table view
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						for (int i = 0; i < table.getColumnCount(); i++) {
							if (table.getColumnName(i).equals("STATUS")) {
								model.setValueAt("Paid", selectedRow, i);
							}
						}
						DBConnect paidBill = new DBConnect();
						boolean isPaid = paidBill.updatePaidStatus(selectedRecord.getBILL_NUMBER());
						if (isPaid)
							JOptionPane.showMessageDialog(panel, "Bill No: " + billNo + " is now Paid.");
						else
							JOptionPane.showMessageDialog(panel, "This bill is already Paid or not found.");
					} else {
						JOptionPane.showMessageDialog(panel, "This bill is already Paid or not found.");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to close.");
				}

			}
		});

		// 28Apr2025 - Start
		btnCloseBillMany.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setTitle("Choose Bill Num");
				dialog.setSize(500, 400);
				JPanel checkBoxPanel = new JPanel();
				checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
				List<JCheckBox> checkBoxes = new ArrayList<>();
				DBConnect loadData = new DBConnect();
				closingBillNums = loadData.loadclosingBillNums();
				for (String option : closingBillNums) {
					JCheckBox checkBox = new JCheckBox(option);
					checkBoxes.add(checkBox);
					checkBoxPanel.add(checkBox);
				}
				JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
				scrollPane.setPreferredSize(new Dimension(350, 150));
				JPanel searchPanel = new JPanel();
				searchPanel.setLayout(new FlowLayout());
				JTextField searchTextField = new JTextField(20);
				// 30Apr2025
				JDateChooser cancelDate;
				cancelDate = new JDateChooser();
				cancelDate.setDateFormatString("dd-MM-yyyy");
				cancelDate.setForeground(new Color(247, 25, 92));
				cancelDate.setDate(new java.util.Date());
				searchPanel.add(searchTextField);
				searchPanel.add(cancelDate);
				// 30Apr2025
				searchTextField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						String searchQuery = searchTextField.getText().toLowerCase();
						for (JCheckBox checkBox : checkBoxes) {
							String option = checkBox.getText().toLowerCase();
							if (option.contains(searchQuery)) {
								checkBox.setVisible(true);
							} else {
								checkBox.setVisible(false);
							}
						}
						checkBoxPanel.revalidate();
						checkBoxPanel.repaint();
					}
				});
				JButton saveButton = new JButton("Pay Bill");
				saveButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						List<String> selectedOptions = new ArrayList<>();
						for (JCheckBox checkBox : checkBoxes) {
							if (checkBox.isSelected()) {
								selectedOptions.add(checkBox.getText());
							}
						}
						if (selectedOptions.isEmpty()) {
							JOptionPane.showMessageDialog(dialog, "Please Choose Bill Num.");
						} else {
							DBConnect paidBill = new DBConnect();
							// 30Apr2025
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							String CANCELDATEFORMATTED = "";
							if (null != cancelDate.getDate()) {
								CANCELDATEFORMATTED = sdf.format(cancelDate.getDate());
								for (String billNO : selectedOptions) {
									boolean isPaid = paidBill.updatePaidStatus(billNO.trim().toUpperCase().toString(),
											CANCELDATEFORMATTED);
									if (!isPaid)
										JOptionPane.showMessageDialog(panel,
												"This bill " + billNO + " is already Paid or not found.");
								}
							} else {
								JOptionPane.showMessageDialog(dialog, "Cancel Date Is Mandatory.");
							}
							// 30Apr2025

						}
						dialog.dispose();
					}
				});
				JPanel paneling = new JPanel();
				paneling.setLayout(new BorderLayout());
				paneling.add(searchPanel, BorderLayout.NORTH);
				paneling.add(scrollPane, BorderLayout.CENTER);
				paneling.add(saveButton, BorderLayout.SOUTH);
				dialog.add(paneling);
				dialog.setVisible(true);
			}
		});
		// 28Apr2025 - End

		btnExportPDF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Document document = new Document();
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Save PDF File");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

					// Filter to show only PDF files
					fileChooser.setAcceptAllFileFilterUsed(false);
					fileChooser.addChoosableFileFilter(
							new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));

					// Show save dialog
					int result = fileChooser.showSaveDialog(panel);

					if (result == JFileChooser.APPROVE_OPTION) {
						// Get the selected file
						File file = fileChooser.getSelectedFile();

						// Ensure the file has a .pdf extension
						if (!file.getName().endsWith(".pdf")) {
							file = new File(file.getAbsolutePath() + ".pdf");
						}

						// You can now save your PDF to the selected location
						try {
							// Output stream to save the PDF
							PdfWriter.getInstance(document, new FileOutputStream(file));
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
							JOptionPane.showMessageDialog(panel,
									"PDF saved successfully to: " + file.getAbsolutePath());
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(panel, "Error saving the PDF: " + ex.getMessage());
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(panel, "Error exporting to PDF: " + e1.getMessage());
				}

			}
		});

		dPrintUrlLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String licenseno = txtLicenceNo.getText().toString().trim();
				PrintBill print = new PrintBill();

				if (null != licenseno && licenseno.equals("*****")) {
					String printFileName = "DummyAdagu.pdf";
					try {
						print.printFormDummy(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					String printFileName = "DummyAdaguLIC.pdf";
					try {
						print.printFormLicDummy(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnGenerateBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						String LICENCENUM = selectedRecord.getLICENCE_NUMBER();
						String BILLNUM = selectedRecord.getBILL_NUMBER();
						Date ADAGUDATE = new Date();
						BigDecimal ADAGUAMT = new BigDecimal(selectedRecord.getADAGU_AMOUNT());
						BigDecimal CUSTPHONE = new BigDecimal(selectedRecord.getCUSTOMER_PHONE());
						String CUSTNAME = selectedRecord.getCUSTOMER_NAME();
						String CUSTADDRESS = selectedRecord.getCUSTOMER_ADDRESS();
						String CUSTHEIR = selectedRecord.getHEIR();
						String CUSTHEIRRELATION = selectedRecord.getHEIR_RELATION();
						BigDecimal ADAGUACTUALAMT = new BigDecimal(selectedRecord.getACTUAL_ADAGU_AMOUNT());
						String PRODUCTWEIGTH = selectedRecord.getPRODUCT_WEIGHT().toString();
						StringBuffer PRODUCT = new StringBuffer(selectedRecord.getPRODUCTS());
						BigDecimal PRODUCTVALUE = new BigDecimal(selectedRecord.getPRODUCT_VALUE());
						Date REDEMPTION = new Date();
						String ADAGUDATEFORMATTED = selectedRecord.getADAGU_DATE(),
								REDEMPTIONDATEFORMATTED = selectedRecord.getREDEMPTION_DATE();
						if (selectedRecord.getLICENCE_NUMBER().equals("*****")) {
							PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
									CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
									PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
							//27Oct2025
							String PRODUCTTYPE = "";
							PRODUCTTYPE = selectedRecord.getPRODUCT_TYPE();
							print.setPRODUCT_TYPE(PRODUCTTYPE);
							//27Oct2025
							String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
							try {
								print.printForm(print, printFileName);
							} catch (DocumentException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT,
									CUSTPHONE, CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT,
									PRODUCTWEIGTH, PRODUCT, PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
							//27Oct2025
							String PRODUCTTYPE = "";
							PRODUCTTYPE = selectedRecord.getPRODUCT_TYPE();
							print.setPRODUCT_TYPE(PRODUCTTYPE);
							//27Oct2025
							String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
							try {
								print.printFormLic(print, printFileName);
							} catch (DocumentException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Bill Not Found.");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please Select Record To Generate.");
				}

			}
		});

		btnNotifyBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						String LICENCENUM = selectedRecord.getLICENCE_NUMBER();
						String BILLNUM = selectedRecord.getBILL_NUMBER();
						Date ADAGUDATE = new Date();
						BigDecimal ADAGUAMT = new BigDecimal(selectedRecord.getADAGU_AMOUNT());
						BigDecimal CUSTPHONE = new BigDecimal(selectedRecord.getCUSTOMER_PHONE());
						String CUSTNAME = selectedRecord.getCUSTOMER_NAME();
						String CUSTADDRESS = selectedRecord.getCUSTOMER_ADDRESS();
						String CUSTHEIR = selectedRecord.getHEIR();
						String CUSTHEIRRELATION = selectedRecord.getHEIR_RELATION();
						BigDecimal ADAGUACTUALAMT = new BigDecimal(selectedRecord.getACTUAL_ADAGU_AMOUNT());
						BigDecimal PRODUCTWEIGTH = new BigDecimal(selectedRecord.getPRODUCT_WEIGHT());
						StringBuffer PRODUCT = new StringBuffer(selectedRecord.getPRODUCTS());
						BigDecimal PRODUCTVALUE = new BigDecimal(selectedRecord.getPRODUCT_VALUE());
						Date REDEMPTION = new Date();
						String ADAGUDATEFORMATTED = selectedRecord.getADAGU_DATE(),
								REDEMPTIONDATEFORMATTED = selectedRecord.getREDEMPTION_DATE();

						StringBuilder reminderMessage = new StringBuilder();
						reminderMessage.append(" அறிவிப்பு: ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
								+ " அடகு வைத்துளிர்கள்,  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" Bill Number  : " + BILLNUM);
						reminderMessage.append(" \n ");
						reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
						reminderMessage.append(" \n ");
						reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
								+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நன்றி! ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
						reminderMessage.append(" \n ");

						StringBuilder displayMessage = new StringBuilder();
						displayMessage.append(" Bill Number  : " + BILLNUM);
						displayMessage.append(" \n");
						displayMessage.append(" Customer Name : " + CUSTNAME);
						displayMessage.append(" \n");
						displayMessage.append(" Customer Phone : " + CUSTPHONE);
						displayMessage.append(" \n");
						displayMessage.append(" Customer Address : " + CUSTADDRESS);
						displayMessage.append(" \n");
						displayMessage.append(" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : "
								+ REDEMPTIONDATEFORMATTED);
						displayMessage.append(" \n");
						displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
						displayMessage.append(" \n");

						// Create JTextArea for custom popup display
						JTextArea reminderArea = new JTextArea(displayMessage.toString());
						reminderArea.setEditable(false); // Make text area non-editable
						reminderArea.setWrapStyleWord(true); // Word wrapping
						reminderArea.setLineWrap(true);
						reminderArea.setCaretPosition(0); // Set cursor at the top
						reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
						reminderArea.setPreferredSize(new Dimension(400, 200));

						int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
								new Object[] { "Copy" }, "Copy");

						if (choice == 0) {
							String message = "" + reminderMessage;
							StringSelection stringSelection = new StringSelection(message);
							Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
							clipboard.setContents(stringSelection, null);
							JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Bill Not Found.");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please Select Record To Generate.");
				}
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListViewPanel = loadData.loadADAGUData();
				String billNo = txtBillNo.getText().trim();
				String mobileNo = txtMobileNo.getText().trim();
				String status = cmbStatus.getSelectedItem().toString();
				String productType = cmbProductType.getSelectedItem().toString(); //16Sep2025
				String licenseno = txtLicenceNo.getText().toString().trim();
				String Name = txtName.getText().toUpperCase().trim().toString();
				String ADAGUDATEFORMATTED = "";
				Date ADAGUDATE = new Date();
				ADAGUDATE = adaguDate.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if (null != ADAGUDATE) {
					ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				}
				// Clear existing data in the table
				tableModel.setRowCount(0);

				// Logic to filter records based on billNo, mobileNo, status, and billType
				for (AdaguBill record : AdaguBillListViewPanel) {
					// Filter based on Bill No (if not empty)
					boolean matchesBillNo = billNo.isEmpty() || String.valueOf(record.getBILL_NUMBER()).equals(billNo);

					boolean matchesName = Name.isEmpty() || String.valueOf(record.getCUSTOMER_NAME()).equals(Name);

					boolean matchesLicNo = licenseno.isEmpty()
							|| String.valueOf(record.getLICENCE_NUMBER().toString().trim()).equals(licenseno);

					// Filter based on Mobile No (if not empty)
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);

					// Filter based on Status (if not "All")
					boolean matchesStatus = status.equals("") || record.getSTATUS().equals(status);
					
					boolean matchesProductType = productType.equals("") || record.getPRODUCT_TYPE().equalsIgnoreCase(productType); //16Sep2025

					boolean matchesAdaguDate = ADAGUDATEFORMATTED.equals("")
							|| record.getADAGU_DATE().equals(ADAGUDATEFORMATTED);

					// Filter based on Bill Type (if not "All")

					// Only add record if all conditions are met
					if (matchesBillNo && matchesMobileNo && matchesStatus && matchesLicNo && matchesName
							&& matchesAdaguDate && matchesProductType) { //16Sep2025
						/*
						 * tableModel.addRow(new Object[] { record.getLICENCE_NUMBER(),
						 * record.getBILL_NUMBER(), record.getCUSTOMER_NAME(),
						 * record.getCUSTOMER_PHONE(), record.getCUSTOMER_ADDRESS(),
						 * record.getPRODUCTS(), record.getPRODUCT_WEIGHT(), record.getADAGU_AMOUNT(),
						 * record.getADAGU_DATE(), record.getREDEMPTION_DATE(), record.getSTATUS() });
						 */ // CHANGE ORDER
						tableModel.addRow(new Object[] { record.getCUSTOMER_PHONE(), record.getLICENCE_NUMBER(),
								record.getBILL_NUMBER(), record.getCUSTOMER_NAME(), record.getCUSTOMER_ADDRESS(),
								record.getPRODUCTS(), record.getPRODUCT_WEIGHT(), record.getADAGU_AMOUNT(),
								record.getADAGU_DATE(), record.getREDEMPTION_DATE(), record.getSTATUS(),
								record.getCANCEL_DATE() }); // 29Apr2025
					}
				}

			}
		});

		// Set the custom renderer for all columns
		int columnCount = table.getColumnModel().getColumnCount();

		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}

		// Set window properties
		// setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;
	}
	
	public JPanel createInterestCalculationPanel()
	{
		JPanel panel = new JPanel();
		JTextField txtBillNo, txtMobileNo, txtLicenceNo, txtName;
		JButton btnSearch; // Apr2025
		JTable table;
		JDateChooser adaguDate;
		DefaultTableModel tableModel;
		panel.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);
		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		txtLicenceNo = new JTextField(10);
		txtLicenceNo.setText("*****");
		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					txtLicenceNo.setText("*****");
				}
				isFirstImage = !isFirstImage;
			}
		});

		JLabel lblBillNo = new JLabel("Bill No:");
		txtBillNo = new JTextField(10);
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(10);

		JLabel adagudateLabel = new JLabel("Adagu Date:");
		adaguDate = new JDateChooser();
		adaguDate.setDateFormatString("dd-MM-yyyy");

		btnSearch = new JButton("Search");

		headerPanel.add(licNoUrlLabel);
		headerPanel.add(lblBillNo);
		headerPanel.add(txtBillNo);
		headerPanel.add(lblName);
		headerPanel.add(txtName);
		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(adagudateLabel);
		headerPanel.add(adaguDate);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(new String[] { "PHONE", "LIC", "BILL", "NAME", "DATE", "TYPE", "AMT",
				"Diff In Mon", "%", "Amt %", "Amount Payable" }, 0);

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());

		panel.add(footerPanel, BorderLayout.SOUTH);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListInterestPanel = loadData.loadADAGUInterestData();
				String billNo = txtBillNo.getText().trim();
				String mobileNo = txtMobileNo.getText().trim();
				String licenseno = txtLicenceNo.getText().toString().trim();
				String Name = txtName.getText().toUpperCase().trim().toString();
				String ADAGUDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); //23Sep2025
				//25Sep2025 Start
				Date comparisonDate = null;
				try {
					comparisonDate = sdf.parse("01-02-2025");
				}catch(Exception e1) {
					try {
						comparisonDate = sdf.parse("01/02/2025");
					} catch (ParseException e2) {
						comparisonDate = null;
					}
				}
				//25Sep2025 End
				
				tableModel.setRowCount(0);
				for (AdaguBill record : AdaguBillListInterestPanel) {
					boolean matchesBillNo = billNo.isEmpty() || String.valueOf(record.getBILL_NUMBER()).equals(billNo);
					boolean matchesName = Name.isEmpty() || String.valueOf(record.getCUSTOMER_NAME()).equals(Name);
					boolean matchesLicNo = licenseno.isEmpty()
							|| String.valueOf(record.getLICENCE_NUMBER().toString().trim()).equals(licenseno);
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);
					boolean matchesAdaguDate = ADAGUDATEFORMATTED.equals("")
							|| record.getADAGU_DATE().equals(ADAGUDATEFORMATTED);
					if (matchesBillNo && matchesMobileNo && matchesLicNo && matchesName && matchesAdaguDate) {
//						 "PHONE", "LIC", "BILL", "NAME", "DATE", "TYPE", "AMT",
//							"Diff In Mon", "Interest%", "Amount Payable" 
						ADAGUDATEFORMATTED = record.getADAGU_DATE();
				        Date adaguDate = null;
				        int monthDiffInt = 0;
				        String interestPer = "";
				        BigDecimal ADAGUAMT = new BigDecimal(0);
				        BigDecimal INTEREST = new BigDecimal(0);
				        BigDecimal TOTALINTEREST = new BigDecimal(0);
				        BigDecimal amtPayable = new BigDecimal(0);
						try {
							adaguDate = sdf.parse(ADAGUDATEFORMATTED);
							Timestamp currentDay = new Timestamp(System.currentTimeMillis());
					        LocalDate givenDate = adaguDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					        //25Sep2025 Start
					        //LocalDate currentDate = currentDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					        LocalDate currentDate = currentDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(2);
					        //25Sep2025 End
					        long monthDiff = ChronoUnit.MONTHS.between(givenDate.withDayOfMonth(1), currentDate.withDayOfMonth(1));
					        //25Sep2025 Start
					        //monthDiffInt = (int) monthDiff;
					        //monthDiffInt = monthDiffInt - 1;
					        if (adaguDate.before(comparisonDate)) {
					        	monthDiffInt = (int) monthDiff;
					        }else {
					        	monthDiffInt = (int) monthDiff - 1;
					        }
					        //25Sep2025 End
						} catch (ParseException e1) {
							//23Sep2025 Start
							try {
								adaguDate = sdf1.parse(ADAGUDATEFORMATTED);
							} catch (ParseException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							Timestamp currentDay = new Timestamp(System.currentTimeMillis());
					        LocalDate givenDate = adaguDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					        LocalDate currentDate = currentDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(2);
					        long monthDiff = ChronoUnit.MONTHS.between(givenDate.withDayOfMonth(1), currentDate.withDayOfMonth(1));
					        //monthDiffInt = (int) monthDiff;
					        //monthDiffInt = monthDiffInt - 1;
					        if (adaguDate.before(comparisonDate)) {
					        	monthDiffInt = (int) monthDiff;
					        }else {
					        	monthDiffInt = (int) monthDiff - 1;
					        }
					      //23Sep2025 End
						}
						
						try {
							if(null != record.getPRODUCT_TYPE() && "" != record.getPRODUCT_TYPE() && "Gold".equalsIgnoreCase(record.getPRODUCT_TYPE())) {
								interestPer = "2%";
							}else if(null != record.getPRODUCT_TYPE() && "" != record.getPRODUCT_TYPE() && "Silver".equalsIgnoreCase(record.getPRODUCT_TYPE())) {
								interestPer = "3%";
							}else if(null != record.getPRODUCT_TYPE() && "" != record.getPRODUCT_TYPE() && "Gold & Silver".equalsIgnoreCase(record.getPRODUCT_TYPE())) {
								interestPer = "2.5%";
							}else {
								interestPer = "";
							}
						}catch(Exception e2) {
							e2.printStackTrace();
						}
						
						try {
							if(null != interestPer && "" != interestPer && interestPer.equals("2%")) {
								ADAGUAMT = new BigDecimal(record.getADAGU_AMOUNT().toString());
								INTEREST = ADAGUAMT.multiply(new BigDecimal("0.02"));
								TOTALINTEREST = INTEREST.multiply(new BigDecimal(monthDiffInt));
								amtPayable = ADAGUAMT.add(TOTALINTEREST);
							}else if(null != interestPer && "" != interestPer && interestPer.equals("3%")) {
								ADAGUAMT = new BigDecimal(record.getADAGU_AMOUNT().toString());
								INTEREST = ADAGUAMT.multiply(new BigDecimal("0.03"));
								TOTALINTEREST = INTEREST.multiply(new BigDecimal(monthDiffInt));
								amtPayable = ADAGUAMT.add(TOTALINTEREST);
							}else if(null != interestPer && "" != interestPer && interestPer.equals("2.5%")) {
								ADAGUAMT = new BigDecimal(record.getADAGU_AMOUNT().toString());
								INTEREST = ADAGUAMT.multiply(new BigDecimal("0.025"));
								TOTALINTEREST = INTEREST.multiply(new BigDecimal(monthDiffInt));
								amtPayable = ADAGUAMT.add(TOTALINTEREST);
							}else {
								
							}
							
						}catch(Exception e3) {
							e3.printStackTrace();
						}
						
						tableModel.addRow(new Object[] { record.getCUSTOMER_PHONE(), record.getLICENCE_NUMBER(),
								record.getBILL_NUMBER(), record.getCUSTOMER_NAME(), 
								record.getADAGU_DATE(), record.getPRODUCT_TYPE(), record.getADAGU_AMOUNT(), monthDiffInt, interestPer, TOTALINTEREST, amtPayable }); 
					}
				}
			}
		});
		int columnCount = table.getColumnModel().getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;
	}
	
	public JPanel createCancelLedgerPanel() {
		JPanel panel = new JPanel();
		JTextField txtBillNo, txtMobileNo, txtLicenceNo, txtName;
		JButton btnSearch, btnEdit, btnDelete, btnCloseBill, btnExportPDF, btnGenerateBill, btnNotifyBill;
		JTable table;
		DefaultTableModel tableModel;
		JDateChooser adaguDate;
		panel.setLayout(new BorderLayout());
		// Header Panel (for search inputs)
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);
		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		txtLicenceNo = new JTextField(10);
		txtLicenceNo.setText("*****");
		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					txtLicenceNo.setText("*****");
				}
				isFirstImage = !isFirstImage;
			}
		});

		JLabel lblBillNo = new JLabel("Bill No:");
		txtBillNo = new JTextField(10);
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(10);
		JLabel adaguDateLabel = new JLabel("Cancel Date:");
		adaguDate = new JDateChooser();
		adaguDate.setDateFormatString("dd-MM-yyyy");
		btnSearch = new JButton("Search");

		headerPanel.add(licNoUrlLabel);
		headerPanel.add(lblBillNo);
		headerPanel.add(txtBillNo);
		headerPanel.add(lblName);
		headerPanel.add(txtName);
		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(adaguDateLabel);
		headerPanel.add(adaguDate);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(new String[] { "PHONE", "LIC", "BILL", "NAME", "ADDRESS", "AMT",
				"ADAGU DATE", "CANCEL DATE", "STATUS" }, 0);

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());

		btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.RED);
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnCloseBill = new JButton("Close Bill");
		btnCloseBill.setForeground(Color.RED);
		btnExportPDF = new JButton("Export PDF");
		btnExportPDF.setForeground(Color.RED);
		btnGenerateBill = new JButton("Re-Print Bill");
		btnGenerateBill.setForeground(Color.RED);
		btnNotifyBill = new JButton("Re-Notify Bill");
		btnNotifyBill.setForeground(Color.RED);

		// footerPanel.add(btnCloseBill);
		// footerPanel.add(btnGenerateBill);
		// footerPanel.add(btnNotifyBill);
		// footerPanel.add(btnExportPDF);

		panel.add(footerPanel, BorderLayout.SOUTH);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListCancelViewPanel = loadData.loadADAGUDataCancelled();
				String billNo = txtBillNo.getText().trim();
				String mobileNo = txtMobileNo.getText().trim();
				String licenseno = txtLicenceNo.getText().toString().trim();
				String Name = txtName.getText().toUpperCase().trim().toString();
				String ADAGUDATEFORMATTED = "";
				Date ADAGUDATE = new Date();
				ADAGUDATE = adaguDate.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if (null != ADAGUDATE) {
					ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				}
				tableModel.setRowCount(0);
				
				// 24Oct2025
				String CANCELDATEFORMATTED = "";
				boolean isDateChanged = false;
				// 24Oct2025
				
				// Logic to filter records based on billNo, mobileNo, status, and billType
				for (AdaguBill record : AdaguBillListCancelViewPanel) {
					boolean matchesBillNo = billNo.isEmpty() || String.valueOf(record.getBILL_NUMBER()).equals(billNo);
					boolean matchesName = Name.isEmpty() || String.valueOf(record.getCUSTOMER_NAME()).equals(Name);
					boolean matchesLicNo = licenseno.isEmpty()
							|| String.valueOf(record.getLICENCE_NUMBER().toString().trim()).equals(licenseno);
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);
					boolean matchesAdaguDate = ADAGUDATEFORMATTED.equals("")
							|| record.getCANCEL_DATE().equals(ADAGUDATEFORMATTED); // 08Aug2025
					
					// 24Oct2025
					if (null != record.getCANCEL_DATE()
							&& !CANCELDATEFORMATTED.equals(record.getCANCEL_DATE())) {
						CANCELDATEFORMATTED = record.getCANCEL_DATE();
						isDateChanged = true;
					} else {
						isDateChanged = false;
					}
					if (isDateChanged && matchesBillNo && matchesMobileNo && matchesLicNo && matchesName
							&& matchesAdaguDate) {
						tableModel.addRow(
								new Object[] { "--", "--", "--", "--", CANCELDATEFORMATTED, "--", "--", "--", "--" });
					}
					// 24Oct2025
					
					if (matchesBillNo && matchesMobileNo && matchesLicNo && matchesName && matchesAdaguDate) {
						tableModel.addRow(new Object[] { record.getCUSTOMER_PHONE(), record.getLICENCE_NUMBER(),
								record.getBILL_NUMBER(), record.getCUSTOMER_NAME(), record.getCUSTOMER_ADDRESS(),
								record.getADAGU_AMOUNT(), record.getADAGU_DATE(), record.getCANCEL_DATE(),
								record.getSTATUS() });
					}
				}
			}
		});
		//24Oct2025
		// Set the custom renderer for all columns
		int columnCount = table.getColumnModel().getColumnCount();

		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}
		//24Oct2025
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;

	}

	public JPanel createUnPaidBillsPanel() {
		JPanel panel = new JPanel();
		JTextField txtBillNo, txtMobileNo, txtLicenceNo, txtName;
		JButton btnSearch, btnEdit, btnDelete, btnCloseBill, btnExportPDF, btnGenerateBill, btnNotifyBill;
		JTable table;
		DefaultTableModel tableModel;
		panel.setLayout(new BorderLayout());
		// Header Panel (for search inputs)
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);
		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		txtLicenceNo = new JTextField(10);
		txtLicenceNo.setText("*****");
		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					txtLicenceNo.setText("*****");
				}
				isFirstImage = !isFirstImage;
			}
		});

		JLabel lblBillNo = new JLabel("Bill No:");
		txtBillNo = new JTextField(10);
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(10);

		btnSearch = new JButton("Search");

		headerPanel.add(licNoUrlLabel);
		headerPanel.add(lblBillNo);
		headerPanel.add(txtBillNo);
		headerPanel.add(lblName);
		headerPanel.add(txtName);
		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(
				new String[] { "PHONE", "LIC", "BILL", "NAME", "ADAGU AMT", "PAID AMT", "REMAINING AMT", "STATUS" }, 0);

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());

		btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.RED);
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnCloseBill = new JButton("Close Bill");
		btnCloseBill.setForeground(Color.RED);
		btnExportPDF = new JButton("Export PDF");
		btnExportPDF.setForeground(Color.RED);
		btnGenerateBill = new JButton("Re-Print Bill");
		btnGenerateBill.setForeground(Color.RED);
		btnNotifyBill = new JButton("Re-Notify Bill");
		btnNotifyBill.setForeground(Color.RED);

		// footerPanel.add(btnCloseBill);
		// footerPanel.add(btnGenerateBill);
		// footerPanel.add(btnNotifyBill);
		// footerPanel.add(btnExportPDF);

		panel.add(footerPanel, BorderLayout.SOUTH);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListUnpaidViewPanel = loadData.loadADAGUDataUnpaid();
				String billNo = txtBillNo.getText().trim();
				String mobileNo = txtMobileNo.getText().trim();
				String licenseno = txtLicenceNo.getText().toString().trim();
				String Name = txtName.getText().toUpperCase().trim().toString();
				tableModel.setRowCount(0);
				// Logic to filter records based on billNo, mobileNo, status, and billType
				for (AdaguBill record : AdaguBillListUnpaidViewPanel) {
					boolean matchesBillNo = billNo.isEmpty() || String.valueOf(record.getBILL_NUMBER()).equals(billNo);
					boolean matchesName = Name.isEmpty() || String.valueOf(record.getCUSTOMER_NAME()).equals(Name);
					boolean matchesLicNo = licenseno.isEmpty()
							|| String.valueOf(record.getLICENCE_NUMBER().toString().trim()).equals(licenseno);
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);
					if (matchesBillNo && matchesMobileNo && matchesLicNo && matchesName) {
						tableModel.addRow(new Object[] { record.getCUSTOMER_PHONE(), record.getLICENCE_NUMBER(),
								record.getBILL_NUMBER(), record.getCUSTOMER_NAME(), record.getADAGU_AMOUNT(),
								record.getPAID_AMOUNT(), record.getREMAINING_AMOUNT(), record.getSTATUS() });
					}
				}
			}
		});

		//24Oct2025
		// Set the custom renderer for all columns
		int columnCount = table.getColumnModel().getColumnCount();

		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}
		//24Oct2025
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;

	}

	// Panel for Add Sales Bill
	private JPanel createAddSalesBillPanel() {

		JPanel panel;
		JTextField salesAmt, custName, custPhone, productDetails, productWeight, custAddress, balanceAmt, balanceDesc,
				salesAmt2, oldbalanceAmt; //24Oct2025
		JLabel shopName;
		JButton saveBill, notifyBill, printBill, clearBill;
		JDateChooser salesDate;
		DefaultListModel<String> selectedProductsModel;
		JList<String> selectedProductsList;

		String[] productType = { "", "Gold", "Silver", "Gold & Silver" };
		JComboBox<String> productTypes = new JComboBox<>(productType);
		
		//07Oct2025
		String[] billType = { "", "91.6 KDM", "22 CT" };
		JComboBox<String> billTypes = new JComboBox<>(billType);
		//07Oct2025
		
		// 24Oct2025
		String[] statusList = { "", "Paid", "Pending" };
		JComboBox<String> status = new JComboBox<>(statusList);
		// 24Oct2025

		productTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				productGoldList = loadData.loadGoldData();
				productSilverList = loadData.loadSilverData();
			}
		});

		// Set up the JFrame
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		// setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to full screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());

		// Set a custom look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialize components with a larger font and bold text
		Font inputFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);

		shopName = new JLabel(" Sri Lalith Prasanna Jewellery - SALES BILL ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		salesAmt = new JTextFieldWithPlaceholder("Amount");
		salesAmt2 = new JTextFieldWithPlaceholder("Amount2");
		custName = new JTextFieldWithPlaceholder("Name");
		custPhone = new JTextFieldWithPlaceholder("Phone");
		productDetails = new JTextFieldWithPlaceholder("Products");
		productWeight = new JTextFieldWithPlaceholder("Weight");
		custAddress = new JTextFieldWithPlaceholder("Address");
		balanceAmt = new JTextFieldWithPlaceholder("Balance Amount");
		balanceDesc = new JTextFieldWithPlaceholder("Balance Description");
		salesDate = new JDateChooser();
		oldbalanceAmt = new JTextFieldWithPlaceholder("Old Balance Amount"); //24Oct2025

		//balanceAmt.setText("0"); //24Oct2025

		salesDate.setEnabled(true);
		LineBorder border = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
		salesAmt.setBorder(border);
		salesAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		salesAmt2.setBorder(border);
		salesAmt2.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custName.setBorder(border);
		custName.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custPhone.setBorder(border);
		custPhone.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productDetails.setBorder(border);
		productDetails.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productWeight.setBorder(border);
		productWeight.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		salesDate.setBorder(border);
		custAddress.setBorder(border);
		custAddress.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productTypes.setBorder(border);
		productTypes.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		balanceAmt.setBorder(border);
		balanceAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		balanceDesc.setBorder(border);
		balanceDesc.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		//07Oct2025
		billTypes.setBorder(border);
		billTypes.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		//07Oct2025
		
		//24Oct2025
		status.setBorder(border);
		status.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		status.setFont(inputFont);
		status.setPreferredSize(new Dimension(150, 50));
		//24Oct2025
		
		//24Oct2025
		oldbalanceAmt.setBorder(border);
		oldbalanceAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		oldbalanceAmt.setFont(inputFont);
		//24Oct2025

		// Set custom font for input fields and text area
		salesAmt.setFont(inputFont);
		salesAmt2.setFont(inputFont);
		custName.setFont(inputFont);
		custPhone.setFont(inputFont);
		productDetails.setFont(inputFont);
		productWeight.setFont(inputFont);
		custAddress.setFont(inputFont);
		balanceAmt.setFont(inputFont);
		balanceDesc.setFont(inputFont);

		productTypes.setFont(inputFont);
		productTypes.setPreferredSize(new Dimension(150, 50));
		//07Oct2025
		billTypes.setFont(inputFont);
		billTypes.setPreferredSize(new Dimension(150, 50));
		//07Oct2025
		
		//27Oct2025
		oldbalanceAmt.setFont(inputFont);
		//27Oct2025
		
		// Set button colors and styles
		saveBill = new JButton("Save");
		notifyBill = new JButton("Notify");
		printBill = new JButton("Print");
		clearBill = new JButton("New");

		saveBill.setBackground(new Color(56, 142, 60)); // Green
		saveBill.setForeground(Color.RED);
		notifyBill.setBackground(new Color(244, 67, 54)); // Red
		notifyBill.setForeground(Color.RED);
		printBill.setBackground(new Color(33, 150, 243)); // Blue
		printBill.setForeground(Color.RED);
		clearBill.setBackground(new Color(33, 150, 243)); // Blue
		clearBill.setForeground(Color.RED);

		saveBill.setFont(labelFont);
		notifyBill.setFont(labelFont);
		printBill.setFont(labelFont);
		clearBill.setFont(labelFont);

		salesDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		salesDate.setDate(new java.util.Date()); // Set default system date
		salesDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		salesDate.setFont(inputFont);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem newItem = new JMenuItem("New");
		popupMenu.add(newItem);

		productDetails.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productString = JOptionPane.showInputDialog(null, "Product Name : ", "Quantity Input",
						JOptionPane.QUESTION_MESSAGE);
				if (productString != null && !productString.trim().isEmpty()) {
					try {
						String productType = productTypes.getSelectedItem().toString();
						if (productType.length() < 1) {
							JOptionPane.showMessageDialog(null, "Choose Either Gold or Silver", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);
						} else {
							DBConnect saveProd = new DBConnect();
							boolean isSaved = saveProd.saveNewProduct(productType, productString);
							if (isSaved) {
								JOptionPane.showMessageDialog(null, "Product Saved Succesfully!", "Success",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
										JOptionPane.ERROR_MESSAGE);

							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In product Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupAddressMenu = new JPopupMenu();
		JMenuItem newAddressItem = new JMenuItem("New");
		popupAddressMenu.add(newAddressItem);

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterAddressList = loadData.loadAddressData();
			}
		});

		custAddress.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newAddressItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custAddressString = JOptionPane.showInputDialog(null, "Customer Address", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custAddressString != null && !custAddressString.trim().isEmpty()) {
					try {
						DBConnect saveAddress = new DBConnect();
						boolean isSaved = saveAddress.saveNewAddress(custAddressString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Address Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupNameMenu = new JPopupMenu();
		JMenuItem newNameItem = new JMenuItem("New");
		popupNameMenu.add(newNameItem);

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterCustomerList = loadData.loadCustomerData();
			}
		});

		custName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newNameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Name", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewName(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Name Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupHeirMenu = new JPopupMenu();
		JMenuItem newHeirItem = new JMenuItem("New");
		popupHeirMenu.add(newHeirItem);

		newHeirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Heir", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewHeir(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Heir Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();
				if (custPhone.getText().length() >= 10) {
					String phoneNumber = custPhone.getText().trim();
					AdaguBill latestBill = null;
					for (int i = AdaguBillListAdaguPanel.size() - 1; i >= 0; i--) {
						AdaguBill bill = AdaguBillListAdaguPanel.get(i);
						if (bill.getCUSTOMER_PHONE().equals(phoneNumber)) {
							latestBill = bill;
							break;
						}
					}
					if (latestBill != null) {
						custName.setText(latestBill.getCUSTOMER_NAME());
						custName.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custAddress.setText(latestBill.getCUSTOMER_ADDRESS());
						custAddress.setForeground(new Color(247, 25, 92)); // Change text color when typing
					} else {

					}
				}
			}
		});

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		selectedProductsModel = new DefaultListModel<>();
		selectedProductsList = new JList<>(selectedProductsModel);
		selectedProductsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedProductsList.setFont(new Font("Arial", Font.PLAIN, 14));
		selectedProductsList.setVisibleRowCount(5);
		selectedProductsList.setForeground(new Color(247, 25, 92)); // Change text color when typing

		JScrollPane scrollPane = new JScrollPane(selectedProductsList);
		scrollPane.setPreferredSize(new Dimension(200, 50));
		scrollPane.setBorder(border);
		scrollPane.setSize(100, 50);
		scrollPane.setPreferredSize(getMaximumSize());
		// panel.add(scrollPane);
		selectedProductsList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					String selectedProduct = selectedProductsList.getSelectedValue();
					if (selectedProduct != null) {
						selectedProductsModel.removeElement(selectedProduct);
					}
				}
			}
		});

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);

		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		shopNamePanel.add(shopName, BorderLayout.CENTER);
		// shopNamePanel.add(licNoUrlLabel, BorderLayout.WEST);

		URL notifyUrl = TextFieldWithIcon.class.getResource("/images/mail.png");
		ImageIcon notifyicon = new ImageIcon(notifyUrl);
		Image notifyimage = notifyicon.getImage();
		Image notifyImage = notifyimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon notifyIcon = new ImageIcon(notifyImage);
		JLabel notifyLabel = new JLabel(notifyIcon);

		URL printUrl = TextFieldWithIcon.class.getResource("/images/printer.png");
		ImageIcon printicon = new ImageIcon(printUrl);
		Image printimage = printicon.getImage();
		Image printImage = printimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon printIcon = new ImageIcon(printImage);
		JLabel printLabel = new JLabel(printIcon);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 2));
		rightPanel.add(notifyLabel);
		rightPanel.add(printLabel);
		shopNamePanel.add(rightPanel, BorderLayout.LINE_END);

		panel.add(shopNamePanel, gbc);

		notifyLabel.addMouseListener(new MouseAdapter() {

		});

		printLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				BigDecimal SALESAMT = new BigDecimal(0);
				BigDecimal SALESAMT2 = new BigDecimal(0);
				BigDecimal BALANCEAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String PRODUCTTYPE = "";
				String BILLTYPE = ""; //07Oct2025
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				String SALESDATEFORMATTED = "";
				String BALANCEDESC = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (salesAmt.getText().trim().equals("") || salesAmt.getText().trim().equals(null)
						|| salesAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Sales Amount !!! ");
					salesAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				SALESAMT = new BigDecimal(salesAmt.getText().toString().trim());
				if(null != salesAmt2.getText().toString() && !"".equals(salesAmt2.getText().toString())) //16Sep2025
					SALESAMT2 = new BigDecimal(salesAmt2.getText().toString().trim());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString().trim());
				CUSTNAME = custName.getText().toString().trim();
				CUSTADDRESS = custAddress.getText().toString().trim();
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				if (null != salesDate.getDate()) {
					SALESDATEFORMATTED = sdf.format(salesDate.getDate());
				}
				PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
				BILLTYPE = billTypes.getSelectedItem().toString().trim(); //07Oct2025
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + "~");
				}
				if(null != balanceAmt.getText().toString() && !"".equals(balanceAmt.getText().toString())) //16Sep2025
					BALANCEAMT = new BigDecimal(balanceAmt.getText().toString().trim());
				BALANCEDESC = balanceDesc.getText().toString().trim();

				SalesBill salesData = new SalesBill();
				salesData.setCUSTOMER_PHONE("" + CUSTPHONE);
				salesData.setSALES_DATE(SALESDATEFORMATTED);
				salesData.setCUSTOMER_NAME(CUSTNAME);
				salesData.setCUSTOMER_ADDRESS(CUSTADDRESS);
				salesData.setPRODUCT_TYPE(PRODUCTTYPE);
				salesData.setBILL_TYPES(BILLTYPE); //07Oct2025
				salesData.setPRODUCTS(PRODUCT.toString().toUpperCase());
				salesData.setPRODUCT_WEIGHT(PRODUCTWEIGTH);
				salesData.setSALES_AMOUNT("" + SALESAMT);
				salesData.setSALES_AMOUNT2("" + SALESAMT2);
				salesData.setBALANCE_DESCRIPTION(BALANCEDESC);
				salesData.setBALANCE_AMOUNT("" + BALANCEAMT);
				
				//27Oct2025
				GSRate latestGSRate = new GSRate();
				DBConnect getLatest = new DBConnect();
				latestGSRate = getLatest.getLatestGSRatesData();
				if(null != latestGSRate)
				{
					salesData.setDATE(latestGSRate.getDATE());
					salesData.setGOLD_RATE("Gold ₹/gm : "+latestGSRate.getGOLD_RATE());
					salesData.setSILVER_RATE("Silver ₹/gm : "+latestGSRate.getSILVER_RATE());
				}else {
					salesData.setDATE("");
					salesData.setGOLD_RATE("");
					salesData.setSILVER_RATE("");
				}
				//27Oct2025

				PrintBill print = new PrintBill(CUSTPHONE, SALESDATEFORMATTED, CUSTNAME, CUSTADDRESS, PRODUCTTYPE,
						PRODUCT, PRODUCTWEIGTH, SALESAMT, SALESAMT2, BALANCEDESC, BALANCEAMT, BILLTYPE); //07Oct2025
				//27Oct2025
				print.setPRODUCT_TYPE(PRODUCTTYPE);
				print.setDATE(salesData.getDATE());
				print.setGOLD_RATE(salesData.getGOLD_RATE());
				print.setSILVER_RATE(salesData.getSILVER_RATE());
				//27Oct2025
				String printFileName = "" + CUSTPHONE + "_" + CUSTNAME + ".pdf";
				try {
					print.printFormSales(print, printFileName);
				} catch (DocumentException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		URL custPhoneUrl = TextFieldWithIcon.class.getResource("/images/telephone.png");
		ImageIcon custPhoneicon = new ImageIcon(custPhoneUrl);
		Image custPhoneimage = custPhoneicon.getImage();
		Image custPhoneImage = custPhoneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custPhoneIcon = new ImageIcon(custPhoneImage);
		JPanel custPhonePanel = new JPanel(new BorderLayout());
		JLabel custPhoneLabel = new JLabel(custPhoneIcon);
		custPhonePanel.add(custPhoneLabel, BorderLayout.WEST);
		JPanel Panel1 = new JPanel(new GridLayout(1, 2));
		Panel1.add(custPhone);
		Panel1.add(new JLabel(""));
		custPhonePanel.add(Panel1, BorderLayout.CENTER);
		panel.add(custPhonePanel, gbc);

		URL adagudateUrl = TextFieldWithIcon.class.getResource("/images/adaguDate.png");
		ImageIcon adagudateicon = new ImageIcon(adagudateUrl);
		Image adagudateimage = adagudateicon.getImage();
		Image adagudateImage = adagudateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaudateIcon = new ImageIcon(adagudateImage);
		JPanel adagudatePanel = new JPanel(new BorderLayout());
		JLabel adagudateLabel = new JLabel(adaudateIcon);
		adagudatePanel.add(adagudateLabel, BorderLayout.WEST);
		JPanel Panel2 = new JPanel(new GridLayout(1, 2));
		Panel2.add(salesDate);
		Panel2.add(new JLabel(""));
		adagudatePanel.add(Panel2, BorderLayout.CENTER);
		panel.add(adagudatePanel, gbc);

		URL custNameUrl = TextFieldWithIcon.class.getResource("/images/label.png");
		ImageIcon custNameicon = new ImageIcon(custNameUrl);
		Image custNameimage = custNameicon.getImage();
		Image custNameImage = custNameimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custNameIcon = new ImageIcon(custNameImage);
		JPanel custNamePanel = new JPanel(new BorderLayout());
		JLabel custNameLabel = new JLabel(custNameIcon);
		custNamePanel.add(custNameLabel, BorderLayout.WEST);
		JPanel Panel3 = new JPanel(new GridLayout(1, 2));
		Panel3.add(custName);
		Panel3.add(new JLabel(""));
		custNamePanel.add(Panel3, BorderLayout.CENTER);
		panel.add(custNamePanel, gbc);

		URL custAddressUrl = TextFieldWithIcon.class.getResource("/images/gps.png");
		ImageIcon custAddressicon = new ImageIcon(custAddressUrl);
		Image custAddressimage = custAddressicon.getImage();
		Image custAddressImage = custAddressimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custAddressIcon = new ImageIcon(custAddressImage);
		JPanel custAddressPanel = new JPanel(new BorderLayout());
		JLabel custAddressLabel = new JLabel(custAddressIcon);
		custAddressPanel.add(custAddressLabel, BorderLayout.WEST);
		JPanel Panel4 = new JPanel(new GridLayout(1, 2));
		Panel4.add(custAddress);
		Panel4.add(new JLabel(""));
		custAddressPanel.add(Panel4, BorderLayout.CENTER);
		panel.add(custAddressPanel, gbc);

		URL productDetailsUrl = TextFieldWithIcon.class.getResource("/images/product.png");
		ImageIcon productDetailsicon = new ImageIcon(productDetailsUrl);
		Image productDetailsimage = productDetailsicon.getImage();
		Image productDetailsImage = productDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productDetailsIcon = new ImageIcon(productDetailsImage);
		JPanel productDetailsPanel = new JPanel(new BorderLayout());
		JLabel productDetailsLabel = new JLabel(productDetailsIcon);
		productDetailsPanel.add(productDetailsLabel, BorderLayout.WEST);
		productTypes.setSelectedIndex(0);
		billTypes.setSelectedIndex(0); //07Oct2025
		// productDetailsPanel.add(productTypes, BorderLayout.EAST);
		JPanel Panel5 = new JPanel(new GridLayout(1, 3));
		Panel5.add(billTypes); //07Oct2025
		Panel5.add(productTypes);
		Panel5.add(productDetails);
		Panel5.add(new JLabel(""));
		Panel5.add(new JLabel(""));
		Panel5.add(new JLabel(""));
		productDetailsPanel.add(Panel5, BorderLayout.CENTER);
		panel.add(productDetailsPanel, gbc);

		URL scrollPaneUrl = TextFieldWithIcon.class.getResource("/images/scroll.png");
		ImageIcon scrollPaneicon = new ImageIcon(scrollPaneUrl);
		Image scrollPaneimage = scrollPaneicon.getImage();
		Image scrollPaneImage = scrollPaneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon scrollPaneIcon = new ImageIcon(scrollPaneImage);
		JPanel scrollPanePanel = new JPanel(new BorderLayout());
		JLabel scrollPaneLabel = new JLabel(scrollPaneIcon);
		scrollPanePanel.add(scrollPaneLabel, BorderLayout.WEST);
		JPanel Panel6 = new JPanel(new GridLayout(1, 2));
		Panel6.add(scrollPane);
		Panel6.add(new JLabel(""));
		scrollPanePanel.add(Panel6, BorderLayout.CENTER);
		panel.add(scrollPanePanel, gbc);

		URL productWeightUrl = TextFieldWithIcon.class.getResource("/images/weight.png");
		ImageIcon productWeighticon = new ImageIcon(productWeightUrl);
		Image productWeightimage = productWeighticon.getImage();
		Image productWeightImage = productWeightimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productWeightIcon = new ImageIcon(productWeightImage);
		JPanel productWeightPanel = new JPanel(new BorderLayout());
		JLabel productWeightLabel = new JLabel(productWeightIcon);
		productWeightPanel.add(productWeightLabel, BorderLayout.WEST);
		JPanel Panel7 = new JPanel(new GridLayout(1, 2));
		Panel7.add(productWeight);
		Panel7.add(new JLabel(""));
		productWeightPanel.add(Panel7, BorderLayout.CENTER);
		panel.add(productWeightPanel, gbc);

		URL adaguAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguAmticon = new ImageIcon(adaguAmtUrl);
		Image adaguAmtimage = adaguAmticon.getImage();
		Image adaguAmtImage = adaguAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguAmtIcon = new ImageIcon(adaguAmtImage);
		JPanel adaguAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguAmtLabel = new JLabel(adaguAmtIcon);
		adaguAmtPanel.add(adaguAmtLabel, BorderLayout.WEST);
		JPanel adaguAmTPanel = new JPanel(new GridLayout(1, 3));
		adaguAmTPanel.add(salesAmt);
		adaguAmTPanel.add(salesAmt2);
		adaguAmTPanel.add(new JLabel(""));
		adaguAmTPanel.add(new JLabel(""));
		adaguAmtPanel.add(adaguAmTPanel, BorderLayout.CENTER);
		panel.add(adaguAmtPanel, gbc);

		URL balanceDescUrl = TextFieldWithIcon.class.getResource("/images/balance.png");
		ImageIcon balanceDescicon = new ImageIcon(balanceDescUrl);
		Image balanceDescimage = balanceDescicon.getImage();
		Image balanceDescImage = balanceDescimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon balanceDescIcon = new ImageIcon(balanceDescImage);
		JPanel balanceDescPanel = new JPanel(new BorderLayout());
		JLabel balanceDescLabel = new JLabel(balanceDescIcon);
		balanceDescPanel.add(balanceDescLabel, BorderLayout.WEST);
		JPanel Panel8 = new JPanel(new GridLayout(1, 2));
		Panel8.add(balanceDesc);
		Panel8.add(new JLabel(""));
		balanceDescPanel.add(Panel8, BorderLayout.CENTER);
		panel.add(balanceDescPanel, gbc);

		URL balanceAmtUrl = TextFieldWithIcon.class.getResource("/images/balance.png");
		ImageIcon balanceAmticon = new ImageIcon(balanceAmtUrl);
		Image balanceAmtimage = balanceAmticon.getImage();
		Image balanceAmtImage = balanceAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon balanceAmtIcon = new ImageIcon(balanceAmtImage);
		JPanel balanceAmtPanel = new JPanel(new BorderLayout());
		JLabel balanceAmtLabel = new JLabel(balanceAmtIcon);
		balanceAmtPanel.add(balanceAmtLabel, BorderLayout.WEST);
		JPanel Panel9 = new JPanel(new GridLayout(1, 2));
		Panel9.add(balanceAmt);
		Panel9.add(new JLabel(""));
		balanceAmtPanel.add(Panel9, BorderLayout.CENTER);
		panel.add(balanceAmtPanel, gbc);
		
		//24Oct2025
		URL redemptionDateUrl = TextFieldWithIcon.class.getResource("/images/expiry.png");
		ImageIcon redemptionDateicon = new ImageIcon(redemptionDateUrl);
		Image redemptionDateimage = redemptionDateicon.getImage();
		Image redemptionDateImage = redemptionDateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon redemptionDateIcon = new ImageIcon(redemptionDateImage);
		JPanel redemptionDatePanel = new JPanel(new BorderLayout());
		JLabel redemptionDateLabel = new JLabel(redemptionDateIcon);
		redemptionDatePanel.add(redemptionDateLabel, BorderLayout.WEST);
		status.setSelectedIndex(0);
		redemptionDatePanel.add(status, BorderLayout.EAST);
		JPanel redemptionDatesPanel = new JPanel(new GridLayout(1, 3));
		redemptionDatesPanel.add(status);
		redemptionDatesPanel.add(oldbalanceAmt); //24Oct2025
		//27Oct2025
		redemptionDatesPanel.add(new JLabel(""));
		redemptionDatesPanel.add(new JLabel(""));
		//27Oct2025
		redemptionDatePanel.add(redemptionDatesPanel, BorderLayout.CENTER);
		panel.add(redemptionDatePanel, gbc);
		//24Oct2025

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 4));
		bottomPanel.add(saveBill);
		bottomPanel.add(clearBill);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		JPanel bottomPanel1 = new JPanel(new BorderLayout());
		bottomPanel1.add(bottomPanel, BorderLayout.CENTER);
		panel.add(bottomPanel1, gbc);

		clearBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salesAmt.setEditable(true);
				salesAmt.setText("Amount");
				salesAmt.setForeground(new Color(157, 161, 250));
				salesAmt2.setEditable(true);
				salesAmt2.setText("Amount2");
				salesAmt2.setForeground(new Color(157, 161, 250));
				custPhone.setEditable(true);
				custPhone.setText("Phone");
				custPhone.setForeground(new Color(157, 161, 250));
				custName.setEditable(true);
				custName.setText("Name");
				custName.setForeground(new Color(157, 161, 250));
				custAddress.setEditable(true);
				custAddress.setText("Address");
				custAddress.setForeground(new Color(157, 161, 250));
				productTypes.setSelectedIndex(0);
				productTypes.setEditable(true);
				productWeight.setEditable(true);
				productWeight.setText("Weight");
				productWeight.setForeground(new Color(157, 161, 250));
				productDetails.setEditable(true);
				productDetails.setText("Products");
				productDetails.setForeground(new Color(157, 161, 250));
				selectedProductsModel.clear();
				salesDate.setDate(new java.util.Date()); // Set default system date
				saveBill.setEnabled(true);
				// 08Aug2025
				balanceAmt.setText("Balance Amount");
				balanceAmt.setForeground(new Color(157, 161, 250));
				balanceAmt.setEditable(true);
				balanceDesc.setText("Balance Description");
				balanceDesc.setForeground(new Color(157, 161, 250));
				balanceDesc.setEditable(true);
				// 08Aug2025
				//07Oct2025
				billTypes.setSelectedIndex(0);
				billTypes.setEditable(true);
				//07Oct2025
				//24Oct2025
				status.setSelectedIndex(0);
				status.setEditable(true);
				//24Oct2025
				
				//24Oct2025
				oldbalanceAmt.setText("Old Balance Amount");
				oldbalanceAmt.setForeground(new Color(157, 161, 250));
				oldbalanceAmt.setEditable(true);
				//24Oct2025
			}
		});

		productDetails.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = productDetails.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				String PRODUCTTYPE = productTypes.getSelectedItem().toString();
				if (PRODUCTTYPE.equals("Gold")) {
					int i = 0;
					for (String product : productGoldList) {
						if (product.toLowerCase().startsWith(typedText) && i <= 15) {
							suggestions.add(product);
							i++;
						}
					}
				} else if (PRODUCTTYPE.equals("Silver")) {
					int j = 0;
					for (String product : productSilverList) {
						if (product.toLowerCase().startsWith(typedText) && j <= 15) {
							suggestions.add(product);
							j++;
						}
					}
				}

				// Show suggestions in a combo box if any
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									int qty = 0;
									String qtyString = JOptionPane.showInputDialog(null, "" + suggestion + " : ",
											"Quantity Input", JOptionPane.QUESTION_MESSAGE);
									if (qtyString != null && !qtyString.trim().isEmpty()) {
										try {
											int quantity = Integer.parseInt(qtyString);
											if (quantity > 0) {
												qty = quantity;
											} else {
												JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.",
														"Error", JOptionPane.ERROR_MESSAGE);
												qty = 0;
											}
										} catch (NumberFormatException ex) {
											JOptionPane.showMessageDialog(null, "Please enter a valid number.",
													"Invalid Input", JOptionPane.ERROR_MESSAGE);
											qty = 0;
										}
									} else {
										JOptionPane.showMessageDialog(null, "Quantity is required.", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
//									selectedProductsModel.addElement(suggestion + " - " + qty);
									//selectedProductsModel.addElement(PRODUCTTYPE + " - " + suggestion + " - " + qty); // 23July2025
									selectedProductsModel.addElement(suggestion + "-" + qty); //27Oct2025
									productDetails.setText(""); // Clear text field after selection
								}
								// Hide the popup after selection
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(productDetails, 0, productDetails.getHeight());

				}
			}

		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		salesAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		salesAmt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		productWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = productWeight.getText().toString().trim();
				if ((!Character.isDigit(c) && c != '.' && c != '/') || (c == '/' && text.split("/+").length > 1)) {
					e.consume();
				}
			}

			// 30Apr2025 - Start
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String proWgt = productWeight.getText().trim();
					try {
						double weight = Double.parseDouble(proWgt);
						String formattedWeight = String.format("%.3f", weight);
						productWeight.setText(formattedWeight);
					} catch (NumberFormatException ex) {
						if (proWgt.isEmpty()) {
							productWeight.setText("");
						}
					}
				}
			}
			// 30Apr2025 - End
		});

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custName.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterCustomerList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custName.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custName, 0, custName.getHeight());
				}
			}
		});

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custAddress.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterAddressList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custAddress.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custAddress, 0, custAddress.getHeight());
				}
			}
		});

		notifyBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		saveBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 30Apr2025
				BigDecimal SALESAMT = new BigDecimal(0);
				BigDecimal SALESAMT2 = new BigDecimal(0);
				BigDecimal BALANCEAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String PRODUCTTYPE = "";
				String BILLTYPE = ""; //07Oct2025
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				String SALESDATEFORMATTED = "";
				String BALANCEDESC = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;
				String STATUS = ""; //24Oct2025
				String OLDBALANCEAMT = ""; //24Oct2025

				if (salesAmt.getText().trim().equals("") || salesAmt.getText().trim().equals(null)
						|| salesAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Sales Amount !!! ");
					salesAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				SALESAMT = new BigDecimal(salesAmt.getText().toString().trim());
				SALESAMT2 = new BigDecimal(salesAmt2.getText().toString().trim());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString().trim());
				CUSTNAME = custName.getText().toString().trim();
				CUSTADDRESS = custAddress.getText().toString().trim();
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				if (null != salesDate.getDate()) {
					SALESDATEFORMATTED = sdf.format(salesDate.getDate());
				}
				PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
				BILLTYPE = billTypes.getSelectedItem().toString().trim(); //07Oct2025
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + "~");
				}
				BALANCEAMT = new BigDecimal(balanceAmt.getText().toString().trim());
				BALANCEDESC = balanceDesc.getText().toString().trim();
				STATUS = status.getSelectedItem().toString().trim(); //24Oct2025
				OLDBALANCEAMT = oldbalanceAmt.getText().toString().trim(); //24Oct2025

				SalesBill salesData = new SalesBill();
				salesData.setCUSTOMER_PHONE("" + CUSTPHONE);
				salesData.setSALES_DATE(SALESDATEFORMATTED);
				salesData.setCUSTOMER_NAME(CUSTNAME);
				salesData.setCUSTOMER_ADDRESS(CUSTADDRESS);
				salesData.setPRODUCT_TYPE(PRODUCTTYPE);
				salesData.setPRODUCTS(PRODUCT.toString().toUpperCase());
				salesData.setPRODUCT_WEIGHT(PRODUCTWEIGTH);
				salesData.setSALES_AMOUNT("" + SALESAMT);
				salesData.setSALES_AMOUNT2("" + SALESAMT2);
				salesData.setBALANCE_DESCRIPTION(BALANCEDESC);
				salesData.setBALANCE_AMOUNT("" + BALANCEAMT);
				salesData.setBILL_TYPES(BILLTYPE);
				salesData.setOLDBALANCE_AMOUNT(OLDBALANCEAMT); //24Oct2025
				//24Oct2025
				if (STATUS.equals("Paid")) {
					salesData.setSTATUS("Paid");
				} else {
					salesData.setSTATUS("Pending");
				}
				//24Oct2025
				
				//27Oct2025
				GSRate latestGSRate = new GSRate();
				DBConnect getLatest = new DBConnect();
				latestGSRate = getLatest.getLatestGSRatesData();
				if(null != latestGSRate)
				{
					salesData.setDATE(latestGSRate.getDATE());
					salesData.setGOLD_RATE("Gold ₹/gm : "+latestGSRate.getGOLD_RATE());
					salesData.setSILVER_RATE("Silver ₹/gm : "+latestGSRate.getSILVER_RATE());
				}else {
					salesData.setDATE("");
					salesData.setGOLD_RATE("");
					salesData.setSILVER_RATE("");
				}
				//27Oct2025
				
				DBConnect saveSales = new DBConnect();
				boolean isSaved = false;
				isSaved = saveSales.saveSalesBill(salesData);

				if (isSaved) {
					JOptionPane.showConfirmDialog(panel, " Bill Saved Successfully !!! ");
					saveBill.setEnabled(false);
				} else {
					JOptionPane.showConfirmDialog(panel, " Error : Save Bill !!! ");
				}

				salesDate.setBackground(Color.LIGHT_GRAY);
				salesAmt.setEditable(false);
				salesAmt.setBackground(Color.LIGHT_GRAY);
				salesAmt.setBorder(correctborder);
				salesAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				salesAmt2.setEditable(false);
				salesAmt2.setBackground(Color.LIGHT_GRAY);
				salesAmt2.setBorder(correctborder);
				salesAmt2.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				salesAmt.setEditable(false);
				salesAmt.setBackground(Color.LIGHT_GRAY);
				salesAmt.setBorder(correctborder);
				salesAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				salesAmt2.setEditable(false);
				salesAmt2.setBackground(Color.LIGHT_GRAY);
				salesAmt2.setBorder(correctborder);
				salesAmt2.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				salesDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				balanceAmt.setEditable(false);
				balanceAmt.setBackground(Color.LIGHT_GRAY);
				balanceAmt.setBorder(correctborder);
				balanceAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				balanceDesc.setEditable(false);
				balanceDesc.setBackground(Color.LIGHT_GRAY);
				balanceDesc.setBorder(correctborder);
				balanceDesc.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				//24Oct2025
				oldbalanceAmt.setEditable(false);
				oldbalanceAmt.setBackground(Color.LIGHT_GRAY);
				oldbalanceAmt.setBorder(correctborder);
				oldbalanceAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				//24Oct2025
			}
		});
		
		
		//27Oct2025
		JPopupMenu popupOldBalanceMenu = new JPopupMenu();
		JMenuItem popupOldBalanceItem = new JMenuItem("Add Old Balance");
		popupOldBalanceMenu.add(popupOldBalanceItem);

		oldbalanceAmt.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupOldBalanceMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupOldBalanceMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		popupOldBalanceItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setTitle("Choose Bill Num");
				dialog.setSize(500, 400);
				JPanel checkBoxPanel = new JPanel();
				checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
				List<JCheckBox> checkBoxes = new ArrayList<>();
				DBConnect loadData = new DBConnect();
				String mobileNo = "";
				mobileNo = custPhone.getText().trim().toString();
				openOldBalanceAmt = loadData.loadOldOpenBalance(mobileNo);
				for (String option : openOldBalanceAmt) {
					JCheckBox checkBox = new JCheckBox(option);
					checkBoxes.add(checkBox);
					checkBoxPanel.add(checkBox);
				}
				JScrollPane scrollPanel = new JScrollPane(checkBoxPanel);
				scrollPanel.setPreferredSize(new Dimension(350, 150));
				JButton saveButton = new JButton("Add Balance");
				saveButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						List<String> selectedOptions = new ArrayList<>();
						for (JCheckBox checkBox : checkBoxes) {
							if (checkBox.isSelected()) {
								selectedOptions.add(checkBox.getText());
							}
						}
						if (selectedOptions.isEmpty()) {
							JOptionPane.showMessageDialog(dialog, "Please Choose Bill Num.");
						} else {
							String currentOpenBal = balanceAmt.getText().toString();
							StringBuffer openBal = new StringBuffer();
							if(!currentOpenBal.equalsIgnoreCase("Balance Amount"))
								openBal.append("SALES -> "+currentOpenBal+"\n");
							for (String billNO : selectedOptions) {
								openBal.append(billNO);
							}
							oldbalanceAmt.setText(openBal.toString());
						}
						dialog.dispose();
					}
				});
				JPanel paneling = new JPanel();
				paneling.setLayout(new BorderLayout());
				paneling.add(scrollPanel, BorderLayout.CENTER);
				paneling.add(saveButton, BorderLayout.SOUTH);
				dialog.add(paneling);
				dialog.setVisible(true);
			}
		});
		
		//27Oct2025

		setVisible(true);

		return panel;

	}

	private JPanel createViewSalesBillPanel() {

		JPanel panel = new JPanel();
		JTextField txtBillNo, txtMobileNo, txtName;
		JButton btnSearch, btnEdit, btnDelete, btnCloseBill, btnExportPDF, btnGenerateBill, btnNotifyBill;
		JComboBox<String> cmbStatus;
		JTable table;
		JDateChooser adaguDate;
		DefaultTableModel tableModel;

		panel.setLayout(new BorderLayout());

		// Header Panel (for search inputs)
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());

		JLabel lblBillNo = new JLabel("Bill No:");
		txtBillNo = new JTextField(10);
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(10);

		JLabel lblStatus = new JLabel("Status:");
		cmbStatus = new JComboBox<>(new String[] { "", "Paid", "Pending" });

		JLabel adaguDateLabel = new JLabel("Sales Date:");
		adaguDate = new JDateChooser();
		adaguDate.setDateFormatString("dd-MM-yyyy");

		btnSearch = new JButton("Search");

		headerPanel.add(lblBillNo);
		headerPanel.add(txtBillNo);
		headerPanel.add(lblName);
		headerPanel.add(txtName);
		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(lblStatus);
		headerPanel.add(cmbStatus);
		headerPanel.add(adaguDateLabel);
		headerPanel.add(adaguDate);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(
				new String[] { "ID", "PHONE", "DATE", "NAME", "ADDRESS", "PRODUCT", "WEIGHT", "AMT", "BALANCE", "STATUS" }, 0); // 08Aug2025 //07Oct2025 //24Oct2025

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem editItem = new JMenuItem("View");
		popupMenu.add(editItem); //24Oct2025
		JMenuItem paymentItem = new JMenuItem("Pay Bill");
		// popupMenu.add(paymentItem); //08Aug2025
		JMenuItem deleteItem = new JMenuItem("Delete");
		//popupMenu.add(deleteItem);
		table.setComponentPopupMenu(popupMenu);

		// 08Aug2025
		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					int SALESID = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
					BigDecimal billNo = new BigDecimal(SALESID);
					SalesBill selectedRecord = null;
					for (SalesBill record : SalesBillListViewPanel) {
						if (new BigDecimal(record.getAD_SALESDATA_ID()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						int option = JOptionPane.showConfirmDialog(null, "Sure Need To Delete This Record ?",
								"Delete Record", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.removeRow(selectedRow);
							DBConnect deleteRecord = new DBConnect();
							boolean isDeleted = deleteRecord.deleteSalesData("" + selectedRecord.getAD_SALESDATA_ID());
							if (isDeleted) {
								JOptionPane.showMessageDialog(panel, "Record Deleted Successfully!");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Record Deletion");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record Deletion");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Delete.");
				}

			}
		});
		// 08Aug2025

		// 24Oct2025
		editItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// "ID", "PHONE", "DATE", "NAME", "ADDRESS", "PRODUCT", "WEIGHT", "AMT",
				// "BALANCE"
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					int SALESID = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
					BigDecimal billNo = new BigDecimal(SALESID);
					SalesBill selectedRecord = null;
					for (SalesBill record : SalesBillListViewPanel) {
						if (new BigDecimal(record.getAD_SALESDATA_ID()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField CUSTOMER_PHONE = new JTextField(selectedRecord.getCUSTOMER_PHONE());
						CUSTOMER_PHONE.setEditable(false);
						JTextField SALES_DATE = new JTextField(selectedRecord.getSALES_DATE());
						SALES_DATE.setEditable(false);
						JTextField CUSTOME_NAME = new JTextField(selectedRecord.getCUSTOMER_NAME());
						CUSTOME_NAME.setEditable(false);
						JTextField CUSTOMER_ADDRESS = new JTextField(selectedRecord.getCUSTOMER_ADDRESS());
						CUSTOMER_ADDRESS.setEditable(false);
						JTextField PRODUCT = new JTextField(selectedRecord.getPRODUCTS());
						PRODUCT.setEditable(false);
						JTextField PRODUCT_TYPE = new JTextField(selectedRecord.getPRODUCT_TYPE());
						PRODUCT_TYPE.setEditable(false);
						JTextField PRODUCT_WEIGHT = new JTextField(selectedRecord.getPRODUCT_WEIGHT());
						PRODUCT_WEIGHT.setEditable(false);
						JTextField SALES_AMOUNT = new JTextField(selectedRecord.getSALES_AMOUNT());
						SALES_AMOUNT.setEditable(false);
						JTextField BALANCE_DESC = new JTextField(selectedRecord.getBALANCE_DESCRIPTION());
						BALANCE_DESC.setEditable(false);
						JTextField BALANCE_AMOUNT = new JTextField(selectedRecord.getBALANCE_AMOUNT());
						BALANCE_AMOUNT.setEditable(false);

						Object[] message = { "Phone", CUSTOMER_PHONE, "Sales Date", SALES_DATE, "Name", CUSTOME_NAME,
								"Address", CUSTOMER_ADDRESS, "Product", PRODUCT, "Weight", PRODUCT_WEIGHT, "Amount",
								SALES_AMOUNT };
						int option = JOptionPane.showConfirmDialog(null, message, "View Record",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {

						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record View");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to View");
				}

			}
		});
		//24Oct2025

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());

		btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.RED);
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnCloseBill = new JButton("Close Bill");
		btnCloseBill.setForeground(Color.RED);
		btnExportPDF = new JButton("Export PDF");
		btnExportPDF.setForeground(Color.RED);
		btnGenerateBill = new JButton("Re-Print Bill");
		btnGenerateBill.setForeground(Color.RED);
		btnNotifyBill = new JButton("Re-Notify Bill");
		btnNotifyBill.setForeground(Color.RED);

		// footerPanel.add(btnCloseBill);
		footerPanel.add(btnGenerateBill); // 08Aug2025
		// footerPanel.add(btnNotifyBill);
		// footerPanel.add(btnExportPDF);
		
		//07Oct2025
		URL dPrintUrl = TextFieldWithIcon.class.getResource("/images/dummyprint.png");
		ImageIcon dPrintUrlicon = new ImageIcon(dPrintUrl);
		Image dPrintUrlimage = dPrintUrlicon.getImage();
		Image dPrintUrlImage = dPrintUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon dPrintUrlIcon = new ImageIcon(dPrintUrlImage);
		JLabel dPrintUrlLabel = new JLabel(dPrintUrlIcon);
		footerPanel.add(dPrintUrlLabel); 
		//07Oct2025

		panel.add(footerPanel, BorderLayout.SOUTH);

		//07Oct2025
		dPrintUrlLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PrintBill print = new PrintBill();
				String printFileName = "DummySalesBill.pdf";
				try {
					print.printFormSales(print, printFileName);
				} catch (DocumentException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		//07Oct2025
		
		// 08Aug2025
		btnGenerateBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					int SALESID = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
					BigDecimal billNo = new BigDecimal(SALESID);
					SalesBill selectedRecord = null;
					for (SalesBill record : SalesBillListViewPanel) {
						if (new BigDecimal(record.getAD_SALESDATA_ID()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {

						BigDecimal SALESAMT = new BigDecimal(0);
						BigDecimal SALESAMT2 = new BigDecimal(0);
						BigDecimal BALANCEAMT = new BigDecimal(0);
						BigDecimal CUSTPHONE = new BigDecimal(0);
						String CUSTNAME = "";
						String CUSTADDRESS = "";
						String PRODUCTTYPE = "";
						String BILLTYPE = ""; //07Oct2025
						String PRODUCTWEIGTH = "";
						StringBuffer PRODUCT = new StringBuffer("");
						String SALESDATEFORMATTED = "";
						String BALANCEDESC = "";
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
						LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
						Boolean error = false;

						SALESAMT = new BigDecimal(selectedRecord.getSALES_AMOUNT().toString());
						if(null != selectedRecord.getSALES_AMOUNT2().toString() && !"".equals(selectedRecord.getSALES_AMOUNT2().toString())) //16Sep2025
							SALESAMT2 = new BigDecimal(selectedRecord.getSALES_AMOUNT2().toString());
						CUSTPHONE = new BigDecimal(selectedRecord.getCUSTOMER_PHONE());
						CUSTNAME = selectedRecord.getCUSTOMER_NAME();
						CUSTADDRESS = selectedRecord.getCUSTOMER_ADDRESS();
						PRODUCTWEIGTH = selectedRecord.getPRODUCT_WEIGHT();
						if (null != selectedRecord.getSALES_DATE()) {
							SALESDATEFORMATTED = selectedRecord.getSALES_DATE();
						}
						PRODUCTTYPE = selectedRecord.getPRODUCT_TYPE();
						PRODUCT.append("" + selectedRecord.getPRODUCTS());
						if(null != selectedRecord.getBALANCE_AMOUNT().toString() && !"".equals(selectedRecord.getBALANCE_AMOUNT().toString())) //16Sep2025
							BALANCEAMT = new BigDecimal(selectedRecord.getBALANCE_AMOUNT().toString());
						BALANCEDESC = selectedRecord.getBALANCE_DESCRIPTION();

						SalesBill salesData = new SalesBill();
						salesData.setCUSTOMER_PHONE("" + CUSTPHONE);
						salesData.setSALES_DATE(SALESDATEFORMATTED);
						salesData.setCUSTOMER_NAME(CUSTNAME);
						salesData.setCUSTOMER_ADDRESS(CUSTADDRESS);
						salesData.setPRODUCT_TYPE(PRODUCTTYPE);
						salesData.setPRODUCTS(PRODUCT.toString().toUpperCase());
						salesData.setPRODUCT_WEIGHT(PRODUCTWEIGTH);
						salesData.setSALES_AMOUNT("" + SALESAMT);
						salesData.setSALES_AMOUNT2("" + SALESAMT2);
						salesData.setBALANCE_DESCRIPTION(BALANCEDESC);
						salesData.setBALANCE_AMOUNT("" + BALANCEAMT);
						salesData.setBILL_TYPES(BILLTYPE); //07Oct2025
						//27Oct2025
						salesData.setDATE(selectedRecord.getDATE());
						salesData.setGOLD_RATE(selectedRecord.getGOLD_RATE());
						salesData.setSILVER_RATE(selectedRecord.getSILVER_RATE());
						//27Oct2025

						PrintBill print = new PrintBill(CUSTPHONE, SALESDATEFORMATTED, CUSTNAME, CUSTADDRESS,
								PRODUCTTYPE, PRODUCT, PRODUCTWEIGTH, SALESAMT, SALESAMT2, BALANCEDESC, BALANCEAMT, BILLTYPE); //07Oct2025
						//27Oct2025
						print.setPRODUCT_TYPE(PRODUCTTYPE);
						print.setDATE(salesData.getDATE());
						print.setGOLD_RATE(salesData.getGOLD_RATE());
						print.setSILVER_RATE(salesData.getSILVER_RATE());
						//27Oct2025
						String printFileName = "" + CUSTPHONE + "_" + CUSTNAME + ".pdf";
						try {
							print.printFormSales(print, printFileName);
						} catch (DocumentException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}

			}
		});
		// 08Aug2025

		btnNotifyBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				SalesBillListViewPanel = loadData.loadSALESData();
				String mobileNo = txtMobileNo.getText().trim();
				String Name = txtName.getText().toUpperCase().trim().toString();
				String ADAGUDATEFORMATTED = "";
				Date ADAGUDATE = new Date();
				ADAGUDATE = adaguDate.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				if (null != ADAGUDATE) {
					ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				}
				// Clear existing data in the table
				tableModel.setRowCount(0);
				String status = cmbStatus.getSelectedItem().toString(); //24Oct2025

				// Logic to filter records based on billNo, mobileNo, status, and billType
				for (SalesBill record : SalesBillListViewPanel) {
					// Filter based on Bill No (if not empty)
					boolean matchesName = Name.isEmpty() || String.valueOf(record.getCUSTOMER_NAME()).equals(Name);

					// Filter based on Mobile No (if not empty)
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);

					boolean matchesAdaguDate = ADAGUDATEFORMATTED.equals("")
							|| record.getSALES_DATE().equals(ADAGUDATEFORMATTED);
					
					boolean matchesStatus = status.equals("") || record.getSTATUS().equalsIgnoreCase(status); //24Oct2025

					// Filter based on Status (if not "All")
					if (matchesMobileNo && matchesName && matchesAdaguDate && matchesStatus) { //24Oct2025
						tableModel.addRow(new Object[] { record.getAD_SALESDATA_ID(), record.getCUSTOMER_PHONE(),
								record.getSALES_DATE(), // 08Aug2025
								record.getCUSTOMER_NAME(), record.getCUSTOMER_ADDRESS(), record.getPRODUCTS(),
								record.getPRODUCT_WEIGHT(), record.getSALES_AMOUNT(), record.getBALANCE_AMOUNT() //07Oct2025
								, record.getSTATUS() //24Oct2025
						});
					}
				}
			}
		});

		// Set the custom renderer for all columns
		int columnCount = table.getColumnModel().getColumnCount();

		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
			table.getColumnModel().getColumn(i).setPreferredWidth(200); // Adjust the width as needed
		}

		// Set window properties
		// setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;

	}

	// Panel for General Ledger Report
	private JPanel createSynchToMobilePanel() {

		JPanel panel;
		JTextField fromEmail, toEmail, cc, subject, message, fileName, passWord;
		JLabel shopName;
		JButton sendEmail;
		JDateChooser systemDate;
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Font inputFont = new Font("Arial", Font.BOLD, 15);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);
		shopName = new JLabel(" Sri Lalith Prasanna Jewellery - Synch To Mobile ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		systemDate = new JDateChooser();
		systemDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		systemDate.setDate(new java.util.Date()); // Set default system date
		systemDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		systemDate.setFont(inputFont);

		fromEmail = new JTextField();
		toEmail = new JTextField();
		cc = new JTextField();
		subject = new JTextField();
		message = new JTextField();
		fileName = new JTextField();
		passWord = new JPasswordField();

		fromEmail.setFont(inputFont);
		toEmail.setFont(inputFont);
		cc.setFont(inputFont);
		subject.setFont(inputFont);
		message.setFont(inputFont);
		fileName.setFont(inputFont);
		passWord.setFont(inputFont);

		fromEmail.setText("smlalith2101@gmail.com");
		toEmail.setText("");
		cc.setText("");
		subject.setText(" Sri Lalith Prasanna Jewellery & Pawn Shop ");
		message.setText(" Backup : " + systemDate.getDate() + " \n Kindly Refer Below Attachment - CSV ");
		toEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!firstTimeEmail) {
					DBConnect loadData = new DBConnect();
					csvLocation = loadData.exportBackupData();
					for (String path : csvLocation) {
						fileName.setText(fileName.getText() + "\n" + path);
					}
					firstTimeEmail = true;
				}
			}
		});

		passWord.setText("ffbo bbbe bzmq aaah");
		fromEmail.setEditable(false);
		toEmail.setEditable(true);
		cc.setEditable(true);
		subject.setEditable(false);
		message.setEditable(false);
		fileName.setEditable(false);
		passWord.setEditable(false);

		sendEmail = new JButton("Email");
		sendEmail.setBackground(new Color(56, 142, 60)); // Green

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		shopNamePanel.add(shopName, BorderLayout.CENTER);
		panel.add(shopNamePanel, gbc);

		JPanel fromEmailPanel = new JPanel(new BorderLayout());
		fromEmailPanel.add(fromEmail, BorderLayout.CENTER);
		panel.add(fromEmailPanel, gbc);

		JPanel toEmailPanel = new JPanel(new BorderLayout());
		toEmailPanel.add(toEmail, BorderLayout.CENTER);
		panel.add(toEmailPanel, gbc);

		JPanel ccPanel = new JPanel(new BorderLayout());
		ccPanel.add(cc, BorderLayout.CENTER);
		panel.add(ccPanel, gbc);

		JPanel subjectPanel = new JPanel(new BorderLayout());
		subjectPanel.add(subject, BorderLayout.CENTER);
		panel.add(subjectPanel, gbc);

		JPanel messagePanel = new JPanel(new BorderLayout());
		messagePanel.add(message, BorderLayout.CENTER);
		panel.add(messagePanel, gbc);

		JPanel filanamePanel = new JPanel(new BorderLayout());
		filanamePanel.add(fileName, BorderLayout.CENTER);
		panel.add(filanamePanel, gbc);

		JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.add(passWord, BorderLayout.CENTER);
		panel.add(passwordPanel, gbc);

		panel.add(sendEmail, gbc);

		sendEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSent = false;
				EmailConfig sendEmailNow = new EmailConfig();
				String FROM = fromEmail.getText().trim().toString();
				String TO = toEmail.getText().trim().toString();
				String CC = cc.getText().trim().toString();
				String SUBJECT = subject.getText().trim().toString();
				String MESSAGE = message.getText().trim().toString();
				String FILENAME = fileName.getText().trim().toString();
				String PASSWORD = passWord.getText().trim().toString();
				isSent = sendEmailNow.sendEmail(FROM, TO, CC, SUBJECT, MESSAGE, csvLocation, PASSWORD);

				if (isSent) {
					JOptionPane.showMessageDialog(panel, " Email Sent Successfully ... ");
				} else {
					JOptionPane.showMessageDialog(panel, " Error In Email Sending ... ");
				}

			}
		});

		// Set window visibility
		setVisible(true);

		return panel;

	}

	private JPanel createDetailsPanel() {
		JPanel panel = new JPanel();
		JTextField txtMobileNo;
		JButton btnSearch;
		JTable table;
		DefaultTableModel tableModel;
		panel.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		btnSearch = new JButton("Search");

		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(
				new String[] { "BILLTYPE", "BILLNO", "NAME", "AMOUNT", "DATE", "STATUS", "BALANCE" }, 0);

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());
		panel.add(footerPanel, BorderLayout.SOUTH);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				DetailsViewPanel = loadData.loadAllDetailsData();
				String mobileNo = txtMobileNo.getText().trim();
				tableModel.setRowCount(0);
				for (SalesBill record : DetailsViewPanel) {
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);
					if (matchesMobileNo) {
						tableModel.addRow(new Object[] { record.getBILL_TYPE(), record.getBILL_NO(),
								record.getCUSTOMER_NAME(), record.getSALES_AMOUNT(), record.getSALES_DATE(),
								record.getSTATUS(), record.getBALANCE_AMOUNT() });
					}
				}
			}
		});

		// Set the custom renderer for all columns
		int columnCount = table.getColumnModel().getColumnCount();

		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;
	}
	
	//24Oct2025
	private JPanel createGSRatePanel() {
		JPanel panel = new JPanel();
		JPanel paneL = new JPanel();
		JTable tableGSRates;
		DefaultTableModel tableModelGSRates;
		JTextField goldRateField, silverRateField, dateField;
		JButton saveButton, clearButton;
		JLabel dateLabel;

		panel.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());
		JLabel Header = new JLabel("Gold & Silver Rates");
		headerPanel.add(Header);
		URL refreshIconURL = TextFieldWithIcon.class.getResource("/images/reload.png");
		ImageIcon refreshUrlIcon = new ImageIcon(refreshIconURL);
		Image refreshUrlimage = refreshUrlIcon.getImage();
		Image refreshUrlImage = refreshUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon refrershURLIcon = new ImageIcon(refreshUrlImage);
		JLabel refreshURLLabel = new JLabel(refrershURLIcon);
		headerPanel.add(refreshURLLabel);
		panel.add(headerPanel, BorderLayout.NORTH);

		tableModelGSRates = new DefaultTableModel(new String[] { "Date", "Gold Rs/gm", "Silver Rs/gm" }, 0);
		tableGSRates = new JTable(tableModelGSRates);
		tableGSRates.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tableGSRates.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		tableGSRates.getTableHeader().setForeground(new Color(245, 47, 4));
		tableGSRates.setRowHeight(500, 100);
		tableGSRates.getColumnModel().getColumn(0).setPreferredWidth(110);
		JScrollPane tableScrollGSRates = new JScrollPane(tableGSRates);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		//paneL = new JPanel();
		JLabel titleLabel = new JLabel("Gold & Silver Rates");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(new Color(255, 215, 0));
		//titleLabel.setBounds(110, 15, 300, 40);
		//paneL.add(titleLabel);
		// 🕒 Date Label
		dateLabel = new JLabel("Date & Time:");
		dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // ⬆ larger font
		dateLabel.setForeground(new Color(135, 206, 250));
		//dateLabel.setBounds(70, 60, 350, 30); // ⬆ wider & taller
		//paneL.add(dateLabel);
		dateField = new JTextField();
		dateField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		//goldRateField.setBounds(200, 110, 160, 30);
		dateField.setBackground(new Color(255, 255, 230));
		// Timer to update every second
		Timer timer = new Timer(1000, new ActionListener() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

			@Override
			public void actionPerformed(ActionEvent e) {
				dateField.setText("" + sdf.format(new Date()));
			}
		});
		timer.start();
		JLabel goldLabel = new JLabel("Gold Rate (₹):");
		goldLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		goldLabel.setForeground(Color.RED);
		//goldLabel.setBounds(60, 110, 130, 25);
		//paneL.add(goldLabel);
		goldRateField = new JTextField();
		goldRateField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		//goldRateField.setBounds(200, 110, 160, 30);
		goldRateField.setBackground(new Color(255, 255, 230));
		//paneL.add(goldRateField);
		JLabel silverLabel = new JLabel("Silver Rate (₹):");
		silverLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		silverLabel.setForeground(Color.RED);
		silverLabel.setBounds(60, 160, 130, 25);
		//paneL.add(silverLabel);
		silverRateField = new JTextField();
		silverRateField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		//silverRateField.setBounds(200, 160, 160, 30);
		silverRateField.setBackground(new Color(240, 240, 255));
		//paneL.add(silverRateField);
		saveButton = new JButton("💾 Save");
		saveButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		//saveButton.setBounds(150, 230, 140, 45);
		saveButton.setBackground(new Color(255, 215, 0));
		saveButton.setForeground(Color.BLACK);
		saveButton.setFocusPainted(false);
		saveButton.setBorder(BorderFactory.createLineBorder(new Color(180, 150, 0), 2, true));
		saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				saveButton.setBackground(new Color(255, 230, 80));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				saveButton.setBackground(new Color(255, 215, 0));
			}
		});
		//paneL.add(saveButton);
		saveButton.addActionListener(e -> {
			String goldRate = goldRateField.getText();
			String silverRate = silverRateField.getText();
			String dateTime = dateField.getText();

			GSRate gsData = new GSRate();
			gsData.setDATE(dateTime);
			gsData.setGOLD_RATE(goldRate);
			gsData.setSILVER_RATE(silverRate);

			if (goldRate.isEmpty() || silverRate.isEmpty()) {
				JOptionPane.showMessageDialog(panel, "Please enter both rates!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {

				DBConnect saveGSRates = new DBConnect();
				boolean isSaved = false;
				isSaved = saveGSRates.saveGSRate(gsData);

				if (isSaved) {
					JOptionPane.showMessageDialog(panel, "Rates saved successfully!\n" + dateTime + "\n\nGold: ₹"
							+ goldRate + "\nSilver: ₹" + silverRate, "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showConfirmDialog(panel, " Error : Save Rates !!! ");
				}

			}
		});
		
		clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		clearButton.setBackground(new Color(255, 215, 0));
		clearButton.setForeground(Color.BLACK);
		clearButton.setFocusPainted(false);
		clearButton.setBorder(BorderFactory.createLineBorder(new Color(180, 150, 0), 2, true));
		clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				clearButton.setBackground(new Color(255, 230, 80));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				clearButton.setBackground(new Color(255, 215, 0));
			}
		});
		clearButton.addActionListener(e -> {
			goldRateField.setText("");
			silverRateField.setText("");
		});
		
		// 🧮 Numeric validation
		KeyAdapter numericValidator = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				char c = e.getKeyChar();
				String text = src.getText().trim();
				if ((!Character.isDigit(c) && c != '.') || (c == '.' && text.contains("."))) {
					e.consume();
				}
			}
		};
		goldRateField.addKeyListener(numericValidator);
		silverRateField.addKeyListener(numericValidator);
		
		paneL = new JPanel();
		paneL.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbcs = new GridBagConstraints();
		gbcs.insets = new Insets(10, 10, 10, 10);
		gbcs.anchor = GridBagConstraints.NORTH;

		JPanel titleLabelPanel = new JPanel(new BorderLayout());
		titleLabelPanel.add(titleLabel, BorderLayout.CENTER);
		paneL.add(titleLabelPanel, gbcs);
		
		JPanel dateLabelPanel = new JPanel(new BorderLayout());
		JPanel PanelG0 = new JPanel(new GridLayout(1, 2));
		PanelG0.add(dateLabel);
		PanelG0.add(dateField);
		dateLabelPanel.add(PanelG0, BorderLayout.CENTER);
		paneL.add(dateLabelPanel, gbcs);
		
		JPanel goldLabelPanel = new JPanel(new BorderLayout());
		JPanel PanelG1 = new JPanel(new GridLayout(1, 2));
		PanelG1.add(goldLabel);
		PanelG1.add(goldRateField);
		goldLabelPanel.add(PanelG1, BorderLayout.CENTER);
		paneL.add(goldLabelPanel, gbcs);
		
		JPanel silverLabelPanel = new JPanel(new BorderLayout());
		JPanel PanelG2 = new JPanel(new GridLayout(1, 2));
		PanelG2.add(silverLabel);
		PanelG2.add(silverRateField);
		silverLabelPanel.add(PanelG2, BorderLayout.CENTER);
		paneL.add(silverLabelPanel, gbcs);
		
		JPanel saveButtonPanel = new JPanel(new BorderLayout());
		JPanel PanelG3 = new JPanel(new GridLayout(1, 2));
		PanelG3.add(saveButton);
		PanelG3.add(clearButton);
		saveButtonPanel.add(PanelG3, BorderLayout.CENTER);
		paneL.add(saveButtonPanel, gbcs);
		
		JPanel Panel1 = new JPanel(new GridLayout(1, 2));
		Panel1.add(paneL);
		Panel1.add(tableScrollGSRates);
		panel.add(Panel1, BorderLayout.CENTER);

		refreshURLLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DBConnect loadData = new DBConnect();
				GSRatesViewPanel = loadData.loadAllGSRatesData();
				tableModelGSRates.setRowCount(0);
				for (GSRate record : GSRatesViewPanel) {
					tableModelGSRates
							.addRow(new Object[] { record.getDATE(), record.getGOLD_RATE(), record.getSILVER_RATE() });
				}
			}
		});

		// Set the custom renderer for all columns
		int columnCountSales = tableGSRates.getColumnModel().getColumnCount();

		for (int i = 0; i < columnCountSales; i++) {
			tableGSRates.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;

	}

	private JPanel createSearchPanel() {
		JPanel panel = new JPanel();
		JTextField txtMobileNo;
		JButton btnSearch;
		JTable tableAdagu, tableSales;
		DefaultTableModel tableModelAdagu, tableModelSales;
		panel.setLayout(new BorderLayout());
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		btnSearch = new JButton("Search");

		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		tableModelAdagu = new DefaultTableModel(
				new String[] { "ID", "BILLTYPE", "BILLNO", "NAME", "AMOUNT", "DATE", "STATUS", "BALANCE" }, 0); //24Oct2025
		tableAdagu = new JTable(tableModelAdagu);
		tableAdagu.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tableAdagu.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		tableAdagu.getTableHeader().setForeground(new Color(58, 113, 232));
		tableAdagu.setRowHeight(500, 100);
		tableAdagu.getColumnModel().getColumn(0).setPreferredWidth(110);
		JScrollPane tableScrollAdagu = new JScrollPane(tableAdagu);
		
		//24Oct2025
		JPopupMenu popupAdaguMenu = new JPopupMenu();
		JMenuItem editAdaguItem = new JMenuItem("View");
		popupAdaguMenu.add(editAdaguItem); 
		tableAdagu.setComponentPopupMenu(popupAdaguMenu);
		editAdaguItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// "ID", "PHONE", "DATE", "NAME", "ADDRESS", "PRODUCT", "WEIGHT", "AMT",
				// "BALANCE"
				int selectedRow = tableAdagu.getSelectedRow();
				if (selectedRow >= 0) {
					int SALESID = Integer.parseInt(tableModelAdagu.getValueAt(selectedRow, 0).toString());
					BigDecimal billNo = new BigDecimal(SALESID);
					SalesBill selectedRecord = null;
					for (SalesBill record : DetailsViewPanel) {
						if (new BigDecimal(record.getAD_SALESDATA_ID()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField CUSTOMER_PHONE = new JTextField(selectedRecord.getCUSTOMER_PHONE());
						CUSTOMER_PHONE.setEditable(false);
						JTextField SALES_DATE = new JTextField(selectedRecord.getSALES_DATE());
						SALES_DATE.setEditable(false);
						JTextField CUSTOME_NAME = new JTextField(selectedRecord.getCUSTOMER_NAME());
						CUSTOME_NAME.setEditable(false);
						JTextField CUSTOMER_ADDRESS = new JTextField(selectedRecord.getCUSTOMER_ADDRESS());
						CUSTOMER_ADDRESS.setEditable(false);
						JTextField PRODUCT = new JTextField(selectedRecord.getPRODUCTS());
						PRODUCT.setEditable(false);
						JTextField PRODUCT_TYPE = new JTextField(selectedRecord.getPRODUCT_TYPE());
						PRODUCT_TYPE.setEditable(false);
						JTextField PRODUCT_WEIGHT = new JTextField(selectedRecord.getPRODUCT_WEIGHT());
						PRODUCT_WEIGHT.setEditable(false);
						JTextField SALES_AMOUNT = new JTextField(selectedRecord.getSALES_AMOUNT());
						SALES_AMOUNT.setEditable(false);
						JTextField BALANCE_DESC = new JTextField(selectedRecord.getBALANCE_DESCRIPTION());
						BALANCE_DESC.setEditable(false);
						JTextField BALANCE_AMOUNT = new JTextField(selectedRecord.getBALANCE_AMOUNT());
						BALANCE_AMOUNT.setEditable(false);

						Object[] message = { "Phone", CUSTOMER_PHONE, "Sales Date", SALES_DATE, "Name", CUSTOME_NAME,
								"Address", CUSTOMER_ADDRESS, "Product", PRODUCT, "Weight", PRODUCT_WEIGHT, "Amount",
								SALES_AMOUNT };
						int option = JOptionPane.showConfirmDialog(null, message, "View Record",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {

						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record View");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to View");
				}

			}
		});
		//24Oct2025

		tableModelSales = new DefaultTableModel(
				new String[] { "ID", "BILLTYPE", "BILLNO", "NAME", "AMOUNT", "DATE", "STATUS", "BALANCE" }, 0);
		tableSales = new JTable(tableModelSales);
		tableSales.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tableSales.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		tableSales.getTableHeader().setForeground(new Color(245, 47, 4));
		tableSales.setRowHeight(500, 100);
		tableSales.getColumnModel().getColumn(0).setPreferredWidth(110);
		JScrollPane tableScrollSales = new JScrollPane(tableSales);
		//24Oct2025
		JPopupMenu popupSalesMenu = new JPopupMenu();
		JMenuItem editSalesItem = new JMenuItem("View");
		popupSalesMenu.add(editSalesItem); 
		tableSales.setComponentPopupMenu(popupSalesMenu);
		editSalesItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// "ID", "PHONE", "DATE", "NAME", "ADDRESS", "PRODUCT", "WEIGHT", "AMT",
				// "BALANCE"
				int selectedRow = tableSales.getSelectedRow();
				if (selectedRow >= 0) {
					int SALESID = Integer.parseInt(tableModelSales.getValueAt(selectedRow, 0).toString());
					BigDecimal billNo = new BigDecimal(SALESID);
					SalesBill selectedRecord = null;
					for (SalesBill record : DetailsViewPanel) {
						if (new BigDecimal(record.getAD_SALESDATA_ID()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField CUSTOMER_PHONE = new JTextField(selectedRecord.getCUSTOMER_PHONE());
						CUSTOMER_PHONE.setEditable(false);
						JTextField SALES_DATE = new JTextField(selectedRecord.getSALES_DATE());
						SALES_DATE.setEditable(false);
						JTextField CUSTOME_NAME = new JTextField(selectedRecord.getCUSTOMER_NAME());
						CUSTOME_NAME.setEditable(false);
						JTextField CUSTOMER_ADDRESS = new JTextField(selectedRecord.getCUSTOMER_ADDRESS());
						CUSTOMER_ADDRESS.setEditable(false);
						JTextField PRODUCT = new JTextField(selectedRecord.getPRODUCTS());
						PRODUCT.setEditable(false);
						JTextField PRODUCT_TYPE = new JTextField(selectedRecord.getPRODUCT_TYPE());
						PRODUCT_TYPE.setEditable(false);
						JTextField PRODUCT_WEIGHT = new JTextField(selectedRecord.getPRODUCT_WEIGHT());
						PRODUCT_WEIGHT.setEditable(false);
						JTextField SALES_AMOUNT = new JTextField(selectedRecord.getSALES_AMOUNT());
						SALES_AMOUNT.setEditable(false);
						JTextField BALANCE_DESC = new JTextField(selectedRecord.getBALANCE_DESCRIPTION());
						BALANCE_DESC.setEditable(false);
						JTextField BALANCE_AMOUNT = new JTextField(selectedRecord.getBALANCE_AMOUNT());
						BALANCE_AMOUNT.setEditable(false);

						Object[] message = { "Phone", CUSTOMER_PHONE, "Sales Date", SALES_DATE, "Name", CUSTOME_NAME,
								"Address", CUSTOMER_ADDRESS, "Product", PRODUCT, "Weight", PRODUCT_WEIGHT, "Amount",
								SALES_AMOUNT };
						int option = JOptionPane.showConfirmDialog(null, message, "View Record",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {

						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record View");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to View");
				}

			}
		});
		//24Oct2025

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		JPanel Panel1 = new JPanel(new GridLayout(1, 2));
		Panel1.add(tableScrollAdagu);
		Panel1.add(tableScrollSales);
		panel.add(Panel1, BorderLayout.CENTER);

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());
		panel.add(footerPanel, BorderLayout.SOUTH);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				DetailsViewPanel = loadData.loadAllDetailsData();
				String mobileNo = txtMobileNo.getText().trim();
				String billTypeAdagu = "ADAGU";
				String billTypeSales = "SALES";
				tableModelAdagu.setRowCount(0);
				tableModelSales.setRowCount(0);
				for (SalesBill record : DetailsViewPanel) {
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);
					if (matchesMobileNo && billTypeAdagu.equalsIgnoreCase(record.getBILL_TYPE().trim().toString())) {
						tableModelAdagu.addRow(new Object[] { record.getAD_SALESDATA_ID(), record.getBILL_TYPE(), record.getBILL_NO(),
								record.getCUSTOMER_NAME(), record.getSALES_AMOUNT(), record.getSALES_DATE(),
								record.getSTATUS(), record.getBALANCE_AMOUNT() });
					}

					if (matchesMobileNo && billTypeSales.equalsIgnoreCase(record.getBILL_TYPE().trim().toString())) {
						tableModelSales.addRow(new Object[] { record.getAD_SALESDATA_ID(), record.getBILL_TYPE(), record.getBILL_NO(),
								record.getCUSTOMER_NAME(), record.getSALES_AMOUNT(), record.getSALES_DATE(),
								record.getSTATUS(), record.getBALANCE_AMOUNT() });
					}
				}
			}
		});

		// Set the custom renderer for all columns
		int columnCountAdagu = tableAdagu.getColumnModel().getColumnCount();
		int columnCountSales = tableSales.getColumnModel().getColumnCount();

		for (int i = 0; i < columnCountAdagu; i++) {
			tableAdagu.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}
		for (int i = 0; i < columnCountSales; i++) {
			tableSales.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRendererForView());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;

	}

}

class AdminHomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel; // Content panel to dynamically update
	private CardLayout cardLayout; // CardLayout to switch between pages
	ArrayList<AdaguBill> AdaguBillListViewPanel;
	ArrayList<AdaguBill> AdaguBillListAdaguPanel;
	ArrayList<AdaguBill> AdaguBillListGLPanel;
	ArrayList<String> productGoldList;
	ArrayList<String> productSilverList;
	ArrayList<String> masterCustomerList;
	ArrayList<String> masterAddressList;
	ArrayList<String> masterHeirList;
	ArrayList<String> masterLockerList;
	ArrayList<AdaguBill> AdaguBillListCancelViewPanel;

	public AdminHomePage() throws ParseException {

		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to full screen
		// setSize(1100, 800);
		setResizable(false);
		setLocationRelativeTo(null); // Center the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL appIcon = getClass().getResource("/images/jewelry.png");
		ImageIcon APPICON = new ImageIcon(appIcon);
		Image APPLOGO = APPICON.getImage();
		setIconImage(APPLOGO);

		// Main container panel
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		// Sidebar panel
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.setBackground(new Color(72, 61, 139)); // Dark Purple Background

		String[] options = { "Execute", "Logout" };
		JButton[] buttons = new JButton[options.length];

		// Content Panel with CardLayout
		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout); // Set CardLayout to the content panel

		// Add panels for each content page
		contentPanel.add(AdminExecutePanel(), "Execute");

		for (int i = 0; i < options.length; i++) {
			buttons[i] = new JButton(options[i]);
			buttons[i].setBackground(new Color(144, 238, 144)); // Light Green
			buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			buttons[i].setFocusPainted(false);
			buttons[i].setMaximumSize(new Dimension(200, 40));
			buttons[i].setPreferredSize(new Dimension(200, 40));
			buttons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String buttonText = ((JButton) e.getSource()).getText();
					if (buttonText.equals("Logout")) {
						System.exit(0); // Close the application on logout
					} else {
						// Update the content panel based on button click
						showContent(buttonText);
					}
				}
			});
			sidebar.add(buttons[i]);
			sidebar.add(Box.createVerticalStrut(10)); // Add space between buttons
		}
		// Add Sidebar and Content Panel to container
		// Add Sidebar and Content Panel to container
		JPanel calClock = new JPanel();
		calClock.setBackground(new Color(72, 61, 139));

		// Set BoxLayout for vertical arrangement
		calClock.setLayout(new BoxLayout(calClock, BoxLayout.Y_AXIS));

		// Create the CalculatorApp and AnalogClock
		CalculatorApp cal = new CalculatorApp();
		cal.setPreferredSize(new Dimension(80, 80));
		cal.setBackground(new Color(72, 61, 139));

		AnalogClock clock = new AnalogClock();
		clock.setPreferredSize(new Dimension(200, 200));
		clock.setBackground(new Color(72, 61, 139));

		// Add CalculatorApp and AnalogClock to the JPanel
		calClock.add(clock);
		// calClock.add(cal);

		// Add calClock to the sidebar (positioned at the south)
		sidebar.add(calClock, BorderLayout.SOUTH);
		container.add(sidebar, BorderLayout.WEST);
		container.add(contentPanel, BorderLayout.CENTER);

		add(container);
		setVisible(true);
	}

	private JPanel AdminExecutePanel() {
		JPanel panel;
		JTextField sqlScript;
		JLabel shopName;
		JButton excuteScript, clearScript;
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Font inputFont = new Font("Arial", Font.BOLD, 15);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);
		shopName = new JLabel(" Thinesh Raajan - ADMIN ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		sqlScript = new JTextFieldWithPlaceholder("");
		sqlScript.setEditable(true);
		LineBorder border = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
		sqlScript.setBorder(border);
		sqlScript.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		sqlScript.setFont(inputFont);
		excuteScript = new JButton("Execute");
		excuteScript.setBackground(new Color(56, 142, 60)); // Green
		excuteScript.setForeground(Color.RED);
		excuteScript.setFont(labelFont);
		clearScript = new JButton("Clear");
		clearScript.setBackground(new Color(56, 142, 60)); // Green
		clearScript.setForeground(Color.RED);
		clearScript.setFont(labelFont);

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		shopNamePanel.add(shopName, BorderLayout.CENTER);

		panel.add(shopNamePanel, gbc);

		URL billUrl = TextFieldWithIcon.class.getResource("/images/bill.png");
		ImageIcon billicon = new ImageIcon(billUrl);
		Image billimage = billicon.getImage();
		Image billImage = billimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon billIcon = new ImageIcon(billImage);
		JPanel billPanel = new JPanel(new BorderLayout());
		JLabel billLabel = new JLabel(billIcon);
		billPanel.add(billLabel, BorderLayout.WEST);
		JPanel Panel1 = new JPanel(new GridLayout(1, 1));
		Panel1.add(sqlScript);
		billPanel.add(Panel1, BorderLayout.CENTER);
		panel.add(billPanel, gbc);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));
		bottomPanel.add(excuteScript);
		bottomPanel.add(clearScript);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		JPanel bottomPanel1 = new JPanel(new BorderLayout());
		bottomPanel1.add(bottomPanel, BorderLayout.CENTER);
		panel.add(bottomPanel1, gbc);

		clearScript.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				excuteScript.setEnabled(true);
				sqlScript.setEditable(true);
				sqlScript.setText("");
				sqlScript.setForeground(new Color(157, 161, 250));
			}
		});

		excuteScript.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String SQL = "";

				if (sqlScript.getText().trim().equals("") || sqlScript.getText().trim().equals(null)) {
					JOptionPane.showConfirmDialog(panel, " Please Enter SQL Value !!! ");
					sqlScript.setBorder(border);
					return;
				}
				SQL = sqlScript.getText().toString().trim().toUpperCase();
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border

				DBConnect executeSQL = new DBConnect();
				boolean isExecuted = false;

				if (SQL.startsWith("UPDATE")) {
					isExecuted = executeSQL.executeUpdateOrDelete(SQL);
				} else if (SQL.startsWith("DELETE")) {
					isExecuted = executeSQL.executeUpdateOrDelete(SQL);
				}

				if (isExecuted) {
					JOptionPane.showConfirmDialog(panel, " Script Executed Successfully !!! ");
					excuteScript.setEnabled(false);
				} else {
					JOptionPane.showConfirmDialog(panel, " Error In Script Execution !!! ");
				}

				sqlScript.setEditable(false);
				sqlScript.setBackground(Color.LIGHT_GRAY);
				sqlScript.setBorder(correctborder);
				sqlScript.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

			}
		});

		setVisible(true);

		return panel;
	}

	// Method to show the content based on selected option
	private void showContent(String pageName) {
		cardLayout.show(contentPanel, pageName); // Show the corresponding panel
	}

	// Panel for Home
	private JPanel AdmincreateHomePanel() {
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(245, 245, 245)); // Light gray background
		JPanel pieChartPanel = AdmincreateBarChartIncomePanel();
		JPanel barChartPanel = AdmincreateBarChartExpensePanel();

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pieChartPanel, barChartPanel);
		splitPane.setDividerLocation(300);
		homePanel.add(splitPane, BorderLayout.CENTER);
		homePanel.setVisible(true);
		return homePanel;
	}

	// Create Pie Chart Panel
	private JPanel AdmincreateBarChartIncomePanel() {

		Double b1 = 0.0, b2 = 0.0, b3 = 0.0, b4 = 0.0, b5 = 0.0, b6 = 0.0, b7 = 0.0, b8 = 0.0, b9 = 0.0, b10 = 0.0,
				b11 = 0.0, b12 = 0.0;

		DBConnect barGraph = new DBConnect();
		b1 = barGraph.getBarData("B1");
		b2 = barGraph.getBarData("B2");
		b3 = barGraph.getBarData("B3");
		b4 = barGraph.getBarData("B4");
		b5 = barGraph.getBarData("B5");
		b6 = barGraph.getBarData("B6");
		b7 = barGraph.getBarData("B7");
		b8 = barGraph.getBarData("B8");
		b9 = barGraph.getBarData("B9");
		b10 = barGraph.getBarData("B10");
		b11 = barGraph.getBarData("B11");
		b12 = barGraph.getBarData("B12");

		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
		barDataset.addValue(b1, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "9-10");
		barDataset.addValue(b2, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "10-11");
		barDataset.addValue(b3, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "11-12");
		barDataset.addValue(b4, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "12-1");
		barDataset.addValue(b5, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "1-2");
		barDataset.addValue(b6, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "2-3");
		barDataset.addValue(b7, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "3-4");
		barDataset.addValue(b8, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "4-5");
		barDataset.addValue(b9, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "5-6");
		barDataset.addValue(b10, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "6-7");
		barDataset.addValue(b11, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "7-8");
		barDataset.addValue(b12, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "8-9");

		JFreeChart barChart = ChartFactory.createBarChart(" Hourly Money Transit Income ", "Time", "Amount (\u20B9)",
				barDataset, PlotOrientation.VERTICAL, true, true, true);

		// Create the panel and add the bar chart
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(900, 400));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel, BorderLayout.CENTER);
		panel.setVisible(true);
		return panel;
	}

	// Create Bar Chart Panel
	private JPanel AdmincreateBarChartExpensePanel() {

		Double b1 = 0.0, b2 = 0.0, b3 = 0.0, b4 = 0.0, b5 = 0.0, b6 = 0.0, b7 = 0.0, b8 = 0.0, b9 = 0.0, b10 = 0.0,
				b11 = 0.0, b12 = 0.0;

		DBConnect barGraph = new DBConnect();
		b1 = barGraph.getBarData("B1");
		b2 = barGraph.getBarData("B2");
		b3 = barGraph.getBarData("B3");
		b4 = barGraph.getBarData("B4");
		b5 = barGraph.getBarData("B5");
		b6 = barGraph.getBarData("B6");
		b7 = barGraph.getBarData("B7");
		b8 = barGraph.getBarData("B8");
		b9 = barGraph.getBarData("B9");
		b10 = barGraph.getBarData("B10");
		b11 = barGraph.getBarData("B11");
		b12 = barGraph.getBarData("B12");

		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
		barDataset.addValue(b1, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "9-10");
		barDataset.addValue(b2, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "10-11");
		barDataset.addValue(b3, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "11-12");
		barDataset.addValue(b4, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "12-1");
		barDataset.addValue(b5, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "1-2");
		barDataset.addValue(b6, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "2-3");
		barDataset.addValue(b7, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "3-4");
		barDataset.addValue(b8, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "4-5");
		barDataset.addValue(b9, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "5-6");
		barDataset.addValue(b10, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "6-7");
		barDataset.addValue(b11, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "7-8");
		barDataset.addValue(b12, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), "8-9");

		JFreeChart barChart = ChartFactory.createBarChart(" Hourly Money Transit Expense", "Time", "Amount (\u20B9)",
				barDataset, PlotOrientation.VERTICAL, true, true, true);

		// Create the panel and add the bar chart
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(800, 400));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel, BorderLayout.CENTER);
		panel.setVisible(true);
		return panel;
	}

	private JPanel AdmincreateDummyAddAdaguBillPanel() {
		JPanel panel;
		JTextField billNum, adaguAmt, custName, custPhone, custHeir, adaguActualAmt, productDetails, productWeight,
				productValue, custAddress;
		JLabel shopName;
		JButton saveBill, notifyBill, printBill, clearBill;
		JDateChooser adaguDate, redemptionDate, cancelDate;
		DefaultListModel<String> selectedProductsModel;
		JList<String> selectedProductsList;
		JTextField LICENCENO; // = " 16/21-22 "

		JTextField lockerName, lockerBillNumber; // Locker Detail New Fix
		JDateChooser lockerDate; // Locker Detail New Fix

		// Simulating a product list (in a real-world scenario, this could be from a
		// database)
		/*
		 * ArrayList<String> productList = new ArrayList<>(Arrays.asList("2 LINE CHINE",
		 * "ADDAGAI ,STONE TEEKA", "BABY RING", "BANGLES", "BASER", "BOX RING",
		 * "BRACELET", "BROCH", "BUTTON TOPS", "BUTTON TOPS THONGAL", "CAST DROPS",
		 * "CASTING RING", "CASTING TOPS", "CHAIN", "CHAIN WITH DOLLAR", "DELHI CHAIN",
		 * "DELHI SUTTU MATTLE", "DIE COIN", "DOLLAR", "FANCE HARAM", "FANCE NECKLES",
		 * "FANCY AARAM", "FANCY DROPS", "FANCY JUMKI", "FANCY MATTLE", "FANCY NECKLES",
		 * "FANCY RING", "FANCY TOPS", "FANCY TOPS JIMUKI", "GALSER", "GENTS RING",
		 * "GUNDU WT LOCK", "HARAM", "JABKA", "JUMIKI", "JUMIKI TOPS", "K TONGAL",
		 * "KALAN BASEAR", "KAMBI", "KAPPU", "KARI MANI CHAIN", "KASA DROPS",
		 * "KASA HARAM", "KASA JUMIKI", "KASA NC", "KASA NECKLES", "KASA RING",
		 * "KASA T0PS", "KASA THONGAL", "KASA TOPS JIMKI", "KASA VAALI", "KASU", "KOLA",
		 * "KUTHU STAR", "LADIES BRACELET", "LADIES RING", "LAKSHMI BOTTU",
		 * "LAKSHMI KASU", "LAKSHMI POTTU", "LAXMI", "LEAF BESAR", "LEAF TOPS",
		 * "MANG TIKKA", "MANGA", "MANGA KASU", "MANGA NECKLES", "MANGA TOPS", "MATTLE",
		 * "MOHAN MALA", "MOPE CHAIN", "MOTTU", "MOTTU TONGAL", "NECKLACE",
		 * "NELLORE STONE DROPS", "NELLORE STONE TOPS", "NELLORE STONE TOPS JUMKI",
		 * "NER MATTAL", "NETTI CHUTTI", "OWAL RING", "RETTAI SARAM CHAIN", "RING",
		 * "ROUND RING", "S CHAIN", "S CHAIN ATTIKAI STONE TIKA", "SALANGI MATTAL",
		 * "SET JUMIKI", "SIDE BESAR", "side ma", "side mattale", "SIDE MATTLE",
		 * "SILVER CAL CHAIN", "STONE ADDIGAI", "STONE BASER", "STONE BASER",
		 * "STONE BROUCH", "STONE CHITTI MOPE CHAIN", "STONE DOLLAR", "STONE DROPS",
		 * "STONE FANCY CHAIN", "STONE HARAM", "STONE JUMIKI", "STONE JUMIKI TOPS",
		 * "STONE KASU", "STONE MATTLE", "STONE MOPE", "STONE MOPE CHAIN",
		 * "STONE NALY RING", "STONE NECKLES", "STONE NETTI CHUTTI", "STONE PURAI",
		 * "STONE PURAI TOPS", "STONE RING", "STONE SET JUMKI", "STONE THONGAL",
		 * "STONE TOPS", "SUTTU MATTLE", "TALI", "THALI", "THONGAL", "TOKYO CHAIN",
		 * "TONDU MATTLE", "TONGAL", "TOPS", "TV RING", "URU", "VAALI", "VAALI THONGAL",
		 * "VALAISEP", "VALAIYAM", "VISIRI STONE DROPS", "VISIRI STONE TOPS",
		 * "YANAI MUDI RING"));
		 * 
		 * ArrayList<String> productSilverList = new
		 * ArrayList<>(Arrays.asList("MUTHU KODI","ARUNA KODI","S CHAIN","KUSHBOO CHAIN"
		 * ,"KUSHBOO JAALAR CHAIN","KUSHBOO SALANGAI CHAIN","OTIYANAM","KAI KAAPU"
		 * ,"KAAL KAAPU","THANDAI",
		 * "21/2 MUTHU CHAIN","KUSHBOO THANIYA CHAIN","S CHAIN THANIYA CHAIN"
		 * ,"SMS CHAIN","M.S KOKKI CHAIN","S CHAIN PATTANI CHAIN"
		 * ,"KUSHBOO PATTANI CHAIN", "KAI CHAIN","KAL CHAIN","VELLI CHAIN"));
		 */

		String[] heirRelationList = { "", "S/O", "W/O", "H/O", "D/O" };
		JComboBox<String> heirRelation = new JComboBox<>(heirRelationList);

		String[] statusList = { "", "Paid", "Pending" };
		JComboBox<String> status = new JComboBox<>(statusList);

		String[] productType = { "", "Gold", "Silver", "Gold & Silver" };
		JComboBox<String> productTypes = new JComboBox<>(productType);

		productTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				productGoldList = loadData.loadGoldData();
				productSilverList = loadData.loadSilverData();
			}
		});

		// Set up the JFrame
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		// setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to full screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());

		// Set a custom look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialize components with a larger font and bold text
		Font inputFont = new Font("Arial", Font.BOLD, 15);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);

		shopName = new JLabel(" Sri Lalith Prasanna Jewellery - ADAGU BILL ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		billNum = new JTextFieldWithPlaceholder("Bill Num");
		LICENCENO = new JTextFieldWithPlaceholder("16/21-22");
		adaguAmt = new JTextFieldWithPlaceholder("Amount");
		custName = new JTextFieldWithPlaceholder("Name");
		custPhone = new JTextFieldWithPlaceholder("Phone");
		custHeir = new JTextFieldWithPlaceholder("Heir");
		adaguActualAmt = new JTextFieldWithPlaceholder("Actual Amount");
		productDetails = new JTextFieldWithPlaceholder("Products");
		productWeight = new JTextFieldWithPlaceholder("Weight");
		productValue = new JTextFieldWithPlaceholder("Value");
		custAddress = new JTextFieldWithPlaceholder("Address");
		adaguDate = new JDateChooser();
		redemptionDate = new JDateChooser();
		cancelDate = new JDateChooser();

		// Locker Detail New Fix
		lockerName = new JTextFieldWithPlaceholder("Locker Name");
		lockerBillNumber = new JTextFieldWithPlaceholder("Locker Bill Num");
		lockerDate = new JDateChooser();

		billNum.setEditable(true);
		adaguDate.setEnabled(true);
		redemptionDate.setEnabled(true);
		cancelDate.setEnabled(true);
		adaguActualAmt.setEditable(true);
		productValue.setEditable(true);
		LineBorder border = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
		billNum.setBorder(border);
		billNum.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguAmt.setBorder(border);
		adaguAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custName.setBorder(border);
		custName.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custPhone.setBorder(border);
		custPhone.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custHeir.setBorder(border);
		custHeir.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguActualAmt.setBorder(border);
		adaguActualAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productDetails.setBorder(border);
		productDetails.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productWeight.setBorder(border);
		productWeight.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productValue.setBorder(border);
		productValue.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguDate.setBorder(border);
		redemptionDate.setBorder(border);
		cancelDate.setBorder(border);
		custAddress.setBorder(border);
		custAddress.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		heirRelation.setBorder(border);
		heirRelation.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		status.setBorder(border);
		status.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productTypes.setBorder(border);
		productTypes.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));

		// Locker Detail New Fix
		lockerName.setBorder(border);
		lockerName.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		lockerBillNumber.setBorder(border);
		lockerBillNumber.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));

		// Set custom font for input fields and text area
		billNum.setFont(inputFont);
		adaguAmt.setFont(inputFont);
		custName.setFont(inputFont);
		custPhone.setFont(inputFont);
		custHeir.setFont(inputFont);
		adaguActualAmt.setFont(inputFont);
		productDetails.setFont(inputFont);
		productWeight.setFont(inputFont);
		productValue.setFont(inputFont);
		custAddress.setFont(inputFont);
		heirRelation.setFont(inputFont);
		status.setFont(inputFont);
		productTypes.setFont(inputFont);
		heirRelation.setPreferredSize(new Dimension(150, 50));
		status.setPreferredSize(new Dimension(150, 50));
		productTypes.setPreferredSize(new Dimension(150, 50));

		// Locker Detail New Fix
		lockerName.setFont(inputFont);
		lockerBillNumber.setFont(inputFont);
		lockerDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		lockerDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		lockerDate.setFont(inputFont);

		// Set button colors and styles
		saveBill = new JButton("Save");

		saveBill.setBackground(new Color(56, 142, 60)); // Green
		saveBill.setForeground(Color.RED);

		saveBill.setFont(labelFont);

		clearBill = new JButton("New");
		clearBill.setBackground(new Color(56, 142, 60)); // Green
		clearBill.setForeground(Color.RED);
		clearBill.setFont(labelFont);

		adaguDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		adaguDate.setDate(new java.util.Date()); // Set default system date
		adaguDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		adaguDate.setFont(inputFont);

		redemptionDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(adaguDate.getDate());
		calendar.add(Calendar.DAY_OF_YEAR, 365);
		redemptionDate.setDate(calendar.getTime());
		redemptionDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		redemptionDate.setFont(inputFont);

		cancelDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		cancelDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		cancelDate.setFont(inputFont);

		adaguDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = (Date) evt.getNewValue();
				if (selectedDate != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(selectedDate);
					calendar.add(Calendar.DAY_OF_YEAR, 365);
					redemptionDate.setDate(calendar.getTime());
				}
			}
		});

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem newItem = new JMenuItem("New");
		popupMenu.add(newItem);

		productDetails.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productString = JOptionPane.showInputDialog(null, "Product Name : ", "Quantity Input",
						JOptionPane.QUESTION_MESSAGE);
				if (productString != null && !productString.trim().isEmpty()) {
					try {
						String productType = productTypes.getSelectedItem().toString();
						if (productType.length() < 1) {
							JOptionPane.showMessageDialog(null, "Choose Either Gold or Silver", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);
						} else {
							DBConnect saveProd = new DBConnect();
							boolean isSaved = saveProd.saveNewProduct(productType, productString);
							if (isSaved) {
								JOptionPane.showMessageDialog(null, "Product Saved Succesfully!", "Success",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
										JOptionPane.ERROR_MESSAGE);

							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In product Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupAddressMenu = new JPopupMenu();
		JMenuItem newAddressItem = new JMenuItem("New");
		popupAddressMenu.add(newAddressItem);

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterAddressList = loadData.loadAddressData();
			}
		});

		custAddress.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newAddressItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custAddressString = JOptionPane.showInputDialog(null, "Customer Address", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custAddressString != null && !custAddressString.trim().isEmpty()) {
					try {
						DBConnect saveAddress = new DBConnect();
						boolean isSaved = saveAddress.saveNewAddress(custAddressString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Address Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupNameMenu = new JPopupMenu();
		JMenuItem newNameItem = new JMenuItem("New");
		popupNameMenu.add(newNameItem);

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterCustomerList = loadData.loadCustomerData();
			}
		});

		custName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newNameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Name", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewName(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Name Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupHeirMenu = new JPopupMenu();
		JMenuItem newHeirItem = new JMenuItem("New");
		popupHeirMenu.add(newHeirItem);

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterHeirList = loadData.loadCustomerHeirData();
			}
		});

		custHeir.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newHeirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Heir", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewHeir(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Heir Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Locker Detail New Fix
		JPopupMenu popupLockerMenu = new JPopupMenu();
		JMenuItem newLockerItem = new JMenuItem("New");
		popupLockerMenu.add(newLockerItem);

		lockerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterLockerList = loadData.loadLockerData();
			}
		});

		lockerName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupLockerMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupLockerMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newLockerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Locker Name", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewLockerDetails(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Locker Details Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Locker Details Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Locker Details Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Locker Details Saving!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();
				if (custPhone.getText().length() >= 10) {
					String phoneNumber = custPhone.getText().trim();
					AdaguBill latestBill = null;
					for (int i = AdaguBillListAdaguPanel.size() - 1; i >= 0; i--) {
						AdaguBill bill = AdaguBillListAdaguPanel.get(i);
						if (bill.getCUSTOMER_PHONE().equals(phoneNumber)) {
							latestBill = bill;
							break;
						}
					}
					if (latestBill != null) {
						custName.setText(latestBill.getCUSTOMER_NAME());
						custName.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custAddress.setText(latestBill.getCUSTOMER_ADDRESS());
						custAddress.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custHeir.setText(latestBill.getHEIR());
						custHeir.setForeground(new Color(247, 25, 92)); // Change text color when typing
						heirRelation.setSelectedItem(latestBill.getHEIR_RELATION());
						heirRelation.setForeground(new Color(247, 25, 92));
					} else {

					}
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				String LICENCENUM = LICENCENO.getText().toString().trim();
				BigDecimal maxValue = loadData.getMAXValueDummy(LICENCENUM);
				maxValue = maxValue.add(BigDecimal.ONE);
				if (adaguAmt.getText().length() > 0) {
					int adaguamt = Integer.parseInt(adaguAmt.getText().toString());
					int actvalue = adaguamt + 100;
					if (adaguamt > 0) {
						productValue.setText("" + actvalue);
						productValue.setForeground(new Color(247, 25, 92)); // Change text color when typing
						adaguActualAmt.setText("" + actvalue);
						adaguActualAmt.setForeground(new Color(247, 25, 92)); // Change text color when typing
					}
				}
				billNum.setText("" + maxValue.setScale(0, RoundingMode.HALF_UP));
				billNum.setForeground(new Color(247, 25, 92)); // Change text color when
				lockerName.setText("");
				lockerBillNumber.setText("");
			}
		});

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		selectedProductsModel = new DefaultListModel<>();
		selectedProductsList = new JList<>(selectedProductsModel);
		selectedProductsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedProductsList.setFont(new Font("Arial", Font.PLAIN, 14));
		selectedProductsList.setVisibleRowCount(5);
		selectedProductsList.setForeground(new Color(247, 25, 92)); // Change text color when typing

		JScrollPane scrollPane = new JScrollPane(selectedProductsList);
		scrollPane.setPreferredSize(new Dimension(200, 50));
		scrollPane.setBorder(border);
		scrollPane.setSize(100, 50);
		scrollPane.setPreferredSize(getMaximumSize());
		// panel.add(scrollPane);
		selectedProductsList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					String selectedProduct = selectedProductsList.getSelectedValue();
					if (selectedProduct != null) {
						selectedProductsModel.removeElement(selectedProduct);
					}
				}
			}
		});

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);

		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		shopNamePanel.add(shopName, BorderLayout.CENTER);
		shopNamePanel.add(licNoUrlLabel, BorderLayout.WEST);

		URL notifyUrl = TextFieldWithIcon.class.getResource("/images/mail.png");
		ImageIcon notifyicon = new ImageIcon(notifyUrl);
		Image notifyimage = notifyicon.getImage();
		Image notifyImage = notifyimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon notifyIcon = new ImageIcon(notifyImage);
		JLabel notifyLabel = new JLabel(notifyIcon);

		URL printUrl = TextFieldWithIcon.class.getResource("/images/printer.png");
		ImageIcon printicon = new ImageIcon(printUrl);
		Image printimage = printicon.getImage();
		Image printImage = printimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon printIcon = new ImageIcon(printImage);
		JLabel printLabel = new JLabel(printIcon);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 2));
		rightPanel.add(notifyLabel);
		rightPanel.add(printLabel);
		shopNamePanel.add(rightPanel, BorderLayout.LINE_END);

		panel.add(shopNamePanel, gbc);

		notifyLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				StringBuilder reminderMessage = new StringBuilder();
				reminderMessage.append(" அறிவிப்பு: ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
						+ " அடகு வைத்துளிர்கள்,  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" Bill Number  : " + BILLNUM);
				reminderMessage.append(" \n ");
				reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
				reminderMessage.append(" \n ");
				reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
						+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage
						.append(" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நன்றி! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
				reminderMessage.append(" \n ");

				StringBuilder displayMessage = new StringBuilder();
				displayMessage.append(" Bill Number  : " + BILLNUM);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Name : " + CUSTNAME);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Phone : " + CUSTPHONE);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Address : " + CUSTADDRESS);
				displayMessage.append(" \n");
				displayMessage.append(
						" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : " + REDEMPTIONDATEFORMATTED);
				displayMessage.append(" \n");
				displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
				displayMessage.append(" \n");

				// Create JTextArea for custom popup display
				JTextArea reminderArea = new JTextArea(displayMessage.toString());
				reminderArea.setEditable(false); // Make text area non-editable
				reminderArea.setWrapStyleWord(true); // Word wrapping
				reminderArea.setLineWrap(true);
				reminderArea.setCaretPosition(0); // Set cursor at the top
				reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
				reminderArea.setPreferredSize(new Dimension(400, 200));

				int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Copy" },
						"Copy");

				if (choice == 0) {
					String message = "" + reminderMessage;
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

			}
		});

		printLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				if (LICENCENUM.equals("*****")) {
					PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
							CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printForm(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE,
							CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printFormLic(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					LICENCENO.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					LICENCENO.setText("16/21-22");
				}
				isFirstImage = !isFirstImage;
			}
		});

		URL custPhoneUrl = TextFieldWithIcon.class.getResource("/images/telephone.png");
		ImageIcon custPhoneicon = new ImageIcon(custPhoneUrl);
		Image custPhoneimage = custPhoneicon.getImage();
		Image custPhoneImage = custPhoneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custPhoneIcon = new ImageIcon(custPhoneImage);
		JPanel custPhonePanel = new JPanel(new BorderLayout());
		JLabel custPhoneLabel = new JLabel(custPhoneIcon);
		custPhonePanel.add(custPhoneLabel, BorderLayout.WEST);
		JPanel Panel4 = new JPanel(new GridLayout(1, 2));
		Panel4.add(custPhone);
		Panel4.add(new JLabel(""));
		custPhonePanel.add(Panel4, BorderLayout.CENTER);
		panel.add(custPhonePanel, gbc);

		URL billUrl = TextFieldWithIcon.class.getResource("/images/bill.png");
		ImageIcon billicon = new ImageIcon(billUrl);
		Image billimage = billicon.getImage();
		Image billImage = billimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon billIcon = new ImageIcon(billImage);
		JPanel billPanel = new JPanel(new BorderLayout());
		JLabel billLabel = new JLabel(billIcon);
		billPanel.add(billLabel, BorderLayout.WEST);
		JPanel Panel1 = new JPanel(new GridLayout(1, 2));
		Panel1.add(billNum);
		Panel1.add(new JLabel(""));
		billPanel.add(Panel1, BorderLayout.CENTER);
		panel.add(billPanel, gbc);

		URL adagudateUrl = TextFieldWithIcon.class.getResource("/images/adaguDate.png");
		ImageIcon adagudateicon = new ImageIcon(adagudateUrl);
		Image adagudateimage = adagudateicon.getImage();
		Image adagudateImage = adagudateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaudateIcon = new ImageIcon(adagudateImage);
		JPanel adagudatePanel = new JPanel(new BorderLayout());
		JLabel adagudateLabel = new JLabel(adaudateIcon);
		adagudatePanel.add(adagudateLabel, BorderLayout.WEST);
		JPanel Panel2 = new JPanel(new GridLayout(1, 2));
		Panel2.add(adaguDate);
		Panel2.add(new JLabel(""));
		adagudatePanel.add(Panel2, BorderLayout.CENTER);
		panel.add(adagudatePanel, gbc);

		URL adaguAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguAmticon = new ImageIcon(adaguAmtUrl);
		Image adaguAmtimage = adaguAmticon.getImage();
		Image adaguAmtImage = adaguAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguAmtIcon = new ImageIcon(adaguAmtImage);
		JPanel adaguAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguAmtLabel = new JLabel(adaguAmtIcon);
		adaguAmtPanel.add(adaguAmtLabel, BorderLayout.WEST);
		JPanel Panel3 = new JPanel(new GridLayout(1, 2));
		Panel3.add(adaguAmt);
		Panel3.add(new JLabel(""));
		adaguAmtPanel.add(Panel3, BorderLayout.CENTER);
		panel.add(adaguAmtPanel, gbc);

		URL custNameUrl = TextFieldWithIcon.class.getResource("/images/label.png");
		ImageIcon custNameicon = new ImageIcon(custNameUrl);
		Image custNameimage = custNameicon.getImage();
		Image custNameImage = custNameimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custNameIcon = new ImageIcon(custNameImage);
		JPanel custNamePanel = new JPanel(new BorderLayout());
		JLabel custNameLabel = new JLabel(custNameIcon);
		custNamePanel.add(custNameLabel, BorderLayout.WEST);
		JPanel Panel5 = new JPanel(new GridLayout(1, 2));
		Panel5.add(custName);
		Panel5.add(new JLabel(""));
		custNamePanel.add(Panel5, BorderLayout.CENTER);
		panel.add(custNamePanel, gbc);

		heirRelation.setSelectedIndex(0);
		URL custHeirUrl = TextFieldWithIcon.class.getResource("/images/parent.png");
		ImageIcon custHeiricon = new ImageIcon(custHeirUrl);
		Image custHeirimage = custHeiricon.getImage();
		Image custHeirImage = custHeirimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custHeirIcon = new ImageIcon(custHeirImage);
		JPanel heirRelationPanel = new JPanel(new GridLayout(1, 3));
		heirRelationPanel.add(heirRelation);
		heirRelationPanel.add(custHeir);
		heirRelationPanel.add(new JLabel(""));
		heirRelationPanel.add(new JLabel(""));
		JPanel custHeirPanel = new JPanel(new BorderLayout());
		JLabel custHeirLabel = new JLabel(custHeirIcon);
		custHeirPanel.add(custHeirLabel, BorderLayout.WEST);
		custHeirPanel.add(heirRelationPanel, BorderLayout.CENTER);
		panel.add(custHeirPanel, gbc);

		URL custAddressUrl = TextFieldWithIcon.class.getResource("/images/gps.png");
		ImageIcon custAddressicon = new ImageIcon(custAddressUrl);
		Image custAddressimage = custAddressicon.getImage();
		Image custAddressImage = custAddressimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custAddressIcon = new ImageIcon(custAddressImage);
		JPanel custAddressPanel = new JPanel(new BorderLayout());
		JLabel custAddressLabel = new JLabel(custAddressIcon);
		custAddressPanel.add(custAddressLabel, BorderLayout.WEST);
		JPanel Panel6 = new JPanel(new GridLayout(1, 2));
		Panel6.add(custAddress);
		Panel6.add(new JLabel(""));
		custAddressPanel.add(Panel6, BorderLayout.CENTER);
		panel.add(custAddressPanel, gbc);

		/*
		 * URL custHeirUrl = TextFieldWithIcon.class.getResource("/images/parent.png");
		 * ImageIcon custHeiricon = new ImageIcon(custHeirUrl); Image custHeirimage =
		 * custHeiricon.getImage(); Image custHeirImage =
		 * custHeirimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH); ImageIcon
		 * custHeirIcon = new ImageIcon(custHeirImage); JPanel custHeirPanel = new
		 * JPanel(new BorderLayout()); JLabel custHeirLabel = new JLabel(custHeirIcon);
		 * custHeirPanel.add(custHeirLabel, BorderLayout.WEST);
		 * custHeirPanel.add(heirRelation, BorderLayout.EAST);
		 * custHeirPanel.add(custHeir, BorderLayout.CENTER); panel.add(custHeirPanel,
		 * gbc);
		 */

		URL adaguActualAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguActualAmticon = new ImageIcon(adaguActualAmtUrl);
		Image adaguActualAmtimage = adaguActualAmticon.getImage();
		Image adaguActualAmtImage = adaguActualAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguActualAmtIcon = new ImageIcon(adaguActualAmtImage);
		JPanel adaguActualAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguActualAmtLabel = new JLabel(adaguActualAmtIcon);
		adaguActualAmtPanel.add(adaguActualAmtLabel, BorderLayout.WEST);
		JPanel Panel7 = new JPanel(new GridLayout(1, 2));
		Panel7.add(adaguActualAmt);
		Panel7.add(new JLabel(""));
		adaguActualAmtPanel.add(Panel7, BorderLayout.CENTER);
		panel.add(adaguActualAmtPanel, gbc);

		/*
		 * URL productDetailsUrl =
		 * TextFieldWithIcon.class.getResource("/images/product.png"); ImageIcon
		 * productDetailsicon = new ImageIcon(productDetailsUrl); Image
		 * productDetailsimage = productDetailsicon.getImage(); Image
		 * productDetailsImage = productDetailsimage.getScaledInstance(40, 40,
		 * Image.SCALE_SMOOTH); ImageIcon productDetailsIcon = new
		 * ImageIcon(productDetailsImage); JPanel productDetailsPanel = new JPanel(new
		 * BorderLayout()); JLabel productDetailsLabel = new JLabel(productDetailsIcon);
		 * productDetailsPanel.add(productDetailsLabel, BorderLayout.WEST);
		 * productTypes.setSelectedIndex(0); productDetailsPanel.add(productTypes,
		 * BorderLayout.EAST); productDetailsPanel.add(productDetails,
		 * BorderLayout.CENTER); panel.add(productDetailsPanel, gbc);
		 */

		URL productDetailsUrl = TextFieldWithIcon.class.getResource("/images/product.png");
		ImageIcon productDetailsicon = new ImageIcon(productDetailsUrl);
		Image productDetailsimage = productDetailsicon.getImage();
		Image productDetailsImage = productDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productDetailsIcon = new ImageIcon(productDetailsImage);
		JPanel productDetailsPanel = new JPanel(new GridLayout(1, 3));
		JLabel productDetailsLabel = new JLabel(productDetailsIcon);
		productDetailsPanel.add(productTypes);
		productDetailsPanel.add(productDetails);
		productDetailsPanel.add(new JLabel(""));
		productDetailsPanel.add(new JLabel(""));
		JPanel productDetailsPanel1 = new JPanel(new BorderLayout());
		productDetailsPanel1.add(productDetailsLabel, BorderLayout.WEST);
		productDetailsPanel1.add(productDetailsPanel, BorderLayout.CENTER);
		panel.add(productDetailsPanel1, gbc);

		URL scrollPaneUrl = TextFieldWithIcon.class.getResource("/images/scroll.png");
		ImageIcon scrollPaneicon = new ImageIcon(scrollPaneUrl);
		Image scrollPaneimage = scrollPaneicon.getImage();
		Image scrollPaneImage = scrollPaneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon scrollPaneIcon = new ImageIcon(scrollPaneImage);
		JPanel scrollPanePanel = new JPanel(new BorderLayout());
		JLabel scrollPaneLabel = new JLabel(scrollPaneIcon);
		scrollPanePanel.add(scrollPaneLabel, BorderLayout.WEST);
		JPanel Panel8 = new JPanel(new GridLayout(1, 2));
		Panel8.add(scrollPane);
		Panel8.add(new JLabel(""));
		scrollPanePanel.add(Panel8, BorderLayout.CENTER);
		panel.add(scrollPanePanel, gbc);

		URL productWeightUrl = TextFieldWithIcon.class.getResource("/images/weight.png");
		ImageIcon productWeighticon = new ImageIcon(productWeightUrl);
		Image productWeightimage = productWeighticon.getImage();
		Image productWeightImage = productWeightimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productWeightIcon = new ImageIcon(productWeightImage);
		JPanel productWeightPanel = new JPanel(new BorderLayout());
		JLabel productWeightLabel = new JLabel(productWeightIcon);
		productWeightPanel.add(productWeightLabel, BorderLayout.WEST);
		JPanel Panel9 = new JPanel(new GridLayout(1, 2));
		Panel9.add(productWeight);
		Panel9.add(new JLabel(""));
		productWeightPanel.add(Panel9, BorderLayout.CENTER);
		panel.add(productWeightPanel, gbc);

		URL productValueUrl = TextFieldWithIcon.class.getResource("/images/value.png");
		ImageIcon productValueicon = new ImageIcon(productValueUrl);
		Image productValueimage = productValueicon.getImage();
		Image productValueImage = productValueimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productValueIcon = new ImageIcon(productValueImage);
		JPanel productValuePanel = new JPanel(new BorderLayout());
		JLabel productValueLabel = new JLabel(productValueIcon);
		productValuePanel.add(productValueLabel, BorderLayout.WEST);
		JPanel Panel10 = new JPanel(new GridLayout(1, 2));
		Panel10.add(productValue);
		Panel10.add(new JLabel(""));
		productValuePanel.add(Panel10, BorderLayout.CENTER);
		panel.add(productValuePanel, gbc);

		URL redemptionDateUrl = TextFieldWithIcon.class.getResource("/images/expiry.png");
		ImageIcon redemptionDateicon = new ImageIcon(redemptionDateUrl);
		Image redemptionDateimage = redemptionDateicon.getImage();
		Image redemptionDateImage = redemptionDateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon redemptionDateIcon = new ImageIcon(redemptionDateImage);
		JPanel redemptionDatePanel = new JPanel(new BorderLayout());
		JLabel redemptionDateLabel = new JLabel(redemptionDateIcon);
		redemptionDatePanel.add(redemptionDateLabel, BorderLayout.WEST);
		status.setSelectedIndex(0);
		redemptionDatePanel.add(status, BorderLayout.EAST);
		JPanel redemptionDatesPanel = new JPanel(new GridLayout(1, 3));
		redemptionDatesPanel.add(redemptionDate);
		redemptionDatesPanel.add(status);
		redemptionDatesPanel.add(cancelDate);
		redemptionDatesPanel.add(new JLabel(""));
		redemptionDatesPanel.add(new JLabel(""));
		redemptionDatesPanel.add(new JLabel(""));
		redemptionDatePanel.add(redemptionDatesPanel, BorderLayout.CENTER);
		panel.add(redemptionDatePanel, gbc);

		// Locker Detail New Fix
		URL lockerDetailsUrl = TextFieldWithIcon.class.getResource("/images/lock.png");
		ImageIcon lockerDetailsicon = new ImageIcon(lockerDetailsUrl);
		Image lockerDetailsimage = lockerDetailsicon.getImage();
		Image lockerDetailsImage = lockerDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon lockerDetailsIcon = new ImageIcon(lockerDetailsImage);
		JPanel lockerDetailsPanel = new JPanel(new BorderLayout());
		JLabel lockerDetailsLabel = new JLabel(lockerDetailsIcon);
		lockerDetailsPanel.add(lockerDetailsLabel, BorderLayout.WEST);
		JPanel lockerDetailSPanel = new JPanel(new GridLayout(1, 3));
		lockerDetailSPanel.add(lockerName);
		lockerDetailSPanel.add(lockerBillNumber);
		lockerDetailSPanel.add(lockerDate);
		lockerDetailSPanel.add(new JLabel(""));
		lockerDetailSPanel.add(new JLabel(""));
		lockerDetailSPanel.add(new JLabel(""));
		lockerDetailsPanel.add(lockerDetailSPanel, BorderLayout.CENTER);
		panel.add(lockerDetailsPanel, gbc);

		// panel.add(saveBill);
		// panel.add(printBill);
		// panel.add(notifyBill);
		// panel.add(clearBill);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));
		bottomPanel.add(saveBill);
		bottomPanel.add(clearBill);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		JPanel bottomPanel1 = new JPanel(new BorderLayout());
		bottomPanel1.add(bottomPanel, BorderLayout.CENTER);
		panel.add(bottomPanel1, gbc);

		clearBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBill.setEnabled(true);
				billNum.setEditable(true);
				billNum.setText("Bill Num");
				billNum.setForeground(new Color(157, 161, 250));
				adaguAmt.setEditable(true);
				adaguAmt.setText("Amount");
				adaguAmt.setForeground(new Color(157, 161, 250));
				custPhone.setEditable(true);
				custPhone.setText("Phone");
				custPhone.setForeground(new Color(157, 161, 250));
				custName.setEditable(true);
				custName.setText("Name");
				custName.setForeground(new Color(157, 161, 250));
				custAddress.setEditable(true);
				custAddress.setText("Address");
				custAddress.setForeground(new Color(157, 161, 250));
				custHeir.setEditable(true);
				custHeir.setText("Heir");
				custHeir.setForeground(new Color(157, 161, 250));
				heirRelation.setSelectedIndex(0);
				heirRelation.setEditable(true);
				productTypes.setSelectedIndex(0);
				productTypes.setEditable(true);
				adaguActualAmt.setEditable(true);
				adaguActualAmt.setText("Actual Amount");
				adaguActualAmt.setForeground(new Color(157, 161, 250));
				productWeight.setEditable(true);
				productWeight.setText("Weight");
				productWeight.setForeground(new Color(157, 161, 250));
				productValue.setEditable(true);
				productValue.setText("Value");
				productValue.setForeground(new Color(157, 161, 250));
				productDetails.setEditable(true);
				productDetails.setText("Products");
				productDetails.setForeground(new Color(157, 161, 250));
				selectedProductsModel.clear();
				adaguDate.setDate(new java.util.Date()); // Set default system date
				cancelDate.setDate(null);

				// Locker Detail New Fix
				lockerName.setEditable(true);
				lockerName.setText("Locker Name");
				lockerName.setForeground(new Color(157, 161, 250));
				lockerBillNumber.setEditable(true);
				lockerBillNumber.setText("Locker Bill Num");
				lockerBillNumber.setForeground(new Color(157, 161, 250));
				lockerDate.setDate(null);
			}
		});

		productDetails.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = productDetails.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				String PRODUCTTYPE = productTypes.getSelectedItem().toString();
				if (PRODUCTTYPE.equals("Gold")) {
					int i = 0;
					for (String product : productGoldList) {
						if (product.toLowerCase().startsWith(typedText) && i <= 15) {
							suggestions.add(product);
							i++;
						}
					}
				} else if (PRODUCTTYPE.equals("Silver")) {
					int j = 0;
					for (String product : productSilverList) {
						if (product.toLowerCase().startsWith(typedText) && j <= 15) {
							suggestions.add(product);
							j++;
						}
					}
				}

				// Show suggestions in a combo box if any
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									int qty = 0;
									String qtyString = JOptionPane.showInputDialog(null, "" + suggestion + " : ",
											"Quantity Input", JOptionPane.QUESTION_MESSAGE);
									if (qtyString != null && !qtyString.trim().isEmpty()) {
										try {
											int quantity = Integer.parseInt(qtyString);
											if (quantity > 0) {
												qty = quantity;
											} else {
												JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.",
														"Error", JOptionPane.ERROR_MESSAGE);
												qty = 0;
											}
										} catch (NumberFormatException ex) {
											JOptionPane.showMessageDialog(null, "Please enter a valid number.",
													"Invalid Input", JOptionPane.ERROR_MESSAGE);
											qty = 0;
										}
									} else {
										JOptionPane.showMessageDialog(null, "Quantity is required.", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
//									selectedProductsModel.addElement(suggestion + " - " + qty);
									//selectedProductsModel.addElement(PRODUCTTYPE + " - " + suggestion + " - " + qty); // 23July2025
									selectedProductsModel.addElement(suggestion + "-" + qty); //27Oct2025
									productDetails.setText(""); // Clear text field after selection
								}
								// Hide the popup after selection
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(productDetails, 0, productDetails.getHeight());

				}
			}

		});

		billNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		adaguActualAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		productWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = productWeight.getText().toString().trim();
				/*
				 * if (!Character.isDigit(c) && c != '.' || (c == '.' && text.contains("."))) {
				 * e.consume(); }
				 */
				if ((!Character.isDigit(c) && c != '.' && c != '/') || (c == '/' && text.split("/+").length > 1)) {
					e.consume();
				}
			}
		});

		productValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custName.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterCustomerList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custName.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custName, 0, custName.getHeight());
				}
			}
		});

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custAddress.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterAddressList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custAddress.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custAddress, 0, custAddress.getHeight());
				}
			}
		});

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custHeir.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterHeirList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custHeir.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custHeir, 0, custHeir.getHeight());
				}
			}
		});

		// Locker Detail New Fix
		lockerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = lockerName.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterLockerList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									lockerName.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(lockerName, 0, lockerName.getHeight());
				}
			}
		});

		saveBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				String PRODUCTTYPE = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "", CANCELDATEFORMATTED = "";
				String STATUS = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				// Locker Detail New Fix
				String LOCKERNAME = "";
				String LOCKERBILLNUMBER = "";
				String LOCKERDATEFORMATTED = "";

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString().trim();
				BILLNUM = billNum.getText().toString().trim();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString().trim());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString().trim());
				CUSTNAME = custName.getText().toString().trim();
				CUSTADDRESS = custAddress.getText().toString().trim();
				CUSTHEIR = custHeir.getText().toString().trim();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString().trim();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString().trim());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString().trim());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				if (null != cancelDate.getDate()) {
					CANCELDATEFORMATTED = sdf.format(cancelDate.getDate());
				}
				PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
				STATUS = status.getSelectedItem().toString().trim();
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + "~");
				}

				// Locker Detail New Fix
				LOCKERNAME = lockerName.getText().toString().trim().toUpperCase();
				LOCKERBILLNUMBER = lockerBillNumber.getText().toString().trim().toUpperCase();
				if (null != lockerDate.getDate()) {
					LOCKERDATEFORMATTED = sdf.format(lockerDate.getDate());
				}

				AdaguBill adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(LICENCENUM);
				adaguData.setBILL_NUMBER(BILLNUM);
				adaguData.setCUSTOMER_NAME(CUSTNAME);
				adaguData.setCUSTOMER_PHONE("" + CUSTPHONE);
				adaguData.setHEIR_RELATION(CUSTHEIRRELATION);
				adaguData.setHEIR(CUSTHEIR);
				adaguData.setCUSTOMER_ADDRESS(CUSTADDRESS);
				adaguData.setPRODUCTS(PRODUCT.toString().trim());
				adaguData.setPRODUCT_WEIGHT("" + PRODUCTWEIGTH);
				adaguData.setADAGU_AMOUNT("" + ADAGUAMT);
				adaguData.setACTUAL_ADAGU_AMOUNT("" + ADAGUACTUALAMT);
				adaguData.setPRODUCT_VALUE("" + PRODUCTVALUE);
				adaguData.setADAGU_DATE(ADAGUDATEFORMATTED);
				adaguData.setREDEMPTION_DATE(REDEMPTIONDATEFORMATTED);
				adaguData.setCANCEL_DATE(CANCELDATEFORMATTED);
				adaguData.setPRODUCT_TYPE(PRODUCTTYPE);
				adaguData.setBILL_TYPE("Adagu Bill");

				// Locker Detail New Fix
				adaguData.setLOCKER_NAME(LOCKERNAME);
				adaguData.setLOCKER_BILLNUM(LOCKERBILLNUMBER);
				adaguData.setLOCKER_DATE(LOCKERDATEFORMATTED);

				if (STATUS.equals("Paid")) {
					// adaguData.setREDEMPTION_DATE(""+new SimpleDateFormat("dd-MM-yyyy").format(new
					// Date()));
					adaguData.setSTATUS("Paid");
				} else {
					adaguData.setSTATUS("Pending");
				}

				DBConnect saveAdagu = new DBConnect();
				boolean isSaved = false;
				isSaved = saveAdagu.saveAdaguBill(adaguData);

				if (isSaved) {
					JOptionPane.showConfirmDialog(panel, " Bill Saved Successfully !!! ");
					saveBill.setEnabled(false);
				} else {
					JOptionPane.showConfirmDialog(panel, " Error : Save Bill !!! ");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				// Locker Detail New Fix
				lockerName.setEditable(false);
				lockerName.setBackground(Color.LIGHT_GRAY);
				lockerName.setBorder(correctborder);
				lockerName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				lockerBillNumber.setEditable(false);
				lockerBillNumber.setBackground(Color.LIGHT_GRAY);
				lockerBillNumber.setBorder(correctborder);
				lockerBillNumber
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);

			}
		});

		setVisible(true);

		return panel;
	}

	// Panel for Add Adagu Bill
	private JPanel AdmincreateAddAdaguBillPanel() {
		JPanel panel;
		JTextField billNum, adaguAmt, custName, custPhone, custHeir, adaguActualAmt, productDetails, productWeight,
				productValue, custAddress;
		JLabel shopName;
		JButton saveBill, notifyBill, printBill, clearBill;
		JDateChooser adaguDate, redemptionDate;
		DefaultListModel<String> selectedProductsModel;
		JList<String> selectedProductsList;
		JTextField LICENCENO; // = " 16/21-22 "

		// Simulating a product list (in a real-world scenario, this could be from a
		// database)
		/*
		 * ArrayList<String> productList = new ArrayList<>(Arrays.asList("2 LINE CHINE",
		 * "ADDAGAI ,STONE TEEKA", "BABY RING", "BANGLES", "BASER", "BOX RING",
		 * "BRACELET", "BROCH", "BUTTON TOPS", "BUTTON TOPS THONGAL", "CAST DROPS",
		 * "CASTING RING", "CASTING TOPS", "CHAIN", "CHAIN WITH DOLLAR", "DELHI CHAIN",
		 * "DELHI SUTTU MATTLE", "DIE COIN", "DOLLAR", "FANCE HARAM", "FANCE NECKLES",
		 * "FANCY AARAM", "FANCY DROPS", "FANCY JUMKI", "FANCY MATTLE", "FANCY NECKLES",
		 * "FANCY RING", "FANCY TOPS", "FANCY TOPS JIMUKI", "GALSER", "GENTS RING",
		 * "GUNDU WT LOCK", "HARAM", "JABKA", "JUMIKI", "JUMIKI TOPS", "K TONGAL",
		 * "KALAN BASEAR", "KAMBI", "KAPPU", "KARI MANI CHAIN", "KASA DROPS",
		 * "KASA HARAM", "KASA JUMIKI", "KASA NC", "KASA NECKLES", "KASA RING",
		 * "KASA T0PS", "KASA THONGAL", "KASA TOPS JIMKI", "KASA VAALI", "KASU", "KOLA",
		 * "KUTHU STAR", "LADIES BRACELET", "LADIES RING", "LAKSHMI BOTTU",
		 * "LAKSHMI KASU", "LAKSHMI POTTU", "LAXMI", "LEAF BESAR", "LEAF TOPS",
		 * "MANG TIKKA", "MANGA", "MANGA KASU", "MANGA NECKLES", "MANGA TOPS", "MATTLE",
		 * "MOHAN MALA", "MOPE CHAIN", "MOTTU", "MOTTU TONGAL", "NECKLACE",
		 * "NELLORE STONE DROPS", "NELLORE STONE TOPS", "NELLORE STONE TOPS JUMKI",
		 * "NER MATTAL", "NETTI CHUTTI", "OWAL RING", "RETTAI SARAM CHAIN", "RING",
		 * "ROUND RING", "S CHAIN", "S CHAIN ATTIKAI STONE TIKA", "SALANGI MATTAL",
		 * "SET JUMIKI", "SIDE BESAR", "side ma", "side mattale", "SIDE MATTLE",
		 * "SILVER CAL CHAIN", "STONE ADDIGAI", "STONE BASER", "STONE BASER",
		 * "STONE BROUCH", "STONE CHITTI MOPE CHAIN", "STONE DOLLAR", "STONE DROPS",
		 * "STONE FANCY CHAIN", "STONE HARAM", "STONE JUMIKI", "STONE JUMIKI TOPS",
		 * "STONE KASU", "STONE MATTLE", "STONE MOPE", "STONE MOPE CHAIN",
		 * "STONE NALY RING", "STONE NECKLES", "STONE NETTI CHUTTI", "STONE PURAI",
		 * "STONE PURAI TOPS", "STONE RING", "STONE SET JUMKI", "STONE THONGAL",
		 * "STONE TOPS", "SUTTU MATTLE", "TALI", "THALI", "THONGAL", "TOKYO CHAIN",
		 * "TONDU MATTLE", "TONGAL", "TOPS", "TV RING", "URU", "VAALI", "VAALI THONGAL",
		 * "VALAISEP", "VALAIYAM", "VISIRI STONE DROPS", "VISIRI STONE TOPS",
		 * "YANAI MUDI RING"));
		 */

		String[] heirRelationList = { "", "S/O", "W/O", "H/O", "D/O" };
		JComboBox<String> heirRelation = new JComboBox<>(heirRelationList);

		String[] statusList = { "", "Paid", "Pending" };
		JComboBox<String> status = new JComboBox<>(statusList);

		String[] productType = { "", "Gold", "Silver", "Gold & Silver" };
		JComboBox<String> productTypes = new JComboBox<>(productType);

		productTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				productGoldList = loadData.loadGoldData();
				productSilverList = loadData.loadSilverData();
			}
		});

		// Set up the JFrame
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		// setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the JFrame to full screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());

		// Set a custom look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialize components with a larger font and bold text
		Font inputFont = new Font("Arial", Font.BOLD, 15);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);

		shopName = new JLabel(" Sri Lalith Prasanna Jewellery - ADAGU BILL ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		billNum = new JTextFieldWithPlaceholder("Bill Num");
		LICENCENO = new JTextFieldWithPlaceholder("16/21-22");
		adaguAmt = new JTextFieldWithPlaceholder("Amount");
		custName = new JTextFieldWithPlaceholder("Name");
		custPhone = new JTextFieldWithPlaceholder("Phone");
		custHeir = new JTextFieldWithPlaceholder("Heir");
		adaguActualAmt = new JTextFieldWithPlaceholder("Actual Amount");
		productDetails = new JTextFieldWithPlaceholder("Products");
		productWeight = new JTextFieldWithPlaceholder("Weight");
		productValue = new JTextFieldWithPlaceholder("Value");
		custAddress = new JTextFieldWithPlaceholder("Address");
		adaguDate = new JDateChooser();
		redemptionDate = new JDateChooser();

		billNum.setEditable(false);
		adaguDate.setEnabled(true);
		redemptionDate.setEnabled(true);
		adaguActualAmt.setEditable(false);
		productValue.setEditable(false);
		LineBorder border = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
		billNum.setBorder(border);
		billNum.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguAmt.setBorder(border);
		adaguAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custName.setBorder(border);
		custName.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custPhone.setBorder(border);
		custPhone.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		custHeir.setBorder(border);
		custHeir.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguActualAmt.setBorder(border);
		adaguActualAmt.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productDetails.setBorder(border);
		productDetails.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productWeight.setBorder(border);
		productWeight.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productValue.setBorder(border);
		productValue.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		adaguDate.setBorder(border);
		redemptionDate.setBorder(border);
		custAddress.setBorder(border);
		custAddress.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		heirRelation.setBorder(border);
		heirRelation.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		status.setBorder(border);
		status.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));
		productTypes.setBorder(border);
		productTypes.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(5, 5, 5, 5)));

		// Set custom font for input fields and text area
		billNum.setFont(inputFont);
		adaguAmt.setFont(inputFont);
		custName.setFont(inputFont);
		custPhone.setFont(inputFont);
		custHeir.setFont(inputFont);
		adaguActualAmt.setFont(inputFont);
		productDetails.setFont(inputFont);
		productWeight.setFont(inputFont);
		productValue.setFont(inputFont);
		custAddress.setFont(inputFont);
		heirRelation.setFont(inputFont);
		heirRelation.setPreferredSize(new Dimension(150, 50));
		status.setFont(inputFont);
		heirRelation.setPreferredSize(new Dimension(150, 50));
		status.setPreferredSize(new Dimension(150, 50));

		productTypes.setFont(inputFont);
		productTypes.setPreferredSize(new Dimension(150, 50));

		// Set button colors and styles
		saveBill = new JButton("Save");
		notifyBill = new JButton("Notify");
		printBill = new JButton("Print");
		clearBill = new JButton("New");

		saveBill.setBackground(new Color(56, 142, 60)); // Green
		saveBill.setForeground(Color.RED);
		notifyBill.setBackground(new Color(244, 67, 54)); // Red
		notifyBill.setForeground(Color.RED);
		printBill.setBackground(new Color(33, 150, 243)); // Blue
		printBill.setForeground(Color.RED);
		clearBill.setBackground(new Color(33, 150, 243)); // Blue
		clearBill.setForeground(Color.RED);

		saveBill.setFont(labelFont);
		notifyBill.setFont(labelFont);
		printBill.setFont(labelFont);
		clearBill.setFont(labelFont);

		adaguDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		adaguDate.setDate(new java.util.Date()); // Set default system date
		adaguDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		adaguDate.setFont(inputFont);

		redemptionDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 365);
		redemptionDate.setDate(calendar.getTime());
		redemptionDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		redemptionDate.setFont(inputFont);

		adaguDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Date selectedDate = (Date) evt.getNewValue();
				if (selectedDate != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(selectedDate);
					calendar.add(Calendar.DAY_OF_YEAR, 365);
					redemptionDate.setDate(calendar.getTime());
				}
			}
		});

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem newItem = new JMenuItem("New");
		popupMenu.add(newItem);

		productDetails.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productString = JOptionPane.showInputDialog(null, "Product Name : ", "Quantity Input",
						JOptionPane.QUESTION_MESSAGE);
				if (productString != null && !productString.trim().isEmpty()) {
					try {
						String productType = productTypes.getSelectedItem().toString();
						if (productType.length() < 1) {
							JOptionPane.showMessageDialog(null, "Choose Either Gold or Silver", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);
						} else {
							DBConnect saveProd = new DBConnect();
							boolean isSaved = saveProd.saveNewProduct(productType, productString);
							if (isSaved) {
								JOptionPane.showMessageDialog(null, "Product Saved Succesfully!", "Success",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
										JOptionPane.ERROR_MESSAGE);

							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In product Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In product Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupAddressMenu = new JPopupMenu();
		JMenuItem newAddressItem = new JMenuItem("New");
		popupAddressMenu.add(newAddressItem);

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterAddressList = loadData.loadAddressData();
			}
		});

		custAddress.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupAddressMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newAddressItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custAddressString = JOptionPane.showInputDialog(null, "Customer Address", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custAddressString != null && !custAddressString.trim().isEmpty()) {
					try {
						DBConnect saveAddress = new DBConnect();
						boolean isSaved = saveAddress.saveNewAddress(custAddressString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Address Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Address Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupNameMenu = new JPopupMenu();
		JMenuItem newNameItem = new JMenuItem("New");
		popupNameMenu.add(newNameItem);

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterCustomerList = loadData.loadCustomerData();
			}
		});

		custName.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupNameMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newNameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Name", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewName(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Name Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Name Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPopupMenu popupHeirMenu = new JPopupMenu();
		JMenuItem newHeirItem = new JMenuItem("New");
		popupHeirMenu.add(newHeirItem);

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				masterHeirList = loadData.loadCustomerHeirData();
			}
		});

		custHeir.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupHeirMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		newHeirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custNameString = JOptionPane.showInputDialog(null, "Customer Heir", "",
						JOptionPane.QUESTION_MESSAGE);
				if (custNameString != null && !custNameString.trim().isEmpty()) {
					try {
						DBConnect saveName = new DBConnect();
						boolean isSaved = saveName.saveNewHeir(custNameString);
						if (isSaved) {
							JOptionPane.showMessageDialog(null, "Heir Saved Succesfully!", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error In Heir Saving!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();
				if (custPhone.getText().length() >= 10) {
					String phoneNumber = custPhone.getText().trim();
					AdaguBill latestBill = null;
					for (int i = AdaguBillListAdaguPanel.size() - 1; i >= 0; i--) {
						AdaguBill bill = AdaguBillListAdaguPanel.get(i);
						if (bill.getCUSTOMER_PHONE().equals(phoneNumber)) {
							latestBill = bill;
							break;
						}
					}
					if (latestBill != null) {
						custName.setText(latestBill.getCUSTOMER_NAME());
						custName.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custAddress.setText(latestBill.getCUSTOMER_ADDRESS());
						custAddress.setForeground(new Color(247, 25, 92)); // Change text color when typing
						custHeir.setText(latestBill.getHEIR());
						custHeir.setForeground(new Color(247, 25, 92)); // Change text color when typing
						heirRelation.setSelectedItem(latestBill.getHEIR_RELATION());
						heirRelation.setForeground(new Color(247, 25, 92));
					} else {

					}
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListAdaguPanel = loadData.loadADAGUData();
				String LICENCENUM = LICENCENO.getText().trim().toString();
				BigDecimal maxValue = loadData.getMAXValue(LICENCENUM);
				maxValue = maxValue.add(BigDecimal.ONE);
				if (adaguAmt.getText().length() > 0) {
					int adaguamt = Integer.parseInt(adaguAmt.getText().toString());
					int actvalue = adaguamt + 100;
					if (adaguamt > 0) {
						productValue.setText("" + actvalue);
						productValue.setForeground(new Color(247, 25, 92)); // Change text color when typing
						adaguActualAmt.setText("" + actvalue);
						adaguActualAmt.setForeground(new Color(247, 25, 92)); // Change text color when typing
					}
				}
				billNum.setText("" + maxValue.setScale(0, RoundingMode.HALF_UP));
				billNum.setForeground(new Color(247, 25, 92)); // Change text color when typing
			}
		});

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		selectedProductsModel = new DefaultListModel<>();
		selectedProductsList = new JList<>(selectedProductsModel);
		selectedProductsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedProductsList.setFont(new Font("Arial", Font.PLAIN, 14));
		selectedProductsList.setVisibleRowCount(5);
		selectedProductsList.setForeground(new Color(247, 25, 92)); // Change text color when typing

		JScrollPane scrollPane = new JScrollPane(selectedProductsList);
		scrollPane.setPreferredSize(new Dimension(200, 50));
		scrollPane.setBorder(border);
		scrollPane.setSize(100, 50);
		scrollPane.setPreferredSize(getMaximumSize());
		// panel.add(scrollPane);
		selectedProductsList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					String selectedProduct = selectedProductsList.getSelectedValue();
					if (selectedProduct != null) {
						selectedProductsModel.removeElement(selectedProduct);
					}
				}
			}
		});

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);

		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		shopNamePanel.add(shopName, BorderLayout.CENTER);
		shopNamePanel.add(licNoUrlLabel, BorderLayout.WEST);

		URL notifyUrl = TextFieldWithIcon.class.getResource("/images/mail.png");
		ImageIcon notifyicon = new ImageIcon(notifyUrl);
		Image notifyimage = notifyicon.getImage();
		Image notifyImage = notifyimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon notifyIcon = new ImageIcon(notifyImage);
		JLabel notifyLabel = new JLabel(notifyIcon);

		URL printUrl = TextFieldWithIcon.class.getResource("/images/printer.png");
		ImageIcon printicon = new ImageIcon(printUrl);
		Image printimage = printicon.getImage();
		Image printImage = printimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon printIcon = new ImageIcon(printImage);
		JLabel printLabel = new JLabel(printIcon);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(1, 2));
		rightPanel.add(notifyLabel);
		rightPanel.add(printLabel);
		shopNamePanel.add(rightPanel, BorderLayout.LINE_END);

		panel.add(shopNamePanel, gbc);

		notifyLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				StringBuilder reminderMessage = new StringBuilder();
				reminderMessage.append(" அறிவிப்பு: ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
						+ " அடகு வைத்துளிர்கள்,  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" Bill Number  : " + BILLNUM);
				reminderMessage.append(" \n ");
				reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
				reminderMessage.append(" \n ");
				reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
						+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage
						.append(" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நன்றி! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
				reminderMessage.append(" \n ");

				StringBuilder displayMessage = new StringBuilder();
				displayMessage.append(" Bill Number  : " + BILLNUM);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Name : " + CUSTNAME);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Phone : " + CUSTPHONE);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Address : " + CUSTADDRESS);
				displayMessage.append(" \n");
				displayMessage.append(
						" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : " + REDEMPTIONDATEFORMATTED);
				displayMessage.append(" \n");
				displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
				displayMessage.append(" \n");

				// Create JTextArea for custom popup display
				JTextArea reminderArea = new JTextArea(displayMessage.toString());
				reminderArea.setEditable(false); // Make text area non-editable
				reminderArea.setWrapStyleWord(true); // Word wrapping
				reminderArea.setLineWrap(true);
				reminderArea.setCaretPosition(0); // Set cursor at the top
				reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
				reminderArea.setPreferredSize(new Dimension(400, 200));

				int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Copy" },
						"Copy");

				if (choice == 0) {
					String message = "" + reminderMessage;
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				productTypes.setEditable(false);
				productTypes.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

			}
		});

		printLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				if (LICENCENUM.equals("*****")) {
					PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
							CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printForm(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE,
							CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printFormLic(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					LICENCENO.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					LICENCENO.setText("16/21-22");
				}
				isFirstImage = !isFirstImage;
			}
		});

		URL custPhoneUrl = TextFieldWithIcon.class.getResource("/images/telephone.png");
		ImageIcon custPhoneicon = new ImageIcon(custPhoneUrl);
		Image custPhoneimage = custPhoneicon.getImage();
		Image custPhoneImage = custPhoneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custPhoneIcon = new ImageIcon(custPhoneImage);
		JPanel custPhonePanel = new JPanel(new BorderLayout());
		JLabel custPhoneLabel = new JLabel(custPhoneIcon);
		custPhonePanel.add(custPhoneLabel, BorderLayout.WEST);
		custPhonePanel.add(custPhone, BorderLayout.CENTER);
		panel.add(custPhonePanel, gbc);

		URL billUrl = TextFieldWithIcon.class.getResource("/images/bill.png");
		ImageIcon billicon = new ImageIcon(billUrl);
		Image billimage = billicon.getImage();
		Image billImage = billimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon billIcon = new ImageIcon(billImage);
		JPanel billPanel = new JPanel(new BorderLayout());
		JLabel billLabel = new JLabel(billIcon);
		billPanel.add(billLabel, BorderLayout.WEST);
		billPanel.add(billNum, BorderLayout.CENTER);
		panel.add(billPanel, gbc);

		URL adagudateUrl = TextFieldWithIcon.class.getResource("/images/adaguDate.png");
		ImageIcon adagudateicon = new ImageIcon(adagudateUrl);
		Image adagudateimage = adagudateicon.getImage();
		Image adagudateImage = adagudateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaudateIcon = new ImageIcon(adagudateImage);
		JPanel adagudatePanel = new JPanel(new BorderLayout());
		JLabel adagudateLabel = new JLabel(adaudateIcon);
		adagudatePanel.add(adagudateLabel, BorderLayout.WEST);
		adagudatePanel.add(adaguDate, BorderLayout.CENTER);
		panel.add(adagudatePanel, gbc);

		URL adaguAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguAmticon = new ImageIcon(adaguAmtUrl);
		Image adaguAmtimage = adaguAmticon.getImage();
		Image adaguAmtImage = adaguAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguAmtIcon = new ImageIcon(adaguAmtImage);
		JPanel adaguAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguAmtLabel = new JLabel(adaguAmtIcon);
		adaguAmtPanel.add(adaguAmtLabel, BorderLayout.WEST);
		adaguAmtPanel.add(adaguAmt, BorderLayout.CENTER);
		panel.add(adaguAmtPanel, gbc);

		URL custNameUrl = TextFieldWithIcon.class.getResource("/images/label.png");
		ImageIcon custNameicon = new ImageIcon(custNameUrl);
		Image custNameimage = custNameicon.getImage();
		Image custNameImage = custNameimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custNameIcon = new ImageIcon(custNameImage);
		JPanel custNamePanel = new JPanel(new BorderLayout());
		JLabel custNameLabel = new JLabel(custNameIcon);
		custNamePanel.add(custNameLabel, BorderLayout.WEST);
		custNamePanel.add(custName, BorderLayout.CENTER);
		panel.add(custNamePanel, gbc);

		heirRelation.setSelectedIndex(0);

		URL custHeirUrl = TextFieldWithIcon.class.getResource("/images/parent.png");
		ImageIcon custHeiricon = new ImageIcon(custHeirUrl);
		Image custHeirimage = custHeiricon.getImage();
		Image custHeirImage = custHeirimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custHeirIcon = new ImageIcon(custHeirImage);
		JPanel custHeirPanel = new JPanel(new BorderLayout());
		JLabel custHeirLabel = new JLabel(custHeirIcon);
		custHeirPanel.add(custHeirLabel, BorderLayout.WEST);
		custHeirPanel.add(heirRelation, BorderLayout.EAST);
		custHeirPanel.add(custHeir, BorderLayout.CENTER);
		panel.add(custHeirPanel, gbc);

		URL custAddressUrl = TextFieldWithIcon.class.getResource("/images/gps.png");
		ImageIcon custAddressicon = new ImageIcon(custAddressUrl);
		Image custAddressimage = custAddressicon.getImage();
		Image custAddressImage = custAddressimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon custAddressIcon = new ImageIcon(custAddressImage);
		JPanel custAddressPanel = new JPanel(new BorderLayout());
		JLabel custAddressLabel = new JLabel(custAddressIcon);
		custAddressPanel.add(custAddressLabel, BorderLayout.WEST);
		custAddressPanel.add(custAddress, BorderLayout.CENTER);
		panel.add(custAddressPanel, gbc);

		URL adaguActualAmtUrl = TextFieldWithIcon.class.getResource("/images/amount.png");
		ImageIcon adaguActualAmticon = new ImageIcon(adaguActualAmtUrl);
		Image adaguActualAmtimage = adaguActualAmticon.getImage();
		Image adaguActualAmtImage = adaguActualAmtimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon adaguActualAmtIcon = new ImageIcon(adaguActualAmtImage);
		JPanel adaguActualAmtPanel = new JPanel(new BorderLayout());
		JLabel adaguActualAmtLabel = new JLabel(adaguActualAmtIcon);
		adaguActualAmtPanel.add(adaguActualAmtLabel, BorderLayout.WEST);
		adaguActualAmtPanel.add(adaguActualAmt, BorderLayout.CENTER);
		panel.add(adaguActualAmtPanel, gbc);

		URL productDetailsUrl = TextFieldWithIcon.class.getResource("/images/product.png");
		ImageIcon productDetailsicon = new ImageIcon(productDetailsUrl);
		Image productDetailsimage = productDetailsicon.getImage();
		Image productDetailsImage = productDetailsimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productDetailsIcon = new ImageIcon(productDetailsImage);
		JPanel productDetailsPanel = new JPanel(new BorderLayout());
		JLabel productDetailsLabel = new JLabel(productDetailsIcon);
		productDetailsPanel.add(productDetailsLabel, BorderLayout.WEST);
		productTypes.setSelectedIndex(0);
		productDetailsPanel.add(productTypes, BorderLayout.EAST);
		productDetailsPanel.add(productDetails, BorderLayout.CENTER);
		panel.add(productDetailsPanel, gbc);

		URL scrollPaneUrl = TextFieldWithIcon.class.getResource("/images/scroll.png");
		ImageIcon scrollPaneicon = new ImageIcon(scrollPaneUrl);
		Image scrollPaneimage = scrollPaneicon.getImage();
		Image scrollPaneImage = scrollPaneimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon scrollPaneIcon = new ImageIcon(scrollPaneImage);
		JPanel scrollPanePanel = new JPanel(new BorderLayout());
		JLabel scrollPaneLabel = new JLabel(scrollPaneIcon);
		scrollPanePanel.add(scrollPaneLabel, BorderLayout.WEST);
		scrollPanePanel.add(scrollPane, BorderLayout.CENTER);
		panel.add(scrollPanePanel, gbc);

		URL productWeightUrl = TextFieldWithIcon.class.getResource("/images/weight.png");
		ImageIcon productWeighticon = new ImageIcon(productWeightUrl);
		Image productWeightimage = productWeighticon.getImage();
		Image productWeightImage = productWeightimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productWeightIcon = new ImageIcon(productWeightImage);
		JPanel productWeightPanel = new JPanel(new BorderLayout());
		JLabel productWeightLabel = new JLabel(productWeightIcon);
		productWeightPanel.add(productWeightLabel, BorderLayout.WEST);
		productWeightPanel.add(productWeight, BorderLayout.CENTER);
		panel.add(productWeightPanel, gbc);

		URL productValueUrl = TextFieldWithIcon.class.getResource("/images/value.png");
		ImageIcon productValueicon = new ImageIcon(productValueUrl);
		Image productValueimage = productValueicon.getImage();
		Image productValueImage = productValueimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon productValueIcon = new ImageIcon(productValueImage);
		JPanel productValuePanel = new JPanel(new BorderLayout());
		JLabel productValueLabel = new JLabel(productValueIcon);
		productValuePanel.add(productValueLabel, BorderLayout.WEST);
		productValuePanel.add(productValue, BorderLayout.CENTER);
		panel.add(productValuePanel, gbc);

		URL redemptionDateUrl = TextFieldWithIcon.class.getResource("/images/expiry.png");
		ImageIcon redemptionDateicon = new ImageIcon(redemptionDateUrl);
		Image redemptionDateimage = redemptionDateicon.getImage();
		Image redemptionDateImage = redemptionDateimage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon redemptionDateIcon = new ImageIcon(redemptionDateImage);
		JPanel redemptionDatePanel = new JPanel(new BorderLayout());
		JLabel redemptionDateLabel = new JLabel(redemptionDateIcon);
		redemptionDatePanel.add(redemptionDateLabel, BorderLayout.WEST);
		redemptionDatePanel.add(redemptionDate, BorderLayout.CENTER);
		panel.add(redemptionDatePanel, gbc);

		// panel.add(saveBill);
		// panel.add(printBill);
		// panel.add(notifyBill);
		// panel.add(clearBill);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));
		bottomPanel.add(clearBill);
		bottomPanel.add(saveBill);
		JPanel bottomPanel1 = new JPanel(new BorderLayout());
		bottomPanel1.add(bottomPanel, BorderLayout.CENTER);
		panel.add(bottomPanel1, gbc);

		clearBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				billNum.setEditable(true);
				billNum.setText("Bill Num");
				billNum.setForeground(new Color(157, 161, 250));
				adaguAmt.setEditable(true);
				adaguAmt.setText("Amount");
				adaguAmt.setForeground(new Color(157, 161, 250));
				custPhone.setEditable(true);
				custPhone.setText("Phone");
				custPhone.setForeground(new Color(157, 161, 250));
				custName.setEditable(true);
				custName.setText("Name");
				custName.setForeground(new Color(157, 161, 250));
				custAddress.setEditable(true);
				custAddress.setText("Address");
				custAddress.setForeground(new Color(157, 161, 250));
				custHeir.setEditable(true);
				custHeir.setText("Heir");
				custHeir.setForeground(new Color(157, 161, 250));
				heirRelation.setSelectedIndex(0);
				heirRelation.setEditable(true);
				productTypes.setSelectedIndex(0);
				productTypes.setEditable(true);
				adaguActualAmt.setEditable(true);
				adaguActualAmt.setText("Actual Amount");
				adaguActualAmt.setForeground(new Color(157, 161, 250));
				productWeight.setEditable(true);
				productWeight.setText("Weight");
				productWeight.setForeground(new Color(157, 161, 250));
				productValue.setEditable(true);
				productValue.setText("Value");
				productValue.setForeground(new Color(157, 161, 250));
				productDetails.setEditable(true);
				productDetails.setText("Products");
				productDetails.setForeground(new Color(157, 161, 250));
				selectedProductsModel.clear();
				adaguDate.setDate(new java.util.Date()); // Set default system date
			}
		});

		productDetails.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = productDetails.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				String PRODUCTTYPE = productTypes.getSelectedItem().toString();
				if (PRODUCTTYPE.equals("Gold")) {
					int i = 0;
					for (String product : productGoldList) {
						if (product.toLowerCase().startsWith(typedText) && i <= 15) {
							suggestions.add(product);
							i++;
						}
					}
				} else if (PRODUCTTYPE.equals("Silver")) {
					int j = 0;
					for (String product : productSilverList) {
						if (product.toLowerCase().startsWith(typedText) && j <= 15) {
							suggestions.add(product);
							j++;
						}
					}
				}

				// Show suggestions in a combo box if any
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									int qty = 0;
									String qtyString = JOptionPane.showInputDialog(null, "" + suggestion + " : ",
											"Quantity Input", JOptionPane.QUESTION_MESSAGE);
									if (qtyString != null && !qtyString.trim().isEmpty()) {
										try {
											int quantity = Integer.parseInt(qtyString);
											if (quantity > 0) {
												qty = quantity;
											} else {
												JOptionPane.showMessageDialog(null, "Quantity must be greater than 0.",
														"Error", JOptionPane.ERROR_MESSAGE);
												qty = 0;
											}
										} catch (NumberFormatException ex) {
											JOptionPane.showMessageDialog(null, "Please enter a valid number.",
													"Invalid Input", JOptionPane.ERROR_MESSAGE);
											qty = 0;
										}
									} else {
										JOptionPane.showMessageDialog(null, "Quantity is required.", "Error",
												JOptionPane.ERROR_MESSAGE);
									}
//									selectedProductsModel.addElement(suggestion + " - " + qty);
									//selectedProductsModel.addElement(PRODUCTTYPE + " - " + suggestion + " - " + qty); // 23July2025
									selectedProductsModel.addElement(suggestion + "-" + qty); //27Oct2025
									productDetails.setText(""); // Clear text field after selection
								}
								// Hide the popup after selection
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(productDetails, 0, productDetails.getHeight());

				}
			}

		});

		billNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		adaguAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		adaguActualAmt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		productWeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = productWeight.getText().toString().trim();
				/*
				 * if (!Character.isDigit(c) && c != '.' || (c == '.' && text.contains("."))) {
				 * e.consume(); }
				 */
				if ((!Character.isDigit(c) && c != '.' && c != '/') || (c == '/' && text.split("/+").length > 1)) {
					e.consume();
				}
			}
		});

		productValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		custName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custName.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterCustomerList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custName.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custName, 0, custName.getHeight());
				}
			}
		});

		custAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custAddress.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterAddressList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custAddress.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custAddress, 0, custAddress.getHeight());
				}
			}
		});

		custHeir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typedText = custHeir.getText().toLowerCase();
				ArrayList<String> suggestions = new ArrayList<>();
				int i = 0;
				for (String product : masterHeirList) {
					if (product.toLowerCase().startsWith(typedText) && i <= 15) {
						suggestions.add(product);
						i++;
					}
				}
				if (!suggestions.isEmpty()) {
					JPopupMenu prodPopupMenu = new JPopupMenu();
					for (String suggestion : suggestions) {
						JMenuItem menuItem = new JMenuItem(suggestion);
						menuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (!selectedProductsModel.contains(suggestion)) {
									custHeir.setText("" + suggestion);
								}
								prodPopupMenu.setVisible(false);
							}
						});
						prodPopupMenu.add(menuItem);
						prodPopupMenu.setFocusable(false);
					}
					prodPopupMenu.show(custHeir, 0, custHeir.getHeight());
				}
			}
		});

		notifyBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				StringBuilder reminderMessage = new StringBuilder();
				reminderMessage.append(" அறிவிப்பு: ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
						+ " அடகு வைத்துளிர்கள்,  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" Bill Number  : " + BILLNUM);
				reminderMessage.append(" \n ");
				reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
				reminderMessage.append(" \n ");
				reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
						+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
				reminderMessage.append(" \n ");
				reminderMessage.append(
						" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
				reminderMessage.append(" \n ");
				reminderMessage
						.append(" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நன்றி! ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
				reminderMessage.append(" \n ");
				reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
				reminderMessage.append(" \n ");

				StringBuilder displayMessage = new StringBuilder();
				displayMessage.append(" Bill Number  : " + BILLNUM);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Name : " + CUSTNAME);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Phone : " + CUSTPHONE);
				displayMessage.append(" \n");
				displayMessage.append(" Customer Address : " + CUSTADDRESS);
				displayMessage.append(" \n");
				displayMessage.append(
						" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : " + REDEMPTIONDATEFORMATTED);
				displayMessage.append(" \n");
				displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
				displayMessage.append(" \n");

				// Create JTextArea for custom popup display
				JTextArea reminderArea = new JTextArea(displayMessage.toString());
				reminderArea.setEditable(false); // Make text area non-editable
				reminderArea.setWrapStyleWord(true); // Word wrapping
				reminderArea.setLineWrap(true);
				reminderArea.setCaretPosition(0); // Set cursor at the top
				reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
				reminderArea.setPreferredSize(new Dimension(400, 200));

				int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Copy" },
						"Copy");

				if (choice == 0) {
					String message = "" + reminderMessage;
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
			}
		});

		saveBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				String PRODUCTTYPE = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;
				String STATUS = "";

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString().trim();
				BILLNUM = billNum.getText().toString().trim();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString().trim());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString().trim());
				CUSTNAME = custName.getText().toString().trim();
				CUSTADDRESS = custAddress.getText().toString().trim();
				CUSTHEIR = custHeir.getText().toString().trim();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString().trim();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString().trim());
				PRODUCTWEIGTH = productWeight.getText().toString().trim();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString().trim());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				PRODUCTTYPE = productTypes.getSelectedItem().toString().trim();
				STATUS = status.getSelectedItem().toString().trim();
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + "~");
				}

				AdaguBill adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(LICENCENUM);
				adaguData.setBILL_NUMBER(BILLNUM);
				adaguData.setCUSTOMER_NAME(CUSTNAME);
				adaguData.setCUSTOMER_PHONE("" + CUSTPHONE);
				adaguData.setHEIR_RELATION(CUSTHEIRRELATION);
				adaguData.setHEIR(CUSTHEIR);
				adaguData.setCUSTOMER_ADDRESS(CUSTADDRESS);
				adaguData.setPRODUCTS(PRODUCT.toString().trim());
				adaguData.setPRODUCT_WEIGHT("" + PRODUCTWEIGTH);
				adaguData.setADAGU_AMOUNT("" + ADAGUAMT);
				adaguData.setACTUAL_ADAGU_AMOUNT("" + ADAGUACTUALAMT);
				adaguData.setPRODUCT_VALUE("" + PRODUCTVALUE);
				adaguData.setADAGU_DATE(ADAGUDATEFORMATTED);
				adaguData.setREDEMPTION_DATE(REDEMPTIONDATEFORMATTED);
				adaguData.setPRODUCT_TYPE(PRODUCTTYPE);
				adaguData.setBILL_TYPE("Adagu Bill");
				if (STATUS.equals("Paid")) {
					adaguData.setSTATUS("Paid");
				} else {
					adaguData.setSTATUS("Pending");
				}

				DBConnect saveAdagu = new DBConnect();
				boolean isSaved = false;
				isSaved = saveAdagu.saveAdaguBill(adaguData);

				if (isSaved) {
					JOptionPane.showConfirmDialog(panel, " Bill Saved Successfully !!! ");
				} else {
					JOptionPane.showConfirmDialog(panel, " Error : Save Bill !!! ");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
			}
		});

		printBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String LICENCENUM = "";
				String BILLNUM = "";
				Date ADAGUDATE = new Date();
				BigDecimal ADAGUAMT = new BigDecimal(0);
				BigDecimal CUSTPHONE = new BigDecimal(0);
				String CUSTNAME = "";
				String CUSTADDRESS = "";
				String CUSTHEIR = "";
				String CUSTHEIRRELATION = "";
				BigDecimal ADAGUACTUALAMT = new BigDecimal(0);
				String PRODUCTWEIGTH = "";
				StringBuffer PRODUCT = new StringBuffer("");
				BigDecimal PRODUCTVALUE = new BigDecimal(0);
				Date REDEMPTION = new Date();
				String ADAGUDATEFORMATTED = "", REDEMPTIONDATEFORMATTED = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				LineBorder border = new LineBorder(Color.RED, 2); // 2px gray border
				LineBorder correctborder = new LineBorder(Color.DARK_GRAY, 1); // 2px gray border
				Boolean error = false;

				if (billNum.getText().trim().equals("") || billNum.getText().trim().equals(null)
						|| billNum.getText().trim().equals("Bill Num")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Bill Number !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguAmt.getText().trim().equals("") || adaguAmt.getText().trim().equals(null)
						|| adaguAmt.getText().trim().equals("Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Amount !!! ");
					adaguAmt.setBorder(border);
					error = true;
					return;
				}
				if (custPhone.getText().trim().equals("") || custPhone.getText().trim().equals(null)
						|| custPhone.getText().trim().equals("Phone")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Mobile Number !!! ");
					custPhone.setBorder(border);
					error = true;
					return;
				}
				if (custName.getText().trim().equals("") || custName.getText().trim().equals(null)
						|| custName.getText().trim().equals("Name")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Name !!! ");
					custName.setBorder(border);
					error = true;
					return;
				}
				if (custAddress.getText().trim().equals("") || custAddress.getText().trim().equals(null)
						|| custAddress.getText().trim().equals("Address")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Address !!! ");
					custAddress.setBorder(border);
					error = true;
					return;
				}
				if (custHeir.getText().trim().equals("") || custHeir.getText().trim().equals(null)
						|| custHeir.getText().trim().equals("Heir")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Customer Heir !!! ");
					billNum.setBorder(border);
					error = true;
					return;
				}
				if (adaguActualAmt.getText().trim().equals("") || adaguActualAmt.getText().trim().equals(null)
						|| adaguActualAmt.getText().trim().equals("Actual Amount")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Adagu Actual Amount !!! ");
					adaguActualAmt.setBorder(border);
					error = true;
					return;
				}
				if (productWeight.getText().trim().equals("") || productWeight.getText().trim().equals(null)
						|| productWeight.getText().trim().equals("Weight")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Weight !!! ");
					productWeight.setBorder(border);
					error = true;
					return;
				}
				if (productValue.getText().trim().equals("") || productValue.getText().trim().equals(null)
						|| productValue.getText().trim().equals("Value")) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Product Value !!! ");
					productValue.setBorder(border);
					error = true;
					return;
				}
				if (selectedProductsModel.getSize() <= 0) {
					JOptionPane.showConfirmDialog(panel, " Please Enter Products !!! ");
					productDetails.setBorder(border);
					error = true;
					return;
				}

				LICENCENUM = LICENCENO.getText().toString();
				BILLNUM = billNum.getText().toString();
				ADAGUDATE = adaguDate.getDate();
				ADAGUDATEFORMATTED = sdf.format(adaguDate.getDate());
				ADAGUAMT = new BigDecimal(adaguAmt.getText().toString());
				CUSTPHONE = new BigDecimal(custPhone.getText().toString());
				CUSTNAME = custName.getText().toString();
				CUSTADDRESS = custAddress.getText().toString();
				CUSTHEIR = custHeir.getText().toString();
				CUSTHEIRRELATION = heirRelation.getSelectedItem().toString();
				ADAGUACTUALAMT = new BigDecimal(adaguActualAmt.getText().toString());
				PRODUCTWEIGTH = productWeight.getText().toString();
				PRODUCTVALUE = new BigDecimal(productValue.getText().toString());
				REDEMPTION = redemptionDate.getDate();
				REDEMPTIONDATEFORMATTED = sdf.format(redemptionDate.getDate());
				for (int i = 0; i < selectedProductsModel.getSize(); i++) {
					PRODUCT.append("" + selectedProductsModel.get(i) + ",");
				}

				billNum.setEditable(false);
				billNum.setBackground(Color.LIGHT_GRAY);
				billNum.setBorder(correctborder);
				billNum.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				adaguDate.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setEditable(false);
				adaguAmt.setBackground(Color.LIGHT_GRAY);
				adaguAmt.setBorder(correctborder);
				adaguAmt.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custPhone.setEditable(false);
				custPhone.setBackground(Color.LIGHT_GRAY);
				custPhone.setBorder(correctborder);
				custPhone.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custName.setEditable(false);
				custName.setBackground(Color.LIGHT_GRAY);
				custName.setBorder(correctborder);
				custName.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custAddress.setEditable(false);
				custAddress.setBackground(Color.LIGHT_GRAY);
				custAddress.setBorder(correctborder);
				custAddress.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				custHeir.setEditable(false);
				custHeir.setBackground(Color.LIGHT_GRAY);
				custHeir.setBorder(correctborder);
				custHeir.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				heirRelation.setEditable(false);
				heirRelation.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setEditable(false);
				adaguActualAmt.setBackground(Color.LIGHT_GRAY);
				adaguActualAmt.setBorder(correctborder);
				adaguActualAmt
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productWeight.setEditable(false);
				productWeight.setBackground(Color.LIGHT_GRAY);
				productWeight.setBorder(correctborder);
				productWeight.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				productValue.setEditable(false);
				productValue.setBackground(Color.LIGHT_GRAY);
				productValue.setBorder(correctborder);
				productValue.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));
				redemptionDate.setBackground(Color.LIGHT_GRAY);
				productDetails.setEditable(false);
				productDetails.setBackground(Color.LIGHT_GRAY);
				productDetails.setBorder(correctborder);
				productDetails
						.setBorder(BorderFactory.createCompoundBorder(correctborder, new EmptyBorder(5, 5, 5, 5)));

				if (LICENCENUM.equals("*****")) {
					PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
							CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printForm(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE,
							CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
							PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
					String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
					try {
						print.printFormLic(print, printFileName);
					} catch (DocumentException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		// Set window visibility
		setVisible(true);

		return panel;
	}

	public JPanel AdmincreateCancelLedgerPanel() {
		JPanel panel = new JPanel();
		JTextField txtBillNo, txtMobileNo, txtLicenceNo, txtName;
		JButton btnSearch, btnEdit, btnDelete, btnCloseBill, btnExportPDF, btnGenerateBill, btnNotifyBill;
		JTable table;
		DefaultTableModel tableModel;
		panel.setLayout(new BorderLayout());
		// Header Panel (for search inputs)
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);
		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		txtLicenceNo = new JTextField(10);
		txtLicenceNo.setText("16/21-22");
		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				}
				isFirstImage = !isFirstImage;
			}
		});

		JLabel lblBillNo = new JLabel("Bill No:");
		txtBillNo = new JTextField(10);
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(10);

		btnSearch = new JButton("Search");

		headerPanel.add(licNoUrlLabel);
		headerPanel.add(lblBillNo);
		headerPanel.add(txtBillNo);
		headerPanel.add(lblName);
		headerPanel.add(txtName);
		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(new String[] { "PHONE", "LIC", "BILL", "NAME", "ADDRESS", "AMT",
				"ADAGU DATE", "CANCEL DATE", "STATUS" }, 0);

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());

		btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.RED);
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnCloseBill = new JButton("Close Bill");
		btnCloseBill.setForeground(Color.RED);
		btnExportPDF = new JButton("Export PDF");
		btnExportPDF.setForeground(Color.RED);
		btnGenerateBill = new JButton("Re-Print Bill");
		btnGenerateBill.setForeground(Color.RED);
		btnNotifyBill = new JButton("Re-Notify Bill");
		btnNotifyBill.setForeground(Color.RED);

		footerPanel.add(btnCloseBill);
		footerPanel.add(btnGenerateBill);
		footerPanel.add(btnNotifyBill);
		// footerPanel.add(btnExportPDF);

		panel.add(footerPanel, BorderLayout.SOUTH);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListCancelViewPanel = loadData.loadADAGUDataCancelled();
				String billNo = txtBillNo.getText().trim();
				String mobileNo = txtMobileNo.getText().trim();
				String licenseno = txtLicenceNo.getText().toString().trim();
				String Name = txtName.getText().toUpperCase().trim().toString();
				tableModel.setRowCount(0);
				// Logic to filter records based on billNo, mobileNo, status, and billType
				for (AdaguBill record : AdaguBillListCancelViewPanel) {
					boolean matchesBillNo = billNo.isEmpty() || String.valueOf(record.getBILL_NUMBER()).equals(billNo);
					boolean matchesName = Name.isEmpty() || String.valueOf(record.getCUSTOMER_NAME()).equals(Name);
					boolean matchesLicNo = licenseno.isEmpty()
							|| String.valueOf(record.getLICENCE_NUMBER().toString().trim()).equals(licenseno);
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);
					if (matchesBillNo && matchesMobileNo && matchesLicNo && matchesName) {
						tableModel.addRow(new Object[] { record.getCUSTOMER_PHONE(), record.getLICENCE_NUMBER(),
								record.getBILL_NUMBER(), record.getCUSTOMER_NAME(), record.getCUSTOMER_ADDRESS(),
								record.getADAGU_AMOUNT(), record.getADAGU_DATE(), record.getCANCEL_DATE(),
								record.getSTATUS() });
					}
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;

	}

	// Custom JPanel class to paint the background image
	class AdaguBackgroundPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Image backgroundImage;

		// Constructor to load the background image
		public AdaguBackgroundPanel() {
			// Get the image from the "images" folder inside the project
			URL imageUrl = getClass().getResource("/images/back.jpeg"); // Adjust the path to your image
			if (imageUrl != null) {
				backgroundImage = new ImageIcon(imageUrl).getImage();
			} else {
				System.out.println("Image not found!");
			}
		}

		// Override paintComponent to draw the background image
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Scale the image to fit the panel
		}
	}

	// Panel for View Adagu Bill
	public JPanel AdmincreateViewAdaguBillPanel() {
		JPanel panel = new JPanel();
		JTextField txtBillNo, txtMobileNo, txtLicenceNo, txtName;
		JButton btnSearch, btnEdit, btnDelete, btnCloseBill, btnExportPDF, btnGenerateBill, btnNotifyBill;
		JComboBox<String> cmbStatus;
		JTable table;
		DefaultTableModel tableModel;

		panel.setLayout(new BorderLayout());

		// Header Panel (for search inputs)
		JPanel headerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/header.jpeg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		headerPanel.setLayout(new FlowLayout());

		URL licNoUrl = TextFieldWithIcon.class.getResource("/images/eyeopen.png");
		ImageIcon licNoUrlicon = new ImageIcon(licNoUrl);
		Image licNoUrlimage = licNoUrlicon.getImage();
		Image licNoUrlImage = licNoUrlimage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon licNoUrlIcon = new ImageIcon(licNoUrlImage);
		URL newLicNoUrl = TextFieldWithIcon.class.getResource("/images/eyeclose.png");
		ImageIcon newLicNoIcon = new ImageIcon(newLicNoUrl);
		Image newLicNoImage = newLicNoIcon.getImage();
		Image newLicNoUrlImage = newLicNoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newLicNoUrlIcon = new ImageIcon(newLicNoUrlImage);
		JLabel licNoUrlLabel = new JLabel(licNoUrlIcon);
		txtLicenceNo = new JTextField(10);
		txtLicenceNo.setText("16/21-22");
		licNoUrlLabel.addMouseListener(new MouseAdapter() {
			private boolean isFirstImage = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isFirstImage) {
					licNoUrlLabel.setIcon(newLicNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				} else {
					licNoUrlLabel.setIcon(licNoUrlIcon);
					txtLicenceNo.setText("16/21-22");
				}
				isFirstImage = !isFirstImage;
			}
		});

		JLabel lblBillNo = new JLabel("Bill No:");
		txtBillNo = new JTextField(10);
		JLabel lblMobileNo = new JLabel("Mobile No:");
		txtMobileNo = new JTextField(10);
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(10);

		JLabel lblStatus = new JLabel("Status:");
		cmbStatus = new JComboBox<>(new String[] { "", "Paid", "Pending" });

		btnSearch = new JButton("Search");

		headerPanel.add(licNoUrlLabel);
		headerPanel.add(lblBillNo);
		headerPanel.add(txtBillNo);
		headerPanel.add(lblName);
		headerPanel.add(txtName);
		headerPanel.add(lblMobileNo);
		headerPanel.add(txtMobileNo);
		headerPanel.add(lblStatus);
		headerPanel.add(cmbStatus);
		headerPanel.add(btnSearch);

		panel.add(headerPanel, BorderLayout.NORTH);

		// Table for displaying results
		/*
		 * tableModel = new DefaultTableModel(new String[] { "LICENCE NO", "BILL NO",
		 * "NAME", "PHONE", "ADDRESS", "PRODUCTS", "WEIGHT", "AMOUNT", "DATE",
		 * "REDEMPTION", "STATUS" }, 0);
		 */

		tableModel = new DefaultTableModel(new String[] { "PHONE", "LIC", "BILL", "NAME", "ADDRESS", "PRODUCT",
				"WEIGHT", "AMT", "DATE", "R.DATE", "STATUS" }, 0);

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));
		table.getTableHeader().setForeground(new Color(58, 113, 232));
		table.setRowHeight(500, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);

		JScrollPane tableScroll = new JScrollPane(table);
		panel.add(tableScroll, BorderLayout.CENTER);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem editItem = new JMenuItem("Edit");
		popupMenu.add(editItem);
		JMenuItem calculateItem = new JMenuItem("Calculate %");
		// popupMenu.add(calculateItem);
		// Locker Detail New Fix
		JMenuItem lockerItem = new JMenuItem("Locker");
		popupMenu.add(lockerItem);

		// Payment Detail New Fix
		JMenuItem paymentItem = new JMenuItem("Pay Bill");
		popupMenu.add(paymentItem);

		JMenuItem deleteItem = new JMenuItem("Delete");
		popupMenu.add(deleteItem);
		table.setComponentPopupMenu(popupMenu);

		// Locker Detail New Fix
		lockerItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField LICENCE_NUMBER = new JTextField(selectedRecord.getLICENCE_NUMBER());
						LICENCE_NUMBER.setEditable(false);
						JTextField BILL_NUMBER = new JTextField(selectedRecord.getBILL_NUMBER());
						BILL_NUMBER.setEditable(false);
						JTextField LOCKER_NAME = new JTextField(selectedRecord.getLOCKER_NAME());
						LOCKER_NAME.setEditable(true);
						JTextField LOCKER_BILLNUM = new JTextField(selectedRecord.getLOCKER_BILLNUM());
						LOCKER_BILLNUM.setEditable(true);
						JTextField LOCKER_DATE = new JTextField(selectedRecord.getLOCKER_DATE());
						LOCKER_DATE.setEditable(true);

						Object[] message = { "LICENCE_NUMBER ", LICENCE_NUMBER, "BILL_NUMBER ", BILL_NUMBER,
								"LOCKER_NAME ", LOCKER_NAME, "LOCKER_BILLNUM ", LOCKER_BILLNUM, "LOCKER_DATE ",
								LOCKER_DATE };
						int option = JOptionPane.showConfirmDialog(null, message, "Locker Details",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							selectedRecord.setLOCKER_NAME(LOCKER_NAME.getText().toUpperCase().trim());
							selectedRecord.setLOCKER_BILLNUM(LOCKER_BILLNUM.getText().toUpperCase().trim());
							selectedRecord.setLOCKER_DATE(LOCKER_DATE.getText().toUpperCase().trim());
							DBConnect updateRecord = new DBConnect();
							boolean isUpdated = updateRecord.updateLockerData(selectedRecord);
							if (isUpdated) {
								JOptionPane.showMessageDialog(panel, "Locker Details Saved Succesfully.");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Updating Locker Details");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Loading Locker Details");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Load");
				}
			}
		});

		// Payment Detail New Fix
		paymentItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField LICENCE_NUMBER = new JTextField(selectedRecord.getLICENCE_NUMBER());
						LICENCE_NUMBER.setEditable(false);
						JTextField BILL_NUMBER = new JTextField(selectedRecord.getBILL_NUMBER());
						BILL_NUMBER.setEditable(false);
						JTextField ADAGU_AMOUNT = new JTextField(selectedRecord.getADAGU_AMOUNT());
						ADAGU_AMOUNT.setEditable(false);
						JTextField PAID_AMOUNT = new JTextField(selectedRecord.getPAID_AMOUNT());
						PAID_AMOUNT.setEditable(false);
						JTextField REMAINING_AMOUNT = new JTextField(selectedRecord.getREMAINING_AMOUNT());
						REMAINING_AMOUNT.setEditable(false);
						JTextField PAYABLE_AMOUNT = new JTextField("");

						Object[] message = { "LICENCE_NUMBER ", LICENCE_NUMBER, "BILL_NUMBER ", BILL_NUMBER,
								"ADAGU_AMOUNT ", ADAGU_AMOUNT, "PAID_AMOUNT ", PAID_AMOUNT, "REMAINING_AMOUNT ",
								REMAINING_AMOUNT, "PAYABLE_AMOUNT ", PAYABLE_AMOUNT };

						int option = JOptionPane.showConfirmDialog(null, message, "Payment Details",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							String adaguAmt = "", paidAmt = "", remainingAmt = "", payableAmt = "";
							if (ADAGU_AMOUNT.getText().toString().trim().equals("")) {
								adaguAmt = "0";
							} else {
								adaguAmt = ADAGU_AMOUNT.getText().toString().trim();
							}
							if (PAID_AMOUNT.getText().toString().trim().equals("")) {
								paidAmt = "0";
							} else {
								paidAmt = PAID_AMOUNT.getText().toString().trim();
							}
							if (REMAINING_AMOUNT.getText().toString().trim().equals("")) {
								remainingAmt = "0";
							} else {
								remainingAmt = REMAINING_AMOUNT.getText().toString().trim();
							}
							if (PAYABLE_AMOUNT.getText().toString().trim().equals("")) {
								payableAmt = "0";
							} else {
								payableAmt = PAYABLE_AMOUNT.getText().toString().trim();
							}
							double actualAmount = Double.parseDouble(adaguAmt);
							double paidAmount = Double.parseDouble(paidAmt);
							double payingAmount = Double.parseDouble(payableAmt);
							double remainingAmount = actualAmount - paidAmount - payingAmount;
							REMAINING_AMOUNT.setText(String.format("%.2f", remainingAmount));
							PAID_AMOUNT.setText(String.format("%.2f", paidAmount + payingAmount));

							selectedRecord.setPAID_AMOUNT(PAID_AMOUNT.getText().trim().toString()); // Update the table
																									// view
							selectedRecord.setREMAINING_AMOUNT(REMAINING_AMOUNT.getText().trim().toString()); // Update
																												// the
																												// table
																												// view
							DBConnect updateRecord = new DBConnect();
							boolean isUpdated = updateRecord.updatePaymentData(selectedRecord);
							if (isUpdated) {
								JOptionPane.showMessageDialog(panel, "Record Updated Successfully!");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Record Updation");
							}

						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Loading Payment Details");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Load");
				}
			}
		});

		calculateItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null && !selectedRecord.getSTATUS().equals("Paid")) {
						SwingUtilities.invokeLater(() -> {
							LoanInterestCalculator interestCalculator = new LoanInterestCalculator();
							interestCalculator.calculateInterestWithData(BILLNO);
						});
					} else {
						JOptionPane.showMessageDialog(panel, "Record Already Paid");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Calculate Interest.");
				}
			}
		});

		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						int option = JOptionPane.showConfirmDialog(null, "Sure Need To Delete This Record ?",
								"Delete Record", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.removeRow(selectedRow);
							DBConnect deleteRecord = new DBConnect();
							boolean isDeleted = deleteRecord.deleteAdaguData(selectedRecord.getBILL_NUMBER());
							if (isDeleted) {
								JOptionPane.showMessageDialog(panel, "Record Deleted Successfully!");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Record Deletion");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record Deletion");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Delete.");
				}
			}
		});

		editItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						JTextField LICENCE_NUMBER = new JTextField(selectedRecord.getLICENCE_NUMBER());
						LICENCE_NUMBER.setEditable(false);
						JTextField BILL_NUMBER = new JTextField(selectedRecord.getBILL_NUMBER());
						BILL_NUMBER.setEditable(false);
						JTextField CUSTOMER_NAME = new JTextField(selectedRecord.getCUSTOMER_NAME());
						JTextField CUSTOMER_PHONE = new JTextField(selectedRecord.getCUSTOMER_PHONE());
						JTextField CUSTOMER_ADDRESS = new JTextField(selectedRecord.getCUSTOMER_ADDRESS());
						JTextField ADAGU_AMOUNT = new JTextField(selectedRecord.getADAGU_AMOUNT());
						ADAGU_AMOUNT.setEditable(false);
						JTextField PRODUCT_WEIGHT = new JTextField(selectedRecord.getPRODUCT_WEIGHT());
						JTextField ADAGU_DATE = new JTextField(selectedRecord.getADAGU_DATE());
						JTextField REDEMPTION_DATE = new JTextField(selectedRecord.getREDEMPTION_DATE());
						JTextField STATUS = new JTextField(selectedRecord.getSTATUS());

						AdaguBill oldData = new AdaguBill();
						oldData.setCUSTOMER_NAME(selectedRecord.getCUSTOMER_NAME());
						oldData.setCUSTOMER_ADDRESS(selectedRecord.getCUSTOMER_ADDRESS());

						STATUS.setEditable(false);
						Object[] message = { "LICENCE_NUMBER ", LICENCE_NUMBER, "BILL_NUMBER :", BILL_NUMBER,
								"CUSTOMER_NAME ", CUSTOMER_NAME, "CUSTOMER_PHONE ", CUSTOMER_PHONE, "CUSTOMER_ADDRESS ",
								CUSTOMER_ADDRESS, "ADAGU_AMOUNT ", ADAGU_AMOUNT, "PRODUCT_WEIGHT", PRODUCT_WEIGHT,
								"ADAGU_DATE ", ADAGU_DATE, "REDEMPTION_DATE ", REDEMPTION_DATE, "STATUS ", STATUS };
						int option = JOptionPane.showConfirmDialog(null, message, "Edit Record",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							tableModel.setValueAt(CUSTOMER_NAME.getText(), selectedRow, 2); // Update the table view
							tableModel.setValueAt(CUSTOMER_PHONE.getText(), selectedRow, 3); // Update the table view
							tableModel.setValueAt(CUSTOMER_ADDRESS.getText(), selectedRow, 4); // Update the table view
							tableModel.setValueAt(ADAGU_DATE.getText(), selectedRow, 7); // Update the table view
							tableModel.setValueAt(REDEMPTION_DATE.getText(), selectedRow, 8); // Update the table view
							tableModel.setValueAt(PRODUCT_WEIGHT.getText(), selectedRow, 6); // Update the table view
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							for (int i = 0; i < table.getColumnCount(); i++) {
								if (table.getColumnName(i).equals("NAME")) {
									model.setValueAt(CUSTOMER_NAME.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("PHONE")) {
									model.setValueAt(CUSTOMER_PHONE.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("ADDRESS")) {
									model.setValueAt(CUSTOMER_ADDRESS.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("DATE")) {
									model.setValueAt(ADAGU_DATE.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("R.DATE")) {
									model.setValueAt(REDEMPTION_DATE.getText(), selectedRow, i);
								}
								if (table.getColumnName(i).equals("WEIGHT")) {
									model.setValueAt(PRODUCT_WEIGHT.getText(), selectedRow, i);
								}
							}

							selectedRecord.setCUSTOMER_NAME(CUSTOMER_NAME.getText()); // Update the table view
							selectedRecord.setCUSTOMER_PHONE(CUSTOMER_PHONE.getText()); // Update the table view
							selectedRecord.setCUSTOMER_ADDRESS(CUSTOMER_ADDRESS.getText()); // Update the table view
							selectedRecord.setADAGU_DATE(ADAGU_DATE.getText()); // Update the table view
							selectedRecord.setREDEMPTION_DATE(REDEMPTION_DATE.getText()); // Update the table view
							selectedRecord.setPRODUCT_WEIGHT(PRODUCT_WEIGHT.getText()); // Update the table view
							DBConnect updateRecord = new DBConnect();
							boolean isUpdated = updateRecord.updateAdaguData(selectedRecord, oldData);
							if (isUpdated) {
								JOptionPane.showMessageDialog(panel, "Record Updated Successfully!");
							} else {
								JOptionPane.showMessageDialog(panel, "Error In Record Updation");
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Error In Record Updation");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to Update");
				}
			}
		});

		// Footer Panel (for Edit/Delete buttons)
		JPanel footerPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					URL imageUrl = getClass().getResource("/images/footer.jpg"); // Adjust the path to your image
					if (imageUrl != null) {
						backgroundImage = new ImageIcon(imageUrl).getImage();
					} else {
						System.out.println("Image not found!");
					}
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		footerPanel.setLayout(new FlowLayout());

		btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.RED);
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnCloseBill = new JButton("Close Bill");
		btnCloseBill.setForeground(Color.RED);
		btnExportPDF = new JButton("Export PDF");
		btnExportPDF.setForeground(Color.RED);
		btnGenerateBill = new JButton("Re-Print Bill");
		btnGenerateBill.setForeground(Color.RED);
		btnNotifyBill = new JButton("Re-Notify Bill");
		btnNotifyBill.setForeground(Color.RED);

		footerPanel.add(btnCloseBill);
		footerPanel.add(btnGenerateBill);
		footerPanel.add(btnNotifyBill);
		// footerPanel.add(btnExportPDF);

		panel.add(footerPanel, BorderLayout.SOUTH);

		btnCloseBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;

					// Find the selected record from the list
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}

					if (selectedRecord != null && !selectedRecord.getSTATUS().equals("Paid")) {
						selectedRecord.setSTATUS("Paid");
						selectedRecord.setREDEMPTION_DATE("" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
						tableModel.setValueAt("Paid", selectedRow, 10); // Update the table view
						tableModel.setValueAt("" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()), selectedRow,
								9); // Update the table view
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						for (int i = 0; i < table.getColumnCount(); i++) {
							if (table.getColumnName(i).equals("STATUS")) {
								model.setValueAt("Paid", selectedRow, i);
							}
						}
						DBConnect paidBill = new DBConnect();
						boolean isPaid = paidBill.updatePaidStatus(selectedRecord.getBILL_NUMBER());
						if (isPaid)
							JOptionPane.showMessageDialog(panel, "Bill No: " + billNo + " is now Paid.");
						else
							JOptionPane.showMessageDialog(panel, "This bill is already Paid or not found.");
					} else {
						JOptionPane.showMessageDialog(panel, "This bill is already Paid or not found.");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please select a record to close.");
				}

			}
		});

		btnExportPDF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Document document = new Document();
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Save PDF File");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

					// Filter to show only PDF files
					fileChooser.setAcceptAllFileFilterUsed(false);
					fileChooser.addChoosableFileFilter(
							new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));

					// Show save dialog
					int result = fileChooser.showSaveDialog(panel);

					if (result == JFileChooser.APPROVE_OPTION) {
						// Get the selected file
						File file = fileChooser.getSelectedFile();

						// Ensure the file has a .pdf extension
						if (!file.getName().endsWith(".pdf")) {
							file = new File(file.getAbsolutePath() + ".pdf");
						}

						// You can now save your PDF to the selected location
						try {
							// Output stream to save the PDF
							PdfWriter.getInstance(document, new FileOutputStream(file));
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
							JOptionPane.showMessageDialog(panel,
									"PDF saved successfully to: " + file.getAbsolutePath());
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(panel, "Error saving the PDF: " + ex.getMessage());
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(panel, "Error exporting to PDF: " + e1.getMessage());
				}

			}
		});

		btnGenerateBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						String LICENCENUM = selectedRecord.getLICENCE_NUMBER();
						String BILLNUM = selectedRecord.getBILL_NUMBER();
						Date ADAGUDATE = new Date();
						BigDecimal ADAGUAMT = new BigDecimal(selectedRecord.getADAGU_AMOUNT());
						BigDecimal CUSTPHONE = new BigDecimal(selectedRecord.getCUSTOMER_PHONE());
						String CUSTNAME = selectedRecord.getCUSTOMER_NAME();
						String CUSTADDRESS = selectedRecord.getCUSTOMER_ADDRESS();
						String CUSTHEIR = selectedRecord.getHEIR();
						String CUSTHEIRRELATION = selectedRecord.getHEIR_RELATION();
						BigDecimal ADAGUACTUALAMT = new BigDecimal(selectedRecord.getACTUAL_ADAGU_AMOUNT());
						String PRODUCTWEIGTH = selectedRecord.getPRODUCT_WEIGHT().toString();
						StringBuffer PRODUCT = new StringBuffer(selectedRecord.getPRODUCTS());
						BigDecimal PRODUCTVALUE = new BigDecimal(selectedRecord.getPRODUCT_VALUE());
						Date REDEMPTION = new Date();
						String ADAGUDATEFORMATTED = selectedRecord.getADAGU_DATE(),
								REDEMPTIONDATEFORMATTED = selectedRecord.getREDEMPTION_DATE();
						if (selectedRecord.getLICENCE_NUMBER().equals("*****")) {
							PrintBill print = new PrintBill(BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT, CUSTPHONE, CUSTNAME,
									CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT, PRODUCTWEIGTH, PRODUCT,
									PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
							String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
							try {
								print.printForm(print, printFileName);
							} catch (DocumentException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							PrintBill print = new PrintBill(LICENCENUM, BILLNUM, ADAGUDATEFORMATTED, ADAGUAMT,
									CUSTPHONE, CUSTNAME, CUSTADDRESS, CUSTHEIR, CUSTHEIRRELATION, ADAGUACTUALAMT,
									PRODUCTWEIGTH, PRODUCT, PRODUCTVALUE, REDEMPTIONDATEFORMATTED);
							String printFileName = "" + CUSTPHONE + "_" + BILLNUM + ".pdf";
							try {
								print.printFormLic(print, printFileName);
							} catch (DocumentException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Bill Not Found.");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please Select Record To Generate.");
				}

			}
		});

		btnNotifyBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					String BILLNO = tableModel.getValueAt(selectedRow, 2).toString(); // CHANGE ORDER BILLNUM
					BigDecimal billNo = new BigDecimal(BILLNO);
					AdaguBill selectedRecord = null;
					for (AdaguBill record : AdaguBillListViewPanel) {
						if (new BigDecimal(record.getBILL_NUMBER()).compareTo(billNo) == 0) {
							selectedRecord = record;
							break;
						}
					}
					if (selectedRecord != null) {
						String LICENCENUM = selectedRecord.getLICENCE_NUMBER();
						String BILLNUM = selectedRecord.getBILL_NUMBER();
						Date ADAGUDATE = new Date();
						BigDecimal ADAGUAMT = new BigDecimal(selectedRecord.getADAGU_AMOUNT());
						BigDecimal CUSTPHONE = new BigDecimal(selectedRecord.getCUSTOMER_PHONE());
						String CUSTNAME = selectedRecord.getCUSTOMER_NAME();
						String CUSTADDRESS = selectedRecord.getCUSTOMER_ADDRESS();
						String CUSTHEIR = selectedRecord.getHEIR();
						String CUSTHEIRRELATION = selectedRecord.getHEIR_RELATION();
						BigDecimal ADAGUACTUALAMT = new BigDecimal(selectedRecord.getACTUAL_ADAGU_AMOUNT());
						BigDecimal PRODUCTWEIGTH = new BigDecimal(selectedRecord.getPRODUCT_WEIGHT());
						StringBuffer PRODUCT = new StringBuffer(selectedRecord.getPRODUCTS());
						BigDecimal PRODUCTVALUE = new BigDecimal(selectedRecord.getPRODUCT_VALUE());
						Date REDEMPTION = new Date();
						String ADAGUDATEFORMATTED = selectedRecord.getADAGU_DATE(),
								REDEMPTIONDATEFORMATTED = selectedRecord.getREDEMPTION_DATE();

						StringBuilder reminderMessage = new StringBuilder();
						reminderMessage.append(" அறிவிப்பு: ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" மிகவும் முக்கியமான நினைவூட்டல்! ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நீங்கள் ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் " + PRODUCT.toString()
								+ " அடகு வைத்துளிர்கள்,  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" Bill Number  : " + BILLNUM);
						reminderMessage.append(" \n ");
						reminderMessage.append(" Name  : " + CUSTNAME + " ~ " + CUSTPHONE);
						reminderMessage.append(" \n ");
						reminderMessage.append(" மற்றும் இந்தப் பொருளுக்கான பணம் நீங்கள் செலுத்தியதை அடுத்து, ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" உங்கள் பொருட்கலை மீட்டுக் கொள்ள வேண்டும்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" இதை செய்யும் போது, தயவுசெய்து நிச்சயமாக " + REDEMPTIONDATEFORMATTED
								+ " க்குள் உங்கள் பொருட்கலை எடுத்து கொள்ள வேண்டும் என்பதைக் கவனத்தில் வைக்கவும். ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நாங்கள் உங்கள் நம்பிக்கையை மதித்து, சிறந்த சேவையை வழங்குகிறோம்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" இந்த தேதி மற்றும் நேரத்திற்குள் பொருட்கலை மீட்டுக் கொள்ளாத பட்சத்தில், பொருட்கலை மீட்டுக் கொள்வதில் உங்களுக்கு சிக்கல்கள் ஏற்படக்கூடும்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" உங்கள் பொருட்கலை மீட்டுக் கொள்வதற்கு முன் எங்களுக்கு எதுவும் தேவைப்படுகிறதா என்பதைச் சரிபார்க்க, தயவுசெய்து எங்களுடன் விரைந்து தொடர்பு கொள்ளவும். ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை இல் உங்கள் செலுத்திய பணத்தை முழுவதும் மதிக்கின்றோம், மற்றும் உங்கள் பாரம்பரியத்தை பாதுகாக்க எங்களுக்கு இது மிகவும் முக்கியம்.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(
								" உங்கள் தங்கம் உறுதிப்படுத்தப்பட்ட நாளில், உங்கள் பொருட்கலை மீட்டுக் கொள்ளுங்கள்! ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நன்றி! ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" ஸ்ரீ லலித் பிரசன்னா நகை அடகு கடை  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" உரிமை : S.M.மணிமாறன் , Ph.No : 9894885245 ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" No 14, தருமராஜா கோவில் தெரு, திருப்பத்தூர் - 635601.  ");
						reminderMessage.append(" \n ");
						reminderMessage.append(" நேரம் :  " + ADAGUDATEFORMATTED);
						reminderMessage.append(" \n ");

						StringBuilder displayMessage = new StringBuilder();
						displayMessage.append(" Bill Number  : " + BILLNUM);
						displayMessage.append(" \n");
						displayMessage.append(" Customer Name : " + CUSTNAME);
						displayMessage.append(" \n");
						displayMessage.append(" Customer Phone : " + CUSTPHONE);
						displayMessage.append(" \n");
						displayMessage.append(" Customer Address : " + CUSTADDRESS);
						displayMessage.append(" \n");
						displayMessage.append(" Adagu Date : " + ADAGUDATEFORMATTED + " ~ Redemption Date : "
								+ REDEMPTIONDATEFORMATTED);
						displayMessage.append(" \n");
						displayMessage.append(" Products : " + PRODUCT + " = " + PRODUCTWEIGTH + "g");
						displayMessage.append(" \n");

						// Create JTextArea for custom popup display
						JTextArea reminderArea = new JTextArea(displayMessage.toString());
						reminderArea.setEditable(false); // Make text area non-editable
						reminderArea.setWrapStyleWord(true); // Word wrapping
						reminderArea.setLineWrap(true);
						reminderArea.setCaretPosition(0); // Set cursor at the top
						reminderArea.setFont(new Font("LATHA", Font.PLAIN, 14));
						reminderArea.setPreferredSize(new Dimension(400, 200));

						int choice = JOptionPane.showOptionDialog(null, new JScrollPane(reminderArea), "Notification",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
								new Object[] { "Copy" }, "Copy");

						if (choice == 0) {
							String message = "" + reminderMessage;
							StringSelection stringSelection = new StringSelection(message);
							Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
							clipboard.setContents(stringSelection, null);
							JOptionPane.showMessageDialog(panel, "Message copied to clipboard!");
						}
					} else {
						JOptionPane.showMessageDialog(panel, "Bill Not Found.");
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Please Select Record To Generate.");
				}
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnect loadData = new DBConnect();
				AdaguBillListViewPanel = loadData.loadADAGUData();
				String billNo = txtBillNo.getText().trim();
				String mobileNo = txtMobileNo.getText().trim();
				String status = cmbStatus.getSelectedItem().toString();
				String licenseno = txtLicenceNo.getText().toString().trim();
				String Name = txtName.getText().toUpperCase().trim().toString();

				// Clear existing data in the table
				tableModel.setRowCount(0);

				// Logic to filter records based on billNo, mobileNo, status, and billType
				for (AdaguBill record : AdaguBillListViewPanel) {
					// Filter based on Bill No (if not empty)
					boolean matchesBillNo = billNo.isEmpty() || String.valueOf(record.getBILL_NUMBER()).equals(billNo);

					boolean matchesName = Name.isEmpty() || String.valueOf(record.getCUSTOMER_NAME()).equals(Name);

					boolean matchesLicNo = licenseno.isEmpty()
							|| String.valueOf(record.getLICENCE_NUMBER().toString().trim()).equals(licenseno);

					// Filter based on Mobile No (if not empty)
					boolean matchesMobileNo = mobileNo.isEmpty() || record.getCUSTOMER_PHONE().equals(mobileNo);

					// Filter based on Status (if not "All")
					boolean matchesStatus = status.equals("") || record.getSTATUS().equals(status);

					// Filter based on Bill Type (if not "All")

					// Only add record if all conditions are met
					if (matchesBillNo && matchesMobileNo && matchesStatus && matchesLicNo && matchesName) {
						/*
						 * tableModel.addRow(new Object[] { record.getLICENCE_NUMBER(),
						 * record.getBILL_NUMBER(), record.getCUSTOMER_NAME(),
						 * record.getCUSTOMER_PHONE(), record.getCUSTOMER_ADDRESS(),
						 * record.getPRODUCTS(), record.getPRODUCT_WEIGHT(), record.getADAGU_AMOUNT(),
						 * record.getADAGU_DATE(), record.getREDEMPTION_DATE(), record.getSTATUS() });
						 */ // CHANGE ORDER
						tableModel.addRow(new Object[] { record.getCUSTOMER_PHONE(), record.getLICENCE_NUMBER(),
								record.getBILL_NUMBER(), record.getCUSTOMER_NAME(), record.getCUSTOMER_ADDRESS(),
								record.getPRODUCTS(), record.getPRODUCT_WEIGHT(), record.getADAGU_AMOUNT(),
								record.getADAGU_DATE(), record.getREDEMPTION_DATE(), record.getSTATUS() });
					}
				}
			}
		});

		// Set window properties
		// setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		return panel;
	}

	// Panel for Add Sales Bill

	// Panel for General Ledger Report
	private JPanel AdmincreateSynchToMobilePanel() {

		JPanel panel;
		JTextField fromEmail, toEmail, cc, subject, message, fileName, passWord;
		JLabel shopName;
		JButton sendEmail;
		JDateChooser systemDate;
		setTitle("Sri Lalith Prasanna Jewellery & Pawn Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Font inputFont = new Font("Arial", Font.BOLD, 15);
		Font labelFont = new Font("Tahoma", Font.BOLD | Font.ITALIC, 24);
		shopName = new JLabel(" Sri Lalith Prasanna Jewellery - Synch To Mobile ");
		shopName.setFont(labelFont);
		shopName.setAlignmentY(CENTER_ALIGNMENT);
		shopName.setAlignmentX(CENTER_ALIGNMENT);
		systemDate = new JDateChooser();
		systemDate.setDateFormatString("dd-MM-yyyy"); // Set format for display
		systemDate.setDate(new java.util.Date()); // Set default system date
		systemDate.setForeground(new Color(247, 25, 92)); // Change text color when typing
		systemDate.setFont(inputFont);

		fromEmail = new JTextField();
		toEmail = new JTextField();
		cc = new JTextField();
		subject = new JTextField();
		message = new JTextField();
		fileName = new JTextField();
		passWord = new JPasswordField();

		fromEmail.setFont(inputFont);
		toEmail.setFont(inputFont);
		cc.setFont(inputFont);
		subject.setFont(inputFont);
		message.setFont(inputFont);
		fileName.setFont(inputFont);
		passWord.setFont(inputFont);

		fromEmail.setText("thineshraajan8@gmail.com");
		toEmail.setText("");
		cc.setText("");
		subject.setText(" Sri Lalith Prasanna Jewellery & Pawn Shop ");
		message.setText(" Backup : " + systemDate.getDate() + " \n Kindly Refer Below Attachment - CSV ");
		toEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				DBConnect loadData = new DBConnect();
				String csvLocation = loadData.loadADAGUDataAndWriteCSV();
				fileName.setText(csvLocation);
			}
		});

		passWord.setText("xsvq vomq lptf rngx");
		fromEmail.setEditable(false);
		toEmail.setEditable(true);
		cc.setEditable(true);
		subject.setEditable(false);
		message.setEditable(false);
		fileName.setEditable(false);
		passWord.setEditable(false);

		sendEmail = new JButton("Email");
		sendEmail.setBackground(new Color(56, 142, 60)); // Green

		panel = new JPanel();
		panel.setLayout(new GridLayout(18, 10, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTH;

		JPanel shopNamePanel = new JPanel(new BorderLayout());
		shopNamePanel.add(shopName, BorderLayout.CENTER);
		panel.add(shopNamePanel, gbc);

		JPanel fromEmailPanel = new JPanel(new BorderLayout());
		fromEmailPanel.add(fromEmail, BorderLayout.CENTER);
		panel.add(fromEmailPanel, gbc);

		JPanel toEmailPanel = new JPanel(new BorderLayout());
		toEmailPanel.add(toEmail, BorderLayout.CENTER);
		panel.add(toEmailPanel, gbc);

		JPanel ccPanel = new JPanel(new BorderLayout());
		ccPanel.add(cc, BorderLayout.CENTER);
		panel.add(ccPanel, gbc);

		JPanel subjectPanel = new JPanel(new BorderLayout());
		subjectPanel.add(subject, BorderLayout.CENTER);
		panel.add(subjectPanel, gbc);

		JPanel messagePanel = new JPanel(new BorderLayout());
		messagePanel.add(message, BorderLayout.CENTER);
		panel.add(messagePanel, gbc);

		JPanel filanamePanel = new JPanel(new BorderLayout());
		filanamePanel.add(fileName, BorderLayout.CENTER);
		panel.add(filanamePanel, gbc);

		JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.add(passWord, BorderLayout.CENTER);
		panel.add(passwordPanel, gbc);

		panel.add(sendEmail, gbc);

		// Set window visibility
		setVisible(true);

		return panel;

	}

}

class CustomTableCellRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value == null ? "" : value.toString());

		// Default font size
		int fontSize = 12;

		// Increase font size for specific columns (for example, column 0 and 1)
		if (column == 0 || column == 1) {
			fontSize = 12; // Increase font size for column 0 (SNO) and column 1 (LICENCE_NUMBER)
		} else if (column == 2) {
			fontSize = 12;

		}

		// Apply the font size
		setFont(new Font("Serif", Font.PLAIN, fontSize));

		// Set background and foreground color (optional)
		if (isSelected) {
			setBackground(new Color(121, 165, 237));
			setForeground(new Color(232, 31, 5));
		} else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
		}
		setBorder(new EmptyBorder(10, 10, 10, 10));
		return this;
	}
}

class JTextFieldWithPlaceholder extends JTextField {
	private static final long serialVersionUID = 1L;
	private String placeholder;

	public JTextFieldWithPlaceholder(String placeholder) {
		super();
		this.placeholder = placeholder;

		// Set the initial placeholder text
		setText(placeholder);

		// Set the foreground color to a light grey for the placeholder
		setForeground(new Color(157, 161, 250));

		// Add focus listeners to clear the placeholder text when the field is focused
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(placeholder)) {
					setText("");
					setForeground(new Color(247, 25, 92)); // Change text color when typing
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().isEmpty()) {
					setText(placeholder);
					setForeground(Color.LIGHT_GRAY); // Change color back when placeholder is shown
				}
			}
		});

	}
}