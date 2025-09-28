package PracticaParcial1.ConflictoBelico;

public class Monitor {
    private int siguiente = 1;
    private int cantidadDivs;

    Monitor(int num) {
        cantidadDivs = num;
    }

    public synchronized boolean publicar(int idDiv) {
        boolean imprimi=false;
        int oracion1 = idDiv;
        int oracion2 = (2 * cantidadDivs + 1) - idDiv;

        if(idDiv==siguiente){
            System.out.println(Thread.currentThread().getName()+":"+oracion1);
            siguiente++;
            imprimi=true;
        }else if(siguiente==((2 * cantidadDivs + 1) - idDiv)){
            System.out.println(Thread.currentThread().getName()+":"+oracion2);
            siguiente++;
            imprimi=true;
        }
        return imprimi;
    }
}
