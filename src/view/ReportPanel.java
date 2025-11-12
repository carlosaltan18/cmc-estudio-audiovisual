package view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import util.*;
import controller.*;
import model.Report;

public class ReportPanel extends JPanel {
    private final ReportController reportController;
    private final NavigationController navigationController;
    private final MainFrame mainFrame;
    private JTextArea reportArea;

    public ReportPanel(ReportController reportController,
                      NavigationController navigationController,
                      MainFrame mainFrame) {
        this.reportController = reportController;
        this.navigationController = navigationController;
        this.mainFrame = mainFrame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.NORTH);

        JPanel reportPanel = createReportPanel();
        add(reportPanel, BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(245, 245, 245));

        JButton generalButton = new JButton("Reporte General");
        JButton typeButton = new JButton("Por Tipo");
        JButton authorButton = new JButton("Por Autor");
        JButton statsButton = new JButton("EstadÃ­sticas");

        generalButton.addActionListener(e -> displayReport(reportController.generateGeneralReport()));
        typeButton.addActionListener(e -> displayReport(reportController.generateReportByType()));
        authorButton.addActionListener(e -> displayReport(reportController.generateReportByAuthor()));
        statsButton.addActionListener(e -> displayReport(reportController.generateStatistics()));

        panel.add(generalButton);
        panel.add(typeButton);
        panel.add(authorButton);
        panel.add(statsButton);

        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(reportArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void displayReport(Report report) {
        StringBuilder sb = new StringBuilder();
        sb.append("â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•\n");
        sb.append("REPORTE: ").append(report.getType()).append("\n");
        sb.append("Generado: ").append(report.getGeneratedAt()).append("\n");
        sb.append("â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•\n\n");

        Map<String, Object> data = report.getData();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            if (entry.getValue() instanceof java.util.List) {
                sb.append("\n");
                for (Object item : (java.util.List<?>) entry.getValue()) {
                    sb.append("  â€¢ ").append(item).append("\n");
                }
            } else {
                sb.append(entry.getValue()).append("\n");
            }
        }

        sb.append("\nâ•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•\n");
        reportArea.setText(sb.toString());
        Logger.success("Reporte generado: " + report.getType());
    }

    public void refresh() {
        displayReport(reportController.generateGeneralReport());
    }
}