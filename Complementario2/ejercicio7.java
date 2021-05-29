package Complementario2;

import Complementario1.ejercicio2;
import java.util.ArrayList;
import java.util.List;

public class ejercicio7 {
    public static void main (String [] args){
        int [] numeros = ejercicio2.captar2();
        List<String> cadena = new ArrayList<String>();
        if (numeros[0]<numeros[1]){
            for(int i=numeros[0]; i<numeros[1];i++){
                if(multi2(i) && multi3(i)){
                    cadena.add("FizzBuzz");
                }else if (multi2(i)){
                    cadena.add("Fizz");
                }else if (multi3(i)){
                    cadena.add("Buzz");
                }else{
                    cadena.add(String.valueOf(i));
                }
            }
            System.out.println(cadena);
        }else{
            System.out.println("El primero tiene que ser menor que el segundo");
        }
    }

    public static boolean multi2(int i){
        
        if((i%2) == 0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean multi3(int i){
        
        if((i%3) == 0){
            return true;
        }else{
            return false;
        }
    }

}
