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
        Log.d("one", arrayStr);
//        System.out.println(arrayStr);


        final List<String> topics2 =  new ArrayList<>(Arrays.asList(arrayStr.split("\t")));
        System.out.println(topics2.get(0));

        Log.d("f", topics2.get(0));

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String storedTopics = pref.getString("topics", "");


//        destIndexArray = output.toString.split(",")


//
//        StorIOContentResolver storIOContentResolver = DefaultStorIOContentResolver.builder()
//                .contentResolver(yourContentResolver)
//                .addTypeMapping(SomeType.class, typeMapping) // required for object mapping
//                .build();
//
//        List<String> tweets = storIOSQLite
//                .get()
//                .listOfObjects(String.class) // Type safety
//                .withQuery(Query.builder() // Query builder
//                        .table("tweets")
//                        .where("author = ?")
//                        .whereArgs("artem_zin") // Varargs Object..., no more new String[] {"I", "am", "tired", "of", "this", "shit"}
//                        .build()) // Query is immutable — you can save it and share without worries
//                .prepare() // Operation builder
//                .executeAsBlocking(); // Control flow is readable from top to bottom, just like with RxJava
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
