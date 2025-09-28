package PracticaParcial1.EdicionDeImagenes;

public class AppEdi implements Runnable{
    private EdicionDeIm edi;

    AppEdi(EdicionDeIm a){
        edi=a;
    }
    public void run(){
        while(true){
            edi.editTrab();
        }
    }
    
}
