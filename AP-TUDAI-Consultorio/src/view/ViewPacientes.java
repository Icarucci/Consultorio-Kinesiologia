package view;

import javax.swing.JOptionPane;
import objetos.Institucion;
import objetos.Paciente;
import utils.IO;

public class ViewPacientes {
    /**
     * EJECUCION DEL MENU DE PACIENTES
     */
    public static void opcionMenuPacientes(Institucion inst){
        boolean atras = false;
        do {
            int opcionMenuPacientes = IO.opcionSelect("Pacientes", "1.Nuevo paciente\n2.Seleccionar paciente\n3.Listar Pacientes\n4.Editar Paciente", 4);
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
                    boolean cronico = IO.inputCharBoolean("Paciente", "Es cronico");
                    //Crea al paciente
                    Paciente paciente = new Paciente(id, nombre, apellido, direccion, telefono, obraSocial, sesionesTotales, cronico);
                    //Lo agrega al arreglo
                    boolean agregado = inst.agregarPaciente(paciente);
                    //Checkeo si se pudo agregar
                    if (agregado) {
                        JOptionPane.showMessageDialog(null,"Paciente cargado correctamente.\n\n"+paciente.toString(),"Carga de paciente",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"No se pudo cargar el paciente.\nDatos duplicados (DNI).","Error",JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 2:
                    boolean atras2 = false;
                        do {
                            int opcion = IO.opcionSelect("Buscar paciente","1. Buscar por DNI\n2. Buscar por apellido",2);
                            switch (opcion) {
                                case 0:
                                    atras2 = true;
                                    break;
                                case 1:
                                    String dni = IO.inputString("Busqueda Paciente", "Ingrese el dni del paciente:");
                                    Paciente respuesta = inst.buscarPacientePorDni(dni);
                                    if(respuesta != null){
                                        addSesiones(respuesta);
                                    }else{
                                        /*No se encontro paciente*/
                                        JOptionPane.showMessageDialog(null, "Paciente no encontrado","Error",0);
                                    }
                                    break;
                                case 2:
                                    String apellidoBuscado = IO.inputString("Busqueda Paciente", "Ingrese el apellido del paciente");
                                    Paciente[] res = inst.buscarPacienteApellido(apellidoBuscado);
                                    if(res != null){
                                        /*Mostrar encontrados Tengo que elegir cual*/
                                        String muestra="";
                                        for(int i=0;i<res.length;i++){
                                            muestra+= (i+1)+" - "+res[i].vistaReducida()+"\n";
                                        }
                                        int seleccionado = IO.opcionSelect("Seleccion de paciente", muestra, res.length);
                                        Paciente elegido = res[seleccionado-1];
                                        addSesiones(elegido);
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
                        /*Edicion ID */
                        String texto = respuesta.getId();
                        String nuevo = IO.editarCampoString(texto, "ID");
                        respuesta.setId(nuevo);
                        /*Edicion Apellido */
                        texto = respuesta.getApellido();
                        nuevo = IO.editarCampoString(texto,"Apellido");
                        respuesta.setApellido(nuevo);
                        /*Edicion Nombre */
                        texto = respuesta.getNombre();
                        nuevo = IO.editarCampoString(texto,"Nombre");
                        respuesta.setNombre(nuevo);
                        /*Edicion direccion */
                        texto = respuesta.getDireccion();
                        nuevo = IO.editarCampoString(texto, "Direccion");
                        respuesta.setDireccion(nuevo);
                        /*Edicion telefono */
                        int numero = respuesta.getTelefono();
                        int nuevoNumero = IO.editarCampoInteger(numero, "Telefono");
                        respuesta.setTelefono(nuevoNumero);
                        /*Edicion Sesiones */
                        numero = respuesta.getSesionesTotales();
                        nuevoNumero = IO.editarCampoInteger(numero, "Sesiones");
                        respuesta.setSesionesTotales(nuevoNumero);
                        /*Edicion cronico */
                        boolean estado = IO.inputCharBoolean("Paciente", "Es cronico");
                        respuesta.setCronico(estado);
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

    public static void addSesiones(Paciente paciente){
        boolean cargar = IO.inputCharBoolean("Paciente "+paciente.getApellido(), paciente.toString()+"\n\nAgregar sesiones?: ");
        if(cargar){
            int ses = IO.inputIntegerPositive("Cargar sesiones a "+paciente.getApellido(), "Ingrese la cantidad de sesiones a agregar\n\nSesiones remanentes: "+paciente.getSesionesRemanentes());
            paciente.setSesionesTotales(paciente.getSesionesTotales()+ses);
            paciente.setSesionesRemanentes(paciente.getSesionesRemanentes()+ses);
            JOptionPane.showMessageDialog(null, "Agregadas "+ses+" sesiones\nSesiones remanentes: "+paciente.getSesionesRemanentes());
        }
    }
    
}