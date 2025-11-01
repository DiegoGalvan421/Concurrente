package TP5.FormacionDeAgua;

import java.util.concurrent.Semaphore;

public class Recipiente {
    private int recipientesListos = 0;
    private int aguaMax;
    private int aguaActual=0;
    private Semaphore mutex = new Semaphore(1,true);
    private Semaphore hidrogeno = new Semaphore(0,true);
    private Semaphore oxigeno = new Semaphore(0,true);
    private Semaphore terminarO= new Semaphore(0,true);
    private Semaphore terminarH= new Semaphore(0,true);

    Recipiente(int aguaT){
        aguaMax=aguaT;
    }

    public void Hlisto(){
        hidrogeno.release();
    }
    public void Olisto(){
        oxigeno.release();
    }
    
    public void hacerAgua()throws InterruptedException{
        oxigeno.acquire();
        hidrogeno.acquire(2);
        mutex.acquire();
        aguaActual++;
        if(aguaActual==aguaMax){
            System.out.println("agua lista: "+aguaActual);
            recipientesListos++;
            aguaActual=0;
        }
        System.out.println("recipientes listo: "+recipientesListos);
        terminarO.release();
        terminarH.release(2);
        mutex.release();
    }
    public void terminarHid()throws InterruptedException{
        // el hidrógeno debe esperar la señal que libera terminarH (2 permisos)
        terminarH.acquire();
    }
    public void terminarOx()throws InterruptedException{
        // el oxígeno espera la señal que libera terminarO (1 permiso)
        terminarO.acquire();
    }
}
