package repository;

public class Controller {
    // EquationController.java

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        while (true) {
            String equation = view.getEquationInput();
            if (equation.equalsIgnoreCase("exit")) {
                break;
            }
            double result = model.solveEquation(equation);
            view.displayResult(result);
        }
    }

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.run();
    }
}
