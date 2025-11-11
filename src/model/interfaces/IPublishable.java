package model.interfaces;

import model.enums.ContentState;

/**
 * Interfaz que define el contrato para objetos que pueden ser publicados.
 * Los contenidos que implementen esta interfaz podrán cambiar su estado
 * de publicación.
 */
public interface IPublishable {

    /**
     * Publica el contenido, cambiando su estado a PUBLISHED
     * Establece la fecha de publicación al momento actual
     */
    void publish();

    /**
     * Despublica el contenido, regresándolo a estado DRAFT
     * Útil para hacer modificaciones o retirar contenido temporalmente
     */
    void unpublish();

    /**
     * Obtiene el estado actual de publicación del contenido
     * @return El estado actual (DRAFT o PUBLISHED)
     */
    ContentState getState();

    /**
     * Verifica si el contenido está actualmente publicado
     * @return true si el estado es PUBLISHED, false en caso contrario
     */
    default boolean isPublished() {
        return getState() == ContentState.PUBLISHED;
    }
}
