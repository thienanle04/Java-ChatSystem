package user;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import admin.AdminHomePage;
import admin.adminHomePagePanel;
import io.socket.client.Ack;
import user.model.Model_Group_Chat;
import user.event.EventMain;
import user.event.PublicEvent;
import user.service.Service;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user.form.UpdateProfile;
import user.model.Model_User_Profile;

public class Main extends javax.swing.JFrame {
    UpdateProfile updateProfile;
    private admin.adminHomePagePanel adminApp;

    public Main() {
        initComponents();
        init();
    }

    private void init() {
        setIconImage(new ImageIcon(getClass().getResource("/user/icon.png")).getImage());
        updateProfile = new UpdateProfile(this);
        updateProfile.setVisible(false);
        login.setVisible(true);
        loading.setVisible(false);
        home.setVisible(false);
        initEvent();
        Service.getInstance().startServer();
    }

    private void initEvent() {
        PublicEvent.getInstance().addEventMain(new EventMain() {
            @Override
            public void showLoading(boolean show) {
                loading.setVisible(show);
            }

            @Override
            public void initChat() {
                home.setVisible(true);
                login.setVisible(false);
                Service.getInstance().getClient().emit("list_chat", Service.getInstance().getUser().getUserID());
                Service.getInstance().getClient().emit("get_all_chats", Service.getInstance().getUser().getUserID());
                updateProfile.setUser(Service.getInstance().getUser());
            }

            @Override
            public void initAdminApp() {
                home.setVisible(false);
                login.setVisible(false);
                adminApp.setVisible(true);
            }

            @Override
            public void selectChat(Model_Group_Chat chat) {
                home.setChat(chat);
            }

            @Override
            public void updateChat(Model_Group_Chat chat) {
                home.updateChat(chat);
            }

            @Override
            public void editProfile() {
                updateProfile.setUser(Service.getInstance().getUser());
                updateProfile.setVisible(true);
            }

            @Override
            public void updateProfile(Model_User_Profile newUserInfo) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PublicEvent.getInstance().getEventMain().showLoading(true);
                        Service.getInstance().getClient().emit("update_profile", newUserInfo.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    if (action) {
                                        Service.getInstance().setUser(newUserInfo);
                                        showNotificationDialog("Update Profile Success");
                                    } else {
                                        showNotificationDialog("Update Profile Failed");
                                    }
                                }
                                PublicEvent.getInstance().getEventMain().showLoading(false);
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void showNotification(String message) {
                Main.this.showNotificationDialog(message);
            }
        });
    }

    public void showNotificationDialog(String message) {
        // Create a JDialog
        JDialog dialog = new JDialog(this, "Notification", false);
        dialog.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dialog.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Add OK button
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action to close dialog when OK is pressed
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Close the dialog
            }
        });

        dialog.setSize(300, 115);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this); // Center dialog
        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        border = new javax.swing.JPanel();
        background = new javax.swing.JPanel();
        body = new javax.swing.JLayeredPane();
        loading = new user.form.Loading();
        login = new user.form.Login();
        home = new user.form.Home();
        adminApp = new adminHomePagePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        border.setBackground(new java.awt.Color(229, 229, 229));

        background.setBackground(new java.awt.Color(255, 255, 255));

        body.setLayout(new java.awt.CardLayout());
        body.add(loading, "card5");
        body.add(login, "card4");
        body.add(home, "card2");
        body.add(adminApp, "adminCard");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
                                .addContainerGap()));
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                                .addContainerGap()));

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
                borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(borderLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1)));
        borderLayout.setVerticalGroup(
                borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(borderLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("user.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLayeredPane body;
    private javax.swing.JPanel border;
    private user.form.Home home;
    private user.form.Loading loading;
    private user.form.Login login;
    // End of variables declaration//GEN-END:variables
}
