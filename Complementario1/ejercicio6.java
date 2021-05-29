package Complementario1;

public class ejercicio6 {
    public static void main (String [] args){
        mostrar();
    }
    public static void mostrar(){
        System.out.println("Ingrese la base y el exponente");
        potencia(ejercicio2.captar2());
    }
    public static void potencia(int[] numeros){
        if (numeros.length>0){
            var nro= numeros[0];
            var nro1= numeros[1];
            int resultado=numeros[0];
            for(var i=1;i<nro1;i++){
                resultado = resultado * nro;
            }
            System.out.println(nro +" elevado a "+ nro1 + " = " + resultado);
        }
    }
}
