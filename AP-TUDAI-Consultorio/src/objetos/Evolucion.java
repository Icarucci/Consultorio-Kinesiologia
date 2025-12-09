package objetos;

import java.time.LocalDate;

public class Evolucion {
    //ATRIBUTOS
    private LocalDate fecha;
    private String evolucion;
    private Profesional profesional;

    //CONSTRUCTOR
    public Evolucion(LocalDate fecha, String evolucion, Profesional profesional){
        this.fecha = fecha;
        this.evolucion = evolucion;
        this.profesional = profesional;
    }

    //GETTERS & SETTERS
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    

}
