package TP1EXCEPCIONES;

public class EJ6 {
    public static void main(String[] args) {
        double[] v = new double[15];
        acceso_por_indice(v, 16);
    }

    public static double acceso_por_indice(double[] v, int j)
            throws IndexOutOfBoundsException {
        try {
            
                return v[j];
            
        } catch (IndexOutOfBoundsException exc) {
            throw new IndexOutOfBoundsException("El indice " + j + " no existe en el vector");
        }
    }

}
