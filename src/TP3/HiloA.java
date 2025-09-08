package TP3;

public class HiloA implements Runnable{
    private StringCompartido texto;
    private int repeticiones;

    public HiloA(StringCompartido texto, int repeticiones) {
        this.texto = texto;
        this.repeticiones = repeticiones;
    }

    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            texto.imprimir('A', 3, 0, 1); // imprime 3 'A', luego le da el turno a B
        }
    }
}
