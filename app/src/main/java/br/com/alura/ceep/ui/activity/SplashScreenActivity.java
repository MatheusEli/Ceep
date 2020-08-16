package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;

public class SplashScreenActivity extends AppCompatActivity {


    public static final String TEXTO_SHARED = "USUARIO_ACESSOU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences preferences = getSharedPreferences(TEXTO_SHARED, MODE_PRIVATE);
        final Intent intent = new Intent(this,ListaNotasActivity.class);

        Handler handle = new Handler();

        if(preferences.contains(TEXTO_SHARED)){
            handle.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            }, 500);
        }else{
            adicionarPreferenceJaAbriu(preferences);
            handle.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            }, 2000);
        }
    }

    private void adicionarPreferenceJaAbriu(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(TEXTO_SHARED, true);
        editor.commit();
    }
}
