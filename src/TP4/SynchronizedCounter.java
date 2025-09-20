package TP4;
public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }
    //en este caso, faltaria sincornizar el decrementar, ya que accede al mismo recurso critico
    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}