package TP4;

public class ProductoElectrico implements Runnable {
    private final ControladorProduccion controlador;

    public ProductoElectrico(ControladorProduccion controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        try {
            controlador.llegaElectrico();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
