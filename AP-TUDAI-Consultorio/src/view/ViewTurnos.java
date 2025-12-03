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

public class ViewTurnos {
     /**
     * EJECUCION DEL MENU DE TURNOS
     */
    public static void opcionMenuTurnos(Institucion inst){
        boolean atras = false;
        do {
            int opcionMenuTurnos = IO.opcionSelect("Turnos", "1. Agendar turno\n2. Visualizar Turnos\n3. Seleccionar Turno", 3);
            switch (opcionMenuTurnos) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    /*Busco paciente */
                    String pacBuscado = IO.inputString("Seleccion Paciente", "Listado:\n"+inst.mostrarPacientes()+"\nIngrese el dni:");
                    //Volver al menú anterior al presionar cancelar
                    if (pacBuscado == null) {
                        break;
                    }
                    Paciente paciente = inst.buscarPacientePorDni(pacBuscado);
                    if(paciente!=null){
                        /*Busco profesional */
                        if(paciente.getSesionesRemanentes() != 0){
                            String profBuscado = IO.inputString("Seleccion Profesional", "Listado\n"+inst.mostrarProfesionales()+"\nIngrese el dni:");
                            Profesional profesional = inst.buscarProfesionalPorDni(profBuscado);
                            if(profesional != null){
                                /*Selecciono la fecha */
                                LocalDate fecha = IO.inputLocaldate("Fecha", "Seleccione fecha");
                                if(!fecha.isBefore(LocalDate.now())){
                                    Hora hora = IO.inputHora("Horario", "Ingrese horario del turno");
                                    int index = IO.opcionSelect("Seleccion del Puesto", "1.Camilla 1\n2.Camilla 2\n3. Bicicleta\n4.Gimnasio 1\n5.Gimnasio 2", 5);
                                    Puesto puesto = inst.getPuesto(index-1);
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
                                }else{
                                    JOptionPane.showMessageDialog(null, "No puede seleccionarse una fecha previa al dia actual","Error",0);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Profesional con dni: "+profBuscado+" no encontrado","Error",0);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "El paciente no posee mas sesiones","Error",0);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Paciente con dni: "+pacBuscado+" no encontrado","Error",0);
                    }
                    break;
                case 2:
                    /*visualizar turnos */
                    boolean back = false;
                    do {
                        int seleccion = IO.opcionSelect("Visualizacion de Turnos","Que turnos desea visualizar:\n1.Por paciente\n2.Por profesional\n3.Por puesto\n4.Todos\n0.Atras",4);
                        switch (seleccion) {
                            case 0:
                                back = true;
                                break;
                            case 1:
                                viewTurnosPaciente(inst);
                                break;
                            case 2:
                                viewTurnosProfesional(inst); 
                                break;
                            case 3:
                                viewTurnosPuesto(inst);
                                break;
                            case 4:
                                viewTurnosAll(inst);
                                break;
                            default:
                                break;
                        }
                    } while (!back);
                    break;
                case 3:
                    /*Seleccion de un turno */
                    if(inst.cantidadTurnos()==0){
                        JOptionPane.showMessageDialog(null, "No se disponen de turnos","Seleccion de Turno",0);
                    }else{
                        boolean back2 = false;   
                        do {
                            int seleccion = IO.opcionSelect("Seleccion de Turno", inst.showTurnos()+"\n0.Atras",inst.cantidadTurnos());
                            if(seleccion == 0){
                                back2 = true;
                            }else{
                                Turno turnoSeleccionado = inst.getTurno(seleccion-1);
                                int confirmacion = IO.opcionSelect("Turno", turnoSeleccionado.toString()+"\nConfirma asistencia:\n1.Si\n2.No",2);
                                if(confirmacion == 1){
                                    turnoSeleccionado.setAsistencia(true);
                                    turnoSeleccionado.pacienteAsistio();
                                    turnoSeleccionado.profesionalCobra();
                                }
                            }
                        } while (!back2);
                    }
                    break;
                default:
                    break;
            }
        } while (!atras);
    }
    /**
     * VISUALIZACION DE LOS TURNOS POR PACIENTE
     */
    public static void viewTurnosPaciente(Institucion inst){
        String pac = IO.inputString("Seleccion Paciente", "Listado:\n"+inst.mostrarPacientes()+"\nIngrese el dni:");
        Paciente paciente = inst.buscarPacientePorDni(pac);
         if(paciente==null){
            JOptionPane.showMessageDialog(null, "No existe paciente con dni: "+pac,"Error",0);
        }else{
            JOptionPane.showMessageDialog(null, paciente.showTurnos(),"Turnos de "+paciente.getApellido()+", "+paciente.getNombre(),1);
        }
    }
    /**
     * VISUALIZACION DE LOS TURNOS POR PROFESIONAL
     */
    public static void viewTurnosProfesional(Institucion inst){
        String profBuscado = IO.inputString("Seleccion Profesional", "Listado\n"+inst.mostrarProfesionales()+"\nIngrese el dni:");
        Profesional profesional = inst.buscarProfesionalPorDni(profBuscado);
         if(profesional==null){
            JOptionPane.showMessageDialog(null, "No existe profesional con dni: "+profBuscado,"Error",0);
        }else{
        JOptionPane.showMessageDialog(null, profesional.showTurnos(),"Turnos de "+profesional.getApellido()+", "+profesional.getNombre(),1);
        }
    }
    /**
     * VISUALIZACION DE LOS TURNOS POR PUESTO
     */
    public static void viewTurnosPuesto(Institucion inst){
        int index = IO.opcionSelect("Seleccion del Puesto", "1.Camilla 1\n2.Camilla 2\n3. Bicicleta\n4.Gimnasio 1\n5.Gimnasio 2", 5);
        Puesto puesto = inst.getPuesto(index-1);
        JOptionPane.showMessageDialog(null, puesto.showTurnos(),"Turnos de "+puesto.getNombre(),1);
    }
    /**
     * VISUALIZACION DE LOS TURNOS TOTALES
     */
    public static void viewTurnosAll(Institucion inst){
        JOptionPane.showMessageDialog(null, inst.showTurnos(),"Turnos de "+inst.getNombre(),1);
    }

}
