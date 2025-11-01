package TP6.ControlDeAccesoAEstudio;

public class MonitorSala {
    private int mesasDisponibles;

    MonitorSala(int cantMesas){
        mesasDisponibles=cantMesas;
    }

    public synchronized void entrarSala()throws InterruptedException{
        while(mesasDisponibles==0){
            wait();
        }
        mesasDisponibles--;
    }

    public synchronized void salirSala()throws InterruptedException{
        mesasDisponibles++;
        notify();
    }
}
