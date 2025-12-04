package view;

import javax.swing.JOptionPane;
import objetos.Institucion;
import objetos.Profesional;
import utils.Arreglo;
import utils.IO;

public class ViewProfesional {
     /**
     * EJECUCION DEL MENU DE PROFESIONALES
     */
    public static void opcionMenuProfesionales(Institucion inst){
        boolean atras = false;
        do {
            int opcionMenuProfesionales = IO.opcionSelect("Profesionales", "1.Agregar nuevo Profesional\n2.Listar Profesionales\n3.Editar Profesional\n4.Visualizar Profesional", 4);
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
                case 3:
                    /*Edicion de un profesional */
                    String dni = IO.inputString("Busqueda Profesional", inst.mostrarProfesionales()+"\n\nIngrese el dni del profesional:");
                    Profesional respuesta = inst.buscarProfesionalPorDni(dni);
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
                        /*Edicion matricula */
                        int mat = respuesta.getMatricula();
                        int nuevaMat = IO.editarCampoInteger(mat, "Matricula");
                        respuesta.setMatricula(nuevaMat);
                        /*Edicion Sueldo */
                        double sueldo = respuesta.getSueldo();
                        double nuevoSueldo = IO.editarCampoDouble(sueldo,"Sueldo fijo");
                        respuesta.setSueldo(nuevoSueldo);
                    }else{
                        /*No se encontro paciente*/
                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                    }
                    break;
                case 4:
                    boolean atras2 = false;
                        do {
                            int opcion = IO.opcionSelect("Buscar Profesional","1. Buscar por DNI\n2. Buscar por apellido\n3. Buscar por matricula",3);
                            switch (opcion) {
                                case 0:
                                    atras2 = true;
                                    break;
                                case 1:
                                    String ident = IO.inputString("Busqueda Profesional", "Ingrese el dni del profesional:");
                                    Profesional profesional = inst.buscarProfesionalPorDni(ident);
                                    if(profesional != null){
                                        JOptionPane.showMessageDialog(null, profesional.toString(),"Profesional",1);
                                    }else{
                                        /*No se encontro paciente*/
                                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                                    }
                                    break;
                                case 2:
                                    String apellidoBuscado = IO.inputString("Busqueda Profesional", "Ingrese el apellido del profesional");
                                    Profesional[] ans = inst.buscarProfesionalApellido(apellidoBuscado);
                                    if(ans.length != 0){
                                        /*Mostrar encontrados Tengo que elegir cual*/
                                        JOptionPane.showMessageDialog(null, Arreglo.showArregloPersona(ans),"Profesional",1);
                                    }else{
                                        /*No se encontro profesional*/
                                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                                    }
                                    break;
                                case 3:
                                    int matriculaBuscada = IO.inputIntegerPositive("Profesional", "Ingrese la matricula:");
                                    Profesional buscado = inst.buscarProfesionalMatricula(matriculaBuscada);
                                    if(buscado != null){
                                        JOptionPane.showMessageDialog(null, buscado.toString(),"Profesional",1);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                                    }
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
}
