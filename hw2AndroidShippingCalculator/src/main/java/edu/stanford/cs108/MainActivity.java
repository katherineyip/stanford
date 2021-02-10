package edu.stanford.cs108;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioButton radioNextDay;
    private RadioButton radioSecondDay;
    private RadioButton radioStandard;
    private CheckBox checkboxInsurance;
    private TextView shippingPrice;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioNextDay = findViewById(R.id.radio_next_day);
        radioNextDay.setChecked(true);

        radioSecondDay = findViewById(R.id.radio_second_day);
        radioStandard = findViewById(R.id.radio_standard);

        checkboxInsurance = findViewById(R.id.checkbox_insurance);
        checkboxInsurance.setChecked(true);

        shippingPrice = findViewById(R.id.shippingPrice);
        shippingPrice.setText("TBD");

        editText = findViewById(R.id.input_weight);
    }

    public void onClickCalculateCost(View view) {
        String input = editText.getText().toString();

        if (!input.equals("")) {
            float weight = Float.parseFloat(input);
            float shippingRate = 0;
            float insuranceRate = (float) 0.0;

            if (radioNextDay.isChecked()) {
                shippingRate = 10;
            } else if (radioSecondDay.isChecked()) {
                shippingRate = 5;
            } else if (radioStandard.isChecked()) {
                shippingRate = 3;
            } else {
                throw new IllegalStateException("Check radio buttons");
            }

            if (checkboxInsurance.isChecked()) {
                insuranceRate = (float) 0.2;
            }

            float totalCost = (1 + insuranceRate) * shippingRate * weight;
            int roundedCost = (int) Math.ceil(totalCost);
            shippingPrice.setText("$" + String.valueOf(roundedCost));
        }
    }
}