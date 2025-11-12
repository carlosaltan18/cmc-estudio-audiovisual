package model;
import java.time.LocalDateTime;
/**
 * Representa un contenido de tipo imagen dentro del sistema.
 * Contiene metadatos como URL, dimensiones y tamaño de archivo.
 */

public class Image extends Content{
    private String imageUrl;
    private int width;
    private int height;
    private double fileSize;
    /**
     * Constructor de la clase Image.
     * @param id identificador único
     * @param title título de la imagen
     * @param description descripción breve
     * @param author autor del contenido
     */

    public Image(String id, String title, String description, User author) {
        super(id, title, description, author);
        this.imageUrl = "";
        this.width = 0;
        this.height = 0;
        this.fileSize = 0.0;
    }
    /**
     * Obtiene la URL de la imagen.
     * @return URL de la imagen
     */

    public String getImageUrl() {
        return imageUrl;
    }
    /**
     * Define la URL de la imagen y actualiza la fecha de modificación.
     * @param imageUrl nueva URL
     */

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        this.updatedAt = LocalDateTime.now();
    }
    /**
     * Obtiene el ancho de la imagen.
     * @return ancho en píxeles
     */

    public int getWidth() {
        return width;
    }
    /**
     * Obtiene la altura de la imagen.
     * @return altura en píxeles
     */

    public int getHeight() {
        return height;
    }
    /**
     * Obtiene el tamaño del archivo de la imagen.
     * @return tamaño en MB
     */

    public double getFileSize() {
        return fileSize;
    }
    /**
     * Define las dimensiones y el tamaño del archivo.
     * @param width ancho en píxeles
     * @param height altura en píxeles
     * @param fileSize tamaño en MB
     */

    public void setDimensions(int width, int height, double fileSize) {
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
        this.updatedAt = LocalDateTime.now();
    }
    /**
     * Devuelve una representación textual con detalles de la imagen.
     * @return cadena con título, resolución, tamaño y estado
     */

    @Override
    public String toString() {
        return String.format("[IMAGEN] %s | ResoluciÃ³n: %dx%d | TamaÃ±o: %.2f MB | %s",
                title, width, height, fileSize, state.getDisplayName());
    }
}
