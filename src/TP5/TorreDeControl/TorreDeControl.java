package TP5.TorreDeControl;

import java.util.concurrent.Semaphore;

public class TorreDeControl {
    
    int aterrizajesConsecutivos = 0;
    int esperandoAterrizaje = 0;
    int esperandoDespegue = 0;
    
    Semaphore mutex = new Semaphore(1);
    Semaphore pistaLibre = new Semaphore(1);  // 1 -> pista disponible
    Semaphore puedeAterrizar = new Semaphore(0);
    Semaphore puedeDespegar = new Semaphore(0);
    // permisoOtorgado: ya se otorgó un permiso (a aterrizar o despegar)
    // pistaOcupada: la pista está actualmente en uso por un avión
    boolean permisoOtorgado = false;
    boolean pistaOcupada = false;

    public void solicitarAterrizaje() throws InterruptedException {
        mutex.acquire();
        esperandoAterrizaje++;
        // Solo generar un permiso si la pista no está ocupada y no hay
        // ya un permiso otorgado a otro avión. Si no se otorga aca,
        // `liberarSiguiente()` lo hará cuando la pista se desocupe.
        if (!pistaOcupada && !permisoOtorgado) {
            if ((aterrizajesConsecutivos < 10 || esperandoDespegue == 0)) {
                puedeAterrizar.release();
                permisoOtorgado = true;
            }
        }
        mutex.release();
        puedeAterrizar.acquire();  // esperar autorización
        // ahora que tengo autorización, tomo la pista física
        pistaLibre.acquire();      // ocupar pista
        // marco la pista como ocupada (bajo mutex para coherencia)
        mutex.acquire();
        permisoOtorgado = false; // ya consumí el permiso
        pistaOcupada = true;
        mutex.release();
        // aterriza
    }

    public void aterrizar() throws InterruptedException {
        // simula el aterrizaje
        Thread.sleep(1000); // ejemplo
        mutex.acquire();
        esperandoAterrizaje--;
        aterrizajesConsecutivos++;
        // libero siguiente respetando prioridad y reglas
        pistaOcupada = false; // liberé la pista
        liberarSiguiente();
        mutex.release();
        pistaLibre.release(); // libera la pista
    }

    public void solicitarDespegue() throws InterruptedException {
        mutex.acquire();
        esperandoDespegue++;
        // Igual que en aterrizaje: otorgar permiso solo si no hay ya uno
        // y la pista no está ocupada. Si no se otorga aquí, `liberarSiguiente()`
        // lo hará cuando la pista quede libre.
        if (!pistaOcupada && !permisoOtorgado) {
            if ((aterrizajesConsecutivos == 10 || esperandoAterrizaje == 0)) {
                puedeDespegar.release();
                permisoOtorgado = true;
            }
        }
        mutex.release();
        puedeDespegar.acquire();  // esperar autorización
        // ahora que tengo autorización, tomo la pista física
        pistaLibre.acquire();     // ocupar pista
        mutex.acquire();
        permisoOtorgado = false;
        pistaOcupada = true;
        mutex.release();
        // despega
    }

    public void despegar() throws InterruptedException {
        // simula despegue
        Thread.sleep(1000); // ejemplo
        mutex.acquire();
        esperandoDespegue--;
        aterrizajesConsecutivos = 0;  // reinicio del contador
        // libero la pista y autorizo al siguiente según prioridad
        pistaOcupada = false;
        liberarSiguiente();
        mutex.release();
        pistaLibre.release();
    }

    private void liberarSiguiente() {
        // Decidir quién recibe el siguiente permiso. Al otorgar, marco
        // permisoOtorgado = true para evitar duplicados.
        if (esperandoAterrizaje > 0 && aterrizajesConsecutivos < 10) {
            puedeAterrizar.release();
            permisoOtorgado = true;
        } else if (esperandoDespegue > 0 && aterrizajesConsecutivos >= 10) {
            puedeDespegar.release();
            permisoOtorgado = true;
        } else if (esperandoDespegue > 0 && esperandoAterrizaje == 0) {
            // no hay aterrizajes esperando, dar pase a despegues
            puedeDespegar.release();
            permisoOtorgado = true;
        } else if (esperandoAterrizaje > 0) {
            // si ya pasaron 10 aterrizajes y no había despegues antes, retomamos aterrizaje
            aterrizajesConsecutivos = 0;
            puedeAterrizar.release();
            permisoOtorgado = true;
        }
    }
}

