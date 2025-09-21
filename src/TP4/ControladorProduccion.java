package TP4;

import java.util.concurrent.Semaphore;

public class ControladorProduccion {
    private final Semaphore mutex = new Semaphore(1, true);
    private final Semaphore esperaElectrica = new Semaphore(0, true);
    private final Semaphore esperaMecanica = new Semaphore(0, true);
    private String actual = "Electrica"; // línea activa
    private int enLinea = 0; // productos en la línea activa

    public void llegaElectrico() throws InterruptedException {
        mutex.acquire();
        if (!actual.equals("Electrica")) {
            mutex.release();
            esperaElectrica.acquire(); // espera hasta que la línea eléctrica esté activa
            mutex.acquire();
        }
        enLinea++;
        System.out.println("Producto eléctrico entra a la línea.");
        mutex.release();

        // Simula ensamblaje
        Thread.sleep(1000);
        sale();
    }

    public void llegaMecanico() throws InterruptedException {
        mutex.acquire();
        if (!actual.equals("Mecanica")) {
            mutex.release();
            esperaMecanica.acquire(); // espera hasta que la línea mecánica esté activa
            mutex.acquire();
        }
        enLinea++;
        System.out.println("Producto mecánico entra a la línea.");
        mutex.release();

        // Simula ensamblaje
        Thread.sleep(1000);
        sale();
    }

    public void sale() throws InterruptedException {
        mutex.acquire();
        enLinea--;
        System.out.println("Producto sale de la línea " + actual);
        mutex.release();
    }

    public void cambiaLineas() throws InterruptedException {
        mutex.acquire();
        // Espera a que la línea actual esté vacía antes de cambiar
        while (enLinea > 0) {
            mutex.release();
            Thread.sleep(100); // espera a que terminen los productos
            mutex.acquire();
        }
        if (actual.equals("Electrica")) {
            actual = "Mecanica";
            System.out.println("Cambiando a línea mecánica.");
            esperaMecanica.release(100); // despierta productos mecánicos (ajusta el número según la cantidad máxima)
        } else {
            actual = "Electrica";
            System.out.println("Cambiando a línea eléctrica.");
            esperaElectrica.release(100);
        }
        mutex.release();
    }
}
