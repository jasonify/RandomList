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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] topics = {"movement", "opposite", "gravity", "heavy"};
        ListAdapter topicsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, topics);
        ListView topicsListView = (ListView) findViewById(R.id.topicsList);
        topicsListView.setAdapter(topicsAdapter);

        final EditText etNewWord = (EditText) findViewById(R.id.etNewWord);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etText = etNewWord.getText().toString();
                Log.d("BUTTON", etText);
                

            }
        });

    }
}
