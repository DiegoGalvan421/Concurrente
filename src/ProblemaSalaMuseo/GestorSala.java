package ProblemaSalaMuseo;

    public class GestorSala {
    private int personasEnSala = 0;
    private int temperatura = 25;
    private final int UMBRAL = 30;
    private int capacidad = 50;

    private int jubiladosEsperando = 0;
    private int personasEsperando = 0;

    public synchronized void entrarSala() throws InterruptedException {
        personasEsperando++;
        while (!puedeEntrar(false)) {
            wait();
        }
        personasEsperando--;
        personasEnSala++;
    }

    public synchronized void entrarSalaJubilado() throws InterruptedException {
        jubiladosEsperando++;
        while (!puedeEntrar(true)) {
            wait();
        }
        jubiladosEsperando--;
        personasEnSala++;
    }

    public synchronized void salirSala() {
        personasEnSala--;
        notifyAll(); // Puede liberar lugar
    }

    public synchronized void notificarTemperatura(int nuevaTemperatura) {
        temperatura = nuevaTemperatura;
        if (temperatura > UMBRAL) {
            capacidad = 35;
        } else {
            capacidad = 50;
        }
        notifyAll(); // Liberar a los hilos si ahora hay lugar
    }

    // Método auxiliar de condición
    private boolean puedeEntrar(boolean esJubilado) {
        boolean puede=false;
        // Si es jubilado, solo se fija que no se pase del límite
        if (personasEnSala < capacidad) {
            if (esJubilado) {
                puede=true;
            } else {
                // Si hay jubilados esperando, las personas deben esperar
                puede=jubiladosEsperando == 0;
            }
        }
        return puede;
    }
} 

