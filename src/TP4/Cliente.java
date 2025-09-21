package TP4;

public class Cliente implements Runnable{
    private String nombre;
    private GestorImpresoras unG;
    private char tipo;
    Cliente(String nombre, GestorImpresoras ungGest, char unTipo){
        this.nombre=nombre;
        unG=ungGest;
        tipo=unTipo;
    }
    public void run(){ 
        unG.imprimir(tipo);
    }
}
