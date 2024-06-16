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
        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        equationField = new JTextField(25);
        calculateButton = new JButton("Calculate");
        resultLabel = new JLabel();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateEquation();
            }
        });

        add(new JLabel("Enter equation:"));
        add(equationField);
        add(calculateButton);
        add(resultLabel);
    }

    private void calculateEquation() {
        String equation = equationField.getText();
        try {
            if (!isBalanced(equation)) {
                throw new IllegalArgumentException("Unbalanced parentheses");
            }
            double result = model.solveEquation(equation);
            if (Double.isNaN(result)) {
                resultLabel.setText("Error: Division by zero or invalid input");
            } else {
                resultLabel.setText("Result: " + result);
            }
        } catch (IllegalArgumentException e) {
            resultLabel.setText("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            resultLabel.setText("Calculation Error: " + e.getMessage());
        }
    }

    private boolean isBalanced(String equation) {
        int balance = 0;
        for (char ch : equation.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
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
