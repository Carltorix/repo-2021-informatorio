package Complementario1;

public class ejercicio9 {
    public static void main(String[] args){
        mostrar_letras();
    }    

    public static void mostrar_letras(){
        System.out.println("ingrese una frase");
        String frase = ejercicio8.capturarDatos();
        System.out.println("ingrese la letra que desea contar: ");
        char letra= ejercicio1.captarPalabra().charAt(0);
        System.out.println(contarLetras(frase, letra));
    }

    public static int contarLetras(String frase, char letra){
        int contador=0;
        for( int i=0; i< frase.length();i++){
            if(frase.charAt(i)== letra){
                contador+=1;
            }
            }
    
        return contador;
    }
}