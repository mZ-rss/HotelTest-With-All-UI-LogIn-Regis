package com.mycompany.HotelReservationApp.mainsystem.guest.ui;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;

public class SearchRoomsPanel extends JPanel implements ActionListener {

    //labels
    private JLabel lblTitle;
    private JLabel lblDate;
    private JLabel lblType;
    private JLabel lblPax;

    //fields
    private JTextField txtDate;
    private JTextField txtPax;

    //combo boxes
    private JComboBox<String> cbType;

    //buttons
    private JButton btnSearch;

    //table
    private JTable roomTable;
    private DefaultTableModel model;
    private String[] columns;

    //scrollpane
    private JScrollPane scrollPane;

    //panels
    private JPanel titleBar;
    private JPanel filterBar;

    public SearchRoomsPanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));

        titleBar = new JPanel(null);
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);

        lblTitle = new JLabel("SEARCH ROOMS");
        lblTitle.setBounds(15, 8, 400, 34);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titleBar.add(lblTitle);

        filterBar = new JPanel(null);
        filterBar.setBounds(30, 70, 880, 65);
        filterBar.setBackground(Color.decode("#333333"));
        add(filterBar);

        lblDate = new JLabel("Check-in Date:");
        lblDate.setBounds(15, 8, 200, 18);
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(15, 28, 140, 28);
        filterBar.add(txtDate);

        lblType = new JLabel("Room Type:");
        lblType.setBounds(175, 8, 100, 18);
        lblType.setForeground(Color.WHITE);
        lblType.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblType);

        cbType = new JComboBox<>(new String[]{"- Select -", "Single Standard", "Double Standard", "Double Deluxe", "Suite Deluxe"});
        cbType.setBounds(175, 28, 160, 28);
        filterBar.add(cbType);

        lblPax = new JLabel("Pax/Guests:");
        lblPax.setBounds(355, 8, 100, 18);
        lblPax.setForeground(Color.WHITE);
        lblPax.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblPax);

        txtPax = new JTextField();
        txtPax.setBounds(355, 28, 90, 28);
        filterBar.add(txtPax);

        btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(465, 25, 110, 32);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        filterBar.add(btnSearch);

        columns = new String[]{"Room ID", "Floor", "Type", "Beds", "Max Pax", "Price/Night"};
        model = new DefaultTableModel(columns, 0);
        roomTable = new JTable(model);
        roomTable.setFont(new Font("Arial", Font.PLAIN, 13));
        roomTable.setRowHeight(28);
        roomTable.getTableHeader().setBackground(Color.decode("#222222"));
        roomTable.getTableHeader().setForeground(Color.WHITE);
        roomTable.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 12));

        scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(30, 140, 880, 360);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        add(scrollPane);

        btnSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            String dateText = txtDate.getText().trim();

            if (dateText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Please enter a check-in date.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (dateText.length() != 10) {
                JOptionPane.showMessageDialog(this, "Error: Invalid date format. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (dateText.charAt(4) != '-' || dateText.charAt(7) != '-') {
                JOptionPane.showMessageDialog(this, "Error: Invalid date format. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate searchDate;
            try {
                searchDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Error: Invalid date. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (searchDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "Error: Check-in date cannot be in the past.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cbType.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Error: Please select a room type.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String paxText = txtPax.getText().trim();
            if (paxText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Please specify the number of guests.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int guests;
            try {
                guests = Integer.parseInt(paxText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Please enter a valid number for Pax/Guests.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (guests <= 0) {
                JOptionPane.showMessageDialog(this, "Error: Number of guests must be at least 1.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (guests > 10) {
                JOptionPane.showMessageDialog(this, "Error: Maximum room capacity is 10 guests.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            System.out.println("All fields valid. Proceeding with RoomDAO search...");
        }
    }
}
