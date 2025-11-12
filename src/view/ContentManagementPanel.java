package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import controller.*;
import model.Content;
import java.util.List;
import util.Logger;
import model.User;
import model.Article;
import model.Video;

/**
 * Panel para la gestión y administración del contenido en el CMS.
 * Proporciona una interfaz tabular para visualizar, crear, editar, eliminar y publicar contenidos.
 * Soporta diferentes tipos de contenido: artículos, videos e imágenes.
 * 
 * @author Carlos
 * @version 1.0
 */
public class ContentManagementPanel extends JPanel {
    private final ContentController contentController;
    private final SearchController searchController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;
    private JTable contentsTable;
    private DefaultTableModel tableModel;

    /**
     * Construye un ContentManagementPanel con los controladores necesarios.
     * Inicializa la interfaz gráfica del panel de gestión de contenidos.
     * 
     * @param contentController Controlador de contenidos
     * @param searchController Controlador de búsqueda
     * @param navigationController Controlador de navegación
     * @param mainFrame Frame principal de la aplicación
     */
    public ContentManagementPanel(ContentController contentController,
                                 SearchController searchController,
                                 NavigationController navigationController,
                                 MainFrame mainFrame) {
        this.contentController = contentController;
        this.searchController = searchController;
        this.navigationController = navigationController;
        this.mainFrame = mainFrame;
        initializeUI();
    }

    /**
     * Inicializa la interfaz gráfica del panel de gestión.
     * Crea la barra de herramientas con botones de acciones y la tabla de contenidos.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel toolbarPanel = createToolbar();
        add(toolbarPanel, BorderLayout.NORTH);

        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
    }

    /**
     * Crea la barra de herramientas con los botones de acción.
     * Incluye botones para crear nuevo contenido, editar, eliminar y publicar.
     * 
     * @return JPanel con los botones de acción
     */
    private JPanel createToolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(245, 245, 245));

        /**
         * Botón para crear nuevo contenido.
         * Abre el formulario de creación de contenido.
         */
        JButton nuevoButton = new JButton("+ Nuevo Contenido");
        nuevoButton.addActionListener(e -> mainFrame.showContentForm());

        /**
         * Botón para editar contenido seleccionado.
         * Permite modificar un contenido existente.
         */
        JButton editarButton = new JButton("✎ Editar");
        editarButton.addActionListener(e -> editarContenido());

        /**
         * Botón para eliminar contenido seleccionado.
         * Requiere confirmación y permisos adecuados.
         */
        JButton eliminarButton = new JButton("🗑 Eliminar");
        eliminarButton.addActionListener(e -> eliminarContenido());

        /**
         * Botón para publicar contenido seleccionado.
         * Cambia el estado del contenido a publicado.
         */
        JButton publicarButton = new JButton("📤 Publicar");
        publicarButton.addActionListener(e -> publicarContenido());

        panel.add(nuevoButton);
        panel.add(editarButton);
        panel.add(eliminarButton);
        panel.add(publicarButton);

        return panel;
    }

    /**
     * Crea el panel con la tabla de contenidos.
     * La tabla muestra ID, tipo, título, autor, estado y fecha de creación.
     * 
     * @return JPanel con la tabla de contenidos
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"ID", "Tipo", "Título", "Autor", "Estado", "Creado"};
        tableModel = new DefaultTableModel(columns, 0);
        contentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contentsTable);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Edita el contenido seleccionado en la tabla.
     * Valida que haya una fila seleccionada antes de proceder.
     * 
     * @throws IllegalArgumentException si no hay contenido seleccionado
     */
    private void editarContenido() {
        int selectedRow = contentsTable.getSelectedRow();
        if (selectedRow != -1) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de edición en desarrollo", "Editar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contenido para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Elimina el contenido seleccionado de la tabla.
     * Valida permisos del usuario y solicita confirmación antes de eliminar.
     * 
     * @throws IllegalArgumentException si no hay contenido seleccionado
     * @throws SecurityException si el usuario no tiene permisos de eliminación
     */
    private void eliminarContenido() {
        int selectedRow = contentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un contenido para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User currentUser = navigationController.getCurrentUser();
        if (currentUser != null && !currentUser.hasPermission("delete_any")) {
            JOptionPane.showMessageDialog(this, "No tiene permisos para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este contenido?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String contentId = (String) tableModel.getValueAt(selectedRow, 0);
            contentController.deleteContent(contentId);
            Logger.success("Contenido " + contentId + " eliminado");
            refresh();
        }
    }

    /**
     * Publica el contenido seleccionado en la tabla.
     * Cambia el estado del contenido a publicado y actualiza la vista.
     * 
     * @throws IllegalArgumentException si no hay contenido seleccionado
     */
    private void publicarContenido() {
        int selectedRow = contentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un contenido para publicar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String contentId = (String) tableModel.getValueAt(selectedRow, 0);
        contentController.publishContent(contentId);
        Logger.success("Contenido " + contentId + " publicado");
        refresh();
    }

    /**
     * Actualiza la tabla con todos los contenidos del sistema.
     * Obtiene los contenidos del controlador y los muestra en la tabla,
     * indicando el tipo, título, autor, estado y fecha de creación.
     */
    public void refresh() {
        List<Content> contents = contentController.getAllContents();
        tableModel.setRowCount(0);

        for (Content c : contents) {
            if (c != null && c.getAuthor() != null) {
                String type = c instanceof Article ? "Artículo" : (c instanceof Video ? "Video" : "Imagen");
                String authorName = c.getAuthor().getUsername() != null ? c.getAuthor().getUsername() : "Desconocido";
                String stateName = c.getState() != null ? c.getState().toString() : "Sin estado";
                
                tableModel.addRow(new Object[]{
                        c.getId(),
                        type,
                        c.getTitle(),
                        authorName,
                        stateName,
                        c.getCreatedAt()
                });
            }
        }
    }
}