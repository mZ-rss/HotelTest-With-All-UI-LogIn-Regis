package com.mycompany.HotelReservationApp.mainsystem.guest.ui;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;

/**
 * SearchRoomsPanel - Room Search & Availability
 * Comprehensive room filtering with real-time availability checking
 * Features: Date validation, price range filtering, room selection
 */
public class SearchRoomsPanel extends JPanel implements ActionListener {
    
    private JLabel lblTitle;
    private JLabel lblCheckInDate, lblCheckOutDate, lblRoomType, lblPax, lblPriceRange;
    
    private JTextField txtCheckInDate, txtCheckOutDate, txtPax, txtPriceMin, txtPriceMax;
    private JComboBox<String> cbRoomType;
    
    private JButton btnSearch, btnClear, btnSelectRoom;
    private JTable roomTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    
    private JPanel titleBar, filterBar;
    private String[] columns;
    
    // Selected room data
    private int selectedRoomId = -1;
    private String selectedRoomNumber = "";
    
    public SearchRoomsPanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));
        createComponents();
    }
    
    private void createComponents() {
        // Title Bar
        titleBar = new JPanel(null);
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);
        
        lblTitle = new JLabel("SEARCH AVAILABLE ROOMS");
        lblTitle.setBounds(15, 8, 400, 34);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titleBar.add(lblTitle);
        
        // Filter Bar
        filterBar = new JPanel(null);
        filterBar.setBounds(30, 80, 880, 120);
        filterBar.setBackground(Color.decode("#333333"));
        filterBar.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        add(filterBar);
        
        // Check-in Date
        lblCheckInDate = new JLabel("Check-in Date (YYYY-MM-DD):");
        lblCheckInDate.setBounds(15, 8, 180, 18);
        lblCheckInDate.setForeground(Color.WHITE);
        lblCheckInDate.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblCheckInDate);
        
        txtCheckInDate = new JTextField();
        txtCheckInDate.setBounds(15, 28, 140, 28);
        filterBar.add(txtCheckInDate);
        
        // Check-out Date
        lblCheckOutDate = new JLabel("Check-out Date (YYYY-MM-DD):");
        lblCheckOutDate.setBounds(170, 8, 200, 18);
        lblCheckOutDate.setForeground(Color.WHITE);
        lblCheckOutDate.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblCheckOutDate);
        
        txtCheckOutDate = new JTextField();
        txtCheckOutDate.setBounds(170, 28, 140, 28);
        filterBar.add(txtCheckOutDate);
        
        // Room Type
        lblRoomType = new JLabel("Room Type:");
        lblRoomType.setBounds(330, 8, 100, 18);
        lblRoomType.setForeground(Color.WHITE);
        lblRoomType.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblRoomType);
        
        cbRoomType = new JComboBox<>(new String[]{
            "- All Types -", "Single Standard", "Double Standard", "Double Deluxe", "Suite Deluxe"
        });
        cbRoomType.setBounds(330, 28, 150, 28);
        filterBar.add(cbRoomType);
        
        // Number of Pax
        lblPax = new JLabel("Max Guests:");
        lblPax.setBounds(500, 8, 100, 18);
        lblPax.setForeground(Color.WHITE);
        lblPax.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblPax);
        
        txtPax = new JTextField();
        txtPax.setBounds(500, 28, 80, 28);
        filterBar.add(txtPax);
        
        // Price Range
        lblPriceRange = new JLabel("Price Range (PHP):");
        lblPriceRange.setBounds(15, 65, 140, 18);
        lblPriceRange.setForeground(Color.WHITE);
        lblPriceRange.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterBar.add(lblPriceRange);
        
        txtPriceMin = new JTextField("0");
        txtPriceMin.setBounds(15, 85, 80, 25);
        filterBar.add(txtPriceMin);
        
        JLabel lblTo = new JLabel("-");
        lblTo.setBounds(100, 85, 20, 25);
        lblTo.setForeground(Color.WHITE);
        lblTo.setFont(new Font("Arial", Font.BOLD, 12));
        filterBar.add(lblTo);
        
        txtPriceMax = new JTextField("100000");
        txtPriceMax.setBounds(125, 85, 80, 25);
        filterBar.add(txtPriceMax);
        
        // Search Button
        btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(220, 85, 100, 25);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFont(new Font("Arial Black", Font.BOLD, 11));
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);
        filterBar.add(btnSearch);
        
        // Clear Button
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(330, 85, 80, 25);
        btnClear.setBackground(Color.decode("#FFC107"));
        btnClear.setForeground(Color.BLACK);
        btnClear.setFont(new Font("Arial Black", Font.BOLD, 11));
        btnClear.setBorderPainted(false);
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(this);
        filterBar.add(btnClear);
        
        // Table
        columns = new String[]{
            "Room ID", "Room #", "Floor", "Type", "Capacity", "Price/Night", "Status"
        };
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        roomTable = new JTable(model);
        roomTable.setFont(new Font("Arial", Font.PLAIN, 12));
        roomTable.setRowHeight(25);
        roomTable.getTableHeader().setBackground(Color.decode("#222222"));
        roomTable.getTableHeader().setForeground(Color.WHITE);
        roomTable.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 12));
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Enable row selection to pass room data
        roomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = roomTable.getSelectedRow();
                if (row >= 0) {
                    selectedRoomId = (int) model.getValueAt(row, 0);
                    selectedRoomNumber = (String) model.getValueAt(row, 1);
                }
            }
        });
        
        scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(30, 215, 880, 310);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        add(scrollPane);
        
        // Select Room Button
        btnSelectRoom = new JButton("PROCEED TO BOOKING");
        btnSelectRoom.setBounds(600, 535, 310, 35);
        btnSelectRoom.setBackground(new Color(76, 175, 80));
        btnSelectRoom.setForeground(Color.WHITE);
        btnSelectRoom.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnSelectRoom.setBorderPainted(false);
        btnSelectRoom.setFocusPainted(false);
        btnSelectRoom.addActionListener(this);
        add(btnSelectRoom);
        
        btnSearch.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            handleSearch();
        } else if (e.getSource() == btnClear) {
            clearFilters();
        } else if (e.getSource() == btnSelectRoom) {
            handleSelectRoom();
        }
    }
    
    private void handleSearch() {
        String checkInDate = txtCheckInDate.getText().trim();
        String checkOutDate = txtCheckOutDate.getText().trim();
        String roomType = (String) cbRoomType.getSelectedItem();
        String paxText = txtPax.getText().trim();
        String priceMinText = txtPriceMin.getText().trim();
        String priceMaxText = txtPriceMax.getText().trim();
        
        // Validation
        if (checkInDate.isEmpty() || checkOutDate.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Error: Please enter both check-in and check-out dates.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Parse and validate check-in date
        LocalDate checkIn;
        try {
            checkIn = LocalDate.parse(checkInDate, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Invalid check-in date format. Use YYYY-MM-DD.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (checkIn.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(this,
                "Error: Check-in date cannot be in the past.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Parse and validate check-out date
        LocalDate checkOut;
        try {
            checkOut = LocalDate.parse(checkOutDate, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Invalid check-out date format. Use YYYY-MM-DD.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!checkOut.isAfter(checkIn)) {
            JOptionPane.showMessageDialog(this,
                "Error: Check-out date must be after check-in date.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate pax
        int maxGuests = 0;
        if (!paxText.isEmpty()) {
            try {
                maxGuests = Integer.parseInt(paxText);
                if (maxGuests < 1 || maxGuests > 10) {
                    JOptionPane.showMessageDialog(this,
                        "Error: Number of guests must be 1-10.",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error: Please enter a valid number for guests.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Validate price range
        double priceMin = 0, priceMax = 999999.99;
        try {
            if (!priceMinText.isEmpty()) priceMin = Double.parseDouble(priceMinText);
            if (!priceMaxText.isEmpty()) priceMax = Double.parseDouble(priceMaxText);
            
            if (priceMin < 0 || priceMax < 0) {
                JOptionPane.showMessageDialog(this,
                    "Error: Price cannot be negative.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (priceMin > priceMax) {
                JOptionPane.showMessageDialog(this,
                    "Error: Minimum price cannot exceed maximum price.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: Please enter valid prices.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // TODO: Call RoomDAO to fetch available rooms
        System.out.println("Search Parameters:");
        System.out.println("  Check-in: " + checkInDate);
        System.out.println("  Check-out: " + checkOutDate);
        System.out.println("  Room Type: " + roomType);
        System.out.println("  Max Guests: " + maxGuests);
        System.out.println("  Price Range: PHP " + priceMin + " - " + priceMax);
        
        // Sample data for demonstration
        model.setRowCount(0);
        model.addRow(new Object[]{101, "101", 1, "Single Standard", 1, 3500.00, "Available"});
        model.addRow(new Object[]{102, "102", 1, "Double Standard", 2, 5000.00, "Available"});
        model.addRow(new Object[]{201, "201", 2, "Double Deluxe", 3, 7500.00, "Available"});
    }
    
    private void clearFilters() {
        txtCheckInDate.setText("");
        txtCheckOutDate.setText("");
        cbRoomType.setSelectedIndex(0);
        txtPax.setText("");
        txtPriceMin.setText("0");
        txtPriceMax.setText("100000");
        model.setRowCount(0);
        selectedRoomId = -1;
    }
    
    private void handleSelectRoom() {
        if (selectedRoomId == -1) {
            JOptionPane.showMessageDialog(this,
                "Error: Please select a room from the results.",
                "Selection Required", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // TODO: Pass selected room to MakeReservationPanel
        JOptionPane.showMessageDialog(this,
            "Selected Room #" + selectedRoomNumber + " (ID: " + selectedRoomId + ")",
            "Room Selected", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public int getSelectedRoomId() {
        return selectedRoomId;
    }
}