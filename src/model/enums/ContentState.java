package model.enums;

/**
 * Enumeración que representa los estados posibles de un contenido.
 * Un contenido puede estar en borrador o publicado.
 */
public enum ContentState {
    /**
     * Borrador: Contenido en proceso de creación
     * No visible para visitantes
     * Puede ser editado libremente
     */
    DRAFT,

    /**
     * Publicado: Contenido visible públicamente
     * Accesible para todos los usuarios
     * Requiere permisos para editar
     */
    PUBLISHED;

    /**
     * Verifica si el contenido está publicado
     * @return true si el estado es PUBLISHED
     */
    public boolean isPublished() {
        return this == PUBLISHED;
    }

    /**
     * Verifica si el contenido es un borrador
     * @return true si el estado es DRAFT
     */
    public boolean isDraft() {
        return this == DRAFT;
    }

    /**
     * Obtiene el nombre legible del estado
     * @return Nombre del estado en español
     */
    public String getDisplayName() {
        return switch (this) {
            case DRAFT -> "Borrador";
            case PUBLISHED -> "Publicado";
        };
    }
}
