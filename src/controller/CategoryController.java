package controller;
import java.util.*;
import model.Category;
/**
 * Controlador encargado de gestionar las categorías del sistema.
 * Permite crear, obtener, actualizar y eliminar categorías,
 * así como inicializar algunas por defecto.
 */

public class CategoryController {
    /**
     * Mapa que almacena las categorías identificadas por su ID.
     */

    private final Map<String, Category> categories;
    /**
     * Constructor que inicializa el controlador con un conjunto de categorías por defecto.
     */

    public CategoryController() {
        this.categories = new HashMap<>();
        initializeDefaultCategories();
    }
    /**
     * Inicializa un conjunto de categorías predeterminadas (Programación, Diseño, Web).
     */

    private void initializeDefaultCategories() {
        categories.put("cat1", new Category("cat1", "Programacion", "Contenidos sobre programacion"));
        categories.put("cat2", new Category("cat2", "Diseño", "Contenidos sobre diseño y UX"));
        categories.put("cat3", new Category("cat3", "Web", "Contenidos sobre desarrollo web"));
    }
    /**
     * Crea una nueva categoría y la agrega al mapa.
     * @param id ID único de la categoría
     * @param name Nombre de la categoría
     * @param description Descripción de la categoría
     * @return la categoría creada
     */

    public Category createCategory(String id, String name, String description) {
        Category category = new Category(id, name, description);
        categories.put(id, category);
        return category;
    }
    /**
     * Obtiene una categoría por su ID.
     * @param id ID de la categoría a buscar
     * @return la categoría correspondiente o null si no existe
     */

    public Category getCategoryById(String id) {
        return categories.get(id);
    }
    /**
     * Obtiene todas las categorías existentes.
     * @return lista con todas las categorías
     */
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
    /**
     * Actualiza los datos de una categoría existente.
     * @param id ID de la categoría a actualizar
     * @param name nuevo nombre
     * @param description nueva descripción
     */

    public void updateCategory(String id, String name, String description) {
        Category category = categories.get(id);
        if (category != null) {
            category.setName(name);
            category.setDescription(description);
        }
    }
    /**
     * Elimina una categoría del sistema.
     * @param id ID de la categoría a eliminar
     */

    public void deleteCategory(String id) {
        categories.remove(id);
    }
}
