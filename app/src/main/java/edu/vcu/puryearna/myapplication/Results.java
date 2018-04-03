package edu.vcu.puryearna.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Results extends AppCompatActivity{

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        String res = getIntent().getStringExtra("Result");

        TextView tv = (TextView)findViewById(R.id.TVresult);
        tv.setText(res);
    }

    public void onReturnClick(View v){
        if(v.getId() == R.id.Breturn){

            Intent i = new Intent(Results.this, Welcome.class);
            startActivity(i);
        }
    }

}
