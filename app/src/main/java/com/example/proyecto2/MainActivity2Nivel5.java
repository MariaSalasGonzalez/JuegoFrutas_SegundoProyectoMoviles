/* Segundo Proyecto: EIF411 Diseño y Programacion de Plataformas Moviles
 * Profesor: Gregorio Villalobos Camacho
 * Autoras: María Amalia Salas González & Kyara Avalos Escobar
 * Grupo 10
 * I Ciclo 2023 */

package com.example.proyecto2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2Nivel5 extends AppCompatActivity {
    TextView tv_nombre, tv_score;
    ImageView iv_imgUno, iv_imgDos, iv_vidas, iv_signoOperacion;
    EditText et_respuesta;
    MediaPlayer mp, mpGreat, mpBad;

    int score, numAleatorio_uno, numAleatorio_dos, resultado, vidas=3;


    String nombreJugador, string_score, string_vidas;

    String numero[] = {"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_nivel5);

        Toast.makeText(this, "Nivel 5 - Multiplicaciones Básicas", Toast.LENGTH_SHORT).show();

        tv_nombre = findViewById(R.id.textViewNombre);
        tv_score = findViewById(R.id.textViewScore);
        iv_vidas = findViewById(R.id.imageViewVidas);
        iv_imgUno = findViewById(R.id.imageViewNumUno);
        iv_imgDos = findViewById(R.id.imageViewNumDos);
        iv_signoOperacion = findViewById(R.id.imageViewSigno);
        et_respuesta = findViewById(R.id.editTextRespuesta);

        nombreJugador = getIntent().getStringExtra("jugador");
        tv_nombre.setText("Jugador "+ nombreJugador);

        string_score = getIntent().getStringExtra("score");
        score = Integer.parseInt(string_score);
        tv_score.setText("Score "+ score);

        string_vidas = getIntent().getStringExtra("vidas");
        vidas = Integer.parseInt(string_vidas);


        switch (vidas){
            case 3:
                iv_vidas.setImageResource(R.drawable.tresvidas);
                break;
            case 2:
                iv_vidas.setImageResource(R.drawable.dosvidas);
                break;
            case 1:
                iv_vidas.setImageResource(R.drawable.unavida);
                break;
        }

        setSupportActionBar(findViewById(R.id.toolbarNivelUno));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icono);

        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);

        mpGreat = MediaPlayer.create(this, R.raw.wonderful);
        mpBad = MediaPlayer.create(this, R.raw.bad);

        numeroAleatorio();

    }

    public void comparar(View vista){
        String respuesta = et_respuesta.getText().toString();
        if(!respuesta.equals("")){
            int respuestaJuegador = Integer.parseInt(respuesta);

            if(respuestaJuegador == resultado){
                mpGreat.start();
                score++;
                tv_score.setText("Score: "+ score);
            }else{
                mpBad.start();
                vidas--;
                switch (vidas){
                    case 2:
                        iv_vidas.setImageResource(R.drawable.dosvidas);
                        Toast.makeText(this, "Quedan dos Manzanas.", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        iv_vidas.setImageResource(R.drawable.unavida);
                        Toast.makeText(this, "Queda una Manzana.", Toast.LENGTH_SHORT).show();
                        break;

                    case 0:

                        Toast.makeText(this, "Has perdido todas tus Manzanas.", Toast.LENGTH_SHORT).show();
                        mp.stop();
                        mp.release();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

            baseDeDatos();
            et_respuesta.setText("");
            numeroAleatorio();

        }else{
            Toast.makeText(this, "Debe dar una respuesta.", Toast.LENGTH_SHORT).show();
        }
    }

    public void numeroAleatorio() {

        if(score <= 49){

            numAleatorio_uno = (int) (Math.random() * 10);
            numAleatorio_dos = (int) (Math.random() * 10);

            resultado = numAleatorio_uno * numAleatorio_dos;

            if(resultado <= 10){
                for(int i = 0; i < numero.length; i++){
                    int id = getResources().getIdentifier(numero[i],"drawable", getPackageName());
                    if(numAleatorio_uno == i){
                        iv_imgUno.setImageResource(id);
                    }
                    if(numAleatorio_dos == i){
                        iv_imgDos.setImageResource(id);
                    }
                }
            }else{
                numeroAleatorio();
            }

        }
        else{
            Intent intent = new Intent(this, MainActivity2Nivel6.class);

            string_score = String.valueOf(score);
            string_vidas = String.valueOf(vidas);

            intent.putExtra("jugador", nombreJugador);
            intent.putExtra("score", string_score);
            intent.putExtra("vidas", string_vidas);

            mp.stop();
            mp.release();
            startActivity(intent);
            finish();

        }
    }
    public void baseDeDatos(){
        AdminSQLIteOpenHelper admin = new AdminSQLIteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery("SELECT * " +
                "FROM puntaje " +
                "WHERE score = " +
                "(SELECT MAX(score) " +
                "FROM puntaje)", null);
        if(consulta.moveToFirst()){
            String temp_nombre = consulta.getString(0);
            String temp_score = consulta.getString(1);

            int bestScore = Integer.parseInt(temp_score);

            if(score > bestScore){
                ContentValues modificacion = new ContentValues();
                modificacion.put("nombre", nombreJugador);
                modificacion.put("score",score);
                BD.update("puntaje",modificacion,"score="+bestScore,null);
            }
        } else{
            ContentValues insertar = new ContentValues();
            insertar.put("nombre",nombreJugador);
            insertar.put("score",score);
            BD.insert("puntaje",null,insertar);
        }

        BD.close();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}