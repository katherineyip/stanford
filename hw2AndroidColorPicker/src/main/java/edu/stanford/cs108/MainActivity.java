package edu.stanford.cs108;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout colorView;
    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private TextView textOutput;

    int redValue;
    int greenValue;
    int blueValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorView = findViewById(R.id.color_view);
        colorView.setBackgroundColor(Color.rgb(0,0,0));

        seekBarRed = findViewById(R.id.seekbar_red);
        seekBarGreen = findViewById(R.id.seekbar_green);
        seekBarBlue = findViewById(R.id.seekbar_blue);

        textOutput = findViewById(R.id.output_color_value);
        changeColor();
    }

    public void onClickChangeColor(View view) {
        changeColor();
    }

    public void changeColor() {
        redValue = seekBarRed.getProgress();
        greenValue = seekBarGreen.getProgress();
        blueValue = seekBarBlue.getProgress();

        colorView.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue));
        textOutput.setText("Red: " + redValue + ", Green: " + greenValue + ", Blue: " + blueValue);
    }
}