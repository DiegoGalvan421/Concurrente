package PracticaSemaforos.ProblemaComedero;

public class Animal implements Runnable {
    private Comedor sal;
    private int tipo;

    Animal(Comedor sal, int i) {
        this.sal = sal;
        tipo = i;
    }

    public void run() {
        try {
            if (tipo == 0) {
                while (true) {
                    sal.entrarPerro();
                    sal.salirPerro();
                    Thread.sleep(300);
                }

            } else {
                while (true) {
                    sal.entrarGato();
                    sal.salirGato();
                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
