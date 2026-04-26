public class Master {
    AdditionCalculator additionCalculator;
    SubtractionCalculator subtractionCalculator;
    public void start() {
        additionCalculator = new AdditionCalculator();
        subtractionCalculator = new SubtractionCalculator();
        int newNum1 = additionCalculator.add(1, 1);
        int newNum2 = subtractionCalculator.subtract(1, 3);
        int newNum3 = additionCalculator.subtract(3, 2);
        int newNum4 = subtractionCalculator.add(4, 2);
        System.out.println(newNum1);
        System.out.println(newNum2);
        System.out.println(newNum3);
        System.out.println(newNum4);
    }
}
