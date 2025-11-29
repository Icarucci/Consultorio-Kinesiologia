package view;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import objetos.Hora;
import objetos.Horario;
import objetos.Institucion;
import objetos.Paciente;
import objetos.Persona;
import objetos.Profesional;
import objetos.Turno;
import utils.Arreglo;
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
            opcionMenuPacientes = IO.opcionSelect("Pacientes", "1.Nuevo paciente\n2.Buscar paciente\n0. Atras", 5);
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
                                buscarPacientePorDni();
                                break;
                            case 2:
                                buscarPacientePorApellido();
                                break;
                            default:
                                break;
                        }
                    } while (!atras2);
                    break;
                default:
                    break;
            }
        } while (!atras);
    }

    public void opcionMenuProfesionales(){
        boolean atras = false;
        do {
            opcionMenuProfesionales = IO.opcionSelect("Profesionales", "1.Agregar NUEVO Profesional\n0. Atras", 5);
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
                    Profesional prof = new Profesional(id, nombre, apellido, direccion, telefono, matricula, 0, null);
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
                    agendarNuevoTurno();
                    break;
                default:
                    break;
            }
        } while (!atras);
    }

    private Paciente buscarPacientePorDni() {
        //1. Ingresamos el DNI del paciente a buscar
        String dni = IO.inputString("Buscar DNI", "Ingrese el DNI del paciente");
        //2. Ordenamos ANTES de búsqueda binaria
        Arreglo.ordenaPersonasID(inst.getPacientes());
        //3. Aplicamos búsqueda binaria
        Persona encontrado = Arreglo.buscaPersonaId(inst.getPacientes(), dni);
        //4. Mostramos el resultado
        if (encontrado != null) {JOptionPane.showMessageDialog(null,encontrado.toString(),"Paciente encontrado",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"No se encontró un paciente con DNI: " + dni,"Sin resultados",JOptionPane.WARNING_MESSAGE);
        }
        //Casteamos para retornar paciente en vez de persona
        return (Paciente) encontrado;
    }

    private void buscarPacientePorApellido(){
        //1. Ingresamos el apellido del paciente a buscar
        String apellido = IO.inputString("Buscar apellido", "Ingrese el apellido del paciente");
        //2. Busqueda lineal
        Persona encontrado = Arreglo.buscarPersonaApellido(inst.getPacientes(), apellido);
        //3. Mostramos el paciente encontrado
        if (encontrado != null) {JOptionPane.showMessageDialog(null,encontrado.toString(),"Paciente encontrado",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"No se encontró un paciente con DNI: " + apellido,"Sin resultados",JOptionPane.WARNING_MESSAGE);
        }
    }

    //AGENDAR NUEVO TURNO
    public void agendarNuevoTurno(){
        //Recibimos el paciente que buscamos por DNI
        Paciente pteEncontrado = buscarPacientePorDni();
        if(pteEncontrado != null){
        
            String profesional = IO.inputString("Seleccion profesional", "Ingrese el Apellido del profesional con el que desea sacar turno");
            //ACA DEBEMOS BUSCAR EL PROFESIONAL SI EXISTE FALTA EL METODO BUSQUEDA PROFESIONAL

            //FORZAMOS EL PROFESIONAL EN LA POSICION 0 PARA HACER LA PRUEBA DEL TURNO
            Profesional prof = inst.getProfesionales()[0];
            //HORARIO
            Horario horarioElegido = consultaHorarios();
            if (horarioElegido == null) {
                JOptionPane.showMessageDialog(null, "No se seleccionó horario.");
                return;
            }
            //FECHA
            LocalDate fecha = inst.getCalendario().getFecha();
            //CREAMOS EL TURNO
            Turno turno = new Turno(null, pteEncontrado, prof, fecha, horarioElegido);
            //GUARDAMOS EL TURNO EN ISNTITUCION
            boolean agregado = horarioElegido.agregarTurno(turno);
            //MANEJ ODE ERRORES
            //SI SE PUDO GUARDAR, LO MOSTRAMOS
            if (agregado) {
                JOptionPane.showMessageDialog(null,
                    "Turno cargado correctamente\n\n" +
                    "Paciente: " + pteEncontrado.getApellido() +" "+pteEncontrado.getNombre()+"\n"+
                    "Profesional: " + prof.getApellido() +" "+prof.getNombre() +"\n"+
                    "Fecha: " + fecha +"\n"+
                    "Hora: " + horarioElegido.getHora() 
            );
            //SI NO HAY MAS LUGAR, ERROR
            } else {
                JOptionPane.showMessageDialog(null,"No hay más lugares para turnos","Error",JOptionPane.WARNING_MESSAGE);
            }
            
        }

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
