package objetos;

public class Especialista extends Profesional{
    /*Atributos */
    private Especialidad especialidad;
    /*Constructor */
    public Especialista(String id, String nombre, String apellido, String direccion, int telefono, int matricula, Especialidad especialidad) {
        //ID NOMBRE APELLIDO DIRECCION TELEFONO MATRICULA Y SUELDO vienen del padre Profesional
        super(id, nombre, apellido, direccion, telefono, matricula);
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
        public double getSueldo(double valorTurno, double coef){
            //Redefine - Cobra un 25% mas. por ser especialista
            return valorTurno*getTurnosTrabajados()*coef;
            
    }
    @Override
    public boolean esEspecialista(){
        return true;
    }
    @Override
    public String vistaReducida(){
        return super.vistaReducida()+" Especialidad: "+especialidad;
    }

    @Override
    public String toString(){
        return super.toString()+ "\nEspecialidad: "+especialidad;
    }
}
