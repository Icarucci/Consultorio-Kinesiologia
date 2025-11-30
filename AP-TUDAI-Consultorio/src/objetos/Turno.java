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

    //CONSTRUCTOR
    public Turno(Puesto puestoAsignado, Paciente paciente, Profesional profesional, LocalDate fecha,Hora hora) {
        this.puestoAsignado = puestoAsignado;
        this.paciente = paciente;
        this.profesional = profesional;
        this.fecha = fecha;
        this.hora = hora;
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

    
}
