package com.example.tic_tac_toe_java;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class Dialog_winner extends Dialog {
    private final String message;
    private final MainActivity mainActivity;

    public Dialog_winner(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message ;
        this.mainActivity=mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ganador);
        final TextView messagetxt =findViewById(R.id.textmess);
        final Button playAgain= findViewById(R.id.startAgain);

        messagetxt.setText(message);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mainActivity.playAgain();
                    dismiss();
            }
        });
    }
}
