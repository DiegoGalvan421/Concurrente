package ProblemasClasicos.LectoresYEscritores;

public class ProbarLectoresYEsc {
    public static void main(String[] args) {
        Libro lib = new Libro(20);

        for (int i = 0; i < 5; i++) {
            Thread lector = new Thread(new Lector(lib), "lector " + i);
            lector.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread escritor = new Thread(new Escritor(lib), "escritor " + i);
            escritor.start();
        }

    }
}
