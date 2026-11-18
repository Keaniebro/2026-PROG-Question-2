import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends JFrame {

    JTextField txtCustomer;
    JTextField txtPrice;

    JComboBox<String> cmbProduct;

    JButton btnProcess;

    JMenuItem processItem;
    JMenuItem clearItem;
    JMenuItem exitItem;

    public Main() {

        setTitle("Product App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Customer name
        add(new JLabel("Customer Name:"));

        txtCustomer = new JTextField();
        add(txtCustomer);

        // Product selection
        add(new JLabel("Select Product:"));

        cmbProduct = new JComboBox<>();

        cmbProduct.addItem("Cozy Nights Monarch");
        cmbProduct.addItem("Restonic Barbados");
        cmbProduct.addItem("Sealy Phuket");

        add(cmbProduct);

        // Product price
        add(new JLabel("Enter Price:"));

        txtPrice = new JTextField();
        add(txtPrice);

        // Process button
        btnProcess = new JButton("PROCESS");
        add(btnProcess);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        processItem = new JMenuItem("Process");
        clearItem = new JMenuItem("Clear");
        exitItem = new JMenuItem("Exit");

        fileMenu.add(processItem);
        fileMenu.add(exitItem);

        editMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        // Button and menu events
        btnProcess.addActionListener(e -> Process());

        processItem.addActionListener(e -> Process());

        clearItem.addActionListener(e -> Clear());

        exitItem.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public void Process() {

        try {

            String customer = txtCustomer.getText();

            String product = cmbProduct.getSelectedItem().toString();

            double price = Double.parseDouble(txtPrice.getText());

            ProductCalculations calculations =
                    new ProductCalculations(price);

            double vat = calculations.GetVat();

            double commission = calculations.GetCommission();

            double total = calculations.GetTotal();

            // Display results
            JOptionPane.showMessageDialog(this,
                    "Customer Name: " + customer +
                            "\nProduct: " + product +
                            "\nVAT: R " + vat +
                            "\nCOMMISSION: R " + commission +
                            "\nTOTAL: R " + total);

            // Save to file
            FileWriter writer = new FileWriter("data.txt", true);

            writer.write("DATA LOG\n");
            writer.write("**********\n");
            writer.write("Customer Name: " + customer + "\n");
            writer.write("Product: " + product + "\n");
            writer.write("VAT: R " + vat + "\n");
            writer.write("COMMISSION: R " + commission + "\n");
            writer.write("TOTAL: R " + total + "\n");
            writer.write("**********\n\n");

            writer.close();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid price!");

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(this,
                    "Error saving file!");

        }
    }

    public void Clear() {

        txtCustomer.setText("");
        txtPrice.setText("");

        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        new Main();
    }
}