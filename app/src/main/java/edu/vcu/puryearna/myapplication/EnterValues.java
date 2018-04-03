package edu.vcu.puryearna.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EnterValues extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_values);
    }

    public void onSubmitClick(View v){
        if(v.getId() == R.id.Bsubmit){
            EditText worda = (EditText)findViewById(R.id.TFworda);
            EditText wordb = (EditText)findViewById(R.id.TFwordb);

            String wordastr = worda.getText().toString();
            String wordbstr = wordb.getText().toString();

            WordPair wp = new WordPair();
            wp.setWord1(wordastr);
            wp.setWord2(wordbstr);

            helper.insertWordPair(wp);

            Intent i = new Intent(EnterValues.this, Welcome.class);
            startActivity(i);
        }
    }

}
