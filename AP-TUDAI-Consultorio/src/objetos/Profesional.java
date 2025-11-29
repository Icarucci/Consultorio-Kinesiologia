package objetos;

//CLASE PROFESIONAL QUE ES HIJA DE CLASE PERSONA
public class Profesional extends Persona {
    //ATRIBUTOS PRIVADOS
    private int matricula;
    private double sueldo;
    private Turno[] turnosAtencion;

    //CONSTRUCTOR
    public Profesional(String id, String nombre, String apellido, String direccion, int telefono, int matricula, double sueldo, Turno[] turnosAtencion) {
        //ID, NOMBRE, APELLIDO, DIRECCION, TELEFONO se heredan del padre PERSONA
        super(id, nombre, apellido, direccion, telefono);
        //Son particulares de Profesional
        this.matricula = matricula;
        this.sueldo = sueldo;
        this.turnosAtencion = turnosAtencion;
    }

    //Metodo Mostrar Profesional
    @Override
    public String toString() {
        return  "PROFESIONAL\n" +
                "-----------------------\n" +
                "DNI: " + getId() + "\n" +
                "Apellido: " + getApellido() + "\n" +
                "Nombre: " + getNombre() + "\n" +
                "Dirección: " + getDireccion() + "\n" +
                "Teléfono: " + getTelefono() + "\n" +
                "Matrícula: " + matricula + "\n" +
                "Sueldo: $" + sueldo;
    }

    //Getters & Setters

    //MATRICULA
    /**
     * Trae la Matricula del Profesional
     * @return
     */
    public int getMatricula() {
        return matricula;
    }
    /**
     * Modifica la Matricula del Profesional
     * @param matricula
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    //SUELDO
    /**
     * Trae ek Sueldo del Profesional
     * @return
     */
    public double getSueldo() {
        return sueldo;
    }
    /**
     * Modifica el Sueldo del Profesional
     * @param sueldo
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Trae el arreglo de los Turnos del Profesional
     * @return
     */
    public Turno[] getTurnosAtencion() {
        return turnosAtencion;
    }

    /**
     * Modifica el arreglo de los Turnos del Profesional
     * @param turnosAtencion
     */
    public void setTurnosAtencion(Turno[] turnosAtencion) {
        this.turnosAtencion = turnosAtencion;
    }

    



}
