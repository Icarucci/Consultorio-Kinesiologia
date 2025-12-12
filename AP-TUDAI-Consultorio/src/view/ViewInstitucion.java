package view;

import javax.swing.JOptionPane;
import objetos.Comprobante;
import objetos.Institucion;
import objetos.Profesional;
import objetos.Puesto;
import utils.IO;

public class ViewInstitucion {
    
     public static void opcionMenuFinanzas(Institucion inst){
        boolean atras = false;
        do {
           int opcionMenuFinanzas = IO.opcionSelect("Institucion", "1. Gastos mensuales\n2. Comprobantes\n3. Nombre de la institucion\n4.Valor turno\n5.Puestos\n0. Atras", 5);
           switch (opcionMenuFinanzas) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    boolean atras2 = false;
                    do {
                        int opcionMenuGastosMensuales = IO.opcionSelect("Gastos Mensuales", "Gastos Totales: $"+inst.getGastosTotales()+"\n\n1. Gastos fijos\n2. Gastos Sueldos\n0. Atras", 2);
                        switch (opcionMenuGastosMensuales) {
                            case 0:
                                atras2 = true;
                                break;
                            case 1:
                                boolean atras3 = false;
                                do {
                                    String gastos = String.valueOf(inst.getCostoFijo());
                                    int opcionMenuGastosFijos = IO.opcionSelect("Gastos Fijos", "Monto fijo mensual: $"+gastos+" pesos.\n\n1. Editar gastos\n0. Atras", 1);
                                    switch (opcionMenuGastosFijos) {
                                        case 0:
                                            atras3 = true;
                                            break;
                                        case 1:
                                            Double gastoActualizado = IO.inputDoublePositive("Actualizar gastos fijos", "Ingrese el nuevo monto:");
                                            inst.setCostoFijo(gastoActualizado);
                                            JOptionPane.showMessageDialog(null, "Nuevo monto fijo mensual: $"+String.valueOf(inst.getCostoFijo()), "Gastos fijos", opcionMenuGastosFijos);        
                                            break;
                                        default:
                                            break;
                                    }        
                                } while (!atras3);
                                break;
                            case 2:
                                boolean atras4 = false;
                                do {
                                    int seleccion = IO.opcionSelect("Gastos Sueldos", "Seleccione el profesional para pagar el sueldo:\n\n"+inst.mostrarSueldosArreglo()+"\n0. Atras\n\nTotal sueldos: $ "+inst.sumaSueldos(), inst.cantProfesionales());
                                    switch (seleccion) {
                                    case 0:
                                        atras4 = true;
                                        break;
                                    default:
                                        Profesional profesional = inst.getProfesionalIndex(seleccion);
                                        if(profesional != null){
                                            if(inst.calculoSueldo(profesional) == 0.0){
                                                JOptionPane.showMessageDialog(null, "No hay saldo pendiente para abonar a "+profesional.getApellido()+", "+profesional.getNombre(), "Error", 2);
                                            } else {
                                                double aPagar = inst.calculoSueldo(profesional);
                                                JOptionPane.showMessageDialog(null, "Pagado a "+profesional.getApellido()+" sueldo de: $"+aPagar,"Pagado",3);
                                                inst.generarComprobante(profesional);
                                                inst.pagarSueldo(profesional);
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(null, "No hay profesionales cargados","Error",0);
                                        }
                                        break;
                                    }      
                                } while (!atras4);
                                break;    
                            default:
                                break;
                        }
                    } while (!atras2);
                    break;
                case 2:
                    if(inst.getCantComprobantes() == 0){
                        JOptionPane.showMessageDialog(null, "No hay comprobantes.", "ERROR", 0);
                    } else {
                        String comprobantes = inst.mostrarComprobantesArreglo();
                        int index = IO.opcionSelect("COMPROBANTES", "Seleccione el comprobante que desea visualizar\n0. Atras\n\n"+comprobantes, inst.getCantComprobantes());
                        Comprobante comp = inst.mostrarComprobante(index);
                        JOptionPane.showMessageDialog(null, comp.getDato(), "Comprobante N° "+index, 1);
                    }
                    break;
                case 3:
                    String nombre = IO.editarCampoString(inst.getNombre(),"nombre");
                    inst.setNombre(nombre);
                    break;
                case 4:
                    Double valor = IO.inputDoublePositive("Valor del Turno", "Ingrese el nuevo importe("+inst.getValorTurno()+"): ");
                    inst.setValorTurno(valor);
                case 5:
                    boolean atras3 = false;
                    do {
                        int op = IO.opcionSelect("Puestos", "1. Listar puestos\n2.Crear puesto\n0.Atras",2);
                        switch (op) {
                            case 0:
                                atras3=true;
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(null,inst.showPuestos(),"Puestos laborales",1);
                                break;
                            case 2:
                                String name = IO.inputString("Nuevo puesto", "Ingrese nombre");
                                Puesto newPuesto = new Puesto(name);
                                inst.addPuesto(newPuesto);
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