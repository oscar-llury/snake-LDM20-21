package com.ldm.ejemplojuegopiratas.juego;

import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import com.ldm.ejemplojuegopiratas.Juego;
import com.ldm.ejemplojuegopiratas.Graficos;
import com.ldm.ejemplojuegopiratas.Input.TouchEvent;
import com.ldm.ejemplojuegopiratas.Pantalla;
import android.content.Context;


public class PantallaMaximasPuntuaciones extends Pantalla {
    private final Object PantallaMaximasPuntuaciones = this;
    ArrayList<String> ranking = new ArrayList();
    public PantallaMaximasPuntuaciones(Juego juego) {
        super(juego);
            AdminSQLiteOpenHelper dataBase_helper = new AdminSQLiteOpenHelper((Context) PantallaMaximasPuntuaciones, "Virus_bbdd", null, 1);
            SQLiteDatabase dataBase = dataBase_helper.getWritableDatabase();
            Cursor cursor = dataBase.rawQuery("select * from ranking  order by score DESC", null);

            while (cursor.moveToNext()) {
                ranking.add(cursor.getString(1));
            }
            dataBase.close();
    }


    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y > 416) {
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
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
        g.drawPixmap(Assets.menuprincipal, 64, 20, 0, 42, 196, 42);

        int y = 100;
        for (int i = 0; i < ranking.size(); i++) {
            dibujarTexto(g, ranking.get(i), 20, y);
            y += 50;
        }

        g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);
    }

    public void dibujarTexto(Graficos g, String linea, int x, int y) {
        int len = linea.length();
        for (int i = 0; i < len; i++) {
            char character = linea.charAt(i);

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

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

