package model.interfaces;

import model.enums.ContentState;

/**
 * Interfaz que define el contrato para objetos que pueden ser publicados.
 * Los contenidos que implementen esta interfaz podrán cambiar su estado
 * de publicación.
 */
public interface IPublishable {
    void publish();
    void unpublish();
    ContentState getState();
}
