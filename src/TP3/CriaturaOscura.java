package TP3;

public class CriaturaOscura implements Runnable {
    private Energia energia;

    public CriaturaOscura(Energia energia) {
        this.energia = energia;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            energia.modificarEnergia(-3);
            System.out.println(Thread.currentThread().getName() + " (Criatura Oscura) drena energía. Actual: " + energia.getEnergia());
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}
