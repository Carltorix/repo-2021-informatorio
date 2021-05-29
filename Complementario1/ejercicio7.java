package Complementario1;

public class ejercicio7 {
    public static void main (String [] args){
        mostrar();
    }

    public static void mostrar(){
        System.out.print("Ingrese una palabra: ");
        mayusculas(ejercicio1.captarPalabra());
    }
    public static void mayusculas(String palabra) {
        char [] lista = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
        char [] lista1={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};       
        String palabra_salida ="";
        for( int i=0; i < palabra.length();i++){
            int x = 0;
            if(palabra.charAt(i) != ' '){
                for (x = 0;x<lista.length;x++){
                    if(palabra.charAt(i) == lista[x]){
                        palabra_salida=palabra_salida+lista1[x];
                        break; 
                    }
                }
            } 
            if(x == lista.length || palabra.charAt(i) == ' ' ){
                palabra_salida=palabra_salida+palabra.charAt(i);
            }

        }
        System.out.println(palabra_salida);
    }
}