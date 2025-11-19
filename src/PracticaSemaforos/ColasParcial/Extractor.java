package PracticaSemaforos.ColasParcial;

public class Extractor implements Runnable {
    private Cola col;

    Extractor(Cola cl) {
        col = cl;
    }

    public void run() {
        while (true) {
            try {
                Object ob = col.extraer();
                Thread.sleep(400);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}
