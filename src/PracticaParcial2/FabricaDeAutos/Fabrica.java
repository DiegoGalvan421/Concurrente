package PracticaParcial2.FabricaDeAutos;

import java.util.concurrent.Semaphore;

public class Fabrica {
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore ruedas;// semaforo para controlar la produccion de objetos
    private Semaphore puertas;
    private Semaphore carrocerias;
    private Semaphore ruedasListas = new Semaphore(0); // son las que ya estan terminadas
    private Semaphore puertasListas = new Semaphore(0);
    private Semaphore carroceriasListas = new Semaphore(0);
    private int cajaRuedas;// marcan los espacios libres que le quedan a las cajas
    private int cajaPuertas;
    private int cajaCarrocerias;
    private int autosArmados=0;

    Fabrica(int capRuedas, int capPuertas, int capCarrocerias) {
        ruedas = new Semaphore(capRuedas);
        puertas = new Semaphore(capPuertas);
        carrocerias = new Semaphore(capCarrocerias);
        cajaRuedas = capRuedas;
        cajaPuertas = capPuertas;
        cajaCarrocerias = capCarrocerias;
    }
     
    public void producirRuedas()throws InterruptedException{
        ruedas.acquire();
        mutex.acquire();
        System.out.println("Produje una rueda");
        ruedasListas.release();
        cajaRuedas--;
        mutex.release();
    }
    public void producirPuertas()throws InterruptedException{
        puertas.acquire();
        mutex.acquire();
        System.out.println("Produje una puerta");
        puertasListas.release();
        cajaPuertas--;
        mutex.release();
    }
    public void producirCarroceria()throws InterruptedException{
        carrocerias.acquire();
        mutex.acquire();
        System.out.println("Produje una carroceria");
        carroceriasListas.release();
        cajaCarrocerias--;
        mutex.release();
    }
    public void fabricarAuto()throws InterruptedException{
        ruedasListas.acquire(4);
        System.out.println("Ya separe las 4 ruedas");
        puertasListas.acquire(2);
        System.out.println("Ya separe las 2 puertas");
        carroceriasListas.acquire(1);
        System.out.println("Ya separe la carroceria");
        mutex.acquire();
        System.out.println("Estoy armando el auto");
        cajaRuedas+=4;
        cajaPuertas+=2;
        cajaCarrocerias+=1;
        ruedas.release(4);
        puertas.release(2);
        carrocerias.release(1);
        autosArmados++;
        if(autosArmados==5){
            System.out.println("Se termino el lote, procedemos a empaquetarlos y dejarlos listos");
            autosArmados=0;
        }
        mutex.release();
    }
}
