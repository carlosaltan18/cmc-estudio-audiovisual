package controller;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import model.enums.UserRole;
import model.enums.ContentState;

public class ContentController {
    private final Map<String, Content> contents;

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

    public Article createArticle(String id, String title, String description, String content, User author) {
        Article article = new Article(id, title, description, author);
        article.setContent(content);
        contents.put(id, article);
        return article;
    }

    public Video createVideo(String id, String title, String description, String videoUrl,
                             int duration, String resolution, User author) {
        Video video = new Video(id, title, description, author);
        video.setVideoUrl(videoUrl);
        video.setDuration(duration);
        video.setResolution(resolution);
        contents.put(id, video);
        return video;
    }

    public Image createImage(String id, String title, String description, String imageUrl,
                             int width, int height, double fileSize, User author) {
        Image image = new Image(id, title, description, author);
        image.setImageUrl(imageUrl);
        image.setDimensions(width, height, fileSize);
        contents.put(id, image);
        return image;
    }

    public Content getContentById(String id) {
        return contents.get(id);
    }

    public List<Content> getAllContents() {
        return new ArrayList<>(contents.values());
    }

    public List<Content> getPublishedContents() {
        return contents.values().stream()
                .filter(c -> c.getState() == ContentState.PUBLISHED)
                .collect(Collectors.toList());
    }

    public void updateContent(String id, String title, String description) {
        Content content = contents.get(id);
        if (content != null) {
            content.update(title, description);
        }
    }

    public void deleteContent(String id) {
        contents.remove(id);
    }

    public void publishContent(String id) {
        Content content = contents.get(id);
        if (content != null) {
            content.publish();
        }
    }

    public void unpublishContent(String id) {
        Content content = contents.get(id);
        if (content != null) {
            content.unpublish();
        }
    }

    public List<Content> getContentByType(Class<?> type) {
        return contents.values().stream()
                .filter(c -> c.getClass() == type)
                .collect(Collectors.toList());
    }

    public List<Content> getContentByAuthor(User author) {
        return contents.values().stream()
                .filter(c -> c.getAuthor().getId().equals(author.getId()))
                .collect(Collectors.toList());
    }
}
