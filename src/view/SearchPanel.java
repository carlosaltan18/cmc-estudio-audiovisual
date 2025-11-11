package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchPanel extends JPanel {
    private final SearchController searchController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;
    private JTextField searchField;
    private JComboBox<String> typeFilter;
    private JComboBox<String> stateFilter;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    public SearchPanel(SearchController searchController,
                      NavigationController navigationController,
                      MainFrame mainFrame) {
        this.searchController = searchController;
        this.navigationController = navigationController;
        this.mainFrame = mainFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        JPanel resultsPanel = createResultsPanel();
        add(resultsPanel, BorderLayout.CENTER);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(245, 245, 245));

        JLabel searchLabel = new JLabel("Buscar:");
        searchField = new JTextField(20);

        JLabel typeLabel = new JLabel("Tipo:");
        typeFilter = new JComboBox<>(new String[]{"Todos", "Artículo", "Video", "Imagen"});

        JLabel stateLabel = new JLabel("Estado:");
        stateFilter = new JComboBox<>(new String[]{"Todos", "Publicado", "Borrador"});

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

    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"Tipo", "Título", "Autor", "Estado", "Creado"};
        tableModel = new DefaultTableModel(columns, 0);
        resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void performSearch() {
        String keyword = searchField.getText();
        String typeStr = (String) typeFilter.getSelectedItem();
        String stateStr = (String) stateFilter.getSelectedItem();

        ContentType type = null;
        if (!typeStr.equals("Todos")) {
            type = switch (typeStr) {
                case "Artículo" -> ContentType.ARTICLE;
                case "Video" -> ContentType.VIDEO;
                case "Imagen" -> ContentType.IMAGE;
                default -> null;
            };
        }

        ContentState state = null;
        if (!stateStr.equals("Todos")) {
            state = stateStr.equals("Publicado") ? ContentState.PUBLISHED : ContentState.DRAFT;
        }

        List<Content> results = searchController.advancedSearch(keyword, type, state);
        displayResults(results);
        Logger.info("Búsqueda realizada: " + keyword + " | Resultados: " + results.size());
    }

    private void displayResults(List<Content> contents) {
        tableModel.setRowCount(0);

        for (Content c : contents) {
            String type = c instanceof Article ? "Artículo" : (c instanceof Video ? "Video" : "Imagen");
            tableModel.addRow(new Object[]{
                    type,
                    c.getTitle(),
                    c.getAuthor().getUsername(),
                    c.getState().getDisplayName(),
                    c.getCreatedAt()
            });
        }
    }

    public void reset() {
        searchField.setText("");
        typeFilter.setSelectedIndex(0);
        stateFilter.setSelectedIndex(0);
        tableModel.setRowCount(0);
    }
}
