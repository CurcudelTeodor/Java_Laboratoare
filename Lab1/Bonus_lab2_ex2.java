/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bonus_lab2_ex2;

import java.util.Arrays;

/**
 * @author Teo
 */
public class Bonus_lab2_ex2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   //n-6 k=4
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        if (n < k + 1 || (n * k) % 2 == 1) {
            System.out.println("Nu poate exista un graf " + k + " regulat cu " + n + " noduri");
            System.exit(1);
        }
        int[][] m = new int[n][n];
        int i, j;
        for (i = 0; i < n; i++)
            for (j = 1; j <= k / 2; j++) //de la 1 pt a nu pune muchie de la 0 la 0, daca am merge pana la k -> n*k muchii, dar noua ne trebuie doar n*k/2
            {
                int nod = (i + j) % n; //mergem circular
                m[i][nod] = m[nod][i] = 1;
            }
        System.out.println(Arrays.deepToString(m));
        afiseazaMatrice(m);
    }

    public static void afiseazaMatrice(int x[][]) {
        int i, j;
        for (i = 0; i < x.length; i++) {
            for (j = 0; j < x.length; j++)
                System.out.print(x[i][j] + " ");
            System.out.println();
        }
    }

}
