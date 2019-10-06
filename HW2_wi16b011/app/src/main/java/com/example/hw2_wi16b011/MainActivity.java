package com.example.hw2_wi16b011;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_wi16b011.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView hw1TextView;
    private Button hw1Button;
    private TextView mErrorMessageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hw1Button = (Button) findViewById(R.id.idofButton);
        hw1TextView = (TextView ) findViewById(R.id.idofTextView);
        mErrorMessageDisplay = (TextView) findViewById(R.id.error_message_display);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        hw1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Result placeholder";

                hw1TextView.setText(result);
                makeCardsSearchQuery();
            }
        });
    }

    private void makeCardsSearchQuery() {
        String cardsQuery = "1";
        URL cardsSearchUrl = NetworkUtils.buildUrl(cardsQuery);
        hw1TextView.setText(cardsSearchUrl.toString());
        new CardsQueryTask().execute(cardsSearchUrl);
    }

    public class CardsQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL cardsUrl = params[0];
            String cardsResults = null;
            try {
                cardsResults = NetworkUtils.getResponseFromHttpUrl(cardsUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return cardsResults;
        }

        @Override
        protected void onPostExecute(String cardsResults) {
            if (cardsResults != null && !cardsResults.equals("")) {
                hw1TextView.setText(cardsResults);
            } else {
                mErrorMessageDisplay.setVisibility(View.VISIBLE);
            }
        }
    }

}