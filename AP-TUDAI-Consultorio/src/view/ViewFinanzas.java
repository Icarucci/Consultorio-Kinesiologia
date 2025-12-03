package view;

import javax.swing.JOptionPane;

import objetos.Institucion;
import utils.IO;

public class ViewFinanzas {
    
     public static void opcionMenuFinanzas(Institucion inst){
        boolean atras = false;
        do {
           int opcionMenuFinanzas = IO.opcionSelect("Finanzas", "1. Gastos mensuales\n2. Pagar Sueldos", 3);
           switch (opcionMenuFinanzas) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    boolean atras2 = false;
                    do {
                        int opcionMenuGastosMensuales = IO.opcionSelect("Gastos Mensuales", "1. Gastos fijos\n2. Gastos Sueldos", 2);
                        switch (opcionMenuGastosMensuales) {
                            case 0:
                                atras2 = true;
                                break;
                            case 1:
                                boolean atras3 = false;
                                do {
                                    String gastos = String.valueOf(inst.getCostoFijo());
                                    int opcionMenuGastosFijos = IO.opcionSelect("Gastos Fijos", "Monto fijo mensual: $"+gastos+" pesos.\n\n1. Editar gastos", 1);
                                    switch (opcionMenuGastosFijos) {
                                        case 0:
                                            atras3 = true;
                                            break;
                                        case 1:
                                            Double gastoActualizado = IO.inputDoublePositive("Actualizar gastos fijos", "Ingrese el nuevo monto:");
                                            inst.setCostoFijo(gastoActualizado);
                                            JOptionPane.showMessageDialog(null, "Nuevo monto fijo mensual: $"+String.valueOf(inst.getCostoFijo())+" $", "Gastos fijos", opcionMenuGastosFijos);        
                                            break;
                                        default:
                                            break;
                                    }        
                                } while (!atras3);
                                break;
                            default:
                                break;
                        }
                    } while (!atras2);
                    break;
                case 2:
                    boolean atras4 = false;
                    do {
                        int opcionMenuPagarSueldos = IO.opcionSelect("Pagar Sueldos", "Seleccione el profesional para pagar el sueldo:\n\n"+inst.mostrarSueldos()+"\n\nTotal sueldos: $ "+inst.totalSueldos(), inst.cantProfesionales());
                        if (opcionMenuPagarSueldos >= 1 && opcionMenuPagarSueldos <= inst.cantProfesionales()) {
                            
                            inst.pagarSueldo(opcionMenuPagarSueldos);
        
                        } else {
                            System.out.println("Opción inválida");
                            atras4 = true;
                        }
                        
                    } while (!atras4);
                    break;
            default:
                break;
           }
        } while (!atras);

    }
}