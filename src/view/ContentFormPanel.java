package view;

import javax.swing.*;
import java.awt.*;

public class ContentFormPanel extends JPanel {
    private final ContentController contentController;
    private final CategoryController categoryController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;
    private JComboBox<String> typeCombo;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JPanel dynamicPanel;
    private CardLayout dynamicLayout;

    public ContentFormPanel(ContentController contentController,
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

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel typeLabel = new JLabel("Tipo de Contenido:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(typeLabel, gbc);

        typeCombo = new JComboBox<>(new String[]{"Artículo", "Video", "Imagen"});
        typeCombo.addActionListener(e -> updateDynamicFields());
        gbc.gridx = 1;
        formPanel.add(typeCombo, gbc);

        JLabel titleLabel = new JLabel("Título:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(titleLabel, gbc);

        titleField = new JTextField(30);
        gbc.gridx = 1;
        formPanel.add(titleField, gbc);

        JLabel descLabel = new JLabel("Descripción:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(descLabel, gbc);

        descriptionArea = new JTextArea(5, 30);
        descriptionArea.setLineWrap(true);
        gbc.gridx = 1;
        gbc.gridheight = 2;
        formPanel.add(new JScrollPane(descriptionArea), gbc);

        dynamicLayout = new CardLayout();
        dynamicPanel = new JPanel(dynamicLayout);
        dynamicPanel.add(createArticlePanel(), "article");
        dynamicPanel.add(createVideoPanel(), "video");
        dynamicPanel.add(createImagePanel(), "image");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        formPanel.add(dynamicPanel, gbc);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");

        saveButton.addActionListener(e -> saveContent());
        cancelButton.addActionListener(e -> mainFrame.showDashboard());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridy = 5;
        formPanel.add(buttonPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createArticlePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel contentLabel = new JLabel("Contenido del Artículo:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(contentLabel, gbc);

        JTextArea contentArea = new JTextArea(5, 40);
        contentArea.setName("articleContent");
        gbc.gridy = 1;
        panel.add(new JScrollPane(contentArea), gbc);

        return panel;
    }

    private JPanel createVideoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel urlLabel = new JLabel("URL del Video:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(urlLabel, gbc);

        JTextField urlField = new JTextField(30);
        urlField.setName("videoUrl");
        gbc.gridx = 1;
        panel.add(urlField, gbc);

        JLabel durationLabel = new JLabel("Duración (minutos):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(durationLabel, gbc);

        JTextField durationField = new JTextField(10);
        durationField.setName("videoDuration");
        gbc.gridx = 1;
        panel.add(durationField, gbc);

        JLabel resolutionLabel = new JLabel("Resolución:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(resolutionLabel, gbc);

        JComboBox<String> resolutionCombo = new JComboBox<>(new String[]{"720p", "1080p", "4K"});
        resolutionCombo.setName("videoResolution");
        gbc.gridx = 1;
        panel.add(resolutionCombo, gbc);

        return panel;
    }

    private JPanel createImagePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel urlLabel = new JLabel("URL de la Imagen:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(urlLabel, gbc);

        JTextField urlField = new JTextField(30);
        urlField.setName("imageUrl");
        gbc.gridx = 1;
        panel.add(urlField, gbc);

        JLabel widthLabel = new JLabel("Ancho (px):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(widthLabel, gbc);

        JTextField widthField = new JTextField(10);
        widthField.setName("imageWidth");
        gbc.gridx = 1;
        panel.add(widthField, gbc);

        JLabel heightLabel = new JLabel("Alto (px):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(heightLabel, gbc);

        JTextField heightField = new JTextField(10);
        heightField.setName("imageHeight");
        gbc.gridx = 1;
        panel.add(heightField, gbc);

        return panel;
    }

    private void updateDynamicFields() {
        String selected = (String) typeCombo.getSelectedItem();
        switch (selected) {
            case "Artículo" -> dynamicLayout.show(dynamicPanel, "article");
            case "Video" -> dynamicLayout.show(dynamicPanel, "video");
            case "Imagen" -> dynamicLayout.show(dynamicPanel, "image");
        }
    }

    private void saveContent() {
        String title = titleField.getText();
        String description = descriptionArea.getText();

        if (title.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User author = navigationController.getCurrentUser();
        String type = (String) typeCombo.getSelectedItem();

        try {
            switch (type) {
                case "Artículo" -> {
                    String content = getFieldValue("articleContent");
                    contentController.createArticle(IDGenerator.generate("art"), title, description, content, author);
                    Logger.success("Artículo creado: " + title);
                }
                case "Video" -> {
                    String url = getFieldValue("videoUrl");
                    int duration = Integer.parseInt(getFieldValue("videoDuration"));
                    String resolution = getFieldValue("videoResolution");
                    contentController.createVideo(IDGenerator.generate("vid"), title, description, url, duration, resolution, author);
                    Logger.success("Video creado: " + title);
                }
                case "Imagen" -> {
                    String url = getFieldValue("imageUrl");
                    int width = Integer.parseInt(getFieldValue("imageWidth"));
                    int height = Integer.parseInt(getFieldValue("imageHeight"));
                    contentController.createImage(IDGenerator.generate("img"), title, description, url, width, height, 0.0, author);
                    Logger.success("Imagen creada: " + title);
                }
            }
            JOptionPane.showMessageDialog(this, "Contenido guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            reset();
            mainFrame.showContentManagement();
        } catch (NumberFormatException e) {
            Logger.error("Error en formato de datos: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Por favor verifique los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getFieldValue(String fieldName) {
        for (Component comp : dynamicPanel.getComponents()) {
            if (comp instanceof JPanel) {
                for (Component subComp : ((JPanel) comp).getComponents()) {
                    if (subComp instanceof JTextField && fieldName.equals(subComp.getName())) {
                        return ((JTextField) subComp).getText();
                    } else if (subComp instanceof JTextArea && fieldName.equals(subComp.getName())) {
                        return ((JTextArea) subComp).getText();
                    } else if (subComp instanceof JComboBox && fieldName.equals(subComp.getName())) {
                        return ((JComboBox<?>) subComp).getSelectedItem().toString();
                    } else if (subComp instanceof JScrollPane) {
                        Component viewport = ((JScrollPane) subComp).getViewport().getView();
                        if (viewport instanceof JTextArea && fieldName.equals(viewport.getName())) {
                            return ((JTextArea) viewport).getText();
                        }
                    }
                }
            }
        }
        return "";
    }

    public void reset() {
        titleField.setText("");
        descriptionArea.setText("");
        typeCombo.setSelectedIndex(0);
        updateDynamicFields();
    }
}
