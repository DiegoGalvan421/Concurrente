package ProblemasClasicos.FilosofosCenando;

public class Filosofo implements Runnable {
    private Tenedor ten1;
    private Tenedor ten2;
    private int pos;

    Filosofo(Tenedor ten1, Tenedor ten2, int pos) {
        this.ten1 = ten1; //derecho
        this.ten2 = ten2; //izqueirdo
        this.pos = pos;
    }

    public void run() {
        while (true) {
            System.out.println("Filosofo "+pos+": estoy pensando");
            try {
                Thread.sleep(500);
                tomarTenedores();
            } catch (InterruptedException e) {
            }
            System.out.println("Filosofo "+pos+": estoy comiendo");
            try {
                Thread.sleep(500);
                soltarTenedores();
            } catch (InterruptedException e) {
            }
        }
    }

    public void tomarTenedores() throws InterruptedException {
        if (pos % 2 != 0) {
            ten1.agarrarTenedor();
            System.out.println("Filosofo " + pos + " toma tenedor derecho");
            ten2.agarrarTenedor();
            System.out.println("Filosofo " + pos + " toma tenedor izquierdo");
        } else {
            ten2.agarrarTenedor();
            System.out.println("Filosofo " + pos + " toma tenedor izquierdo");
            ten1.agarrarTenedor();
            System.out.println("Filosofo " + pos + " toma tenedor derecho");
        }
    }

    public void soltarTenedores() throws InterruptedException {
        ten1.soltarTenedor();
        System.out.println("Filosofo " + pos + " suelta tenedor izquierdo");
        ten2.soltarTenedor();
        System.out.println("Filosofo " + pos + " suelta tenedor derecho");
    }

}
