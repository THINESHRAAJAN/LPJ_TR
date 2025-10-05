package source;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailConfig {

	public boolean sendEmail(String fROM, String tO, String cC, String sUBJECT, String mESSAGE, ArrayList<String> exportedFilePaths,
			String pASSWORD) {
		boolean isSent = false;
		
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587"); 
        properties.setProperty("mail.smtp.starttls.enable", "true"); 
        properties.setProperty("mail.smtp.auth", "true"); 

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(""+fROM, ""+pASSWORD);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tO));
            //message.addRecipient(Message.RecipientType.CC, new InternetAddress(cC));
            message.setSubject(""+sUBJECT);
            message.setText(""+mESSAGE);
            
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(""+mESSAGE);
            multipart.addBodyPart(textPart);
            /*MimeBodyPart attachmentPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(fILENAME);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(source.getName());
            multipart.addBodyPart(attachmentPart);*/
            
            for (String filePath : exportedFilePaths) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                FileDataSource source = new FileDataSource(filePath);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(source.getName());
                multipart.addBodyPart(attachmentPart);
            }
            message.setContent(multipart);


            Transport.send(message);
            isSent = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
		
		
		
		return isSent;
	}
	
	
	
	public static void main(String...args) {
		
		
	}
}
