import java.util.Objects;

public class Master {
    AdditionCalculator additionCalculator;
    SubtractionCalculator subtractionCalculator;
    Client1 client1;
    Client2 client2;

    public void start() {
        client1 = new Client1();
        client2 = new Client2();
        additionCalculator = new AdditionCalculator();
        subtractionCalculator = new SubtractionCalculator();
        int solution;
        for (int i = 1; i <= 10; i++) {
            mathProblem problem1 = client1.getMathProblem();
            mathProblem problem2 = client2.getMathProblem();
            if (Objects.equals(problem1.operation(), "addition")) {
                solution = additionCalculator.solve(problem1);
            } else solution = subtractionCalculator.solve(problem1);
            System.out.println(solution);
            if (Objects.equals(problem2.operation(), "addition")) {
                solution = additionCalculator.solve(problem2);
            } else solution = subtractionCalculator.solve(problem2);
            System.out.println(solution);
        }
    }
}
