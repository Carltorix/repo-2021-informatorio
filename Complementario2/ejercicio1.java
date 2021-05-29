package Complementario2;

import java.util.ArrayList;
import java.util.List;

public class ejercicio1 {

    public static void main(String[] args){
        mostrar(captar_palabras());

    }

    public static List<String> captar_palabras(){
        List<String> ciudades = new ArrayList<>();
        String palabra="";
        boolean bandera=true;
        do {
            System.out.println("Ingrese sus ciudades favoritas, si ingreso un numero mayor a 0");
            palabra=System.console().readLine();
            if (validacion(palabra)){
                bandera=false;
            }else{
                ciudades.add(palabra);
            }

        } while (bandera);

        return ciudades;
    }
    public static boolean validacion(String palabra){
        try{
            Integer.parseInt(palabra);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static void mostrar(List<String> ciudades){
        int i=1;
        for (String ciudad : ciudades) {
            System.out.println("# "+i+" - "+ciudad);
            i++;
        }

    }

}