package view;

import objetos.Institucion;
import utils.IO;

public class ViewFinanzas {
    
     public static void opcionMenuFinanzas(Institucion inst){
        boolean atras = false;
        do {
           int opcionMenuFinanzas = IO.opcionSelect("Finanzas", "1. Gastos mensuales\n2. Pagar Sueldos\n0. Atras", 3);
           switch (opcionMenuFinanzas) {
                case 0:
                    atras = true;
                    break;
                case 1:
                    break;
                case 2:
                    break;
            default:
                break;
           }
        } while (!atras);

    }
}
