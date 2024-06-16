package repository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JTextField equationField;
    private JButton calculateButton;
    private JLabel resultLabel;

    private Model model;

    public GUI() {
        super("Equation Solver");
        model = new Model();
        initializeComponents();
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        equationField = new JTextField(20);
        calculateButton = new JButton("Calculate");
        resultLabel = new JLabel();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateEquation();
            }
        });

        add(equationField);
        add(calculateButton);
        add(resultLabel);
    }

    private void calculateEquation() {
        String equation = equationField.getText();
        double result = model.solveEquation(equation);
        if (Double.isNaN(result)) {
            resultLabel.setText("Error: Division by zero or invalid input");
        } else {
            resultLabel.setText("Result: " + result);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
