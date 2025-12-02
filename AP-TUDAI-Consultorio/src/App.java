import objetos.Especialidad;
import objetos.Especialista;
import objetos.Institucion;
import objetos.Paciente;
import objetos.Profesional;
import objetos.Puesto;
import view.Visualizacion;

public class App {
    public static void main(String[] args) throws Exception {

        //Inicializamos la institucion
        Institucion delta = new Institucion("\u0394 Delta", 0);
        Visualizacion view = new Visualizacion(delta);
        //Valores Harcodeados
        /*Pacientes */
        Paciente pac1 = new Paciente("29555208", "Omar", "Roselli", "Vivorata 2968", 4379450, "OSDE", 10, false); 
        Paciente pac2 = new Paciente("35250250", "Ignacio", "Carucci", "Planes 1234", 1234567, "SMATA", 10, false);
        delta.agregarPaciente(pac1);
        delta.agregarPaciente(pac2);
        /*Profesionales */
        Especialista prof1 = new Especialista("31625325", "Malena", "Griffiths", "Colon 30",58744291 ,10363,12500,Especialidad.OSTEOPATIA);
        Profesional prof2 = new Profesional("1234567","John","Doe","Fake Street 1234",1234567,101010,10500);
        delta.agregarProfesional(prof1);
        delta.agregarProfesional(prof2);
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
        /*EJECUCION DEL MENU */
        view.menuPrincipal();
    }
    
}
