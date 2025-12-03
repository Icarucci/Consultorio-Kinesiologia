package objetos;

//CLASE PACIENTE QUE ES HIJA DE CLASE PERSONA

import java.time.LocalDate;

public class Paciente extends Persona {
    /*Atributos Privados */
    private String obraSocial;
    private int sesionesTotales;
    private String[] historiaClinica;
    private int sesionesRemanentes;
    private boolean cronico;

    /*Constructor */
    public Paciente(String id, String nombre, String apellido, String direccion, int telefono, String obraSocial,
            int sesionesTotales, boolean cronico) {
        //ID, NOMBRE, APELLIDO, DIRECCION, TELEFONO se heredan del padre PERSONA
        super(id, nombre, apellido, direccion, telefono);
        //Son caracteristicos de Paciente
        this.obraSocial = obraSocial;
        this.sesionesTotales = sesionesTotales;
        this.historiaClinica = new String[0];
        this.cronico = cronico;
        sesionesRemanentes = sesionesTotales;
    }
    //Getters & Setters
    /**
     * Retorna la cantidad de sesiones remanentes
     * @return entero
     */
    public int getSesionesRemanentes() {
        return sesionesRemanentes;
    }
    /**
     * Setea la cantidad de sesiones remanentes
     * @param sesionesRemanentes
     */
    public void setSesionesRemanentes(int sesionesRemanentes) {
        this.sesionesRemanentes = sesionesRemanentes;
    }
    //OBRA SOCIAL
    /**
     * Retorno la Obra Social del paciente
     * @return String
     */
    public String getObraSocial() {
        return obraSocial;
    }
    /**
     * Modifica la Obra Social del paciente
     * @param obraSocial
     */
    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    //CANTIDAD DE SESIONES
    /**
     * Trae la cantidad de Sesiones del paciente
     * @return
     */
    public int getSesionesTotales() {
        return sesionesTotales;
    }
    /**
     * Modifica la cantidad de Sesiones del paciente
     * @param sesionesTotales
     */
    public void setSesionesTotales(int sesionesTotales) {
        this.sesionesTotales = sesionesTotales;
    }

    //CRONICO
    /**
     * Devuelve si es cronico o no
     * @return
     */
    public boolean isCronico() {
        return cronico;
    }
    /**
     * Modifica si es crónico o no
     * @param cronico
     */
    public void setCronico(boolean cronico) {
        this.cronico = cronico;
    }

    /**
     * Controla si el paciente ya posee un turno en la fecha pasada por parametro.
     * @param fecha
     * @return boolean
     */
    public boolean validacion(LocalDate fecha){
        if(turnos.length == 0){
            return true;
        }
        for(int i=0;i<turnos.length;i++){
            LocalDate comparada = turnos[i].getFecha();
            if(!comparada.equals(fecha)){
                return true;
            }
        }
        return false;        
    }
    
    //Mostrar paciente
    @Override
    public String toString() {
        if(cronico){
            return  "PACIENTE\n"+super.toString()+
                "\nObra Social: "+obraSocial+
                "\nSesiones Totales: "+sesionesTotales+
                "\nCrónico: es cronico";
        }else{
            return  "PACIENTE\n"+super.toString()+
                "\nObra Social: "+obraSocial+
                "\nSesiones Totales: "+sesionesTotales+
                "\nCrónico: no es cronico";
        }
        
    }
}
