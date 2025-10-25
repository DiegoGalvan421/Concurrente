package TP5.TorreDeControl;

public class AvionDespegue implements Runnable{
    private TorreDeControl tor;

    AvionDespegue(TorreDeControl tor) {
        this.tor = tor;
    }

    public void run() {
        try {
            tor.solicitarDespegue();
            System.out.println("solicite despegar: "+Thread.currentThread().getName());
            tor.despegar(); // luego va a tierra
            System.out.println("despegue: "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
