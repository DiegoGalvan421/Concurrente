package TP3;

public class Energia {
    private int energia = 10;

    public int getEnergia() {
        return energia;
    }

    public synchronized void modificarEnergia(int cantidad) {
        int temp = energia;
        temp += cantidad;
        // Simula retardo para aumentar la posibilidad de condición de carrera
        try { Thread.sleep(100); 
        } catch (InterruptedException e) {

        }
        energia = temp;
    }
}
