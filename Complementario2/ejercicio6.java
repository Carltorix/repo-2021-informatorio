package Complementario2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ejercicio6 {
    public static void main (String [] args){
    
        Set<Empleado> empleadosHashSet = new HashSet<Empleado>();
        cargarSet(empleadosHashSet);
        
        Map<Integer, Double> sueldosHashMap = new HashMap<Integer, Double>();
        for(Empleado empleado : empleadosHashSet){
            sueldosHashMap.put(empleado.getDni(),empleado.getSueldo());
        }
        System.out.println(sueldosHashMap);
    }
    public static void cargarSet(Set<Empleado> empleadosHashSet){
        Empleado empleado1 = new Empleado("Raul", "Perez", 25461287, 25, 190.50);
        Empleado empleado2 = new Empleado("Araceli", "Ramirez", 35421026, 20, 280.35);
        Empleado empleado3 = new Empleado("Alberto", "Fernandez", 26351478, 15, 250.40);
        empleadosHashSet.add(empleado1);
        empleadosHashSet.add(empleado2);
        empleadosHashSet.add(empleado3);
    }

}
