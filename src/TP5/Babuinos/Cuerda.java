package TP5.Babuinos;

import java.util.concurrent.Semaphore;

public class Cuerda {
    // mutex para proteger variables compartidas
    private final Semaphore mutex = new Semaphore(1, true);
    // semáforos de espera por lado
    private final Semaphore semA = new Semaphore(0, true);
    private final Semaphore semB = new Semaphore(0, true);

    // estado
    private int enCuerda = 0; // número de babuinos en la cuerda
    private int dir = 0; // 0 = libre, 1 = A->B, -1 = B->A
    private int esperandoA = 0;
    private int esperandoB = 0;

    // contadores finales
    private int cruzoA = 0;
    private int cruzoB = 0;

    Cuerda() {

    }

    public void cruzarA() throws InterruptedException {
        mutex.acquire();
        // si la dirección está en contra o la cuerda está llena, esperar
        if (dir == -1 || enCuerda == 5) {
            esperandoA++;
            mutex.release();
            semA.acquire(); // esperar turno
            mutex.acquire();
            esperandoA--;
        }
        // establecer dirección y ocupar sitio
        dir = 1;
        enCuerda++;
        mutex.release();
    }

    public void terminarCruzarA() throws InterruptedException {
        mutex.acquire();
        enCuerda--;
        cruzoA++;
        // si ya no hay nadie en la cuerda, permitir al otro lado o continuar
        if (enCuerda == 0) {
            if (esperandoB > 0) {
                // ceder turno a B: despertar hasta 5 B
                int n = Math.min(5, esperandoB);
                dir = -1;
                semB.release(n);
            } else if (esperandoA > 0) {
                // permitir más A
                int n = Math.min(5, esperandoA);
                dir = 1;
                semA.release(n);
            } else {
                dir = 0; // nadie esperando
            }
        }
        mutex.release();
    }

    public void cruzarB() throws InterruptedException {
        mutex.acquire();
        if (dir == 1 || enCuerda == 5) {
            esperandoB++;
            mutex.release();
            semB.acquire();
            mutex.acquire();
            esperandoB--;
        }
        dir = -1;
        enCuerda++;
        mutex.release();
    }

    public void terminarCruzarB() throws InterruptedException {
        mutex.acquire();
        enCuerda--;
        cruzoB++;
        if (enCuerda == 0) {
            if (esperandoA > 0) {
                int n = Math.min(5, esperandoA);
                dir = 1;
                semA.release(n);
            } else if (esperandoB > 0) {
                int n = Math.min(5, esperandoB);
                dir = -1;
                semB.release(n);
            } else {
                dir = 0;
            }
        }
        mutex.release();
    }

    // getters para verificar al final
    public int getcruzoA() {
        return cruzoA;
    }

    public int getCruzoB() {
        return cruzoB;
    }

    public int getTotal() {
        return cruzoA + cruzoB;
    }
}
