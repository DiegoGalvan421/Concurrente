package TP5.Babuinos;

public class BabuinoB implements Runnable{
    private Cuerda cu;

    BabuinoB(Cuerda cu){
        this.cu=cu;
    }

    public void run(){
        try {
            cu.cruzarB();
            System.out.println("Estoy cruzando "+Thread.currentThread().getName());
            Thread.sleep(1000);
            cu.terminarCruzarB();
            System.out.println("Termine de cruzar "+Thread.currentThread().getName());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
