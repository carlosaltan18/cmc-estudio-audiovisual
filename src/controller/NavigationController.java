package controller;
import model.User;
/**
 * Controlador responsable de la navegación entre diferentes paneles de la aplicación.
 * Gestiona la sesión del usuario actual y el panel activo.
 * @author Carlos
 * @version 1.0
 */
public class NavigationController {
    private User currentUser;
    private String currentPanel;
    /**
     * Construye un NavigationController vacío sin usuario ni panel activo.
     * Inicializa el usuario actual a null y el panel activo a "login".
     * 
     */
    public NavigationController() {
        this.currentUser = null;
        this.currentPanel = "login";
    }
    /**
     * Establece el usuario actual de la sesión. 
     * @param user  Usuario que inicia sesión
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    /**}
     * Obtiene el usuario actual de la sesión.
     * @return Usuario actualmente autenticado
     */
    public User getCurrentUser() {
        return currentUser;
    }
    /**
     * Navega al panel especificado.
     * @param panelName Nombre del panel al que se desea navegar
    */
    public void navigateTo(String panelName) {
        this.currentPanel = panelName;
    }
    /**
     * Obtiene el nombre del panel actualmente activo.
     * @return Nombre del panel activo
     */
    public String getCurrentPanel() {
        return currentPanel;
    }
    /**
     * Verifica si hay un usuario actualmente autenticado.
     * @return true si hay un usuario autenticado, false en caso contrario
     */
    public boolean isUserLoggedIn() {
        return currentUser != null;
    }
}
