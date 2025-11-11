package model.interfaces;

import model.User;

/**
 * Interfaz que define el contrato para control de permisos de edición.
 * Permite verificar si un usuario tiene permisos para editar o eliminar.
 */
public interface IEditableByRole {

    /**
     * Verifica si un usuario puede editar este contenido
     * @param user Usuario que intenta editar
     * @return true si el usuario tiene permisos de edición
     */
    boolean canEdit(User user);

    /**
     * Verifica si un usuario puede eliminar este contenido
     * @param user Usuario que intenta eliminar
     * @return true si el usuario tiene permisos de eliminación
     */
    boolean canDelete(User user);

    /**
     * Verifica si un usuario es el propietario del contenido
     * @param user Usuario a verificar
     * @return true si el usuario es el autor/propietario
     */
    default boolean isOwner(User user) {
        return false;
    }
}
