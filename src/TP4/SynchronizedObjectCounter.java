package TP4;

public class SynchronizedObjectCounter {
    private int c = 0;
    private final Object lock = new Object();
    //no es correcto que se sincronicen en el objeto que va variando
    //se debe generar un objeto de dedicado para hacer de lock.
    //se debe utilizar un objeto inmutable, es decir usar final
    public void increment() {
        synchronized (lock) {
            c++;
        }
    }

    public void decrement() {
        synchronized (lock) {
            c--;
        }
    }

    public int value() {
        return c;
    }
}
