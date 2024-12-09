package user.form;

import java.time.LocalDate;
import java.time.YearMonth;

import user.event.PublicEvent;
import user.model.Model_User_Profile;

public class UpdateProfile extends javax.swing.JDialog {
    private Model_User_Profile user;

    public UpdateProfile(java.awt.Frame parent) { // , Model_User_Profile profile) {
        super(parent, true);
        initComponents();
        init();
        this.setLocationRelativeTo(parent);
    }

    private void init () {
        LocalDate now = LocalDate.now();
        for (int i = 0; i <= 100; i++) {
            year.addItem(String.valueOf(now.getYear() - i));
        }

        for (int i = 1; i <= 12; i++) {
            month.addItem(String.valueOf(i));
        }

        month.setSelectedItem(String.valueOf(now.getMonthValue()));
        year.setSelectedItem(String.valueOf(now.getYear()));
        populateDays();
        day.setSelectedItem(String.valueOf(now.getDayOfMonth()));
    }

    public void setUser(Model_User_Profile user) {
        this.user = user;
        newUsername.setText(user.getUserName());
        newEmail.setText(user.getEmail());
        currentPassword.setText("");
        newPasswordInput.setText("");
        addressInput.setText(user.getAddress());
        
        if (user.getGender() != null) {
            genderOption.setSelectedItem(user.getGender());
        }

        if (user.getDob() != null) {
            LocalDate dob = user.getLocalDateDob();
            month.setSelectedItem(String.valueOf(dob.getMonthValue()));
            year.setSelectedItem(String.valueOf(dob.getYear()));
            day.setSelectedItem(String.valueOf(dob.getDayOfMonth()));
        } else {

        }

    }

    private void populateDays() {
        // Check if the month, year, and day JComboBox are selected
        if (month.getSelectedItem() == null || year.getSelectedItem() == null) {
            return; // Return if month or year is not selected
        }
    
        // Get the selected month and year
        int selectedYear = Integer.parseInt(year.getSelectedItem().toString());
        int selectedMonth = Integer.parseInt(month.getSelectedItem().toString());
    
        // Get the number of days in the selected month and year
        int daysInMonth = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth();
    
        // Get the currently selected day (if any)
        int selectedDay = 1;  // Default to day 1
        if (day.getSelectedItem() != null) {
            selectedDay = Integer.parseInt(day.getSelectedItem().toString());
        }
    
        // Clear the current day items
        day.removeAllItems();
    
        // Add the days for the selected month
        for (int i = 1; i <= daysInMonth; i++) {
            day.addItem(String.valueOf(i));
        }
    
        // Reselect the day, if possible
        if (selectedDay <= daysInMonth) {
            day.setSelectedItem(String.valueOf(selectedDay));
        } else {
            day.setSelectedItem(null); // If the previously selected day is invalid, set it to null
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bottom = new javax.swing.JPanel();
        Save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        passwordPane = new javax.swing.JPanel();
        password = new javax.swing.JLabel();
        currentPassword = new javax.swing.JTextField();
        genderPane = new javax.swing.JPanel();
        gender = new javax.swing.JLabel();
        genderOption = new javax.swing.JComboBox<>();
        usernamePane = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        newUsername = new javax.swing.JTextField();
        newPasswordPane = new javax.swing.JPanel();
        newPassword = new javax.swing.JLabel();
        newPasswordInput = new javax.swing.JTextField();
        emailPane = new javax.swing.JPanel();
        email = new javax.swing.JLabel();
        newEmail = new javax.swing.JTextField();
        addressPane = new javax.swing.JPanel();
        address = new javax.swing.JLabel();
        addressInput = new javax.swing.JTextField();
        dobPane = new javax.swing.JPanel();
        dob = new javax.swing.JLabel();
        day = new javax.swing.JComboBox<>();
        month = new javax.swing.JComboBox<>();
        year = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Profile");
        setMinimumSize(new java.awt.Dimension(400, 423));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.setSelected(true);
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        Logout.setText("Log out");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Logout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(Cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Save)
                .addGap(20, 20, 20))
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save)
                    .addComponent(Cancel)
                    .addComponent(Logout))
                .addGap(5, 5, 5))
        );

        getContentPane().add(bottom, java.awt.BorderLayout.SOUTH);

        password.setText("Password");

        currentPassword.setText("Enter your current password");
        currentPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout passwordPaneLayout = new javax.swing.GroupLayout(passwordPane);
        passwordPane.setLayout(passwordPaneLayout);
        passwordPaneLayout.setHorizontalGroup(
            passwordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        passwordPaneLayout.setVerticalGroup(
            passwordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(passwordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password)
                    .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        gender.setText("Gender");

        genderOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an option", "Male", "Female", "Other", " " }));
        genderOption.setToolTipText("");

        javax.swing.GroupLayout genderPaneLayout = new javax.swing.GroupLayout(genderPane);
        genderPane.setLayout(genderPaneLayout);
        genderPaneLayout.setHorizontalGroup(
            genderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genderPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(genderOption, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );
        genderPaneLayout.setVerticalGroup(
            genderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genderPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(genderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gender)
                    .addComponent(genderOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        username.setText("Username");

        newUsername.setText("Enter a new username");

        javax.swing.GroupLayout usernamePaneLayout = new javax.swing.GroupLayout(usernamePane);
        usernamePane.setLayout(usernamePaneLayout);
        usernamePaneLayout.setHorizontalGroup(
            usernamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usernamePaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        usernamePaneLayout.setVerticalGroup(
            usernamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usernamePaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(usernamePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username)
                    .addComponent(newUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        newPassword.setText("New Password");

        newPasswordInput.setText("Enter a new password");
        newPasswordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newPasswordPaneLayout = new javax.swing.GroupLayout(newPasswordPane);
        newPasswordPane.setLayout(newPasswordPaneLayout);
        newPasswordPaneLayout.setHorizontalGroup(
            newPasswordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPasswordPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(newPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        newPasswordPaneLayout.setVerticalGroup(
            newPasswordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newPasswordPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(newPasswordPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPassword)
                    .addComponent(newPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        email.setText("Email");

        newEmail.setText("Enter a new email");
        newEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout emailPaneLayout = new javax.swing.GroupLayout(emailPane);
        emailPane.setLayout(emailPaneLayout);
        emailPaneLayout.setHorizontalGroup(
            emailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(newEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        emailPaneLayout.setVerticalGroup(
            emailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(emailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email)
                    .addComponent(newEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        address.setText("Address");

        addressInput.setText("Enter a new address");
        addressInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addressPaneLayout = new javax.swing.GroupLayout(addressPane);
        addressPane.setLayout(addressPaneLayout);
        addressPaneLayout.setHorizontalGroup(
            addressPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(address)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addressInput, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        addressPaneLayout.setVerticalGroup(
            addressPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(addressPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address)
                    .addComponent(addressInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        dob.setText("Date of birth");

        day.setToolTipText("");

        month.setToolTipText("");
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        year.setToolTipText("");
        year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dobPaneLayout = new javax.swing.GroupLayout(dobPane);
        dobPane.setLayout(dobPaneLayout);
        dobPaneLayout.setHorizontalGroup(
            dobPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dobPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(dob)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        dobPaneLayout.setVerticalGroup(
            dobPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dobPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(dobPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dob)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(passwordPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(newPasswordPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(emailPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(genderPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(addressPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dobPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(usernamePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(usernamePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(passwordPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(newPasswordPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(genderPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(dobPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(emailPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(addressPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(content, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearActionPerformed
        populateDays();
    }//GEN-LAST:event_yearActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        dispose();
    }//GEN-LAST:event_LogoutActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SaveActionPerformed
        String new_username = newUsername.getText();
        String new_email = newEmail.getText();
        String new_address = addressInput.getText();
        String new_gender = genderOption.getSelectedItem().toString();
        LocalDate dob = LocalDate.of(Integer.parseInt(year.getSelectedItem().toString()), Integer.parseInt(month.getSelectedItem().toString()), Integer.parseInt(day.getSelectedItem().toString()));
        Model_User_Profile newUserInfo = new Model_User_Profile(user.getUserID(), new_username, new_email, new_address, new_gender, dob);
        PublicEvent.getInstance().getEventMain().updateProfile(newUserInfo);
        dispose();
    }// GEN-LAST:event_SaveActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CancelActionPerformed
        dispose();
    }// GEN-LAST:event_CancelActionPerformed

    private void currentPasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_currentPasswordActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_currentPasswordActionPerformed

    private void newPasswordInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newPasswordInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_newPasswordInputActionPerformed

    private void newEmailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newEmailActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_newEmailActionPerformed

    private void addressInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addressInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_addressInputActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_monthActionPerformed
        populateDays();
    }// GEN-LAST:event_monthActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Logout;
    private javax.swing.JButton Save;
    private javax.swing.JLabel address;
    private javax.swing.JTextField addressInput;
    private javax.swing.JPanel addressPane;
    private javax.swing.JPanel bottom;
    private javax.swing.JPanel content;
    private javax.swing.JTextField currentPassword;
    private javax.swing.JComboBox<String> day;
    private javax.swing.JLabel dob;
    private javax.swing.JPanel dobPane;
    private javax.swing.JLabel email;
    private javax.swing.JPanel emailPane;
    private javax.swing.JLabel gender;
    private javax.swing.JComboBox<String> genderOption;
    private javax.swing.JPanel genderPane;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JTextField newEmail;
    private javax.swing.JLabel newPassword;
    private javax.swing.JTextField newPasswordInput;
    private javax.swing.JPanel newPasswordPane;
    private javax.swing.JTextField newUsername;
    private javax.swing.JLabel password;
    private javax.swing.JPanel passwordPane;
    private javax.swing.JLabel username;
    private javax.swing.JPanel usernamePane;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
