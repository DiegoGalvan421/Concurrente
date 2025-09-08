package TP3;

public class SumadorParcial implements Runnable {
    private int[] arreglo;
    private int inicio, fin;
    private int[] sumasParciales;
    private int indice;

    public SumadorParcial(int[] arreglo, int inicio, int fin, int[] sumasParciales, int indice) {
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
        this.sumasParciales = sumasParciales;
        this.indice = indice;
    }

    @Override
    public void run() {
        int suma = 0;
        for (int i = inicio; i < fin; i++) {
            suma += arreglo[i];
        }
        sumasParciales[indice] = suma;
    }
}
