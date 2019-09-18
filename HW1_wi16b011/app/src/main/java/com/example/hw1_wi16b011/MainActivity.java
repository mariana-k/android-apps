package com.example.hw1_wi16b011;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView hw1TextView;
    private EditText hw1EditText;
    private Button hw1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hw1Button = (Button) findViewById(R.id.idofButton);
        hw1TextView = (TextView ) findViewById(R.id.idofTextView);
        hw1EditText = (EditText ) findViewById(R.id.idofEditText);

        hw1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = hw1EditText.getText().toString();
                hw1TextView.setText(txt);

           }
        });
    }
}