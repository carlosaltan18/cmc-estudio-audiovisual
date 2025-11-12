package model.enums;

/**
 * Enumeración que representa los estados posibles de un contenido.
 * Un contenido puede estar en borrador o publicado.
 */
public enum ContentState {
    DRAFT("Borrador"),
    PUBLISHED("Publicado");

    private final String displayName;

    ContentState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
