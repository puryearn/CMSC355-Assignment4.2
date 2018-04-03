package edu.vcu.puryearna.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onButtonClick(View v){

        if(v.getId()==R.id.Bentervalues){
            Intent i = new Intent(Welcome.this, EnterValues.class);
            startActivity(i);
        }
        if(v.getId()==R.id.Bsearchword){
            EditText sw = (EditText)findViewById(R.id.TFsearchword);
            String str = sw.getText().toString();

            String res = helper.searchPair(str);

            Intent i = new Intent(Welcome.this,Results.class);
            i.putExtra("Result",res);
            startActivity(i);
        }

    }

}
