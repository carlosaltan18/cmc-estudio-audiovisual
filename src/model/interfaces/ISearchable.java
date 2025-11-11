package model.interfaces;

import java.util.List;

/**
 * Interfaz que define el contrato para objetos que pueden ser buscados.
 * Permite implementar funcionalidad de búsqueda en diferentes campos.
 */
public interface ISearchable {

    /**
     * Busca una palabra clave en los campos buscables del objeto
     * @param keyword Palabra o frase a buscar (no case-sensitive)
     * @return true si se encuentra la palabra en algún campo
     */
    boolean search(String keyword);

    /**
     * Obtiene la lista de nombres de campos que pueden ser buscados
     * @return Lista de nombres de campos buscables (ej: "título", "descripción")
     */
    List<String> getSearchableFields();

    /**
     * Verifica si un campo específico contiene la palabra clave
     * @param fieldName Nombre del campo a buscar
     * @param keyword Palabra clave a buscar
     * @return true si el campo contiene la palabra clave
     */
    default boolean searchInField(String fieldName, String keyword) {
        // Implementación por defecto que puede ser sobrescrita
        return false;
    }
}
