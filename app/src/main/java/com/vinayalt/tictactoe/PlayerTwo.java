package com.vinayalt.tictactoe;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerTwo extends AppCompatActivity {
    public char activeplayer = '0';
    Button back, reset;
    String won;
    String Activeuser = "0";
    boolean Active = true;
    TextView t0, t1,t2,t3,t4,t5,t6,t7,t8;
    TextView img0, imgX;
    char[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winnerpos = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_two);
        back = findViewById(R.id.end);
        img0 = findViewById(R.id.img0);
        imgX = findViewById(R.id.imgX);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerTwo.this, HomeScreen.class);
                PlayerTwo.this.startActivity(intent);
            }
        });

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<gamestate.length; i++) {
                    gamestate[i] = 2;
                }
                Active= true;
                Activeuser="0";
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
            }
        });

        img0.setBackgroundResource(R.drawable.activebackgound);
    }
    public void tapped(View view) {
        TextView t = (TextView) view;
        String tapped = t.getText().toString();
        if (tapped!="0" && tapped !="X"){
            if (Active) {
                t.setText(Activeuser);
                int number = Integer.parseInt(t.getTag().toString());
                gamestate[number] = Activeuser.charAt(0);
                for(int[] winpos: winnerpos) {
                    if (gamestate[winpos[0]] == gamestate[winpos[1]] && gamestate[winpos[1]] == gamestate[winpos[2]] && gamestate[winpos[0]] != 2) {
                        Active = false;
                        if(gamestate[winpos[0]] == '0'){
                            won = "0 has won";
                            dialog(view);
                        }
                        else if (gamestate[winpos[0]]=='X') {
                            won = "X has won";
                            dialog(view);
                        }
                    }
                }
                String a = "";
                for (int j:gamestate){
                    a = a + j;
                }
                int b = a.indexOf('2');
                if (b<0) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(PlayerTwo.this, R.style.MyDialogTheme);
                    builder.setTitle("It's a Draw !!");
                    final View customLayout = getLayoutInflater().inflate(R.layout.alertbox, null);
                    builder.setView(customLayout);
                    builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for(int i=0; i<gamestate.length; i++) {
                                gamestate[i] = 2;
                            }
                            Active= true;
                            Activeuser="0";
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
        }
    public void dialog(View v) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(PlayerTwo.this, R.style.MyDialogTheme);
        builder.setTitle("Congrats! "+won);
        final View customLayout = getLayoutInflater().inflate(R.layout.alertbox, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0; i<gamestate.length; i++) {
                    gamestate[i] = 2;
                }
                Active= true;
                Activeuser="0";
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