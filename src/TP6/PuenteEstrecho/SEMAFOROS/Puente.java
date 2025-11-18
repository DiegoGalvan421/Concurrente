package TP6.PuenteEstrecho.SEMAFOROS;

import java.util.concurrent.Semaphore;

public class Puente {
    private Semaphore mutex = new Semaphore(1,true);
    private Semaphore sur = new Semaphore(0,true);
    private Semaphore norte = new Semaphore(0,true);
    private Semaphore pasar = new Semaphore(1,true);
    private int norteEnEspera = 0;
    private int surEnEspera = 0;
    private int curcesSeguidos=0;

    Puente() {

    }
    //no dar por hecho que empieza de un lado, hacer un if, que considere neutro hasta que llegue uno
    public void cruzarNorte() throws InterruptedException {
        mutex.acquire();
        norteEnEspera++;
        System.out.println("Estoy esperando a cruzar norte " + Thread.currentThread().getName());
        if(surEnEspera==0 && curcesSeguidos==0){
            System.out.println("Empezamos a pasar desde el norte");
            norte.release();
        }
        mutex.release();
        norte.acquire();
        pasar.acquire();
        System.out.println("Me dejaron pasar norte " + Thread.currentThread().getName());
        
    }
    public void terminarCruzarNorte()throws InterruptedException{
        mutex.acquire();
        norteEnEspera--;
        curcesSeguidos++;
        if ((norteEnEspera > 0 && curcesSeguidos<5)|| surEnEspera==0) {
            System.out.println("No alcanzamos los 5 cruces o no hay en espera norte, seguimos Norte");
            norte.release();
        } else {
            System.out.println("Alcanzamos los 5 cruces seguidos y hay Sur en espera, cambiamos");
            curcesSeguidos=0;
            sur.release();
        }
        System.out.println("Termine de cruzar Norte" + Thread.currentThread().getName());
        pasar.release();
        mutex.release();
    }
    public void terminarCruzarSur()throws InterruptedException{
        mutex.acquire();
        surEnEspera--;
        curcesSeguidos++;
        if ((surEnEspera > 0 && curcesSeguidos<5)|| norteEnEspera==0) {
            System.out.println("No alcanzamos los 5 cruces o no hay en espera norte, seguimos sur");
            sur.release();
        } else {
            System.out.println("Alcanzamos los 5 cruces seguidos y hay norte en espera, cambiamos");
            curcesSeguidos=0;
            norte.release();

        }
        System.out.println("Termine de cruzar Sur" + Thread.currentThread().getName());
        pasar.release();
        mutex.release();
    }
    public void cruzarSur() throws InterruptedException {
        mutex.acquire();
        surEnEspera++;
        System.out.println("Estoy esperando a cruzar sur " + Thread.currentThread().getName());
        if(norteEnEspera==0 && curcesSeguidos==0){
            System.out.println("Empezamos a pasar desde el sur");
            sur.release();
        }
        mutex.release();
        sur.acquire();
        pasar.acquire();
        System.out.println("Me dejaron pasar sur " + Thread.currentThread().getName());
    }
}
