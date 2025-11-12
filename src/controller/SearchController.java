package controller;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import model.enums.*;
/**
 * Controlador responsable de realizar búsquedas y filtros de contenidos.
 */
public class SearchController {
    private final ContentController contentController;
    /**
     * Constructor que recibe una instancia del ContentController para operar sobre los contenidos.
     * @param contentController controlador principal de contenidos
     */

    public SearchController(ContentController contentController) {
        this.contentController = contentController;
    }
    /**
     * Busca contenidos que coincidan con una palabra clave.
     * @param keyword palabra clave a buscar
     * @return lista de contenidos coincidentes
     */

    public List<Content> search(String keyword) {
        return contentController.getAllContents().stream()
                .filter(c -> c.search(keyword))
                .collect(Collectors.toList());
    }
    /**
     * Filtra los contenidos por tipo (Artículo, Video o Imagen).
     * @param type tipo de contenido
     * @return lista filtrada de contenidos
     */

    public List<Content> filterByType(ContentType type) {
        return contentController.getAllContents().stream()
                .filter(c -> {
                    if (type == ContentType.ARTICLE) return c instanceof Article;
                    if (type == ContentType.VIDEO) return c instanceof Video;
                    if (type == ContentType.IMAGE) return c instanceof Image;
                    return false;
                })
                .collect(Collectors.toList());
    }
    /**
     * Filtra los contenidos según su estado (Publicado o Borrador).
     * @param state estado del contenido
     * @return lista filtrada de contenidos
     */

    public List<Content> filterByState(ContentState state) {
        return contentController.getAllContents().stream()
                .filter(c -> c.getState() == state)
                .collect(Collectors.toList());
    }
    /**
     * Filtra los contenidos creados por un autor específico.
     * @param author autor de referencia
     * @return lista de contenidos del autor
     */
    public List<Content> filterByAuthor(User author) {
        return contentController.getContentByAuthor(author);
    }
    /**
     * Filtra los contenidos que pertenecen a una categoría dada.
     * @param category categoría de referencia
     * @return lista de contenidos en la categoría
     */

    public List<Content> filterByCategory(Category category) {
        return contentController.getAllContents().stream()
                .filter(c -> c.getCategories().contains(category))
                .collect(Collectors.toList());
    }
    /**
     * Realiza una búsqueda avanzada combinando palabra clave, tipo y estado.
     * @param keyword palabra clave
     * @param type tipo de contenido (puede ser null)
     * @param state estado del contenido (puede ser null)
     * @return lista de contenidos que cumplen los criterios
     */

    public List<Content> advancedSearch(String keyword, ContentType type, ContentState state) {
        return contentController.getAllContents().stream()
                .filter(c -> c.search(keyword))
                .filter(c -> {
                    if (type == ContentType.ARTICLE) return c instanceof Article;
                    if (type == ContentType.VIDEO) return c instanceof Video;
                    if (type == ContentType.IMAGE) return c instanceof Image;
                    return true;
                })
                .filter(c -> state == null || c.getState() == state)
                .collect(Collectors.toList());
    }
}
