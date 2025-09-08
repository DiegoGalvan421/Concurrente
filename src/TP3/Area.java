package TP3;

public class Area {
    private int espacios=20;
    private int numeroArea;

    Area ( int numero){
        this.numeroArea=numero;
    }
    public int getNumeroArea(){
        return numeroArea;
    }
    public synchronized int hacerReserva(){
        int reservado=0;
        if(espacios==0){
            System.out.println("no hay mas espacios");
        }else{
            reservado=espacios;
            espacios--;
        }
        return reservado;
    }
}
