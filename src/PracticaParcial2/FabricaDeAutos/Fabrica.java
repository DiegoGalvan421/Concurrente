package PracticaParcial2.FabricaDeAutos;

import java.util.concurrent.Semaphore;

public class Fabrica {
    private Semaphore[] mutex = new Semaphore[] {
            new Semaphore(1, true),
            new Semaphore(1, true),
            new Semaphore(1, true),
    };
    private Semaphore[] espacios = new Semaphore[3];

    private Semaphore[] objetosListo = new Semaphore[] {
            new Semaphore(0), // son las que ya estan terminadas
            new Semaphore(0),
            new Semaphore(0),
    };

    private int cajaRuedas=4;// marcan los espacios libres que le quedan a las cajas
    private int cajaPuertas=2;
    private int cajaCarrocerias=1;
    private int autosArmados = 0;
    private String[] mensajes = new String[]{
        "Produje Ruedas",
        "Produje Puertas",
        "Produje Carroceria",
    };
    Fabrica(int capRuedas, int capPuertas, int capCarrocerias) {
        espacios[0] = new Semaphore(capRuedas);
        espacios[1] = new Semaphore(capPuertas);
        espacios[2] = new Semaphore(capCarrocerias);
    }

    public void producir(int a) throws InterruptedException{
        espacios[a].acquire();
        mutex[a].acquire();
        System.out.println(mensajes[a]);
        objetosListo[a].release();
        mutex[a].release();
    }

    public void fabricarAuto() throws InterruptedException {
        objetosListo[0].acquire(4);
        System.out.println("Ya separe las 4 ruedas");
        objetosListo[1].acquire(2);
        System.out.println("Ya separe las 2 puertas");
        objetosListo[2].acquire(1);
        System.out.println("Ya separe la carroceria");
        System.out.println("Estoy armando el auto");
        mutex[0].acquire();
        //cajaRuedas += 4;
        espacios[0].release(4);
        mutex[0].release();
        mutex[1].acquire();
        //cajaPuertas += 2;
        espacios[1].release(2);
        mutex[1].release();
        mutex[2].acquire();
        //cajaCarrocerias += 1;
        espacios[2].release(1);
        mutex[2].release();
        autosArmados++;
        if (autosArmados == 5) {
            System.out.println("Se termino el lote, procedemos a empaquetarlos y dejarlos listos");
            autosArmados = 0;
        }
    }
}
