package TP1EXCEPCIONES;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PruebaExcep {
     // a) Verificar edad
    public void verificarEdad(int edad) throws Exception {
        if (edad < 18) {
            throw new Exception("La persona es menor de edad: " + edad);
        } else {
            System.out.println("Edad válida: " + edad);
        }
    }

    // b) Ruleta
    public void jugarRuleta(int numeroElegido) throws Exception {
        if (numeroElegido < 0 || numeroElegido > 36) {
            throw new Exception("Número de ruleta inválido: " + numeroElegido);
        }
        Random random = new Random();
        int numeroSorteado = random.nextInt(37); // entre 0 y 36
        System.out.println("Número sorteado: " + numeroSorteado);

        if (numeroElegido != numeroSorteado) {
            throw new Exception("Perdiste, tu número (" + numeroElegido + 
                                ") no salió.");
        } else {
            System.out.println("¡Ganaste! Salió tu número: " + numeroElegido);
        }
    }

    // c) Mostrar colección con excepción
    public void mostrarColeccion() {
        Scanner sc = new Scanner(System.in);
        List<Integer> numeros = new ArrayList<>();

        System.out.println("Ingrese 5 números:");
        for (int i = 0; i < 5; i++) {
            numeros.add(sc.nextInt());
        }

        try {
            System.out.println("Mostrando 7 valores (aunque hay solo 5):");
            for (int i = 0; i < 7; i++) {
                System.out.println("Valor en posición " + i + ": " + numeros.get(i));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Excepción generada: " + e.getMessage());
        }
    }
}
