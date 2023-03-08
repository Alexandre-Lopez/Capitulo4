import java.util.Scanner;
public class Criba {
    // Generar números primos de 1 a max
    public static int[] generarPrimos (int max) {
        if (max < 2) {
            return new int[0]; // Vector vacío
        }

        boolean[] esPrimo = inicializarArrayPrimos(max);
        cribarMultiplosDePrimos(esPrimo);
        int[] primos = obtenerNumerosPrimos(esPrimo);

        return primos;
    }

    private static boolean[] inicializarArrayPrimos(int max) {
        int dim = max + 1;
        boolean[] esPrimo = new boolean[dim];
        for (int i = 0; i < dim; i++) {
            esPrimo[i] = true;
        }
        esPrimo[0] = esPrimo[1] = false;
        return esPrimo;
    }

    private static void cribarMultiplosDePrimos(boolean[] esPrimo) {
        int dim = esPrimo.length;
        for (int i = 2; i < Math.sqrt(dim) + 1; i++) {
            if (esPrimo[i]) {
                for (int j = 2 * i; j < dim; j += i) {
                    esPrimo[j] = false;
                }
            }
        }
    }

    private static int[] obtenerNumerosPrimos(boolean[] esPrimo) {
        int cuenta = 0;
        int dim = esPrimo.length;
        for (int i = 0; i < dim; i++) {
            if (esPrimo[i]) {
                cuenta++;
            }
        }
        int[] primos = new int[cuenta];
        for (int i = 0, j = 0; i < dim; i++) {
            if (esPrimo[i]) {
                primos[j++] = i;
            }
        }
        return primos;
    }

    public static void imprimirVector(int[] vector, String titulo) {
        System.out.println("\n" + titulo + ":");
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(vector[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        int dato = teclado.nextInt();
        int vector[] = new int[dato];

        for (int i = 0; i < vector.length; i++) {
            vector[i] = i+1;
        }

        int[] primos = generarPrimos(dato);

        imprimirVector(vector, "Vector inicial hasta " + dato);
        imprimirVector(primos, "Vector de primos hasta " + dato);
    }
}
