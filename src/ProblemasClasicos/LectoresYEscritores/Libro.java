package ProblemasClasicos.LectoresYEscritores;

import java.util.concurrent.Semaphore;

public class Libro {
    private int cantiPag;
    private int totalPag;
    private Semaphore mutex1 = new Semaphore(1);
    private Semaphore mutex2 = new Semaphore(1);
    private Semaphore lectores = new Semaphore(1);
    private Semaphore escritores = new Semaphore(1);
    private Semaphore turno = new Semaphore(1); // Controla el turno entre lectores y escritores
    private int nLectores = 0;
    private int nEscritores = 0;

    Libro(int total) {
        totalPag = total;
    }

    public void empezarLeer() throws InterruptedException {
        turno.acquire(); // Respeta el turno
        lectores.acquire();
        mutex1.acquire();
        nLectores++;
        if (nLectores == 1) {
            escritores.acquire(); // Bloquea escritores
        }
        mutex1.release();
        lectores.release();
        turno.release(); // Libera el turno
    }

    public void terminarLeer() throws InterruptedException {
        mutex1.acquire();
        nLectores--;
        if (nLectores == 0) {
            escritores.release(); // El último lector libera a los escritores
        }
        mutex1.release();
    }

    public void empezarEscribir() throws InterruptedException {
        turno.acquire(); // Respeta el turno
        mutex2.acquire();
        nEscritores++;
        if (nEscritores == 1) {
            lectores.acquire(); // Bloquea lectores
        }
        mutex2.release();
        escritores.acquire(); // Exclusión mutua para escritores
    }

    public void terminarEscribir() throws InterruptedException {
        escritores.release();
        mutex2.acquire();
        nEscritores--;
        if (nEscritores == 0) {
            lectores.release(); // El último escritor libera a los lectores
        }
        mutex2.release();
        turno.release(); // Libera el turno
    }

    public boolean finalizado() throws InterruptedException {
        boolean terminado = false;
        mutex2.acquire();
        if (cantiPag == totalPag) {
            terminado = true;
        }
        mutex2.release();
        return terminado;
    }

    public boolean hayEscrito() throws InterruptedException {
        boolean hay;
        mutex2.acquire();
        hay = cantiPag > 0;
        mutex2.release();
        return hay;
    }

    public void escribir() throws InterruptedException {
        mutex2.acquire();
        cantiPag++;
        mutex2.release();
    }
}
