package TP5.FormacionDeAgua;

import java.util.concurrent.Semaphore;

public class Prueba {
    public static void main(String[] args) {
        Recipiente rec = new Recipiente(2);
        for (int i = 0; i < 20; i++) {
            Thread hid= new Thread(new Hidrogeno(rec),"Hidrogeno "+(i+1));
            hid.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread oxi= new Thread(new Oxigeno(rec),"Oxigeno "+(i+1));
            oxi.start();
        }
        
    }
}
