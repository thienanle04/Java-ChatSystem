package server.utility;

import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    private static final String FROM_EMAIL = "javamailtest22294@gmail.com";
    private static final String PASSWORD = "pkyeurwkmhtbohpw";
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;

    public static void sendEmail(String recipientEmail, String subject, String content) throws MessagingException, SQLException {
        // Cấu hình mail server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Xác thực tài khoản email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        // Tạo nội dung email
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject(subject);
        message.setText(content);

        // Gửi email
        Transport.send(message);

        return;
    }
}
