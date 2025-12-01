package utils;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import objetos.Hora;

/*CLASE UTILITARIA PARA GESTIONAR LOS INPUTS Y OUTPUS */
public class IO {
   
    /**
     * METODO PARA SOLICITAR EN EL INPUT DEL USUARIO LA SELECCION EN UN MENU NUMERICO DESDE 0-limite.
     * RECOMENDADO EL 0 PARA OPCION SALIR O ATRAS.
     * @param titulo
     * @param mensaje
     * @param limite
     * @return entero con la opcion seleccionada
     */
    public static int opcionSelect(String titulo,String mensaje,int limite){
        boolean condicion = false;
        int retorno = 0;
        do { 
            try {
                retorno = Integer.parseInt(JOptionPane.showInputDialog(null,mensaje,titulo,1));
                if(retorno>=0 && retorno<=limite){
                    condicion = true;
                }else{
                    JOptionPane.showMessageDialog(null, "Valor ingresado no valido","Error",0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error en el ingreso","Error",0);
            }
        } while (!condicion);
        return retorno;
    }
    /**
     * METODO PARA E INGRESO DE UN ENTERO POSITIVO
     * @param titulo
     * @param mensaje
     * @return entero positivo
     */
    public static int inputIntegerPositive(String titulo,String mensaje){
        boolean condicion = false;
        int retorno = 0;
        do { 
            try {
                retorno = Integer.parseInt(JOptionPane.showInputDialog(null,mensaje,titulo,1));
                if(retorno>0){
                    condicion = true;
                }else{
                    JOptionPane.showMessageDialog(null, "Valor ingresado no valido","Error",0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error en el ingreso","Error",0);
            }
        } while (!condicion);
        return retorno;
    }


    public static boolean inputBoolean(String titulo,String mensaje){
        boolean condicion = false;
        boolean retorno = false;
        do { 
            try {
                retorno = Boolean.parseBoolean(JOptionPane.showInputDialog(null,mensaje,titulo,1));
                condicion = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error en el ingreso","Error",0);
            }
        } while (!condicion);
        return retorno;
    }

    /**
     * METODO PARA EL INPUT DE UN REAL POSITIVO.
     * @param titulo
     * @param mensaje
     * @return double positivo
     */
    public static double inputDoublePositive(String titulo,String mensaje){
        boolean condicion = false;
        double retorno = 0;
        do { 
            try {
                retorno = Double.parseDouble(JOptionPane.showInputDialog(null,mensaje,titulo,1));
                if(retorno>=0){
                    condicion = true;
                }else{
                    JOptionPane.showMessageDialog(null, "Valor ingresado no valido","Error",0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error en el ingreso","Error",0);
            }
        } while (!condicion);
        return retorno;
    }

    /**
     * METODO PARA GESTIONAR DESDE LA CLASE UTILITARIA EL INGRESO DE STRINGS
     * @param titulo
     * @param mensaje
     * @return String input
     */
    public static String inputString(String titulo, String mensaje){
        return JOptionPane.showInputDialog(null,mensaje,titulo,1);
    }

     /**
     * METODO PARA GESTIONAR DESDE LA CLASE UTILITARIA EL INGRESO DE LOCALDATES
     * @param titulo
     * @param mensajes  
     * @return LocalDate input
     */
    public static LocalDate inputLocaldate(String titulo, String mensaje){
        boolean condicion = false;
        LocalDate fecha = LocalDate.of(1900,1,1);
        do {
            try {
                int day = opcionSelect("Dia", "Seleccione el numero de dia", 31);
                int month = opcionSelect("Mes", "Seleccione el numero de mes", 12);
                int year = inputIntegerPositive("Año", "Ingrese el año");
                fecha = LocalDate.of(year, month, day);        
                condicion = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el ingreso","Error",0);
            }
        } while (!condicion);
        return fecha;
    }
    public static Hora inputHora(String titulo, String mensaje){
        boolean condicion = false;
        Hora hora = Hora.H9;
        do {
            try {
                int hr = inputIntegerPositive(titulo, mensaje);
                switch (hr) {
                    case 9:
                        hora = Hora.H9;
                        condicion = true;
                        break;
                    case 10:
                        hora = Hora.H10;
                        condicion = true;
                        break;
                    case 11:
                        hora = Hora.H11;
                        condicion = true;
                        break;
                    case 12:
                        hora = Hora.H12;
                        condicion = true;
                        break;
                    case 13:
                        hora = Hora.H13;
                        condicion = true;
                        break;
                    case 14:
                        hora = Hora.H14;
                        condicion = true;
                        break;
                    case 15:
                        hora = Hora.H15;
                        condicion = true;
                        break;
                    case 16:
                        hora = Hora.H16;
                        condicion = true;
                        break;
                    case 17:
                        hora = Hora.H17;
                        condicion = true;
                        break;
                    case 18:
                        hora = Hora.H18;
                        condicion = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error en el ingreso","Error",0);
                        break;
                }     
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el ingreso","Error",0);
            }
        } while (!condicion);
        return hora;
    }
}
