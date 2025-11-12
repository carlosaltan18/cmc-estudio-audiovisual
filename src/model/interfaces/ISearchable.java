package model.interfaces;

import java.util.List;

/**
 * Interfaz que define el contrato para objetos que pueden ser buscados.
 * Permite implementar funcionalidad de búsqueda en diferentes campos.
 */
public interface ISearchable {
    /**
     * Busca una palabra clave en los campos buscables del objeto.
     * La búsqueda puede ser sensible o insensible a mayúsculas según la implementación.
     *
     * @param keyword La palabra clave a buscar
     * @return true si se encontró la palabra clave en algún campo, false en caso contrario
     */
    boolean search(String keyword);

    /**
     * Obtiene la lista de nombres de los campos en los que se puede realizar búsquedas.
     *
     * @return Una lista con los nombres de los campos buscables
     */
    List<String> getSearchableFields();
}
