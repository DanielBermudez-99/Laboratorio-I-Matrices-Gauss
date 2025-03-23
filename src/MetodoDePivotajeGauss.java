public class MetodoDePivotajeGauss {
    public static void main(String[] args) {
        double[][] matriz = {
                {4, 5, 6, 7},
                {3, 4, 1, 7},
                {2, 3, 4, 5},
                {6, 3, 2, 1}
        };

        System.out.println("Matriz Original:");
        imprimirMatriz(matriz);

        triangularizar(matriz);

        System.out.println("\nMatriz Triangular Superior:");
        imprimirMatriz(matriz);
    }

    public static void triangularizar(double[][] matriz) {
        int n = matriz.length;
        double[] escala = new double[n];

        // 1. Determinar los factores de escala
        for (int i = 0; i < n; i++) {
            double max = 0;
            for (int j = 0; j < n; j++) {
                max = Math.max(max, Math.abs(matriz[i][j]));
            }
            escala[i] = max;
        }

        // 2. Aplicar el pivotaje parcial escalado y triangularización
        for (int k = 0; k < n - 1; k++) {
            // Encontrar la mejor fila para el pivote
            int filaPivote = k;
            double maxRatio = 0;
            for (int i = k; i < n; i++) {
                double ratio = Math.abs(matriz[i][k]) / escala[i];
                if (ratio > maxRatio) {
                    maxRatio = ratio;
                    filaPivote = i;
                }
            }

            // Intercambiar filas si es necesario
            if (filaPivote != k) {
                double[] temp = matriz[k];
                matriz[k] = matriz[filaPivote];
                matriz[filaPivote] = temp;

                double tempEscala = escala[k];
                escala[k] = escala[filaPivote];
                escala[filaPivote] = tempEscala;
            }

            // Eliminación de Gauss
            for (int i = k + 1; i < n; i++) {
                double factor = matriz[i][k] / matriz[k][k];
                for (int j = k; j < n; j++) {
                    matriz[i][j] -= factor * matriz[k][j];
                }
            }
        }
    }

    public static void imprimirMatriz(double[][] matriz) {
        for (double[] fila : matriz) {
            for (double valor : fila) {
                System.out.printf("%8.2f ", valor);
            }
            System.out.println();
        }
    }
}
