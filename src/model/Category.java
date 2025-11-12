package model;

/**
 * Clase que representa una categorÃ­a para clasificar contenidos.
 * Las categorÃ­as permiten organizar el contenido temÃ¡ticamente.
 */
public class Category {
    private final String id;
    private String name;
    private String description;

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Category && ((Category) obj).id.equals(this.id);
    }
}
