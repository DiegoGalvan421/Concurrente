
import java.util.Scanner;

public class IntegralesDoblesSimples {

    // ========================================================
    // FUNCIÓN A INTEGRAR – CAMBIAR A GUSTO
    // Ejemplo: f(x,y) = x^2 + y^2
    // ========================================================
    public static double f(double x, double y) {
        return x * x + y * y;
    }

    // ========================================================
    // MÉTODO DEL TRAPECIO DOBLE (simple)
    // ========================================================
    public static double trapecioDoble(double a, double b, int m,
            double c, double d, int n) {

        double h = (b - a) / m; // paso en x
        double k = (d - c) / n; // paso en y
        double suma = 0;

        for (int i = 0; i <= m; i++) {
            double x = a + i * h;
            int pesoX = (i == 0 || i == m) ? 1 : 2;

            for (int j = 0; j <= n; j++) {
                double y = c + j * k;
                int pesoY = (j == 0 || j == n) ? 1 : 2;

                suma += pesoX * pesoY * f(x, y);
            }
        }

        double resultado = (h * k / 4.0) * suma;
        return resultado;
    }

    // ========================================================
    // MÉTODO DE SIMPSON 1/3 DOBLE (simple)
    // ========================================================
    public static double simpsonDoble(double a, double b, int m,
            double c, double d, int n) {

        if (m % 2 != 0 || n % 2 != 0) {
            System.out.println("ERROR: m y n deben ser pares en Simpson 1/3.");
            return 0;
        }

        double h = (b - a) / m;
        double k = (d - c) / n;
        double suma = 0;

        for (int i = 0; i <= m; i++) {
            double x = a + i * h;
            int pesoX = (i == 0 || i == m) ? 1 : (i % 2 == 1 ? 4 : 2);

            for (int j = 0; j <= n; j++) {
                double y = c + j * k;
                int pesoY = (j == 0 || j == n) ? 1 : (j % 2 == 1 ? 4 : 2);

                suma += pesoX * pesoY * f(x, y);
            }
        }

        double resultado = (h * k / 9.0) * suma;
        return resultado;
    }

    // ========================================================
    // PROGRAMA PRINCIPAL – INGRESO POR TECLADO
    // ========================================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Integral doble aproximada ===");

        System.out.print("Ingrese a: ");
        double a = sc.nextDouble();
        System.out.print("Ingrese b: ");
        double b = sc.nextDouble();
        System.out.print("Ingrese m (subdivisiones en x): ");
        int m = sc.nextInt();

        System.out.print("Ingrese c: ");
        double c = sc.nextDouble();
        System.out.print("Ingrese d: ");
        double d = sc.nextDouble();
        System.out.print("Ingrese n (subdivisiones en y): ");
        int n = sc.nextInt();

        double trap = trapecioDoble(a, b, m, c, d, n);
        double simp = simpsonDoble(a, b, m, c, d, n);

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Método del Trapecio = " + trap);
        System.out.println("Método de Simpson 1/3 = " + simp);
    }
}
