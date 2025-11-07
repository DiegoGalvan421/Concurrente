package PracticaParcial2.ParqueVecinal;

public class Escuela implements Runnable {
    private Parque par;

    Escuela(Parque par) {
        this.par = par;
    }

    public void run() {
        try {
            par.entranEscuelas();
            System.out.println("Entramos al parque " + Thread.currentThread().getName());
            Thread.sleep(300);
            par.salirEscuela();
            System.out.println("Salidemos del parque " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
