/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bonus1;

import java.util.Arrays;

/**
 * @author Teo
 */
public class Bonus1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length != 1)
            throw new IllegalArgumentException("Trebuie dat un parametru de intrare!");

        try {
            Integer.parseInt(args[0]);
            System.out.println(args[0] + " e parametru valid");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Parametrul de intrare nu e intreg!");
            //System.out.println(args[0]+"NU e parametru valid");
        }

        int n = Integer.parseInt(args[0]);
        int a[][] = new int[n][n];
        int i, j, poz;
        for (i = 0; i < n; i++) {
            poz = (i + 1) % n;
            a[i][poz] = 1;
        }
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                a[j][i] = a[i][j];
        a[0][n - 1] = a[n - 1][0] = 1;
//            for(j=0;j<n;j++)
//            {
//                if(i>j)
//                    a[i][i-1]=1;
//                a[j][i]=a[i][j];
//            }
//        a[n][0]=a[0][n]=1;
        System.out.println("Matricea: ");
        System.out.println(Arrays.deepToString(a));
        afisareMatrice(a);

//        int id[][]={{2,0,0},{0,2,0},{0,0,2}};
//        inmultire(a,id,3);
//        System.out.println(Arrays.deepToString(a));

        exponentiereBinara(a, n, 7);
        System.out.println("Exponentierea babana binara:");
        System.out.println(Arrays.deepToString(a));
        afisareMatrice(a);
        System.out.println("A^k - care drumuri de lungime k sunt intre i si j");

    }

    public static void exponentiereBinara(int x[][], int n, int p) {
        int rez[][] = new int[n][n];
        for (int i = 0; i < n; i++)
            rez[i][i] = 1;

        while (p > 0) {
            if (p % 2 == 1)
                inmultire(rez, x, n);
            inmultire(x, x, n);
            p = p / 2;
        }

        //copiem ce e in rez in x
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                x[i][j] = rez[i][j];
    }

    public static void inmultire(int x[][], int y[][], int n) {
        int rez[][] = new int[n][n];
        int i, j, k;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                for (k = 0; k < n; k++) {
                    rez[i][j] = rez[i][j] + x[i][k] * y[k][j];
                }
            }
        }

        //Punem rezultatul inmultirii in x
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                x[i][j] = rez[i][j];
    }

    public static void afisareMatrice(int matrice[][]){
        for (int linie = 0; linie < matrice.length; linie++) {
            for (int coloana = 0; coloana < matrice.length; coloana++) {
                System.out.print(matrice[linie][coloana] + " ");
            }
            System.out.println();
        }
    }

}
