package com.ldm.ejemplojuegopiratas.juego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.ldm.ejemplojuegopiratas.FileIO;

public class Configuraciones {
    public static boolean sonidoHabilitado = true;

    public static void cargar(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    files.leerArchivo(".piratas")));
            sonidoHabilitado = Boolean.parseBoolean(in.readLine());
        } catch (IOException e) {
            // :( Está bien aquí debería ir algo
        } catch (NumberFormatException e) {
            // :/ Nadie es perfecto
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.escribirArchivo(".piratas")));
            out.write(Boolean.toString(sonidoHabilitado));
            out.write("\n");
            out.write("\n");

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public void addScore(int score) {
         Object Config = this;
    //Añadir score a la bbdd
        AdminSQLiteOpenHelper dataBase_helper = new AdminSQLiteOpenHelper((Context) Config, "Virus_bbdd", null, 1);
        SQLiteDatabase dataBase = dataBase_helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("score",score);
        dataBase.insert("ranking",null,value);
        dataBase.close();
    }
}