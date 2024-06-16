package repository;

import java.util.Scanner;

public class View {
    // EquationView.java
    public void displayResult(double result) {
        System.out.println("Результат: " + result);
    }

    public String getEquationInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите математическое выражение: ");
        return scanner.nextLine();
    }
}

