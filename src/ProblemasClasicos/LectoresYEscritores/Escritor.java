package ProblemasClasicos.LectoresYEscritores;

public class Escritor implements Runnable {
    private Libro lib;

    Escritor(Libro lib) {
        this.lib = lib;
    }

    public void run() {
        try {
            while (!lib.finalizado()) {
                lib.empezarEscribir();
                System.out.println("estoy escribiendo " + Thread.currentThread().getName());
                Thread.sleep(500);
                lib.terminarEscribir();
                System.out.println("termine de escribir " + Thread.currentThread().getName());
            }
            System.out.println("finalizamos el libro " + Thread.currentThread().getName());
        } catch (InterruptedException e) {

        }

    }

}
