package repository;

import java.util.Scanner;

public class View {
    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public String getEquationInput() {
        System.out.print("Enter equation (or 'exit' to quit): ");
        return scanner.nextLine();
    }

    public void displayResult(double result) {
        if (Double.isNaN(result)) {
            System.out.println("Result: NaN");
        } else {
            System.out.println("Result: " + result);
        }
    }

    public void displayError(String message) {
        System.out.println(message);
    }
}
