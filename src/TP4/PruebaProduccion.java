package TP4;

public class PruebaProduccion {
    public static void main(String[] args) throws InterruptedException {
        ControladorProduccion controlador = new ControladorProduccion();

        // Hilo de control
        Thread control = new Thread(new HiloControl(controlador), "Control");

        // Crear productos eléctricos y mecánicos
        int cantidadElectrico = 5;
        int cantidadMecanico = 5;
        Thread[] electricos = new Thread[cantidadElectrico];
        Thread[] mecanicos = new Thread[cantidadMecanico];

        for (int i = 0; i < cantidadElectrico; i++) {
            electricos[i] = new Thread(new ProductoElectrico(controlador), "Electrico-" + i);
            electricos[i].start();
        }
        for (int i = 0; i < cantidadMecanico; i++) {
            mecanicos[i] = new Thread(new ProductoMecanico(controlador), "Mecanico-" + i);
            mecanicos[i].start();
        }

        control.start();

        // Espera a que terminen todos los productos y el control
        int i;
        for(i=0;i<cantidadElectrico;i++){
            electricos[i].join();
        }
        for(i=0;i<cantidadMecanico;i++){
            mecanicos[i].join();
        }
        control.join();

        System.out.println("Producción finalizada.");
    }
}
