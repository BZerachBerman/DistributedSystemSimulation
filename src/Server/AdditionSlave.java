package Server;

import Shared.mathProblem;

import java.util.concurrent.TimeUnit;

/**
 * Calculator that is optimized to doing addition quickly (2s) and subtraction slowly (10s).
 */
public class AdditionSlave {
    public int solve(mathProblem problem) {
        if (problem.operator().equals("+")) {
            return add(problem.right(),  problem.left());
        }
        else return subtract(problem.right(), problem.left());
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
