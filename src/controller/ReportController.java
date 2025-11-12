package controller;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import model.enums.*;
/**
 * Controlador encargado de generar reportes estadísticos y de resumen
 * sobre los contenidos existentes en el sistema.
 */

public class ReportController {
    private final ContentController contentController;
    /**
     * Constructor que recibe el ContentController para acceder a los contenidos.
     * @param contentController controlador de contenidos
     */

    public ReportController(ContentController contentController) {
        this.contentController = contentController;
    }
    /**
     * Genera un reporte general con todos los contenidos y sus descripciones.
     * @return objeto Report con los datos
     */

    public Report generateGeneralReport() {
        Report report = new Report(UUID.randomUUID().toString(), "Reporte General");
        Map<String, Object> data = new HashMap<>();

        List<Content> allContents = contentController.getAllContents();
        data.put("totalContents", allContents.size());
        data.put("contents", allContents.stream().map(Content::generateReport).collect(Collectors.toList()));

        report.setData(data);
        return report;
    }
    /**
     * Genera un reporte agrupando los contenidos por tipo (Artículo, Video, Imagen).
     * @return objeto Report con estadísticas por tipo
     */

    public Report generateReportByType() {
        Report report = new Report(UUID.randomUUID().toString(), "Reporte por Tipo");
        Map<String, Object> data = new HashMap<>();

        data.put("articles", contentController.getContentByType(Article.class).size());
        data.put("videos", contentController.getContentByType(Video.class).size());
        data.put("images", contentController.getContentByType(Image.class).size());

        report.setData(data);
        return report;
    }
    /**
     * Genera un reporte con el número de contenidos por autor.
     * @return objeto Report con datos por autor
     */

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
    /**
     * Genera estadísticas generales sobre los contenidos del sistema.
     * Incluye totales, publicados, en borrador y por tipo.
     * @return objeto Report con las estadísticas
     */

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
