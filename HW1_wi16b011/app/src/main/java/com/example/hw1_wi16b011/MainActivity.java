package com.example.hw1_wi16b011;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView hw1TextView2;
    private TextView hw1TextView;
    private EditText hw1EditText1;
    private EditText hw1EditText2;
    private Button hw1Button;
    private Button hw1ButtonNavigate;
    private SeekBar simpleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hw1Button = (Button) findViewById(R.id.idofButton);
        hw1ButtonNavigate = (Button) findViewById(R.id.idofButtonNavigate);
        hw1TextView = (TextView ) findViewById(R.id.idofTextView);
        hw1TextView2 = (TextView ) findViewById(R.id.idofTextView2);
        hw1EditText1 = (EditText ) findViewById(R.id.idofEditText1);
        hw1EditText2 = (EditText ) findViewById(R.id.idofEditText2);
        simpleSeekBar = (SeekBar) findViewById(R.id.simpleSeekBar);

        simpleSeekBar.setProgress(Integer.parseInt(hw1TextView2.getText().toString()));

        hw1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int input1 = TextUtils.isDigitsOnly(hw1EditText1.getText())
                    ? Integer.parseInt(hw1EditText1.getText().toString()) : 0;
            int input2 = TextUtils.isDigitsOnly(hw1EditText2.getText())
                    ? Integer.parseInt(hw1EditText2.getText().toString()) : 0;

            int sum = input1 + input2;
            String result = Integer.toString(sum);
            hw1TextView.setText(result);

           }
        });

        hw1ButtonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int input1 = TextUtils.isDigitsOnly(hw1EditText1.getText())
                        ? Integer.parseInt(hw1EditText1.getText().toString()) : 0;
                int input2 = TextUtils.isDigitsOnly(hw1EditText2.getText())
                        ? Integer.parseInt(hw1EditText2.getText().toString()) : 0;

                int sum = input1 + input2;
                String result = Integer.toString(sum);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra(Intent.EXTRA_TEXT, result);
                startActivity(intent);
            }
        });

        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
                hw1TextView2.setText(Integer.toString(progressChangedValue));
            }
        });
    }
}