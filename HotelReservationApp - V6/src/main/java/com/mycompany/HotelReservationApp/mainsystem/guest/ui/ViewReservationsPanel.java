package com.mycompany.HotelReservationApp.mainsystem.guest.ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ViewReservationsPanel - Guest Reservation History
 * Displays all guest reservations with filtering and details view
 * Features: Search, filter by status, view full details
 */
public class ViewReservationsPanel extends JPanel implements ActionListener {
    
    private JTable resTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JPanel titleBar, filterBar;
    private JLabel lblTitle;
    private JComboBox<String> cmbStatus;
    private JTextField txtSearchRoom;
    private JButton btnSearch, btnClear, btnViewDetails;
    private String[] columns;
    
    public ViewReservationsPanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));
        createComponents();
        loadReservations();
    }
    
    private void createComponents() {
        // Title Bar
        titleBar = new JPanel(null);
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);
        
        lblTitle = new JLabel("MY RESERVATIONS");
        lblTitle.setBounds(15, 8, 400, 34);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titleBar.add(lblTitle);
        
        // Filter Bar
        filterBar = new JPanel(null);
        filterBar.setBounds(30, 80, 880, 60);
        filterBar.setBackground(Color.decode("#333333"));
        add(filterBar);
        
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(15, 12, 80, 18);
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblStatus);
        
        cmbStatus = new JComboBox<>(new String[]{
            "All", "Confirmed", "Checked-In", "Checked-Out", "Cancelled"
        });
        cmbStatus.setBounds(15, 32, 150, 25);
        filterBar.add(cmbStatus);
        
        JLabel lblSearch = new JLabel("Room Number:");
        lblSearch.setBounds(180, 12, 120, 18);
        lblSearch.setForeground(Color.WHITE);
        lblSearch.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblSearch);
        
        txtSearchRoom = new JTextField();
        txtSearchRoom.setBounds(180, 32, 120, 25);
        filterBar.add(txtSearchRoom);
        
        btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(315, 32, 80, 25);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFont(new Font("Arial Black", Font.BOLD, 11));
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);
        filterBar.add(btnSearch);
        
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(405, 32, 80, 25);
        btnClear.setBackground(new Color(255, 193, 7));
        btnClear.setForeground(Color.BLACK);
        btnClear.setFont(new Font("Arial Black", Font.BOLD, 11));
        btnClear.setBorderPainted(false);
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(this);
        filterBar.add(btnClear);
        
        // Table
        columns = new String[]{
            "Res ID", "Room #", "Check-In", "Check-Out", "Nights", "Status", "Total"
        };
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        resTable = new JTable(model);
        resTable.setFont(new Font("Arial", Font.PLAIN, 12));
        resTable.setRowHeight(25);
        resTable.getTableHeader().setBackground(Color.decode("#222222"));
        resTable.getTableHeader().setForeground(Color.WHITE);
        resTable.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 12));
        resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        scrollPane = new JScrollPane(resTable);
        scrollPane.setBounds(30, 150, 880, 320);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        add(scrollPane);
        
        // View Details Button
        btnViewDetails = new JButton("VIEW DETAILS");
        btnViewDetails.setBounds(750, 485, 160, 35);
        btnViewDetails.setBackground(new Color(33, 150, 243));
        btnViewDetails.setForeground(Color.WHITE);
        btnViewDetails.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnViewDetails.setBorderPainted(false);
        btnViewDetails.setFocusPainted(false);
        btnViewDetails.addActionListener(this);
        add(btnViewDetails);
        
        btnSearch.addActionListener(this);
    }
    
    private void loadReservations() {
        // TODO: Call GuestDAO to fetch guest's reservations
        // Sample data
        model.addRow(new Object[]{1001, "101", "2025-06-15", "2025-06-18", 3, "Confirmed", "PHP 10,500.00"});
        model.addRow(new Object[]{1002, "205", "2025-07-01", "2025-07-05", 4, "Checked-Out", "PHP 28,000.00"});
        model.addRow(new Object[]{1003, "302", "2025-08-10", "2025-08-12", 2, "Checked-In", "PHP 15,000.00"});
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            handleSearch();
        } else if (e.getSource() == btnClear) {
            clearFilters();
        } else if (e.getSource() == btnViewDetails) {
            handleViewDetails();
        }
    }
    
    private void handleSearch() {
        String status = (String) cmbStatus.getSelectedItem();
        String roomNumber = txtSearchRoom.getText().trim();
        
        // TODO: Filter results based on status and room number
        System.out.println("Filter by Status: " + status + ", Room: " + roomNumber);
    }
    
    private void clearFilters() {
        cmbStatus.setSelectedIndex(0);
        txtSearchRoom.setText("");
        loadReservations();
    }
    
    private void handleViewDetails() {
        int row = resTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a reservation to view details.",
                "Selection Required", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int resId = (int) model.getValueAt(row, 0);
        String roomNum = (String) model.getValueAt(row, 1);
        String checkIn = (String) model.getValueAt(row, 2);
        String checkOut = (String) model.getValueAt(row, 3);
        String nights = (String) model.getValueAt(row, 4);
        String status = (String) model.getValueAt(row, 5);
        String total = (String) model.getValueAt(row, 6);
        
        String details = "Reservation Details:\n\n" +
            "Reservation ID: " + resId + "\n" +
            "Room Number: " + roomNum + "\n" +
            "Check-in: " + checkIn + "\n" +
            "Check-out: " + checkOut + "\n" +
            "Nights: " + nights + "\n" +
            "Status: " + status + "\n" +
            "Total: " + total;
        
        JOptionPane.showMessageDialog(this, details,
            "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
    }
}