package TP5.PollosHermanos2;

public class Empleado implements Runnable {
    private SalaDeEspera sala;
    private int cantP;

    Empleado(SalaDeEspera sal, int pedido) {
        sala = sal;
        cantP = pedido;
    }

    public void run() {
        try {
            System.out.println("intento entrar a la sala " + Thread.currentThread().getName());
            sala.entrarSala();
            System.out.println("entre a la sala " + Thread.currentThread().getName());
            if (cantP == 2) {
                sala.avisarB();
                System.out.println("pedi una bebida " + Thread.currentThread().getName());
                sala.recibirB();
                System.out.println("recibi una bebida " + Thread.currentThread().getName());
                sala.avisarC();
                System.out.println("Pedi comida " + Thread.currentThread().getName());
                sala.recibirC();
                System.out.println("recibi comida " + Thread.currentThread().getName());
                sala.dejarSala();
                System.out.println("sali de la sala " + Thread.currentThread().getName());
            } else if (cantP == 1) {
                sala.avisarB();
                System.out.println("pedi una bebida " + Thread.currentThread().getName());
                sala.recibirB();
                System.out.println("recibi una bebida " + Thread.currentThread().getName());
                sala.dejarSala();
                System.out.println("sali de la sala " + Thread.currentThread().getName());
            } else {
                sala.avisarC();
                System.out.println("Pedi comida " + Thread.currentThread().getName());
                sala.recibirC();
                System.out.println("recibi comida " + Thread.currentThread().getName());
                sala.dejarSala();
                System.out.println("sali de la sala " + Thread.currentThread().getName());
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
