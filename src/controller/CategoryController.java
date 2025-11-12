package controller;
import java.util.*;
import model.Category;

public class CategoryController {
    private final Map<String, Category> categories;

    public CategoryController() {
        this.categories = new HashMap<>();
        initializeDefaultCategories();
    }

    private void initializeDefaultCategories() {
        categories.put("cat1", new Category("cat1", "ProgramaciÃ³n", "Contenidos sobre programaciÃ³n"));
        categories.put("cat2", new Category("cat2", "DiseÃ±o", "Contenidos sobre diseÃ±o y UX"));
        categories.put("cat3", new Category("cat3", "Web", "Contenidos sobre desarrollo web"));
    }

    public Category createCategory(String id, String name, String description) {
        Category category = new Category(id, name, description);
        categories.put(id, category);
        return category;
    }

    public Category getCategoryById(String id) {
        return categories.get(id);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public void updateCategory(String id, String name, String description) {
        Category category = categories.get(id);
        if (category != null) {
            category.setName(name);
            category.setDescription(description);
        }
    }

    public void deleteCategory(String id) {
        categories.remove(id);
    }
}
