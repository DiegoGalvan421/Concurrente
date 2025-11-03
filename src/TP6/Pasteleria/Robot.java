package TP6.Pasteleria;

public class Robot implements Runnable {
    private Mostrador most;
    private MesaDeCaja mesa;

    Robot(Mostrador most, MesaDeCaja mesa) {
        this.most = most;
        this.mesa = mesa;
    }

    public void run() {
        while (true) {
            int peso = most.tomarPastel();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            mesa.soltarPastel(peso);
        }
    }
}
