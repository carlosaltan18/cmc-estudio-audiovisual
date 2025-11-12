package view;

import javax.swing.*;
import java.awt.*;
import controller.*;
import model.User;
import util.Logger;

/**
 * Panel de inicio de sesión para el CMS.
 * Proporciona una interfaz gráfica para que los usuarios autentiquen sus credenciales
 * y accedan al sistema. Valida el usuario y establece la sesión actual.
 * 
 * @author Carlos
 * @version 1.0
 */
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private final AuthenticationController authController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;

    /**
     * Construye un LoginPanel con los controladores necesarios.
     * Inicializa la interfaz gráfica del panel de inicio de sesión.
     * 
     * @param authController Controlador de autenticación para validar credenciales
     * @param navigationController Controlador de navegación para gestionar la sesión del usuario
     * @param mainFrame Frame principal de la aplicación para mostrar el dashboard después del login
     */
    public LoginPanel(AuthenticationController authController,
                     NavigationController navigationController,
                     MainFrame mainFrame) {
        this.authController = authController;
        this.navigationController = navigationController;
        this.mainFrame = mainFrame;
        initializeUI();
    }

    /**
     * Inicializa la interfaz gráfica del panel de login.
     * Crea los componentes visuales incluyendo título, campos de entrada,
     * botón de inicio de sesión e información de usuarios de prueba.
     * Utiliza GridBagLayout para organizar los componentes en la interfaz.
     */
    private void initializeUI() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        /**
         * Etiqueta de título principal.
         * Muestra el nombre de la aplicación con fuente grande y negrita.
         */
        JLabel titleLabel = new JLabel("CMS - Estudio de Grabación Audiovisual");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        /**
         * Etiqueta de subtítulo.
         * Indica que se debe iniciar sesión.
         */
        JLabel subtitleLabel = new JLabel("Iniciar Sesión");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        add(subtitleLabel, gbc);

        /**
         * Etiqueta para el campo de usuario.
         */
        JLabel usernameLabel = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        /**
         * Campo de texto para ingresar el nombre de usuario.
         * Acepta cualquier valor de texto ingresado por el usuario.
         */
        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        /**
         * Etiqueta para el campo de contraseña.
         */
        JLabel passwordLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        /**
         * Campo de contraseña (oculta los caracteres ingresados).
         * Los caracteres se muestran como puntos por seguridad.
         */
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        /**
         * Botón para iniciar sesión.
         * Al hacer clic, valida las credenciales del usuario.
         */
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> handleLogin());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        /**
         * Etiqueta informativa con usuarios de prueba disponibles.
         * Muestra ejemplos de usuarios que se pueden usar para pruebas del sistema.
         */
        JLabel infoLabel = new JLabel("<html>Usuarios de prueba:<br/>admin1 | editor1 | visitor1</html>");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        gbc.gridy = 5;
        add(infoLabel, gbc);
    }

    /**
     * Maneja el evento de inicio de sesión.
     * Valida que el campo de usuario no esté vacío, autentica al usuario
     * a través del controlador de autenticación, y si es exitoso,
     * establece la sesión actual y muestra el dashboard.
     * 
     * <p>Proceso:
     * <ol>
     *   <li>Obtiene el nombre de usuario del campo de entrada</li>
     *   <li>Valida que el campo no esté vacío</li>
     *   <li>Intenta autenticar al usuario mediante AuthenticationController</li>
     *   <li>Si es exitoso, establece el usuario en NavigationController</li>
     *   <li>Muestra el dashboard principal</li>
     *   <li>Si falla, muestra un mensaje de error</li>
     * </ol>
     * </p>
     * 
     * @throws NullPointerException si user es null después de la autenticación
     */
    private void handleLogin() {
        String username = usernameField.getText();
        
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = authController.login(username);
        
        if (user != null) {
            // ✅ Primero establecer el usuario
            navigationController.setCurrentUser(user);
            Logger.success("Usuario " + username + " autenticado con rol: " + user.getRole().getDisplayName());
            
            // ✅ Luego mostrar dashboard (que ahora creará el panel con el usuario disponible)
            mainFrame.showDashboard();
        } else {
            Logger.error("Intento de login fallido para usuario: " + username);
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}