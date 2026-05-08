package Server;
import Shared.mathProblem;

import java.util.concurrent.TimeUnit;

/**
 * Calculator that is optimized to doing subtraction quickly (2s) and addition slowly (10s).
 */
public class SubtractionSlave {
    public int solve(mathProblem problem) {
        if (problem.operator().equals("-")) {
            return subtract(problem.right(), problem.left());
        } else return add(problem.right(), problem.left());
    }

    private int subtract(int i, int i1) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("subtraction calculator just spent 2 seconds subtracting " + i1 + " from " + i);
        return i - i1;
    }

    private int add(int i, int i1) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("subtraction calculator just spent 10 seconds adding " + i + " and " + i1);
        return i - i1;
    }

}
