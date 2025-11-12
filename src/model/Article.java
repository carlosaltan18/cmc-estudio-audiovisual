package model;

import java.time.LocalDateTime;

/**
 * Representa un contenido de tipo artículo dentro del sistema.
 * Contiene texto y calcula el número de palabras.
 */

public class Article extends Content {
    private String content;
    private int wordCount;
    /**
     * Constructor de la clase Article.
     * @param id identificador único
     * @param title título del artículo
     * @param description descripción breve
     * @param author autor del contenido
     */

    public Article(String id, String title, String description, User author) {
        super(id, title, description, author);
        this.content = "";
        this.wordCount = 0;
    }
    /**
     * Obtiene el contenido textual del artículo.
     * @return texto completo
     */

    public String getContent() {
        return content;
    }
    /**
     * Define el contenido del artículo, recalcula el conteo de palabras y actualiza la fecha.
     * @param content texto del artículo
     */

    public void setContent(String content) {
        this.content = content;
        calculateWordCount();
        this.updatedAt = LocalDateTime.now();
    }
    /**
     * Devuelve la cantidad de palabras del artículo.
     * @return número de palabras
     */

    public int getWordCount() {
        return wordCount;
    }
    /**
     * Calcula la cantidad de palabras del texto actual.
     */

    private void calculateWordCount() {
        this.wordCount = content.trim().isEmpty() ? 0 : content.trim().split("\\s+").length;
    }
    /**
     * Devuelve una representación textual del artículo.
     * @return cadena con título, número de palabras y estado
     */

    @Override
    public String toString() {
        return String.format("[ARTÃCULO] %s | Palabras: %d | %s", title, wordCount, state.getDisplayName());
    }
}
