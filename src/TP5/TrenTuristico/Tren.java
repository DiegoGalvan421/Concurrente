package TP5.TrenTuristico;

import java.util.concurrent.Semaphore;

public class Tren {
    private int pasajerosActuales=0;
    private int enEspera=0;
    private int pasajerosTotales;
    private Semaphore mutex=new Semaphore(1,true);
    private Semaphore mutex2=new Semaphore(1,true);
    private Semaphore ingreso=new Semaphore(0,true);
    private Semaphore egreso=new Semaphore(0,true);
    private Semaphore permisoSubir=new Semaphore(0,true);
    private Semaphore movimiento=new Semaphore(1,true);
    private Semaphore tickets=new Semaphore(0,true);
    private Semaphore peticionTicket=new Semaphore(0,true);

    Tren(int pas){
        pasajerosTotales=pas;
    }
    public void enSalida()throws InterruptedException{
        movimiento.acquire();
        // permitir que hasta 'pasajerosTotales' pasajeros intenten subir
        ingreso.release(pasajerosTotales);
    }
    public void permitirSubir()throws InterruptedException{
        permisoSubir.release(pasajerosTotales);
    }
    public void enLlegada()throws InterruptedException{
        movimiento.acquire();
        egreso.release();
    }
    public void comprarTicket()throws InterruptedException{
        mutex2.acquire();
        enEspera++;
        // notificar al vendedor que hay un pedido de ticket
        peticionTicket.release();
        mutex2.release();
        tickets.acquire();
        mutex2.acquire();
        enEspera--;
        mutex2.release();
    }
    public void bajarDelTren()throws InterruptedException{
        egreso.acquire();
        mutex.acquire();
        pasajerosActuales--;
        if(pasajerosActuales==0){
            movimiento.release();
        }else{
            egreso.release();
        }
        mutex.release();
    }
    public void subirAlTren()throws InterruptedException{
        ingreso.acquire();
        permisoSubir.acquire();
        mutex.acquire();
        pasajerosActuales++;
        if(pasajerosActuales==pasajerosTotales){
            movimiento.release();
        }
        mutex.release();
    }
    public void venderTickets(){
        tickets.release();
    }

    // método para que el vendedor espere hasta que haya un pedido
    public void esperarPedidoTicket() throws InterruptedException{
        peticionTicket.acquire();
    }

}
