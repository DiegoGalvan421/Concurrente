package TP6.PuenteEstrecho.SEMAFOROS;

import java.util.concurrent.Semaphore;

public class Puente {
    private Semaphore mutex = new Semaphore(1,true);
    private Semaphore sur = new Semaphore(1,true);
    private Semaphore norte = new Semaphore(0,true);
    private Semaphore pasar = new Semaphore(1,true);
    private int norteEnEspera = 0;
    private int surEnEspera = 0;

    Puente() {

    }

    public void cruzarNorte() throws InterruptedException {
        mutex.acquire();
        norteEnEspera++;
        System.out.println("Estoy esperando a cruzar norte " + Thread.currentThread().getName());
        mutex.release();
        norte.acquire();
        pasar.acquire();
        System.out.println("Me dejaron pasar norte " + Thread.currentThread().getName());
        mutex.acquire();
        norteEnEspera--;
        if (surEnEspera <= 1) {
            System.out.println("no hay sur en espera, seguimos norte");
            norte.release();
        } else {
            System.out.println("Cambiamos direccion porque hay en espera sur");
            sur.release();
        }
        System.out.println("Termine de cruzar Norte" + Thread.currentThread().getName());
        pasar.release();
        mutex.release();
    }

    public void cruzarSur() throws InterruptedException {
        mutex.acquire();
        surEnEspera++;
        System.out.println("Estoy esperando a cruzar sur " + Thread.currentThread().getName());
        mutex.release();
        sur.acquire();
        pasar.acquire();
        System.out.println("Me dejaron pasar sur " + Thread.currentThread().getName());
        mutex.acquire();
        surEnEspera--;
        if (norteEnEspera <= 1) {
            System.out.println("no hay sur en espera, seguimos sur");
            sur.release();
        } else {
            System.out.println("Cambiamos direccion porque hay en espera norte");
            norte.release();
        }
        System.out.println("Termine de cruzar Sur" + Thread.currentThread().getName());
        pasar.release();
        mutex.release();
    }
}
