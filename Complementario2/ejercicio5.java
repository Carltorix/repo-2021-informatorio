package Complementario2;


import java.util.ArrayList;
import java.util.List;

public class ejercicio5 {
    
    public static void main (String [] args){
        List<Integer> valorHora = new ArrayList<Integer>();
        List<Integer> cantHoras = new ArrayList<Integer>();
        List<Integer> resultados = new ArrayList<Integer>();
        cargarCantHora(cantHoras);
        cargarValorHora(valorHora);
        int acum=0;
        System.out.println(cantHoras);
        System.out.println(valorHora);
        for(int i=0; i < cantHoras.size(); i++){
            resultados.add(cantHoras.get(i)*valorHora.get(i));
            acum+=resultados.get(i);
        }
        System.out.println(resultados);
        System.out.println("Total Final: $ "+acum);
    }
    public static void cargarCantHora(List<Integer> cantHoras){
        cantHoras.add(6);
        cantHoras.add(7);
        cantHoras.add(8);
        cantHoras.add(4);
        cantHoras.add(5);
    }
    public static void cargarValorHora(List<Integer> valorHora){
        valorHora.add(350);
        valorHora.add(345);
        valorHora.add(550);
        valorHora.add(600);
        valorHora.add(320);
    }
}
