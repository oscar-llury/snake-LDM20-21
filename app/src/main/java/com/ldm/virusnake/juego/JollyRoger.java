package com.ldm.virusnake.juego;

import java.util.ArrayList;
import java.util.List;

public class JollyRoger {
    public static final int ARRIBA = 0;
    public static final int IZQUIERDA= 1;
    public static final int ABAJO = 2;
    public static final int DERECHA = 3;

    public List<PartesVirus> partes = new ArrayList<>();
    public int direccion;

    public JollyRoger() {
        direccion = ARRIBA;
        partes.add(new PartesVirus(5, 6,0));
        partes.add(new PartesVirus(5, 7,0));
        partes.add(new PartesVirus(5, 8,0));
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
        PartesVirus end = partes.get(partes.size()-1);
        if(tipo != 7){
            partes.add(new PartesVirus(end.getX(), end.getY(),tipo));
        }else{
            this.partes = partes.subList(0,partes.size()-3);
        }
    }

    public void avance() {
        PartesVirus barco = partes.get(0);

        int len = partes.size() - 1;
        for(int i = len; i > 0; i--) {
            PartesVirus antes = partes.get(i-1);
            PartesVirus parte = partes.get(i);
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
        PartesVirus barco = partes.get(0);
        for(int i = 1; i < len; i++) {
            PartesVirus parte = partes.get(i);
            if(parte.getX() == barco.getX() && parte.getY() == barco.getY())
                return true;
        }
        return false;
    }
}

