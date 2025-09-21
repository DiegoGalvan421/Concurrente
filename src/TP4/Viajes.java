package TP4;

import java.util.concurrent.Semaphore;

public class Viajes {
    private Semaphore pedido;
    private Semaphore llegada;

    Viajes() {
        pedido = new Semaphore(0, true);
        llegada = new Semaphore(0, true);
    }

    public void solicitarViaje() {

        pedido.release();
        System.out.println("El usuario: " + Thread.currentThread().getName() + " pidio un taxi");

    }

    public void esperarLlegada() {
        try {
            System.out.println("Pasajero " + Thread.currentThread().getName() + " se sube y espera llegada...");
            llegada.acquire();
            System.out.println("Pasajero " + Thread.currentThread().getName() + " llegó y baja.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // salir ordenado si tenés bucles externos
        }
    }

    public void esperarPasajeroYconducir() {
        try {
            System.out.println("Taxista " + Thread.currentThread().getName() + " duerme esperando pasajero...");
            pedido.acquire();
            System.out.println("Taxista " + Thread.currentThread().getName() + " tomó pasajero. Viajando...");
            Thread.sleep(1000); // simula viaje
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void avisarLlegada() {
        llegada.release();
        System.out.println("Taxista " + Thread.currentThread().getName() + " avisa: ¡llegamos!");
    }
}
