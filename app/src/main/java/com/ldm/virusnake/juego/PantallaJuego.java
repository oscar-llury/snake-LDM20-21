package com.ldm.virusnake.juego;

import java.io.BufferedReader;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.ldm.virusnake.Juego;
import com.ldm.virusnake.Graficos;
import com.ldm.virusnake.Input.TouchEvent;
import com.ldm.virusnake.Pixmap;
import com.ldm.virusnake.Pantalla;
import com.ldm.virusnake.Virusnake;

import static com.ldm.virusnake.juego.Configuraciones.*;

public class PantallaJuego extends Pantalla {
    enum EstadoJuego {
        Preparado,
        Ejecutandose,
        Pausado,
        FinJuego
    }

    EstadoJuego estado = EstadoJuego.Preparado;
    Mundo mundo;
    int antiguaPuntuacion = 0;
    String puntuacion = "0";

    public PantallaJuego(Juego juego) {
        super(juego);
        mundo = new Mundo();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();

        if(estado == EstadoJuego.Preparado)
            updateReady(touchEvents);
        if(estado == EstadoJuego.Ejecutandose)
            updateRunning(touchEvents, deltaTime);
        if(estado == EstadoJuego.Pausado)
            updatePaused(touchEvents);
        if(estado == EstadoJuego.FinJuego)
            updateGameOver(touchEvents);

    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0){
            if(sonidoHabilitado) {
                Assets.ambiente.play();
                Assets.pulsar.play(1);
            }
            estado = EstadoJuego.Ejecutandose;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 64 && event.y < 64) {
                    if(sonidoHabilitado){
                        Assets.ambiente.pause();
                        Assets.pulsar.play(1);
                    }
                    estado = EstadoJuego.Pausado;
                    return;
                }
            }
            if(event.type == TouchEvent.TOUCH_DOWN) {
                if(event.x < 64 && event.y > 416) {
                    mundo.jollyroger.girarIzquierda();
                }
                if(event.x > 256 && event.y > 416) {
                    mundo.jollyroger.girarDerecha();
                }
            }
        }

        mundo.update(deltaTime);
        if(mundo.finalJuego) {
            if(sonidoHabilitado){
                Assets.ambiente.stop();
                Assets.derrota.play(1);
            }
            estado = EstadoJuego.FinJuego;
        }
        if(antiguaPuntuacion != mundo.puntuacion) {
            antiguaPuntuacion = mundo.puntuacion;
            puntuacion = "" + antiguaPuntuacion;
            if(sonidoHabilitado)
                Assets.ataque.play(1);
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        Assets.ambiente.pause();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        if(sonidoHabilitado){
                            Assets.pulsar.play(1);
                            Assets.ambiente.play();
                        }
                        estado = EstadoJuego.Ejecutandose;
                        return;
                    }
                    if(event.y > 148 && event.y < 196) {
                        if(sonidoHabilitado) {
                            Assets.pulsar.play(1);
                            Assets.ambiente.stop();
                        }
                        juego.setScreen(new MainMenuScreen(juego));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if(sonidoHabilitado){
                        Assets.pulsar.play(1);
                        Assets.ambiente.stop();
                    }
                    juego.setScreen(new MainMenuScreen(juego));
                    return;
                }
            }
        }
    }


    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.fondo, 0, 0);
        drawWorld(mundo);
        if(estado == EstadoJuego.Preparado)
            drawReadyUI();
        if(estado == EstadoJuego.Ejecutandose)
            drawRunningUI();
        if(estado == EstadoJuego.Pausado)
            drawPausedUI();
        if(estado == EstadoJuego.FinJuego)
            drawGameOverUI();


        drawText(g, puntuacion, g.getWidth() / 2 - puntuacion.length()*20 / 2, g.getHeight() - 42);
    }

    private void drawWorld(Mundo mundo) {
        Graficos g = juego.getGraphics();
        JollyRoger jollyroger = mundo.jollyroger;
        PartesVirus head = jollyroger.partes.get(0);
        Virus virus = mundo.virus;

        Pixmap stainPixmap = null;
        int tipoVirus = virus.getTipo();
        if(tipoVirus == Virus.TIPO_1)
            stainPixmap = Assets.virus1;
        else if(tipoVirus == Virus.TIPO_2)
            stainPixmap = Assets.virus2;
        else if(tipoVirus == Virus.TIPO_3)
            stainPixmap = Assets.virus3;
        else if(tipoVirus == Virus.TIPO_4)
            stainPixmap = Assets.virus4;
        else if(tipoVirus == Virus.TIPO_5)
            stainPixmap = Assets.virus5;
        else if(tipoVirus == Virus.TIPO_6)
            stainPixmap = Assets.virus6;
        else if(tipoVirus == Virus.TIPO_7)
            stainPixmap = Assets.virus7;

        int x,y;
        if(mundo.medicina!=null) {
            Medicina medicina = mundo.medicina;
            Pixmap medPixmap = null;
            int tipoMedicina = medicina.getTipo();
            if (tipoMedicina == Medicina.TIPO_1)
                medPixmap = Assets.med1;
            else if (tipoMedicina == Medicina.TIPO_2)
                medPixmap = Assets.med2;
            else if (tipoMedicina == Medicina.TIPO_3)
                medPixmap = Assets.med3;

            x = medicina.getX() * 32;
            y = medicina.getY() * 32;
            g.drawPixmap(medPixmap, x,y);
        }
        x = virus.getX() * 32;
        y = virus.getY() * 32;
        g.drawPixmap(stainPixmap, x, y);
        int len = jollyroger.partes.size();
        for(int i = 1; i < len; i++) {
            PartesVirus part = jollyroger.partes.get(i);
            x = part.getX() * 32;
            y = part.getY() * 32;
            Pixmap virusPixmap = null;

            tipoVirus = part.getTipo();
            if(tipoVirus == Virus.TIPO_1)
                virusPixmap = Assets.virus1;
            else if(tipoVirus == Virus.TIPO_2)
                virusPixmap = Assets.virus2;
            else if(tipoVirus == Virus.TIPO_3)
                virusPixmap = Assets.virus3;
            else if(tipoVirus == Virus.TIPO_4)
                virusPixmap = Assets.virus4;
            else if(tipoVirus == Virus.TIPO_5)
                virusPixmap = Assets.virus5;
            else if(tipoVirus == Virus.TIPO_6)
                virusPixmap = Assets.virus6;
            else if(tipoVirus == Virus.TIPO_7)
                virusPixmap = Assets.virus7;
            g.drawPixmap(virusPixmap, x, y);
        }

        Pixmap headPixmap = null;
        if(jollyroger.direccion == JollyRoger.ARRIBA)
            headPixmap = Assets.cabezaarriba;
        if(jollyroger.direccion == JollyRoger.IZQUIERDA)
            headPixmap = Assets.cabezaizquierda;
        if(jollyroger.direccion == JollyRoger.ABAJO)
            headPixmap = Assets.cabezaabajo;
        if(jollyroger.direccion == JollyRoger.DERECHA)
            headPixmap = Assets.cabezaderecha;
        x = head.getX() * 32 + 16;
        y = head.getY() * 32 + 16;
        g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
    }

    private void drawReadyUI() {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.preparado, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawRunningUI() {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.botones, 0, 0, 64, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
        g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.botones, 256, 416, 0, 64, 64, 64);
    }

    private void drawPausedUI() {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.menupausa, 80, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.finjuego, 62, 100);
        g.drawPixmap(Assets.botones, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    public void drawText(Graficos g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }


    @Override
    public void pause() {
        if(estado == EstadoJuego.Ejecutandose)
            estado = EstadoJuego.Pausado;

        if(mundo.finalJuego) {
            addScore(Integer.parseInt(puntuacion));
            save(juego.getFileIO());
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void addScore(int score) {
        Object config = this;
        //Añadir score a la bbdd
        Context context = Virusnake.context;
        AdminSQLiteOpenHelper dataBase_helper = new AdminSQLiteOpenHelper( context, "Virus_bbdd", null, 1);
        SQLiteDatabase dataBase = dataBase_helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("score",score);
        dataBase.insert("ranking",null,value);
        dataBase.close();
    }
}