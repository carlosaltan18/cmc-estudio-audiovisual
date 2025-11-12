package model.enums;

/**
 * Enumeración que define los roles de usuario en el sistema CMS.
 * Define los tres niveles de permisos disponibles.
 */
public enum UserRole {
    /**
     * Rol de administrador con todos los permisos del sistema.
     * Nivel de permisos: 3 (máximo).
     */
    ADMIN("Administrador", 3),

    /**
     * Rol de editor con permisos para crear y editar contenido.
     * Nivel de permisos: 2 (intermedio).
     */
    EDITOR("Editor", 2),

    /**
     * Rol de visitante con permisos solo de visualización.
     * Nivel de permisos: 1 (mínimo).
     */
    VISITOR("Visitante", 1);

    private final String displayName;
    private final int level;

    /**
     * Constructor del enum UserRole.
     *
     * @param displayName Nombre descriptivo del rol para mostrar al usuario
     * @param level Nivel numérico de permisos (1-3), donde 3 es el nivel más alto
     */
    UserRole(String displayName, int level) {
        this.displayName = displayName;
        this.level = level;
    }

    /**
     * Obtiene el nombre descriptivo del rol.
     *
     * @return El nombre descriptivo del rol
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Obtiene el nivel numérico de permisos del rol.
     *
     * @return El nivel de permisos (1-3)
     */
    public int getLevel() {
        return level;
    }
}
