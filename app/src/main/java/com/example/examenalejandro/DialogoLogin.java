package com.example.examenalejandro;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class DialogoLogin extends DialogFragment {

    private OnDialogoListener listener;
    private EditText editTextUsername, editTextPassword;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_login, null);
        builder.setView(v).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegativeButtonClick();
                dialog.cancel();
            }
        }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositiveButtonClick();
                dialog.cancel();
            }
        });

        editTextUsername = v.findViewById(R.id.editTextUsername);
        editTextPassword = v.findViewById(R.id.editTextPassword);

        return builder.create();
    }

    public EditText getEditTextUsername() {
        return editTextUsername;
    }

    public EditText getEditTextPassword() {
        return editTextPassword;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (OnDialogoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +" no ha sido implementado!!!");
        }
    }

}
