package com.ldm.virusnake.juego;

import java.util.Random;

public class Mundo {
    static final int MUNDO_ANCHO = 10;
    static final int MUNDO_ALTO = 13;
    static final float TICK_INICIAL = 0.5f;
    static final float TICK_DECREMENTO = 0.05f;

    public JollyRoger jollyroger;
    public Medicina medicina;
    public Virus virus;
    public boolean finalJuego = false;
    public int puntuacion = 0;

    boolean[][] campos = new boolean[MUNDO_ANCHO][MUNDO_ALTO];
    Random random = new Random();
    float tiempoTick = 0;
    static float tick = TICK_INICIAL;

    public Mundo() {
        jollyroger = new JollyRoger();
        this.medicina=null;
        colocarVirus();
    }

    private void colocarVirus() {
        for (int x = 0; x < MUNDO_ANCHO; x++) {
            for (int y = 0; y < MUNDO_ALTO; y++) {
                campos[x][y] = false;
            }
        }

        int len = jollyroger.partes.size();
        for (int i = 0; i < len; i++) {
            PartesVirus parte = jollyroger.partes.get(i);
            campos[parte.getX()][parte.getY()] = true;
        }

        int virusX = random.nextInt(MUNDO_ANCHO);
        int virusY = random.nextInt(MUNDO_ALTO);

        int medicinaX = random.nextInt(MUNDO_ANCHO);
        int medicinaY = random.nextInt(MUNDO_ALTO);

        while (campos[virusX][virusY]) {
            virusX += 1;
            if (virusX >= MUNDO_ANCHO) {
                virusX = 0;
                virusY += 1;
                if (virusY >= MUNDO_ALTO) {
                    virusY = 0;
                }
            }
        }

        while (campos[medicinaX][medicinaY]) {
            medicinaX += 1;
            if (medicinaX >= MUNDO_ANCHO) {
                medicinaX = 0;
                medicinaY += 1;
                if (medicinaY >= MUNDO_ALTO) {
                    medicinaY = 0;
                }
            }
        }

        virus = new Virus(virusX, virusY, random.nextInt(7));
        if (medicina == null) {
            int randommed = random.nextInt(10);
            if (randommed == 5) {
                medicina = new Medicina(medicinaX, medicinaY, random.nextInt(2));
            }
        }
    }

    public void update(float deltaTime) {
        if (finalJuego)
            return;

        tiempoTick += deltaTime;

        while (tiempoTick > tick) {
            tiempoTick -= tick;
            jollyroger.avance();
            if (jollyroger.comprobarChoque()) {
                finalJuego = true;
                return;
            }

            PartesVirus head = jollyroger.partes.get(0);
            if (head.getX() == virus.getX() && head.getY() == virus.getY()) {
                puntuacion += virus.getValor();
                jollyroger.abordaje(virus.getTipo());
                if (jollyroger.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
                    finalJuego = true;
                    return;
                } else {
                    colocarVirus();
                }

                if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
                    tick -= TICK_DECREMENTO;
                }
            }
            if(this.medicina!=null) {
                if (head.getX() == medicina.getX() && head.getY() == medicina.getY()) {
                    //puntuacion += virus.getValor();
                    boolean exit = jollyroger.vacuna(medicina.getTipo());
                    if (exit){
                        finalJuego = true;
                    }
                    if (jollyroger.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
                        finalJuego = true;
                        return;
                    } else {
                        this.medicina = null;
                    }

                    if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
                        tick -= TICK_DECREMENTO;
                    }
                }
            }
        }
    }
}

