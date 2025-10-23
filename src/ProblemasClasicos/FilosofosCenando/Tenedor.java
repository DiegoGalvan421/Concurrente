package ProblemasClasicos.FilosofosCenando;

public class Tenedor {
    private Boolean estado=false;//false=libre true=ocupado
    //implementacion con monitores
    //si quisiera hacerlo con semaforos, deberia hacer que cambien la variable estado, con un semaforo
    //Consultar como hacer  para evitar inanicion(starvation)?
    public synchronized void agarrarTenedor() throws InterruptedException{
        while(estado){
            this.wait();
        }
        estado=true;
    }
    public synchronized void soltarTenedor() throws InterruptedException{
        estado=false;
        notify();//no es necesario hacer el all, ya que cada tenedor tiene solo 2 filosofos
    }

}
