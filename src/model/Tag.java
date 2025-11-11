package model;

import java.util.Objects;

/**
 * Clase que representa una etiqueta para clasificar contenidos.
 * Las etiquetas son más específicas que las categorías y permiten
 * una organización más granular.
 */
public class Tag {

    // Atributos privados
    private final String id;
    private String name;

    /**
     * Constructor de Etiqueta
     * @param id Identificador único de la etiqueta
     * @param name Nombre de la etiqueta
     */
    public Tag(String id, String name) {
        this.id = Objects.requireNonNull(id, "ID no puede ser nulo");
        this.name = Objects.requireNonNull(name, "Name no puede ser nulo");
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name no puede ser nulo");
    }

    /**
     * Normaliza el nombre de la etiqueta (lowercase, sin espacios extras)
     * @return Nombre normalizado
     */
    public String getNormalizedName() {
        return name.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Tag{id='%s', name='%s'}", id, name);
    }
}
