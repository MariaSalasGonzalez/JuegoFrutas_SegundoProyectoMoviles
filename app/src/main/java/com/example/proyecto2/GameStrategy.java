/* Segundo Proyecto: EIF411 Diseño y Programacion de Plataformas Moviles
 * Profesor: Gregorio Villalobos Camacho
 * Autoras: María Amalia Salas González & Kyara Avalos Escobar
 * Grupo 10
 * I Ciclo 2023 */

package com.example.proyecto2;

import android.content.Context;

interface GameStrategy {
    int getCharacterImageResource();
}

class MangoGameStrategy implements GameStrategy {
    private Context context;
    MangoGameStrategy(Context context) {
        this.context = context;
    }

    @Override
    public int getCharacterImageResource() {
        return context.getResources().getIdentifier("mango", "drawable", context.getPackageName());
    }
}

class FresaGameStrategy implements GameStrategy {
    private Context context;

    FresaGameStrategy(Context context) {
        this.context = context;
    }

    @Override
    public int getCharacterImageResource() {
        return context.getResources().getIdentifier("fresa", "drawable", context.getPackageName());
    }
}

class ManzanaGameStrategy implements GameStrategy {
    private Context context;

    ManzanaGameStrategy(Context context) {
        this.context = context;
    }

    @Override
    public int getCharacterImageResource() {
        return context.getResources().getIdentifier("manzana", "drawable", context.getPackageName());
    }
}

class SandiaGameStrategy implements GameStrategy {
    private Context context;

    SandiaGameStrategy(Context context) {
        this.context = context;
    }

    @Override
    public int getCharacterImageResource() {
        return context.getResources().getIdentifier("sandia", "drawable", context.getPackageName());
    }
}

class NaranjaGameStrategy implements GameStrategy {
    private Context context;

    NaranjaGameStrategy(Context context) {
        this.context = context;
    }

    @Override
    public int getCharacterImageResource() {
        return context.getResources().getIdentifier("naranja", "drawable", context.getPackageName());
    }
}
