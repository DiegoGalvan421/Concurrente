package TP6.BarcoDePyA;

public class Ferry {
    private int espaciosDisponibles;
    private boolean embarque=true;
    private boolean desembarque=false;
    private int cantidadMax;
    Ferry(int espacios) {
        espaciosDisponibles = espacios;
        cantidadMax=espacios;
    }

    public synchronized void ingresarPasajero() throws InterruptedException {
        while (espaciosDisponibles == 0 || !embarque || desembarque) {
            wait();
        }
        espaciosDisponibles--;
        if(espaciosDisponibles==0){
            System.out.println("Embarcaron todos, nos movemos");
            embarque=false;
            desembarque=true;
            notifyAll();
        }
    }

    public synchronized void ingresarAuto()throws InterruptedException{
        while (espaciosDisponibles<2 || !embarque || desembarque) {
            wait();
        }
        espaciosDisponibles=espaciosDisponibles-2;
        if(espaciosDisponibles==0){
            System.out.println("Embarcaron todos, nos movemos");
            embarque=false;
            desembarque=true;
            notifyAll();
        }
    }

    public synchronized void salirPasajero()throws InterruptedException{
        while (!desembarque) {
            wait();
        }
        espaciosDisponibles++;
        if(cantidadMax==espaciosDisponibles){
            System.out.println("Desembarcaron todos, nos movemos");
            embarque=true;
            desembarque=false;
            notifyAll();
        }
    }
    public synchronized void salirAuto()throws InterruptedException{
        while (!desembarque) {
            wait();
        }
        espaciosDisponibles=espaciosDisponibles+2;
        if(cantidadMax==espaciosDisponibles){
            System.out.println("Desembarcaron todos, nos movemos");
            embarque=true;
            desembarque=false;
            notifyAll();
        }
    }
}
