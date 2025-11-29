import java.time.LocalDate;

import objetos.Calendario;
import objetos.Institucion;
import objetos.Paciente;
import objetos.Profesional;
import objetos.Puesto;
import objetos.Turno;
import view.Visualizacion;

public class App {
    public static void main(String[] args) throws Exception {

        // Crear arreglos vacíos
        Profesional[] profesionales = new Profesional[0];
        Paciente[] pacientes = new Paciente[0];
        Turno[] turnos = new Turno[200];
        Puesto[] puestos = new Puesto[5];

        // Calendario del día actual
        Calendario calendario = new Calendario(LocalDate.now());

        //Inicializamos la institucion
        Institucion apnea = new Institucion("Apnea", profesionales, pacientes, turnos, puestos, 0, 0, calendario);
        Visualizacion view = new Visualizacion(apnea);

        view.menuPrincipal();
    }
    
}
