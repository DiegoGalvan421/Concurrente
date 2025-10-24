package ProblemasClasicos.LectoresYEscritores;

public class Lector implements Runnable {
    private Libro lib;

    Lector(Libro lib) {
        this.lib = lib;
    }

    public void run() {
        try {
            while (true) {
                if (lib.hayEscrito()) {
                    lib.empezarLeer();
                    System.out.println("estoy leyendo " + Thread.currentThread().getName());
                    //lib.escribir();
                    Thread.sleep(500);
                    lib.terminarLeer();
                    System.out.println("termine de leer " + Thread.currentThread().getName());
                }
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
