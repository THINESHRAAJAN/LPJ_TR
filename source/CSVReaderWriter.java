package source;

import java.io.*;
import java.util.*;

public class CSVReaderWriter {
    public static void main(String[] args) {
        String inputFilePath = "/home/thineshraajan/Downloads/AdaguBill_DB.csv";
        String outputFilePath = "/home/thineshraajan/Downloads/AdaguBill_DB.csv";  // Same file for appending

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
                    maxSerialNo = Math.max(maxSerialNo, serialNo);  // Update max serial no
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
        AdaguBill data = new AdaguBill();
        data.setSNO(String.valueOf(newSerialNo));
        data.setLICENCE_NUMBER("16/21-22");
        data.setBILL_NUMBER("22022025001");
        data.setCUSTOMER_NAME("Thinesh Raajan");
        data.setCUSTOMER_PHONE("6379300216");
        data.setHEIR_RELATION("S/O");
        data.setHEIR("Shankar");
        data.setCUSTOMER_ADDRESS("Tirupattur");
        data.setPRODUCTS("Chain");
        data.setPRODUCT_WEIGHT("2");
        data.setADAGU_AMOUNT("100");
        data.setACTUAL_ADAGU_AMOUNT("200");
        data.setPRODUCT_VALUE("200");
        data.setADAGU_DATE("22-02-2025");
        data.setREDEMPTION_DATE("22-02-2026");
        data.setBILL_TYPE("Adagu Bill");
        data.setSTATUS("Pending");
        
        ArrayList<String> newRow = new ArrayList<>();
        newRow.add(data.getSNO());
        newRow.add(data.getLICENCE_NUMBER());
        newRow.add(data.getBILL_NUMBER());
        newRow.add(data.getCUSTOMER_NAME());
        newRow.add(data.getCUSTOMER_PHONE());
        newRow.add(data.getHEIR_RELATION());
        newRow.add(data.getHEIR());
        newRow.add(data.getCUSTOMER_ADDRESS());
        newRow.add(data.getPRODUCTS());
        newRow.add(data.getPRODUCT_WEIGHT());
        newRow.add(data.getADAGU_AMOUNT());
        newRow.add(data.getACTUAL_ADAGU_AMOUNT());
        newRow.add(data.getPRODUCT_VALUE());
        newRow.add(data.getADAGU_DATE());
        newRow.add(data.getREDEMPTION_DATE());
        newRow.add(data.getBILL_TYPE());
        newRow.add(data.getSTATUS());
        
        
        

        // Step 3: Append the new data to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath, true))) {
            bw.write(String.join(",", newRow)); // Write the new row
            bw.newLine();  // New line for next row
            System.out.println("New data appended with Serial No: " + newSerialNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
