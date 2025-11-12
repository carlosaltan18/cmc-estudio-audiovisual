package model.interfaces;

import java.util.List;

/**
 * Interfaz que define el contrato para objetos que pueden ser buscados.
 * Permite implementar funcionalidad de bÃºsqueda en diferentes campos.
 */
public interface ISearchable {
    boolean search(String keyword);
    List<String> getSearchableFields();
}
