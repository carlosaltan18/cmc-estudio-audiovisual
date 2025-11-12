package model;

import model.enums.UserRole;
import java.time.LocalDateTime;

/**
 * Clase que representa un usuario del sistema CMS.
 * Maneja la informaciÃ³n bÃ¡sica y permisos de los usuarios.
 */
public class User {
    private final String id;
    private String username;
    private String email;
    private UserRole role;
    private final LocalDateTime createdAt;

    public User(String id, String username, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role != null ? role : UserRole.VISITOR; // Valor por defecto
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean hasPermission(String action) {
        return switch (role) {
            case ADMIN -> true;
            case EDITOR -> !action.equals("delete_any") && !action.equals("manage_users");
            case VISITOR -> action.equals("view");
        };
    }

    @Override
    public String toString() {
        return username + " (" + role.getDisplayName() + ")";
    }
}
