package com.vicvaz.MainApp;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

public class UIHelper {

    public static JComboBox<String> createCurrencyDropdown() {
        String[] currencies = {"MXN", "USD", "EUR", "GBP", "JPY", "KRW"};
        JComboBox<String> comboBox = new JComboBox<>(currencies);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        return comboBox;
    }

    public static JPanel createChartPanel(double[] rates) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "KRW"};
        for (int i = 0; i < rates.length; i++) {
            dataset.addValue(rates[i], "Tasa", currencies[i]);
        }

        return new ChartPanel(ChartFactory.createBarChart("Tasas de cambio", "Moneda", "Tasa", dataset));
    }

    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
