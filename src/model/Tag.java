package model;

/**
 * Clase que representa una etiqueta para clasificar contenidos.
 * Las etiquetas son más específicas que las categorías y permiten
 * una organización más granular.
 */
public class Tag {
    private final String id;
    private String name;

    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tag && ((Tag) obj).id.equals(this.id);
    }
}
