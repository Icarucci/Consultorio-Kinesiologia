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
            int opcionMenuPacientes = IO.opcionSelect("Pacientes", "1.Nuevo paciente\n2.Seleccionar paciente\n3.Listar Pacientes\n4.Editar Paciente\n5.Eliminar paciente\n6. Ver Historia Clinica del paciente\n0. Atras\n\n", 6);
            switch (opcionMenuPacientes) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    /*NUEVO PACIENTE */
                    String id = IO.inputString("Paciente", "Ingrese el DNI");
                    if(id != null && inst.validaIdPaciente(id)){
                        String nombre = IO.inputString("Paciente", "Ingrese nombre");
                        if(nombre !=null){
                            String apellido = IO.inputString("Paciente", "Ingrese apellido");
                            if(apellido != null){
                                String direccion = IO.inputString("Paciente", "Ingrese direccion");
                                if(direccion != null){
                                    int telefono = IO.inputIntegerPositive("Paciente", "Ingrese el telefono");
                                    int sesionesTotales = IO.inputIntegerPositive("Paciente", "Ingrese cantidad de sesiones");
                                    String obraSocial = IO.inputString("Paciente", "Ingrese Obra Social");
                                    if(obraSocial != null){
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
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Carga cancelada","Cancelado",2);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Carga cancelada","Cancelado",2);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Carga cancelada","Cancelado",2);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Carga cancelada","Cancelado",2);
                        }
                    }else if(id != null){
                        JOptionPane.showMessageDialog(null, "Dni duplicado","Cancelado",2);
                    }else{
                        JOptionPane.showMessageDialog(null, "Carga cancelada","Cancelado",2);
                    }
                    break;
                case 2:
                    /*SELECCIONAR PACIENTE */
                    boolean atras2 = false;
                        do {
                            int opcion = IO.opcionSelect("Buscar paciente","1. Buscar por DNI\n2. Buscar por apellido\n0. Atras",2);
                            switch (opcion) {
                                case 0:
                                    atras2 = true;
                                    break;
                                case 1:
                                    String dni = IO.inputString("Busqueda Paciente", "Ingrese el dni del paciente:");
                                    if(dni != null){
                                        Paciente respuesta = inst.buscarPacientePorDni(dni);
                                        if(respuesta != null){
                                            addSesiones(respuesta);
                                        }else{
                                            /*No se encontro paciente*/
                                            JOptionPane.showMessageDialog(null, "Paciente no encontrado","Error",0);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                                    }
                                    
                                    break;
                                case 2:
                                    String apellidoBuscado = IO.inputString("Busqueda Paciente", "Ingrese el apellido del paciente");
                                    if(apellidoBuscado != null){
                                        Paciente[] res = inst.buscarPacienteApellido(apellidoBuscado);
                                        if(res.length != 0){
                                            /*Mostrar encontrados*/
                                            String muestra="";
                                            for(int i=0;i<res.length;i++){
                                                muestra+= (i+1)+" - "+res[i].vistaReducida()+"\n";
                                            }
                                            int seleccionado = IO.opcionSelect("Seleccion de paciente", muestra+"\n0.Atras", res.length);
                                            if(seleccionado == 0){
                                                JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                                            }else{
                                                Paciente elegido = res[seleccionado-1];
                                                addSesiones(elegido);
                                            }                                            
                                        }else{
                                            /*No se encontro paciente*/
                                            JOptionPane.showMessageDialog(null, "No se encontraron pacientes","Error",0);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } while (!atras2);
                    break;
                case 3: 
                    /*Listar pacientes. - Se lista la informacion escencial. Apellido, Nombre - Dni , Sesiones realizadas/Sesiones totales. */
                    boolean fin = false;
                    do {
                        String res = inst.mostrarPacientes();
                        res = "Ordenar por:\n1. DNI - 2. Apellido - 3. Sesiones Remantenes\n\n"+res;
                        int index = IO.opcionSelect("Ordenamiento", res+"\n0.Atras", 3);
                        switch (index) {
                            case 0:
                                fin = true;
                                break;
                            case 1:
                                /*Ordeno por DNI */
                                inst.ordenaPacienteId();
                                break;
                            case 2:
                                /*Ordeno por Apellido */
                                inst.ordenaPacienteApellido();
                                break;
                            case 3:
                                /*Ordeno por Sesiones remanentes */
                                inst.ordenaPacienteSesiones();
                                break;
                            default:
                                break;
                        }
                    } while (!fin);
                    break;
                case 4:
                    /*Edicion de un paciente */
                    String dni = IO.inputString("Busqueda Paciente", inst.mostrarPacientes()+"\n\nIngrese el dni del paciente:");
                    if(dni != null){
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
                    }else{
                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                    }
                    
                    break;
                case 5:
                    /*Elimino un paciente segun su ID */
                    String pacientes = inst.mostrarPacientes();
                    String ident = IO.inputString("Eliminar Paciente", pacientes+"\nIngrese dni del paciente a eliminar");
                    if(ident != null){
                        boolean response = inst.eliminarPacienteConIndex(ident);
                        if(response){
                            JOptionPane.showMessageDialog(null, "Paciente eliminado con exito","Eliminado",2);
                        }else{
                            JOptionPane.showMessageDialog(null, "No se encontro el paciente","Error",0);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                    }
                    
                    break;
                case 6:
                    /*Ver HC del paciente */
                    String ptes = inst.mostrarPacientes();
                    boolean atras3 = false;
                    do {
                        String dniStr = IO.stringCancelable("Historia Clínica", "Ingrese el DNI del paciente para ver su HC:\n\n" + ptes);
                        if (dniStr==null) {
                            atras3 = true;
                        }else{
                            Paciente pte = inst.buscarPacientePorDni(dniStr);
                            if (pte == null) {
                                JOptionPane.showMessageDialog(null,"No existe un paciente registrado con ese DNI.","Error",0);
                            } else {
                                if (pte.getCantidadEvoluciones() == 0) {
                                    JOptionPane.showMessageDialog(null, "El paciente no tiene evoluciones cargadas.");
                                } else {
                                    String listado = pte.getListadoEvoluciones();
                                    int cantidad = pte.getCantidadEvoluciones();
                                    int opcion = IO.opcionSelect("Historia Clínica","Seleccione la evolución a ver:\n\n"+listado+"\n0. Atrás",cantidad);
                                    String texto = pte.getEvolucion(opcion-1);
                                    JOptionPane.showMessageDialog(null,texto,"Evolución "+opcion,1);
                                    atras3 = true;
                                }
                            }  
                        }
                        
                    } while (!atras3);
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