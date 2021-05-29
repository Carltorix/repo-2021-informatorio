package Complementario2;

public class Persona {

    private String nombre;
    private String apellido;
    private int dni;


    public Persona (String n, String a, int d){
        this.nombre = n;
        this.apellido = a;
        this.dni = d;
    }

    public void setNombre(String n){
        this.nombre = n;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setApellido(String a){
        this.apellido = a;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setDni(int d){
        this.dni = d;
    }

    public int getDni(){
        return this.dni;
    }
}