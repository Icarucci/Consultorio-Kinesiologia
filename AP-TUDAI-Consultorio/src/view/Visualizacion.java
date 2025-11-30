package view;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import objetos.Hora;
import objetos.Institucion;
import objetos.Paciente;
import objetos.Profesional;
import objetos.Puesto;
import objetos.Turno;
import utils.IO;

public class Visualizacion {  
    private Institucion inst;
    private int opcionMenuPrincipal;
    private int opcionMenuPacientes;
    private int opcionMenuProfesionales;

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
            opcionMenuPacientes = IO.opcionSelect("Turnos", "1. Agendar turno\n2. Visualizar Turnos\n0. Atras", 5);
            switch (opcionMenuPacientes) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    String pacBuscado = IO.inputString("Seleccion Paciente", "Listado:\n"+inst.mostrarPacientes()+"\nIngrese el dni:");
                    Paciente paciente = inst.buscarPacientePorDni(pacBuscado);
                    String profBuscado = IO.inputString("Seleccion Profesional", "Listado\n"+inst.mostrarProfesionales()+"\nIngrese el dni:");
                    Profesional profesional = inst.buscarProfesionalPorDni(profBuscado);
                    LocalDate fecha = IO.inputLocaldate("Fecha", "Seleccione fecha");
                    Hora hora = IO.inputHora("Horario", "Ingrese horario del turno");
                    int index = IO.opcionSelect("Seleccion del Puesto", "1.Camilla 1\n2.Camilla 2\n3. Bicicleta\n4.Gimnasio 1\n5.Gimnasio 2", 5);
                    Puesto puesto = inst.getPuesto(index-1);
                    if(paciente==null || profesional==null){
                        JOptionPane.showMessageDialog(null, "Error en la busqueda de paciente o profesional","Error",0);
                    }else{
                        /*Validaciones*/
                        /*El paciente debe validar que no tiene turno ese dia */
                        boolean validacionPaciente = paciente.validacion(fecha);
                        /*El profesional debe validar que no tiene otro turno ese dia a esa hora */
                        boolean validacionProfesional = profesional.validacion(fecha,hora);
                        /*El puesto debe validar que ese dia a esa hora esta libre */
                        boolean validacionPuesto = puesto.validacion(fecha,hora);
                        if(!validacionPaciente){
                            JOptionPane.showMessageDialog(null, "No pudo asignarse el turno, el paciente: "+paciente.getApellido()+", "+paciente.getNombre()+" ya posee un turno este dia: "+fecha.toString(),"Error",0);
                        }else if (!validacionProfesional) {
                            JOptionPane.showMessageDialog(null, "No pudo asignarse el turno, el profesional: "+profesional.getApellido()+", "+profesional.getNombre()+"tiene ese turno ocupado","Error",0);
                        }else if (!validacionPuesto){
                            JOptionPane.showMessageDialog(null, "No pudo asignarse el turno, el puesto: "+puesto.getPuestoNumero()+"."+puesto.getNombre()+" Se encuentra ocupado.","Error",0);
                        }else{
                            Turno nuevoTurno = new Turno(puesto,paciente,profesional,fecha,hora);
                            inst.agendarNuevoTurno(nuevoTurno);
                            paciente.agendarNuevoTurno(nuevoTurno);
                            profesional.agendarNuevoTurno(nuevoTurno);
                            puesto.agendarNuevoTurno(nuevoTurno);
                        }
                    }
                    break;
                case 2:
                    boolean back = false;
                    do {
                        int seleccion = IO.opcionSelect("Visualizacion de Turnos","Que turnos desea visualizar:\n1.Por paciente\n2.Por profesional\n3.Por puesto\n4.Todos\n0.Atras",4);
                        switch (seleccion) {
                            case 0:
                                back = true;
                                break;
                            case 1:
                                viewTurnosPaciente();
                                break;
                            case 2:
                                viewTurnosProfesional(); 
                                break;
                            case 3:
                                viewTurnosPuesto();
                                break;
                            case 4:
                                viewTurnosAll();
                                break;
                            default:
                                break;
                        }
                    } while (!back);
                    break;
                default:
                    break;
            }
        } while (!atras);
    }
    public void viewTurnosPaciente(){
        String pac = IO.inputString("Seleccion Paciente", "Listado:\n"+inst.mostrarPacientes()+"\nIngrese el dni:");
        Paciente paciente = inst.buscarPacientePorDni(pac);
         if(paciente==null){
            JOptionPane.showMessageDialog(null, "No existe paciente con dni: "+pac,"Error",0);
        }else{
        JOptionPane.showMessageDialog(null, paciente.showTurnos(),"Turnos de "+paciente.getApellido()+", "+paciente.getNombre(),1);
        }
    }
    public void viewTurnosProfesional(){
        String profBuscado = IO.inputString("Seleccion Profesional", "Listado\n"+inst.mostrarProfesionales()+"\nIngrese el dni:");
        Profesional profesional = inst.buscarProfesionalPorDni(profBuscado);
         if(profesional==null){
            JOptionPane.showMessageDialog(null, "No existe profesional con dni: "+profBuscado,"Error",0);
        }else{
        JOptionPane.showMessageDialog(null, profesional.showTurnos(),"Turnos de "+profesional.getApellido()+", "+profesional.getNombre(),1);
        }
    }
    public void viewTurnosPuesto(){
        int index = IO.opcionSelect("Seleccion del Puesto", "1.Camilla 1\n2.Camilla 2\n3. Bicicleta\n4.Gimnasio 1\n5.Gimnasio 2", 5);
        Puesto puesto = inst.getPuesto(index-1);
        JOptionPane.showMessageDialog(null, puesto.showTurnos(),"Turnos de "+puesto.getNombre(),1);
    }
    public void viewTurnosAll(){
        JOptionPane.showMessageDialog(null, inst.showTurnos(),"Turnos de "+inst.getNombre(),1);
    }

}
