package com.ldm.ejemplojuegopiratas.juego;

public class Botin {
    public static final int TIPO_1 = 0;
    public static final int TIPO_2 = 1;
    public static final int TIPO_3 = 2;
    public static final int TIPO_4 = 3;
    public static final int TIPO_5 = 4;
    public static final int TIPO_6 = 5;
    public static final int TIPO_7 = 6;
    public int x, y,tipo;

    public Botin(int x, int y, int tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }
}
