package TP2CONCURRENCIA;

import java.util.Random.*;

public class Corredor implements Runnable {
    private String nombre;
    private int distancia = 0;

    Corredor(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        int pasos=0;
        System.out.println("Comenzando a correr:"+nombre);
        while(distancia<=100){
            try {
                pasos = (int) (Math.random() * 10) + 1;
                System.out.println(nombre+": hice "+pasos+" pasos");
                distancia= distancia + pasos;
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("me cai");
            }  
        }
        System.out.println("termine la carrera:"+ nombre);
    }

}
