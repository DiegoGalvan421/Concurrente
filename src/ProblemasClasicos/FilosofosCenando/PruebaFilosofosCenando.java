package ProblemasClasicos.FilosofosCenando;

public class PruebaFilosofosCenando {
    public static void main(String[] args) {
        Tenedor[]tenedores= new Tenedor[5];
        for(int i=0; i<=4;i++){
            tenedores[i]=new Tenedor();
        }
        for(int i=0;i<=4;i++){
            Thread filosofo= new Thread(new Filosofo(tenedores[i], tenedores[(i + 1) % 5], i));
            filosofo.start();
            
        }
    }
}
