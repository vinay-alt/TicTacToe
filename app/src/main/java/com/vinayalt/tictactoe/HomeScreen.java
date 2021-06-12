package com.vinayalt.tictactoe;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class HomeScreen extends AppCompatActivity {
    Button player2, quit, player1;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(HomeScreen.this, R.style.MyDialogTheme);
        builder.setTitle("Are you sure you want to quit?");
        final View customLayout = getLayoutInflater().inflate(R.layout.alertbox, null);
        builder.setView(customLayout);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();finish();
                moveTaskToBack(true);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create();
        builder.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, PlayerTwo.class);
                HomeScreen.this.startActivity(intent);
            }
        });
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, PlayerOne.class);
                startActivity(intent);
            }
        });
        quit = findViewById(R.id.player3);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(HomeScreen.this, R.style.MyDialogTheme);
                builder.setTitle("Are you sure you want to quit?");
                final View customLayout = getLayoutInflater().inflate(R.layout.alertbox, null);
                builder.setView(customLayout);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();finish();
                        moveTaskToBack(true);
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}
