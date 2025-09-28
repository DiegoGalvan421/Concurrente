package PracticaParcial1.EdicionDeImagenes;

import java.util.concurrent.Semaphore;

public class EdicionDeIm {
    Semaphore imagDisp = new Semaphore(1);
    Semaphore despEdit = new Semaphore(0);
    Semaphore trabajando = new Semaphore(0);

    public void meterIm() {
        try {
            imagDisp.acquire();
            despEdit.release();
            System.out.println("la imagen se cargo en la app");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void editTrab() {
        try {
            despEdit.acquire();
            System.out.println("trabajando en imagen");
            Thread.sleep(1000);
            trabajando.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("editor entra en modo reposo");
    }

    public void guardarIm() {
        try {
            trabajando.acquire();
            System.out.println("imagen terminada, guardando");
            imagDisp.release();
            System.out.println("imagen guardada");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
