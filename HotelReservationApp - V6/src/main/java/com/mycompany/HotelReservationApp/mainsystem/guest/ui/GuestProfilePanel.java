package com.mycompany.HotelReservationApp.mainsystem.guest.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuestProfilePanel extends JPanel implements ActionListener {

    //labels
    private JLabel lblTitle;
    private JLabel secInfo;
    private JLabel lblName;
    private JLabel lblEmail;
    private JLabel lblOldPassword;
    private JLabel lblPassword;
    private JLabel lblConfirmPassword;

    //fields
    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtOldPassword;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    //buttons
    private JButton btnUpdate;
    private JButton btnChangePassword;

    //scroll
    private JScrollPane scrollPane;

    //panels
    private JPanel titleBar;
    private JPanel formContent;
    private JPanel card;

    public GuestProfilePanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));

        titleBar = new JPanel();
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setLayout(null);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);

        lblTitle = new JLabel("GUEST PROFILE");
        lblTitle.setBounds(15, 8, 400, 34);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titleBar.add(lblTitle);

        formContent = new JPanel(null);
        formContent.setBackground(Color.decode("#F5F5F5"));
        formContent.setPreferredSize(new Dimension(860, 560));

        card = new JPanel();
        card.setBounds(30, 10, 550, 530);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        formContent.add(card);

        secInfo = new JLabel("PROFILE INFORMATION");
        secInfo.setBounds(25, 20, 350, 25);
        secInfo.setFont(new Font("Arial Black", Font.BOLD, 15));
        card.add(secInfo);

        lblName = new JLabel("Full Name");
        lblName.setBounds(25, 60, 150, 25);
        lblName.setFont(new Font("Arial Black", Font.BOLD, 13));
        card.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(25, 88, 480, 40);
        txtName.setFont(new Font("Arial", Font.PLAIN, 14));
        txtName.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        card.add(txtName);

        lblEmail = new JLabel("Email Address");
        lblEmail.setBounds(25, 140, 150, 25);
        lblEmail.setFont(new Font("Arial Black", Font.BOLD, 13));
        card.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(25, 168, 480, 40);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        card.add(txtEmail);

        lblOldPassword = new JLabel("Old Password");
        lblOldPassword.setBounds(25, 220, 150, 25);
        lblOldPassword.setFont(new Font("Arial Black", Font.BOLD, 13));
        card.add(lblOldPassword);

        txtOldPassword = new JPasswordField();
        txtOldPassword.setBounds(25, 248, 480, 40);
        txtOldPassword.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        card.add(txtOldPassword);

        lblPassword = new JLabel("New Password");
        lblPassword.setBounds(25, 300, 150, 25);
        lblPassword.setFont(new Font("Arial Black", Font.BOLD, 13));
        card.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(25, 328, 480, 40);
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        card.add(txtPassword);

        lblConfirmPassword = new JLabel("Confirm New Password");
        lblConfirmPassword.setBounds(25, 380, 220, 25);
        lblConfirmPassword.setFont(new Font("Arial Black", Font.BOLD, 13));
        card.add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(25, 408, 480, 40);
        txtConfirmPassword.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        card.add(txtConfirmPassword);

        btnUpdate = new JButton("UPDATE PROFILE");
        btnUpdate.setBounds(25, 465, 230, 45);
        btnUpdate.setBackground(Color.decode("#222222"));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnUpdate.setBorderPainted(false);
        btnUpdate.setFocusPainted(false);
        card.add(btnUpdate);

        btnChangePassword = new JButton("CHANGE PASSWORD");
        btnChangePassword.setBounds(265, 465, 230, 45);
        btnChangePassword.setBackground(Color.decode("#222222"));
        btnChangePassword.setForeground(Color.WHITE);
        btnChangePassword.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnChangePassword.setBorderPainted(false);
        btnChangePassword.setFocusPainted(false);
        card.add(btnChangePassword);

        scrollPane = new JScrollPane(formContent);
        scrollPane.setBounds(30, 80, 880, 460);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.decode("#F5F5F5"));
        scrollPane.getViewport().setBackground(Color.decode("#F5F5F5"));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        JScrollBar vBar = scrollPane.getVerticalScrollBar();
        vBar.setBackground(Color.decode("#222222"));
        vBar.setForeground(Color.decode("#555555"));
        vBar.setPreferredSize(new Dimension(8, 0));
        vBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.decode("#555555");
                this.trackColor = Color.decode("#222222");
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(0, 0));
                return btn;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(0, 0));
                return btn;
            }
        });

        add(titleBar);
        add(scrollPane);

        btnUpdate.addActionListener(this);
        btnChangePassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUpdate) {
            String name  = txtName.getText().trim();
            String email = txtEmail.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Full Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Email Address cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Error: Please enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to update your profile?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.out.println("Profile update confirmed. Proceeding with GuestDAO update...");
            }
        }

        if (e.getSource() == btnChangePassword) {
            String oldPassword     = new String(txtOldPassword.getPassword()).trim();
            String newPassword     = new String(txtPassword.getPassword()).trim();
            String confirmPassword = new String(txtConfirmPassword.getPassword()).trim();

            if (oldPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Old Password cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: New Password cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.length() < 6) {
                JOptionPane.showMessageDialog(this, "Error: New Password must be at least 6 characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Please confirm your new password.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Error: New Password and Confirm Password do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (oldPassword.equals(newPassword)) {
                JOptionPane.showMessageDialog(this,"Error: New Password must be different from Old Password.","Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to change your password?","Confirm Password Change", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.out.println("Password change confirmed. Proceeding with GuestDAO update...");
            }
        }
    }
}
