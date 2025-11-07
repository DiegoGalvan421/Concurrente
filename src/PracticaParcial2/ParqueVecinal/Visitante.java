package PracticaParcial2.ParqueVecinal;

public class Visitante implements Runnable {
    private Parque par;
    private int tipo;

    Visitante(int tipo, Parque par) {
        this.par = par;
        this.tipo = tipo;
    }

    public void run() {
        try {
            if (tipo == 0) {
                par.entrarVisitante();
                System.out.println("Entre a la sala soy residente" + Thread.currentThread().getName());
                Thread.sleep(300);
                par.salirVisitante();
                System.out.println("sali de la sala soy residente" + Thread.currentThread().getName());
            } else {
                par.entrarResidente();
                System.out.println("Entre a la sala " + Thread.currentThread().getName());
                Thread.sleep(300);
                par.salirResidente();
                System.out.println("sali de la sala " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }

    }
}
