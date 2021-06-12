package com.vinayalt.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

import static android.content.ContentValues.TAG;

class GFG {
    static class Move { int row, col; };
}
public class PlayerOne extends AppCompatActivity {
    boolean Active = true;
    String Activeuser = "X";
    String won;
    TextView img0, imgX;
    final char[][] gamestate = {{'_', '_', '_'},{ '_', '_', '_'},{ '_', '_', '_'}};
    public char[] emptyspaces = {'_','_','_','_','_','_','_','_','_'};
    static char player = '0', opponent = 'X';
    int[][] winnerpos = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    TextView t0,t1,t2,t3,t4,t5,t6,t7,t8;
    Button back, reset;


    static GFG.Move computermove(char board[][]) {
        int bestVal = -1000;
        GFG.Move bestMove = new GFG.Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Log.e("outside", "gysh"+board[i][j]);
                if (board[i][j] == '_') {
                    Log.e("inside", "gysh"+board[i][j]+" "+i+" "+j);
                    board[i][j] = '0';
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = '_';

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }
    static int minimax(char board[][], int depth, Boolean isMax) {
        int score = evaluate(board);
        if (score == 10) return score;
        if (score == -10) return score;
        if (isMovesLeft(board) == false) return 0;
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j]=='_') {
                        board[i][j] = player;
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
        else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = opponent;
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }
    static int evaluate(char b[][]) {
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == player) return +10;
                else if (b[row][0] == opponent) return -10;
            }
        }
        for (int col = 0; col < 3; col++){
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == player) return +10;
                else if (b[0][col] == opponent) return -10;
            }
        }
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player) return +10;
            else if (b[0][0] == opponent) return -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player) return +10;
            else if (b[0][2] == opponent) return -10;
        }
        return 0;
    }
    static Boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_one);
        t0 = findViewById(R.id.text0);
        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
        t3 = findViewById(R.id.text3);
        t4 = findViewById(R.id.text4);
        t5 = findViewById(R.id.text5);
        t6 = findViewById(R.id.text6);
        t7 = findViewById(R.id.text7);
        t8 = findViewById(R.id.text8);
        img0 = findViewById(R.id.img0);
        imgX = findViewById(R.id.imgX);
        back = findViewById(R.id.end);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerOne.this, HomeScreen.class);
                PlayerOne.this.startActivity(intent);
            }
        });
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<3; i+=1) {
                    for (int j=0;j<3;j+=1){
                        gamestate[i][j] = '_';
                    }
                }
                Active= true;
                Activeuser="X";
                won=null;
                imgX.setBackgroundResource(R.drawable.activebackgound);
                img0.setBackgroundResource(R.drawable.textviewback);
                ((TextView)findViewById(R.id.text0)).setText("");
                ((TextView)findViewById(R.id.text1)).setText("");
                ((TextView)findViewById(R.id.text2)).setText("");
                ((TextView)findViewById(R.id.text3)).setText("");
                ((TextView)findViewById(R.id.text4)).setText("");
                ((TextView)findViewById(R.id.text5)).setText("");
                ((TextView)findViewById(R.id.text6)).setText("");
                ((TextView)findViewById(R.id.text7)).setText("");
                ((TextView)findViewById(R.id.text8)).setText("");
            }
        });
        imgX.setBackgroundResource(R.drawable.activebackgound);
    }
    public void tapped(View view) {
        if (Activeuser == "X") {
            TextView t = (TextView) view;
            String tapped = t.getText().toString();
            if (tapped != "0" && tapped != "X") {
                if (Active) {
                    t.setText(Activeuser);
                    int number = Integer.parseInt(t.getTag().toString());
                    int count = 0;
                    for(int i=0;i<3;i+=1){
                        for (int j=0;j<3;j+=1){
                            if(count==number) gamestate[i][j] = Activeuser.charAt(0);
                            count+=1;
                        }
                    }
                    count = 0;
                    for(int i=0;i<3;i+=1){
                        for (int j=0;j<3;j+=1){
                            emptyspaces[count]=gamestate[i][j];
                            count+=1;
                        }
                    }
                    for(int[] winpos: winnerpos) {
                        if (emptyspaces[winpos[0]]==emptyspaces[winpos[1]] && emptyspaces[winpos[1]] == emptyspaces[winpos[2]] && emptyspaces[winpos[0]]!='_' ) {
                            Active = false;
                            if(emptyspaces[winpos[0]]=='0' ){
                                won = "0 has won";
                                dialog(view);
                            }
                            else if (emptyspaces[winpos[0]]== 'X' ) {
                                won = "X has won";
                                dialog(view);
                            }
                        }
                    }
                }
            }
            String a = "";
            for (int i=0;i<3;i+=1){
                for (int j=0;j<3;j+=1){
                    a= a+gamestate[i][j];
                }
            }
            int b = -1;
            b=a.indexOf('_');
            if (b<0) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(PlayerOne.this, R.style.MyDialogTheme);
                builder.setTitle("It's a Draw!");
                final View customLayout = getLayoutInflater().inflate(R.layout.alertbox, null);
                builder.setView(customLayout);
                builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0; i<3; i+=1) {
                            for (int j=0;j<3;j+=1){
                                gamestate[i][j] = '_';
                            }
                        }
                        won=null;
                        Active= true;
                        Activeuser="X";
                        img0.setBackgroundResource(R.drawable.activebackgound);
                        imgX.setBackgroundResource(R.drawable.textviewback);
                        ((TextView)findViewById(R.id.text0)).setText("");
                        ((TextView)findViewById(R.id.text1)).setText("");
                        ((TextView)findViewById(R.id.text2)).setText("");
                        ((TextView)findViewById(R.id.text3)).setText("");
                        ((TextView)findViewById(R.id.text4)).setText("");
                        ((TextView)findViewById(R.id.text5)).setText("");
                        ((TextView)findViewById(R.id.text6)).setText("");
                        ((TextView)findViewById(R.id.text7)).setText("");
                        ((TextView)findViewById(R.id.text8)).setText("");
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Back to Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });
                builder.create();
                builder.show();
            }

            if (Activeuser=="0"){
                imgX.setBackgroundResource(R.drawable.activebackgound);
                img0.setBackgroundResource(R.drawable.textviewback);
                Activeuser = "X";
            } else {
                img0.setBackgroundResource(R.drawable.activebackgound);
                imgX.setBackgroundResource(R.drawable.textviewback);
                Activeuser = "0";
            }
            if (won==null) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {@Override public void run() { Computermove(gamestate); }}, 800);
            }
        } else {
            Toast.makeText(this, "It is computer's Turn", Toast.LENGTH_SHORT).show();
        }
    }
    private void Computermove(char[][] gamestate) {
        GFG.Move compmove =  computermove(gamestate);
        if (!(compmove.row==-1)  || !(compmove.col==-1)){
            gamestate[compmove.row][compmove.col] = '0';
        }
            if ((compmove.row==0) && (compmove.col==0) ) t0.setText("0");
            if ((compmove.row==0) && (compmove.col==1) ) t1.setText("0");
            if ((compmove.row==0) && (compmove.col==2) ) t2.setText("0");
            if ((compmove.row==1) && (compmove.col==0) ) t3.setText("0");
            if ((compmove.row==1) && (compmove.col==1) ) t4.setText("0");
            if ((compmove.row==1) && (compmove.col==2) ) t5.setText("0");
            if ((compmove.row==2) && (compmove.col==0) ) t6.setText("0");
            if ((compmove.row==2) && (compmove.col==1) ) t7.setText("0");
            if ((compmove.row==2) && (compmove.col==2) ) t8.setText("0");

        int count = 0;
        for(int i=0;i<3;i+=1){
            for (int j=0;j<3;j+=1){
                emptyspaces[count]=gamestate[i][j];
                count+=1;
            }
        }
        for(int[] winpos: winnerpos) {
            if (emptyspaces[winpos[0]]==emptyspaces[winpos[1]] && emptyspaces[winpos[1]] == emptyspaces[winpos[2]] && emptyspaces[winpos[0]]!='_' ) {
                Active = false;
                if(emptyspaces[winpos[0]]=='0' ){
                    won = "0 has won";
                    dialog_comp();
                    Toast.makeText(this, won, Toast.LENGTH_SHORT).show();
                }
                else if (emptyspaces[winpos[0]]== 'X' ) {
                    won = "X has won";
                    dialog_comp();
                    Toast.makeText(this, won, Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (Activeuser=="0"){
            imgX.setBackgroundResource(R.drawable.activebackgound);
            img0.setBackgroundResource(R.drawable.textviewback);
            Activeuser = "X";
        } else {
            img0.setBackgroundResource(R.drawable.activebackgound);
            imgX.setBackgroundResource(R.drawable.textviewback);
            Activeuser = "0";
        }
    }
    public void dialog(View v) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(PlayerOne.this, R.style.MyDialogTheme);
        builder.setTitle("Congrats! "+won);
        final View customLayout = getLayoutInflater().inflate(R.layout.alertbox, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0; i<3; i+=1) {
                    for (int j=0;j<3;j+=1){
                        gamestate[i][j] = '_';
                    }
                }
                Active= true;
                won=null;
                Activeuser="X";
                img0.setBackgroundResource(R.drawable.activebackgound);
                imgX.setBackgroundResource(R.drawable.textviewback);
                ((TextView)findViewById(R.id.text0)).setText("");
                ((TextView)findViewById(R.id.text1)).setText("");
                ((TextView)findViewById(R.id.text2)).setText("");
                ((TextView)findViewById(R.id.text3)).setText("");
                ((TextView)findViewById(R.id.text4)).setText("");
                ((TextView)findViewById(R.id.text5)).setText("");
                ((TextView)findViewById(R.id.text6)).setText("");
                ((TextView)findViewById(R.id.text7)).setText("");
                ((TextView)findViewById(R.id.text8)).setText("");
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Back to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        builder.create();
        builder.show();
    }
    public void dialog_comp() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(PlayerOne.this, R.style.MyDialogTheme);
        builder.setTitle("Congrats! "+won);
        final View customLayout = getLayoutInflater().inflate(R.layout.alertbox, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0; i<3; i+=1) {
                    for (int j=0;j<3;j+=1){
                        gamestate[i][j] = '_';
                    }
                }
                won=null;
                Active= true;
                Activeuser="X";
                img0.setBackgroundResource(R.drawable.activebackgound);
                imgX.setBackgroundResource(R.drawable.textviewback);
                ((TextView)findViewById(R.id.text0)).setText("");
                ((TextView)findViewById(R.id.text1)).setText("");
                ((TextView)findViewById(R.id.text2)).setText("");
                ((TextView)findViewById(R.id.text3)).setText("");
                ((TextView)findViewById(R.id.text4)).setText("");
                ((TextView)findViewById(R.id.text5)).setText("");
                ((TextView)findViewById(R.id.text6)).setText("");
                ((TextView)findViewById(R.id.text7)).setText("");
                ((TextView)findViewById(R.id.text8)).setText("");
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Back to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        builder.create();
        builder.show();
    }




}




