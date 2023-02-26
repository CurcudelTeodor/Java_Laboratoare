/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema1;
import java.util.Arrays;
/**
 *
 * @author Teo
 */
public class Tema1 {

    /**
     * @param args the command line arguments
     */
     public static void main(String []args) 
    {      
        long startTime=System.nanoTime();

        if(args.length!=1)
            throw new IllegalArgumentException("Trebuie dat un parametru de intrare!");
        
        try
        {
            Integer.parseInt(args[0]);
            System.out.println(args[0]+" e parametru valid");
        }
        catch(NumberFormatException e)
        {   
            throw new IllegalArgumentException("Parametrul de intrare nu e intreg!");
            //System.out.println(args[0]+"NU e parametru valid");
        }

        //am ajuns aici -> parametru valid
        int n=Integer.parseInt(args[0]);
        int [][] m = new int [n][n];
        int i,j;

        for(j=0;j<n;j++)
            m[0][j]=j+1;

        for (i=1;i<n;i++)
        {   
            for(j=0;j<n;j++)
            {
                m[i][j]=m[i-1][j]%n+1;
            }    
        }
        System.out.println(Arrays.deepToString(m));

        System.out.println("Pe linii:");
        for(i=0;i<n;i++)
        {
            StringBuilder sb = new StringBuilder();
            for(j=0;j<n;j++)
                sb.append(m[i][j]);
            System.out.println(sb);
        }

        System.out.println("Pe coloane:");
        for(j=0;j<n;j++)
        {
            StringBuilder sb = new StringBuilder();
            for(i=0;i<n;i++)
                sb.append(m[i][j]);
            System.out.println(sb);
        }

        long endTime=System.nanoTime();
        long totalTime=endTime-startTime;
        System.out.println(totalTime + " nanosecunde");
    }
    
}
