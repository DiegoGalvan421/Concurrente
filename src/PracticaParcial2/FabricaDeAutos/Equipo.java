package PracticaParcial2.FabricaDeAutos;

public class Equipo implements Runnable {
    private char tipo;
    private Fabrica fab;

    Equipo(char tipo, Fabrica fab) {
        this.fab = fab;
        this.tipo = tipo;
    }

    public void run() {
        try {
            switch (tipo) {
                case 'R':
                    while (true) {
                        Thread.sleep(100);
                        fab.producirRuedas();
                    }
                case 'P':
                    while (true) {
                        Thread.sleep(200);
                        fab.producirPuertas();
                    }
                case 'C':
                    while (true) {
                        Thread.sleep(400);
                        fab.producirCarroceria();
                    }
                case 'A':
                    while (true) {
                        fab.fabricarAuto();
                    }
                default:
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
