package utils;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
                if(e.getMessage().equals("Cannot parse null string")){
                    return 0;
                }else{
                    JOptionPane.showMessageDialog(null,"Error en el ingreso","Error",0);
                }
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

    public static int inputIntegerPositiveLimite(String titulo,String mensaje,int limite){
        boolean condicion = false;
        int retorno = 0;
        do { 
            try {
                retorno = Integer.parseInt(JOptionPane.showInputDialog(null,mensaje,titulo,1));
                if(retorno>0 && retorno<=limite){
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
     * Input de un boolean
     * @param titulo
     * @param mensaje
     * @return el booleano ingresado
     */
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
    public static Double inputDoublePositive(String titulo, String mensaje){
        boolean condicion = false;
        Double retorno = null;
        do {
            String input = JOptionPane.showInputDialog(null, mensaje, titulo, 1);
            //Si se cancela se genera un null, y lo retorna
            if (input == null) {
                return null;
            }
            //Si no es null, lo parsea y se trabaja
            try {
                retorno = Double.parseDouble(input);
                if (retorno >= 0) {
                    condicion = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Valor ingresado no válido", "Error", 0);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error en el ingreso", "Error", 0);
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
        boolean condicion = false;
        String resultado ="";
        do {
            resultado = JOptionPane.showInputDialog(null,mensaje,titulo,1);
            if(resultado == null){
                return resultado;
            }
            if(resultado.isEmpty()){
                JOptionPane.showMessageDialog(null, "Upppppsss Campo vacio!","Error",0);
            }else{
                condicion = true;
            }    
        } while (!condicion);
        return resultado;
    }
    public static String stringCancelable(String titulo, String mensaje){
        boolean condicion = false;
        String resultado ="";
        do {
            resultado = JOptionPane.showInputDialog(null,mensaje,titulo,1);
            if(resultado == null){
                return null;
            }
            if(resultado.isEmpty()){
                JOptionPane.showMessageDialog(null, "Upppppsss Campo vacio!","Error",0);
            }else{
                condicion = true;
            }    
        } while (!condicion);
        return resultado;
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
    /**
     * METODO QUE GESTIONA EL INGRESO DE UN CAMPO SOLICITADO COMO SI O NO
     * @param titulo
     * @param mensaje
     * @return boolean 
     */
    public static boolean inputCharBoolean(String titulo,String mensaje){
        boolean condicion = false;
        boolean resultado = false;
        do {
            try {
                char res = JOptionPane.showInputDialog(null,mensaje,titulo,1).charAt(0);
                if(res=='s'||res=='S'||res=='y'||res=='Y'||res=='y'){
                    condicion=true;
                    resultado= true;
                }else if(res=='n'||res=='N'){
                    condicion= true;
                    resultado= false;
                }else{
                    JOptionPane.showMessageDialog(null, "Valor ingresado no valido","Upsss",0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el ingreso","Error",0);
            }
        } while (!condicion);
        return resultado;
    }
    /**
     * Metodo de ingreso de una hora
     * @param titulo
     * @param mensaje
     * @return Hora
     */
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

    public static String editarCampoString(String texto,String valor){
        JTextField campo = new JTextField("");
        Placeholder.setPlaceholder(campo, texto);
        Object[] contenido = {"Ingrese el nuevo "+valor,campo};
        int resName = JOptionPane.showConfirmDialog(null, contenido,"Entrada",1,1);
        String nuevo = texto;
        if(resName == JOptionPane.OK_OPTION){
            nuevo= campo.getText();
        }
        return nuevo;
    }
     public static int editarCampoInteger(int numero,String valor){
        JTextField campo = new JTextField("");
        String texto = String.valueOf(numero);
        Placeholder.setPlaceholder(campo, texto);
        Object[] contenido = {"Ingrese el nuevo "+valor,campo};
        int resName = JOptionPane.showConfirmDialog(null, contenido,"Entrada",1,1);
        int nuevo = 0;
        if(resName == JOptionPane.OK_OPTION){
            nuevo= Integer.parseInt(campo.getText());
        }
        return nuevo;
    }

    public static double editarCampoDouble(double numero,String valor){
        JTextField campo = new JTextField("");
        String texto = String.valueOf(numero);
        Placeholder.setPlaceholder(campo, texto);
        Object[] contenido = {"Ingrese el nuevo "+valor,campo};
        int resName = JOptionPane.showConfirmDialog(null, contenido,"Entrada",1,1);
        double nuevo = 0;
        if(resName == JOptionPane.OK_OPTION){
            nuevo= Double.parseDouble(campo.getText());
        }
        return nuevo;
    }
}
