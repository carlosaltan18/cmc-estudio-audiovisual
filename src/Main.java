import javax.swing.SwingUtilities;
import util.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                initializeApplication();
            } catch (Exception e) {
                Logger.error("Error al inicializar la aplicación: " + e.getMessage());
            }
        });
    }

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
