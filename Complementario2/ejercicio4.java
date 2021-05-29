package Complementario2;

import java.util.ArrayList;
import java.util.List;

public class ejercicio4 {

    public static void main (String[] args){
        List<String> nombres = new ArrayList<String>();
        String [] nombreEst = {"Romina","Laura","Eliana","Sabrina","Micaela","Alejandra","Luis","Jonathan","Carlos","Diego","David","Raul"};
        for (int i = 0; i<12 ; i++){
            nombres.add(nombreEst[i]);
        }
        List<String> arrlist1 = nombres.subList(0, 4);
        List<String> arrlist2 = nombres.subList(4, 8);
        List<String> arrlist3 = nombres.subList(8, 12);
        System.out.println(arrlist1);
        System.out.println(arrlist2);
        System.out.println(arrlist3);
    }
    
    
}
