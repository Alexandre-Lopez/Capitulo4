import java.util.Scanner;

/**
 * Clase que genera todos los números primos de 1 hasta un número máximo especificado por el usuario utilizando el algoritmo
 * de la criba de Eratóstenes
 *
 * @author Alexandre Lopez Sanchez
 * @version 2.0
 */
public class Criba {
    /**
     * Genera un vector de números primos hasta el número máximo especificado.
     *
     * @param max el número máximo hasta el que se quieren generar los primos
     * @return un vector con los números primos hasta el número máximo
     */
    public static int[] generarPrimos(int max) {
        if (max < 2) {
            return new int[0]; // Vector vacío
        }

        boolean[] esPrimo = inicializarArrayPrimos(max);
        cribarMultiplosDePrimos(esPrimo);
        int[] primos = obtenerNumerosPrimos(esPrimo);

        return primos;
    }

    /**
     * Inicializa un array de booleanos indicando que todos los números hasta el máximo son primos.
     *
     * @param max el número máximo hasta el que se quieren generar los primos
     * @return un array de booleanos con valor "true" en las posiciones que corresponden a números primos
     */
    private static boolean[] inicializarArrayPrimos(int max) {
        int dim = max + 1;
        boolean[] esPrimo = new boolean[dim];
        for (int i = 0; i < dim; i++) {
            esPrimo[i] = true;
        }
        esPrimo[0] = esPrimo[1] = false;
        return esPrimo;
    }

    /**
     * Marca como no primos todos los múltiplos de los números primos encontrados.
     *
     * @param esPrimo un array de booleanos con valor "true" en las posiciones que corresponden a números primos
     */
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

    /**
     * Obtiene los números primos a partir del array de booleanos que indica qué números son primos.
     *
     * @param esPrimo un array de booleanos con valor "true" en las posiciones que corresponden a números primos
     * @return un array con los números primos encontrados
     */
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

    /**
     * Imprime un vector de enteros junto con un título en la consola.
     *
     * @param vector El vector de enteros a imprimir.
     * @param titulo El título que se mostrará junto al vector en la consola.
     */
    public static void imprimirVector(int[] vector, String titulo) {
        System.out.println("\n" + titulo + ":");
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(vector[i] + "\t");
        }
        System.out.println();
    }

    /**
     * Método main de la clase Criba
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        int dato = teclado.nextInt();
        int vector[] = new int[dato];

        for (int i = 0; i < vector.length; i++) {
            vector[i] = i + 1;
        }

        int[] primos = generarPrimos(dato);

        imprimirVector(vector, "Vector inicial hasta " + dato);
        imprimirVector(primos, "Vector de primos hasta " + dato);
    }
}
