package com.example.tic_tac_toe_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Add_Players extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresos_jugadores);
        final EditText PlayerOne= findViewById(R.id.player1);
        final  EditText PlayerTwo = findViewById(R.id.player2);
        final Button start= findViewById(R.id.iniciobtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String getPlayerOneName = PlayerOne.getText().toString();
               final String getPlayerTwoName = PlayerTwo.getText().toString();

               if (getPlayerOneName.isEmpty()||getPlayerTwoName.isEmpty()){
                   Toast.makeText(Add_Players.this,"Porfavor ingrese un nombre",Toast.LENGTH_SHORT).show();
               }else{
                   Intent intent = new Intent(Add_Players.this, MainActivity.class);
                   intent.putExtra("Jugador1", getPlayerOneName );
                   intent.putExtra("Jugador2", getPlayerTwoName);
                   startActivity(intent);
               }

            }
        });
    }
}
