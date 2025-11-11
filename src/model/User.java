package model;

import model.enums.UserRole;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que representa un usuario del sistema CMS.
 * Maneja la información básica y permisos de los usuarios.
 */
public class User {

    // Atributos privados
    private final String id;
    private String username;
    private String email;
    private UserRole role;
    private final LocalDateTime createdAt;

    /**
     * Constructor principal de Usuario
     * @param id Identificador único del usuario
     * @param username Nombre de usuario (login)
     * @param email Correo electrónico
     * @param role Rol asignado (ADMIN, EDITOR, VISITOR)
     */
    public User(String id, String username, String email, UserRole role) {
        this.id = Objects.requireNonNull(id, "ID no puede ser nulo");
        this.username = Objects.requireNonNull(username, "Username no puede ser nulo");
        this.email = Objects.requireNonNull(email, "Email no puede ser nulo");
        this.role = Objects.requireNonNull(role, "Role no puede ser nulo");
        this.createdAt = LocalDateTime.now();
    }

    // Getters
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
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setUsername(String username) {
        this.username = Objects.requireNonNull(username, "Username no puede ser nulo");
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "Email no puede ser nulo");
    }

    public void setRole(UserRole role) {
        this.role = Objects.requireNonNull(role, "Role no puede ser nulo");
    }

    /**
     * Verifica si el usuario tiene un permiso específico basado en su rol
     * @param action Acción a verificar (create, edit, delete, manage_users)
     * @return true si el usuario tiene el permiso
     */
    public boolean hasPermission(String action) {
        return switch (action.toLowerCase()) {
            case "create" -> role.canEdit();
            case "edit_all" -> role.isAdmin();
            case "edit_own" -> role.canEdit();
            case "delete" -> role.isAdmin();
            case "publish" -> role.canEdit();
            case "manage_users" -> role.isAdmin();
            case "view_reports" -> role.canEdit();
            default -> false;
        };
    }

    /**
     * Verifica si el usuario es administrador
     * @return true si el rol es ADMIN
     */
    public boolean isAdmin() {
        return role.isAdmin();
    }

    /**
     * Verifica si el usuario puede editar contenido
     * @return true si el rol permite edición
     */
    public boolean canEdit() {
        return role.canEdit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("User{id='%s', username='%s', email='%s', role=%s}",
                           id, username, email, role);
    }
}
