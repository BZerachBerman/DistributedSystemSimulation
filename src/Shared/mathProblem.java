package Shared;

/**
 * A math problem.
 * There's no need for an actual math problem.
 * @param problemID To keep track of which problem it is.
 * @param left The left integer
 * @param operator The operator
 * @param right The right integer
 */

public record mathProblem(int problemID, int left, String operator, int right){}
