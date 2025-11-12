package view;

import javax.swing.*;
import java.awt.*;
import model.*;
import model.enums.UserRole;
import controller.*;

/**
 * Frame principal de la aplicación CMS para Estudio de Grabación Audiovisual.
 * Gestiona la navegación entre diferentes paneles y la presentación de la interfaz gráfica.
 * Proporciona un contenedor central para todos los paneles de la aplicación.
 * 
 * @author Carlos
 * @version 1.0
 */
public class MainFrame extends JFrame {
    private final NavigationController navigationController;
    private final AuthenticationController authController;
    private final ContentController contentController;
    private final CategoryController categoryController;
    private final SearchController searchController;
    private final ReportController reportController;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private LoginPanel loginPanel;
    private DashboardPanel dashboardPanel;
    private ContentManagementPanel contentManagementPanel;
    private ContentFormPanel contentFormPanel;
    private SearchPanel searchPanel;
    private ReportPanel reportPanel;
    private JMenuBar menuBar;

    /**
     * Construye un MainFrame con todos los controladores necesarios.
     * Inicializa la interfaz gráfica y establece las propiedades de la ventana.
     * 
     * @param authController Controlador de autenticación
     * @param contentController Controlador de contenidos
     * @param categoryController Controlador de categorías
     * @param searchController Controlador de búsqueda
     * @param reportController Controlador de reportes
     * @param navigationController Controlador de navegación
     */
    public MainFrame(AuthenticationController authController,
            ContentController contentController,
            CategoryController categoryController,
            SearchController searchController,
            ReportController reportController,
            NavigationController navigationController) {
        this.authController = authController;
        this.contentController = contentController;
        this.categoryController = categoryController;
        this.searchController = searchController;
        this.reportController = reportController;
        this.navigationController = navigationController;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CMS - Estudio de Grabación Audiovisual");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        initializeUI();
    }

    /**
     * Inicializa la interfaz gráfica del frame principal.
     * Crea el panel de contenido con CardLayout para gestionar la navegación entre paneles.
     * Inicializa todos los paneles excepto el dashboard, que se crea después del login.
     * 
     * <p>Paneles inicializados:
     * <ul>
     *   <li>LoginPanel - Panel de inicio de sesión</li>
     *   <li>ContentManagementPanel - Gestión de contenidos</li>
     *   <li>ContentFormPanel - Formulario de creación/edición de contenido</li>
     *   <li>SearchPanel - Búsqueda de contenidos</li>
     *   <li>ReportPanel - Generación de reportes</li>
     * </ul>
     * </p>
     */
    private void initializeUI() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(authController, navigationController, this);
        dashboardPanel = null;
        contentManagementPanel = new ContentManagementPanel(contentController, searchController, navigationController, this);
        contentFormPanel = new ContentFormPanel(contentController, categoryController, navigationController, this);
        searchPanel = new SearchPanel(searchController, navigationController, this);
        reportPanel = new ReportPanel(reportController, navigationController, this);

        contentPanel.add(loginPanel, "login");
        contentPanel.add(contentManagementPanel, "content");
        contentPanel.add(contentFormPanel, "form");
        contentPanel.add(searchPanel, "search");
        contentPanel.add(reportPanel, "report");

        add(contentPanel);
        showLoginPanel();
    }

    /**
     * Muestra el panel de inicio de sesión.
     * Limpia la barra de menú y oculta todos los demás paneles.
     * Este es el primer panel mostrado cuando se inicia la aplicación.
     */
    public void showLoginPanel() {
        cardLayout.show(contentPanel, "login");
        setJMenuBar(null);
    }

    /**
     * Muestra el panel de control (dashboard).
     * Crea el dashboard por primera vez cuando el usuario inicia sesión.
     * En accesos posteriores, reutiliza la instancia existente.
     * Inicializa la barra de menú e inicializa el contenido del dashboard.
     */
    public void showDashboard() {
        if (dashboardPanel == null) {
            dashboardPanel = new DashboardPanel(contentController, categoryController, navigationController, this);
            contentPanel.add(dashboardPanel, "dashboard");
        }
        
        initializeMenuBar();
        dashboardPanel.refresh();
        cardLayout.show(contentPanel, "dashboard");
    }

    /**
     * Muestra el panel de gestión de contenidos.
     * Actualiza la tabla de contenidos antes de mostrar el panel.
     */
    public void showContentManagement() {
        contentManagementPanel.refresh();
        cardLayout.show(contentPanel, "content");
    }

    /**
     * Muestra el panel de formulario para crear nuevo contenido.
     * Reinicia los campos del formulario antes de mostrar el panel.
     */
    public void showContentForm() {
        contentFormPanel.reset();
        cardLayout.show(contentPanel, "form");
    }

    /**
     * Muestra el panel de búsqueda de contenidos.
     * Reinicia los criterios de búsqueda antes de mostrar el panel.
     */
    public void showSearch() {
        searchPanel.reset();
        cardLayout.show(contentPanel, "search");
    }

    /**
     * Muestra el panel de generación de reportes.
     * Actualiza los datos de los reportes antes de mostrar el panel.
     */
    public void showReports() {
        reportPanel.refresh();
        cardLayout.show(contentPanel, "report");
    }

    /**
     * Inicializa la barra de menú de la aplicación.
     * Crea los menús de navegación, usuario y administración (si el usuario es ADMIN).
     * Se llama después de que el usuario ha iniciado sesión correctamente.
     * 
     * <p>Menús creados:
     * <ul>
     *   <li>Navegación - Acceso a todos los paneles principales</li>
     *   <li>Usuario - Perfil y cerrar sesión</li>
     *   <li>Administración - Solo visible para usuarios con rol ADMIN</li>
     * </ul>
     * </p>
     */
    private void initializeMenuBar() {
        menuBar = new JMenuBar();

        // Menú de Navegación
        JMenu menuNavegacion = new JMenu("Navegación");
        JMenuItem itemDashboard = new JMenuItem("Dashboard");
        JMenuItem itemContenidos = new JMenuItem("Gestionar Contenidos");
        JMenuItem itemNuevo = new JMenuItem("Nuevo Contenido");
        JMenuItem itemBuscar = new JMenuItem("Buscar");
        JMenuItem itemReportes = new JMenuItem("Reportes");

        itemDashboard.addActionListener(e -> showDashboard());
        itemContenidos.addActionListener(e -> showContentManagement());
        itemNuevo.addActionListener(e -> showContentForm());
        itemBuscar.addActionListener(e -> showSearch());
        itemReportes.addActionListener(e -> showReports());

        menuNavegacion.add(itemDashboard);
        menuNavegacion.addSeparator();
        menuNavegacion.add(itemContenidos);
        menuNavegacion.add(itemNuevo);
        menuNavegacion.addSeparator();
        menuNavegacion.add(itemBuscar);
        menuNavegacion.add(itemReportes);

        // Menú de Usuario
        JMenu menuUsuario = new JMenu("Usuario");
        JMenuItem itemPerfil = new JMenuItem("Mi Perfil");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");

        itemPerfil.addActionListener(e -> showUserProfile());
        itemCerrarSesion.addActionListener(e -> logout());

        menuUsuario.add(itemPerfil);
        menuUsuario.addSeparator();
        menuUsuario.add(itemCerrarSesion);

        // Menú de Administración (solo para ADMIN)
        User currentUser = navigationController.getCurrentUser();
        if (currentUser != null && currentUser.getRole() == UserRole.ADMIN) {
            JMenu menuAdmin = new JMenu("Administración");
            JMenuItem itemUsuarios = new JMenuItem("Gestionar Usuarios");
            itemUsuarios.addActionListener(e -> showUserManagement());
            menuAdmin.add(itemUsuarios);
            menuBar.add(menuAdmin);
        }

        menuBar.add(menuNavegacion);
        menuBar.add(menuUsuario);
        setJMenuBar(menuBar);
    }

    /**
     * Muestra un diálogo con la información del perfil del usuario actual.
     * Obtiene el usuario de la sesión actual y muestra sus detalles: nombre, email, rol y fecha de creación.
     * Verifica que el usuario no sea nulo antes de acceder a sus propiedades.
     * 
     * <p>Información mostrada:
     * <ul>
     *   <li>Usuario (nombre de usuario)</li>
     *   <li>Email</li>
     *   <li>Rol</li>
     *   <li>Creado (fecha de creación)</li>
     * </ul>
     * </p>
     */
    private void showUserProfile() {
        User currentUser = navigationController.getCurrentUser();
        if (currentUser != null) {
            String roleName = currentUser.getRole() != null ? currentUser.getRole().getDisplayName() : "Sin rol";
            String message = String.format("Usuario: %s\nEmail: %s\nRol: %s\nCreado: %s",
                    currentUser.getUsername(),
                    currentUser.getEmail(),
                    roleName,
                    currentUser.getCreatedAt());
            JOptionPane.showMessageDialog(this, message, "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No hay usuario autenticado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra un diálogo con la tabla de gestión de usuarios.
     * Solo es accesible para administradores.
     * Obtiene todos los usuarios del sistema y los muestra en una tabla
     * con sus datos: ID, nombre de usuario, email y rol.
     * Valida que los datos del usuario no sean nulos antes de mostrarlos.
     * 
     * <p>Columnas mostradas:
     * <ul>
     *   <li>ID - Identificador único del usuario</li>
     *   <li>Usuario - Nombre de usuario</li>
     *   <li>Email - Correo electrónico</li>
     *   <li>Rol - Rol del usuario en el sistema</li>
     * </ul>
     * </p>
     */
    private void showUserManagement() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = { "ID", "Usuario", "Email", "Rol" };
        
        java.util.List<User> users = authController.getAllUsers();
        Object[][] data = new Object[users.size()][4];

        int row = 0;
        for (User user : users) {
            if (user != null) {
                String roleName = user.getRole() != null ? user.getRole().getDisplayName() : "Sin rol";
                String username = user.getUsername() != null ? user.getUsername() : "N/A";
                String email = user.getEmail() != null ? user.getEmail() : "N/A";
                
                data[row][0] = user.getId();
                data[row][1] = username;
                data[row][2] = email;
                data[row][3] = roleName;
                row++;
            }
        }

        JDialog dialog = new JDialog(this, "Gestionar Usuarios", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    /**
     * Cierra la sesión del usuario actual.
     * Establece el usuario actual en null, muestra el panel de login
     * y muestra un mensaje de confirmación de cierre de sesión.
     */
    private void logout() {
        navigationController.setCurrentUser(null);
        showLoginPanel();
        JOptionPane.showMessageDialog(this, "Sesión cerrada correctamente", "Logout", JOptionPane.INFORMATION_MESSAGE);
    }
}