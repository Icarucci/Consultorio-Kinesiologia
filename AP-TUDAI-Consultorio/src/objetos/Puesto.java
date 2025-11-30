package objetos;

import java.time.LocalDate;
import utils.Arreglo;

public class Puesto {
    //ATRIBUTOS
    private static int contador=1;
    private String nombre;
    private Turno[] turnos;
    private int puestoNumero;
    
    //CONSTRUCTOR
    public Puesto(String nombre) {
        this.nombre = nombre;
        turnos = new Turno[0];
        puestoNumero = contador;
        contador++;
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getPuestoNumero() {
        return puestoNumero;
    }

    public void setPuestoNumero(int puestoNumero) {
        this.puestoNumero = puestoNumero;
    }

    public boolean validacion(LocalDate fecha, Hora hora){
        if(turnos.length == 0){
            return true;
        }
        for(int i=0;i<turnos.length;i++){
            if(turnos[i].getFecha().equals(fecha) && turnos[i].getHora()== hora){
                return false;
            }
        }
        return true;
    }
    //AGENDAR NUEVO TURNO
    public void agendarNuevoTurno(Turno tt){
        turnos = Arreglo.agregarTurno(turnos, tt);
    }
    public String showTurnos(){
        String res ="";
        if(turnos.length==0){
            res+="Sin turnos al momento";
            return res;
        }
        for(int i=0;i<turnos.length;i++){
            res += turnos[i]+"\n";
        }
        return res;
    }
}
