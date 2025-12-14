package objetos;

//CLASE PACIENTE QUE ES HIJA DE CLASE PERSONA

import java.time.LocalDate;

public class Paciente extends Persona {
    /*Atributos Privados */
    private String obraSocial;
    private int sesionesTotales;
    private Evolucion[] historiaClinica;
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
        this.historiaClinica = new Evolucion[0];
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
    public String vistaReducida(){
        return getApellido()+", "+getNombre()+" - "+getId();
    }

    //HISTORIA CLINICA
    /**
     * Crea un arreglo más grande
     * Copia el contenido anterior
     * Agrega la evolución nueva
     * Actualiza el arreglo del paciente
     * @param evo
     */
    public void agregarEvolucion(Evolucion evo) {
        Evolucion[] aux = new Evolucion[historiaClinica.length + 1];
        for (int i = 0; i < historiaClinica.length; i++) {
            aux[i] = historiaClinica[i];
        }
        aux[historiaClinica.length] = evo;
        historiaClinica = aux;
    }

    /**
     * Retorna un String con evolucion
     * @return
     */
    public String getEvolucion(int index) {
        if (index < 0 || index >= historiaClinica.length) {
            return null;
        }
        Evolucion evo = historiaClinica[index];
        return "Fecha: " + evo.getFecha() +
            "\n\n" + evo.getEvolucion() +
            "\n\n" + evo.getProfesional().getApellido() + ", " +
            evo.getProfesional().getNombre() +
            "\nMatrícula " + evo.getProfesional().getMatricula();
    }

    /**
     * Retorna la cantidad de evoluciones de la Historia Clinica
     * @return
     */
    public int getCantidadEvoluciones() {
        return historiaClinica.length;
    }

    /**
     * Retorna un String con todas las evoluciones
     * @return
     */
    public String getListadoEvoluciones() {
        String listado = "";
        for (int i = 0; i < historiaClinica.length; i++) {
            Evolucion evo = historiaClinica[i];
            LocalDate fecha = evo.getFecha();
            String profesional = evo.getProfesional().getApellido() + ", "+evo.getProfesional().getNombre();
            listado += (i+1)+". "+fecha+ "-"+profesional+"\n";
        }
        return listado;
    }
    /**
     * METODO QUE RETORNA LA CANTIDAD DE TURNOS QUE POSEE EL PACIENTE.
     * @return
     */
    public int cantidadTurnos(){
        return turnos.length;
    }
    /**
     * METODO QUE MODIFICA LAS SESIONES REMANENTES PARA RECUPERAR UN TURNO.
     */
    public void recuperarTurno(){
        setSesionesRemanentes(getSesionesRemanentes()+1);
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
                "\nSesiones Remanenes/Totales: "+sesionesRemanentes+"/"+sesionesTotales+
                "\nCrónico: no es cronico";
        }
        
    }
}