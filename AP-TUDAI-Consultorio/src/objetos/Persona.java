package objetos;

import utils.Arreglo;

public class Persona {
    //ATRIBUTOS PRIVADOS
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    protected Turno[] turnos;

    //CONSTRUCTOR PERSONA (PADRE)
    public Persona(String id, String nombre, String apellido, String direccion, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        turnos = new Turno[0];
    }
    //Getters & Setters
    //ID
    /**
     * Trae la ID de la persona
     * @return
     */
    public String getId() {
        return id;
    }
    /**
     * Modifica la ID de la persona
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    //NOMBRE
    /**
     * Trae el nombre de la persona
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre de la persona
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //APELLIDO
    /**
     * Trae el apellido de la persona
     * @return
     */
    public String getApellido() {
        return apellido;
    }
    /**
     * Modifica el apellido de la persona
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    //DIRECCION
    /**
     * Trae la direccion de la persona
     * @return
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * Modifica la direccion de la persona
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    //TELEFONO
    /**
     * Muestra el telefono de la persona
     * @return
     */
    public int getTelefono() {
        return telefono;
    }
    /**
     * Modifica el telefono de la persona
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
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

    public String toString(){
        return  "DNI: " + getId() +
                "\nApellido: " + getApellido()+
                "\nNombre: " + getNombre()+
                "\nDirección: " + getDireccion()+
                "\nTeléfono: " + getTelefono();
    }
}
