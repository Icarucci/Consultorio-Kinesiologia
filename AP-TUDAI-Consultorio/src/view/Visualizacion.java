package view;

import javax.swing.JOptionPane;

import objetos.Institucion;
import objetos.Paciente;
import objetos.Persona;
import objetos.Profesional;
import utils.Arreglo;
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
            opcionMenuPacientes = IO.opcionSelect("Turnos", "0. Atras", 5);
            switch (opcionMenuPacientes) {
                case 0:
                    atras = true;
                    break;
                default:
                    break;
            }
        } while (!atras);
    }

    private void buscarPacientePorDni() {
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
   
}
