package TP3;

public class StringCompartido {
    private StringBuilder texto = new StringBuilder();
    private int turno = 0; // 0: A, 1: B, 2: C

    public synchronized void imprimir(char letra, int cantidad, int miTurno, int siguienteTurno) {
        try {
            while (turno != miTurno) {
                wait();
            }
            for (int i = 0; i < cantidad; i++) {
                texto.append(letra);
            }
            turno = siguienteTurno;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String toString() {
        return texto.toString();
    }
}
