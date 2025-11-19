package PracticaSemaforos.MontañaRusa;

import java.util.concurrent.Semaphore;

public class Carro {
    private Semaphore mutex = new Semaphore(1,true);
    private Semaphore subir;
    private Semaphore despertarOperador = new Semaphore(0);
    private int capMax;
    private int capAct=0;
    private Semaphore bajar= new Semaphore(0,true);
    private int recorridos;

    Carro(int a,int rec){
        capMax=a;
        recorridos=rec;
        subir = new Semaphore(a,true);
    }

    public void intentarSubir()throws InterruptedException{
        System.out.println("Esperando para subir "+Thread.currentThread().getName());
        subir.acquire();
        System.out.println("Pude subir "+Thread.currentThread().getName());
        mutex.acquire();
        try{
            capAct++;
            if(capMax==capAct){
                System.out.println("Soy el utlimo, la actividad comienza "+Thread.currentThread().getName());
                despertarOperador.release();
            }
        }finally{
            mutex.release();
        }
    }
    public void comenzarRecorrido()throws InterruptedException{
        despertarOperador.acquire();
        System.out.println("Comenzo el recorrido, el carro avanza");
        mutex.acquire();
        try{
            recorridos--;
        }finally{
            mutex.release();
        }
        Thread.sleep(500);
        System.out.println("Termino el recorrido los visitantes pueden bajar");
        bajar.release(capMax);
    }

    public void resetRecorrido()throws InterruptedException{
        // Espera la señal del ultimo visitante que baja
        despertarOperador.acquire();
        System.out.println("Comenzamos a subir devuelta los visitantes");
        mutex.acquire();
        try{
            if(recorridos==0){
                System.out.println("Se cerro la atraccion");
                // Indicar al controlador que termine su bucle
                throw new InterruptedException("Recorridos finalizados");
            }else{
                subir.release(capMax);
            }
        }finally{
            mutex.release();
        }
    }

    public void bajarMR()throws InterruptedException{
        System.out.println("Estoy esperando para bajar "+Thread.currentThread().getName());
        bajar.acquire();
        System.out.println("Baje "+Thread.currentThread().getName());
        mutex.acquire();
        try{
            capAct--;
            if(capAct==0){
                System.out.println("soy el ultimo aviso "+Thread.currentThread().getName());
                despertarOperador.release();
            }
        }finally{
            mutex.release();
        }
    }
}

