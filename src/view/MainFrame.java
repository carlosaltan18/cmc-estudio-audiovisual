package view;

import javax.swing.*;
import java.awt.*;

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

    private void initializeUI() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(authController, navigationController, this);
        dashboardPanel = new DashboardPanel(contentController, categoryController, navigationController, this);
        contentManagementPanel = new ContentManagementPanel(contentController, searchController, navigationController, this);
        contentFormPanel = new ContentFormPanel(contentController, categoryController, navigationController, this);
        searchPanel = new SearchPanel(searchController, navigationController, this);
        reportPanel = new ReportPanel(reportController, navigationController, this);

        contentPanel.add(loginPanel, "login");
        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(contentManagementPanel, "content");
        contentPanel.add(contentFormPanel, "form");
        contentPanel.add(searchPanel, "search");
        contentPanel.add(reportPanel, "report");

        add(contentPanel);
        showLoginPanel();
    }

    public void showLoginPanel() {
        cardLayout.show(contentPanel, "login");
        setJMenuBar(null);
    }

    public void showDashboard() {
        initializeMenuBar();
        dashboardPanel.refresh();
        cardLayout.show(contentPanel, "dashboard");
    }

    public void showContentManagement() {
        contentManagementPanel.refresh();
        cardLayout.show(contentPanel, "content");
    }

    public void showContentForm() {
        contentFormPanel.reset();
        cardLayout.show(contentPanel, "form");
    }

    public void showSearch() {
        searchPanel.reset();
        cardLayout.show(contentPanel, "search");
    }

    public void showReports() {
        reportPanel.refresh();
        cardLayout.show(contentPanel, "report");
    }

    private void initializeMenuBar() {
        menuBar = new JMenuBar();

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

        JMenu menuUsuario = new JMenu("Usuario");
        JMenuItem itemPerfil = new JMenuItem("Mi Perfil");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");

        itemPerfil.addActionListener(e -> showUserProfile());
        itemCerrarSesion.addActionListener(e -> logout());

        menuUsuario.add(itemPerfil);
        menuUsuario.addSeparator();
        menuUsuario.add(itemCerrarSesion);

        if (navigationController.getCurrentUser() != null &&
            navigationController.getCurrentUser().getRole() == UserRole.ADMIN) {
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

    private void showUserProfile() {
        User currentUser = navigationController.getCurrentUser();
        if (currentUser != null) {
            String message = String.format("Usuario: %s\nEmail: %s\nRol: %s\nCreado: %s",
                    currentUser.getUsername(),
                    currentUser.getEmail(),
                    currentUser.getRole().getDisplayName(),
                    currentUser.getCreatedAt());
            JOptionPane.showMessageDialog(this, message, "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showUserManagement() {
        JDialog dialog = new JDialog(this, "Gestionar Usuarios", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"ID", "Usuario", "Email", "Rol"};
        Object[][] data = new Object[authController.getAllUsers().size()][4];

        int row = 0;
        for (User user : authController.getAllUsers()) {
            data[row][0] = user.getId();
            data[row][1] = user.getUsername();
            data[row][2] = user.getEmail();
            data[row][3] = user.getRole().getDisplayName();
            row++;
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void logout() {
        navigationController.setCurrentUser(null);
        showLoginPanel();
        JOptionPane.showMessageDialog(this, "Sesión cerrada correctamente", "Logout", JOptionPane.INFORMATION_MESSAGE);
    }
}
