package buscaminas;

import static buscaminas.Buscaminas.Movimiento;
import static buscaminas.Buscaminas.comprobarCelda;
import static buscaminas.Buscaminas.comprobarMinas;
import static buscaminas.Buscaminas.rellenarTablaMinas;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char filaMov;
        int filaMov1 = 0;
        char colMov;
        int colMov1 = 0;

        System.out.print("Introduce el numero de filas: ");
        int fila = sc.nextInt();
        System.out.print("Introduce el numero de columnas: ");
        int col = sc.nextInt();
        sc.nextLine();

        while (fila < 1 || col < 1) {
            System.out.println("Error, el numero de filas o columnas no puede ser menor a 1, vuelve a introducirlos");
            System.out.print("Introduce el numero de filas: ");
            fila = sc.nextInt();
            System.out.print("Introduce el numero de columnas: ");
            col = sc.nextInt();
            sc.nextLine();
        }
        char[][] tabla = new char[fila][col];

        System.out.print("Introduce el numero de minas: ");
        int numMinas = sc.nextInt();
        sc.nextLine();

        int[][] tablaMinas = rellenarTablaMinas(numMinas, fila, col);

        while (!comprobarCelda(tablaMinas, filaMov1, colMov1)) {
            System.out.println("Introduce la celda a destapar (i,j): ");
            String celda = sc.nextLine();
            filaMov = celda.charAt(0);
            filaMov1 = filaMov - '0';
            colMov = celda.charAt(2);
            colMov1 = colMov - '0';

            while ((filaMov1 < 0 || filaMov1 >= fila) || (colMov1 < 0 || colMov1 >= col)) {
                System.out.println("Celda incorrecta, introduce la celda a destapar (i,j) de nuevo: ");
                celda = sc.nextLine();
                filaMov = celda.charAt(0);
                filaMov1 = filaMov - '0';
                colMov = celda.charAt(2);
                colMov1 = colMov - '0';
            }

            int minasDestapadas = comprobarMinas(tablaMinas, filaMov1, colMov1, fila, col);
            Movimiento(tabla, filaMov1, colMov1, minasDestapadas);

            for (int i = 0; i < fila; i++) {
                System.out.print("| ");
                for (int j = 0; j < col; j++) {
                    System.out.print(tabla[i][j] + " | ");
                }
                System.out.println();
            }
        }
        if (comprobarCelda(tablaMinas, filaMov1, colMov1)) {
            System.out.println("Has perdido. Intentalo de nuevo");
            for (int i = 0; i < tablaMinas.length; i++) {
                System.out.print("| ");
                for (int j = 0; j < tablaMinas[0].length; j++) {
                    System.out.print(tablaMinas[i][j] + " | ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Felicidades! Has ganado");
        }

    }

}
