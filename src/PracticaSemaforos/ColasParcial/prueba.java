package PracticaSemaforos.ColasParcial;

public class prueba {
    public static void main(String[] args) {
        Cola col = new Cola();
        Thread ins = new Thread(new Insertor(col),"Insertor");
        Thread ext = new Thread(new Extractor(col),"Extractor");
        ins.start();
        ext.start();
    }
}
