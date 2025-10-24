package TP5.PollosHermanos2;

public class Prueba {
    public static void main(String[] args) {
        SalaDeEspera sala= new SalaDeEspera();
        int[]pedidos={1,2,3};
        Thread mozo= new Thread(new Mozo(sala));
        mozo.start();
        Thread cocinero= new Thread(new Cocinero (sala));
        cocinero.start();
        for(int i=0;i<=10;i++){
            Thread emp= new Thread(new Empleado(sala, pedidos[(i + 1) % 2]),"Empleado "+(i+1));
            emp.start();
        }
    }
}
