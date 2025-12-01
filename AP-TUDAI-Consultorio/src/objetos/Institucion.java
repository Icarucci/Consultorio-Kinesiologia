package objetos;

import utils.Arreglo;

public class Institucion {
    //Declaracion de variables
    private String nombre;

    private Profesional[] profesionales = new Profesional[0];
    private Paciente[] pacientes = new Paciente[0];
    private Turno[] turnos = new Turno[0];
    private Puesto[] puestos = new Puesto[5];
    private double costoFijo;
    private double sueldos;

    public Institucion(String nombre, double costoFijo, double sueldos){
        this.nombre = nombre;
        this.costoFijo = costoFijo;
        this.sueldos = sueldos;
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

    public boolean addPuesto(Puesto pp){
        for(int i=0; i<puestos.length;i++){
            if(puestos[i]==null){
                puestos[i] =pp;
                return true;
            }
        }
        return false;
    }
    public Puesto getPuesto(int index){
        return puestos[index];
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
            if(!turnos[i].isAsistencia()){
                res += turnos[i].getTurnoId()+" - "+turnos[i]+"\n";
            }
        }
        return res;
    }

    public Turno getTurno(int index){
        return turnos[index];
    }

    public int cantidadTurnos(){
        return turnos.length;
    }
}
