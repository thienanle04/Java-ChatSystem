/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Nghiax
 */
public class sendRandomPasswordToEmail {
    private static final String FROM_EMAIL = "javamailtest22294@gmail.com";
    private static final String PASSWORD = "pkyeurwkmhtbohpw";
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;

    public static void sendEmailWithGeneratedPassword(String recipientEmail, String username)
            throws MessagingException, SQLException {
        // Tạo mật khẩu mới
        String newPassword = generatePassword(8);

        // Nội dung email
        String subject = "Reset Password";
        String body = "Your new password is: " + newPassword;

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
        message.setText(body);

        // Gửi email
        Transport.send(message);

        // Cập nhật mật khẩu mới vào cơ sở dữ liệu
        updatePasswordInDatabase(username, newPassword);

        return;
    }

    private static String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            passwordBuilder.append(characters.charAt(index));
        }

        return passwordBuilder.toString();
    }

    private static void updatePasswordInDatabase(String username, String newPassword) throws SQLException {
        String updateSQL = "UPDATE Users SET password_hash = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chatsystem?zeroDateTimeBehavior=CONVERT_TO_NULL", "JavaChatSystem",
                "javachatsystem");
                PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected <= 0) {
                throw new SQLException("Failed to update password for user: " + username);
            }
        }
    }

    public static void main(String[] args) {

    }
}
