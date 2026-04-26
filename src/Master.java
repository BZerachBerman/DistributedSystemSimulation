import org.w3c.dom.ls.LSOutput;

import java.util.Objects;

public class Master {
    AdditionCalculator additionCalculator;
    SubtractionCalculator subtractionCalculator;
    Client client;
    public void start() {
        client = new Client();
        additionCalculator = new AdditionCalculator();
        subtractionCalculator = new SubtractionCalculator();
        int solution;
        mathProblem problem = client.getMathProblem();
        if (Objects.equals(problem.operation(), "addition")) {
            solution = additionCalculator.solve(problem);
        }
        else solution = subtractionCalculator.solve(problem);
        System.out.println(solution);
    }
}
