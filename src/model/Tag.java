package model;

/**
 * Clase que representa una etiqueta para clasificar contenidos.
 * Las etiquetas son más específicas que las categorías y permiten
 * una organización más granular.
 */
public class Tag {
    private final String id;
    private String name;

    /**
     * Constructor de la clase Tag.
     *
     * @param id Identificador único de la etiqueta
     * @param name Nombre de la etiqueta
     */
    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Obtiene el identificador único de la etiqueta.
     *
     * @return El ID de la etiqueta
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la etiqueta.
     *
     * @return El nombre de la etiqueta
     */
    public String getName() {
        return name;
    }

    /**
     * Establece un nuevo nombre para la etiqueta.
     *
     * @param name El nuevo nombre de la etiqueta
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Representa la etiqueta como una cadena de texto.
     *
     * @return El nombre de la etiqueta
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Compara esta etiqueta con otro objeto para determinar si son iguales.
     * Dos etiquetas son iguales si tienen el mismo ID.
     *
     * @param obj El objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tag && ((Tag) obj).id.equals(this.id);
    }
}
