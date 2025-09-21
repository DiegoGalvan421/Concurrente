package TP4;

public class PruebaImpresoras {
    public static void main(String[] args) throws InterruptedException{
        GestorImpresoras ung = new GestorImpresoras(1,2);
        Thread pedro = new Thread(new Cliente("pedro", ung,'A'), "pedro");
        Thread jorge = new Thread(new Cliente("jorge", ung,'A'), "jorge");
        Thread diego = new Thread(new Cliente("diego", ung,'B'), "diego");
        Thread facu = new Thread(new Cliente("facu", ung,'B'), "facu");
        Thread hui = new Thread(new Cliente("hui", ung,'C'), "hui");
        Thread myd = new Thread(new Cliente("myd", ung,'B'), "myd");
        
        pedro.start();
        jorge.start();
        diego.start();
        facu.start();
        hui.start();
        myd.start();

        pedro.join();
        jorge.join();
        diego.join();
        facu.join();
        hui.join();
        myd.join();
    }
}


