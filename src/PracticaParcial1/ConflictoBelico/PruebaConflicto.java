package PracticaParcial1.ConflictoBelico;

public class PruebaConflicto {
    public static void main(String[] args) {
        int n=5;
        Monitor mon= new Monitor(n);
        Thread[]divisiones=new Thread[n];
        int i;
        for(i=0;i<n;i++){
            divisiones[i]=new Thread(new Divisiones(i+1,mon),"division"+ (i+1));
            divisiones[i].start();
        }
        for(i=0;i<n;i++){
            try{
                divisiones[i].join();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            
        }
    }
}
