package com.ldm.ejemplojuegopiratas.juego;

import com.ldm.ejemplojuegopiratas.Pantalla;
import com.ldm.ejemplojuegopiratas.androidimpl.AndroidJuego;

public class JuegoVirus extends AndroidJuego {
    @Override
    public Pantalla getStartScreen() {
        return new LoadingScreen(this);
    }
}
