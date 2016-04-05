package xyz.koiduste.calcuroid2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CalcuroidReceiver extends BroadcastReceiver {

    private static final String OPERAND = "OPERAND";
    private static final String RESULT = "RESULT";
    private static final String OPERATOR = "OPERATOR";

    private String result;
    private String operand;
    private String operator;

    private CalcuroidEngine engine = new CalcuroidEngineImpl();

    public CalcuroidReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            result = intent.getStringExtra(RESULT);
            operand = intent.getStringExtra(OPERAND);
            operator = intent.getStringExtra(OPERATOR);

            setResultCode(Activity.RESULT_OK);
            setResultData(String.valueOf(engine.getResult(Double.parseDouble(result),Double.parseDouble(operand),operator)));
        }
    }
}
