package TP5.LosToboganes;

import java.util.concurrent.Semaphore;

public class Escalera {
    private int cantidadEsc;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore escalera;
    private Semaphore tobogan1 = new Semaphore(1);
    private Semaphore tobogan2 = new Semaphore(1);
    private Semaphore pedirBajada = new Semaphore(0);
    private Semaphore permisoBajar = new Semaphore(0);
    private int toboganAct = 1;

    Escalera(int cantidadEsc) {
        this.cantidadEsc = cantidadEsc;
        escalera = new Semaphore(cantidadEsc, true);
    }

    public void entrar() throws InterruptedException {
        escalera.acquire();
    }

    public void esperarPermiso() throws InterruptedException {
        pedirBajada.release();
        permisoBajar.acquire();
    }

    public void bajar() throws InterruptedException {
        mutex.acquire();
        if (toboganAct == 1) {
            tobogan1.acquire();
            System.out.println("estoy bajando por el tobogan 1: " + Thread.currentThread().getName());
            toboganAct = 2;
            mutex.release();
            Thread.currentThread().sleep(500);
            System.out.println("Termine de bajar " + Thread.currentThread().getName());
            tobogan1.release();
        } else {
            tobogan2.acquire();
            System.out.println("estoy bajando por el tobogan 2: " + Thread.currentThread().getName());
            toboganAct = 1;
            mutex.release();
            Thread.currentThread().sleep(500);
            System.out.println("Termine de bajar " + Thread.currentThread().getName());
            tobogan2.release();
        }
    }

    public void habilitarBajada() {
        permisoBajar.release();
    }

    public void esperarPedido() throws InterruptedException {
        pedirBajada.acquire();
    }

    public void liberarMirador() {
        escalera.release();
    }

}
