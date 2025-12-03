package view;

import objetos.Institucion;
import utils.IO;

public class Visualizacion {  
    /*Atributos privados */
    private Institucion inst;
    private int opcionMenuPrincipal;
  
    /*Constructor */
    public Visualizacion(Institucion inst){
        this.inst = inst;
    }
    /**
     * EJECUCION DEL MENU PRINCIPAL
     */
    public void menuPrincipal(){
        boolean finalizar = false;
        do {
            opcionMenuPrincipal = IO.opcionSelect("Bienvenido a "+inst.getNombre(), "1.Pacientes\n2.Profesionales\n3.Turnos\n4.Finanzas\n0.Salir", 4);
            switch (opcionMenuPrincipal) {
                case 1:
                    ViewPacientes.opcionMenuPacientes(inst);
                    break;
                case 2:
                    ViewProfesional.opcionMenuProfesionales(inst);
                    break;
                case 3:
                    ViewTurnos.opcionMenuTurnos(inst);
                    break;
                case 4:
                    ViewFinanzas.opcionMenuFinanzas(inst);
                    break;
                case 0:
                    finalizar = true;
                    break;
                default:
                    break;
            }
        } while (!finalizar);
    }
}
