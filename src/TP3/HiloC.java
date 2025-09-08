package TP3;

public class HiloC implements Runnable{
    private StringCompartido texto;
    private int repeticiones;

    public HiloC(StringCompartido texto, int repeticiones) {
        this.texto = texto;
        this.repeticiones = repeticiones;
    }

    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            texto.imprimir('C', 4, 2, 0); // imprime 4 'C', luego le da el turno a A
        }
    }
}
