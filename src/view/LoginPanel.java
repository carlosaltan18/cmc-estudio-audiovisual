package view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private final AuthenticationController authController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;

    public LoginPanel(AuthenticationController authController,
                     NavigationController navigationController,
                     MainFrame mainFrame) {
        this.authController = authController;
        this.navigationController = navigationController;
        this.mainFrame = mainFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("CMS - Estudio de Grabación Audiovisual");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("Iniciar Sesión");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        add(subtitleLabel, gbc);

        JLabel usernameLabel = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> handleLogin());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        JLabel infoLabel = new JLabel("<html>Usuarios de prueba:<br/>admin1 | editor1 | visitor1</html>");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        gbc.gridy = 5;
        add(infoLabel, gbc);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = authController.login(username);
        
        if (user != null) {
            navigationController.setCurrentUser(user);
            Logger.success("Usuario " + username + " autenticado con rol: " + user.getRole().getDisplayName());
            mainFrame.showDashboard();
        } else {
            Logger.error("Intento de login fallido para usuario: " + username);
            JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}