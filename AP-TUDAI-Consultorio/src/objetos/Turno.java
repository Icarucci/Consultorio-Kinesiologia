package objetos;

import java.time.LocalDate;

//TURNOS
public class Turno {
    //ATRIBUTOS PRIVADOS
    private Puesto puestoAsignado;
    private Paciente paciente;
    private Profesional profesional;
    private LocalDate fecha;
    private Hora hora;
    private boolean asistencia;
    private int turnoId;
    private static int contador=1;

    //CONSTRUCTOR
    public Turno(Puesto puestoAsignado, Paciente paciente, Profesional profesional, LocalDate fecha,Hora hora) {
        this.puestoAsignado = puestoAsignado;
        this.paciente = paciente;
        this.profesional = profesional;
        this.fecha = fecha;
        this.hora = hora;
        this.asistencia = false;
        this.turnoId = contador;
        this.contador++;
    }
    //Getters & Setters

    //PUESTO
    /**
     * Trae el puesto que corresponde a ese turno
     * @return
     */
    public Puesto getPuestoAsignado() {
        return puestoAsignado;
    }
    /**
     * Modifica el puesto del turno
     * @param puestoAsignado
     */
    public void setPuestoAsignado(Puesto puestoAsignado) {
        this.puestoAsignado = puestoAsignado;
    }

    //PACIENTE
    /**
     * Muestra al paciente correspondiente a ese turno
     * @return
     */
    public Paciente getPaciente() {
        return paciente;
    }

    //PROFESIONAL
    /**
     * Muestra al profesional de ese turno
     * @return
     */
    public Profesional getProfesional() {
        return profesional;
    }

    //FECHA
    /**
     * Muestra la fecha del turno
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }
    

    public int getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(int turnoId) {
        this.turnoId = turnoId;
    }
    
    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public void pacienteAsistio(){
        paciente.setSesionesRemanentes(paciente.getSesionesRemanentes()-1);
    }

    @Override
    public String toString(){
        return "Turno: "+fecha.toString()+" Hora:"+hora+"\nPaciente:"+paciente.getApellido()+" Profesional: "+profesional.getApellido()+" Puesto: "+puestoAsignado.getNombre();
    }
}
