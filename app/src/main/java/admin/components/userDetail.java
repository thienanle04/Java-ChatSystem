/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package admin.components;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nghiax
 */
public class userDetail extends javax.swing.JPanel {

    /**
     * Creates new form userDetail
     */
    public userDetail() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        UserDetails = new javax.swing.JTable();
        add_button = new javax.swing.JButton();
        update_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        UserDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Nghia", "test1", "1234", "None", "16-09-2004", "Male", "None", null},
                {"An", "test2", "1234", "None", "None", "Male", "None",  new Boolean(true)}
            },
            new String [] {
                "Name", "Username", "Password", "Address", "Date-of-birth", "Gender", "Email", "Lock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(UserDetails);

        add_button.setText("Add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        update_button.setText("Update");
        update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_buttonActionPerformed(evt);
            }
        });

        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        jButton1.setText("Lock/ Unlock");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete_button)
                    .addComponent(update_button)
                    .addComponent(add_button)
                    .addComponent(jButton1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        // TODO add your handling code here:
        JTextField usernameField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField addressField = new JTextField(10);
        JTextField dobField = new JTextField(10);
        JTextField genderField = new JTextField(10);
        JTextField emailField = new JTextField(10);

        JPanel addUserPanel = new JPanel();
        addUserPanel.setLayout(new GridLayout(6, 2));
        addUserPanel.add(new JLabel("Username:"));
        addUserPanel.add(usernameField);
        addUserPanel.add(new JLabel("Name:"));
        addUserPanel.add(nameField);
        addUserPanel.add(new JLabel("Address:"));
        addUserPanel.add(addressField);
        addUserPanel.add(new JLabel("Date of Birth:"));
        addUserPanel.add(dobField);
        addUserPanel.add(new JLabel("Gender:"));
        addUserPanel.add(genderField);
        addUserPanel.add(new JLabel("Email:"));
        addUserPanel.add(emailField);

        // Hiển thị hộp thoại yêu cầu người dùng nhập thông tin
        int result = JOptionPane.showConfirmDialog(this, addUserPanel, "Add New User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Nếu người dùng nhấn OK, lấy dữ liệu và thêm vào bảng
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            String dob = dobField.getText();
            String gender = genderField.getText();
            String email = emailField.getText();

            // Kiểm tra xem tên người dùng và tên có trống không
            if (!username.isEmpty() && !name.isEmpty()) {
                // Thêm người dùng vào bảng
                DefaultTableModel model = (DefaultTableModel) UserDetails.getModel();
                model.addRow(new Object[]{username, name, address, dob, gender, email});

                // Xóa các trường nhập liệu sau khi thêm
                usernameField.setText("");
                nameField.setText("");
                addressField.setText("");
                dobField.setText("");
                genderField.setText("");
                emailField.setText("");

                JOptionPane.showMessageDialog(this, "User added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Username and Name are required.");
            }
        }
    }//GEN-LAST:event_add_buttonActionPerformed

    private void update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) UserDetails.getModel();

        // Lấy hàng được chọn
        int selectedRow = UserDetails.getSelectedRow();

        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow >= 0) {
            // Lấy thông tin hiện tại từ hàng đã chọn
            String currentUsername = model.getValueAt(selectedRow, 0).toString();
            String currentName = model.getValueAt(selectedRow, 1).toString();
            String currentAddress = model.getValueAt(selectedRow, 2).toString();
            String currentDOB = model.getValueAt(selectedRow, 3).toString();
            String currentGender = model.getValueAt(selectedRow, 4).toString();
            String currentEmail = model.getValueAt(selectedRow, 5).toString();

            // Tạo các trường để nhập thông tin mới
            JTextField usernameField = new JTextField(currentUsername);
            JTextField nameField = new JTextField(currentName);
            JTextField addressField = new JTextField(currentAddress);
            JTextField dobField = new JTextField(currentDOB);
            JTextField genderField = new JTextField(currentGender);
            JTextField emailField = new JTextField(currentEmail);

            // Tạo panel và thêm các trường vào panel
            JPanel updatePanel = new JPanel(new GridLayout(6, 2));
            updatePanel.add(new JLabel("Username:"));
            updatePanel.add(usernameField);
            updatePanel.add(new JLabel("Name:"));
            updatePanel.add(nameField);
            updatePanel.add(new JLabel("Address:"));
            updatePanel.add(addressField);
            updatePanel.add(new JLabel("Date of Birth:"));
            updatePanel.add(dobField);
            updatePanel.add(new JLabel("Gender:"));
            updatePanel.add(genderField);
            updatePanel.add(new JLabel("Email:"));
            updatePanel.add(emailField);

            // Hiển thị hộp thoại để nhập thông tin cập nhật
            int result = JOptionPane.showConfirmDialog(this, updatePanel, "Update User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Lấy dữ liệu mới từ các trường
                String newUsername = usernameField.getText();
                String newName = nameField.getText();
                String newAddress = addressField.getText();
                String newDOB = dobField.getText();
                String newGender = genderField.getText();
                String newEmail = emailField.getText();

                // Cập nhật dữ liệu trong mô hình bảng
                model.setValueAt(newUsername, selectedRow, 0);
                model.setValueAt(newName, selectedRow, 1);
                model.setValueAt(newAddress, selectedRow, 2);
                model.setValueAt(newDOB, selectedRow, 3);
                model.setValueAt(newGender, selectedRow, 4);
                model.setValueAt(newEmail, selectedRow, 5);

                JOptionPane.showMessageDialog(this, "User updated successfully!");
            }
        } else {
            // Thông báo nếu không có hàng nào được chọn
            JOptionPane.showMessageDialog(this, "Please select a user to update.");
        }
    }//GEN-LAST:event_update_buttonActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) UserDetails.getModel();

        // Lấy hàng được chọn
        int selectedRow = UserDetails.getSelectedRow();

        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow >= 0) {
            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa hàng khỏi bảng
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "User deleted successfully!");
            }
        } else {
            // Thông báo nếu không có hàng nào được chọn
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable UserDetails;
    private javax.swing.JButton add_button;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables
}
