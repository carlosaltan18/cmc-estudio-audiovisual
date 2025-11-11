package controller;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import model.enums.*;

public class ReportController {
    private final ContentController contentController;

    public ReportController(ContentController contentController) {
        this.contentController = contentController;
    }

    public Report generateGeneralReport() {
        Report report = new Report(UUID.randomUUID().toString(), "Reporte General");
        Map<String, Object> data = new HashMap<>();

        List<Content> allContents = contentController.getAllContents();
        data.put("totalContents", allContents.size());
        data.put("contents", allContents.stream().map(Content::generateReport).collect(Collectors.toList()));

        report.setData(data);
        return report;
    }

    public Report generateReportByType() {
        Report report = new Report(UUID.randomUUID().toString(), "Reporte por Tipo");
        Map<String, Object> data = new HashMap<>();

        data.put("articles", contentController.getContentByType(Article.class).size());
        data.put("videos", contentController.getContentByType(Video.class).size());
        data.put("images", contentController.getContentByType(Image.class).size());

        report.setData(data);
        return report;
    }

    public Report generateReportByAuthor() {
        Report report = new Report(UUID.randomUUID().toString(), "Reporte por Autor");
        Map<String, Object> data = new HashMap<>();

        Map<String, Long> authorCounts = contentController.getAllContents().stream()
                .collect(Collectors.groupingBy(
                        c -> c.getAuthor().getUsername(),
                        Collectors.counting()
                ));

        data.put("byAuthor", authorCounts);
        report.setData(data);
        return report;
    }

    public Report generateStatistics() {
        Report report = new Report(UUID.randomUUID().toString(), "Estadísticas");
        Map<String, Object> data = new HashMap<>();

        List<Content> all = contentController.getAllContents();
        long published = all.stream().filter(c -> c.getState() == ContentState.PUBLISHED).count();
        long draft = all.stream().filter(c -> c.getState() == ContentState.DRAFT).count();

        data.put("totalContents", all.size());
        data.put("publishedContents", published);
        data.put("draftContents", draft);
        data.put("articleCount", contentController.getContentByType(Article.class).size());
        data.put("videoCount", contentController.getContentByType(Video.class).size());
        data.put("imageCount", contentController.getContentByType(Image.class).size());

        report.setData(data);
        return report;
    }
}
