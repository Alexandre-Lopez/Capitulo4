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
        aplicarCriba(esPrimo, max + 1); // Realiza la criba de Eratóstenes
        return encontrarNumerosPrimos(esPrimo); // Devuelve un vector con los números primos
    }

    private static void inicializarEsPrimo(boolean[] esPrimo) {
        for (int i = 2; i < esPrimo.length; i++) {
            esPrimo[i] = true;
        }
        esPrimo[0] = false;
        esPrimo[1] = false;
    }

    /* En este método se ha modificado la condición del primer bucle for, sustituyendo "Math.sqrt(dim) + 1"
    por "i * i < dim". Esta modificación se realiza para mejorar el rendimiento del programa,
    ya que calcular la raíz cuadrada de un número es más costoso computacionalmente que multiplicar un número por sí mismo.
    También se ha modificado el segundo bucle for, cambiando el valor inicial de j en el bucle interno para comenzar
    en "i * i" en lugar de "2 * i", lo que nos permite evitar duplicar el trabajo al eliminar los múltiplos de números
    menores a "i"*/
    private static void aplicarCriba(boolean[] esPrimo, int dim) {
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
        System.out.println("Introduce el número para la criba de Erastótenes:");
        return teclado.nextInt();
    }

    /* Se ha utilizado un StringBuilder en los métodos de "imprimirVectorInicial" y "imprimirVectorPrimos" en lugar de
    la concatenación de cadenas porque el proceso de concatenación de cadenas es ineficiente y puede afectar
    negativamente el rendimiento, ya que crea objetos String nuevos en memoria cada vez que se concatenan dos cadenas.
    En cambio, el StringBuilder crea un objeto mutable que permite la adición de cadenas sin crear un nuevo objeto cada
    vez, lo que mejora el rendimiento y la eficiencia de la aplicación. */
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
