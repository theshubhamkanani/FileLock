package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendOTP {
    public static void sendOTP(String email, String genOTP) {

        String msg = "Hello "+email+" welcome to FILELOCK! Your data security is our top priority. Thanks for choosing us. Your journey to a secure digital space starts now!\n"+"Your FILELOCK verification code is: "+genOTP+ ". Please use this code to complete the login process. If you didn't request this code, please ignore this message. Stay secure!\n"+"\n\nYour FILELOCK verification code is: "+genOTP;

        // Recipient's email ID needs to be mentioned. here 'email' variable contain Recipient mail id
        String to = email;
        // Sender's email ID needs to be mentioned
        String from = "your_email_id@gmail.com";
        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "xaishflfglyuxzky"); // Sender Mail Password
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Your FILELOCK One Time Password");
            // Now set the actual message
            message.setText(msg);
            // Send message
            Transport.send(message);
            System.out.println("Sent OTP successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}