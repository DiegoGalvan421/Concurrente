package TP6.Pasteleria;

public class Horno implements Runnable {
    private Mostrador cinta;
    private char tipo;

    Horno(Mostrador cint, char tipo) {
        cinta = cint;
        this.tipo = tipo;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            switch (tipo) {
                case 'A':
                    cinta.ponerPastelEnMostrador(1);
                    break;

                case 'B':
                    cinta.ponerPastelEnMostrador(3);
                    break;

                case 'C':
                    cinta.ponerPastelEnMostrador(5);
                    break;
                default:
                    break;
            }
        }

    }
}
