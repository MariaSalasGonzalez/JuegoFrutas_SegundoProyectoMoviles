/* Segundo Proyecto: EIF411 Diseño y Programacion de Plataformas Moviles
 * Profesor: Gregorio Villalobos Camacho
 * Autoras: María Amalia Salas González & Kyara Avalos Escobar
 * Grupo 10
 * I Ciclo 2023 */

package com.example.proyecto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageView imageViewPersonaje;
    Button btnJugar;
    EditText editTextNombre;
    TextView textViewScore;
    MediaPlayer mp;
    int numAleatorio = (int) (Math.random() * 10);

    private GameStrategy gameStrategy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageViewPersonaje = findViewById(R.id.imageView_Personaje);
        btnJugar = findViewById(R.id.btn_Jugar);
        editTextNombre = findViewById(R.id.et_Nombre);
        textViewScore = findViewById(R.id.textView_BestScore);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icono);

        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);

        AdminSQLIteOpenHelper admin = new AdminSQLIteOpenHelper(this , "BD", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor consulta = db.rawQuery("select * from puntaje where score = (select max(score) from puntaje)", null);

        if(consulta.moveToFirst()){
            String nombre = consulta.getString(0);
            String score = consulta.getString(1);
            textViewScore.setText("Record: " + score + " de " + nombre);
        }

       /* Para probar el Crashlytics
        Button crashButton = new Button(this);
        crashButton.setText("Test Crash");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        */

        db.close();
        consulta.close();


        int id;
        switch (numAleatorio) {
            case 0: case 10: {
                gameStrategy = new FresaGameStrategy(this);
                break;
            }
            case 1: case 9: {
                gameStrategy = new MangoGameStrategy(this);
                break;
            }
            case 2: case 8: {
                gameStrategy = new ManzanaGameStrategy(this);
                break;
            }
            case 3: case 7: {
                gameStrategy = new SandiaGameStrategy(this);
                break;
            }
            case 4: case 5: case 6: {
                gameStrategy = new NaranjaGameStrategy(this);
                break;
            }
        }//fin del switch
    }



    public void jugar(View view){
        String nombre = editTextNombre.getText().toString();
        if (!nombre.equals("")){
            mp.stop();
            mp.release();
            Intent intent = new Intent(this, MainActivity2Nivel1.class);
            intent.putExtra("jugador", nombre);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Debe ingresar su nombre.", Toast.LENGTH_SHORT).show();
            editTextNombre.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editTextNombre, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onBackPressed(){
    }
}