package TP5.TrenTuristico;

public class Pasajero implements Runnable {
    private Tren tren;

    Pasajero(Tren tren) {
        this.tren = tren;
    }

    public void run() {
        try {
            System.out.println("Estoy comprando un ticket: "+Thread.currentThread().getName());
            tren.comprarTicket();
            System.out.println("compre un ticket: "+Thread.currentThread().getName());
            tren.subirAlTren();
            System.out.println("me subi al tren: "+Thread.currentThread().getName());
            tren.bajarDelTren();
            System.out.println("me baje del tren: "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
