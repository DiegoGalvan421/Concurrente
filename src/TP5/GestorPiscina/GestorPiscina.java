package TP5.GestorPiscina;

import java.util.concurrent.Semaphore;

public class GestorPiscina {
    private int capacidadAct=0;
    private int capacidadMax;
    private Semaphore acceso;
    private Semaphore mutex=new Semaphore(1,true);
    
    GestorPiscina(int cap){
        capacidadMax=cap;
        acceso= new Semaphore(cap,true);
    }
    public void ingresar()throws InterruptedException{
        acceso.acquire();
        mutex.acquire();
        capacidadAct++;
        mutex.release();
    }
    public void salir() throws InterruptedException{
        mutex.acquire();
        capacidadAct--;
        mutex.release();
        acceso.release();
    }
}
