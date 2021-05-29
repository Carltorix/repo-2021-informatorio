package Complementario2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ejercicio3 {

    public static void main (String[] args){
        List<Integer> barajas = new ArrayList<Integer>();
        System.out.println("Creciente");
        for (int i = 0; i<13 ; i++){
            barajas.add(i+1);
            System.out.print(barajas.get(i) + " ");
        }
        System.out.println("");
        System.out.println("Descreciente");
        for (int i = barajas.size() - 1;i > -1 ;i--){
            System.out.print(barajas.get(i) + " ");
        }
        for (int i = 0; i<barajas.size() ; i++){
        int randomIndex = getRandomNumber(0, barajas.size() - 1);
        int temp = barajas.get(i);
            barajas.set(i, barajas.get(randomIndex));
            barajas.set(randomIndex, temp);
        }
        System.out.println("");
        System.out.println("Aleatorio");
        for (int value : barajas) System.out.print(value + " ");
    }

    public static int getRandomNumber(int min, int max) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    

}
