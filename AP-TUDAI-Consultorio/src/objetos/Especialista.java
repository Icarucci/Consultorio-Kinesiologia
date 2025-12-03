package objetos;

public class Especialista extends Profesional{
    /*Atributos */
    private Especialidad especialidad;
    /*Constructor */
    public Especialista(String id, String nombre, String apellido, String direccion, int telefono, int matricula,double sueldo, Especialidad especialidad) {
        //ID NOMBRE APELLIDO DIRECCION TELEFONO MATRICULA Y SUELDO vienen del padre Profesional
        super(id, nombre, apellido, direccion, telefono, matricula, sueldo);
        this.especialidad = especialidad;
    }
    /*Getters & Setters */
    /**
     * Retorna la especialidad
     * @return Especialidad
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }
    /**
     * Setea la especialidad 
     * @param especialidad
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    @Override
    public double sueldoTotal(double valor){
        //Redefine - Cobra un 25% mas. por se especialista
        return super.sueldoTotal(valor)*1.25;
    }

    @Override
    public String toString(){
        return super.toString()+ "\nEspecialidad: "+especialidad;
    }
}
