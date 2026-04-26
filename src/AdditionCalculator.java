import java.util.concurrent.TimeUnit;
public class AdditionCalculator {
    public int solve(mathProblem problem) {
        if (problem.operation().equals("addition")) {
            return add(problem.num1(),  problem.num2());
        }
        else return subtract(problem.num1(), problem.num2());
    }

    private int add(int i, int i1) {

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("addition calculator just spent 2 seconds adding " + i + " and " + i1);
        return i + i1;
    }

    private int subtract(int i, int i1) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("addition calculator just spent 10 seconds subtracting " + i1 + " from " + i);
        return i - i1;
    }


}
