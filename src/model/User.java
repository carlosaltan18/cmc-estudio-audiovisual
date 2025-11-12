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

    /**
     * Constructor de la clase User.
     *
     * @param id Identificador único del usuario
     * @param username Nombre de usuario
     * @param email Correo electrónico del usuario
     * @param role Rol del usuario en el sistema
     */
    public User(String id, String username, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El ID del usuario
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene el rol del usuario.
     * Si el rol es null, retorna VISITOR por defecto.
     *
     * @return El rol del usuario
     */
    public UserRole getRole() {
        return role != null ? role : UserRole.VISITOR; // Valor por defecto
    }

    /**
     * Establece un nuevo rol para el usuario.
     *
     * @param role El nuevo rol del usuario
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Obtiene la fecha y hora de creación del usuario.
     *
     * @return La fecha y hora de creación
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Verifica si el usuario tiene permiso para realizar una acción específica.
     * Los permisos varían según el rol del usuario:
     * - ADMIN: Tiene todos los permisos
     * - EDITOR: Tiene todos los permisos excepto "delete_any" y "manage_users"
     * - VISITOR: Solo tiene permiso "view"
     *
     * @param action La acción a verificar
     * @return true si el usuario tiene permiso, false en caso contrario
     */
    public boolean hasPermission(String action) {
        return switch (role) {
            case ADMIN -> true;
            case EDITOR -> !action.equals("delete_any") && !action.equals("manage_users");
            case VISITOR -> action.equals("view");
        };
    }

    /**
     * Representa el usuario como una cadena de texto.
     *
     * @return El nombre de usuario y su rol entre paréntesis
     */
    @Override
    public String toString() {
        return username + " (" + role.getDisplayName() + ")";
    }
}
