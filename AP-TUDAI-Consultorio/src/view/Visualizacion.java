package view;

import javax.swing.JOptionPane;
import objetos.Horario;
import objetos.Institucion;
import objetos.Paciente;
import objetos.Profesional;
import utils.IO;

public class Visualizacion {  
    private Institucion inst;
    private int opcionMenuPrincipal;
    private int opcionMenuPacientes;
    private int opcionMenuProfesionales;
    private int opcionTurnosLibres;


    public Visualizacion(Institucion inst){
        this.inst = inst;
    }
   
    public void menuPrincipal(){
        boolean finalizar = false;
        do {
             opcionMenuPrincipal = IO.opcionSelect("Bienvenido a "+inst.getNombre(), "1.Pacientes\n2.Profesionales\n3.Turnos\n0.Salir", 3);
            switch (opcionMenuPrincipal) {
                case 1:
                    opcionMenuPacientes();
                    break;
                case 2:
                    opcionMenuProfesionales();
                    break;
                case 3:
                    opcionMenuTurnos();
                    break;
                case 0:
                    finalizar = true;
                    break;
                default:
                    break;
            }
        } while (!finalizar);
    }

    public void opcionMenuPacientes(){
        boolean atras = false;
        do {
            opcionMenuPacientes = IO.opcionSelect("Pacientes", "1.Nuevo paciente\n2.Buscar paciente\n3.Listar Pacientes\n4.Editar Paciente\n0. Atras", 5);
            switch (opcionMenuPacientes) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    String id = IO.inputString("Paciente", "Ingrese el DNI");
                    String nombre = IO.inputString("Paciente", "Ingrese nombre");
                    String apellido = IO.inputString("Paciente", "Ingrese apellido");
                    String direccion = IO.inputString("Paciente", "Ingrese direccion");
                    int telefono = IO.inputIntegerPositive("Paciente", "Ingrese el telefono");
                    int sesionesTotales = IO.inputIntegerPositive("Paciente", "Ingrese cantidad de sesiones");
                    String obraSocial = IO.inputString("Paciente", "Ingrese Obra Social");
                    boolean cronico = IO.inputBoolean("Paciente", "Es cronico (true/false)");
                    //Crea al paciente
                    Paciente paciente = new Paciente(id, nombre, apellido, direccion, telefono, obraSocial, sesionesTotales, cronico);
                    //Lo agrega al arreglo
                    boolean agregado = inst.agregarPaciente(paciente);
                    //Checkeo si se pudo agregar
                    if (agregado) {
                        JOptionPane.showMessageDialog(null,"Paciente cargado correctamente.\n\n"+paciente.toString(),"Carga de paciente",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"No se pudo cargar el paciente.\nLa lista está llena o datos duplicados (DNI).","Error",JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 2:
                boolean atras2 = false;
                    do {
                        int opcion = IO.opcionSelect("Buscar paciente","1. Buscar por DNI\n2. Buscar por apellido\n0. Atras",5);
                        switch (opcion) {
                            case 0:
                                atras2 = true;
                                break;
                            case 1:
                                String dni = IO.inputString("Busqueda Paciente", "Ingrese el dni del paciente:");
                                Paciente respuesta = inst.buscarPacientePorDni(dni);
                                if(respuesta != null){
                                    /*Mostrar encontrado */
                                    JOptionPane.showMessageDialog(null, respuesta.toString());
                                }else{
                                    /*No se encontro paciente*/
                                    JOptionPane.showMessageDialog(null, "Paciente no encontrado","Error",0);
                                }
                                break;
                            case 2:
                                String apellidoBuscado = IO.inputString("Busqueda Paciente", "Ingrese el apellido del paciente");
                                Paciente res = inst.buscarPacienteApellido(apellidoBuscado);
                                 if(res != null){
                                    /*Mostrar encontrado */
                                    JOptionPane.showMessageDialog(null, res.toString());
                                }else{
                                    /*No se encontro paciente*/
                                    JOptionPane.showMessageDialog(null, "Paciente no encontrado","Error",0);
                                }
                                break;
                            default:
                                break;
                        }
                    } while (!atras2);
                    break;
                case 3: 
                    /*Listar pacientes. - Se lista la informacion escencial. Apellido, Nombre - Dni , Sesiones realizadas/Sesiones totales. */
                    String res = inst.mostrarPacientes();
                    JOptionPane.showMessageDialog(null,res,"Listado Pacientes",1);
                    break;
                case 4:
                    /*Edicion de un paciente */
                    String dni = IO.inputString("Busqueda Paciente", "Ingrese el dni del paciente:");
                    Paciente respuesta = inst.buscarPacientePorDni(dni);
                    if(respuesta != null){
                        /*Mostrar encontrado */
                        JOptionPane.showMessageDialog(null, respuesta.toString());
                    }else{
                        /*No se encontro paciente*/
                        JOptionPane.showMessageDialog(null, "Paciente no encontrado","Error",0);
                    }
                    break;
                default:
                    break;
            }
        } while (!atras);
    }

    public void opcionMenuProfesionales(){
        boolean atras = false;
        do {
            opcionMenuProfesionales = IO.opcionSelect("Profesionales", "1.Agregar nuevo Profesional\n2.Listar Profesionales\n0. Atras", 5);
            switch (opcionMenuProfesionales) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    String id = IO.inputString("Profesional", "Ingrese el DNI");
                    String nombre =  IO.inputString("Profesional", "Ingrese nombre");
                    String apellido = IO.inputString("Profesional", "Ingrese apellido");
                    String direccion = IO.inputString("Profesional", "Ingrese direccion");
                    int telefono = IO.inputIntegerPositive("Profesional", "Ingrese el telefono");
                    int matricula = IO.inputIntegerPositive("Profesional", "Ingrese la matricula");
                    Profesional prof = new Profesional(id, nombre, apellido, direccion, telefono, matricula, 0);
                    //Lo agrega al arreglo
                    boolean agregado = inst.agregarProfesional(prof);
                    //Checkeo si se pudo agregar
                    if (agregado) {
                        JOptionPane.showMessageDialog(null, "Profesional registrado correctamente.\n\n"+prof.toString());   
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo registrar el profesional.\n"+
                        "Lista llena o datos duplicados (DNI / Matrícula).","Error",JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 2:
                    /*Listar profesionales */
                    String res = inst.mostrarProfesionales();
                    JOptionPane.showMessageDialog(null,res,"Listado Profesionales",1);
                    break;
                default:
                    break;
            }
        } while (!atras);
    }

    public void opcionMenuTurnos(){
        boolean atras = false;
        do {
            opcionMenuPacientes = IO.opcionSelect("Turnos", "1. Agendar turno\n0. Atras", 5);
            switch (opcionMenuPacientes) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    String pacBuscado = IO.inputString("Selccion Paciente", "Ingrese el dni:");
                    Paciente pac = inst.buscarPacientePorDni(pacBuscado);
                    String profBuscado = IO.inputString("Seleccion Profesional", "Ingrese el dni:");
                    Profesional prof = inst.buscarProfesionalPorDni(profBuscado);
                    if(pac==null || prof==null){
                        JOptionPane.showMessageDialog(null, "Error en la busqueda de paciente o profesional","Error",0);
                    }else{
                        /*Seleccion de horario */
                        //Horario seleccionado = inst.seleccionHorario();
                        //inst.agendarNuevoTurno(pac, prof,seleccionado);
                        if (true) {
                            JOptionPane.showMessageDialog(null,
                                "Turno cargado correctamente\n\n" +
                                "Paciente: " +
                                "Profesional: "+
                                "Fecha: " +"\n"+
                                "Hora: "
                        );
                        //SI NO HAY MAS LUGAR, ERROR
                        } else {
                            JOptionPane.showMessageDialog(null,"No hay más lugares para turnos","Error",JOptionPane.WARNING_MESSAGE);
                        }   
                    }
                    break;
                default:
                    break;
            }
        } while (!atras);
    }

    //CONSULTA HORARIOS DISPONIBLES
    public Horario consultaHorarios() {
        Horario[] horarios = inst.getCalendario().getHorarios();
        boolean atras = false;
        do {
            String mensaje = "Horarios disponibles:\n\n";
            for (int i = 0; i < horarios.length; i++) {
                //Agregamos al mensaje, el horario y los turnos que quedan libres
                mensaje += (i + 1) + ". " + horarios[i].getHora() + " - Lugares libres:"+ horarios[i].getLugaresLibres()+"\n";
            }
            mensaje += "\n0. Atras";

            int opcion = IO.opcionSelect("Horarios", mensaje, horarios.length);
            if (opcion == 0) {
                return null;
            }
            //Validamos que esté en rango
            if (opcion >= 1 && opcion <= horarios.length) {
                //Devolvemos el Horario elegido
                return horarios[opcion - 1];
            }
            //Si la opcion es invalida, ERROR
            JOptionPane.showMessageDialog(null, "Opción inválida.", "Error", JOptionPane.WARNING_MESSAGE);

        } while (!atras);
        return null;
    }
   
}
