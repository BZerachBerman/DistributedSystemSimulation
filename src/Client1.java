public class Client1 {
    mathProblem[] mathProblems;
    int nextProblem;
    Client1() {
        nextProblem = 0;
        mathProblems = new mathProblem[10];
        int randInt1;
        int randInt2;
        for(int i = 0; i < 10; i++) {
            randInt1 = (int) (Math.random() * 10);
            randInt2 = (int) (Math.random() * 10);
            mathProblems[i] = new mathProblem(randInt1, randInt2, Math.random() < 0.5 ? "addition" : "subtraction");
        }
    }

    public mathProblem getMathProblem() {
        int currentProblem = nextProblem;
        nextProblem = currentProblem + 1 % mathProblems.length;
        return mathProblems[currentProblem];
    }
}
