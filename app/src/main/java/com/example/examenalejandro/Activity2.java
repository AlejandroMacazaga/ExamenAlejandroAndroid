package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private SpinnerAdapter spinnerAdapter;
    private TextView textUbi;
    private Button btnVisualizar, btnVolver;
    private RadioButton btnCosta, btnInterior;
    private String selectedLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_locales, android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        btnVisualizar = (Button) findViewById(R.id.btnVisualizar);
        btnVolver = (Button) findViewById(R.id.btnVolver2);
        btnCosta = (RadioButton) findViewById(R.id.btnCosta);
        btnInterior = (RadioButton) findViewById(R.id.btnInterior);
        textUbi = (TextView) findViewById(R.id.textUbi);
        textUbi.setVisibility(View.GONE);
        btnCosta.setVisibility(View.GONE);
        btnInterior.setVisibility(View.GONE);
        btnVisualizar.setVisibility(View.GONE);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!selectedLocal.equals("Araba") && selectedLocal.equals("Bizkaia") && selectedLocal.equals("Gipuzkua")) {
                        AlertDialog d = new AlertDialog.Builder(Activity2.this).create();
                        d.setCancelable(false);
                        d.setTitle(getText(R.string.info));
                        d.setMessage(getText(R.string.infolocal));
                        d.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        d.show();
                    }
                }
        });
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        TextView tvMensaje=(TextView) view;
        String localidades[] = getResources().getStringArray(R.array.array_locales);
        selectedLocal = localidades[position];
        if(selectedLocal.equals("Bizkaia") || selectedLocal.equals("Gipuzkua")) {
            textUbi.setVisibility(View.VISIBLE);
            btnCosta.setVisibility(View.VISIBLE);
            btnInterior.setVisibility(View.VISIBLE);
        }

        btnVisualizar.setVisibility(View.VISIBLE);;

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        btnVisualizar.setVisibility(View.GONE);
        selectedLocal = null;
    }
}