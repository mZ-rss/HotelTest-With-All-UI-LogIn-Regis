package com.mycompany.HotelReservationApp.mainsystem.guest.ui;
import javax.swing.*;
import java.awt.*;

public class ViewReservationsPanel extends JPanel {
    private JTable resTable;
    private JScrollPane scrollPane;
    private JPanel titleBar;
    private JLabel lblTitle;
    private String[] columns;
    private Object[][] data;

    public ViewReservationsPanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));

        titleBar = new JPanel(null);
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);

        lblTitle = new JLabel("VIEW RESERVATIONS");
        lblTitle.setBounds(15, 8, 400, 34);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titleBar.add(lblTitle);

        columns = new String[]{"ID", "Room", "Check In", "Check Out", "Status"};
        data = new Object[][]{};

        resTable = new JTable(data, columns);
        resTable.setFont(new Font("Arial", Font.PLAIN, 13));
        resTable.setRowHeight(28);
        resTable.getTableHeader().setBackground(Color.decode("#222222"));
        resTable.getTableHeader().setForeground(Color.WHITE);
        resTable.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 12));

        scrollPane = new JScrollPane(resTable);
        scrollPane.setBounds(30, 80, 880, 420);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        add(scrollPane);
    }
}
