package source;

public class SalesBill {

	private String CUSTOMER_PHONE = "";
	private String SALES_DATE = "";
	private String CUSTOMER_NAME = "";
	private String CUSTOMER_ADDRESS = "";
	private String PRODUCT_TYPE = "";
	private String PRODUCTS = "";
	private String PRODUCT_WEIGHT = "";
	private String SALES_AMOUNT = "";
	private String BALANCE_DESCRIPTION = "";
	private String BALANCE_AMOUNT = "";
	private String SALES_AMOUNT2 = "";
	private String BILL_TYPE = "";
	private String BILL_NO = "";
	private String STATUS = "";
	private int AD_SALESDATA_ID = 0;
	private String BILL_TYPES = ""; //07Oct2025
	private String OLD_BALANCE_AMOUNT = ""; //24Oct2025
	
	//27Oct2025
	private String GOLD_RATE = "";
	private String SILVER_RATE = "";
	private String DATE = "";
	public String getGOLD_RATE() {
		return GOLD_RATE;
	}
	public void setGOLD_RATE(String gOLD_RATE) {
		GOLD_RATE = gOLD_RATE;
	}
	public String getSILVER_RATE() {
		return SILVER_RATE;
	}
	public void setSILVER_RATE(String sILVER_RATE) {
		SILVER_RATE = sILVER_RATE;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	//27Oct2025

	//24Oct2025
	public String getOLDBALANCE_AMOUNT() {
		return OLD_BALANCE_AMOUNT;
	}
	public void setOLDBALANCE_AMOUNT(String bALANCE_AMOUNT) {
		OLD_BALANCE_AMOUNT = bALANCE_AMOUNT;
	}
	//24Oct2025
	
	
	public int getAD_SALESDATA_ID() {
		return AD_SALESDATA_ID;
	}
	public void setAD_SALESDATA_ID(int aD_SALESDATA_ID) {
		AD_SALESDATA_ID = aD_SALESDATA_ID;
	}


	
	
	public String getCUSTOMER_PHONE() {
		return CUSTOMER_PHONE;
	}
	public void setCUSTOMER_PHONE(String cUSTOMER_PHONE) {
		CUSTOMER_PHONE = cUSTOMER_PHONE;
	}
	public String getSALES_DATE() {
		return SALES_DATE;
	}
	public void setSALES_DATE(String sALES_DATE) {
		SALES_DATE = sALES_DATE;
	}
	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}
	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
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
	public String getSALES_AMOUNT() {
		return SALES_AMOUNT;
	}
	public void setSALES_AMOUNT(String sALES_AMOUNT) {
		SALES_AMOUNT = sALES_AMOUNT;
	}
	public String getBALANCE_DESCRIPTION() {
		return BALANCE_DESCRIPTION;
	}
	public void setBALANCE_DESCRIPTION(String bALANCE_DESCRIPTION) {
		BALANCE_DESCRIPTION = bALANCE_DESCRIPTION;
	}
	public String getBALANCE_AMOUNT() {
		return BALANCE_AMOUNT;
	}
	public void setBALANCE_AMOUNT(String bALANCE_AMOUNT) {
		BALANCE_AMOUNT = bALANCE_AMOUNT;
	}
	@Override
	public String toString() {
		return "SalesBill [CUSTOMER_PHONE=" + CUSTOMER_PHONE + ", SALES_DATE=" + SALES_DATE + ", CUSTOMER_NAME="
				+ CUSTOMER_NAME + ", CUSTOMER_ADDRESS=" + CUSTOMER_ADDRESS + ", PRODUCTS=" + PRODUCTS
				+ ", PRODUCT_WEIGHT=" + PRODUCT_WEIGHT + ", SALES_AMOUNT=" + SALES_AMOUNT + ", BALANCE_DESCRIPTION="
				+ BALANCE_DESCRIPTION + ", BALANCE_AMOUNT=" + BALANCE_AMOUNT + "]";
	}
	public String getPRODUCT_TYPE() {
		return PRODUCT_TYPE;
	}
	public void setPRODUCT_TYPE(String pRODUCT_TYPE) {
		PRODUCT_TYPE = pRODUCT_TYPE;
	}
	public String getSALES_AMOUNT2() {
		return SALES_AMOUNT2;
	}
	public void setSALES_AMOUNT2(String sALES_AMOUNT2) {
		SALES_AMOUNT2 = sALES_AMOUNT2;
	}
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bILL_TYPE) {
		BILL_TYPE = bILL_TYPE;
	}
	public String getBILL_NO() {
		return BILL_NO;
	}
	public void setBILL_NO(String bILL_NO) {
		BILL_NO = bILL_NO;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	//07Oct2025
	public String getBILL_TYPES() {
		return BILL_TYPES;
	}
	public void setBILL_TYPES(String bILL_TYPES) {
		BILL_TYPES = bILL_TYPES;
	}
	//07Oct2025
}