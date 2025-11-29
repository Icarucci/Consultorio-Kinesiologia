package objetos;

import javax.swing.JOptionPane;

public class Horario {
    //ATRIBUTOS
    //PUBLICO
    public static final int MAX_TURNOS_POR_HORA = 5;
    //PRIVADOS
    private Hora hora; //Es el enum Hora
    private Turno[] turnos;

    //CONSTRUCTOR
    public Horario(Hora hora) {
        this.hora = hora;
        this.turnos = new Turno[MAX_TURNOS_POR_HORA];
    }

    //Getters & Setters
    /**
     * Muestra la hora
     * @return
     */
    public int getHora() {
        return hora;
    }

    /**
     * Muestra los turnos de ese horario
     * @return
     */
    public Turno[] getTurnos() {
        return turnos;
    }

    //METODOS
    public boolean agregarTurno(Turno nuevoTurno){
        //Si recibe un null, no se agrega el turno
        if(nuevoTurno == null){
            JOptionPane.showMessageDialog(null, "ERROR: Se recibio un turno vacio.");
            return false;
        }
        //Recorremos el arreglo turnos[] hasta encontrar un lugar vacio, si
        for (int index = 0; index < turnos.length; index++) {
            if(turnos[i] == null){
                turnos[i] = nuevoTurno;
                JOptionPane.showMessageDialog(null, "Turno agregado correctamente.");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "ERROR: Horario lleno.");
        return false;
    }
    


}
