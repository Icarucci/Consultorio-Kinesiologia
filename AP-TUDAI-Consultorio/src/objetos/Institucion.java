package objetos;

import java.time.LocalDate;
import java.time.LocalTime;


public class Institucion {
    /*Atributos privados */
    private String nombre;
    private Profesional[] profesionales = new Profesional[0];
    private Paciente[] pacientes = new Paciente[0];
    private Turno[] turnos = new Turno[0];
    private final Puesto[] puestos = new Puesto[5];
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
        Paciente[] auxiliar = new Paciente[pacientes.length+1];
        Persona [] resultado = agregarPersona(pacientes, pte);
        for(int i=0;i<auxiliar.length;i++){
            auxiliar[i] = (Paciente)resultado[i];
        }
        pacientes = auxiliar;
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
                if (profesionales[i].getId().equals(prof.getId())||profesionales[i].getMatricula() == prof.getMatricula()) {
                    return false;
                }
            }
        }
        //Si no hay otro profesional con mismo dni o matricula, lo agrega
        Profesional[] auxiliar = new Profesional[profesionales.length+1];
        Persona [] resultado = agregarPersona(profesionales, prof);
        for(int i=0;i<auxiliar.length;i++){
            auxiliar[i] = (Profesional)resultado[i];
        }
        profesionales = auxiliar;
        return true;
    }
    /**
     * METODO QUE RECIBE UN ARREGLO DE PERSONAS Y AGREGA UN LUGAR Y UNA NUEVA PERSONA AL ARREGLO
     * @param personas
     * @param persona
     * @return arreglo de personas
     */
    public Persona[] agregarPersona(Persona[]personas,Persona persona){
        Persona[]resultado = new Persona[personas.length+1];
        for(int i=0;i<personas.length;i++){
            resultado[i]=personas[i];
        }
        resultado[personas.length] = persona;
        return resultado;
    }
    //METODOS DE ORDENAMIENTO DE PACIENTES
    public void ordenaPacienteId(){
        ordenaPersonasID(pacientes);
    }
    public void ordenaPacienteApellido(){
        ordenaPersonasApellido(pacientes);
    }
    public void ordenaPacienteSesiones(){
        for(int i=0;i<pacientes.length-1;i++){
            for(int j=0;j<pacientes.length-1;j++){
                if(pacientes[j].getSesionesRemanentes()>pacientes[j+1].getSesionesRemanentes()){
                    Paciente aux = pacientes[j];
                    pacientes[j] = pacientes[j+1];
                    pacientes[j+1] = aux;
                }
            }
        }
    }
    //METODOS DE ORDENAMIENTO DE PROFESIONALES
    public void ordenaProfesionalesID(){
        ordenaPersonasID(profesionales);
    }
    public void ordenaProfesionalesApellido(){
        ordenaPersonasApellido(profesionales);
    }
    public void ordenaProfesionalesMatricula(){
        for(int i=0;i<profesionales.length-1;i++){
            for(int j=0;j<profesionales.length-1;j++){
                if(profesionales[j].getMatricula()>profesionales[j+1].getMatricula()){
                    Profesional aux = profesionales[j];
                    profesionales[j] = profesionales[j+1];
                    profesionales[j+1] = aux;
                }
            }
        }
    }
    /* METODO QUE RECIBE UN ARREGLO DE PERSONAS Y LO DEVUELVE ORDENADO POR SU ID.
    * burbujeo
    * @param arreglo
    * @return arreglo ordenado por ID
    */
    public Persona[] ordenaPersonasID(Persona[] arreglo){
        for(int i=0; i < arreglo.length - 1; i++){
            for(int j=0; j < arreglo.length - 1; j++){
                if (arreglo[j].getId().compareTo(arreglo[j+1].getId()) > 0) {
                    Persona aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;
                }
            }
        }
        return arreglo;
    }
    
    /* METODO QUE RECIBE UN ARREGLO DE PERSONAS Y LO DEVUELVE ORDENADO POR APELLIDO.
    * burbujeo
    * @param arreglo
    * @return arreglo ordenado por apellido
    */
    public Persona[] ordenaPersonasApellido(Persona[]arreglo){
        for(int i=0;i<arreglo.length-1;i++){
            for(int j=0;j<arreglo.length-1;j++){
                if((arreglo[j].getApellido().compareTo(arreglo[j+1].getApellido()))>0){
                    Persona aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;
                }
            }
        }
        return arreglo;
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
        Persona[]ordenado = ordenaPersonasID(pacientes);
        //3. Aplicamos búsqueda binaria
        Persona encontrado = buscaPersonaId(ordenado, dni);
        return (Paciente)encontrado;
    }
    /* METODO DE BUSQUEDA BINARIA DE PERSONAS A PARTIR DE SU ID.
    * @param arreglo de Persona ordenado por ID
    * @param id
    * @return objeto Persona o Null.
    */
    public Persona buscaPersonaId(Persona[]arreglo,String id){
        int inicio = 0;
        int fin = arreglo.length-1;
        while(inicio<=fin){
            int medio = inicio+(fin-inicio)/2;
            if(arreglo[medio].getId().compareTo(id)==0){
                return arreglo[medio];
            }
            if(arreglo[medio].getId().compareTo(id)<0){
                inicio=medio+1;
            }else{
                fin=medio-1;
            }
        }
        return null;
    }
    /**
     * Busca paciente por apellido
     * @param apellido
     * @return Paciente
     */
    public Paciente[] buscarPacienteApellido(String apellido){
        Persona[] encontrado = buscarPersonaApellido(pacientes, apellido);
        Paciente[] resultado = new Paciente[encontrado.length];
        for(int i=0;i<resultado.length;i++){
            resultado[i] = (Paciente)encontrado[i];
        }
        return resultado;
    }

    /**
     * METODO DE BUSQUEDA POR APELLIDO (LINEAL)
     * @param arreglo
     * @param apellido
     * @return objeto Persona o Null
     */
    public Persona[] buscarPersonaApellido(Persona[]arreglo,String apellido){
        Persona[] retorno = new Persona[0];
        for(int i=0;i<arreglo.length;i++){
            if(arreglo[i].getApellido().equals(apellido)){
                retorno = agregarPersona(retorno, arreglo[i]);
            }
        }
        return retorno;
    }
    /**
     * Busca profesional por dni
     * @param dni
     * @return Profesional
     */
    public Profesional buscarProfesionalPorDni(String dni) {
        Persona[]ordenado = ordenaPersonasID(profesionales);
        //3. Aplicamos búsqueda binaria
        Persona encontrado = buscaPersonaId(ordenado, dni);
        return (Profesional)encontrado;
    }
    /**
     * Busca profesional por apellido
     * @param apellido
     * @return Profesional
     */
    public Profesional[] buscarProfesionalApellido(String apellido){
        Persona[] encontrado = buscarPersonaApellido(profesionales, apellido);
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
    public boolean eliminarPacienteConIndex(String id){
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
            pacientes = eliminar(pacientes,i);
            resultado=true;
        }
        return resultado;
    }
    public Paciente[] eliminar(Paciente[]personas,int index){
        Paciente[] resultado = new Paciente[personas.length-1];
        int j=0;
        for(int i=0; i<personas.length;i++){
            if(i!=index){
                resultado[j]=personas[i];
                j++;
            }
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
    /**
     * Muestra los turnos registrados
     * @return String
     */
    public String showTurnos(){
        if(turnos.length==0){
            String res="Sin turnos al momento";
            return res;
        }
        String ausentes="\nAusentes:";
        String presentes="\nPresentes:";
        String futuros="\nFuturos:";
        for(int i=0;i<turnos.length;i++){
            if(turnos[i].getAsistencia()>0){
                presentes+="\n"+turnos[i].getTurnoId()+" - "+turnos[i];
            }else if(turnos[i].getAsistencia()<0){
                ausentes+="\n"+turnos[i].getTurnoId()+" - "+turnos[i];
            }else{
                futuros+="\n"+turnos[i].getTurnoId()+" - "+turnos[i];
            }
        }
        return ausentes+"\n---------------------------------"+presentes+"\n---------------------------------"+futuros;
    }
    public String showTurnosDisponibles(){
         if(turnos.length==0){
            String res="Sin turnos al momento";
            return res;
        }
        String res ="";
        for(int i=0;i<turnos.length;i++){
            if(turnos[i].getAsistencia()==0){
                res += "\n"+turnos[i].getTurnoId()+" - "+turnos[i];
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
    public int cantidadTurnosVigentes(){
        int contador = 0;
        for(int i=0;i<turnos.length;i++){
            if(turnos[i].getAsistencia()==0){
                contador++;
            }
        }
        return contador;
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
        String datos = getNombre()+
                    "\n\nComprobante de pago"+
                    "\nFecha: "+fecha+
                    " | Hora: "+hora+":"+minuto+
                    "\n\nSe pagó un total de: $"+calculoSueldo(prof)+
                    "\nSe pagó a: "+prof.getApellido()+", "+prof.getNombre()+
                    "\n\nNúmero de comprobante de pago: "+numero;
        //Crea el objeto Comprobante
        Comprobante comp = new Comprobante(fecha, datos);
        //Lo agrega al arreglo comprobantes
        agregarComprobante(comp);
    }
    public void agregarComprobante(Comprobante comprobante){
        Comprobante[] auxiliar = new Comprobante[comprobantes.length+1];
        for(int i=0;i<comprobantes.length;i++){
            auxiliar[i]=comprobantes[i];
        }
        auxiliar[comprobantes.length] = comprobante;
        comprobantes = auxiliar;
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
    /**
    * METODO QUE RECIBE UN ARREGLO DE PERSONAS Y RETORNA LA INFORMACION DE TODOS LOS ELEMENTOS.
    * @param arreglo
    * @return String 
    */
    public String showArregloPersona(Persona[]arreglo){
        String resultado="";
        for(int i=0;i<arreglo.length;i++){
            resultado+=arreglo[i].toString()+"\n";
        }
        return resultado;
    }
    /**
    * METODO QUE RECIBE UN ARREGLO DE TURNOS Y LE AGREGA UN TURNO EN UNA NUEVA POSICION.
    * @param arreglo
    * @return arreglo de Turno con una posicion mas
    */
    public void agregarTurno(Turno turno){
        Turno [] nuevo = new Turno[turnos.length+1];
        for(int i=0;i<turnos.length;i++){
            nuevo[i]=turnos[i];
        }
        nuevo[nuevo.length-1] = turno;
        turno.getPaciente().setSesionesRemanentes(turno.getPaciente().getSesionesRemanentes()-1);
        turnos = nuevo;
    }
    /**
     * ELIMINA UN TURNO SEGUN SU ID
     * @param id
     */
    public void eliminarTurnoId(int id){
        Turno[] nuevo = new Turno[turnos.length-1];
        int count =0;
        for (int i=0;i<turnos.length;i++){
            if(turnos[i].getTurnoId()!= id){
                nuevo[count] = turnos[i];
                count++;
            }
        }
        turnos = nuevo;
    }
}



