package model.interfaces;

import model.User;

/**
 * Interfaz que define el contrato para control de permisos de edición.
 * Permite verificar si un usuario tiene permisos para editar o eliminar.
 */
public interface IEditableByRole {
    /**
     * Verifica si un usuario tiene permisos para editar este objeto.
     *
     * @param user El usuario que intenta realizar la edición
     * @return true si el usuario puede editar, false en caso contrario
     */
    boolean canEdit(User user);

    /**
     * Verifica si un usuario tiene permisos para eliminar este objeto.
     *
     * @param user El usuario que intenta realizar la eliminación
     * @return true si el usuario puede eliminar, false en caso contrario
     */
    boolean canDelete(User user);
}
