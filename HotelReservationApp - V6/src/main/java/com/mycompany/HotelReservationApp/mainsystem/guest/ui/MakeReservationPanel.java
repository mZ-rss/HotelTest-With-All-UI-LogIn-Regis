package com.mycompany.HotelReservationApp.mainsystem.guest.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class MakeReservationPanel extends JPanel implements ActionListener {

    //labels
    private JLabel lblHeader;
    private JLabel secRoom;
    private JLabel lblType;
    private JLabel lblPax;
    private JLabel lblDates;
    private JLabel lblTo;
    private JLabel lblNights;
    private JLabel lblTotalDisplay;
    private JLabel secPay;
    private JLabel lblPayMethod;
    private JLabel lblCardNumber;
    private JLabel lblCardHolder;
    private JLabel lblExpiry;
    private JLabel lblCvv;
    private JLabel lblWalletPhone;
    private JLabel lblOtp;
    private JLabel lblReceiptTitle;

    //fields
    private JTextField txtPax;
    private JTextField txtIn;
    private JTextField txtOut;
    private JTextField txtCardNumber;
    private JTextField txtCardHolder;
    private JTextField txtExpiry;
    private JTextField txtCvv;
    private JTextField txtWalletPhone;
    private JTextField txtOtp;

    //txtarea
    private JTextArea txtReceipt;

    //cbboxes
    private JComboBox<String> cbRoomType;
    private JComboBox<String> cbPayment;

    //buttons
    private JButton btnCalculate;
    private JButton btnBook;
    private JButton btnCloseReceipt;

    //scrollpane
    private JScrollPane scrollPane;
    private JScrollBar vBar;

    //seperators
    private JSeparator sep;

    //panels
    private JPanel titleBar;
    private JPanel formContent;
    private JPanel formCard;
    private JPanel payDetailsPanel;
    private JPanel receiptOverlay;

    //ratepax
    private double selectedRate = 0.0;
    private int maxPax = 0;
    private long numberOfNights = 0;
    private double finalTotal = 0.0;

    private static final double[] RATES = {0, 3500, 5000, 7500, 12000};
    private static final int[]    PAXES = {0,    1,    2,    3,    4};

    public MakeReservationPanel() {
        setLayout(null);
        setBackground(Color.decode("#F5F5F5"));

        titleBar = new JPanel(null);
        titleBar.setBounds(30, 20, 880, 50);
        titleBar.setBackground(Color.decode("#222222"));
        add(titleBar);

        lblHeader = new JLabel("MAKE A RESERVATION");
        lblHeader.setBounds(15, 8, 500, 34);
        lblHeader.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblHeader.setForeground(Color.WHITE);
        titleBar.add(lblHeader);

        formContent = new JPanel(null);
        formContent.setBackground(Color.decode("#F5F5F5"));
        formContent.setPreferredSize(new Dimension(860, 740));

        formCard = new JPanel(null);
        formCard.setBounds(10, 10, 500, 620);
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        formContent.add(formCard);

        secRoom = new JLabel("ROOM DETAILS");
        secRoom.setBounds(20, 15, 300, 22);
        secRoom.setFont(new Font("Arial Black", Font.BOLD, 13));
        formCard.add(secRoom);

        lblType = new JLabel("Select Room Type:");
        lblType.setBounds(20, 45, 180, 18);
        lblType.setFont(new Font("Arial Black", Font.BOLD, 11));
        formCard.add(lblType);

        cbRoomType = new JComboBox<>(new String[]{
            "- Select -",
            "Single Standard (PHP 3,500/night) - 1 Pax",
            "Double Standard (PHP 5,000/night) - 2 Pax",
            "Double Deluxe (PHP 7,500/night) - 3 Pax",
            "Suite Deluxe (PHP 12,000/night) - 4 Pax"
        });
        cbRoomType.setBounds(20, 65, 340, 30);
        formCard.add(cbRoomType);

        lblPax = new JLabel("Number of Guests:");
        lblPax.setBounds(20, 108, 160, 18);
        lblPax.setFont(new Font("Arial Black", Font.BOLD, 11));
        formCard.add(lblPax);

        txtPax = new JTextField();
        txtPax.setBounds(20, 128, 90, 30);
        txtPax.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        formCard.add(txtPax);

        lblDates = new JLabel("Check-in / Check-out (YYYY-MM-DD):");
        lblDates.setBounds(20, 172, 320, 18);
        lblDates.setFont(new Font("Arial Black", Font.BOLD, 11));
        formCard.add(lblDates);

        txtIn = new JTextField();
        txtIn.setBounds(20, 192, 120, 30);
        txtIn.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        formCard.add(txtIn);

        lblTo = new JLabel("to");
        lblTo.setBounds(150, 192, 25, 30);
        lblTo.setFont(new Font("Arial Black", Font.BOLD, 11));
        formCard.add(lblTo);

        txtOut = new JTextField();
        txtOut.setBounds(178, 192, 120, 30);
        txtOut.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        formCard.add(txtOut);

        lblNights = new JLabel("Nights: --");
        lblNights.setBounds(315, 192, 150, 30);
        lblNights.setFont(new Font("Arial Black", Font.BOLD, 11));
        formCard.add(lblNights);

        btnCalculate = new JButton("Calculate Price");
        btnCalculate.setBounds(20, 238, 145, 30);
        btnCalculate.setBackground(Color.decode("#222222"));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setFont(new Font("Arial Black", Font.BOLD, 11));
        btnCalculate.setBorderPainted(false);
        btnCalculate.setFocusPainted(false);
        formCard.add(btnCalculate);

        lblTotalDisplay = new JLabel("Total: PHP 0.00");
        lblTotalDisplay.setBounds(178, 238, 290, 30);
        lblTotalDisplay.setFont(new Font("Arial Black", Font.BOLD, 12));
        formCard.add(lblTotalDisplay);

        sep = new JSeparator();
        sep.setBounds(15, 282, 468, 2);
        sep.setForeground(Color.LIGHT_GRAY);
        formCard.add(sep);

        secPay = new JLabel("PAYMENT DETAILS");
        secPay.setBounds(20, 292, 300, 22);
        secPay.setFont(new Font("Arial Black", Font.BOLD, 13));
        formCard.add(secPay);

        lblPayMethod = new JLabel("Payment Method:");
        lblPayMethod.setBounds(20, 322, 180, 18);
        lblPayMethod.setFont(new Font("Arial Black", Font.BOLD, 11));
        formCard.add(lblPayMethod);

        cbPayment = new JComboBox<>(new String[]{"- Select -", "Credit Card", "E-Wallet", "Cash"});
        cbPayment.setBounds(20, 342, 230, 30);
        formCard.add(cbPayment);

        payDetailsPanel = new JPanel(null);
        payDetailsPanel.setBounds(15, 385, 468, 220);
        payDetailsPanel.setBackground(Color.WHITE);
        formCard.add(payDetailsPanel);

        lblCardNumber = new JLabel("Card Number (13-19 digits):");
        lblCardNumber.setBounds(0, 0, 300, 18);
        lblCardNumber.setFont(new Font("Arial Black", Font.BOLD, 11));
        payDetailsPanel.add(lblCardNumber);

        txtCardNumber = new JTextField();
        txtCardNumber.setBounds(0, 20, 440, 30);
        txtCardNumber.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        payDetailsPanel.add(txtCardNumber);

        lblCardHolder = new JLabel("Card Holder Name:");
        lblCardHolder.setBounds(0, 60, 300, 18);
        lblCardHolder.setFont(new Font("Arial Black", Font.BOLD, 11));
        payDetailsPanel.add(lblCardHolder);

        txtCardHolder = new JTextField();
        txtCardHolder.setBounds(0, 80, 440, 30);
        txtCardHolder.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        payDetailsPanel.add(txtCardHolder);

        lblExpiry = new JLabel("Expiry Date (MM/YY):");
        lblExpiry.setBounds(0, 120, 220, 18);
        lblExpiry.setFont(new Font("Arial Black", Font.BOLD, 11));
        payDetailsPanel.add(lblExpiry);

        txtExpiry = new JTextField();
        txtExpiry.setBounds(0, 140, 140, 30);
        txtExpiry.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        payDetailsPanel.add(txtExpiry);

        lblCvv = new JLabel("CVV:");
        lblCvv.setBounds(160, 120, 80, 18);
        lblCvv.setFont(new Font("Arial Black", Font.BOLD, 11));
        payDetailsPanel.add(lblCvv);

        txtCvv = new JTextField();
        txtCvv.setBounds(160, 140, 80, 30);
        txtCvv.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        payDetailsPanel.add(txtCvv);

        lblWalletPhone = new JLabel("E-Wallet Phone (09XXXXXXXXX or +639XXXXXXXXX):");
        lblWalletPhone.setBounds(0, 0, 440, 18);
        lblWalletPhone.setFont(new Font("Arial Black", Font.BOLD, 11));
        payDetailsPanel.add(lblWalletPhone);

        txtWalletPhone = new JTextField();
        txtWalletPhone.setBounds(0, 20, 280, 30);
        txtWalletPhone.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        payDetailsPanel.add(txtWalletPhone);

        lblOtp = new JLabel("OTP (6-digit code):");
        lblOtp.setBounds(0, 60, 220, 18);
        lblOtp.setFont(new Font("Arial Black", Font.BOLD, 11));
        payDetailsPanel.add(lblOtp);

        txtOtp = new JTextField();
        txtOtp.setBounds(0, 80, 140, 30);
        txtOtp.setBorder(BorderFactory.createLineBorder(Color.decode("#222222")));
        payDetailsPanel.add(txtOtp);

        payDetailsPanel.setVisible(false);

        btnBook = new JButton("CONFIRM & PAY");
        btnBook.setBounds(290, 342, 150, 30);
        btnBook.setBackground(Color.decode("#222222"));
        btnBook.setForeground(Color.WHITE);
        btnBook.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnBook.setBorderPainted(false);
        btnBook.setFocusPainted(false);
        formCard.add(btnBook);

        receiptOverlay = new JPanel(null);
        receiptOverlay.setBounds(530, 10, 320, 700);
        receiptOverlay.setBackground(Color.decode("#222222"));
        receiptOverlay.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        receiptOverlay.setVisible(false);
        formContent.add(receiptOverlay);

        lblReceiptTitle = new JLabel("BOOKING RECEIPT");
        lblReceiptTitle.setBounds(15, 12, 290, 26);
        lblReceiptTitle.setFont(new Font("Arial Black", Font.BOLD, 14));
        lblReceiptTitle.setForeground(Color.WHITE);
        receiptOverlay.add(lblReceiptTitle);

        txtReceipt = new JTextArea();
        txtReceipt.setEditable(false);
        txtReceipt.setBackground(Color.decode("#333333"));
        txtReceipt.setForeground(Color.WHITE);
        txtReceipt.setFont(new Font("Courier New", Font.PLAIN, 12));
        txtReceipt.setLineWrap(true);
        txtReceipt.setWrapStyleWord(false);

        JScrollPane receiptScroll = new JScrollPane(txtReceipt);
        receiptScroll.setBounds(15, 48, 290, 600);
        receiptScroll.setBorder(BorderFactory.createLineBorder(Color.decode("#555555")));
        receiptScroll.setBackground(Color.decode("#333333"));
        receiptScroll.getViewport().setBackground(Color.decode("#333333"));
        receiptScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        receiptScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        receiptOverlay.add(receiptScroll);

        btnCloseReceipt = new JButton("CLOSE");
        btnCloseReceipt.setBounds(100, 655, 120, 30);
        btnCloseReceipt.setBackground(Color.WHITE);
        btnCloseReceipt.setForeground(Color.BLACK);
        btnCloseReceipt.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnCloseReceipt.setBorderPainted(false);
        btnCloseReceipt.setFocusPainted(false);
        receiptOverlay.add(btnCloseReceipt);

        scrollPane = new JScrollPane(formContent);
        scrollPane.setBounds(30, 80, 880, 460);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.decode("#F5F5F5"));
        scrollPane.getViewport().setBackground(Color.decode("#F5F5F5"));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        vBar = scrollPane.getVerticalScrollBar();
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
        add(titleBar);

        cbPayment.addActionListener(this);
        btnCalculate.addActionListener(this);
        btnBook.addActionListener(this);
        btnCloseReceipt.addActionListener(this);
    }

    private int validateRoomAndPax() {
        int selection = cbRoomType.getSelectedIndex();
        if (selection == 0) {
            showError("Please select a room type.");
            return -1;
        }

        String paxText = txtPax.getText().trim();
        if (paxText.isEmpty()) {
            showError("Please enter number of guests.");
            return -1;
        }

        int inputPax;
        try {
            inputPax = Integer.parseInt(paxText);
        } catch (NumberFormatException ex) {
            showError("Please enter a valid number for guests.");
            return -1;
        }

        if (inputPax <= 0) {
            showError("Guests must be at least 1.");
            return -1;
        }

        int allowedPax = PAXES[selection];
        if (inputPax > allowedPax) {
            showError("This room type only allows up to " + allowedPax + " guest(s).");
            return -1;
        }

        return inputPax;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cbPayment) {
            String method    = cbPayment.getSelectedItem().toString();
            boolean isCard   = method.equals("Credit Card");
            boolean isWallet = method.equals("E-Wallet");

            lblCardNumber.setVisible(isCard);    txtCardNumber.setVisible(isCard);
            lblCardHolder.setVisible(isCard);    txtCardHolder.setVisible(isCard);
            lblExpiry.setVisible(isCard);        txtExpiry.setVisible(isCard);
            lblCvv.setVisible(isCard);           txtCvv.setVisible(isCard);
            lblWalletPhone.setVisible(isWallet); txtWalletPhone.setVisible(isWallet);
            lblOtp.setVisible(isWallet);         txtOtp.setVisible(isWallet);

            payDetailsPanel.setVisible(isCard || isWallet);
            formCard.revalidate();
            formCard.repaint();
        }

        if (e.getSource() == btnCalculate) {
            int selection = cbRoomType.getSelectedIndex();
            if (selection == 0) {
                showError("Please select a room type.");
                return;
            }

            //validation of pax before calculation
            int inputPax = validateRoomAndPax();
            if (inputPax == -1) return;

            selectedRate = RATES[selection];
            maxPax       = PAXES[selection];

            LocalDate checkIn = parseDate(txtIn.getText().trim(), "check-in");
            if (checkIn == null) return;

            LocalDate checkOut = parseDate(txtOut.getText().trim(), "check-out");
            if (checkOut == null) return;

            if (checkIn.isBefore(LocalDate.now())) {
                showError("Check-in date cannot be in the past.");
                return;
            }
            if (!checkOut.isAfter(checkIn)) {
                showError("Check-out date must be after check-in date.");
                return;
            }

            numberOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);
            if (numberOfNights < 1) {
                showError("Minimum stay is 1 night.");
                return;
            }
            if (numberOfNights > 365) {
                showError("Maximum stay is 365 nights.");
                return;
            }

            double subtotal = selectedRate * numberOfNights;
            double tax      = subtotal * 0.12;
            finalTotal      = subtotal + tax;

            lblNights.setText("Nights: " + numberOfNights);
            lblTotalDisplay.setText(String.format(
                "<html>Subtotal: PHP%,.2f | Tax(12%%): PHP%,.2f | <b>Total: PHP%,.2f</b></html>",
                subtotal, tax, finalTotal
            ));
        }

        if (e.getSource() == btnBook) {
            //re-validate room and pax at booking time
            int inputPax = validateRoomAndPax();
            if (inputPax == -1) return;

            if (txtIn.getText().trim().isEmpty() || txtOut.getText().trim().isEmpty()) {
                showError("Please enter check-in and check-out dates.");
                return;
            }
            if (finalTotal == 0) {
                showError("Please calculate the price first.");
                return;
            }
            if (cbPayment.getSelectedIndex() == 0) {
                showError("Please select a payment method.");
                return;
            }
            if (finalTotal > 999999.99) {
                showError("Total exceeds maximum allowed amount of PHP 999,999.99.");
                return;
            }

            String payMethod = cbPayment.getSelectedItem().toString();

            if (payMethod.equals("Credit Card")) {
                String cardNum    = txtCardNumber.getText().trim();
                String cardHolder = txtCardHolder.getText().trim();
                String expiry     = txtExpiry.getText().trim();
                String cvv        = txtCvv.getText().trim();

                if (cardNum.isEmpty()) {
                    showError("Card number cannot be empty.");
                    return;
                }
                if (cardNum.length() < 13 || cardNum.length() > 19) {
                    showError("Card number must be 13-19 digits.");
                    return;
                }
                boolean allDigits = true;
                for (int i = 0; i < cardNum.length(); i++) {
                    if (!Character.isDigit(cardNum.charAt(i))) {
                        allDigits = false;
                        break;
                    }
                }
                if (!allDigits) {
                    showError("Card number must contain digits only.");
                    return;
                }
                if (!passesLuhn(cardNum)) {
                    showError("Invalid card number (failed Luhn check).");
                    return;
                }
                if (cardHolder.isEmpty()) {
                    showError("Card holder name cannot be empty.");
                    return;
                }
                boolean validHolder = true;
                for (int i = 0; i < cardHolder.length(); i++) {
                    char c = cardHolder.charAt(i);
                    if (!Character.isLetter(c) && c != ' ') {
                        validHolder = false;
                        break;
                    }
                }
                if (!validHolder) {
                    showError("Card holder name must contain letters and spaces only.");
                    return;
                }
                if (expiry.isEmpty()) {
                    showError("Expiry date cannot be empty.");
                    return;
                }
                if (expiry.length() < 4 || !expiry.contains("/")) {
                    showError("Expiry must be MM/YY format.");
                    return;
                }
                if (isCardExpired(expiry)) {
                    showError("This card has expired.");
                    return;
                }
                if (cvv.isEmpty() || cvv.length() < 3 || cvv.length() > 4) {
                    showError("CVV must be 3 or 4 digits.");
                    return;
                }
                boolean validCvv = true;
                for (int i = 0; i < cvv.length(); i++) {
                    if (!Character.isDigit(cvv.charAt(i))) {
                        validCvv = false;
                        break;
                    }
                }
                if (!validCvv) {
                    showError("CVV must contain digits only.");
                    return;
                }
            }

            if (payMethod.equals("E-Wallet")) {
                String walletPhone = txtWalletPhone.getText().trim();
                String otp         = txtOtp.getText().trim();

                if (walletPhone.isEmpty()) {
                    showError("E-Wallet phone number cannot be empty.");
                    return;
                }
                if (walletPhone.length() != 11 && walletPhone.length() != 13) {
                    showError("Phone must be 09XXXXXXXXX (11 digits) or +639XXXXXXXXX (13 chars).");
                    return;
                }
                if (walletPhone.length() == 11 && !walletPhone.startsWith("09")) {
                    showError("Phone must start with 09 for local format.");
                    return;
                }
                if (walletPhone.length() == 13 && !walletPhone.startsWith("+639")) {
                    showError("Phone must start with +639 for international format.");
                    return;
                }
                if (otp.isEmpty()) {
                    showError("OTP cannot be empty.");
                    return;
                }
                if (otp.length() != 6) {
                    showError("OTP must be exactly 6 digits.");
                    return;
                }
                boolean validOtp = true;
                for (int i = 0; i < otp.length(); i++) {
                    if (!Character.isDigit(otp.charAt(i))) {
                        validOtp = false;
                        break;
                    }
                }
                if (!validOtp) {
                    showError("OTP must contain digits only.");
                    return;
                }
            }

            double subtotal = selectedRate * numberOfNights;
            receiptOverlay.setVisible(true);
            txtReceipt.setText(
                "--- HOTEL RESERVATION RECEIPT ---\n\n" +
                "Room:      " + cbRoomType.getSelectedItem() + "\n" +
                "Guests:    " + txtPax.getText() + "\n" +
                "Check-in:  " + txtIn.getText() + "\n" +
                "Check-out: " + txtOut.getText() + "\n" +
                "Nights:    " + numberOfNights + "\n" +
                "---------------------------------\n" +
                String.format("Subtotal:  PHP %,.2f%n", subtotal) +
                String.format("Tax (12%%): PHP %,.2f%n", subtotal * 0.12) +
                String.format("TOTAL:     PHP %,.2f%n", finalTotal) +
                "---------------------------------\n" +
                "Method:    " + payMethod + "\n" +
                "---------------------------------\n" +
                "STATUS:    SUCCESSFUL"
            );
        }

        if (e.getSource() == btnCloseReceipt) {
            receiptOverlay.setVisible(false);
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, "Error: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private LocalDate parseDate(String text, String label) {
        if (text.isEmpty()) {
            showError("Please enter a " + label + " date.");
            return null;
        }
        try {
            DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d").toFormatter();
            return LocalDate.parse(text, dtf);
        } catch (DateTimeParseException ex) {
            showError("Invalid " + label + " date. Use YYYY-MM-DD.");
            return null;
        }
    }

    private boolean passesLuhn(String number) {
        int sum = 0;
        boolean alt = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(number.charAt(i));
            if (alt) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alt = !alt;
        }
        return sum % 10 == 0;
    }

    private boolean isCardExpired(String expiry) {
        try {
            String[] parts = expiry.split("/");
            int month = Integer.parseInt(parts[0]);
            int year  = Integer.parseInt(parts[1]);
            if (year < 100) year += 2000;
            return LocalDate.of(year, month, 1).plusMonths(1).minusDays(1).isBefore(LocalDate.now());
        } catch (Exception ex) {
            return true;
        }
    }
}