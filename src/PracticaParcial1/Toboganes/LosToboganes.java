package PracticaParcial1.Toboganes;

import java.util.concurrent.Semaphore;

public class LosToboganes {
    private Semaphore tob1 = new Semaphore(1);
    private Semaphore tob2 = new Semaphore(1);
    private Semaphore ingresos = new Semaphore(1);
    private Semaphore permisoBajar = new Semaphore(0); // binario, inicializado en 0
    private int cantidadDePersonas;
    private int toboganAct = 1;
    private final int capacidadTotal; // NUEVO

    LosToboganes(int a) {
        this.capacidadTotal = a; // NUEVO
        this.cantidadDePersonas = a; // cantidad de lugares libres
    }

    public boolean hayPersonasEnMirador() {
        boolean hay;
        try {
            ingresos.acquire();
            hay = (cantidadDePersonas < capacidadTotal); // hay >0 personas si se ocupó al menos 1 lugar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            hay = false;
        } finally {
            ingresos.release();
        }
        return hay;
    }

    public int getCantidadDePersonas() {
        return cantidadDePersonas;
    }

    public boolean intentarSubir() {
        boolean pudoSubir = false;
        try {
            ingresos.acquire();
            if (cantidadDePersonas > 0) {
                cantidadDePersonas--;
                pudoSubir = true;
            } else {
                System.out.println(Thread.currentThread().getName() + " no pudo subir: mirador lleno");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            ingresos.release();
        }
        return pudoSubir;
    }

    public void esperarPermiso() {
        try {
            permisoBajar.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void bajarPorTobogan() {
        if (toboganAct == 1) {
            try {
                tob1.acquire();
                System.out.println(Thread.currentThread().getName() + " baja por tobogán 1");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                tob1.release();
                System.out.println(Thread.currentThread().getName() + " salió del tobogán 1");
            }
        } else {
            try {
                tob2.acquire();
                System.out.println(Thread.currentThread().getName() + " baja por tobogán 2");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                tob2.release();
                System.out.println(Thread.currentThread().getName() + " salió del tobogán 2");
            }
        }
    }

    public void liberarMirador() {
        try {
            ingresos.acquire();
            cantidadDePersonas++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            ingresos.release();
        }
    }

    // Método para que el encargado habilite a una persona y decida el tobogán
    public void habilitarBajada() {
        // Alterna el tobogán para la próxima persona
        if (toboganAct == 1) {
            toboganAct = 2;
        } else {
            toboganAct = 1;
        }
        permisoBajar.release(); // habilita a una persona para bajar
    }
}
