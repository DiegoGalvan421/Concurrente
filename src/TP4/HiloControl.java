package TP4;

public class HiloControl implements Runnable {
    private final ControladorProduccion controlador;

    public HiloControl(ControladorProduccion controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) { // Cambia la línea 5 veces
                Thread.sleep(3000); // Espera 3 segundos entre cambios
                controlador.cambiaLineas();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
