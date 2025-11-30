package objetos;

//CLASE PROFESIONAL QUE ES HIJA DE CLASE PERSONA

import java.time.LocalDate;

public class Profesional extends Persona {
    //ATRIBUTOS PRIVADOS
    private int matricula;
    private double sueldo;

    //CONSTRUCTOR
    public Profesional(String id, String nombre, String apellido, String direccion, int telefono, int matricula, double sueldo) {
        //ID, NOMBRE, APELLIDO, DIRECCION, TELEFONO se heredan del padre PERSONA
        super(id, nombre, apellido, direccion, telefono);
        //Son particulares de Profesional
        this.matricula = matricula;
        this.sueldo = sueldo;
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
    public boolean validacion(LocalDate fecha, Hora hora){
        /*   TODO   */
        return true;
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



}
