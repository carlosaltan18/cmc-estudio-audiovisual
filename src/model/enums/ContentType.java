package model.enums;

/**
 * Enumeración que define los tipos de contenido soportados por el CMS.
 * Cada tipo tiene características específicas.
 */
public enum ContentType {
    /**
     * Contenido de tipo artículo. Representa contenido textual largo.
     */
    ARTICLE("Artículo", "📄"),

    /**
     * Contenido de tipo video. Representa contenido multimedia audiovisual.
     */
    VIDEO("Video", "🎥"),

    /**
     * Contenido de tipo imagen. Representa contenido gráfico o fotográfico.
     */
    IMAGE("Imagen", "🖼️");

    private final String displayName;
    private final String icon;

    /**
     * Constructor del enum ContentType.
     *
     * @param displayName Nombre descriptivo del tipo de contenido
     * @param icon Icono emoji que representa visualmente el tipo de contenido
     */
    ContentType(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    /**
     * Obtiene el nombre descriptivo del tipo de contenido.
     *
     * @return El nombre descriptivo del tipo de contenido
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Obtiene el icono emoji asociado al tipo de contenido.
     *
     * @return El icono del tipo de contenido
     */
    public String getIcon() {
        return icon;
    }
}

