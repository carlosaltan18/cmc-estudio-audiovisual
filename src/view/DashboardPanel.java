package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.*;
import model.enums.ContentState;
import controller.*;
import java.util.List;

/**
 * Panel de panel de control (dashboard) para el CMS.
 * Proporciona una vista general de estadísticas de contenido y resumen de actividades recientes.
 * Muestra el total de contenidos, contenidos publicados, borradores y una tabla de contenidos recientes.
 * 
 * @author Carlos
 * @version 1.0
 */
public class DashboardPanel extends JPanel {
    private final ContentController contentController;
    private final CategoryController categoryController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;
    private JLabel totalLabel;
    private JLabel publishedLabel;
    private JLabel draftLabel;
    private JTable recentTable;

    /**
     * Construye un DashboardPanel con los controladores necesarios.
     * Inicializa la interfaz gráfica del panel de control.
     * 
     * @param contentController Controlador de contenidos
     * @param categoryController Controlador de categorías
     * @param navigationController Controlador de navegación
     * @param mainFrame Frame principal de la aplicación
     */
    public DashboardPanel(ContentController contentController,
                         CategoryController categoryController,
                         NavigationController navigationController,
                         MainFrame mainFrame) {
        this.contentController = contentController;
        this.categoryController = categoryController;
        this.navigationController = navigationController;
        this.mainFrame = mainFrame;
        initializeUI();
    }

    /**
     * Inicializa la interfaz gráfica del panel de control.
     * Crea el panel superior con bienvenida, panel de estadísticas y tabla de contenidos recientes.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        JPanel statsPanel = createStatsPanel();
        add(statsPanel, BorderLayout.CENTER);

        JPanel recentPanel = createRecentPanel();
        add(recentPanel, BorderLayout.SOUTH);
    }

    /**
     * Crea el panel superior con el mensaje de bienvenida.
     * Muestra el nombre de usuario, rol y un mensaje de bienvenida personalizado.
     * Verifica que el usuario no sea nulo antes de acceder a sus propiedades.
     * 
     * @return JPanel configurado con el mensaje de bienvenida
     */
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 100, 200));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        // ✅ Verificar que el usuario NO es null
        User currentUser = navigationController.getCurrentUser();
        if (currentUser != null) {
            String roleName = currentUser.getRole() != null ? currentUser.getRole().getDisplayName() : "Sin rol";
            JLabel welcomeLabel = new JLabel("Bienvenido, " + currentUser.getUsername() + " (" + roleName + ")");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            welcomeLabel.setForeground(Color.WHITE);
            panel.add(welcomeLabel);
        } else {
            JLabel welcomeLabel = new JLabel("Usuario no identificado");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            welcomeLabel.setForeground(Color.WHITE);
            panel.add(welcomeLabel);
        }
        
        return panel;
    }

    /**
     * Crea el panel de estadísticas del dashboard.
     * Muestra tres tarjetas de estadísticas: total de contenidos, contenidos publicados y borradores.
     * Las tarjetas tienen colores diferenciados para mejor visualización.
     * 
     * @return JPanel configurado con las tarjetas de estadísticas
     */
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 0));
        panel.setBackground(new Color(245, 245, 245));

        panel.add(createStatCard("Total de Contenidos", "0", new Color(100, 150, 255)));
        panel.add(createStatCard("Publicados", "0", new Color(100, 255, 150)));
        panel.add(createStatCard("Borradores", "0", new Color(255, 200, 100)));

        return panel;
    }

    /**
     * Crea una tarjeta de estadística individual.
     * Cada tarjeta muestra una etiqueta y un valor numérico con un color de fondo específico.
     * Las referencias a las etiquetas se almacenan globalmente para actualización posterior.
     * 
     * @param label Etiqueta descriptiva de la estadística
     * @param value Valor numérico a mostrar (típicamente "0" en inicialización)
     * @param color Color de fondo de la tarjeta
     * @return JPanel configurado como tarjeta de estadística
     */
    private JPanel createStatCard(String label, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createRaisedBevelBorder());

        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Arial", Font.BOLD, 12));
        labelComp.setForeground(Color.WHITE);

        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Arial", Font.BOLD, 28));
        valueComp.setForeground(Color.WHITE);
        valueComp.setHorizontalAlignment(JLabel.CENTER);

        card.add(labelComp, BorderLayout.NORTH);
        card.add(valueComp, BorderLayout.CENTER);

        // Almacenar referencias para actualizaciones
        if (label.contains("Total")) {
            totalLabel = valueComp;
        } else if (label.contains("Publicados")) {
            publishedLabel = valueComp;
        } else if (label.contains("Borradores")) {
            draftLabel = valueComp;
        }

        return card;
    }

    /**
     * Crea el panel con los contenidos recientes.
     * Muestra una tabla con hasta 5 contenidos más recientes,
     * incluyendo tipo, título, autor, estado y opciones de acciones.
     * 
     * @return JPanel configurado con la tabla de contenidos recientes
     */
    private JPanel createRecentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Contenidos Recientes"));
        panel.setBackground(Color.WHITE);

        String[] columns = {"Tipo", "Título", "Autor", "Estado", "Acciones"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        recentTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(recentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Actualiza todas las estadísticas del dashboard.
     * Recalcula el total de contenidos, número de publicados y borradores.
     * Actualiza la tabla de contenidos recientes con hasta 5 elementos.
     * Verifica que los autores y estados no sean nulos antes de acceder a ellos.
     */
    public void refresh() {
        List<Content> contents = contentController.getAllContents();
        
        totalLabel.setText(String.valueOf(contents.size()));
        publishedLabel.setText(String.valueOf(contents.stream()
                .filter(c -> c != null && c.getState() == ContentState.PUBLISHED)
                .count()));
        draftLabel.setText(String.valueOf(contents.stream()
                .filter(c -> c != null && c.getState() == ContentState.DRAFT)
                .count()));

        DefaultTableModel model = (DefaultTableModel) recentTable.getModel();
        model.setRowCount(0);

        int limit = Math.min(5, contents.size());
        for (int i = 0; i < limit; i++) {
            Content c = contents.get(i);
            if (c != null && c.getAuthor() != null && c.getState() != null) {
                String type = c instanceof Article ? "📄" : (c instanceof Video ? "🎥" : "🖼️");
                String authorName = c.getAuthor().getUsername() != null ? c.getAuthor().getUsername() : "Desconocido";
                String stateName = c.getState().getDisplayName() != null ? c.getState().getDisplayName() : "Sin estado";
                
                model.addRow(new Object[]{type, c.getTitle(), authorName, stateName, "..."});
            }
        }
    }
}