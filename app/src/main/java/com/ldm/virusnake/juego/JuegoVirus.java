package com.ldm.virusnake.juego;

import com.ldm.virusnake.Pantalla;
import com.ldm.virusnake.androidimpl.AndroidJuego;

public class JuegoVirus extends AndroidJuego {
    @Override
    public Pantalla getStartScreen() {
        return new LoadingScreen(this);
    }
}
