package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JPanel {
    private final ContentController contentController;
    private final CategoryController categoryController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;
    private JLabel totalLabel;
    private JLabel publishedLabel;
    private JLabel draftLabel;
    private JTable recentTable;

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

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 100, 200));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        User currentUser = navigationController.getCurrentUser();
        JLabel welcomeLabel = new JLabel("Bienvenido, " + currentUser.getUsername() + " (" + currentUser.getRole().getDisplayName() + ")");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);
        panel.add(welcomeLabel);
        
        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 0));
        panel.setBackground(new Color(245, 245, 245));

        panel.add(createStatCard("Total de Contenidos", "0", new Color(100, 150, 255)));
        panel.add(createStatCard("Publicados", "0", new Color(100, 255, 150)));
        panel.add(createStatCard("Borradores", "0", new Color(255, 200, 100)));

        return panel;
    }

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

        if (label.contains("Total")) {
            totalLabel = valueComp;
        } else if (label.contains("Publicados")) {
            publishedLabel = valueComp;
        } else if (label.contains("Borradores")) {
            draftLabel = valueComp;
        }

        return card;
    }

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

    public void refresh() {
        List<Content> contents = contentController.getAllContents();
        
        totalLabel.setText(String.valueOf(contents.size()));
        publishedLabel.setText(String.valueOf(contents.stream().filter(c -> c.getState() == ContentState.PUBLISHED).count()));
        draftLabel.setText(String.valueOf(contents.stream().filter(c -> c.getState() == ContentState.DRAFT).count()));

        DefaultTableModel model = (DefaultTableModel) recentTable.getModel();
        model.setRowCount(0);

        int limit = Math.min(5, contents.size());
        for (int i = 0; i < limit; i++) {
            Content c = contents.get(i);
            String type = c instanceof Article ? "📄" : (c instanceof Video ? "🎥" : "🖼️");
            model.addRow(new Object[]{type, c.getTitle(), c.getAuthor().getUsername(), c.getState().getDisplayName(), "..."});
        }
    }
}
