package com.ldm.virusnake.juego;

public class Virus {
    public static final int TIPO_1 = 0;
    public static final int TIPO_2 = 1;
    public static final int TIPO_3 = 2;
    public static final int TIPO_4 = 3;
    public static final int TIPO_5 = 4;
    public static final int TIPO_6 = 5;
    public static final int TIPO_7 = 6;

    private final int x;
    private final int y;
    private final int tipo;
    private final int valor;

    public Virus(int x, int y, int tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        if(tipo == TIPO_1)
            this.valor = 1;
        else if(tipo == TIPO_2)
            this.valor = 3;
        else if(tipo == TIPO_3)
            this.valor = 5;
        else if(tipo == TIPO_4)
            this.valor = 7;
        else if(tipo == TIPO_5)
            this.valor = 11;
        else if(tipo == TIPO_6)
            this.valor = 17;
        else if(tipo == TIPO_7)
            this.valor = 21;
        else
            this.valor = 0;
    }

    public int getValor() {
        return valor;
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
