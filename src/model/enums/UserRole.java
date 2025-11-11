package model.enums;

/**
 * Enumeración que define los roles de usuario en el sistema CMS.
 * Define los tres niveles de permisos disponibles.
 */
public enum UserRole {
    /**
     * Administrador: Acceso completo al sistema
     * Puede crear, editar, eliminar cualquier contenido
     * Puede gestionar usuarios y categorías
     */
    ADMIN,

    /**
     * Editor: Acceso limitado para creación de contenido
     * Puede crear y editar su propio contenido
     * No puede eliminar contenidos de otros usuarios
     */
    EDITOR,

    /**
     * Visitante: Solo lectura
     * Puede ver contenidos publicados
     * No puede crear ni modificar contenidos
     */
    VISITOR;

    /**
     * Verifica si el rol tiene permisos de administrador
     * @return true si el rol es ADMIN
     */
    public boolean isAdmin() {
        return this == ADMIN;
    }

    /**
     * Verifica si el rol puede editar contenido
     * @return true si el rol es ADMIN o EDITOR
     */
    public boolean canEdit() {
        return this == ADMIN || this == EDITOR;
    }

    /**
     * Obtiene una descripción legible del rol
     * @return Descripción del rol en español
     */
    public String getDescription() {
        return switch (this) {
            case ADMIN -> "Administrador del Sistema";
            case EDITOR -> "Editor de Contenidos";
            case VISITOR -> "Visitante";
        };
    }
}
