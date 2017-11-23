package com.example.jason.randomlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] topicsArray = {"movement", "opposite", "gravity", "heavy"};

        final List<String> topics = new ArrayList<>(Arrays.asList(topicsArray));
//        ArrayList<String> places = new ArrayList<String>(
//        Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));


        final ArrayAdapter topicsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, topics);
        final ListView topicsListView = (ListView) findViewById(R.id.topicsList);
        topicsListView.setAdapter(topicsAdapter);

        final EditText etNewWord = (EditText) findViewById(R.id.etNewWord);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etText = etNewWord.getText().toString();
                Log.d("BUTTON", etText);
                topics.add(etText);
                etNewWord.setText("");
                topicsAdapter.notifyDataSetChanged();
            }
        });

        Button btnRandom = (Button) findViewById(R.id.btnRandom);
        final TextView etRandom = (TextView) findViewById(R.id.tvRandom);
        btnRandom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int randomNumber = getRandomNuber(0, topics.size());
                String word = topics.get(randomNumber);
                Log.d("BUTTON", word);
                Toast.makeText(getApplicationContext(), word, Toast.LENGTH_LONG).show();
                etRandom.setText(word);
            }
        });

    }

    int getRandomNuber(int min, int max) {
        // Get Random Numbers:
        int random = (int) (Math.random() * max + min);
        return random;
    }
}
