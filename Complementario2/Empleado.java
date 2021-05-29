package Complementario2;

public class Empleado extends Persona{

    private int horasTrabajadas;
    private double valorPorHora;

    public Empleado(String n, String a, int d, int hT, double e) {
        super(n, a, d);
        this.horasTrabajadas = hT;
        this.valorPorHora = e;
    }
    
    public String tString(){
        String cadena="";
        cadena = this.getNombre()+", "+this.getApellido();
        return cadena;
    }
    public void setHorasTrabajadas(int hT){
        this.horasTrabajadas = hT;
    }

    public int getHorasTrabajadas(){
        return this.horasTrabajadas;
    }

    public void setValorPorHora(double vP){
        this.valorPorHora = vP;
    }

    public double getValorPorHora(){
        return this.valorPorHora;
    }

    public double getSueldo(){
        return this.getValorPorHora() * this.getHorasTrabajadas();
    }
}
