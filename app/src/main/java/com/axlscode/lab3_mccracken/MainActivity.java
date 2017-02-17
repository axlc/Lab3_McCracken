package com.axlscode.lab3_mccracken;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String tag = "AJM";

    private EditText entry_et;
    private Button calculate_btn;
    private TextView result_tv;

    private double rate = 0.2;
    String priceString;
    String rateString;
    String tipAmountString;
    String fullPriceString;
    String result;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // The Bundle provides a way of storing a NVP ("Name-Value Pair") map.
        // It will get passed in to onRestoreInstanceState() OR onCreate()
        Log.i(tag, "onSaveInstanceState: priceString = [" + priceString + "]");
        Log.i(tag, "onSaveInstanceState: rateString = [" + rateString + "]");
        Log.i(tag, "onSaveInstanceState: tipAmountString = [" + tipAmountString + "]");
        Log.i(tag, "onSaveInstanceState: fullPriceString = [" + fullPriceString + "]");
        Log.i(tag, "onSaveInstanceState: result = [" + result + "]");

        savedInstanceState.putString("priceString", priceString);
        savedInstanceState.putString("rateString", rateString);
        savedInstanceState.putString("tipAmountString", tipAmountString);
        savedInstanceState.putString("fullPriceString", fullPriceString);
        savedInstanceState.putString("result", result);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        priceString = savedInstanceState.getString("priceString");
        rateString = savedInstanceState.getString("rateString");
        tipAmountString = savedInstanceState.getString("tipAmountString");
        fullPriceString = savedInstanceState.getString("fullPriceString");
        result = savedInstanceState.getString("result");

        Log.i(tag, "onRestoreInstanceState: priceString = [" + priceString + "]");
        Log.i(tag, "onRestoreInstanceState: rateString = [" + rateString + "]");
        Log.i(tag, "onRestoreInstanceState: tipAmountString = [" + tipAmountString + "]");
        Log.i(tag, "onRestoreInstanceState: fullPriceString = [" + fullPriceString + "]");
        Log.i(tag, "onRestoreInstanceState: result = [" + result + "]");

        // this works, but doesn’t restore the textView.  How to fix???
        result_tv.setText(result);
    }

    // NOTE:  import android.view.View.OnClickListener!!!
    // NOTE:  OnClickListener is an interface
    private View.OnClickListener onClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            Log.i(tag, "onClick: Method Entry");

            if (v.getId() == R.id.calculate_btn) {  // not needed with only one button!

                String s = entry_et.getText().toString();
                entry_et.setText("");

                if (s.length() == 0) {
                    result_tv.setText(getText(R.string.error_string));
                    return;
                }

                Log.i(tag, "onClick: text = [" + s + "]");
                double entry = 0;
                try
                {
                    entry = Double.parseDouble(s);
                }
                catch(NumberFormatException e)
                {
                    Log.i(tag, "OnClick: Double.parseDouble(s) failed.");
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                rateString = String.format(getString(R.string.rate_string), rate * 100);
                priceString = String.format(getString(R.string.price_string), entry);

                double tipAmount = entry * rate;
                tipAmountString = String.format(getString(R.string.amount_string), tipAmount);

                double fullPrice = entry + tipAmount;
                fullPriceString = String.format(getString(R.string.full_price_string), fullPrice);

                result = priceString + rateString + tipAmountString + fullPriceString;
                result_tv.setText(result);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entry_et      = (EditText) findViewById(R.id.entry_et);
        calculate_btn = (Button) findViewById(R.id.calculate_btn);
        result_tv     = (TextView) findViewById(R.id.result_tv);

        calculate_btn.setOnClickListener(onClickListener);
    }
}