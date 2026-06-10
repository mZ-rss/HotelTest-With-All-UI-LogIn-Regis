package com.mycompany.HotelReservationApp.mainsystem.guest.ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.*;

/**
 * CancelReservationPanel - Reservation Cancellation with Refund Policy
 * Displays cancellation policy, calculates refund amounts, processes cancellations
 * Features: Real-time refund calculation, policy display, confirmation dialogs
 */
public class CancelReservationPanel extends JPanel implements ActionListener {
    
    private JLabel lblTitle;
    private JLabel lblSelect, lblPolicy, lblRefundInfo, lblReason;
    private JLabel lblErrorMessage;
    
    private JComboBox<String> cmbReservations;
    private JComboBox<String> cmbReason;
    
    private JTextArea areaPolicy;
    private JTable reservationTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane, tableScrollPane;
    
    private JButton btnProcess, btnClear;
    
    private JPanel titleBar, formContent, filterPanel, card;
    private String[] columns;
    
    public CancelReservationPanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));
        createComponents();
        loadReservations();
    }
    
    private void createComponents() {
        // Title Bar
        titleBar = new JPanel(null);
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setLayout(null);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);
        
        lblTitle = new JLabel("CANCEL RESERVATION");
        lblTitle.setBounds(15, 8, 400, 34);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titleBar.add(lblTitle);
        
        // Error Message
        lblErrorMessage = new JLabel();
        lblErrorMessage.setFont(new Font("Arial", Font.PLAIN, 11));
        lblErrorMessage.setForeground(new Color(244, 67, 54));
        lblErrorMessage.setBounds(30, 75, 880, 20);
        lblErrorMessage.setVisible(false);
        add(lblErrorMessage);
        
        // Filter/Selection Panel
        filterPanel = new JPanel(null);
        filterPanel.setBounds(30, 100, 880, 45);
        filterPanel.setBackground(Color.decode("#333333"));
        add(filterPanel);
        
        JLabel lblSelectRes = new JLabel("Select Reservation to Cancel:");
        lblSelectRes.setBounds(15, 10, 200, 25);
        lblSelectRes.setForeground(Color.WHITE);
        lblSelectRes.setFont(new Font("Arial Black", Font.BOLD, 11));
        filterPanel.add(lblSelectRes);
        
        cmbReservations = new JComboBox<>(new String[]{"- Select Reservation -"});
        cmbReservations.setBounds(220, 10, 500, 25);
        cmbReservations.setFont(new Font("Arial", Font.PLAIN, 12));
        cmbReservations.addActionListener(this);
        filterPanel.add(cmbReservations);
        
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(740, 10, 120, 25);
        btnClear.setBackground(new Color(255, 193, 7));
        btnClear.setForeground(Color.BLACK);
        btnClear.setFont(new Font("Arial Black", Font.BOLD, 11));
        btnClear.setBorderPainted(false);
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(this);
        filterPanel.add(btnClear);
        
        // Reservations Table
        columns = new String[]{"Res ID", "Room #", "Check-In", "Check-Out", "Status", "Total"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        reservationTable = new JTable(model);
        reservationTable.setFont(new Font("Arial", Font.PLAIN, 12));
        reservationTable.setRowHeight(22);
        reservationTable.getTableHeader().setBackground(Color.decode("#222222"));
        reservationTable.getTableHeader().setForeground(Color.WHITE);
        reservationTable.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 11));
        reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableScrollPane = new JScrollPane(reservationTable);
        tableScrollPane.setBounds(30, 150, 880, 150);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        add(tableScrollPane);
        
        // Form Content
        formContent = new JPanel(null);
        formContent.setBackground(Color.decode("#F5F5F5"));
        formContent.setPreferredSize(new Dimension(860, 300));
        
        card = new JPanel(null);
        card.setBounds(10, 10, 840, 280);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        formContent.add(card);
        
        // Cancellation Policy Section
        lblPolicy = new JLabel("CANCELLATION REFUND POLICY:");
        lblPolicy.setBounds(25, 15, 400, 22);
        lblPolicy.setFont(new Font("Arial Black", Font.BOLD, 12));
        card.add(lblPolicy);
        
        areaPolicy = new JTextArea(
            "  > More than 7 days before check-in: 100% FULL REFUND\n" +
            "  > Exactly 7 days before check-in:   90% REFUND (10% fee)\n" +
            "  > Less than 7 days before check-in: NO REFUND\n" +
            "  > After check-in:                   NO REFUND"
        );
        areaPolicy.setBounds(25, 40, 790, 75);
        areaPolicy.setEditable(false);
        areaPolicy.setBackground(new Color(255, 250, 205));
        areaPolicy.setFont(new Font("Courier New", Font.BOLD, 11));
        areaPolicy.setBorder(BorderFactory.createLineBorder(Color.decode("#FFA500")));
        areaPolicy.setLineWrap(true);
        areaPolicy.setWrapStyleWord(true);
        card.add(areaPolicy);
        
        // Refund Info
        lblRefundInfo = new JLabel("Estimated Refund: Select a reservation to calculate.");
        lblRefundInfo.setBounds(25, 125, 790, 22);
        lblRefundInfo.setFont(new Font("Arial Black", Font.BOLD, 12));
        lblRefundInfo.setForeground(new Color(27, 94, 32));
        card.add(lblRefundInfo);
        
        // Cancellation Reason
        lblReason = new JLabel("Cancellation Reason: *");
        lblReason.setBounds(25, 155, 200, 20);
        lblReason.setFont(new Font("Arial Black", Font.BOLD, 11));
        card.add(lblReason);
        
        cmbReason = new JComboBox<>(new String[]{
            "- Select Reason -",
            "Change of Plans",
            "Emergency",
            "Health Issue",
            "Work Conflict",
            "Financial Reason",
            "Other"
        });
        cmbReason.setBounds(25, 176, 400, 28);
        cmbReason.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(cmbReason);
        
        // Process Button
        btnProcess = new JButton("CONFIRM CANCELLATION");
        btnProcess.setBounds(450, 176, 365, 28);
        btnProcess.setBackground(new Color(244, 67, 54));
        btnProcess.setForeground(Color.WHITE);
        btnProcess.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnProcess.setBorderPainted(false);
        btnProcess.setFocusPainted(false);
        btnProcess.addActionListener(this);
        card.add(btnProcess);
        
        // Confirmation text
        JLabel lblConfirm = new JLabel("(Click to confirm - This action cannot be undone)");
        lblConfirm.setBounds(25, 215, 790, 15);
        lblConfirm.setFont(new Font("Arial", Font.ITALIC, 10));
        lblConfirm.setForeground(new Color(150, 0, 0));
        card.add(lblConfirm);
        
        // Scroll Pane for Form
        scrollPane = new JScrollPane(formContent);
        scrollPane.setBounds(30, 310, 880, 220);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.decode("#F5F5F5"));
        scrollPane.getViewport().setBackground(Color.decode("#F5F5F5"));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
    }
    
    private void loadReservations() {
        model.setRowCount(0);
        model.addRow(new Object[]{1001, "101", "2025-06-15", "2025-06-18", "Confirmed", "PHP 10,500.00"});
        model.addRow(new Object[]{1002, "205", "2025-07-01", "2025-07-05", "Confirmed", "PHP 28,000.00"});
        model.addRow(new Object[]{1003, "302", "2025-08-10", "2025-08-12", "Checked-In", "PHP 15,000.00"});
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbReservations) {
            if (cmbReservations.getSelectedIndex() > 0) {
                updateRefundEstimate();
                selectReservationInTable();
            } else {
                lblRefundInfo.setText("Estimated Refund: Select a reservation to calculate.");
            }
        } else if (e.getSource() == btnProcess) {
            handleCancellation();
        } else if (e.getSource() == btnClear) {
            clearForm();
        }
    }
    
    private void selectReservationInTable() {
        int selectedIndex = cmbReservations.getSelectedIndex();
        if (selectedIndex > 0 && selectedIndex <= model.getRowCount()) {
            reservationTable.setRowSelectionInterval(selectedIndex - 1, selectedIndex - 1);
        }
    }
    
    private void updateRefundEstimate() {
        int selectedIndex = cmbReservations.getSelectedIndex();
        if (selectedIndex <= 0) return;
        
        LocalDate[] checkInDates = {
            LocalDate.now().plusDays(10),
            LocalDate.now().plusDays(7),
            LocalDate.now().plusDays(3)
        };
        
        double[] totals = {10500.00, 28000.00, 15000.00};
        
        LocalDate checkInDate = checkInDates[Math.min(selectedIndex - 1, checkInDates.length - 1)];
        double reservationTotal = totals[Math.min(selectedIndex - 1, totals.length - 1)];
        
        LocalDate today = LocalDate.now();
        long daysUntilCheckIn = ChronoUnit.DAYS.between(today, checkInDate);
        double refund;
        String refundLabel;
        
        if (daysUntilCheckIn > 7) {
            refund = reservationTotal;
            refundLabel = "100% FULL REFUND";
            lblRefundInfo.setForeground(new Color(27, 94, 32));
        } else if (daysUntilCheckIn == 7) {
            refund = reservationTotal * 0.90;
            refundLabel = "90% REFUND (10% cancellation fee)";
            lblRefundInfo.setForeground(new Color(251, 188, 4));
        } else if (daysUntilCheckIn >= 0) {
            refund = 0;
            refundLabel = "NO REFUND (within 7 days of check-in)";
            lblRefundInfo.setForeground(new Color(211, 47, 47));
        } else {
            refund = 0;
            refundLabel = "NO REFUND (Already checked-in or past check-out)";
            lblRefundInfo.setForeground(new Color(211, 47, 47));
        }
        
        lblRefundInfo.setText(String.format("Estimated Refund: PHP %,.2f (%s) | Days until check-in: %d", refund, refundLabel, daysUntilCheckIn));
    }
    
    private void handleCancellation() {
        lblErrorMessage.setVisible(false);
        
        if (cmbReservations.getSelectedIndex() == 0) {
            showError("Please select a reservation to cancel");
            return;
        }
        
        if (cmbReason.getSelectedIndex() == 0) {
            showError("Please select a cancellation reason");
            return;
        }
        
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow < 0) {
            showError("Please select a reservation from the table");
            return;
        }
        
        String status = (String) model.getValueAt(selectedRow, 4);
        if (status.equals("Checked-Out") || status.equals("Cancelled")) {
            showError("Cannot cancel reservations that are already checked-out or cancelled");
            return;
        }
        
        String refundInfo = lblRefundInfo.getText();
        String reason = (String) cmbReason.getSelectedItem();
        
        String message = "Are you sure you want to cancel this reservation?\n\n" +
                         refundInfo + "\n\n" +
                         "Reason: " + reason + "\n\n" +
                         "This action cannot be undone.";
        
        int confirm = JOptionPane.showConfirmDialog(this, message,
            "Confirm Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Cancellation confirmed for reservation: " + cmbReservations.getSelectedItem());
            System.out.println("Reason: " + reason);
            
            JOptionPane.showMessageDialog(this,
                "Reservation cancelled successfully!\n\nYou will receive your refund within 5-7 business days.",
                "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);
            
            clearForm();
            loadReservations();
        }
    }
    
    private void clearForm() {
        cmbReservations.setSelectedIndex(0);
        cmbReason.setSelectedIndex(0);
        lblRefundInfo.setText("Estimated Refund: Select a reservation to calculate.");
        reservationTable.clearSelection();
        lblErrorMessage.setVisible(false);
    }
    
    private void showError(String message) {
        lblErrorMessage.setText("⚠ " + message);
        lblErrorMessage.setVisible(true);
    }
}