package model;

import java.util.Objects;

/**
 * Clase que representa una categoría para clasificar contenidos.
 * Las categorías permiten organizar el contenido temáticamente.
 */
public class Category {

    // Atributos privados
    private final String id;
    private String name;
    private String description;

    /**
     * Constructor de Categoría
     * @param id Identificador único de la categoría
     * @param name Nombre de la categoría
     * @param description Descripción de la categoría
     */
    public Category(String id, String name, String description) {
        this.id = Objects.requireNonNull(id, "ID no puede ser nulo");
        this.name = Objects.requireNonNull(name, "Name no puede ser nulo");
        this.description = description != null ? description : "";
    }

    /**
     * Constructor simplificado sin descripción
     * @param id Identificador único
     * @param name Nombre de la categoría
     */
    public Category(String id, String name) {
        this(id, name, "");
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name no puede ser nulo");
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    /**
     * Verifica si la categoría tiene descripción
     * @return true si la descripción no está vacía
     */
    public boolean hasDescription() {
        return !description.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Category{id='%s', name='%s', description='%s'}",
                           id, name, description);
    }
}
