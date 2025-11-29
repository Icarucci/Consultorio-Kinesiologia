import objetos.Institucion;
import view.Visualizacion;

public class App {
    public static void main(String[] args) throws Exception {
        Institucion apnea = new Institucion("Apnea", null, null, null, null, 0, 0, null);
        Visualizacion view = new Visualizacion(apnea);

        view.menuPrincipal();
    }
    
}
