package PracticaParcial1.PuentesColgantes;

import java.util.concurrent.Semaphore;

public class Puente {
    // semáforos binarios
    private final Semaphore mutex  = new Semaphore(1, true);
    private final Semaphore gateN  = new Semaphore(0, true); // Norte->Sur
    private final Semaphore gateS  = new Semaphore(0, true); // Sur->Norte
    private final Semaphore vacio  = new Semaphore(0, true); // evento "puente vacío"

    private final int CAP;
    private int enPuente = 0;
    private boolean sentidoNorte = true; // true: Norte->Sur; false: Sur->Norte
    private boolean cambiando = false;

    public Puente(int capacidad, boolean sentidoInicialNorte) {
        this.CAP = capacidad;
        this.sentidoNorte = sentidoInicialNorte;
        // Abrir la puerta inicial del sentido activo
        if (this.sentidoNorte) {
            gateN.release();
        } else {
            gateS.release();
        }
    }

    // ===== Entradas =====

    public void entrarDesdeNorte() throws InterruptedException {
        boolean entro = false;
        while (!entro) {
            gateN.acquire();                 // espera puerta Norte
            mutex.acquire();
            try {
                if (!cambiando && sentidoNorte && enPuente < CAP) {
                    enPuente = enPuente + 1;
                    // si aún hay lugar, dejamos la puerta abierta para otro del mismo sentido
                    if (enPuente < CAP) {
                        gateN.release();
                    }
                    entro = true;
                } else {
                    // no puedo entrar ahora. Reabrir si corresponde para no frenar a otros
                    if (!cambiando && sentidoNorte && enPuente < CAP) {
                        gateN.release();
                    }
                }
            } finally {
                mutex.release();
            }
        }
    }

    public void entrarDesdeSur() throws InterruptedException {
        boolean entro = false;
        while (!entro) {
            gateS.acquire();                 // espera puerta Sur
            mutex.acquire();
            try {
                if (!cambiando && !sentidoNorte && enPuente < CAP) {
                    enPuente = enPuente + 1;
                    if (enPuente < CAP) {
                        gateS.release();
                    }
                    entro = true;
                } else {
                    if (!cambiando && !sentidoNorte && enPuente < CAP) {
                        gateS.release();
                    }
                }
            } finally {
                mutex.release();
            }
        }
    }

    // ===== Cruce =====
    public void cruzar(long milis) throws InterruptedException {
        Thread.sleep(milis); // simula cruce; no hay locks acá
    }

    // ===== Salida =====
    public void salir() {
        try {
            mutex.acquire();
            enPuente = enPuente - 1;

            if (cambiando) {
                if (enPuente == 0) {
                    vacio.release(); // avisar que ya no queda nadie
                }
            } else {
                // se liberó un lugar; reabrir la puerta del sentido activo si estaba cerrada
                if (enPuente == CAP - 1) {
                    if (sentidoNorte) {
                        if (gateN.availablePermits() == 0) {
                            gateN.release();
                        }
                    } else {
                        if (gateS.availablePermits() == 0) {
                            gateS.release();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            mutex.release();
        }
    }

    // ===== Cambio de sentido =====
    public void cambiarSentido() throws InterruptedException {
        // cerrar el “grifo”: que quienes intentan entrar ya no dejen la puerta abierta
        mutex.acquire();
        try {
            cambiando = true;
        } finally {
            mutex.release();
        }

        // esperar a que se vacíe
        vacio.acquire();

        // activar el otro sentido y abrir UNA puerta de ese sentido
        mutex.acquire();
        try {
            if (sentidoNorte) {
                sentidoNorte = false; // ahora Sur->Norte
                if (gateS.availablePermits() == 0) {
                    gateS.release();
                }
            } else {
                sentidoNorte = true; // ahora Norte->Sur
                if (gateN.availablePermits() == 0) {
                    gateN.release();
                }
            }
            cambiando = false;
        } finally {
            mutex.release();
        }
    }
}

