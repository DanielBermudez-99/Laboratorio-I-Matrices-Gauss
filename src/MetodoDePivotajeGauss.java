public class MetodoDePivotajeGauss {

    // Función para hacer el pivotaje parcial escalado y obtener la matriz triangular superior
    public static void pivotajeParcialEscalado(double[][] A) {
        int n = A.length;

        // Aplicar la eliminación de Gauss con pivotaje parcial escalado
        for (int k = 0; k < n; k++) {
            // Paso 1: Escalado
            double[] maxEscala = new double[n];
            for (int i = 0; i < n; i++) {
                double max = Math.abs(A[i][0]);
                for (int j = 1; j < n; j++) {
                    max = Math.max(max, Math.abs(A[i][j]));
                }
                maxEscala[i] = max;
            }

            // Paso 2: Pivotaje parcial
            int maxRow = k;
            double maxVal = 0;
            for (int i = k; i < n; i++) {
                double scaleFactor = Math.abs(A[i][k]) / maxEscala[i];
                if (scaleFactor > maxVal) {
                    maxVal = scaleFactor;
                    maxRow = i;
                }
            }

            // Intercambiar filas si es necesario
            if (maxRow != k) {
                double[] temp = A[k];
                A[k] = A[maxRow];
                A[maxRow] = temp;
            }

            // Paso 3: Eliminación de Gauss
            for (int i = k + 1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                for (int j = k; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }
            }
        }
    }

    // Función para imprimir la matriz
    public static void imprimirMatriz(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(String.format("%6.2f", A[i][j]) + " ");
            }
            System.out.println();
        }
    }

    // Función principal para probar la implementación
    public static void main(String[] args) {
        // Matriz de entrada
        double[][] A = {
                {1, 1, -1, 1},
                {2, 5, -3, 3},
                {-1, 5, -2, 3},
                {2, 11, -4, 4}
        };

        // Mostrar la matriz original
        System.out.println("Matriz original:");
        imprimirMatriz(A);

        // Aplicar el pivotaje parcial escalado
        pivotajeParcialEscalado(A);

        // Mostrar la matriz triangular superior resultante
        System.out.println("\nMatriz triangular superior:");
        imprimirMatriz(A);
    }
}
