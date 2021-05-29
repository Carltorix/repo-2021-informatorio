package Complementario1;

public class ejercicio8 {
    public static void main(String [] args){
        ordenarDatos(datosPersonales());
    }


    public static String[] datosPersonales(){
        String [] datos = {"Nombre y Apellido" , "Edad" , "Dirección" , "Ciudad"};
        String [] datosSalida = new String [4];
        for(int i=0;i<datos.length;i++){
        System.out.println("ingrese su "+datos[i]+": ");
        datosSalida[i]=capturarDatos();
        }
        return datosSalida;
    }
    public static void ordenarDatos(String [] datos){
        String mostrar="";
        for(int i =datos.length-1;i>0;i--){
            mostrar= mostrar + datos[i] +" - ";
        }
        mostrar= mostrar + datos[0];
        System.out.println(mostrar);
    }
    public static String capturarDatos() {
        String  palabra= System.console().readLine();
        return palabra;
    }
}