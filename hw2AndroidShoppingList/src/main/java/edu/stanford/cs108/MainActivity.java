package edu.stanford.cs108;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewGroup mainLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLinearLayout =  findViewById(R.id.linear_layout);
    }

    public void onClickAddItem(View view) {
        EditText editText = findViewById(R.id.text_input);
        String item = editText.getText().toString();
        if (!item.matches("")) {
            TextView textView = new TextView(this);
            textView.setText(item);
            textView.setTextSize(20);
            mainLinearLayout.addView(textView);
            editText.setText(null);
        }
    }

    public void onClickClearList(View view) {
        mainLinearLayout.removeAllViews();
    }
}