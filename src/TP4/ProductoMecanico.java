package TP4;

public class ProductoMecanico implements Runnable {
    private final ControladorProduccion controlador;

    public ProductoMecanico(ControladorProduccion controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        try {
            controlador.llegaMecanico();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
