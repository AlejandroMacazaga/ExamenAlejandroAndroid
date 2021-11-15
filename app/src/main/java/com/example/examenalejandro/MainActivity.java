package com.example.examenalejandro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnDialogoListener {
    private DialogoLogin dLogin;
    private FragmentManager fgm;
    private final static String USERNAME = "usuario";
    private final static String PASSWORD = "123456";

    private Button btnAct1, btnAct2, btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fgm = getSupportFragmentManager();
        dLogin= new DialogoLogin();
        dLogin.show(fgm,"Dialog");

        btnAct1 = (Button) findViewById(R.id.btnActividad1);
        btnAct2 = (Button) findViewById(R.id.btnActividad2);
        btnExit = (Button) findViewById(R.id.btnExit);

        btnAct1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Activity1.class);
                startActivity(i);
            }
        });

        btnAct2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivity(i);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog d = new AlertDialog.Builder(MainActivity.this).create();
                d.setCancelable(false);
                d.setTitle(getText(R.string.info));
                d.setMessage(getText(R.string.goodbye));
                d.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                d.show();
            }
        });


    }
    public void onPositiveButtonClick() {
        String username = dLogin.getEditTextUsername().getText().toString();
        String password = dLogin.getEditTextPassword().getText().toString();

        if(!username.equals(USERNAME) || !password.equals(PASSWORD)){
            AlertDialog d = new AlertDialog.Builder(MainActivity.this).create();
            d.setCancelable(false);
            d.setTitle(getText(R.string.atencion));
            d.setMessage(getText(R.string.errorUserPass2));
            d.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            d.show();
        }

    }

    @Override
    public void onNegativeButtonClick() {
        AlertDialog d = new AlertDialog.Builder(MainActivity.this).create();
        d.setCancelable(false);
        d.setTitle(getText(R.string.atencion));
        d.setMessage(getText(R.string.errorUserPass));
        d.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        d.show();
    }
}