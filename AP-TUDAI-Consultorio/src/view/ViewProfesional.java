package view;

import javax.swing.JOptionPane;

import objetos.Especialidad;
import objetos.Especialista;
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
            int opcionMenuProfesionales = IO.opcionSelect("Profesionales", "1. Agregar nuevo Profesional\n2. Seleccionar Profesional\n3. Listar Profesionales\n4. Editar Profesional\n5. Eliminar Profesional\n0. Atras", 5);
            switch (opcionMenuProfesionales) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    String id = IO.inputString("Profesional", "Ingrese el DNI");
                    if(id != null && inst.validaIdProfesional(id)){
                        int matricula = IO.inputIntegerPositive("Profesional", "Ingrese la matricula");
                        if(matricula != 0 && inst.validaMatriculaProfesional(matricula)){
                            String nombre =  IO.inputString("Profesional", "Ingrese nombre");
                            if(nombre != null){
                                String apellido = IO.inputString("Profesional", "Ingrese apellido");
                                if(apellido != null){
                                    String direccion = IO.inputString("Profesional", "Ingrese direccion");
                                    if(direccion != null){
                                        int telefono = IO.inputIntegerPositive("Profesional", "Ingrese el telefono");
                                        int esProf = JOptionPane.showConfirmDialog(null,"¿El profesional tiene especialidad?","Especialidad",0,3);
                                        if (esProf == -1) {
                                            JOptionPane.showMessageDialog(null, "Carga cancelada", "Cancelado", 2);
                                        }
                                        Profesional prof = null;
                                        if(esProf == 0){
                                        Especialidad especialidad = (Especialidad) JOptionPane.showInputDialog(null,"Seleccione la especialidad del profesional:","Especialidad",JOptionPane.QUESTION_MESSAGE,null,Especialidad.values(),Especialidad.values()[0]);
                                            if(especialidad != null){
                                                    prof = new Especialista(id, nombre, apellido, direccion, telefono, matricula, especialidad);
                                            } else if (especialidad == null){
                                                    JOptionPane.showMessageDialog(null, "Carga cancelada", "Cancelado", 2);  
                                            }
                                        } else if (esProf == 1){
                                            prof = new Profesional(id, nombre, apellido, direccion, telefono, matricula);
                                        }                                    
                                        //Lo agrega al arreglo
                                        boolean agregado = inst.agregarProfesional(prof);
                                        //Checkeo si se pudo agregar
                                        if (agregado) {
                                            JOptionPane.showMessageDialog(null, "Profesional registrado correctamente.\n\n"+prof.toString());   
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se pudo registrar el profesional.","Error",JOptionPane.WARNING_MESSAGE);
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
                        } else if (matricula == 0){
                            JOptionPane.showMessageDialog(null, "Carga cancelada","Cancelado",2);
                        } else {
                            JOptionPane.showMessageDialog(null, "Matrícula duplicada", "Cancelado", 2);
                        }
                    }else if(id != null){
                        JOptionPane.showMessageDialog(null, "Dni duplicado","Cancelado",2);
                    }else{
                        JOptionPane.showMessageDialog(null, "Carga cancelada","Cancelado",2);
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
                                    if(dni != null){
                                        Profesional respuesta = inst.buscarProfesionalPorDni(dni);
                                        if(respuesta != null){
                                            JOptionPane.showMessageDialog(null, respuesta.toString(),"Profesional: ",1);
                                        }else{
                                            /*No se encontro profesional*/
                                            JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                                    }    
                                    break;
                                case 2:
                                    String apellidoBuscado = IO.inputString("Busqueda profesional", "Ingrese el apellido del profesional");
                                    if(apellidoBuscado != null){
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
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
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
                    if(dni != null){
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
                            /*Edicion Especialidad */
                            Especialidad nueva = (Especialidad) JOptionPane.showInputDialog(null,"Seleccione la nueva especialidad:","Especialidad",3,null,Especialidad.values(),Especialidad.values()[0]);
                            if (nueva != null) {
                                respuesta.setEspecialidad(nueva);
                                JOptionPane.showMessageDialog(null, "Los datos fueron modificados con éxito","Edicion de datos",1);
                            } else {
                                JOptionPane.showMessageDialog(null, "La especialidad no fue cambiada","Error",2);
                            }
                        }else{
                            /*No se encontro paciente*/
                            JOptionPane.showMessageDialog(null, "Profesional no encontrado","Error",0);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                    }                   
                    break;
                
                case 5:
                    /*Eliminar Profesional */
                    String profesionales = inst.mostrarProfesionales();
                    String ident = IO.inputString("Eliminar Profesional", profesionales+"\nIngrese dni del profesional a eliminar");
                    if(ident != null){
                        boolean response = inst.eliminarProfesionalConIndex(ident);
                        if(response){
                            JOptionPane.showMessageDialog(null, "Profesional eliminado con exito","Eliminado",2);
                        }else{
                            JOptionPane.showMessageDialog(null, "No se encontro el profesional","Error",0);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Proceso cancelado","Cancelado",2);
                    }                    
                    break;
                default:
                    break;
            }
        } while (!atras);
    }
}
