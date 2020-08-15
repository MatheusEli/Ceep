package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Intent intent = new Intent(this,ListaNotasActivity.class);

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
            }
        }, 2000);
    }
}
