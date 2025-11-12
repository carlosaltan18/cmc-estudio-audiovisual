package model.interfaces;

import model.enums.ContentState;

/**
 * Interfaz que define el contrato para objetos que pueden ser publicados.
 * Los contenidos que implementen esta interfaz podrán cambiar su estado
 * de publicación.
 */
public interface IPublishable {
    /**
     * Publica el contenido, cambiando su estado a PUBLISHED.
     * El contenido se vuelve visible públicamente.
     */
    void publish();

    /**
     * Despublica el contenido, cambiando su estado a DRAFT.
     * El contenido deja de ser visible públicamente.
     */
    void unpublish();

    /**
     * Obtiene el estado actual de publicación del contenido.
     *
     * @return El estado actual (DRAFT o PUBLISHED)
     */
    ContentState getState();
}
