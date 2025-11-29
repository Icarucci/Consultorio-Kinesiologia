package utils;

import objetos.Paciente;
import objetos.Persona;
import objetos.Profesional;
import objetos.Puesto;
import objetos.Turno;

/*CLASE UTILITARIA PARA GESTIONAR LOS ARREGLOS.*/
public class Arreglo {
    /*INSERCIONES*/
    /**
     * METODO QUE RECIBE UN ARREGLO DE PERSONAS Y LE AGREGA UNA PERSONA EN UNA NUEVA POSICION.
     * mediante polimorfismo, sirve para paciente y profesional
     * @param arreglo
     * @return arreglo de Persona con una posicion mas
     */
    /*public static Persona[] agregarPersona(Persona[]arreglo,Persona persona){
        Persona [] nuevo = new Persona[arreglo.length+1];
        for(int i=0;i<arreglo.length;i++){
            nuevo[i]=arreglo[i];
        }
        nuevo[nuevo.length-1] = persona;
        return nuevo;
    }*/

    //AGREGAR PACIENTE
    /**
     * METODO QUE RECIBE UN ARREGLO DE PERSONAS Y LE AGREGA UNA PERSONA EN UNA NUEVA POSICION.
     * SIRVE PARA PACIENTE
     * @param arreglo
     * @param pte
     * @return
     */
    public static Paciente[] agregarPaciente(Paciente[] arreglo, Paciente pte){
        Paciente [] nuevo = new Paciente[arreglo.length+1];
        for(int i=0;i<arreglo.length;i++){
            nuevo[i]=arreglo[i];
        }
        nuevo[nuevo.length-1] = pte;
        return nuevo;
    }

    //AGREGAR PROFESIONAL
    /**
     * METODO QUE RECIBE UN ARREGLO DE PERSONAS Y LE AGREGA UNA PERSONA EN UNA NUEVA POSICION.
     * SIRVE PARA PACIENTE
     * @param arreglo
     * @param pte
     * @return
     */
    public static Profesional[] agregarProfesional(Profesional[] arreglo, Profesional prof){
        Profesional [] nuevo = new Profesional[arreglo.length+1];
        for(int i=0;i<arreglo.length;i++){
            nuevo[i]=arreglo[i];
        }
        nuevo[nuevo.length-1] = prof;
        return nuevo;
    }

    /**
     * METODO QUE RECIBE UN ARREGLO DE TURNOS Y LE AGREGA UN TURNO EN UNA NUEVA POSICION.
     * @param arreglo
     * @return arreglo de Turno con una posicion mas
     */
    public static Turno[] agregarTurno(Turno[]arreglo, Turno turno){
        Turno [] nuevo = new Turno[arreglo.length+1];
        for(int i=0;i<arreglo.length;i++){
            nuevo[i]=arreglo[i];
        }
        nuevo[nuevo.length-1] = turno;
        return nuevo;
    }
     /**
     * METODO QUE RECIBE UN ARREGLO DE PUESTOS Y LE AGREGA UN PUESTO EN UNA NUEVA POSICION.
     * @param arreglo
     * @return arreglo de Puesto con una posicion mas
     */
    public static Puesto[] agregarPuesto(Puesto[]arreglo,Puesto puesto){
        Puesto [] nuevo = new Puesto[arreglo.length+1];
        for(int i=0;i<arreglo.length;i++){
            nuevo[i]=arreglo[i];
        }
        nuevo[nuevo.length-1]=puesto;
        return nuevo;
    }
     /**
     * METODO QUE RECIBE UN ARREGLO DE STRING Y LE AGREGA UNA STRING EN UNA NUEVA POSICION.
     * @param arreglo
     * @return arreglo de Turno con una posicion mas
     */
    public static String[] agregarString(String[]arreglo,String cadena){
        String [] nuevo = new String[arreglo.length+1];
        for(int i=0;i<arreglo.length;i++){
            nuevo[i]=arreglo[i];
        }
        nuevo [nuevo.length-1]=cadena;
        return nuevo;
    }
    /*ORDENAMIENTOS*/
    /**
     * METODO QUE RECIBE UN ARREGLO DE PERSONAS Y LO DEVUELVE ORDENADO POR APELLIDO.
     * burbujeo
     * @param arreglo
     * @return arreglo ordenado por apellido
     */
    public static Persona[] ordenaPersonasApellido(Persona[]arreglo){
        for(int i=0;i<arreglo.length-1;i++){
            for(int j=0;j<arreglo.length-1;j++){
                if((arreglo[j].getApellido().compareTo(arreglo[j+1].getApellido()))>0){
                    Persona aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;
                }
            }
        }
        return arreglo;
    }
    /**
     * METODO QUE RECIBE UN ARREGLO DE PERSONAS Y LO DEVUELVE ORDENADO POR SU ID.
     * burbujeo
     * @param arreglo
     * @return arreglo ordenado por ID
     */
    public static Persona[] ordenaPersonasID(Persona[] arreglo){
        for(int i=0; i < arreglo.length - 1; i++){
            for(int j=0; j < arreglo.length - 1; j++){
                if (arreglo[j].getId().compareTo(arreglo[j+1].getId()) > 0) {
                    Persona aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;
                }
            }
        }
        return arreglo;
    }

    /*BUSQUEDA BINARIA */
    /**
     * METODO DE BUSQUEDA BINARIA DE PERSONAS A PARTIR DE SU ID.
     * @param arreglo de Persona ordenado por ID
     * @param id
     * @return objeto Persona o Null.
     */
    public static Persona buscaPersonaId(Persona[]arreglo,String id){
        int inicio = 0;
        int fin = arreglo.length-1;
        while(inicio<=fin){
            int medio = inicio+(fin-inicio)/2;
            if(arreglo[medio].getId().compareTo(id)==0){
                return arreglo[medio];
            }
            if(arreglo[medio].getId().compareTo(id)<0){
                inicio=medio+1;
            }else{
                fin=medio-1;
            }
        }
        return null;
    }

    /**
     * METODO DE BUSQUEDA POR APELLIDO (LINEAL)
     * @param arreglo
     * @param apellido
     * @return objeto Persona o Null
     */
    public static Persona buscarPersonaApellido(Persona[]arreglo,String apellido){
        Persona retorno = null;
        for(int i=0;i<arreglo.length;i++){
            if(arreglo[i].getApellido().compareTo(apellido)==0){
                retorno = arreglo[i];
            }
        }
        return retorno;
    }
}
