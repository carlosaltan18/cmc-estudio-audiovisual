package controller;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import model.enums.UserRole;
import model.enums.ContentState;
/**
 * Controlador encargado de gestionar los distintos tipos de contenido del sistema:
 * artículos, videos e imágenes.
 * Permite crearlos, modificarlos, publicarlos y eliminarlos.
 */

public class ContentController {
    /**
     * Mapa que almacena todos los contenidos creados, indexados por su ID.
     */

    private final Map<String, Content> contents;
    /**
     * Constructor que inicializa el controlador con contenido de ejemplo por defecto.
     */

    public ContentController() {
        this.contents = new HashMap<>();
        initializeDefaultContent();
    }

    private void initializeDefaultContent() {
        User admin = new User("admin1", "admin1", "admin@ega.edu", UserRole.ADMIN);
        User editor = new User("editor1", "editor1", "editor@ega.edu", UserRole.EDITOR);

        Article article1 = new Article("art1", "Introducción a Java", "Conceptos básicos de POO", admin);
        article1.setContent("Java es un lenguaje de programación orientado a objetos. Este artículo explora los conceptos fundamentales de la POO en Java.");
        article1.publish();

        Video video1 = new Video("vid1", "Tutorial OOP", "Aprende programación orientada a objetos", editor);
        video1.setVideoUrl("https://example.com/oop-tutorial");
        video1.setDuration(45);
        video1.setResolution("1080p");
        video1.publish();

        Image image1 = new Image("img1", "Diagrama de Clases", "Ejemplo de diagrama UML", admin);
        image1.setImageUrl("https://example.com/uml-diagram.png");
        image1.setDimensions(1920, 1080, 2.5);
        image1.publish();

        contents.put(article1.getId(), article1);
        contents.put(video1.getId(), video1);
        contents.put(image1.getId(), image1);
    }
    /**
     * Crea un nuevo artículo con el texto especificado.
     * @param id identificador del artículo
     * @param title título del artículo
     * @param description descripción breve
     * @param content texto del artículo
     * @param author autor del contenido
     * @return el artículo creado
     */
    public Article createArticle(String id, String title, String description, String content, User author) {
        Article article = new Article(id, title, description, author);
        article.setContent(content);
        contents.put(id, article);
        return article;
    }
    /**
     * Crea un nuevo video con sus metadatos.
     * @param id identificador del video
     * @param title título del video
     * @param description descripción breve
     * @param videoUrl enlace al video
     * @param duration duración en minutos
     * @param resolution resolución del video
     * @param author autor del contenido
     * @return el video creado
     */

    public Video createVideo(String id, String title, String description, String videoUrl,
                             int duration, String resolution, User author) {
        Video video = new Video(id, title, description, author);
        video.setVideoUrl(videoUrl);
        video.setDuration(duration);
        video.setResolution(resolution);
        contents.put(id, video);
        return video;
    }
    /**
     * Crea una nueva imagen con sus dimensiones y URL.
     * @param id identificador de la imagen
     * @param title título de la imagen
     * @param description descripción breve
     * @param imageUrl enlace de la imagen
     * @param width ancho en píxeles
     * @param height altura en píxeles
     * @param fileSize tamaño en MB
     * @param author autor del contenido
     * @return la imagen creada
     */

    public Image createImage(String id, String title, String description, String imageUrl,
                             int width, int height, double fileSize, User author) {
        Image image = new Image(id, title, description, author);
        image.setImageUrl(imageUrl);
        image.setDimensions(width, height, fileSize);
        contents.put(id, image);
        return image;
    }
    /**
     * Obtiene un contenido específico por su identificador.
     * @param id identificador del contenido
     * @return el contenido correspondiente o null si no existe
     */

    public Content getContentById(String id) {
        return contents.get(id);
    }
    /**
     * Devuelve una lista con todos los contenidos del sistema.
     * @return lista de todos los contenidos
     */

    public List<Content> getAllContents() {
        return new ArrayList<>(contents.values());
    }
    /**
     * Obtiene todos los contenidos que están actualmente publicados.
     * @return lista de contenidos publicados
     */

    public List<Content> getPublishedContents() {
        return contents.values().stream()
                .filter(c -> c.getState() == ContentState.PUBLISHED)
                .collect(Collectors.toList());
    }
    /**
     * Actualiza el título y la descripción de un contenido existente.
     * @param id identificador del contenido
     * @param title nuevo título
     * @param description nueva descripción
     */

    public void updateContent(String id, String title, String description) {
        Content content = contents.get(id);
        if (content != null) {
            content.update(title, description);
        }
    }
    /**
     * Elimina un contenido del sistema.
     * @param id identificador del contenido a eliminar
     */

    public void deleteContent(String id) {
        contents.remove(id);
    }
    /**
     * Publica un contenido si está en estado de borrador.
     * @param id identificador del contenido a publicar
     */

    public void publishContent(String id) {
        Content content = contents.get(id);
        if (content != null) {
            content.publish();
        }
    }
    /**
     * Despublica un contenido si está actualmente publicado.
     * @param id identificador del contenido a despublicar
     */

    public void unpublishContent(String id) {
        Content content = contents.get(id);
        if (content != null) {
            content.unpublish();
        }
    }
    /**
     * Obtiene todos los contenidos que coincidan con un tipo específico.
     * @param type clase del tipo de contenido (Article, Video, Image)
     * @return lista de contenidos del tipo solicitado
     */

    public List<Content> getContentByType(Class<?> type) {
        return contents.values().stream()
                .filter(c -> c.getClass() == type)
                .collect(Collectors.toList());
    }
    /**
     * Obtiene todos los contenidos creados por un autor determinado.
     * @param author autor de referencia
     * @return lista de contenidos creados por ese autor
     */

    public List<Content> getContentByAuthor(User author) {
        return contents.values().stream()
                .filter(c -> c.getAuthor().getId().equals(author.getId()))
                .collect(Collectors.toList());
    }
}
