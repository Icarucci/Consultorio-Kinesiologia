package objetos;

//CLASE PROFESIONAL QUE ES HIJA DE CLASE PERSONA

import java.time.LocalDate;

public class Profesional extends Persona {
    //ATRIBUTOS PRIVADOS
    private int matricula;
    private double sueldo;
    private int turnosTrabajados;

    //CONSTRUCTOR
    public Profesional(String id, String nombre, String apellido, String direccion, int telefono, int matricula, double sueldo) {
        //ID, NOMBRE, APELLIDO, DIRECCION, TELEFONO se heredan del padre PERSONA
        super(id, nombre, apellido, direccion, telefono);
        //Son particulares de Profesional
        this.matricula = matricula;
        this.sueldo = sueldo;
        turnosTrabajados = 0;
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
    /**
     * Retorna la cantidad de turnos trabajados
     * @return entero
     */
    public int getTurnosTrabajados() {
        return turnosTrabajados;
    }
    /**
     * Setea la cantidad de turnos trabajados
     * @param turnosTrabajados
     */
    public void setTurnosTrabajados(int turnosTrabajados) {
        this.turnosTrabajados = turnosTrabajados;
    }

    //SUELDO
    /**
     * Trae el Sueldo del Profesional
     * @return double 
     */
    public double getSueldo(double valorTurno) {
        //HARDCODEAMOS UN VALOR ESTANDAR
        double sueldo = valorTurno*getTurnosTrabajados();
        return sueldo;
    }
    /**
     * Modifica el Sueldo del Profesional
     * @param sueldo
     */
    public void setSueldo(double saldarSueldo) {
        this.sueldo = saldarSueldo;
    }
    /**
     * Validacion que el profesional no tiene un turno a la misma hora y el mismo dia
     * @param fecha
     * @param hora
     * @return boolean
     */
    public boolean validacion(LocalDate fecha, Hora hora){
        if(turnos.length == 0){
            return true;
        }
        for(int i=0; i<turnos.length;i++){
            Turno tt = turnos[i];
            if(tt.getFecha().equals(fecha) && tt.getHora() == hora){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Incrementa la cantidad de turnos trabajados
     *
     */
    public void trabajarTurno(){
        setTurnosTrabajados(getTurnosTrabajados()+1);
    }
    /**
     * Retorna el sueldo total = Sueldo Basico + (valor del turno * cantidad de turnos)
     * @param valorTurno
     * @return double
     */
    public double sueldoTotal(double valorTurno){
        return sueldo+(valorTurno*getTurnosTrabajados());
    }

    public double cobrar(){
        double sueldo = sueldoTotal(matricula);
        setTurnosTrabajados(0);
        return sueldo;
    }
    //Metodo Mostrar Profesional
    @Override
    public String toString() {
        return  "PROFESIONAL\n" +super.toString()+
                "\nMatrícula: " + matricula +
                "\nSueldo: $" + sueldo;
    }
}
