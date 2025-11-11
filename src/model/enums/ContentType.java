package model.enums;

/**
 * Enumeración que define los tipos de contenido soportados por el CMS.
 * Cada tipo tiene características específicas.
 */
public enum ContentType {
    /**
     * Artículo: Contenido textual
     * Incluye título, descripción y contenido extenso
     */
    ARTICLE,

    /**
     * Video: Contenido multimedia de video
     * Incluye URL, duración y resolución
     */
    VIDEO,

    /**
     * Imagen: Contenido visual estático
     * Incluye URL, dimensiones y tamaño de archivo
     */
    IMAGE;

    /**
     * Obtiene el nombre legible del tipo de contenido
     * @return Nombre del tipo en español
     */
    public String getDisplayName() {
        return switch (this) {
            case ARTICLE -> "Artículo";
            case VIDEO -> "Video";
            case IMAGE -> "Imagen";
        };
    }

    /**
     * Obtiene el icono o símbolo representativo del tipo
     * @return Símbolo emoji o carácter representativo
     */
    public String getIcon() {
        return switch (this) {
            case ARTICLE -> "📄";
            case VIDEO -> "🎥";
            case IMAGE -> "🖼️";
        };
    }

    /**
     * Verifica si el tipo es multimedia (video o imagen)
     * @return true si el tipo es VIDEO o IMAGE
     */
    public boolean isMultimedia() {
        return this == VIDEO || this == IMAGE;
    }
}
