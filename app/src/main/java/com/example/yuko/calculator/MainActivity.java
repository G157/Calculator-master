package com.example.yuko.calculator;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            textView.setText(editText.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        textView = (TextView) findViewById(R.id.textview);

        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        editText = (EditText) findViewById(R.id.edittext);
        editText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonListener);


        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);

    }

    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;

            if (isOperatorKeyPushed == true) {
                editText.setText(button.getText());
            } else {
                editText.append(button.getText());
            }

            isOperatorKeyPushed = false;
        }
    };

    int recentOperator = R.id.button_equal; // 最近押された計算キー
    double result;  // 計算結果
    boolean isOperatorKeyPushed;    // 計算キーが押されたことを記憶

    View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button operatorButton = (Button) view;
            double value = Double.parseDouble(editText.getText().toString());
            if (recentOperator == R.id.button_equal) {
                result = value;
            } else {
                result = calc(recentOperator, result, value);
                editText.setText(String.valueOf(result));
            }

            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            isOperatorKeyPushed = true;
        }
    };

    double calc(int operator, double value1, double value2) {
        switch (operator) {
            case R.id.button_add:
                return value1 + value2;
            case R.id.button_subtract:
                return value1 - value2;
            case R.id.button_multiply:
                return value1 * value2;
            case R.id.button_divide:
                return value1 / value2;
            default:
                return value1;
        }
    }
    public void setFontType(TextView txt) {
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "Qarmic_sans_Abridged.ttf"));
    }


}
