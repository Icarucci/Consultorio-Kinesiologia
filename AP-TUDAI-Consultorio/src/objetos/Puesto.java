package objetos;

import java.time.LocalDate;

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
    /**
     * Retorna el nombre del puesto
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setea el nombre del puesto
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Retorna el numero unico de puesto
     * @return
     */
    public int getPuestoNumero() {
        return puestoNumero;
    }
    /**
     * Setea el numero del puesto.
     * @param puestoNumero
     */
    public void setPuestoNumero(int puestoNumero) {
        this.puestoNumero = puestoNumero;
    }
    /**
     * Valida que no este ocupado en la fecha y hora solicitadas
     * @param fecha
     * @param hora
     * @return boolean
     */
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
    /**
     * Agrega un nuevo turno al arreglo de turnos
     * @param Turno
     */
    public void agendarNuevoTurno(Turno tt){
        turnos = agregarTurno(turnos, tt);
    }
    /* METODO QUE RECIBE UN ARREGLO DE TURNOS Y LE AGREGA UN TURNO EN UNA NUEVA POSICION.
    * @param arreglo
    * @return arreglo de Turno con una posicion mas
    */
    public Turno[] agregarTurno(Turno[]arreglo, Turno turno){
        Turno [] nuevo = new Turno[arreglo.length+1];
        for(int i=0;i<arreglo.length;i++){
            nuevo[i]=arreglo[i];
        }
        nuevo[nuevo.length-1] = turno;
        return nuevo;
    }

    /**
     * Retorna un String con la informacion de los turnos
     * @return String
     */
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
