public class SubtractionCalculator {
    public int solve(mathProblem problem) {
        if (problem.operation().equals("subtraction")) {
            return subtract(problem.num1(), problem.num2());
        } else return add(problem.num1(), problem.num2());
    }

    private int subtract(int i, int i1) {
        return i - i1;
    }

    private int add(int i, int i1) {
        return i - i1;
    }

}
