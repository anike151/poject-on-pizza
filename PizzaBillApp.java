import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaBillApp extends JFrame implements ActionListener {
    private JComboBox<String> sizeComboBox;
    private JCheckBox cheeseCheckBox;
    private JCheckBox mushroomCheckBox;
    private JCheckBox pepperoniCheckBox;
    private JTextArea outputTextArea;
    private JButton calculateButton;
    private JLabel pizzaImageLabel;

    private final double GST_RATE = 0.18; // 18% GST

    public PizzaBillApp() {
        setTitle("Pizza Bill Generator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Load and add pizza image
        ImageIcon pizzaIcon = new ImageIcon("Pizza-3007395.jpg"); // 🔥 make sure "pizza.png" is in the project folder
        Image pizzaImage = pizzaIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        pizzaImageLabel = new JLabel(new ImageIcon(pizzaImage));
        pizzaImageLabel.setBounds(180, 10, 120, 120);
        add(pizzaImageLabel);

        JLabel sizeLabel = new JLabel("Select Size:");
        sizeLabel.setBounds(50, 150, 100, 20);
        add(sizeLabel);

        String[] sizes = {"Small ($5)", "Medium ($7)", "Large ($10)"};
        sizeComboBox = new JComboBox<>(sizes);
        sizeComboBox.setBounds(150, 150, 150, 20);
        add(sizeComboBox);

        cheeseCheckBox = new JCheckBox("Cheese (+$1.00)");
        cheeseCheckBox.setBounds(50, 190, 200, 20);
        add(cheeseCheckBox);

        mushroomCheckBox = new JCheckBox("Mushroom (+$1.50)");
        mushroomCheckBox.setBounds(50, 220, 200, 20);
        add(mushroomCheckBox);

        pepperoniCheckBox = new JCheckBox("Pepperoni (+$2.00)");
        pepperoniCheckBox.setBounds(50, 250, 200, 20);
        add(pepperoniCheckBox);

        calculateButton = new JButton("Generate Bill");
        calculateButton.setBounds(150, 290, 150, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        outputTextArea = new JTextArea();
        outputTextArea.setBounds(50, 340, 380, 200);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(outputTextArea);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double subtotal = 0.0;
        StringBuilder bill = new StringBuilder();
        bill.append("---- Pizza Bill ----\n");

        // Pizza size selection
        String size = (String) sizeComboBox.getSelectedItem();
        if (size.contains("Small")) {
            subtotal += 5.0;
            bill.append("- Small Pizza: $5.00\n");
        } else if (size.contains("Medium")) {
            subtotal += 7.0;
            bill.append("- Medium Pizza: $7.00\n");
        } else if (size.contains("Large")) {
            subtotal += 10.0;
            bill.append("- Large Pizza: $10.00\n");
        }

        // Toppings selection
        if (cheeseCheckBox.isSelected()) {
            subtotal += 1.0;
            bill.append("- Extra Cheese: $1.00\n");
        }
        if (mushroomCheckBox.isSelected()) {
            subtotal += 1.5;
            bill.append("- Mushrooms: $1.50\n");
        }
        if (pepperoniCheckBox.isSelected()) {
            subtotal += 2.0;
            bill.append("- Pepperoni: $2.00\n");
        }

        // GST calculation
        double gstAmount = subtotal * GST_RATE;
        double totalAmount = subtotal + gstAmount;

        // Final bill summary
        bill.append("-------------------------\n");
        bill.append(String.format("Subtotal:    $%.2f\n", subtotal));
        bill.append(String.format("GST (18%%):   $%.2f\n", gstAmount));
        bill.append("-------------------------\n");
        bill.append(String.format("Total:       $%.2f\n", totalAmount));

        outputTextArea.setText(bill.toString());
    }

    public static void main(String[] args) {
        new PizzaBillApp();
    }
}