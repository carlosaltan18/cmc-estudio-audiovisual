import javax.swing.SwingUtilities;
import util.*;
import controller.*;
import view.*;
/**
 * Clase principal de la aplicación CMS para Estudio de Grabación Audiovisual.
 * Inicia la aplicación en el Event Dispatch Thread de Swing.
 * 
 * @author Carlos
 * @version 1.0
 */
public class Main {
    
    /**
     * Punto de entrada de la aplicación.
     * Ejecuta la inicialización en el Event Dispatch Thread de Swing
     * para garantizar seguridad de hilos en operaciones GUI.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                initializeApplication();
            } catch (Exception e) {
                Logger.error("Error al inicializar la aplicación: " + e.getMessage());
            }
        });
    }

    /**
     * Inicializa todos los controladores y componentes principales de la aplicación.
     * Crea las instancias de los controladores necesarios y construye la interfaz gráfica principal.
     * 
     * <p>Controladores inicializados:
     * <ul>
     *   <li>AuthenticationController - Gestión de autenticación de usuarios</li>
     *   <li>ContentController - Gestión de contenidos</li>
     *   <li>CategoryController - Gestión de categorías</li>
     *   <li>SearchController - Búsqueda de contenidos</li>
     *   <li>ReportController - Generación de reportes</li>
     *   <li>NavigationController - Navegación entre paneles</li>
     * </ul>
     * </p>
     * 
     * @throws Exception Si ocurre un error durante la inicialización
     */
    private static void initializeApplication() {
        AuthenticationController authController = new AuthenticationController();
        ContentController contentController = new ContentController();
        CategoryController categoryController = new CategoryController();
        SearchController searchController = new SearchController(contentController);
        ReportController reportController = new ReportController(contentController);
        NavigationController navigationController = new NavigationController();

        Logger.info("Aplicación CMS iniciada");
        Logger.info("Cargando componentes principales");

        MainFrame mainFrame = new MainFrame(
                authController,
                contentController,
                categoryController,
                searchController,
                reportController,
                navigationController
        );

        mainFrame.setVisible(true);
        Logger.info("Interfaz gráfica lista");
    }
}