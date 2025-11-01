package TP5.Babuinos;

public class BabuinoA implements Runnable{
    private Cuerda cu;

    BabuinoA(Cuerda cu){
        this.cu=cu;
    }

    public void run(){
        try {
            cu.cruzarA();
            System.out.println("Estoy cruzando "+Thread.currentThread().getName());
            Thread.sleep(1000);
            cu.terminarCruzarA();
            System.out.println("Termine de cruzar "+Thread.currentThread().getName());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
