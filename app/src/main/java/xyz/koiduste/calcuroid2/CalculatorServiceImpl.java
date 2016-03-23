package xyz.koiduste.calcuroid2;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import org.parceler.Parcel;


/**
 * Created by marko on 3/10/16.
 */
@Parcel
public class CalculatorServiceImpl extends MathServiceImpl implements CalculatorService {

    private final static String ADD = "+";
    private final static String SUBTRACT = "-";
    private final static String MULTIPLY = "*";
    private final static String DIVIDE = "/";
    private final static String EQUALS = "=";
    private final static String CLEAR = "C";
    private final static String BRACKETS ="( )";
    private final static String DELETE = "<-";

    private String waitingOperator = "";

    public final ObservableField<String> operandText = new ObservableField<>("");
    public final ObservableField<String> resultText = new ObservableField<>("");
    public final ObservableBoolean commaEnabled = new ObservableBoolean(true);

    @Override
    public double getOperand() {
        if (this.operandText.get().equals("")) {
            return 0;
        } else return Double.parseDouble(this.operandText.get());
        //TODO! Error handling

    }

    @Override
    public double getResult() {
        if (this.resultText.get().equals("")) {
            return 0;
        } else return Double.parseDouble(this.resultText.get());
       // TODO! Error handling

    }

    @Override
    public void calcOperation(String operator) {
        commaEnabled.set(true);
        switch(operator) {
            case ADD:
                resultText.set(String.valueOf(add(getResult(), getOperand())));
                operandText.set("");
                break;
            case SUBTRACT:
                resultText.set(String.valueOf(subtract(getResult(), getOperand())));
                operandText.set("");
                break;
            case MULTIPLY:
                resultText.set(String.valueOf(multiply(getResult(), getOperand())));
                operandText.set("");
                break;
            case DIVIDE:
                resultText.set(String.valueOf(divide(getResult(), getOperand())));
                operandText.set("");
                break;
            case "":
                if (!operandText.get().isEmpty()) {
                    resultText.set(operandText.get());
                }
                operandText.set("");
                break;
            default:
                //throw new RuntimeException("Tundmatu operaator!");
                //TODO Error handling
                break;
        }
        //TODO operandText.set(""); //maybex
    }

    @Override
    public void opAction(String key) {
        commaEnabled.set(true);
        switch (key) {
            case EQUALS:
                calcOperation(waitingOperator);
                operandText.set("");
                waitingOperator = "";
                break;
            case CLEAR:
                operandText.set("");
                resultText.set("");
                break;
            case DELETE:
                operandText.set("");
                break;
            case BRACKETS:
                break;
            default:
                if (!waitingOperator.equals(EQUALS)) {
                    calcOperation(waitingOperator);
                }
                waitingOperator = key;
                break;
        }
    }

    @Override
    public void digitAction(String key) {
        operandText.set(operandText.get()+key);
        if (key.equals(".")) {
            commaEnabled.set(false);
        }
    }


}
