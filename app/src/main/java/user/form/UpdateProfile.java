package user.form;

import java.time.LocalDate;
import java.time.YearMonth;
import io.socket.client.Ack;
import user.model.Model_Message;
import user.event.EventMessage;

import user.event.EventUpdateInfo;
import user.event.PublicEvent;
import user.model.Model_User_Profile;
import user.model.Model_Reset_Password;
import user.service.Service;

@SuppressWarnings("unused")
public class UpdateProfile extends javax.swing.JDialog {
    private Model_User_Profile user;

    public UpdateProfile(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        init();
        this.setLocationRelativeTo(parent);
    }

    private void init () {

        PublicEvent.getInstance().addEventUpdateInfo(new EventUpdateInfo() {
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
                                        PublicEvent.getInstance().getEventMain().showNotification("Update Profile Success");
                                    } else {
                                        PublicEvent.getInstance().getEventMain().showNotification("Update Profile Failed");
                                    }
                                }
                                PublicEvent.getInstance().getEventMain().showLoading(false);
                            }
                        });
                    }
                }).start();
            }
    
            @Override
            public void updatePassword(Model_Reset_Password password, EventMessage message) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PublicEvent.getInstance().getEventMain().showLoading(true);
                        Service.getInstance().getClient().emit("update_password", password.toJsonObject(), new Ack() {
                            @Override
                            public void call(Object... os) {
                                if (os.length > 0) {
                                    boolean action = (Boolean) os[0];
                                    if (action) {
                                        PublicEvent.getInstance().getEventMain().showNotification("Update Password Success");
                                    } else {
                                        PublicEvent.getInstance().getEventMain().showNotification("Update Password Failed");
                                    }
                                }
                                PublicEvent.getInstance().getEventMain().showLoading(false);
                            }
                        });
                    }
                }).start();
            }
        });

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        updateProfile = new javax.swing.JPanel();
        content = new javax.swing.JPanel();
        genderPane = new javax.swing.JPanel();
        gender = new javax.swing.JLabel();
        genderOption = new javax.swing.JComboBox<>();
        usernamePane = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        newUsername = new javax.swing.JTextField();
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
        bottom = new javax.swing.JPanel();
        Save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        updatePassword = new javax.swing.JPanel();
        bottomPassword = new javax.swing.JPanel();
        savePasswordChange = new javax.swing.JButton();
        cancelPassword = new javax.swing.JButton();
        passwordContent = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        currentPassword = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        newPasswordInput = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Profile");
        setMinimumSize(new java.awt.Dimension(400, 423));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        updateProfile.setLayout(new java.awt.BorderLayout());

        gender.setText("Gender");

        genderOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an option", "Male", "Female", "Other" }));
        genderOption.setToolTipText("");

        javax.swing.GroupLayout genderPaneLayout = new javax.swing.GroupLayout(genderPane);
        genderPane.setLayout(genderPaneLayout);
        genderPaneLayout.setHorizontalGroup(
            genderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genderPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(emailPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(genderPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(addressPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dobPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(usernamePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(usernamePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        updateProfile.add(content, java.awt.BorderLayout.PAGE_START);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
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

        updateProfile.add(bottom, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Profile", updateProfile);

        updatePassword.setLayout(new java.awt.BorderLayout());

        savePasswordChange.setText("Save");
        savePasswordChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePasswordChangeActionPerformed(evt);
            }
        });

        cancelPassword.setText("Cancel");
        cancelPassword.setSelected(true);
        cancelPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPasswordLayout = new javax.swing.GroupLayout(bottomPassword);
        bottomPassword.setLayout(bottomPasswordLayout);
        bottomPasswordLayout.setHorizontalGroup(
            bottomPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPasswordLayout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addComponent(cancelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savePasswordChange)
                .addGap(20, 20, 20))
        );
        bottomPasswordLayout.setVerticalGroup(
            bottomPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPasswordLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(bottomPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savePasswordChange)
                    .addComponent(cancelPassword))
                .addGap(5, 5, 5))
        );

        updatePassword.add(bottomPassword, java.awt.BorderLayout.SOUTH);

        jLabel1.setText("Password");

        currentPassword.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(currentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jLabel2.setText("New password");

        newPasswordInput.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(newPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(newPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout passwordContentLayout = new javax.swing.GroupLayout(passwordContent);
        passwordContent.setLayout(passwordContentLayout);
        passwordContentLayout.setHorizontalGroup(
            passwordContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        passwordContentLayout.setVerticalGroup(
            passwordContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordContentLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );

        updatePassword.add(passwordContent, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Password", updatePassword);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearActionPerformed
        populateDays();
    }//GEN-LAST:event_yearActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        dispose();
    }//GEN-LAST:event_LogoutActionPerformed

    private void savePasswordChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePasswordChangeActionPerformed
        String curPass = String.valueOf(currentPassword.getPassword());
        String newPass = String.valueOf(newPasswordInput.getPassword());
        if (curPass.equals("") || newPass.equals("")) {
            PublicEvent.getInstance().getEventMain().showNotification("Please fill in all fields");
            return;
        }
        Model_Reset_Password password = new Model_Reset_Password(Service.getInstance().getUser().getUserName(), curPass, newPass);
        PublicEvent.getInstance().getEventUpdateInfo().updatePassword(password, new EventMessage() {
            @Override
            public void callMessage(Model_Message message) {
                PublicEvent.getInstance().getEventMain().showNotification(message.getMessage());
            }
        });
        dispose();
    }//GEN-LAST:event_savePasswordChangeActionPerformed

    private void cancelPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelPasswordActionPerformed
        dispose();
    }//GEN-LAST:event_cancelPasswordActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SaveActionPerformed
        String new_username = newUsername.getText();
        String new_email = newEmail.getText();
        String new_address = addressInput.getText();
        String new_gender = genderOption.getSelectedItem().toString();
        LocalDate dob = LocalDate.of(Integer.parseInt(year.getSelectedItem().toString()), Integer.parseInt(month.getSelectedItem().toString()), Integer.parseInt(day.getSelectedItem().toString()));
        Model_User_Profile newUserInfo = new Model_User_Profile(user.getUserID(), new_username, new_email, new_address, new_gender, dob);
        PublicEvent.getInstance().getEventUpdateInfo().updateProfile(newUserInfo);
        dispose();
    }// GEN-LAST:event_SaveActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CancelActionPerformed
        dispose();
    }// GEN-LAST:event_CancelActionPerformed

    private void currentPasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_currentPasswordActionPerformed
    }// GEN-LAST:event_currentPasswordActionPerformed

    private void newPasswordInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newPasswordInputActionPerformed
    }// GEN-LAST:event_newPasswordInputActionPerformed

    private void newEmailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newEmailActionPerformed
    }// GEN-LAST:event_newEmailActionPerformed

    private void addressInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addressInputActionPerformed
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
    private javax.swing.JPanel bottomPassword;
    private javax.swing.JButton cancelPassword;
    private javax.swing.JPanel content;
    private javax.swing.JPasswordField currentPassword;
    private javax.swing.JComboBox<String> day;
    private javax.swing.JLabel dob;
    private javax.swing.JPanel dobPane;
    private javax.swing.JLabel email;
    private javax.swing.JPanel emailPane;
    private javax.swing.JLabel gender;
    private javax.swing.JComboBox<String> genderOption;
    private javax.swing.JPanel genderPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JTextField newEmail;
    private javax.swing.JPasswordField newPasswordInput;
    private javax.swing.JTextField newUsername;
    private javax.swing.JPanel passwordContent;
    private javax.swing.JButton savePasswordChange;
    private javax.swing.JPanel updatePassword;
    private javax.swing.JPanel updateProfile;
    private javax.swing.JLabel username;
    private javax.swing.JPanel usernamePane;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
