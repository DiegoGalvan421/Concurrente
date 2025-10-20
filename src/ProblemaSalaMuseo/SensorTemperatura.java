package ProblemaSalaMuseo;

class SensorTemperatura implements Runnable {
    private final GestorSala gestor;

    public SensorTemperatura(GestorSala g) {
        this.gestor = g;
    }

    @Override
    public void run() {
        int[] lecturas = {25, 32, 28, 33, 27}; // Simuladas
        int i = 0;

        while (i < lecturas.length) {
            int t = lecturas[i];
            gestor.notificarTemperatura(t);
            System.out.println("⚠️ Temperatura actual: " + t);
            i++;
            try {
                Thread.sleep(3000); // cada 3 segundos cambia temperatura
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
