package objetos;

import utils.Arreglo;

public class Institucion {
    /*Atributos privados */
    private String nombre;
    private Profesional[] profesionales = new Profesional[0];
    private Paciente[] pacientes = new Paciente[0];
    private Turno[] turnos = new Turno[0];
    private Puesto[] puestos = new Puesto[5];
    private double costoFijo;
    private double valorTurno;

    /*Constructor */
    public Institucion(String nombre, double costoFijo){
        this.nombre = nombre;
        this.costoFijo = costoFijo;
        valorTurno = 25000;
    }
    //Getters & Setters
    /**
     * Nombre de la Institucion
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
    /**
     * Retorna el valor individual del turno
     * @return valor del turno
     */
    public double getValorTurno() {
        return valorTurno;
    }
    /**
     * Setea el valor individual del turno
     * @param valorTurno
     */
    public void setValorTurno(double valorTurno) {
        this.valorTurno = valorTurno;
    }
    /**
     * Agrega un puesto de trabajo al Arreglo de puestos
     * @param pp
     * @return true/false en la carga
     */
    public boolean addPuesto(Puesto pp){
        for(int i=0; i<puestos.length;i++){
            if(puestos[i]==null){
                puestos[i] =pp;
                return true;
            }
        }
        return false;
    }
    /**
     * Retorna un puesto a partir de su index
     * @param index
     * @return puesto de indice index
     */
    public Puesto getPuesto(int index){
        return puestos[index];
    }
    //COSTO FIJO
    /**
     * Muestra el costo fijo
     * @return valor de costo fijo
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
    /**
     * Agrega un paciente al arreglo de pacientes
     * @param paciente
     * @return boolean
     */
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
    /**
     * Agrega un profesional al arreglo de profesionales
     * @param profesional
     * @return boolean
     */
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
    /**
     * Busca paciente por dni
     * @param dni
     * @return paciente
     */
    public Paciente buscarPacientePorDni(String dni) {
        Persona[]ordenado = Arreglo.ordenaPersonasID(pacientes);
        //3. Aplicamos búsqueda binaria
        Persona encontrado = Arreglo.buscaPersonaId(ordenado, dni);
        return (Paciente)encontrado;
    }
    /**
     * Busca paciente por apellido
     * @param apellido
     * @return Paciente
     */
    public Paciente buscarPacienteApellido(String apellido){
        Persona encontrado = Arreglo.buscarPersonaApellido(pacientes, apellido);
        return (Paciente)encontrado;
    }
    /**
     * Busca profesional por dni
     * @param dni
     * @return Profesional
     */
    public Profesional buscarProfesionalPorDni(String dni) {
        Persona[]ordenado = Arreglo.ordenaPersonasID(profesionales);
        //3. Aplicamos búsqueda binaria
        Persona encontrado = Arreglo.buscaPersonaId(ordenado, dni);
        return (Profesional)encontrado;
    }
    /**
     * Busca profesional por apellido
     * @param apellido
     * @return Profesional
     */
    public Profesional buscarProfesionalApellido(String apellido){
        Persona encontrado = Arreglo.buscarPersonaApellido(profesionales, apellido);
        return (Profesional)encontrado;
    }
    /**
     * Retorna String con informacion de los pacientes
     * @return String
     */
    public String mostrarPacientes(){
        String retorno = "";
        for (int index = 0; index < pacientes.length; index++){
            retorno += pacientes[index].getApellido()+", "+pacientes[index].getNombre()+" - "+pacientes[index].getId()+" Sesiones: "+pacientes[index].getSesionesRemanentes()+"/"+pacientes[index].getSesionesTotales()+"\n";
        }
        return retorno;
    }
    /**
     * Retorna String con informacion de los profesionales
     * @return String
     */
    public String mostrarProfesionales(){
        String retorno = "";
        for (int index = 0; index < profesionales.length; index++){
            retorno += profesionales[index].getApellido()+", "+profesionales[index].getNombre()+" - "+profesionales[index].getId()+" - Matricula: "+profesionales[index].getMatricula()+"\n";
        }
        return retorno;
    }
    //AGENDAR NUEVO TURNO
    /**
     * Agrega nuevo Turno al arreglo de turnos
     * @param Turno
     */
    public void agendarNuevoTurno(Turno tt){
        turnos = Arreglo.agregarTurno(turnos, tt);
    }
    /**
     * Muestra los turnos registrados
     * @return String
     */
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
    /**
     * Retorna un turno a partir de su index
     * @param index
     * @return Turno
     */
    public Turno getTurno(int index){
        return turnos[index];
    }
    /**
     * Retorna un entero representando la cantidad total de turnos
     * @return integer
     */
    public int cantidadTurnos(){
        return turnos.length;
    }
    /**
     * Retorna el total de los sueldos de los profesionales.
     * @return double
     */
    public double pagarSueldos(){
        double total = 0.0;
        for(int i=0;i<profesionales.length;i++){
            total += profesionales[i].sueldoTotal(valorTurno);
        }
        return total;
    }

}
