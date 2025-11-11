package controller;
import java.util.*;
import java.util.stream.Collectors;
import model.*;
import model.enums.*;

public class SearchController {
    private final ContentController contentController;

    public SearchController(ContentController contentController) {
        this.contentController = contentController;
    }

    public List<Content> search(String keyword) {
        return contentController.getAllContents().stream()
                .filter(c -> c.search(keyword))
                .collect(Collectors.toList());
    }

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

    public List<Content> filterByState(ContentState state) {
        return contentController.getAllContents().stream()
                .filter(c -> c.getState() == state)
                .collect(Collectors.toList());
    }

    public List<Content> filterByAuthor(User author) {
        return contentController.getContentByAuthor(author);
    }

    public List<Content> filterByCategory(Category category) {
        return contentController.getAllContents().stream()
                .filter(c -> c.getCategories().contains(category))
                .collect(Collectors.toList());
    }

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
