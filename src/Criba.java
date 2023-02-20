import java.util.Scanner;

public class Criba {
    public static void main(String[] args) {
        int max = solicitarNumeroMaximo(); // Solicita el número máximo para la criba
        imprimirVectorInicial(max); // Imprime el vector inicial de números hasta el número máximo
        int[] primos = generarVectorPrimos(max); // Genera un vector con los números primos hasta el número máximo
        imprimirVectorPrimos(max, primos); // Imprime el vector de números primos
    }

    public static int[] generarVectorPrimos(int max) {
        if (max < 2) {
            return new int[0];
        }

        boolean[] esPrimo = new boolean[max + 1];
        inicializarEsPrimo(esPrimo); // Inicializa el vector de booleanos que representa los números primos
        aplicarCribaDeEratostenes(esPrimo, max + 1); // Realiza la criba de Eratóstenes
        return encontrarNumerosPrimos(esPrimo); // Devuelve un vector con los números primos
    }

    private static void inicializarEsPrimo(boolean[] esPrimo) {
        for (int i = 2; i < esPrimo.length; i++) {
            esPrimo[i] = true;
        }
        esPrimo[0] = false;
        esPrimo[1] = false;
    }

    private static void aplicarCribaDeEratostenes(boolean[] esPrimo, int dim) {
        for (int i = 2; i * i < dim; i++) {
            if (esPrimo[i]) {
                for (int j = i * i; j < dim; j += i) {
                    esPrimo[j] = false;
                }
            }
        }
    }

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

    private static int solicitarNumeroMaximo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número máximo para la criba de Eratóstenes:");
        return teclado.nextInt();
    }

    private static void imprimirVectorInicial(int max) {
        StringBuilder vectorInicial = new StringBuilder("\nVector inicial de números hasta: " + max + "\n");
        for (int i = 1; i <= max; i++) {
            vectorInicial.append(i).append("\t");
            if (i % 10 == 0) {
                vectorInicial.append("\n");
            }
        }
        System.out.println(vectorInicial);
    }

    private static void imprimirVectorPrimos(int max, int[] primos) {
        StringBuilder vectorPrimos = new StringBuilder("\nVector de números primos hasta: " + max + "\n");
        for (int i = 0; i < primos.length; i++) {
            vectorPrimos.append(primos[i]).append("\t");
            if ((i + 1) % 10 == 0) {
                vectorPrimos.append("\n");
            }
        }
        System.out.println(vectorPrimos);
    }
}
