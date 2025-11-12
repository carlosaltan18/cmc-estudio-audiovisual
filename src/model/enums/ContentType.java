package model.enums;

/**
 * Enumeración que define los tipos de contenido soportados por el CMS.
 * Cada tipo tiene características específicas.
 */
public enum ContentType {
    ARTICLE("Artículo", "📄"),
    VIDEO("Video", "🎥"),
    IMAGE("Imagen", "🖼️");

    private final String displayName;
    private final String icon;

    ContentType(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIcon() {
        return icon;
    }
}

