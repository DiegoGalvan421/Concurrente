package PracticaParcial2.PlantaEmbotelladora;

import java.util.concurrent.Semaphore;

public class Planta {
    private int caja = 0;
    private int cajaMax = 10;
    private int EspacioAlmacen = 0;
    private int almacenMax = 100;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore empaquetador = new Semaphore(0, true);
    private Semaphore embotelladorVino = new Semaphore(0, true);
    private Semaphore embotelladorSaborizado = new Semaphore(0, true);
    private int vinoEnEspera = 0;
    private int saborizadoEnEspera = 0;
    private int tipoAnterior = 0; // 1 vino , 2 sabor, 0 nada

    Planta() {

    }

    public void embotellarVino() throws InterruptedException {
        mutex.acquire();
        vinoEnEspera++;
        if (tipoAnterior == 0 && saborizadoEnEspera == 0) {
            System.out.println("Empezamos con vino");
            embotelladorVino.release();
            tipoAnterior = 1;
        }
        mutex.release();
        embotelladorVino.acquire();
        mutex.acquire();
        vinoEnEspera--;
        System.out.println("Guardo un vino");
        caja++;
        if (caja == cajaMax) {
            System.out.println("La caja se lleno de vino, despierto al empaquetador");
            empaquetador.release();
        } else {
            embotelladorVino.release();
        }
        mutex.release();
    }

    public void embotellarSaborizado() throws InterruptedException {
        mutex.acquire();
        saborizadoEnEspera++;
        if (tipoAnterior == 0 && vinoEnEspera == 0) {
            System.out.println("Empezamos con saborizado");
            embotelladorSaborizado.release();
            tipoAnterior = 2;
        }
        mutex.release();
        embotelladorSaborizado.acquire();
        mutex.acquire();
        saborizadoEnEspera--;
        System.out.println("Guardo saborizada");
        caja++;
        if (caja == cajaMax) {
            System.out.println("La caja se lleno  de saborizadas, despierto al empaquetador");
            empaquetador.release();
        } else {
            embotelladorSaborizado.release();
        }
        mutex.release();
    }

    public void empaquetar() throws InterruptedException {
        empaquetador.acquire();
        System.out.println("Me desperte cambio caja");
        mutex.acquire();
        caja = 0;
        EspacioAlmacen += 10;
        if(tipoAnterior==1){
                System.out.println("El vino debe madurar esperamos");
                Thread.sleep(1000);
            }
        if (EspacioAlmacen == almacenMax) {
            System.out.println("Se lleno el almacen, sale de reparto el camion");
            EspacioAlmacen = 0;
        }
        if (vinoEnEspera > 0 && tipoAnterior == 2) {
            System.out.println("Hay vino en espera y antes fue saborizada");
            embotelladorVino.release();
            tipoAnterior=1;
        } else if (saborizadoEnEspera > 0 && tipoAnterior == 1) {
            System.out.println("Hay saborizado en espera y antes fue vino");
            embotelladorSaborizado.release();
            tipoAnterior=2;
        } else if (vinoEnEspera > 0) {
            System.out.println("Hay vinno en espera y nada mas");
            embotelladorVino.release();
            tipoAnterior=1;
        } else if (saborizadoEnEspera > 0) {
            System.out.println("Hay saborizada en espera y nada mas");
            embotelladorSaborizado.release();
            tipoAnterior=2;
        } else {
            System.out.println("No hay nada en espera, volvemos a neutro");
            tipoAnterior = 0;
        }
        mutex.release();
    }
}
