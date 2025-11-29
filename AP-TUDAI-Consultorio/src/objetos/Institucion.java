package objetos;

import javax.swing.JOptionPane;

public class Institucion {
    //Declaracion de variables
    private String nombre;

    private Profesional[] profesionales;
    private Paciente[] pacientes;
    private Turno[] turnos;
    private Puesto[] puestosLaborales;

    private double costoFijo;
    private double sueldos;

    private Calendario calendario;

    public Institucion(String nombre, Profesional[] profesionales, Paciente[] pacientes, Turno[] turnos, Puesto[] puestosLaborales, double costoFijo, double sueldos, Calendario calendario){
        this.nombre = nombre;
        this.profesionales = profesionales;
        this.pacientes = pacientes;
        this.turnos = turnos;
        this.puestosLaborales = puestosLaborales;
        this.costoFijo = costoFijo;
        this.sueldos = sueldos;
        this.calendario = calendario;

    }


    //Getters & Setters
    //NOMBRE
    /**
     * Trae el nombre de la Institucion
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre de la Institucion
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //PROFESIONAL
    /***
     * Trae el arreglo de profesionales
     * @return
     */
    public Profesional[] getProfesionales() {
        return profesionales;
    }
    /**
     * Modifica el arreglo de profesionales
     * @param profesionales
     */
    public void setProfesionales(Profesional[] profesionales) {
        this.profesionales = profesionales;
    }

    //PACIENTES
    /**
     * Muestra el arreglo de pacientes
     * @return
     */
    public Paciente[] getPacientes() {
        return pacientes;
    }

    /**
     * Modifica el arreglo de pacientes
     * @param pacientes
     */
    public void setPacientes(Paciente[] pacientes) {
        this.pacientes = pacientes;
    }


    //TURNOS
    /**
     * Muestra los turnos
     * @return
     */
    public Turno[] getTurnos() {
        return turnos;
    }

    /**
     * Modifica los turnos
     * @param turnos
     */
    public void setTurnos(Turno[] turnos) {
        this.turnos = turnos;
    }

    //PUESTOS LABORALES
    /**
     * Muestra los puestos laborales
     * @return
     */
    public Puesto[] getPuestosLaborales() {
        return puestosLaborales;
    }

    /**
     * Modifica los puestos laborales
     * @param puestosLaborales
     */
    public void setPuestosLaborales(Puesto[] puestosLaborales) {
        this.puestosLaborales = puestosLaborales;
    }

    //COSTO FIJO
    /**
     * Muestra el costo fijo
     * @return
     */
    public double getCostoFijo() {
        return costoFijo;
    }

    /**
     * Modifica el costo fijo
     * @param costoFijo
     */
    public void setCostoFijo(double costoFijo) {
        this.costoFijo = costoFijo;
    }

    //SUELDOS
    /**
     * Muestra los sueldos
     * @return
     */
    public double getSueldos() {
        return sueldos;
    }

    /**
     * Modifica los sueldos
     * @param sueldos
     */
    public void setSueldos(double sueldos) {
        this.sueldos = sueldos;
    }

    //CALENDARIO
    /**
     * Muestra el calendario
     * @return
     */
    public Calendario getCalendario() {
        return calendario;
    }

    /**
     * Modifica el calendario
     * @param calendario
     */
    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    //METODOS
    // AGREGAR PACIENTE
    public boolean agregarPaciente(Paciente pte){
        if (pte == null) return false; 
        //Valida que NO haya otro paciente con el mismo DNI
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i] != null &&
                pacientes[i].getId().equals(pte.getId())) {
                return false;
            }
        }
        //Busca un lugar vacío para agregarlo
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i] == null) {
                pacientes[i] = pte;
                return true;
            }
        }
        //Si no hay posiciones null, la lista está llena
        return false;
    }

    //AGREGAR PROFESIONAL
    public boolean agregarProfesional(Profesional prof){
        if (prof == null) return false; 
        //Validamos si hay otro prof con mismo DNI o Matricula
        for (int i = 0; i < profesionales.length; i++) {
            if (profesionales[i] != null) {
                //DNI duplicado?
                if (profesionales[i].getId().equals(prof.getId())) {
                    return false;
                }
                //Matricula duplicada?
                if (profesionales[i].getMatricula() == prof.getMatricula()) {
                    return false;
                }
            }
        }
        //Recorremos el arreglo y agregamos el profesional en el lugar vacio
        for (int i = 0; i < profesionales.length; i++) {
            if (profesionales[i] == null) {
                profesionales[i] = prof;
                return true;
            }
        }
        return false;
    }





}
