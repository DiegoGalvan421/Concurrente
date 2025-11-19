package PracticaSemaforos.ColasParcial;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Cola {
    private Semaphore hayDato = new Semaphore(0, true);
    private Semaphore ext = new Semaphore(1, true);
    private Semaphore ins = new Semaphore(1, true);
    private Queue<Object> extraccion = new LinkedList<>();
    private Queue<Object> insercion = new LinkedList<>();
    

    Cola() {

    }

    public Object extraer() throws InterruptedException {
        // esperar hasta que exista al menos un elemento en el buffer completo
        hayDato.acquire();
        System.out.println("Hay datos, voy a extraer");
        ext.acquire();
        System.out.println("Estoy empezando el proceso de extraccion");
        try {
            if (extraccion.isEmpty()) {
                System.out.println("La cola esta vacia, hago oscilacion");
                // intercambiar colas de forma exclusiva
                ins.acquire();
                try {
                    Queue<Object> aux = extraccion;
                    extraccion = insercion;
                    insercion = aux;
                } finally {
                    ins.release();
                }
            }
        } finally {
            ext.release();
        }
        System.out.println("Termine de extraer");
        return extraccion.poll();
    }

    public void insertar(Object a) throws InterruptedException {
        ins.acquire();
        try {
            System.out.println("Estoy insertando");
            insercion.add(a);
            // señalamos que hay un elemento disponible (total)
            hayDato.release();
        } finally {
            ins.release();
        }
    }
}
