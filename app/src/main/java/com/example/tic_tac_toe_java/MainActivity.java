package com.example.tic_tac_toe_java;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneScore, playerTwoScore, playerStatus,playerOne,playerTwo;
    private Button[] buttons = new Button[9];
    private Button resetGame;
    private int playerOneScoreCount, playerTwoScoreCount,rountCount;
    boolean activePlayer;

   // private final List<int[]> combinationList=new ArrayList<>();



    int [] gameState={2,2,2,2,2,2,2,2,2};

        int[][]winningPosition={
                {0,1,2},{3,4,5},{6,7,8},//filas
                {0,3,6},{1,4,7},{2,5,8},//columnas
                {0,4,8},{2,4,6}//diagonales
        };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerOneScore= (TextView) findViewById(R.id.PlayerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.PlayerTwoScore);
        playerStatus=(TextView) findViewById(R.id.PlayerStatus);
        resetGame=(Button) findViewById(R.id.reset);
        playerOne=findViewById(R.id.PlayerOne);
        playerTwo=findViewById(R.id.PlayerTwo);


         for(int i=0;i<buttons.length;i++){
             String buttonID="btn_"+i;
             int resourceID=getResources().getIdentifier(buttonID,"id",getPackageName());
             buttons[i]=(Button) findViewById(resourceID);
             buttons[i].setOnClickListener(this);
         }
         rountCount=0;
         playerOneScoreCount=0;
         playerTwoScoreCount=0;
         activePlayer=true;


        final String getPlayerOneName=getIntent().getStringExtra("Jugador1");
        final String getPlayerTwoName=getIntent().getStringExtra("Jugador2");
        playerOne.setText(getPlayerOneName);
        playerTwo.setText(getPlayerTwoName);

    }



    @Override
    public void onClick(View v) {
      if(!((Button)v).getText().toString().equals("")){
          return;
      }
      String buttonID = v.getResources().getResourceEntryName(v.getId());
      int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        if (activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer]=0;
        }else{
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer]=1;
        }

        rountCount++;

        if (checkWinner()){
            if (activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                 Dialog_winner dialog_winner= new Dialog_winner(MainActivity.this,"Ganador "+playerOne.getText().toString(), MainActivity.this);
                    dialog_winner.show();
                 //Toast.makeText(this, "player One Ganador",Toast.LENGTH_LONG).show();
            }else{
                playerTwoScoreCount++;
                updatePlayerScore();
                Dialog_winner dialog_winner= new Dialog_winner(MainActivity.this,"Ganador "+ playerTwo.getText().toString(), MainActivity.this);
                dialog_winner.show();
            }
        }else if (rountCount==9){
            Dialog_winner dialog_winner= new Dialog_winner(MainActivity.this,"Sin ganador", MainActivity.this);
            dialog_winner.show();
        }else{
            activePlayer= !activePlayer;
        }
        if (playerOneScoreCount>playerTwoScoreCount){
            playerStatus.setText("Player One is Winning");
        }else if(playerTwoScoreCount>playerOneScoreCount) {
            playerStatus.setText("Player Two is Winning");
        }else {
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });

    }

    public boolean checkWinner(){
        boolean winnerResult=false;
        for (int[]winningPosition:winningPosition){
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2){
                winnerResult=true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        rountCount=0;
        activePlayer=true;
        for (int i=0;i< buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }

}