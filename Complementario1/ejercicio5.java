package Complementario1;

public class ejercicio5 {
    
    public static void main (String [] args){
        multiplicar(ejercicio2.captar2());
    }
    public static void multiplicar(int [] numeros){
        if (numeros.length>0){
            var nro = numeros[0];
            var nro1 = numeros[1];
            int resultado=nro1;
            for(var i=1;i<nro;i++){
                resultado = resultado + nro1;
            }
            System.out.println(nro +" x "+ nro1 + " = " + resultado);
        }
    }
}
