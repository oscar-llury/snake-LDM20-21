package com.ldm.virusnake.juego;

import java.util.List;

import com.ldm.virusnake.Juego;
import com.ldm.virusnake.Graficos;
import com.ldm.virusnake.Input.TouchEvent;
import com.ldm.virusnake.Pantalla;

public class PantallaAyuda4 extends Pantalla {
    public PantallaAyuda4(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 256 && event.y > 416 ) {
                    juego.setScreen(new PantallaAyuda5(juego));
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();
        g.drawPixmap(Assets.fondo, 0, 0);
        g.drawPixmap(Assets.ayuda4, 64, 100);
        g.drawPixmap(Assets.botones, 256, 416, 0, 64, 64, 64);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}