package Complementario1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ejercicio2 {
    public static void main (String [] args){
        multioperacion(captar2());
    }

    public static int[] captar2(){

        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese dos numeros: ");
            int nro1 = scan. nextInt();
            int nro2 = scan. nextInt();
            scan.close();
            /* var nro1 = Integer.parseInt(System.console().readLine());
            var nro2 = Integer.parseInt(System.console().readLine()); */
            int[] numeros ={nro1,nro2};
            return numeros;
        } catch (InputMismatchException ex) {
            System.out.println("Debe ingresar obligatoriamente un número entero.");
            int[] numeros = {};
            return numeros;
        }

    }
    public static void multioperacion(int[] numeros){
        if (numeros.length>0){
            String [] signo = {"+","-","*","/","%"};
            int[] oper = {numeros[0] + numeros[1],numeros[0] - numeros[1],numeros[0] * numeros[1],numeros[0] / numeros[1],numeros[0] % numeros[1]};
            for (var i=0; i<oper.length;i++){
                System.out.println(numeros[0]+" "+ signo[i] +" "+ numeros[1]+" = "+oper[i]);
            }
        }
    }

}