package com.mycompany.HotelReservationApp.mainsystem.guest.ui;

import javax.swing.*;
import javax.swing.JScrollBar;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.*;

public class CancelReservationPanel extends JPanel implements ActionListener {

    //labels
    private JLabel lblTitle;
    private JLabel lblSelect;
    private JLabel lblPolicy;
    private JLabel lblRefundInfo;
    private JLabel lblReason;

    //combo boxes
    private JComboBox<String> cmbReservations;
    private JComboBox<String> cmbReason;

    //textarea
    private JTextArea areaPolicy;

    //buttons
    private JButton btnProcess;

    //scrollpane
    private JScrollPane scrollPane;

    //panels
    private JPanel titleBar;
    private JPanel formContent;
    private JPanel card;

    public CancelReservationPanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));

        titleBar = new JPanel();
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setLayout(null);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);

        lblTitle = new JLabel("CANCEL BOOKING");
        lblTitle.setBounds(15, 8, 400, 34);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titleBar.add(lblTitle);

        formContent = new JPanel(null);
        formContent.setBackground(Color.decode("#F5F5F5"));
        formContent.setPreferredSize(new Dimension(860, 500));

        card = new JPanel();
        card.setBounds(30, 10, 550, 460);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        formContent.add(card);

        lblSelect = new JLabel("Select Reservation:");
        lblSelect.setBounds(25, 25, 200, 25);
        lblSelect.setFont(new Font("Arial Black", Font.BOLD, 14));
        card.add(lblSelect);

        cmbReservations = new JComboBox<>(new String[]{"- Select Reservation -"});
        cmbReservations.setBounds(25, 55, 490, 35);
        cmbReservations.setFont(new Font("Arial", Font.PLAIN, 13));
        card.add(cmbReservations);

        lblPolicy = new JLabel("Cancellation Policy:");
        lblPolicy.setBounds(25, 105, 200, 25);
        lblPolicy.setFont(new Font("Arial Black", Font.BOLD, 14));
        card.add(lblPolicy);

        areaPolicy = new JTextArea(
            "  > More than 7 days before check-in: 100% Full Refund\n" +
            "  > Exactly 7 days before check-in:   90% Refund\n" +
            "  > Less than 7 days before check-in: No Refund\n" +
            "  > After check-in:                   No Refund"
        );
        areaPolicy.setBounds(25, 135, 490, 80);
        areaPolicy.setEditable(false);
        areaPolicy.setBackground(Color.decode("#F5F5F5"));
        areaPolicy.setFont(new Font("Courier New", Font.PLAIN, 12));
        areaPolicy.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        card.add(areaPolicy);

        lblRefundInfo = new JLabel("Estimated Refund: Select a reservation to calculate.");
        lblRefundInfo.setBounds(25, 228, 490, 22);
        lblRefundInfo.setFont(new Font("Arial Black", Font.BOLD, 12));
        lblRefundInfo.setForeground(Color.decode("#222222"));
        card.add(lblRefundInfo);

        lblReason = new JLabel("Cancellation Reason:");
        lblReason.setBounds(25, 265, 220, 25);
        lblReason.setFont(new Font("Arial Black", Font.BOLD, 14));
        card.add(lblReason);

        cmbReason = new JComboBox<>(new String[]{
            "- Select Reason -",
            "Change of Plans",
            "Emergency",
            "Health",
            "Work",
            "Other"
        });
        cmbReason.setBounds(25, 295, 300, 35);
        cmbReason.setFont(new Font("Arial", Font.PLAIN, 13));
        card.add(cmbReason);

        btnProcess = new JButton("CONFIRM CANCELLATION");
        btnProcess.setBounds(25, 375, 250, 45);
        btnProcess.setBackground(Color.decode("#222222"));
        btnProcess.setForeground(Color.WHITE);
        btnProcess.setFont(new Font("Arial Black", Font.BOLD, 14));
        btnProcess.setBorderPainted(false);
        btnProcess.setFocusPainted(false);
        card.add(btnProcess);

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

        add(scrollPane);

        cmbReservations.addActionListener(this);
        btnProcess.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbReservations) {
            if (cmbReservations.getSelectedIndex() == 0) {
                lblRefundInfo.setText("Estimated Refund: Select a reservation to calculate.");
                return;
            }
            updateRefundEstimate();
        }

        if (e.getSource() == btnProcess) {
            if (cmbReservations.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Error: Please select a reservation to cancel.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cmbReason.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Error: Please select a cancellation reason.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String refundMsg = lblRefundInfo.getText();
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this reservation?\n" + refundMsg, "Confirm Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.out.println("Cancellation confirmed. Proceeding with ReservationDAO update...");
            }
        }
    }

    private void updateRefundEstimate() {
        LocalDate checkInDate = LocalDate.now().plusDays(10);
        LocalDate today = LocalDate.now();
        long daysUntilCheckIn = ChronoUnit.DAYS.between(today, checkInDate);
        double reservationTotal = 28000.00;
        double refund;
        String refundLabel;
        if (daysUntilCheckIn > 7) {
            refund = reservationTotal;
            refundLabel = "100% Full Refund";
        } else if (daysUntilCheckIn == 7) {
            refund = reservationTotal * 0.90;
            refundLabel = "90% Refund";
        } else {
            refund = 0;
            refundLabel = "No Refund";
        }
        lblRefundInfo.setText(String.format("Estimated Refund: PHP %,.2f (%s)", refund, refundLabel));
    }
}
