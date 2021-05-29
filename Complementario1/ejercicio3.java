package Complementario1;

import java.util.Scanner;

public class ejercicio3 {
    public static void main (String [] args){
        mostrarfactorial(captar1());
    }

    public static int captar1(){
        System.out.print("ingrese un numero: ");
        Scanner scan = new Scanner(System.in);
        int nro = scan. nextInt();
        scan.close();
        /* int  nro = Integer.parseInt(System.console().readLine()); */
        return nro;
    }

    public static void mostrarfactorial(int nro){
        for(var i=0;i<=nro;i++){
            for(var x=1;x<=i;x++){
                System.out.print(x);
            }
            System.out.println();
        }
        
    }
}
