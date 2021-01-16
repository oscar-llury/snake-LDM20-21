package com.ldm.virusnake.juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.ldm.virusnake.FileIO;

public class Configuraciones {
    public static boolean sonidoHabilitado = true;

    public static void cargar(FileIO files) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                files.leerArchivo(".piratas")))) {
            sonidoHabilitado = Boolean.parseBoolean(in.readLine());
        } catch (IOException ignored) {
        } catch (NumberFormatException ignored) {
        }
    }

    public static void save(FileIO files) {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                files.escribirArchivo(".piratas")))) {
            out.write(Boolean.toString(sonidoHabilitado));
            out.write("\n");
            out.write("\n");

        } catch (IOException ignored) {
        }
    }


}