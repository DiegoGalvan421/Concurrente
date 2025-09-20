package TP4;
import java.util.concurrent.Semaphore;
public class SincronicacionDe3 {
    private final Semaphore sem1= new Semaphore(1,true);
    private final Semaphore sem2= new Semaphore(0,true);
    private final Semaphore sem3= new Semaphore(0,true);


    //se puede mejorar
    public void proceso1(){
        try {
            sem1.acquire();
            System.out.println("hace algo:"+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }finally{
            sem2.release();
        }
    }
    public void proceso3(){
        try {
            sem2.acquire();
            System.out.println("hace algo:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }finally{
            sem3.release();
        }
    }
    public void proceso2 (){
        try {
            sem3.acquire();
            System.out.println("hace algo:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
        }finally{
            sem1.release();
        }
    }
}
