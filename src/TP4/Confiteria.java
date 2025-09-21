package TP4;

import java.util.concurrent.Semaphore;

public class Confiteria {
    private final Semaphore asiento  = new Semaphore(1, true); // 1 silla
    private final Semaphore pedido   = new Semaphore(0, true); // señal empleado->mozo
    private final Semaphore servido  = new Semaphore(0, true); // señal mozo->empleado

    // ===== EMPLEADO =====
    public boolean intentarSentarse() {
        boolean pudo = asiento.tryAcquire();   // no bloquea
        return pudo;
    }

    public void avisarPedido() {
        pedido.release(); // despierta al mozo
    }

    public void esperarServido() throws InterruptedException {
        servido.acquire(); // bloquea hasta que el mozo sirva
    }

    public void liberarAsiento() {
        asiento.release();
    }

    // ===== MOZO =====
    public void esperarPedido() throws InterruptedException {
        pedido.acquire(); // duerme hasta que haya alguien
    }

    public void servir() {
        servido.release(); // habilita a comer
    }
}

