package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.*;
import model.enums.*;
import controller.*;
import util.*;

/**
 * Panel de búsqueda y filtrado de contenidos en el CMS.
 * Proporciona una interfaz para buscar contenidos por palabra clave
 * y filtrar por tipo de contenido y estado de publicación.
 * Muestra los resultados en una tabla con información detallada.
 * 
 * @author Carlos
 * @version 1.0
 */
public class SearchPanel extends JPanel {
    private final SearchController searchController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;
    private JTextField searchField;
    private JComboBox<String> typeFilter;
    private JComboBox<String> stateFilter;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    /**
     * Construye un SearchPanel con los controladores necesarios.
     * Inicializa la interfaz gráfica del panel de búsqueda.
     * 
     * @param searchController Controlador de búsqueda de contenidos
     * @param navigationController Controlador de navegación
     * @param mainFrame Frame principal de la aplicación
     */
    public SearchPanel(SearchController searchController,
                      NavigationController navigationController,
                      MainFrame mainFrame) {
        this.searchController = searchController;
        this.navigationController = navigationController;
        this.mainFrame = mainFrame;
        initializeUI();
    }

    /**
     * Inicializa la interfaz gráfica del panel de búsqueda.
     * Crea el panel de criterios de búsqueda y el panel de resultados.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        JPanel resultsPanel = createResultsPanel();
        add(resultsPanel, BorderLayout.CENTER);
    }

    /**
     * Crea el panel de criterios de búsqueda.
     * Contiene campos para ingresa la palabra clave a buscar,
     * filtros por tipo de contenido (Artículo, Video, Imagen),
     * filtros por estado (Publicado, Borrador) y botón para ejecutar la búsqueda.
     * 
     * @return JPanel configurado con los criterios de búsqueda
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(245, 245, 245));

        /**
         * Etiqueta y campo de texto para la palabra clave de búsqueda.
         */
        JLabel searchLabel = new JLabel("Buscar:");
        searchField = new JTextField(20);

        /**
         * Etiqueta y filtro de tipo de contenido.
         * Opciones: Todos, Artículo, Video, Imagen.
         */
        JLabel typeLabel = new JLabel("Tipo:");
        typeFilter = new JComboBox<>(new String[]{"Todos", "Artículo", "Video", "Imagen"});

        /**
         * Etiqueta y filtro de estado de publicación.
         * Opciones: Todos, Publicado, Borrador.
         */
        JLabel stateLabel = new JLabel("Estado:");
        stateFilter = new JComboBox<>(new String[]{"Todos", "Publicado", "Borrador"});

        /**
         * Botón para ejecutar la búsqueda avanzada.
         * Ejecuta performSearch() al hacer clic.
         */
        JButton searchButton = new JButton("🔍 Buscar");
        searchButton.addActionListener(e -> performSearch());

        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(typeLabel);
        panel.add(typeFilter);
        panel.add(stateLabel);
        panel.add(stateFilter);
        panel.add(searchButton);

        return panel;
    }

    /**
     * Crea el panel que contiene la tabla de resultados de búsqueda.
     * La tabla muestra tipo, título, autor, estado y fecha de creación de cada contenido.
     * 
     * @return JPanel configurado con la tabla de resultados
     */
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        /**
         * Columnas de la tabla de resultados.
         * - Tipo: Tipo de contenido (Artículo, Video, Imagen)
         * - Título: Título del contenido
         * - Autor: Usuario que creó el contenido
         * - Estado: Estado actual del contenido (Publicado, Borrador)
         * - Creado: Fecha de creación del contenido
         */
        String[] columns = {"Tipo", "Título", "Autor", "Estado", "Creado"};
        tableModel = new DefaultTableModel(columns, 0);
        resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Ejecuta la búsqueda avanzada basada en los criterios ingresados.
     * Obtiene los valores del campo de búsqueda y los filtros,
     * convierte los valores de filtro a los enums correspondientes,
     * y ejecuta la búsqueda a través del controlador de búsqueda.
     * Luego muestra los resultados en la tabla y registra la búsqueda en el log.
     * 
     * <p>Criterios de búsqueda:
     * <ul>
     *   <li>keyword - Palabra clave a buscar (obligatoria)</li>
     *   <li>type - Tipo de contenido (opcional, null si es "Todos")</li>
     *   <li>state - Estado de publicación (opcional, null si es "Todos")</li>
     * </ul>
     * </p>
     */
    private void performSearch() {
        String keyword = searchField.getText();
        String typeStr = (String) typeFilter.getSelectedItem();
        String stateStr = (String) stateFilter.getSelectedItem();

        /**
         * Convierte la selección de tipo a enum ContentType.
         * Si la selección es "Todos", type permanece null.
         */
        ContentType type = null;
        if (!typeStr.equals("Todos")) {
            type = switch (typeStr) {
                case "Artículo" -> ContentType.ARTICLE;
                case "Video" -> ContentType.VIDEO;
                case "Imagen" -> ContentType.IMAGE;
                default -> null;
            };
        }

        /**
         * Convierte la selección de estado a enum ContentState.
         * Si la selección es "Todos", state permanece null.
         */
        ContentState state = null;
        if (!stateStr.equals("Todos")) {
            state = stateStr.equals("Publicado") ? ContentState.PUBLISHED : ContentState.DRAFT;
        }

        /**
         * Ejecuta la búsqueda avanzada y obtiene los resultados.
         */
        List<Content> results = searchController.advancedSearch(keyword, type, state);
        displayResults(results);
        Logger.info("Búsqueda realizada: " + keyword + " | Resultados: " + results.size());
    }

    /**
     * Muestra los resultados de la búsqueda en la tabla.
     * Convierte cada contenido a una fila en la tabla,
     * extrayendo tipo, título, autor, estado y fecha de creación.
     * Valida que los datos no sean nulos antes de acceder a ellos.
     * 
     * @param contents Lista de contenidos a mostrar en la tabla
     * @throws NullPointerException si algún campo del contenido es null sin validación previa
     */
    private void displayResults(List<Content> contents) {
        tableModel.setRowCount(0);

        for (Content c : contents) {
            if (c != null && c.getAuthor() != null && c.getState() != null) {
                String type = c instanceof Article ? "Artículo" : (c instanceof Video ? "Video" : "Imagen");
                String authorName = c.getAuthor().getUsername() != null ? c.getAuthor().getUsername() : "Desconocido";
                String stateName = c.getState().getDisplayName() != null ? c.getState().getDisplayName() : "Sin estado";
                
                tableModel.addRow(new Object[]{
                        type,
                        c.getTitle(),
                        authorName,
                        stateName,
                        c.getCreatedAt()
                });
            }
        }
    }

    /**
     * Reinicia todos los campos del panel de búsqueda a sus valores por defecto.
     * Limpia el campo de búsqueda, restaura los filtros a "Todos"
     * y vacía la tabla de resultados.
     */
    public void reset() {
        searchField.setText("");
        typeFilter.setSelectedIndex(0);
        stateFilter.setSelectedIndex(0);
        tableModel.setRowCount(0);
    }
}