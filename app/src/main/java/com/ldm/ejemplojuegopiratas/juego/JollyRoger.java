package com.ldm.ejemplojuegopiratas.juego;

import java.util.ArrayList;
import java.util.List;

public class JollyRoger {
    public static final int ARRIBA = 0;
    public static final int IZQUIERDA= 1;
    public static final int ABAJO = 2;
    public static final int DERECHA = 3;

    public List<Tripulacion> partes = new ArrayList<>();
    public int direccion;

    public JollyRoger() {
        direccion = ARRIBA;
        partes.add(new Tripulacion(5, 6,0));
        partes.add(new Tripulacion(5, 7,0));
        partes.add(new Tripulacion(5, 8,0));
    }

    public void girarIzquierda() {
        direccion += 1;
        if(direccion > DERECHA)
            direccion = ARRIBA;
    }

    public void girarDerecha() {
        direccion -= 1;
        if(direccion < ARRIBA)
            direccion = DERECHA;
    }

    public void abordaje(int tipo) {
        Tripulacion end = partes.get(partes.size()-1);
        partes.add(new Tripulacion(end.getX(), end.getY(),tipo));
    }

    public void avance() {
        Tripulacion barco = partes.get(0);

        int len = partes.size() - 1;
        for(int i = len; i > 0; i--) {
            Tripulacion antes = partes.get(i-1);
            Tripulacion parte = partes.get(i);
            parte.setX(antes.getX());
            parte.setY(antes.getY());
        }

        if(direccion == ARRIBA)
            barco.setY(barco.getY() - 1);
        if(direccion == IZQUIERDA)
            barco.setX(barco.getX() - 1);
        if(direccion == ABAJO)
            barco.setY(barco.getY() + 1);
        if(direccion == DERECHA)
            barco.setX(barco.getX() + 1);

        if(barco.getX() < 0)
            barco.setX(9);
        if(barco.getX() > 9)
            barco.setX(0);
        if(barco.getY() < 0)
            barco.setY(12);
        if(barco.getY() > 12)
            barco.setY(0);
    }

    public boolean comprobarChoque() {
        int len = partes.size();
        Tripulacion barco = partes.get(0);
        for(int i = 1; i < len; i++) {
            Tripulacion parte = partes.get(i);
            if(parte.getX() == barco.getX() && parte.getY() == barco.getY())
                return true;
        }
        return false;
    }
}

