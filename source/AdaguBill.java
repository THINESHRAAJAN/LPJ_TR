package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdaguBill {

	private String SNO = "";
	private String LICENCE_NUMBER = "";
	private String BILL_NUMBER = "";
	private String CUSTOMER_NAME = "";
	private String CUSTOMER_PHONE = "";
	private String HEIR_RELATION = "";
	private String HEIR = "";
	private String CUSTOMER_ADDRESS = "";
	private String PRODUCTS = "";
	private String PRODUCT_WEIGHT = "";
	private String ADAGU_AMOUNT = "";
	private String ACTUAL_ADAGU_AMOUNT = "";
	private String PRODUCT_VALUE = "";
	private String ADAGU_DATE = "";
	private String REDEMPTION_DATE = "";
	private String CANCEL_DATE = "";
	private String BILL_TYPE = "";
	private String STATUS = "";
	private String PRODUCT_TYPE = "";

	// Locker Detail New Fix
	private String LOCKER_NAME = "";
	private String LOCKER_BILLNUM = "";
	private String LOCKER_DATE = "";
	
	// Balance Detail New Fix
	private String BALANCE_PRODUCT = "";
	private String BALANCE_BILLNUM = "";
	private String BALANCE_DATE = "";

	public String getBALANCE_PRODUCT() {
		return BALANCE_PRODUCT;
	}

	public void setBALANCE_PRODUCT(String bALANCE_PRODUCT) {
		BALANCE_PRODUCT = bALANCE_PRODUCT;
	}

	public String getBALANCE_BILLNUM() {
		return BALANCE_BILLNUM;
	}

	public void setBALANCE_BILLNUM(String bALANCE_BILLNUM) {
		BALANCE_BILLNUM = bALANCE_BILLNUM;
	}

	public String getBALANCE_DATE() {
		return BALANCE_DATE;
	}

	public void setBALANCE_DATE(String bALANCE_DATE) {
		BALANCE_DATE = bALANCE_DATE;
	}

	public String getLOCKER_NAME() {
		return LOCKER_NAME;
	}

	public void setLOCKER_NAME(String lOCKER_NAME) {
		LOCKER_NAME = lOCKER_NAME;
	}

	public String getLOCKER_BILLNUM() {
		return LOCKER_BILLNUM;
	}

	public void setLOCKER_BILLNUM(String lOCKER_BILLNUM) {
		LOCKER_BILLNUM = lOCKER_BILLNUM;
	}

	public String getLOCKER_DATE() {
		return LOCKER_DATE;
	}

	public void setLOCKER_DATE(String lOCKER_DATE) {
		LOCKER_DATE = lOCKER_DATE;
	}

	// Payment Detail New Fix
	private String PAID_AMOUNT = "";
	private String REMAINING_AMOUNT = "";

	public String getPAID_AMOUNT() {
		return PAID_AMOUNT;
	}

	public void setPAID_AMOUNT(String pAID_AMOUNT) {
		PAID_AMOUNT = pAID_AMOUNT;
	}

	public String getREMAINING_AMOUNT() {
		return REMAINING_AMOUNT;
	}

	public void setREMAINING_AMOUNT(String rEMAINING_AMOUNT) {
		REMAINING_AMOUNT = rEMAINING_AMOUNT;
	}

	public String getSNO() {
		return SNO;
	}

	public void setSNO(String sNO) {
		SNO = sNO;
	}

	public String getLICENCE_NUMBER() {
		return LICENCE_NUMBER;
	}

	public void setLICENCE_NUMBER(String lICENCE_NUMBER) {
		LICENCE_NUMBER = lICENCE_NUMBER;
	}

	public String getBILL_NUMBER() {
		return BILL_NUMBER;
	}

	public void setBILL_NUMBER(String bILL_NUMBER) {
		BILL_NUMBER = bILL_NUMBER;
	}

	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}

	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}

	public String getCUSTOMER_PHONE() {
		return CUSTOMER_PHONE;
	}

	public void setCUSTOMER_PHONE(String cUSTOMER_PHONE) {
		CUSTOMER_PHONE = cUSTOMER_PHONE;
	}

	public String getHEIR_RELATION() {
		return HEIR_RELATION;
	}

	public void setHEIR_RELATION(String hEIR_RELATION) {
		HEIR_RELATION = hEIR_RELATION;
	}

	public String getHEIR() {
		return HEIR;
	}

	public void setHEIR(String hEIR) {
		HEIR = hEIR;
	}

	public String getCUSTOMER_ADDRESS() {
		return CUSTOMER_ADDRESS;
	}

	public void setCUSTOMER_ADDRESS(String cUSTOMER_ADDRESS) {
		CUSTOMER_ADDRESS = cUSTOMER_ADDRESS;
	}

	public String getPRODUCTS() {
		return PRODUCTS;
	}

	public void setPRODUCTS(String pRODUCTS) {
		PRODUCTS = pRODUCTS;
	}

	public String getPRODUCT_WEIGHT() {
		return PRODUCT_WEIGHT;
	}

	public void setPRODUCT_WEIGHT(String pRODUCT_WEIGHT) {
		PRODUCT_WEIGHT = pRODUCT_WEIGHT;
	}

	public String getADAGU_AMOUNT() {
		return ADAGU_AMOUNT;
	}

	public void setADAGU_AMOUNT(String aDAGU_AMOUNT) {
		ADAGU_AMOUNT = aDAGU_AMOUNT;
	}

	public String getACTUAL_ADAGU_AMOUNT() {
		return ACTUAL_ADAGU_AMOUNT;
	}

	public void setACTUAL_ADAGU_AMOUNT(String aCTUAL_ADAGU_AMOUNT) {
		ACTUAL_ADAGU_AMOUNT = aCTUAL_ADAGU_AMOUNT;
	}

	public String getPRODUCT_VALUE() {
		return PRODUCT_VALUE;
	}

	public void setPRODUCT_VALUE(String pRODUCT_VALUE) {
		PRODUCT_VALUE = pRODUCT_VALUE;
	}

	public String getADAGU_DATE() {
		return ADAGU_DATE;
	}

	public void setADAGU_DATE(String aDAGU_DATE) {
		ADAGU_DATE = aDAGU_DATE;
	}

	public String getREDEMPTION_DATE() {
		return REDEMPTION_DATE;
	}

	public void setREDEMPTION_DATE(String rEDEMPTION_DATE) {
		REDEMPTION_DATE = rEDEMPTION_DATE;
	}

	public String getBILL_TYPE() {
		return BILL_TYPE;
	}

	public void setBILL_TYPE(String bILL_TYPE) {
		BILL_TYPE = bILL_TYPE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getPRODUCT_TYPE() {
		return PRODUCT_TYPE;
	}

	public void setPRODUCT_TYPE(String pRODUCT_TYPE) {
		PRODUCT_TYPE = pRODUCT_TYPE;
	}

	public String getCANCEL_DATE() {
		return CANCEL_DATE;
	}

	public void setCANCEL_DATE(String cANCEL_DATE) {
		CANCEL_DATE = cANCEL_DATE;
	}

	public AdaguBill(String sNO, String lICENCE_NUMBER, String bILL_NUMBER, String cUSTOMER_NAME, String cUSTOMER_PHONE,
			String hEIR_RELATION, String hEIR, String cUSTOMER_ADDRESS, String pRODUCTS, String pRODUCT_WEIGHT,
			String aDAGU_AMOUNT, String aCTUAL_ADAGU_AMOUNT, String pRODUCT_VALUE, String aDAGU_DATE,
			String rEDEMPTION_DATE, String cANCEL_DATE, String bILL_TYPE, String sTATUS) {
		super();
		SNO = sNO;
		LICENCE_NUMBER = lICENCE_NUMBER;
		BILL_NUMBER = bILL_NUMBER;
		CUSTOMER_NAME = cUSTOMER_NAME;
		CUSTOMER_PHONE = cUSTOMER_PHONE;
		HEIR_RELATION = hEIR_RELATION;
		HEIR = hEIR;
		CUSTOMER_ADDRESS = cUSTOMER_ADDRESS;
		PRODUCTS = pRODUCTS;
		PRODUCT_WEIGHT = pRODUCT_WEIGHT;
		ADAGU_AMOUNT = aDAGU_AMOUNT;
		ACTUAL_ADAGU_AMOUNT = aCTUAL_ADAGU_AMOUNT;
		PRODUCT_VALUE = pRODUCT_VALUE;
		ADAGU_DATE = aDAGU_DATE;
		REDEMPTION_DATE = rEDEMPTION_DATE;
		CANCEL_DATE = cANCEL_DATE;
		BILL_TYPE = bILL_TYPE;
		STATUS = sTATUS;
	}

	public AdaguBill() {
		// TODO Auto-generated constructor stub
	}

	public boolean saveAdaguBill(AdaguBill adaguData, String fileName) {
		boolean isSaved = false;

		String inputFilePath = fileName;
		String outputFilePath = fileName;

		List<String[]> records = new ArrayList<>();
		int maxSerialNo = 0;

		// Step 1: Read the existing CSV data and find the maximum serial number
		try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				records.add(row);

				// Assume the first column is the Serial No, and check for the maximum
				try {
					int serialNo = Integer.parseInt(row[0].trim());
					maxSerialNo = Math.max(maxSerialNo, serialNo); // Update max serial no
				} catch (NumberFormatException e) {
					// Skip if there's an error in parsing the serial number
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Step 2: Create the new record with the next serial number
		int newSerialNo = maxSerialNo + 1;

		ArrayList<String> newRow = new ArrayList<>();
		newRow.add("" + newSerialNo);
		newRow.add(adaguData.getLICENCE_NUMBER());
		newRow.add(adaguData.getBILL_NUMBER());
		newRow.add(adaguData.getCUSTOMER_NAME());
		newRow.add(adaguData.getCUSTOMER_PHONE());
		newRow.add(adaguData.getHEIR_RELATION());
		newRow.add(adaguData.getHEIR());
		newRow.add(adaguData.getCUSTOMER_ADDRESS());
		newRow.add(adaguData.getPRODUCTS());
		newRow.add(adaguData.getPRODUCT_WEIGHT());
		newRow.add(adaguData.getADAGU_AMOUNT());
		newRow.add(adaguData.getACTUAL_ADAGU_AMOUNT());
		newRow.add(adaguData.getPRODUCT_VALUE());
		newRow.add(adaguData.getADAGU_DATE());
		newRow.add(adaguData.getREDEMPTION_DATE());
		newRow.add(adaguData.getBILL_TYPE());
		newRow.add(adaguData.getSTATUS());

		// Step 3: Append the new data to the CSV file
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath, true))) {
			bw.write(String.join(",", newRow)); // Write the new row
			bw.newLine(); // New line for next row
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return isSaved;
	}

	@Override
	public String toString() {
		return "AdaguBill [SNO=" + SNO + ", LICENCE_NUMBER=" + LICENCE_NUMBER + ", BILL_NUMBER=" + BILL_NUMBER
				+ ", CUSTOMER_NAME=" + CUSTOMER_NAME + ", CUSTOMER_PHONE=" + CUSTOMER_PHONE + ", HEIR_RELATION="
				+ HEIR_RELATION + ", HEIR=" + HEIR + ", CUSTOMER_ADDRESS=" + CUSTOMER_ADDRESS + ", PRODUCTS=" + PRODUCTS
				+ ", PRODUCT_WEIGHT=" + PRODUCT_WEIGHT + ", ADAGU_AMOUNT=" + ADAGU_AMOUNT + ", ACTUAL_ADAGU_AMOUNT="
				+ ACTUAL_ADAGU_AMOUNT + ", PRODUCT_VALUE=" + PRODUCT_VALUE + ", ADAGU_DATE=" + ADAGU_DATE
				+ ", REDEMPTION_DATE=" + REDEMPTION_DATE + ", BILL_TYPE=" + BILL_TYPE + ", STATUS=" + STATUS + "]";
	}

	public ArrayList<AdaguBill> loadCSVData(String csvFile) {
		ArrayList<AdaguBill> AdaguBillList;
		AdaguBillList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				AdaguBill data = new AdaguBill();
				data.setSNO(row[0]);
				data.setLICENCE_NUMBER(row[1]);
				data.setBILL_NUMBER(row[2]);
				data.setCUSTOMER_NAME(row[3]);
				data.setCUSTOMER_PHONE(row[4]);
				data.setHEIR_RELATION(row[5]);
				data.setHEIR(row[6]);
				data.setCUSTOMER_ADDRESS(row[7]);
				data.setPRODUCTS(row[8]);
				data.setPRODUCT_WEIGHT(row[9]);
				data.setADAGU_AMOUNT(row[10]);
				data.setACTUAL_ADAGU_AMOUNT(row[11]);
				data.setPRODUCT_VALUE(row[12]);
				data.setADAGU_DATE(row[13]);
				data.setREDEMPTION_DATE(row[14]);
				data.setBILL_TYPE(row[15]);
				data.setSTATUS(row[16]);
				AdaguBillList.add(data);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return AdaguBillList;
	}

	public void saveDataToCSV(JTable table, String csvFile) {
		String filePath = csvFile; // Path to your CSV file
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// Write column names to the file
			for (int i = 0; i < model.getColumnCount(); i++) {
				bw.write(model.getColumnName(i));
				if (i < model.getColumnCount() - 1)
					bw.write(",");
			}
			bw.newLine();

			// Write data to the file
			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					bw.write(model.getValueAt(i, j).toString());
					if (j < model.getColumnCount() - 1)
						bw.write(",");
				}
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BigDecimal findMaxInCSV(String filePath, int columnIndex) {
		BigDecimal max = new BigDecimal(Double.MIN_VALUE); // Initialize to the smallest possible value
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length > columnIndex) {
					try {
						BigDecimal value = new BigDecimal(values[columnIndex].trim());
						value = value.setScale(2, RoundingMode.HALF_UP); // Optional: Set the scale to 2 decimal places
						if (value.compareTo(max) > 0) {
							max = value;
						}
					} catch (NumberFormatException ex) {
						// Handle case where the value is not a valid BigDecimal
						System.err.println("Skipping invalid number: " + values[columnIndex]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return max;
	}

}
