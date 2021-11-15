package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {
    private EditText editTextName, editTextDay, editTextMonth, editTextYear;
    private RadioButton rbMale, rbFemale;
    private CheckBox cbPHP, cbJAVA, cbHTML, cbCSS;
    private Button btnCheck, btnExit2;
    private TextView txtNumCandidates;
    private static int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDay = (EditText) findViewById(R.id.editTextDay);
        editTextMonth = (EditText) findViewById(R.id.editTextMonth);
        editTextYear = (EditText) findViewById(R.id.editTextYear);

        rbMale = (RadioButton) findViewById(R.id.rbMasculino);
        rbFemale = (RadioButton) findViewById(R.id.rbFemenino);

        cbPHP = (CheckBox) findViewById(R.id.cbPHP);
        cbJAVA = (CheckBox) findViewById(R.id.cbJAVA);
        cbHTML = (CheckBox) findViewById(R.id.cbHTML);
        cbCSS = (CheckBox) findViewById(R.id.cbCSS);

        txtNumCandidates = (TextView) findViewById(R.id.textNumCandidates);

        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnExit2 = (Button) findViewById(R.id.btnExit2);
        btnExit2.setVisibility(View.GONE);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(editTextName.getText().toString().trim().equals("")) {
                       throw new Exception();
                    }
                    String name = editTextName.getText().toString();
                    int day = Integer.parseInt(editTextDay.getText().toString()); // no me da tiempo a comprobar si son dias o meses validos
                    int month = Integer.parseInt(editTextMonth.getText().toString());
                    int year = Integer.parseInt(editTextYear.getText().toString());
                    String sex = getString(R.string.male);
                    if(rbFemale.isChecked()) {
                        sex = getString(R.string.female);
                    }
                    String[] knowledge = new String[] {
                            (cbPHP.isSelected() ? "PHP" : "")    ,
                            (cbJAVA.isSelected() ? "JAVA" : "")    ,
                            (cbHTML.isSelected() ? "HTML" : "")    ,
                            (cbCSS.isSelected() ? "CSS" : "")    ,
                    };
                    Intent intent = new Intent(Activity1.this, Activity1_aux.class);

                    intent.putExtra("name", name);
                    intent.putExtra("day", day);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);
                    intent.putExtra("sex", sex);
                    intent.putExtra("knowledge", knowledge);
                    startActivityForResult(intent, 1);

                } catch(Exception ex) {
                    AlertDialog d = new AlertDialog.Builder(Activity1.this).create();
                    d.setCancelable(false);
                    d.setTitle(getText(R.string.atencion));
                    d.setMessage(getText(R.string.errorValors));
                    d.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    d.show();
                }

            }
        });

        btnExit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 1
        if(requestCode==1)
        {
            boolean result = data.getExtras().getBoolean("success");
            if(result) {
                num++;
                txtNumCandidates.setText(String.valueOf(num) + " ");

                rbMale.setSelected(true);
                cbPHP.setSelected(false);
                cbHTML.setSelected(false);
                cbJAVA.setSelected(false);
                cbCSS.setSelected(false);
                editTextName.setText("");
                editTextDay.setText("");
                editTextMonth.setText("");
                editTextYear.setText("");

                if(num == 4) {
                    btnExit2.setVisibility(View.VISIBLE);
                    btnCheck.setVisibility(View.GONE);
                }
            }
            else {
                editTextName.setText(data.getExtras().getString("name"));
                editTextDay.setText(String.valueOf(data.getExtras().getInt("day")));
                editTextMonth.setText(String.valueOf(data.getExtras().getInt("month")));
                editTextYear.setText(String.valueOf(data.getExtras().getInt("year")));
                String sex = data.getExtras().getString("sex");
                if(sex.equals("male")) {
                    rbMale.setSelected(true);
                    rbFemale.setSelected(false);
                } else {
                    rbFemale.setSelected(true);
                    rbMale.setSelected(false);
                }
                for(String know : data.getExtras().getStringArray("knowledge")) {
                    if(know.equals("PHP")) {
                        cbPHP.setSelected(true);
                    }
                    if(know.equals("JAVA")) {
                        cbJAVA.setSelected(true);
                    }
                    if(know.equals("HTML")) {
                        cbHTML.setSelected(true);
                    }
                    if(know.equals("CSS")) {
                        cbCSS.setSelected(true);
                    }
                }
            }
        }
    }
}