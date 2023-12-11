import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {
    private JComboBox<String> baseCurrencyComboBox, targetCurrencyComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;

    // Static conversion rates for demonstration purposes
    private static final Map<String, Double> CONVERSION_RATES = new HashMap<>();

    static {
        // Add real-world conversion rates here
        CONVERSION_RATES.put("USD", 1.0);
        CONVERSION_RATES.put("EUR", 0.85);
        CONVERSION_RATES.put("GBP", 0.74);
        CONVERSION_RATES.put("JPY", 112.0);
        CONVERSION_RATES.put("AUD", 1.35);
        CONVERSION_RATES.put("CAD", 1.31);
        // For demonstration, using a simple conversion rate for INR
        CONVERSION_RATES.put("INR", 73.0);
    }

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        initializeComponents();
        addComponents();

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void initializeComponents() {
        // Assuming a list of commonly used currencies
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "INR"};

        baseCurrencyComboBox = new JComboBox<>(currencies);
        targetCurrencyComboBox = new JComboBox<>(currencies);
        amountTextField = new JTextField(10);
        resultLabel = new JLabel("Converted Amount: ");
    }

    private void addComponents() {
        add(new JLabel("Base Currency: "));
        add(baseCurrencyComboBox);

        add(new JLabel("Target Currency: "));
        add(targetCurrencyComboBox);

        add(new JLabel("Amount to Convert: "));
        add(amountTextField);

        add(resultLabel);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
        add(convertButton);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String baseCurrency = (String) baseCurrencyComboBox.getSelectedItem();
            String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();

            // Get the conversion rate from the map
            double conversionRate = CONVERSION_RATES.get(targetCurrency);

            // Calculate the converted amount
            double convertedAmount = amount * conversionRate;

            // Get the country name from the currency code
            String baseCountry = getCountryName(baseCurrency);
            String targetCountry = getCountryName(targetCurrency);

            // Display the result with country names and currency symbols
            resultLabel.setText("Converted Amount: " + convertedAmount + " " + getCurrencySymbol(targetCurrency) +
                    " (" + baseCountry + " to " + targetCountry + ")");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getCountryName(String currencyCode) {
        switch (currencyCode) {
            case "USD":
                return "United States";
            case "EUR":
                return "Eurozone";
            case "GBP":
                return "United Kingdom";
            case "JPY":
                return "Japan";
            case "AUD":
                return "Australia";
            case "CAD":
                return "Canada";
            case "INR":
                return "India";
            default:
                return "";
        }
    }

    private String getCurrencySymbol(String currencyCode) {
        switch (currencyCode) {
            case "USD":
                return "$";
            case "EUR":
                return "€";
            case "GBP":
                return "£";
            case "JPY":
                return "¥";
            case "AUD":
                return "$";
            case "CAD":
                return "$";
            case "INR":
                return "₹";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter();
            }
        });
    }
}
