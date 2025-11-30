package objetos;

import java.time.LocalDate;
import utils.Arreglo;

public class Institucion {
    //Declaracion de variables
    private String nombre;

    private Profesional[] profesionales = new Profesional[0];
    private Paciente[] pacientes = new Paciente[0];
    private Turno[] turnos = new Turno[200];
    private Puesto[] puestosLaborales = new Puesto[5];

    private double costoFijo;
    private double sueldos;

    private Calendario calendario;

    public Institucion(String nombre, double costoFijo, double sueldos, Calendario calendario){
        this.nombre = nombre;
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
    /**
     * Modifica el arreglo de profesionales
     * @param profesionales
     */
    public void setProfesionales(Profesional[] profesionales) {
        this.profesionales = profesionales;
    }
    //PACIENTE
    /**
     * Modifica el arreglo de pacientes
     * @param pacientes
     */
    public void setPacientes(Paciente[] pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * Modifica los turnos
     * @param turnos
     */
    public void setTurnos(Turno[] turnos) {
        this.turnos = turnos;
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
    public boolean  agregarPaciente(Paciente pte) {
        //Si recibe un paciente null, error
        if (pte == null){
            return false;
        } 
        //Valida si hay otro usuario con mismo por DNI
        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].getId().equals(pte.getId())) {
                return false;
            }
        }
        //Si no hay otro paciente con mismo dni, agrega al paciente
        pacientes = Arreglo.agregarPaciente(pacientes, pte);
        return true;
    }

    //AGREGAR PROFESIONAL
    public boolean agregarProfesional(Profesional prof){
        if (prof == null){
            return false; 
        }
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
        //Si no hay otro profesional con mismo dni o matricula, lo agrega
        profesionales = Arreglo.agregarProfesional(profesionales, prof);
        return true;
    }

    public Paciente buscarPacientePorDni(String dni) {
        Persona[]ordenado = Arreglo.ordenaPersonasID(pacientes);
        //3. Aplicamos búsqueda binaria
        Persona encontrado = Arreglo.buscaPersonaId(ordenado, dni);
        return (Paciente)encontrado;
    }

    public Paciente buscarPacienteApellido(String apellido){
        Persona encontrado = Arreglo.buscarPersonaApellido(pacientes, apellido);
        return (Paciente)encontrado;
    }

    public Profesional buscarProfesionalPorDni(String dni) {
        Persona[]ordenado = Arreglo.ordenaPersonasID(profesionales);
        //3. Aplicamos búsqueda binaria
        Persona encontrado = Arreglo.buscaPersonaId(ordenado, dni);
        return (Profesional)encontrado;
    }

    public Profesional buscarProfesionalApellido(String apellido){
        Persona encontrado = Arreglo.buscarPersonaApellido(profesionales, apellido);
        return (Profesional)encontrado;
    }

    public String mostrarPacientes(){
        String retorno = "";
        for (int index = 0; index < pacientes.length; index++){
            retorno += pacientes[index].getApellido()+", "+pacientes[index].getNombre()+" - "+pacientes[index].getId()+" Sesiones: "+pacientes[index].getSesionesRemanentes()+"/"+pacientes[index].getSesionesTotales()+"\n";
        }
        return retorno;
    }
    public String mostrarProfesionales(){
        String retorno = "";
        for (int index = 0; index < profesionales.length; index++){
            retorno += profesionales[index].getApellido()+", "+profesionales[index].getNombre()+" - "+profesionales[index].getId()+" - Matricula: "+profesionales[index].getMatricula()+"\n";
        }
        return retorno;
    }
    //AGENDAR NUEVO TURNO
    public void agendarNuevoTurno(Paciente pc, Profesional pf,Horario horario){
        LocalDate fecha = getCalendario().getFecha();
        
    }
}
