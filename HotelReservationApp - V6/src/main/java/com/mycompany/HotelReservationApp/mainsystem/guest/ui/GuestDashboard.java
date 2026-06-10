package com.mycompany.HotelReservationApp.mainsystem.guest.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuestDashboard extends JFrame implements ActionListener {

    //labels
    private JLabel lblWelcome;
    private JLabel lblHotel;
    private JLabel lblManagement;

    //panels
    private JPanel sidebar;
    private JPanel topPan;
    private JPanel contentArea;

    //buttons
    private JButton btnRooms;
    private JButton btnMake;
    private JButton btnView;
    private JButton btnCancel;
    private JButton btnGProfile;
    private JButton btnLogout;

    public GuestDashboard() {
        setTitle("Hotel Guest System");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        sidebar = new JPanel();
        sidebar.setBounds(0, 0, 250, 700);
        sidebar.setLayout(null);
        sidebar.setBackground(Color.decode("#222222"));

        lblHotel = new JLabel("HOTEL");
        lblHotel.setBounds(10, 10, 230, 50);
        lblHotel.setFont(new Font("Arial Black", Font.BOLD, 40));
        lblHotel.setForeground(Color.WHITE);
        sidebar.add(lblHotel);

        lblManagement = new JLabel("GUEST PORTAL");
        lblManagement.setBounds(10, 50, 230, 40);
        lblManagement.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblManagement.setForeground(Color.WHITE);
        sidebar.add(lblManagement);

        btnRooms = makeSideButton("Search Rooms", 160);
        btnMake = makeSideButton("Make Reservation", 230);
        btnView = makeSideButton("View Reservations", 300);
        btnCancel = makeSideButton("Cancel Reservation", 370);
        btnGProfile = makeSideButton("Guest Profile", 440);
        btnLogout = makeSideButton("Logout", 610);

        btnRooms.addActionListener(this);
        btnMake.addActionListener(this);
        btnView.addActionListener(this);
        btnCancel.addActionListener(this);
        btnGProfile.addActionListener(this);
        btnLogout.addActionListener(this);

        sidebar.add(btnRooms);
        sidebar.add(btnMake);
        sidebar.add(btnView);
        sidebar.add(btnCancel);
        sidebar.add(btnGProfile);
        sidebar.add(btnLogout);

        topPan = new JPanel();
        topPan.setBounds(0, 0, 1200, 150);
        topPan.setLayout(null);
        topPan.setBackground(Color.WHITE);

        lblWelcome = new JLabel("GUEST DASHBOARD");
        lblWelcome.setBounds(280, 60, 800, 60);
        lblWelcome.setFont(new Font("Arial Black", Font.BOLD, 55));
        topPan.add(lblWelcome);

        contentArea = new JPanel();
        contentArea.setBounds(250, 150, 950, 550);
        contentArea.setLayout(null);
        contentArea.setBackground(Color.decode("#F5F5F5"));

        add(sidebar);
        add(topPan);
        add(contentArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRooms) {
            switchPanel(new SearchRoomsPanel());
        }
        if (e.getSource() == btnMake) {
            switchPanel(new MakeReservationPanel());
        }
        if (e.getSource() == btnView) {
            switchPanel(new ViewReservationsPanel());
        }
        if (e.getSource() == btnCancel) {
            switchPanel(new CancelReservationPanel());
        }
        if (e.getSource() == btnGProfile) {
            switchPanel(new GuestProfilePanel());
        }
        if (e.getSource() == btnLogout) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.out.println("Logout confirmed.");
            }
        }
    }

    private JButton makeSideButton(String text, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(0, y, 250, 50);
        btn.setBackground(Color.decode("#222222"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial Black", Font.BOLD, 14));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBorder(null);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.decode("#222222"));
                btn.setForeground(Color.WHITE);
            }
        });
        return btn;
    }

    public void switchPanel(JPanel panel) {
        contentArea.removeAll();
        panel.setBounds(0, 0, 950, 550);
        contentArea.add(panel);
        contentArea.revalidate();
        contentArea.repaint();
    }
}
