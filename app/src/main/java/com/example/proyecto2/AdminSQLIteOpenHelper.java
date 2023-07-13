/* Segundo Proyecto: EIF411 Diseño y Programacion de Plataformas Moviles
 * Profesor: Gregorio Villalobos Camacho
 * Autoras: María Amalia Salas González & Kyara Avalos Escobar
 * Grupo 10
 * I Ciclo 2023 */

package com.example.proyecto2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLIteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLIteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table puntaje(nombre text, score int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
