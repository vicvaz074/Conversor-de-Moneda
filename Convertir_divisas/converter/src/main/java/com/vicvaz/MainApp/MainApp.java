package com.vicvaz.MainApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conversor de Divisas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        Color primaryColor = new Color(59, 62, 67);
        Color secondaryColor = new Color(241, 162, 8);
        Color tertiaryColor = new Color(211, 212, 216);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        JLabel headerLabel = new JLabel("Conversor de Divisas");
        headerLabel.setForeground(tertiaryColor);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headerPanel.add(headerLabel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(tertiaryColor);
        GridBagConstraints c = new GridBagConstraints();

        JComboBox<String> fromCurrencyDropdown = UIHelper.createCurrencyDropdown();
        JComboBox<String> toCurrencyDropdown = UIHelper.createCurrencyDropdown();
        JTextField amountField = new JTextField(10);
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton convertButton = new JButton("Convertir");
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        convertButton.setBackground(secondaryColor);
        JLabel resultLabel = new JLabel("El resultado aparecerá aquí");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultLabel.setForeground(primaryColor);
        
        JLabel differenceWithUSD = new JLabel("Diferencia con USD: ");
        differenceWithUSD.setFont(new Font("Arial", Font.PLAIN, 16));

        c.insets = new Insets(10,10,10,10);
        c.gridx = 0; c.gridy = 0; centerPanel.add(new JLabel("De:"), c);
        c.gridx = 1; centerPanel.add(fromCurrencyDropdown, c);

        c.gridx = 0; c.gridy = 1; centerPanel.add(new JLabel("A:"), c);
        c.gridx = 1; centerPanel.add(toCurrencyDropdown, c);

        c.gridx = 0; c.gridy = 2; centerPanel.add(new JLabel("Cantidad:"), c);
        c.gridx = 1; centerPanel.add(amountField, c);

        c.gridx = 1; c.gridy = 3; centerPanel.add(convertButton, c);
        c.gridx = 1; c.gridy = 4; centerPanel.add(resultLabel, c);
        c.gridx = 1; c.gridy = 5; centerPanel.add(differenceWithUSD, c);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String fromCurrency = (String) fromCurrencyDropdown.getSelectedItem();
                    String toCurrency = (String) toCurrencyDropdown.getSelectedItem();
                    double amount = Double.parseDouble(amountField.getText());

                    CurrencyConverter converter = new CurrencyConverter();
                    double result = converter.convert(fromCurrency, toCurrency, amount);
                    resultLabel.setText(String.format("%.2f %s", result, toCurrency));

                    // Añadimos la diferencia con USD
                    if(!toCurrency.equals("USD")) {
                        double usdDifference = converter.convert(fromCurrency, "USD", amount);
                        differenceWithUSD.setText(String.format("Diferencia con USD: %.2f USD", usdDifference));
                    } else {
                        differenceWithUSD.setText(" ");
                    }

                    // Gráfica pequeña
                    JPanel chartPanel = UIHelper.createChartPanel(new double[]{result});
                    chartPanel.setPreferredSize(new Dimension(300, 200));
                    c.gridx = 1; c.gridy = 6; centerPanel.add(chartPanel, c);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                } catch (Exception ex) {
                    UIHelper.showErrorDialog("Hubo un error al convertir. Asegúrese de ingresar un valor válido.");
                }
            }
        });

        JLabel footerLabel = new JLabel("Hecho por Vicvaz074");
        footerLabel.setForeground(primaryColor);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(footerLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
