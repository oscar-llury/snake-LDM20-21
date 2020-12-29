package com.ldm.ejemplojuegopiratas.juego;

import com.ldm.ejemplojuegopiratas.Juego;
import com.ldm.ejemplojuegopiratas.Graficos;
import com.ldm.ejemplojuegopiratas.Pantalla;
import com.ldm.ejemplojuegopiratas.Graficos.PixmapFormat;

public class LoadingScreen extends Pantalla{
    public LoadingScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        Graficos g = juego.getGraphics();
        Assets.fondo = g.newPixmap("background.jpg", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.menuprincipal = g.newPixmap("menuprincipal.png", PixmapFormat.ARGB4444);
        Assets.botones = g.newPixmap("botones.png", PixmapFormat.ARGB4444);
        Assets.ayuda1 = g.newPixmap("ayuda1.png", PixmapFormat.ARGB4444);
        Assets.ayuda2 = g.newPixmap("ayuda2.png", PixmapFormat.ARGB4444);
        Assets.ayuda3 = g.newPixmap("ayuda3.png", PixmapFormat.ARGB4444);
        Assets.numeros = g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
        Assets.preparado = g.newPixmap("preparado.png", PixmapFormat.ARGB4444);
        Assets.menupausa = g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
        Assets.finjuego = g.newPixmap("finjuego.png", PixmapFormat.ARGB4444);
        Assets.cabezaarriba = g.newPixmap("cabeza-arriba.png", PixmapFormat.ARGB4444);
        Assets.barcoizquierda = g.newPixmap("cabeza-izq.png", PixmapFormat.ARGB4444);
        Assets.barcoabajo = g.newPixmap("cabeza-abajo.png", PixmapFormat.ARGB4444);
        Assets.barcoderecha = g.newPixmap("cabeza-der.png", PixmapFormat.ARGB4444);
        Assets.tripulacion = g.newPixmap("tripulacion.png", PixmapFormat.ARGB4444);
        Assets.virus1 = g.newPixmap("virus1.png", PixmapFormat.ARGB4444);
        Assets.virus2 = g.newPixmap("virus2.png", PixmapFormat.ARGB4444);
        Assets.virus3 = g.newPixmap("virus3.png", PixmapFormat.ARGB4444);
        Assets.virus4 = g.newPixmap("virus4.png", PixmapFormat.ARGB4444);
        Assets.virus5 = g.newPixmap("virus5.png", PixmapFormat.ARGB4444);
        Assets.virus6 = g.newPixmap("virus6.png", PixmapFormat.ARGB4444);
        Assets.virus7 = g.newPixmap("virus7.png", PixmapFormat.ARGB4444);
        Assets.pulsar = juego.getAudio().nuevoSonido("pulsar.ogg");
        Assets.ataque = juego.getAudio().nuevoSonido("ataque.ogg");
        Assets.derrota = juego.getAudio().nuevoSonido("derrota.ogg");


        Configuraciones.cargar(juego.getFileIO());
        juego.setScreen(new MainMenuScreen(juego));
    }

    @Override
    public void present(float deltaTime) {

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