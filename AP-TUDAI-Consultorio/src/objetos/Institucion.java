package objetos;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private Comprobante[] comprobantes = new Comprobante[0];

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
    //METODOS DE ORDENAMIENTO DE PACIENTES
    public void ordenaPacienteId(){
        Arreglo.ordenaPersonasID(pacientes);
    }
    public void ordenaPacienteApellido(){
        Arreglo.ordenaPersonasApellido(pacientes);
    }
    public void ordenaPAcienteSesiones(){
        Arreglo.ordenaPersonasSesiones(pacientes);
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
     * Retorna la cantidad de profesionales
     * @param prof
     * @return
     */
    public int cantProfesionales(){
        return profesionales.length;
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
    public Paciente[] buscarPacienteApellido(String apellido){
        Persona[] encontrado = Arreglo.buscarPersonaApellido(pacientes, apellido);
        Paciente[] resultado = new Paciente[encontrado.length];
        for(int i=0;i<resultado.length;i++){
            resultado[i] = (Paciente)encontrado[i];
        }
        return resultado;
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
    public Profesional[] buscarProfesionalApellido(String apellido){
        Persona[] encontrado = Arreglo.buscarPersonaApellido(profesionales, apellido);
        Profesional[] resultado = new Profesional[encontrado.length];
        for(int i=0;i<resultado.length;i++){
            resultado[i] = (Profesional)encontrado[i];
        }
        return resultado;
    }
    /**
     * Retorna al profesional buscado por matricula
     * @param buscada
     * @return
     */
        public Profesional buscarProfesionalMatricula(int buscada){
        for(int i=0;i<profesionales.length;i++){
            if(profesionales[i].getMatricula()== buscada){
                return profesionales[i];
            }
        }
        return null;
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
    public boolean encontrarPacienteIndex(String id){
        boolean resultado = false;
        boolean fin=false;
        int index=0;
        int i = -1;
        while(!fin){
            if(index<pacientes.length){
                if(pacientes[index].getId().equals(id)){
                    fin=true;
                    i = index;
                }
            }else{
                fin = true;
            }
            index++;
        }
        if(i != -1){
            pacientes = (Paciente[])Arreglo.eliminar(pacientes,i);
            resultado=true;
        }
        return resultado;
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

    //SUELDOS
    public double calculoSueldo(Profesional prof){
        return prof.getSueldo(getValorTurno());
    }
    /**
     * Retorna la sumatoria de todos los sueldos de los profesionales
     * @return sueldos
     */
    public double sumaSueldos(){
        double sueldos = 0;
        for (int i = 0; i < profesionales.length; i++) {
            sueldos += calculoSueldo(profesionales[i]);
        }
        return sueldos;
    }
    /**
     * Muestra listado de todos los profesionales con su sueldo
     * @return
     */
    public String mostrarSueldosArreglo(){
        String retorno = "";
        for (int index = 0; index < profesionales.length; index++){
            retorno += (index+1)+". "+profesionales[index].getApellido()+", "+profesionales[index].getNombre()+" - Matricula: "+profesionales[index].getMatricula()+" | Sueldo: $ "+calculoSueldo(profesionales[index])+"\n\n";
        }
        return retorno;
    }    
    /**
     * Para pagar el sueldo, resetea la cantidad de turnos trabajados del profesional a 0.
     * @param prof
     */
    public void pagarSueldo(Profesional prof){
        prof.setTurnosTrabajados(0);
    }

    public Profesional getProfesionalIndex(int index){
        if(profesionales.length > 0){
            return profesionales[index-1];
        }else{
            return null;
        }
    }

    public double getGastosTotales(){
        double gastosTotales = sumaSueldos()+getCostoFijo();
        return gastosTotales;
    }

    public void generarComprobante(Profesional prof){
        //Obtiene la fecha, hora y minutos actual
        LocalDate fecha = LocalDate.now();
        int hora = LocalTime.now().getHour();
        int minuto = LocalTime.now().getMinute();
        //El número de comprobante se genera acorde al correspondiente al indice del arreglo comprobantes
        int numero = comprobantes.length+1;
        //Genera el texto del comprobante
        String datos = "Delta Kinesiología"+
                    "\n\nComprobante de pago"+
                    "\nFecha: "+fecha+
                    " | Hora: "+hora+":"+minuto+
                    "\n\nSe pagó un total de: $"+calculoSueldo(prof)+
                    "\nSe pagó a: "+prof.getApellido()+", "+prof.getNombre()+
                    "\n\nNúmero de comprobante de pago: "+numero;
        //Crea el objeto Comprobante
        Comprobante comp = new Comprobante(fecha, datos);
        //Lo agrega al arreglo comprobantes
        comprobantes = Arreglo.agregarComprobante(comprobantes, comp);
    }

    /**
     * Muestra la cantidad de comprobantes del arreglo con su fecha de pago
     * @return
     */
    public String mostrarComprobantesArreglo(){
        String retorno = "";
        for (int index = 0; index < comprobantes.length; index++){
            retorno += (index+1)+". Fecha de pago: "+comprobantes[index].getFecha()+"\n\n";
        }
        return retorno;
    }   

    /**
     * Retorna la cantidad de comprobantes totales
     * @return
     */
    public int getCantComprobantes(){
        return comprobantes.length;
    }

    /**
     * Muestra el objeto comprobante correspondiente al index que le pasemos del optionSelect
     * @param index
     * @return
     */
    public Comprobante mostrarComprobante(int index){
        return comprobantes[index-1];
    }
}



