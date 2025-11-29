package objetos;

import java.time.LocalDate;

public class Calendario {
    //ATRIBUTOS PRIVADOS
    private LocalDate fecha;
    private Horario[] horarios;

    //CONSTRUCTOR
    public Calendario(LocalDate fecha){
        this.fecha = fecha;
        this.horarios = new Horario[Hora.values().length];

        //Recorre el enum Hora y por cada valor(horario) crea un objeto horario y lo guarda en el arreglo horarios
        for (int i = 0; i < Hora.values().length; i++) {
            horarios[i] = new Horario(Hora.values()[i]);
        }
    }

    /**
     * Muestra la fecha del dia
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Muestra los horarios del dia
     * @return
     */
    public Horario[] getHorarios() {
        return horarios;
    }

    
}
