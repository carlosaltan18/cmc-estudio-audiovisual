package model.enums;

/**
 * Enumeración que define los roles de usuario en el sistema CMS.
 * Define los tres niveles de permisos disponibles.
 */
public enum UserRole {
    ADMIN("Administrador", 3),
    EDITOR("Editor", 2),
    VISITOR("Visitante", 1);

    private final String displayName;
    private final int level;

    UserRole(String displayName, int level) {
        this.displayName = displayName;
        this.level = level;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLevel() {
        return level;
    }
}
