package PracticaParcial1.EdicionDeImagenes;

public class PruebaEdicion {
    public static void main(String[] args) throws InterruptedException {
        EdicionDeIm edi = new EdicionDeIm();

        // Hilo editor (aplicación)
        Thread editor = new Thread(new AppEdi(edi), "Editor");
        editor.start();

        // Simula carga de 5 imágenes
        for (int i = 1; i <= 5; i++) {
            Thread imagen = new Thread(new Imagenes(edi), "Imagen-" + i);
            imagen.start();
            Thread.sleep(500); // Simula llegada de imágenes en distintos momentos
        }
    }
}
