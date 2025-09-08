package TP3;

public class HiloB implements Runnable {
    private StringCompartido texto;
    private int repeticiones;

    public HiloB(StringCompartido texto, int repeticiones) {
        this.texto = texto;
        this.repeticiones = repeticiones;
    }

    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            texto.imprimir('B', 2, 1, 2); // imprime 2 'B', luego le da el turno a C
        }
    }
}
