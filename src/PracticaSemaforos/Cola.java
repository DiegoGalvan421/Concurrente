package PracticaSemaforos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Cola {
    private Semaphore available = new Semaphore(0, true);
    private Semaphore ext = new Semaphore(1, true);
    private Semaphore ins = new Semaphore(1, true);
    private Queue<Object> extraccion = new LinkedList<>();
    private Queue<Object> insercion = new LinkedList<>();

    Cola() {

    }

    public Object extraer() throws InterruptedException {
        // esperar hasta que exista al menos un elemento en el buffer completo
        available.acquire();
        ext.acquire();
        try {
            if (extraccion.isEmpty()) {
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
            return extraccion.poll(); // guaranteed non-null because available acquired
        } finally {
            ext.release();
        }
    }

    public void insertar(Object a) throws InterruptedException {
        ins.acquire();
        try {
            insercion.add(a);
            // señalamos que hay un elemento disponible (total)
            available.release();
        } finally {
            ins.release();
        }
    }
}
