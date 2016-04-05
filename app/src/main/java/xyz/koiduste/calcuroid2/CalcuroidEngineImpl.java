package xyz.koiduste.calcuroid2;

/**
 * Created by marko on 4/5/16.
 */
public class CalcuroidEngineImpl implements CalcuroidEngine {
    private MathService service = new MathServiceImpl();
    @Override
    public double getResult(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return service.add(num1, num2);

            case "-":
                return service.subtract(num1, num2);

            case "*":
                return service.multiply(num1, num2);

            case "/":
                return service.divide(num1, num2);
            default:
                throw new RuntimeException("Unknown operator: "+ op);
        }
    }
}
