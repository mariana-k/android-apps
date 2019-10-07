package com.example.hw2_wi16b011;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_wi16b011.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView hw1TextView;
    private Button hw1Button;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private Integer pageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hw1Button = (Button) findViewById(R.id.idofButton);
        hw1TextView = (TextView ) findViewById(R.id.idofTextView);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        hw1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            pageNumber++;
            makeCardsSearchQuery(pageNumber);
            }
        });
    }

    private void showErrorMessage() {
        Context context = getApplicationContext();
        mErrorMessageDisplay = (TextView)findViewById(R.id.error_message_display);
        CharSequence text = mErrorMessageDisplay.getText().toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void makeCardsSearchQuery(int pageNumber) {
        String cardsQuery = Integer.toString(pageNumber);
        URL cardsSearchUrl = NetworkUtils.buildUrl(cardsQuery);
        hw1TextView.setText(cardsSearchUrl.toString());
        new CardsQueryTask().execute(cardsSearchUrl);
    }

    public class CardsQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            hw1Button.setEnabled(false);
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(URL... params) {
            URL cardsUrl = params[0];
            String cardsResults = null;
            try {
                cardsResults = NetworkUtils.getResponseFromHttpUrl(cardsUrl);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Exception found ", e.getMessage());
            }
            return cardsResults;
        }

        @Override
        protected void onPostExecute(String cardsResults) {
            if (cardsResults != null && !cardsResults.equals("")) {

                try {
                    JSONArray cards = null;
                    JSONObject cardsJson = new JSONObject(cardsResults);
                    cards = cardsJson.getJSONArray("cards");
                    ArrayList<MagicCard> magicCards = new ArrayList<>();

                    StringBuilder content = new StringBuilder();
                    for(int i = 0; i < cards.length(); i++) {
                        JSONObject card = cards.getJSONObject(i);
                        String name = card.getString("name");
                        String type = card.getString("type");
                        String rarity = card.getString("rarity");
                        JSONArray colors = card.getJSONArray("colors");
                        ArrayList<String> colorsArray = new ArrayList<String>();
                        MagicCard magicCard = new MagicCard(name, type, rarity);
                        for(int j = 0; j < colors.length(); j++) {
                            colorsArray.add(colors.getString(j));
                            magicCard.addColor(colorsArray.get(j));
                        }
                        magicCards.add(magicCard);
                    }
                    for (int i = 0; i < magicCards.size(); i++) {
                        content.append(magicCards.get(i).getName());
                        content.append(" - ");
                        content.append(magicCards.get(i).getType());
                        content.append(" - ");

                        for(String value : magicCards.get(i).getColors()) {
                            content.append(value);
                            content.append(", ");
                        }
                        content.append(" - ");
                        content.append(magicCards.get(i).getRarity());
                        content.append('\n');
                        content.append("-------------------");
                        content.append('\n');
                    }
                    if (magicCards.size() <= 0) {
                        pageNumber = 1;
                        makeCardsSearchQuery(pageNumber);
                    } else {
                        hw1Button.setEnabled(true);
                        mLoadingIndicator.setVisibility(View.INVISIBLE);
                        hw1TextView.setText(content.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Exception found: ", e.getMessage());
                }


            } else {
                showErrorMessage();
            }
        }
    }

}