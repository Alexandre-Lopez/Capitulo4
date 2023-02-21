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
     * Método main de la clase Criba
     *
     * @param args
     */
    public static void main(String[] args) {
        int max = solicitarNumeroMaximo();
        imprimirVectorInicial(max);
        int[] primos = generarVectorPrimos(max);
        imprimirVectorPrimos(max, primos);
    }

    /**
     * Método que genera un vector con todos los números primos desde 2 hasta el número máximo especificado.
     * Si el número máximo es menor que 2, el método devuelve un vector vacío.
     *
     * @param max El número máximo hasta el cual se generan los números primos
     * @return Un vector de enteros que contiene todos los números primos entre 2 y el número máximo (inclusive),
     * o un vector vacío si el número máximo es menor que 2.
     */
    private static int[] generarVectorPrimos(int max) {
        if (max < 2) {
            return new int[0];
        }

        boolean[] esPrimo = new boolean[max + 1];
        inicializarEsPrimo(esPrimo);
        aplicarCriba(esPrimo, max + 1);
        return encontrarNumerosPrimos(esPrimo);
    }

    /**
     * Método que inicializa el vector de primos "esPrimo" con el valor "true" para las posiciones mayores a 1,
     * y con el valor "false" para las posiciones 0 y 1
     *
     * @param esPrimo Vector de booleanos que indica si cada número hasta una dimensión dada es primo o no
     */
    private static void inicializarEsPrimo(boolean[] esPrimo) {
        for (int i = 2; i < esPrimo.length; i++) {
            esPrimo[i] = true;
        }
        esPrimo[0] = false;
        esPrimo[1] = false;
    }

    /**
     * Método que implementa el algoritmo de la criba de Eratóstenes para identificar qué números hasta un valor máximo
     * proporcionado por el usuario son primos y cuáles no lo son
     *
     * @param esPrimo Vector de booleanos que indica si cada número hasta una dimensión dada es primo o no
     * @param dim Tamaño del vector
     */
    private static void aplicarCriba(boolean[] esPrimo, int dim) {
        for (int i = 2; i * i < dim; i++) {
            if (esPrimo[i]) {
                for (int j = i * i; j < dim; j += i) {
                    esPrimo[j] = false;
                }
            }
        }
    }

    /**
     * Método que devuelve un vector que contiene todos los números primos encontrados en el vector de booleanos que se le pasa como parámetro
     *
     * @param esPrimo Vector de booleanos que indica si cada número hasta una dimensión dada es primo o no
     * @return Vector de enteros que contiene todos los números primos encontrados en el vector de booleanos
     */
    private static int[] encontrarNumerosPrimos(boolean[] esPrimo) {
        int cuenta = 0;
        for (boolean esPrimoActual : esPrimo) {
            if (esPrimoActual) {
                cuenta++;
            }
        }

        int[] primos = new int[cuenta];
        for (int i = 0, j = 0; i < esPrimo.length; i++) {
            if (esPrimo[i]) {
                primos[j++] = i;
            }
        }

        return primos;
    }

    /**
     * Método que solicita al usuario un número máximo para aplicar la criba de Erastótenes
     *
     * @return El número máximo ingresado por el usuario
     */
    private static int solicitarNumeroMaximo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        return teclado.nextInt();
    }

    /**
     * Método que imprime el vector inicial hasta el número máximo especificado.
     *
     * @param max Número que el usuario ha introducido por teclado
     */
    private static void imprimirVectorInicial(int max) {
        StringBuilder vectorInicial = new StringBuilder("\nVector inicial hasta: " + max + "\n");
        for (int i = 1; i <= max; i++) {
            vectorInicial.append(i).append("\t");
            if (i % 10 == 0) {
                vectorInicial.append("\n");
            }
        }
        System.out.println(vectorInicial);
    }

    /**
     * Método que imprime el vector de números primos hasta el número máximo especificado.
     *
     * @param max Número que el usuario ha introducido por teclado
     * @param primos Vector de enteros que contiene todos los números primos encontrados en el vector de booleanos esPrimo
     */
    private static void imprimirVectorPrimos(int max, int[] primos) {
        StringBuilder vectorPrimos = new StringBuilder("\nVector de primos hasta: " + max + "\n");
        for (int i = 0; i < primos.length; i++) {
            vectorPrimos.append(primos[i]).append("\t");
            if ((i + 1) % 10 == 0) {
                vectorPrimos.append("\n");
            }
        }
        System.out.println(vectorPrimos);
    }
}