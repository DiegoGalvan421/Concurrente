package PracticaParcial2.FabricaDeAutos;

public class Equipo implements Runnable {
    private int tipo;
    private Fabrica fab;

    Equipo(int tipo, Fabrica fab) {
        this.fab = fab;
        this.tipo = tipo;
    }

    public void run() {
        if (tipo == 3) {
            try {
                while (true) {
                    fab.fabricarAuto();
                }
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        } else {
            try {
                while (true) {
                    fab.producir(tipo);
                }
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        }
    }
}
