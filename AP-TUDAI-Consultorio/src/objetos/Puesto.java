package objetos;

public class Puesto {
    //ATRIBUTOS
    private String nombre;
    private boolean ocupado;
    
    //CONSTRUCTOR
    public Puesto(String nombre) {
        this.nombre = nombre;
        this.ocupado = false;
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isOcupado() {
        return ocupado;
    }
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
}
