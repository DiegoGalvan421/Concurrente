package PracticaParcial1.Toboganes;

public class PruebaToboganes {
    public static void main(String[] args) {
        LosToboganes tobs = new LosToboganes(3);
        int i;
        Thread[] personas = new Thread[4];
        for (i = 0; i < 4; i++) {
            personas[i] = new Thread(new Persona(tobs), "Persona" + i);
            personas[i].start();
        }
        Thread encargado= new Thread(new Encargado(tobs),"encargado");
        encargado.start();
        try{
                encargado.join();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        for(i=0;i<4;i++){
            try{
                personas[i].join();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        
        
    }

}
