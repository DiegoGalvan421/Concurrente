package TP1EXCEPCIONES;

public class CiudadNoEncontradaException extends Exception{
    /* para definir tus propias excepciones se hace de esta forma, luego la forma de tratarlas depende de como lo quieras tratar en el codigo */
    public CiudadNoEncontradaException(String nombreCiudad) {
        super("La ciudad '" + nombreCiudad + "' no existe en el sistema.");
    }
    /*ej: de uso aca, lanza la excepcion para poder capturarla despues.
     * public void buscarCiudad(String nombre) throws CiudadNoEncontradaException {
        // Supongamos que no la encuentra en el AVL
        boolean existe = false; 
        if (!existe) {
            throw new CiudadNoEncontradaException(nombre);
        }
    }
        y aca lo que hace es capturar el error que se lanza dentro del metodo.
        public class Main {
    public static void main(String[] args) {
        RedAgua red = new RedAgua();

        try {
            red.buscarCiudad("Cordoba");
        } catch (CiudadNoEncontradaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
     */
}
