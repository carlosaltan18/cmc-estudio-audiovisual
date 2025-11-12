package model;

/**
 * Clase que representa una categorÃ­a para clasificar contenidos.
 * Las categorÃ­as permiten organizar el contenido temÃ¡ticamente.
 */
public class Category {
    private final String id;
    private String name;
    private String description;

    /**
     * Constructor de la clase Category.
     *
     * @param id Identificador único de la categoría
     * @param name Nombre de la categoría
     * @param description Descripción detallada de la categoría
     */
    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Obtiene el identificador único de la categoría.
     *
     * @return El ID de la categoría
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return El nombre de la categoría
     */
    public String getName() {
        return name;
    }

    /**
     * Establece un nuevo nombre para la categoría.
     *
     * @param name El nuevo nombre de la categoría
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción de la categoría.
     *
     * @return La descripción de la categoría
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece una nueva descripción para la categoría.
     *
     * @param description La nueva descripción de la categoría
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Representa la categoría como una cadena de texto.
     *
     * @return El nombre de la categoría
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Compara esta categoría con otro objeto para determinar si son iguales.
     * Dos categorías son iguales si tienen el mismo ID.
     *
     * @param obj El objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Category && ((Category) obj).id.equals(this.id);
    }
}
