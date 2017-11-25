package com.example.jason.randomlist;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pushtorefresh.storio2.sqlite.queries.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView topicsListView = (ListView) findViewById(R.id.topicsList);

        final EditText etNewWord = (EditText) findViewById(R.id.etNewWord);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String storedTopicsString = pref.getString("topics", "");

        String[] topicsArray = {"movement", "opposite", "gravity", "heavy"};
        List<String> topics2 = new ArrayList<>(Arrays.asList(topicsArray));
        // Overwrite with local storage:
        if (!storedTopicsString.equals("") ) {
            topics2 = new ArrayList<>(Arrays.asList(storedTopicsString.split("\t")));
        }
        final List<String> topics  =  topics2;
        
        final ArrayAdapter topicsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, topics);
        topicsListView.setAdapter(topicsAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etText = etNewWord.getText().toString();
                if (etText.length() == 0) {
                    return;
                }
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

                etRandom.setText(word);
                hideSoftKeyboard();
            }
        });


        Log.d("three", "threeee");

        StringBuilder sb = new StringBuilder();
        for (String s : topics)
        {
            sb.append(s);
            sb.append("\t");
        }

        String arrayStr = sb.toString();



    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    int getRandomNuber(int min, int max) {
        // Get Random Numbers:
        int random = (int) (Math.random() * max + min);
        return random;
    }
}
