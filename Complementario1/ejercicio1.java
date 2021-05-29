package Complementario1;

import java.util.Scanner;

public class ejercicio1 {
    public static void main (String [] args){
       mostrar();    
    }

    public static void mostrar(){
        System.out.print("ingrese su nombre: ");
        holamundo(captarPalabra());
    }
    public static String captarPalabra() {
        Scanner scan = new Scanner(System.in);
        String palabra = scan. nextLine();
        scan.close();
        return palabra;
    }

    public static void holamundo(String palabra){
        System.out.println("Hola "+palabra);

    }

}