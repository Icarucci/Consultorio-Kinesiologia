import java.time.LocalDate;
import objetos.Calendario;
import objetos.Institucion;
import objetos.Paciente;
import objetos.Profesional;
import view.Visualizacion;

public class App {
    public static void main(String[] args) throws Exception {
        // Calendario del día actual
        Calendario calendario = new Calendario(LocalDate.now());

        //Inicializamos la institucion
        Institucion delta = new Institucion("\u0394 Delta", 0, 0, calendario);
        Visualizacion view = new Visualizacion(delta);

        //Valores Harcodeados
        Paciente pac1 = new Paciente("29555208", "Omar", "Roselli", "Vivorata 2968", 4379450, "OSDE", 10, false); 
        Paciente pac2 = new Paciente("35250250", "Ignacio", "Carucci", "FakeStreet 1234", 1234567, "SMATA", 10, false);
        delta.agregarPaciente(pac1);
        delta.agregarPaciente(pac2);

        Profesional prof1 = new Profesional("31625325", "Malena", "Griffiths", "Colon 30",58744291 ,10058,5500);
        delta.agregarProfesional(prof1);
        view.menuPrincipal();
    }
    
}
