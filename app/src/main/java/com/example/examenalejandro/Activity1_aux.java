package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity1_aux extends AppCompatActivity {
    private TextView textName, textDate, textSex, textKnowledge;
    private Button btnAccept1, btnDeny1;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1_aux);

        textName = (TextView) findViewById(R.id.textName);
        textDate = (TextView) findViewById(R.id.textDate);
        textSex = (TextView) findViewById(R.id.textSex);
        textKnowledge = (TextView) findViewById(R.id.textKnowledge);

        btnAccept1 = (Button) findViewById(R.id.btnAceptar1);
        btnDeny1 = (Button) findViewById(R.id.btnRechazar1);

        bundle = getIntent().getExtras();
        textName.setText(bundle.getString("name"));
        textDate.setText(bundle.getInt("date") + "/" + bundle.getInt("month") + "/" + bundle.getInt("year"));
        textSex.setText(bundle.getString("sex"));
        String know = "";
        for(String k: bundle.getStringArray("knowledge")) {
            if(k.equals("")) {

            }
            else {
                know = know + " " + k;
            }
        }
        textKnowledge.setText(know);
        btnAccept1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Activity1_aux.this, Activity1.class);
                intent.putExtra("success", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnDeny1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Activity1_aux.this, Activity1.class);
                intent.putExtra("name", bundle.getString("name"));
                intent.putExtra("day", bundle.getInt("day"));
                intent.putExtra("month", bundle.getInt("month"));
                intent.putExtra("year", bundle.getInt("year"));
                intent.putExtra("sex", bundle.getString("sex"));
                intent.putExtra("knowledge", bundle.getStringArray("knowledge"));
                intent.putExtra("success", false);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}