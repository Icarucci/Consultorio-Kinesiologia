import java.time.LocalDate;
import objetos.Especialidad;
import objetos.Especialista;
import objetos.Hora;
import objetos.Institucion;
import objetos.Paciente;
import objetos.Profesional;
import objetos.Puesto;
import objetos.Turno;
import view.Visualizacion;

public class App {
    public static void main(String[] args) throws Exception {

   
        //Inicializamos la institucion
        Institucion delta = new Institucion("\u0394 Delta", 1750000);
        Visualizacion view = new Visualizacion(delta);
        //Valores Harcodeados
        /*Pacientes */
        Paciente pac1 = new Paciente("29555208", "Omar", "Roselli", "Vivorata 2968", 4379450, "OSDE", 3, false); 
        Paciente pac2 = new Paciente("36397301", "Ignacio", "Carucci", "Planes 916", 1234567, "OSMATA", 10, false);
        Paciente pac3 = new Paciente("25452831","Maria Soledad","Roselli","Scavini 2345",643169,"Medife",25,false);
        Paciente pac4 = new Paciente("05795903","Maria de los Angeles","Inclan","San Lorenzo 262",15020650,"IOMA",10,false);
        delta.agregarPaciente(pac1);
        delta.agregarPaciente(pac2);
        delta.agregarPaciente(pac3);
        delta.agregarPaciente(pac4);
        /*Profesionales */
        Especialista prof1 = new Especialista("31625325", "Malena", "Griffiths", "Colon 30",58744291 ,10363,Especialidad.OSTEOPATIA);
        Profesional prof2 = new Profesional("1234567","John","Doe","Fake Street 1234",1234567,101010);
        Especialista prof3 = new Especialista("38601802", "Julieta", "Della Penna", "Planes 916", 33959603, 10760, Especialidad.ATM);
        delta.agregarProfesional(prof1);
        delta.agregarProfesional(prof2);
        delta.agregarProfesional(prof3);
        /*Puestos laborales*/
        Puesto p1 = new Puesto("Camilla 1");
        Puesto p2 = new Puesto("Camilla 2");
        Puesto p3 = new Puesto("Bicicleta fija");
        Puesto p4 = new Puesto("Gimnasio 1");
        Puesto p5 = new Puesto("Gimnasio 2");
        delta.addPuesto(p1);
        delta.addPuesto(p2);
        delta.addPuesto(p3);
        delta.addPuesto(p4);
        delta.addPuesto(p5);
        /*TURNOS HARDCODEADOS */
        Turno t1 = new Turno(p5, pac4, prof3, LocalDate.of(2026,1,10), Hora.H10);
        Turno t2 = new Turno(p3, pac3, prof2, LocalDate.of(2026,1,10), Hora.H10);
        Turno t3 = new Turno(p1, pac2, prof1, LocalDate.of(2026,1,9), Hora.H10);
        Turno t4 = new Turno(p2, pac1, prof2, LocalDate.of(2026,1,9), Hora.H10);
        Turno t5 = new Turno(p2, pac2, prof1, LocalDate.of(2026,1,5), Hora.H9);
        delta.agregarTurno(t1);
        delta.agregarTurno(t2);
        delta.agregarTurno(t3);
        delta.agregarTurno(t4);
        delta.agregarTurno(t5);
        
        /*EJECUCION DEL MENU */
        view.menuPrincipal();
    }
    
}
