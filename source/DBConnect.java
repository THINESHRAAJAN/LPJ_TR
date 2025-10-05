package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class DBConnect {
	//For LPJ Prod
	private static final String URL = "jdbc:mysql://localhost:3306/LPJ";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	//For Thinesh Testing
	/*private static final String URL = "jdbc:mysql://ThineshR08.infinityfreeapp.com:3306/if0_39748281_lpj?useSSL=true";
	private static final String USER = "if0_39748281";
	private static final String PASSWORD = "TanVxljE5RB";*/
	
	private Connection connection;

	public DBConnect() {

	}

	public DBConnect(String Connection) {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Database connected successfully.");
		} catch (SQLException e) {
			System.out.println("Error while connecting to database: " + e.getMessage());
		}
	}

	// Close the database connection
	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Database connection closed.");
			}
		} catch (SQLException e) {
			System.out.println("Error while closing connection: " + e.getMessage());
		}
	}

	public boolean authenticateUser(String UName, String UPass) {
		boolean isValidLogin = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int AD_User_ID = 0;

		String query = "SELECT AD_User_ID FROM AD_USER WHERE isActive = 'Y' AND UserName = ? AND Password = ?";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, UName);
			pstmt.setString(2, UPass);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				AD_User_ID = rs.getInt("AD_User_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (AD_User_ID > 0) {
			isValidLogin = true;
		}

		return isValidLogin;
	}

	public boolean saveAdaguBill(AdaguBill adaguData) {
		boolean isSaved = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "INSERT INTO AD_ADAGUDATA (LICENCE_NO, BILL_NO, CUSTOMER_NAME, CUSTOMER_PHONE, HEIR, HEIR_RELATION, "
				+ "CUSTOMER_ADDRESS, PRODUCT, PRODUCT_TYPE, PRODUCT_WEIGHT, ADAGU_AMOUNT, ADAGU_ACTUAL_AMOUNT, PRODUCT_VALUE, ADAGU_DATE, "
				+ "REDEMPTION_DATE, CANCEL_DATE, BILL_TYPE, STATUS, LOCKER_NAME, LOCKER_BILLNUM, LOCKER_DATE"
				+ " , BALANCE_BILLNUM, BALANCE_PRODUCT, BALANCE_DATE ) VALUES " // Locker Detail New Fix
				+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, adaguData.getLICENCE_NUMBER().toUpperCase().trim().toString());
			pstmt.setString(2, adaguData.getBILL_NUMBER().toUpperCase().trim().toString());
			pstmt.setString(3, adaguData.getCUSTOMER_NAME().toUpperCase().trim().toString());
			pstmt.setString(4, adaguData.getCUSTOMER_PHONE().toUpperCase().trim().toString());
			pstmt.setString(5, adaguData.getHEIR().toUpperCase().trim().toString());
			pstmt.setString(6, adaguData.getHEIR_RELATION().toUpperCase().trim().toString());
			pstmt.setString(7, adaguData.getCUSTOMER_ADDRESS().toUpperCase().trim().toString());
			pstmt.setString(8, adaguData.getPRODUCTS().toUpperCase().trim().toString());
			pstmt.setString(9, adaguData.getPRODUCT_TYPE().toUpperCase().trim().toString());
			pstmt.setString(10, adaguData.getPRODUCT_WEIGHT().toUpperCase().trim().toString());
			pstmt.setString(11, adaguData.getADAGU_AMOUNT().toUpperCase().trim().toString());
			pstmt.setString(12, adaguData.getACTUAL_ADAGU_AMOUNT().toUpperCase().trim().toString());
			pstmt.setString(13, adaguData.getPRODUCT_VALUE().toUpperCase().trim().toString());
			pstmt.setString(14, adaguData.getADAGU_DATE().toUpperCase().trim().toString());
			pstmt.setString(15, adaguData.getREDEMPTION_DATE().toUpperCase().trim().toString());
			pstmt.setString(16, adaguData.getCANCEL_DATE().toUpperCase().trim().toString());
			pstmt.setString(17, adaguData.getBILL_TYPE().toUpperCase().trim().toString());
			pstmt.setString(18, adaguData.getSTATUS().toUpperCase().trim().toString());

			// Locker Detail New Fix
			pstmt.setString(19, adaguData.getLOCKER_NAME().toUpperCase().trim().toString());
			pstmt.setString(20, adaguData.getLOCKER_BILLNUM().toUpperCase().trim().toString());
			pstmt.setString(21, adaguData.getLOCKER_DATE().toUpperCase().trim().toString());

			// Balance Detail New Fix
			pstmt.setString(22, adaguData.getBALANCE_BILLNUM().toUpperCase().trim().toString());
			pstmt.setString(23, adaguData.getBALANCE_PRODUCT().toUpperCase().trim().toString());
			pstmt.setString(24, adaguData.getBALANCE_DATE().toUpperCase().trim().toString());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				isSaved = true;
			}

			// 29Apr2025
			if (adaguData.getCUSTOMER_PHONE().toUpperCase().trim().toString().length() >= 10) {
				updateOldRecords(adaguData.getCUSTOMER_PHONE().toUpperCase().trim().toString(),
						adaguData.getCUSTOMER_NAME().toUpperCase().trim().toString(),
						adaguData.getCUSTOMER_ADDRESS().toUpperCase().trim().toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isSaved;
	}

	//29Apr2025
	private void updateOldRecords(String phone, String name, String address) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String queryName = " UPDATE AD_ADAGUDATA SET CUSTOMER_NAME = ? WHERE CUSTOMER_PHONE = ? AND UPPER(CUSTOMER_NAME) <> UPPER('"
				+ name + "')";
		String queryAddress = " UPDATE AD_ADAGUDATA SET CUSTOMER_ADDRESS = ? WHERE CUSTOMER_PHONE = ? AND UPPER(CUSTOMER_ADDRESS) <> UPPER('"
				+ address + "')";
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(queryName);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			int rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(queryAddress);
			pstmt.setString(1, address);
			pstmt.setString(2, phone);
			int rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	//29Apr2025

	public ArrayList<AdaguBill> loadADAGUData() {
		ArrayList<AdaguBill> AdaguBillList = new ArrayList<AdaguBill>();
		AdaguBill adaguData = new AdaguBill();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT LICENCE_NO, BILL_NO, CUSTOMER_NAME, CUSTOMER_PHONE, HEIR, HEIR_RELATION, CUSTOMER_ADDRESS, "
				+ "PRODUCT, PRODUCT_WEIGHT, ADAGU_AMOUNT, ADAGU_ACTUAL_AMOUNT, PRODUCT_VALUE, ADAGU_DATE, REDEMPTION_DATE, "
				+ "CANCEL_DATE, BILL_TYPE, STATUS, LOCKER_NAME, LOCKER_BILLNUM, LOCKER_DATE, PAID_AMOUNT, REMAINING_AMOUNT"
				+ ", BALANCE_BILLNUM, BALANCE_PRODUCT, BALANCE_DATE, PRODUCT_TYPE FROM AD_ADAGUDATA WHERE ISACTIVE='Y' ORDER BY BILL_NO "; //08Aug2025

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(rs.getString("LICENCE_NO"));
				adaguData.setBILL_NUMBER(rs.getString("BILL_NO"));
				adaguData.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
				adaguData.setCUSTOMER_PHONE(rs.getString("CUSTOMER_PHONE"));
				adaguData.setHEIR_RELATION(rs.getString("HEIR_RELATION"));
				adaguData.setHEIR(rs.getString("HEIR"));
				adaguData.setCUSTOMER_ADDRESS(rs.getString("CUSTOMER_ADDRESS"));
				adaguData.setPRODUCTS(rs.getString("PRODUCT"));
				adaguData.setPRODUCT_WEIGHT(rs.getString("PRODUCT_WEIGHT"));
				adaguData.setADAGU_AMOUNT(rs.getString("ADAGU_AMOUNT"));
				adaguData.setACTUAL_ADAGU_AMOUNT(rs.getString("ADAGU_ACTUAL_AMOUNT"));
				adaguData.setPRODUCT_VALUE(rs.getString("PRODUCT_VALUE"));
				adaguData.setADAGU_DATE(rs.getString("ADAGU_DATE"));
				adaguData.setREDEMPTION_DATE(rs.getString("REDEMPTION_DATE"));
				adaguData.setCANCEL_DATE(rs.getString("CANCEL_DATE"));
				adaguData.setBILL_TYPE(rs.getString("BILL_TYPE"));
				adaguData.setSTATUS(rs.getString("STATUS"));
				adaguData.setLOCKER_NAME(rs.getString("LOCKER_NAME"));
				adaguData.setLOCKER_BILLNUM(rs.getString("LOCKER_BILLNUM"));
				adaguData.setLOCKER_DATE(rs.getString("LOCKER_DATE"));
				adaguData.setPAID_AMOUNT(rs.getString("PAID_AMOUNT"));
				adaguData.setREMAINING_AMOUNT(rs.getString("REMAINING_AMOUNT"));
				adaguData.setBALANCE_BILLNUM(rs.getString("BALANCE_BILLNUM"));
				adaguData.setBALANCE_PRODUCT(rs.getString("BALANCE_PRODUCT"));
				adaguData.setBALANCE_DATE(rs.getString("BALANCE_DATE"));
				adaguData.setPRODUCT_TYPE(rs.getString("PRODUCT_TYPE"));
				AdaguBillList.add(adaguData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return AdaguBillList;
	}

	public ArrayList<SalesBill> loadSALESData() {
		ArrayList<SalesBill> AdaguBillList = new ArrayList<SalesBill>();
		SalesBill salesData = new SalesBill();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT  AD_SALESDATA_ID, CUSTOMER_PHONE, SALES_DATE, CUSTOME_NAME, CUSTOMER_ADDRESS, PRODUCT, PRODUCT_TYPE, "
				+ "PRODUCT_WEIGHT, SALES_AMOUNT, BALANCE_DESC, BALANCE_AMOUNT FROM AD_SALESDATA WHERE ISACTIVE='Y' "
				+ "ORDER BY AD_SALESDATA_ID "; //08Aug2025

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				salesData = new SalesBill();
				salesData.setAD_SALESDATA_ID(rs.getInt("AD_SALESDATA_ID")); //08Aug2025
				salesData.setCUSTOMER_PHONE(rs.getString("CUSTOMER_PHONE"));
				salesData.setSALES_DATE(rs.getString("SALES_DATE"));
				salesData.setCUSTOMER_NAME(rs.getString("CUSTOME_NAME"));
				salesData.setCUSTOMER_ADDRESS(rs.getString("CUSTOMER_ADDRESS"));
				salesData.setPRODUCT_TYPE(rs.getString("PRODUCT_TYPE"));
				salesData.setPRODUCTS(rs.getString("PRODUCT"));
				salesData.setPRODUCT_WEIGHT(rs.getString("PRODUCT_WEIGHT"));
				salesData.setSALES_AMOUNT(rs.getString("SALES_AMOUNT"));
				salesData.setBALANCE_DESCRIPTION(rs.getString("BALANCE_DESC"));
				salesData.setBALANCE_AMOUNT(rs.getString("BALANCE_AMOUNT"));
				
				AdaguBillList.add(salesData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return AdaguBillList;
	}
	
	public ArrayList<SalesBill> loadAllDetailsData() {
		ArrayList<SalesBill> AdaguBillList = new ArrayList<SalesBill>();
		SalesBill salesData = new SalesBill();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT  'ADAGU' AS BILLTYPE, BILL_NO, CUSTOMER_NAME AS CUSTOME_NAME, ADAGU_AMOUNT AS SALES_AMOUNT, ADAGU_DATE AS SALES_DATE, STATUS, REMAINING_AMOUNT AS BALANCE_AMOUNT, CUSTOMER_PHONE FROM AD_ADAGUDATA UNION ALL "
				+ "SELECT  'SALES' AS BILLTYPE, '' AS BILL_NO, CUSTOME_NAME, SALES_AMOUNT, SALES_DATE, '' AS STATUS, BALANCE_AMOUNT, CUSTOMER_PHONE FROM AD_SALESDATA ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				salesData = new SalesBill();
				salesData.setBILL_TYPE(rs.getString("BILLTYPE"));
				salesData.setBILL_NO(rs.getString("BILL_NO"));
				salesData.setCUSTOMER_NAME(rs.getString("CUSTOME_NAME"));
				salesData.setSALES_AMOUNT(rs.getString("SALES_AMOUNT"));
				salesData.setSALES_DATE(rs.getString("SALES_DATE"));
				salesData.setSTATUS(rs.getString("STATUS"));
				salesData.setBALANCE_AMOUNT(rs.getString("BALANCE_AMOUNT"));
				salesData.setCUSTOMER_PHONE(rs.getString("CUSTOMER_PHONE"));
				AdaguBillList.add(salesData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return AdaguBillList;
	}

	public ArrayList<AdaguBill> loadADAGUDataCancelled() {
		ArrayList<AdaguBill> AdaguBillList = new ArrayList<AdaguBill>();
		AdaguBill adaguData = new AdaguBill();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT LICENCE_NO, BILL_NO, CUSTOMER_NAME, CUSTOMER_PHONE, HEIR, HEIR_RELATION, CUSTOMER_ADDRESS, "
				+ "PRODUCT, PRODUCT_WEIGHT, ADAGU_AMOUNT, ADAGU_ACTUAL_AMOUNT, PRODUCT_VALUE, ADAGU_DATE, REDEMPTION_DATE, "
				+ "CANCEL_DATE, BILL_TYPE, STATUS, LOCKER_NAME, LOCKER_BILLNUM, LOCKER_DATE, PAID_AMOUNT, REMAINING_AMOUNT FROM AD_ADAGUDATA WHERE ISACTIVE='Y' AND LENGTH(CANCEL_DATE) > 0 ORDER BY CANCEL_DATE "; //06Aug2025

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(rs.getString("LICENCE_NO"));
				adaguData.setBILL_NUMBER(rs.getString("BILL_NO"));
				adaguData.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
				adaguData.setCUSTOMER_PHONE(rs.getString("CUSTOMER_PHONE"));
				adaguData.setHEIR_RELATION(rs.getString("HEIR_RELATION"));
				adaguData.setHEIR(rs.getString("HEIR"));
				adaguData.setCUSTOMER_ADDRESS(rs.getString("CUSTOMER_ADDRESS"));
				adaguData.setPRODUCTS(rs.getString("PRODUCT"));
				adaguData.setPRODUCT_WEIGHT(rs.getString("PRODUCT_WEIGHT"));
				adaguData.setADAGU_AMOUNT(rs.getString("ADAGU_AMOUNT"));
				adaguData.setACTUAL_ADAGU_AMOUNT(rs.getString("ADAGU_ACTUAL_AMOUNT"));
				adaguData.setPRODUCT_VALUE(rs.getString("PRODUCT_VALUE"));
				adaguData.setADAGU_DATE(rs.getString("ADAGU_DATE"));
				adaguData.setREDEMPTION_DATE(rs.getString("REDEMPTION_DATE"));
				adaguData.setCANCEL_DATE(rs.getString("CANCEL_DATE"));
				adaguData.setBILL_TYPE(rs.getString("BILL_TYPE"));
				adaguData.setSTATUS(rs.getString("STATUS"));
				adaguData.setLOCKER_NAME(rs.getString("LOCKER_NAME"));
				adaguData.setLOCKER_BILLNUM(rs.getString("LOCKER_BILLNUM"));
				adaguData.setLOCKER_DATE(rs.getString("LOCKER_DATE"));
				adaguData.setPAID_AMOUNT(rs.getString("PAID_AMOUNT"));
				adaguData.setREMAINING_AMOUNT(rs.getString("REMAINING_AMOUNT"));
				AdaguBillList.add(adaguData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return AdaguBillList;
	}

	public ArrayList<AdaguBill> loadADAGUDataUnpaid() {
		ArrayList<AdaguBill> AdaguBillList = new ArrayList<AdaguBill>();
		AdaguBill adaguData = new AdaguBill();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT LICENCE_NO, BILL_NO, CUSTOMER_NAME, CUSTOMER_PHONE, HEIR, HEIR_RELATION, CUSTOMER_ADDRESS, "
				+ "PRODUCT, PRODUCT_WEIGHT, ADAGU_AMOUNT, ADAGU_ACTUAL_AMOUNT, PRODUCT_VALUE, ADAGU_DATE, REDEMPTION_DATE, "
				+ "CANCEL_DATE, BILL_TYPE, STATUS, LOCKER_NAME, LOCKER_BILLNUM, LOCKER_DATE, PAID_AMOUNT, REMAINING_AMOUNT FROM AD_ADAGUDATA WHERE ISACTIVE='Y' AND (LENGTH(REMAINING_AMOUNT) > 0 OR LENGTH(STATUS) < 5 )ORDER BY BILL_NO ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(rs.getString("LICENCE_NO"));
				adaguData.setBILL_NUMBER(rs.getString("BILL_NO"));
				adaguData.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
				adaguData.setCUSTOMER_PHONE(rs.getString("CUSTOMER_PHONE"));
				adaguData.setHEIR_RELATION(rs.getString("HEIR_RELATION"));
				adaguData.setHEIR(rs.getString("HEIR"));
				adaguData.setCUSTOMER_ADDRESS(rs.getString("CUSTOMER_ADDRESS"));
				adaguData.setPRODUCTS(rs.getString("PRODUCT"));
				adaguData.setPRODUCT_WEIGHT(rs.getString("PRODUCT_WEIGHT"));
				adaguData.setADAGU_AMOUNT(rs.getString("ADAGU_AMOUNT"));
				adaguData.setACTUAL_ADAGU_AMOUNT(rs.getString("ADAGU_ACTUAL_AMOUNT"));
				adaguData.setPRODUCT_VALUE(rs.getString("PRODUCT_VALUE"));
				adaguData.setADAGU_DATE(rs.getString("ADAGU_DATE"));
				adaguData.setREDEMPTION_DATE(rs.getString("REDEMPTION_DATE"));
				adaguData.setCANCEL_DATE(rs.getString("CANCEL_DATE"));
				adaguData.setBILL_TYPE(rs.getString("BILL_TYPE"));
				adaguData.setSTATUS(rs.getString("STATUS"));
				adaguData.setLOCKER_NAME(rs.getString("LOCKER_NAME"));
				adaguData.setLOCKER_BILLNUM(rs.getString("LOCKER_BILLNUM"));
				adaguData.setLOCKER_DATE(rs.getString("LOCKER_DATE"));
				adaguData.setPAID_AMOUNT(rs.getString("PAID_AMOUNT"));
				adaguData.setREMAINING_AMOUNT(rs.getString("REMAINING_AMOUNT"));
				AdaguBillList.add(adaguData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return AdaguBillList;
	}

	public BigDecimal getMAXValue(String lICENCENUM) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal maxValue = BigDecimal.ZERO;
		String query = "";

		// String query = " Select IFNULL(Max(Bill_No),0) AS MAXBILLNUM From
		// AD_ADAGUDATA ";

		query = " SELECT IFNULL(MAX(CAST(TRIM(BILL_NO) AS DECIMAL)),0) AS MAXBILLNUM FROM AD_ADAGUDATA WHERE LICENCE_NO = ? ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, lICENCENUM);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				maxValue = new BigDecimal(rs.getString("MAXBILLNUM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxValue;
	}

	public boolean updatePaidStatus(String bill_NUMBER) {
		boolean isPaid = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = " UPDATE AD_ADAGUDATA SET STATUS = ? , CANCEL_DATE = ? WHERE BILL_NO = ? ";
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "Paid".toUpperCase().trim().toString());
			pstmt.setString(2, "" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			pstmt.setString(3, bill_NUMBER);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				isPaid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isPaid;
	}

	public Double getBarData(String type) {
		Double value = 0.0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		switch (type) {
		case "B1":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '09:00:00' AND '10:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B2":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '10:00:00' AND '11:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B3":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '11:00:00' AND '12:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B4":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '12:00:00' AND '13:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B5":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '13:00:00' AND '14:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B6":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '14:00:00' AND '15:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B7":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '15:00:00' AND '16:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B8":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '16:00:00' AND '17:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B9":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '17:00:00' AND '18:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B10":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '18:00:00' AND '19:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B11":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '19:00:00' AND '20:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		case "B12":
			sb.append(
					" SELECT IFNULL(SUM(ADAGU_AMOUNT),0) AS VALUE FROM AD_ADAGUDATA WHERE TIME(CREATED) BETWEEN '20:00:00' AND '21:00:00' AND DATE(CREATED) = CURDATE() ");
			break;
		default:
			System.out.println("Unknown Type!");
		}

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				value = Double.parseDouble(rs.getString("VALUE"));
				;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	public String loadADAGUDataAndWriteCSV() {
		AdaguBill adaguData = new AdaguBill();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userHome = System.getProperty("user.home");
		String downloadsFolder = userHome + File.separator + "Downloads";

		File downloadsDir = new File(downloadsFolder);
		if (!downloadsDir.exists()) {
			downloadsDir.mkdir();
		}

		String filePath = downloadsFolder + File.separator + "BKP_"
				+ new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".csv";

		String query = "SELECT LICENCE_NO, BILL_NO, CUSTOMER_NAME, CUSTOMER_PHONE, HEIR, HEIR_RELATION, CUSTOMER_ADDRESS, "
				+ "PRODUCT, PRODUCT_WEIGHT, ADAGU_AMOUNT, ADAGU_ACTUAL_AMOUNT, PRODUCT_VALUE, ADAGU_DATE, REDEMPTION_DATE, "
				+ "CANCEL_DATE, BILL_TYPE, STATUS FROM AD_ADAGUDATA WHERE ISACTIVE='Y'";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adaguData = new AdaguBill();
				adaguData.setLICENCE_NUMBER(rs.getString("LICENCE_NO"));
				adaguData.setBILL_NUMBER(rs.getString("BILL_NO"));
				adaguData.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
				adaguData.setCUSTOMER_PHONE(rs.getString("CUSTOMER_PHONE"));
				adaguData.setHEIR_RELATION(rs.getString("HEIR_RELATION"));
				adaguData.setHEIR(rs.getString("HEIR"));
				adaguData.setCUSTOMER_ADDRESS(rs.getString("CUSTOMER_ADDRESS"));
				adaguData.setPRODUCTS(rs.getString("PRODUCT"));
				adaguData.setPRODUCT_WEIGHT(rs.getString("PRODUCT_WEIGHT"));
				adaguData.setADAGU_AMOUNT(rs.getString("ADAGU_AMOUNT"));
				adaguData.setACTUAL_ADAGU_AMOUNT(rs.getString("ADAGU_ACTUAL_AMOUNT"));
				adaguData.setPRODUCT_VALUE(rs.getString("PRODUCT_VALUE"));
				adaguData.setADAGU_DATE(rs.getString("ADAGU_DATE"));
				adaguData.setREDEMPTION_DATE(rs.getString("REDEMPTION_DATE"));
				adaguData.setCANCEL_DATE(rs.getString("CANCEL_DATE"));
				adaguData.setBILL_TYPE(rs.getString("BILL_TYPE"));
				adaguData.setSTATUS(rs.getString("STATUS"));

				try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
					writer.write(String.join(",", adaguData.getLICENCE_NUMBER()));
					writer.write(String.join(",", adaguData.getBILL_NUMBER()));
					writer.write(String.join(",", adaguData.getCUSTOMER_NAME()));
					writer.write(String.join(",", adaguData.getCUSTOMER_PHONE()));
					writer.write(String.join(",", adaguData.getHEIR_RELATION()));
					writer.write(String.join(",", adaguData.getHEIR()));
					writer.write(String.join(",", adaguData.getCUSTOMER_ADDRESS()));
					writer.write(String.join(",", adaguData.getPRODUCTS()));
					writer.write(String.join(",", adaguData.getPRODUCT_WEIGHT()));
					writer.write(String.join(",", adaguData.getADAGU_AMOUNT()));
					writer.write(String.join(",", adaguData.getACTUAL_ADAGU_AMOUNT()));
					writer.write(String.join(",", adaguData.getPRODUCT_VALUE()));
					writer.write(String.join(",", adaguData.getADAGU_DATE()));
					writer.write(String.join(",", adaguData.getREDEMPTION_DATE()));
					writer.write(String.join(",", adaguData.getCANCEL_DATE()));
					writer.write(String.join(",", adaguData.getBILL_TYPE()));
					writer.write(String.join(",", adaguData.getSTATUS()));
					writer.newLine();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return filePath;
	}

	public boolean saveNewProduct(String productType, String productString) {
		boolean isSaved = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		if (productType.equals("Gold")) {
			query = "INSERT INTO M_PRODUCT_GOLD (PRODUCTTYPE, PRODUCTNAME) VALUES " + "( ?, ? )";
		} else {
			query = "INSERT INTO M_PRODUCT_SILVER (PRODUCTTYPE, PRODUCTNAME) VALUES " + "( ?, ? )";
		}

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, productType.toUpperCase().trim().toString());
			pstmt.setString(2, productString.toUpperCase().trim().toString());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				isSaved = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSaved;
	}

	public ArrayList<String> loadGoldData() {
		ArrayList<String> productGoldList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT PRODUCTNAME FROM M_PRODUCT_GOLD WHERE ISACTIVE='Y'";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productGoldList.add(rs.getString("PRODUCTNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return productGoldList;
	}

	public ArrayList<String> loadSilverData() {
		ArrayList<String> productSilverList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT PRODUCTNAME FROM M_PRODUCT_SILVER WHERE ISACTIVE='Y'";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productSilverList.add(rs.getString("PRODUCTNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return productSilverList;
	}

	public boolean deleteAdaguData(String bill_NUMBER) {
		boolean isDeleted = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "DELETE FROM AD_ADAGUDATA WHERE AD_ADAGUDATA_ID = (SELECT MAXID FROM (SELECT MAX(AD_ADAGUDATA_ID) AS MAXID FROM AD_ADAGUDATA "
				+ " WHERE BILL_NO = ?) AS TMP )";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			try (Statement stmt = conn.createStatement()) {
				stmt.execute("SET SQL_SAFE_UPDATES = 0;");
			}
			 
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bill_NUMBER.trim().toString());

			int rowsAffected = pstmt.executeUpdate();
			
			try (Statement stmt = conn.createStatement()) {
				stmt.execute("SET SQL_SAFE_UPDATES = 1;");
			}

			if (rowsAffected > 0) {
				isDeleted = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isDeleted;
	}

	public boolean updateAdaguData(AdaguBill updatedData, AdaguBill oldData) {
		boolean isUpdated = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		/*
		 * String query =
		 * " UPDATE AD_ADAGUDATA SET CUSTOMER_NAME = ? , CUSTOMER_PHONE = ? , CUSTOMER_ADDRESS = ? , ADAGU_DATE = ? , REDEMPTION_DATE = ? "
		 * + " WHERE BILL_NO IN ( ";
		 * 
		 * query = query +
		 * "SELECT BILL_NO FROM AD_ADAGUDATA WHERE CUSTOMER_NAME = ? AND CUSTOMER_ADDRESS = ? ) "
		 * ;
		 */

		String query = " UPDATE AD_ADAGUDATA SET CUSTOMER_NAME = ? , CUSTOMER_PHONE = ? , CUSTOMER_ADDRESS = ? , ADAGU_DATE = ? , REDEMPTION_DATE = ? "
				+ " , PRODUCT_WEIGHT = ?, HEIR = ?, CANCEL_DATE = ?  WHERE BILL_NO = ? "; //23July2025 //08Aug2025

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updatedData.getCUSTOMER_NAME().toUpperCase().trim().toString());
			pstmt.setString(2, updatedData.getCUSTOMER_PHONE().toUpperCase().trim().toString());
			pstmt.setString(3, updatedData.getCUSTOMER_ADDRESS().toUpperCase().trim().toString());
			pstmt.setString(4, updatedData.getADAGU_DATE().toUpperCase().trim().toString());
			pstmt.setString(5, updatedData.getREDEMPTION_DATE().toUpperCase().trim().toString());
			pstmt.setString(6, updatedData.getPRODUCT_WEIGHT().toUpperCase().trim().toString());
			pstmt.setString(7, updatedData.getHEIR().toUpperCase().trim().toString()); //23July2025
			pstmt.setString(8, updatedData.getCANCEL_DATE().toUpperCase().trim().toString()); //08Aug2025
			pstmt.setString(9, updatedData.getBILL_NUMBER().toUpperCase().trim().toString());
			/*
			 * pstmt.setString(6,
			 * oldData.getCUSTOMER_NAME().toUpperCase().trim().toString());
			 * pstmt.setString(7,
			 * oldData.getCUSTOMER_ADDRESS().toUpperCase().trim().toString());
			 */
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				isUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;
	}

	public boolean saveNewAddress(String custAddress) {
		boolean isSaved = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		query = "INSERT INTO AD_CUSTOMER_ADDRESS (NAME) VALUES " + "( ? )";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, custAddress.toUpperCase().trim().toString().toString());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				isSaved = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSaved;
	}

	public boolean saveNewName(String custName) {
		boolean isSaved = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		query = "INSERT INTO AD_CUSTOMER_NAME (NAME) VALUES " + "( ? )";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, custName.toUpperCase().trim().toString().toString());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				isSaved = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSaved;
	}

	public ArrayList<String> loadCustomerData() {
		ArrayList<String> masterCustomerList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT DISTINCT NAME FROM AD_CUSTOMER_NAME WHERE ISACTIVE='Y'";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				masterCustomerList.add(rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return masterCustomerList;
	}

	public ArrayList<String> loadAddressData() {
		ArrayList<String> masterCustomerList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT DISTINCT NAME FROM AD_CUSTOMER_ADDRESS WHERE ISACTIVE='Y'";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				masterCustomerList.add(rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return masterCustomerList;
	}

	public ArrayList<String> loadCustomerHeirData() {
		ArrayList<String> masterHeirList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT DISTINCT NAME FROM AD_CUSTOMER_HEIR WHERE ISACTIVE='Y'";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				masterHeirList.add(rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return masterHeirList;
	}

	public boolean saveNewHeir(String custHeir) {
		boolean isSaved = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		query = "INSERT INTO AD_CUSTOMER_HEIR (NAME) VALUES " + "( ? )";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, custHeir.toUpperCase().trim().toString().toString());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				isSaved = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSaved;
	}

	// Locker Detail New Fix
	public boolean saveNewLockerDetails(String lockerNameName) {
		boolean isSaved = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		query = "INSERT INTO AD_LOCKER_DETAILS (NAME) VALUES " + "( ? )";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, lockerNameName.trim().toString());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				isSaved = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSaved;
	}

	public ArrayList<String> loadLockerData() {
		ArrayList<String> masterLockerList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT DISTINCT NAME FROM AD_LOCKER_DETAILS WHERE ISACTIVE='Y'";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				masterLockerList.add(rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return masterLockerList;
	}

	// Payment Detail New Fix
	public boolean updatePaymentData(AdaguBill updatedData) {
		boolean isUpdated = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = " UPDATE AD_ADAGUDATA SET PAID_AMOUNT = ? , REMAINING_AMOUNT = ? WHERE BILL_NO = ? ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updatedData.getPAID_AMOUNT().toUpperCase().trim().toString());
			pstmt.setString(2, updatedData.getREMAINING_AMOUNT().toUpperCase().trim().toString());
			pstmt.setString(3, updatedData.getBILL_NUMBER().toUpperCase().trim().toString());
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				isUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;
	}

	public boolean updateLockerData(AdaguBill updatedData) {
		boolean isUpdated = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = " UPDATE AD_ADAGUDATA SET LOCKER_NAME = ? , LOCKER_BILLNUM = ? , LOCKER_DATE = ?  WHERE BILL_NO = ? ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updatedData.getLOCKER_NAME().toUpperCase().trim().toString());
			pstmt.setString(2, updatedData.getLOCKER_BILLNUM().toUpperCase().trim().toString());
			pstmt.setString(3, updatedData.getLOCKER_DATE().toUpperCase().trim().toString());
			pstmt.setString(4, updatedData.getBILL_NUMBER().toUpperCase().trim().toString());
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				isUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;
	}

	public boolean updateBalanceData(AdaguBill updatedData) {
		boolean isUpdated = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = " UPDATE AD_ADAGUDATA SET BALANCE_BILLNUM = ? , BALANCE_PRODUCT = ? , BALANCE_DATE = ?  WHERE BILL_NO = ? ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updatedData.getBALANCE_BILLNUM().toUpperCase().trim().toString());
			pstmt.setString(2, updatedData.getBALANCE_PRODUCT().toUpperCase().trim().toString());
			pstmt.setString(3, updatedData.getBALANCE_DATE().toUpperCase().trim().toString());
			pstmt.setString(4, updatedData.getBILL_NUMBER().toUpperCase().trim().toString());
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				isUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;
	}

	public BigDecimal getMAXValueDummy(String lICENCENUM) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal maxValue = BigDecimal.ZERO;
		String query = "";

		// String query = " Select IFNULL(Max(Bill_No),0) AS MAXBILLNUM From
		// AD_ADAGUDATA ";

		query = " SELECT IFNULL(CAST(TRIM(BILL_NO) AS DECIMAL), 0) AS MAXBILLNUM FROM AD_ADAGUDATA "
				+ " WHERE LICENCE_NO = ? ORDER BY AD_ADAGUDATA_ID DESC LIMIT 1 ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, lICENCENUM);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				maxValue = new BigDecimal(rs.getString("MAXBILLNUM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxValue;
	}

	public boolean isAdmin(String UName, String UPass) {
		boolean isValidLogin = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int AD_User_ID = 0;

		String query = "SELECT AD_User_ID FROM AD_USER WHERE isAdmin = 'Y' AND isActive = 'Y' AND UserName = ? AND Password = ?";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, UName);
			pstmt.setString(2, UPass);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				AD_User_ID = rs.getInt("AD_User_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (AD_User_ID > 0) {
			isValidLogin = true;
		}

		return isValidLogin;
	}

	public boolean checkbillNum(String BillNum) {
		boolean isDuplicateBillNum = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int BILLNUM = 0;

		String query = "SELECT MAX(BILL_NO) AS BILL_NUM FROM AD_ADAGUDATA WHERE BILL_NO = ? ";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, BillNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				BILLNUM = rs.getInt("BILL_NUM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (BILLNUM > 0) {
			isDuplicateBillNum = true;
		}

		return isDuplicateBillNum;
	}

	public ArrayList<String> exportBackupData() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> exportedFilePaths = new ArrayList<>(); 
		String userHome = System.getProperty("user.home");
		String downloadsFolder = userHome + File.separator + "Downloads" + File.separator + "LPJBackUps";

		File downloadsDir = new File(downloadsFolder);
		if (!downloadsDir.exists()) {
			downloadsDir.mkdir();
		}

		try {
			// Connect to the database
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "SHOW TABLES";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String tableName = rs.getString(1);
				Statement tableStmt = conn.createStatement();
				ResultSet tableRs = tableStmt.executeQuery("SELECT * FROM " + tableName);
				ResultSetMetaData metaData = tableRs.getMetaData();
				int columnCount = metaData.getColumnCount();
				// Define CSV file path
				String csvFilePath = downloadsFolder + File.separator + "BKP_" + tableName + "_"
						+ new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".csv";
				FileWriter csvWriter = new FileWriter(csvFilePath);

				// Write header
				for (int i = 1; i <= columnCount; i++) {
					csvWriter.append(metaData.getColumnLabel(i));
					if (i < columnCount)
						csvWriter.append(",");
				}
				csvWriter.append("\n");

				// Write data rows
				while (tableRs.next()) {
					for (int i = 1; i <= columnCount; i++) {
						String value = tableRs.getString(i);
						csvWriter.append(value != null ? value.replaceAll("\"", "\"\"") : "");
						if (i < columnCount)
							csvWriter.append(",");
					}
					csvWriter.append("\n");
				}

				// Clean up
				csvWriter.flush();
				csvWriter.close();
				tableRs.close();
				tableStmt.close();
				exportedFilePaths.add(csvFilePath);
			}

		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return exportedFilePaths;

	}
	
	// 28Apr2025 - Start
		public ArrayList<String> loadclosingBillNums() {
			ArrayList<String> closingBillNums = new ArrayList<String>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String query = "SELECT DISTINCT BILL_NO AS BILL_NO FROM AD_ADAGUDATA WHERE ISACTIVE='Y' AND (STATUS <> 'PAID' OR STATUS <> 'Paid') ";

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					closingBillNums.add(rs.getString("BILL_NO"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return closingBillNums;
		}
		// 28Apr2025 - End

		// 30Apr2025
		public boolean updatePaidStatus(String bill_NUMBER, String cancel_date) {
			boolean isPaid = false;
			Connection conn = null;
			PreparedStatement pstmt = null;

			String query = " UPDATE AD_ADAGUDATA SET STATUS = ? , CANCEL_DATE = ? WHERE BILL_NO = ? ";
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "Paid".toUpperCase().trim().toString());
				pstmt.setString(2, cancel_date);
				pstmt.setString(3, bill_NUMBER);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					isPaid = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return isPaid;
		}

		public boolean saveSalesBill(SalesBill salesData) {
			boolean isSaved = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String query = "INSERT INTO AD_SALESDATA ( CUSTOMER_PHONE, SALES_DATE, CUSTOME_NAME, CUSTOMER_ADDRESS, PRODUCT,"
					+ " PRODUCT_TYPE, PRODUCT_WEIGHT, SALES_AMOUNT, BALANCE_DESC, BALANCE_AMOUNT, SALES_AMOUNT2) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?)";

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, salesData.getCUSTOMER_PHONE());
				pstmt.setString(2, salesData.getSALES_DATE());
				pstmt.setString(3, salesData.getCUSTOMER_NAME());
				pstmt.setString(4, salesData.getCUSTOMER_ADDRESS());
				pstmt.setString(5, salesData.getPRODUCTS());
				pstmt.setString(6, salesData.getPRODUCT_TYPE());
				pstmt.setString(7, salesData.getPRODUCT_WEIGHT());
				pstmt.setString(8, salesData.getSALES_AMOUNT());
				pstmt.setString(9, salesData.getBALANCE_DESCRIPTION());
				pstmt.setString(10, salesData.getBALANCE_AMOUNT());
				pstmt.setString(11, salesData.getSALES_AMOUNT2());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					isSaved = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return isSaved;
		}
		// 30Apr2025

		// TR Licence Check Logic Start
		public String getLicenseEndDate(String UName) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String EndDate = "";

			String query = "SELECT DATE_FORMAT(ENDDATE, '%d-%m-%Y') AS ENDDATE FROM TR_LICENSE WHERE NAME = ? ";

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, UName);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					EndDate = rs.getString("ENDDATE");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return EndDate;
		}
		// TR Licence Check Logic Start
		
		//23July2025
		public boolean executeUpdateOrDelete(String sql) {
			boolean isUpdated = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String query = "" + sql;
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				try (Statement stmt = conn.createStatement()) {
					stmt.execute("SET SQL_SAFE_UPDATES = 0;");
				}
				pstmt = conn.prepareStatement(query);
				int rowsAffected = pstmt.executeUpdate();
				try (Statement stmt = conn.createStatement()) {
					stmt.execute("SET SQL_SAFE_UPDATES = 1;");
				}
				if (rowsAffected > 0) {
					isUpdated = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return isUpdated;
		}
		
		// 08Aug2025
		public boolean deleteSalesData(String bill_NUMBER) {
			boolean isDeleted = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "DELETE FROM AD_SALESDATA WHERE AD_SALESDATA_ID = ?";

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);

				try (Statement stmt = conn.createStatement()) {
					stmt.execute("SET SQL_SAFE_UPDATES = 0;");
				}

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, bill_NUMBER.trim());

				int rowsAffected = pstmt.executeUpdate();

				try (Statement stmt = conn.createStatement()) {
					stmt.execute("SET SQL_SAFE_UPDATES = 1;");
				}

				if (rowsAffected > 0) {
					isDeleted = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return isDeleted;
		}
		
		public ArrayList<AdaguBill> loadADAGUInterestData() {
			ArrayList<AdaguBill> AdaguBillList = new ArrayList<AdaguBill>();
			AdaguBill adaguData = new AdaguBill();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String query = "SELECT LICENCE_NO, BILL_NO, CUSTOMER_NAME, CUSTOMER_PHONE, HEIR, HEIR_RELATION, CUSTOMER_ADDRESS, "
					+ "PRODUCT, PRODUCT_WEIGHT, ADAGU_AMOUNT, ADAGU_ACTUAL_AMOUNT, PRODUCT_VALUE, ADAGU_DATE, REDEMPTION_DATE, "
					+ "CANCEL_DATE, BILL_TYPE, STATUS, LOCKER_NAME, LOCKER_BILLNUM, LOCKER_DATE, PAID_AMOUNT, REMAINING_AMOUNT"
					+ ", BALANCE_BILLNUM, BALANCE_PRODUCT, BALANCE_DATE, PRODUCT_TYPE FROM AD_ADAGUDATA WHERE ISACTIVE='Y' AND STATUS != 'PAID' ORDER BY BILL_NO ";

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					adaguData = new AdaguBill();
					adaguData.setLICENCE_NUMBER(rs.getString("LICENCE_NO"));
					adaguData.setBILL_NUMBER(rs.getString("BILL_NO"));
					adaguData.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
					adaguData.setCUSTOMER_PHONE(rs.getString("CUSTOMER_PHONE"));
					adaguData.setHEIR_RELATION(rs.getString("HEIR_RELATION"));
					adaguData.setHEIR(rs.getString("HEIR"));
					adaguData.setCUSTOMER_ADDRESS(rs.getString("CUSTOMER_ADDRESS"));
					adaguData.setPRODUCTS(rs.getString("PRODUCT"));
					adaguData.setPRODUCT_WEIGHT(rs.getString("PRODUCT_WEIGHT"));
					adaguData.setADAGU_AMOUNT(rs.getString("ADAGU_AMOUNT"));
					adaguData.setACTUAL_ADAGU_AMOUNT(rs.getString("ADAGU_ACTUAL_AMOUNT"));
					adaguData.setPRODUCT_VALUE(rs.getString("PRODUCT_VALUE"));
					adaguData.setADAGU_DATE(rs.getString("ADAGU_DATE"));
					adaguData.setREDEMPTION_DATE(rs.getString("REDEMPTION_DATE"));
					adaguData.setCANCEL_DATE(rs.getString("CANCEL_DATE"));
					adaguData.setBILL_TYPE(rs.getString("BILL_TYPE"));
					adaguData.setSTATUS(rs.getString("STATUS"));
					adaguData.setLOCKER_NAME(rs.getString("LOCKER_NAME"));
					adaguData.setLOCKER_BILLNUM(rs.getString("LOCKER_BILLNUM"));
					adaguData.setLOCKER_DATE(rs.getString("LOCKER_DATE"));
					adaguData.setPAID_AMOUNT(rs.getString("PAID_AMOUNT"));
					adaguData.setREMAINING_AMOUNT(rs.getString("REMAINING_AMOUNT"));
					adaguData.setBALANCE_BILLNUM(rs.getString("BALANCE_BILLNUM"));
					adaguData.setBALANCE_PRODUCT(rs.getString("BALANCE_PRODUCT"));
					adaguData.setBALANCE_DATE(rs.getString("BALANCE_DATE"));
					adaguData.setPRODUCT_TYPE(rs.getString("PRODUCT_TYPE")); //23Sep2025
					AdaguBillList.add(adaguData);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return AdaguBillList;
		}
		
		public static void main(String...args) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("CONNECTION :: "+conn.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	
}