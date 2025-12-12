package view;

import javax.swing.JOptionPane;
import objetos.Institucion;
import objetos.Profesional;
import utils.IO;

public class ViewProfesional {
     /**
     * EJECUCION DEL MENU DE PROFESIONALES
     */
    public static void opcionMenuProfesionales(Institucion inst){
        boolean atras = false;
        do {
            int opcionMenuProfesionales = IO.opcionSelect("Profesionales", "1. Agregar nuevo Profesional\n2. Seleccionar Profesional\n3. Listar Profesionales\n4. Editar Profesional\n5. Visualizar Profesional\n0. Atras", 4);
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
                    Profesional prof = new Profesional(id, nombre, apellido, direccion, telefono, matricula);
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
                    /*Seleccionar Profesional */
                    boolean atras2 = false;
                        do {
                            int opcion = IO.opcionSelect("Buscar profesional","1. Buscar por DNI\n2. Buscar por apellido\n3. Buscar por matrícula\n0. Atras",3);
                            switch (opcion) {
                                case 0:
                                    atras2 = true;
                                    break;
                                case 1:
                                    String dni = IO.inputString("Busqueda profesional", "Ingrese el dni del profesional:");
                                    Profesional respuesta = inst.buscarProfesionalPorDni(dni);
                                    if(respuesta != null){
                                        JOptionPane.showMessageDialog(null, respuesta.toString(),"Profesional: ",1);
                                    }else{
                                        /*No se encontro profesional*/
                                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                                    }
                                    break;
                                case 2:
                                    String apellidoBuscado = IO.inputString("Busqueda profesional", "Ingrese el apellido del profesional");
                                    Profesional[] res = inst.buscarProfesionalApellido(apellidoBuscado);
                                    if(res.length != 0){
                                        /*Mostrar encontrados Tengo que elegir cual*/
                                        String muestra="";
                                        for(int i=0;i<res.length;i++){
                                            muestra+= (i+1)+" - "+res[i].vistaReducida()+"\n";
                                        }
                                        int seleccionado = IO.opcionSelect("Seleccion de Profesional", muestra+"\n0.Atras", res.length);
                                        Profesional elegido = res[seleccionado-1];
                                        JOptionPane.showMessageDialog(null, elegido.toString(),"Profesional: ",1);
                                    }else{
                                        /*No se encontro profesional*/
                                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                                    }
                                    break;
                                case 3:
                                    int matriculaBuscada = IO.inputIntegerPositive("Busqueda profesional", "Ingrese la matricula del profesional");
                                    Profesional ans = inst.buscarProfesionalMatricula(matriculaBuscada);
                                    if(ans != null){
                                        JOptionPane.showMessageDialog(null, ans.toString(),"Profesional: ",1);
                                    }else{
                                        /*No se encontro profesional*/
                                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } while (!atras2);
                    break;
                case 3:
                    /*Listar profesionales */
                    String res = inst.mostrarProfesionales();
                    JOptionPane.showMessageDialog(null,res,"Listado Profesionales",1);
                    break;
                case 4:
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
                    }else{
                        /*No se encontro paciente*/
                        JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                    }
                    break;
                case 5:
                    boolean atras3 = false;
                        do {
                            int opcion = IO.opcionSelect("Buscar Profesional","1. Buscar por DNI\n2. Buscar por apellido\n3. Buscar por matricula\n0. Atras",3);
                            switch (opcion) {
                                case 0:
                                    atras3 = true;
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
                                        JOptionPane.showMessageDialog(null, inst.showArregloPersona(ans),"Profesional",1);
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
                        } while (!atras3);
                    break;
                default:
                    break;
            }
        } while (!atras);
    }
}
