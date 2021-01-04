package com.ldm.virusnake.juego;

public class Medicina {
    public static final int TIPO_1 = 0;
    public static final int TIPO_2 = 1;

    private int x, y, tipo;

    public Medicina(int x, int y, int tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

