package Complementario1;

public class ejercicio4 {
        public static void main (String [] args){
            factorialresul(ejercicio3.captar1());
        }
        public static void factorialresul(int nro){
            int factorial=1;
            for(var i=2;i<=nro;i++){
                factorial = factorial*i;
            }
            System.out.println("El factorial de "+ nro + " es: " + factorial);

        }
}
