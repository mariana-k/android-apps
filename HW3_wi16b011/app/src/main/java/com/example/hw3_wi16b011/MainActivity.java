package com.example.hw3_wi16b011;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText hwEditText1;
    private EditText hwEditText2;
    private TextView hwTextView2;
    private Button hwButton;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hwButton = (Button) findViewById(R.id.idofButton);
        hwEditText1 = (EditText ) findViewById(R.id.idofEditText1);
        hwEditText2 = (EditText ) findViewById(R.id.idofEditText2);
        hwTextView2 = (TextView) findViewById(R.id.idofTextView2);

        save();
        hwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(v);
                contacts.add(new Contact(hwEditText1.getText().toString(), hwEditText2.getText().toString()));
            }
        });

        // ...
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Initialize contacts
        contacts = Contact.createContactsList(20);

        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertUsingJava8(ArrayList<String> names)
    {
        return String.join(",", names);
    }

    public void load(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput("contacts_file.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            hwTextView2.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save() {


        String text = "hello";
        FileOutputStream fos = null;

        try {
            fos = openFileOutput("contacts_file.txt", MODE_PRIVATE);
            fos.write(text.getBytes());


            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "contacts_file.txt",
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}