package model.enums;

/**
 * Enumeración que representa los estados posibles de un contenido.
 * Un contenido puede estar en borrador o publicado.
 */
public enum ContentState {
    /**
     * Estado inicial del contenido. El contenido no es visible públicamente.
     */
    DRAFT("Borrador"),

    /**
     * Estado publicado. El contenido es visible públicamente.
     */
    PUBLISHED("Publicado");

    private final String displayName;

    /**
     * Constructor del enum ContentState.
     *
     * @param displayName Nombre descriptivo del estado para mostrar al usuario
     */
    ContentState(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Obtiene el nombre descriptivo del estado.
     *
     * @return El nombre descriptivo del estado
     */
    public String getDisplayName() {
        return displayName;
    }
}
