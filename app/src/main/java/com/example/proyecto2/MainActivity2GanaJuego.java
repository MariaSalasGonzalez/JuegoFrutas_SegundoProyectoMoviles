/* Segundo Proyecto: EIF411 Diseño y Programacion de Plataformas Moviles
 * Profesor: Gregorio Villalobos Camacho
 * Autoras: María Amalia Salas González & Kyara Avalos Escobar
 * Grupo 10
 * I Ciclo 2023 */

package com.example.proyecto2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2GanaJuego extends AppCompatActivity {

    ImageView imageViewFelicitaciones;
    Button btnVolverJugar;
    TextView textView_mensaje_felicidades;
    MediaPlayer mp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_ganajuego);

        imageViewFelicitaciones = findViewById(R.id.imageView_Felicidades);
        btnVolverJugar = findViewById(R.id.btn_Volver_Jugar);
        textView_mensaje_felicidades = findViewById(R.id.txt_mensaje_felicidades);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icono);

        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);
    }

    public void volverAJugar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        mp.stop();
        mp.release();
        startActivity(intent);
        finish();
    }
}