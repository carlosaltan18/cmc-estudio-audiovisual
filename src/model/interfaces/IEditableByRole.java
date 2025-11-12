package model.interfaces;

import model.User;

/**
 * Interfaz que define el contrato para control de permisos de ediciÃ³n.
 * Permite verificar si un usuario tiene permisos para editar o eliminar.
 */
public interface IEditableByRole {
    boolean canEdit(User user);
    boolean canDelete(User user);
}
