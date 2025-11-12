package controller;
import java.util.*;
import model.User;
import model.enums.UserRole;
/**
 * Controlador responsable de gestionar la autenticación y administración de usuarios.
 * Permite crear, obtener, actualizar y eliminar usuarios dentro del sistema.
 */

public class AuthenticationController {
    /**
     * Mapa que almacena los usuarios registrados, identificados por su ID.
     */
    private final Map<String, User> users;
    /**
     * Constructor que inicializa el controlador con un conjunto de usuarios predeterminados.
     */

    public AuthenticationController() {
        this.users = new HashMap<>();
        initializeDefaultUsers();
    }
    /**
     * Inicializa usuarios por defecto con distintos roles (ADMIN, EDITOR, VISITOR).
     */

    private void initializeDefaultUsers() {
        users.put("admin1", new User("a0001", "admin1", "admin@ega.edu", UserRole.ADMIN));
        users.put("editor1", new User("e0001", "editor1", "editor@ega.edu", UserRole.EDITOR));
        users.put("visitor1", new User("v0001", "visitor1", "visitor@ega.edu", UserRole.VISITOR));
    }
    /**
     * Permite iniciar sesión con un nombre de usuario.
     * @param username nombre de usuario a autenticar
     * @return el usuario si existe, null en caso contrario
     */
    public User login(String username) {
        return users.getOrDefault(username, null);
    }
    /**
     * Crea un nuevo usuario y lo agrega al mapa de usuarios registrados.
     * @param id identificador único del usuario
     * @param username nombre de usuario
     * @param email correo electrónico
     * @param role rol asignado al usuario
     * @return el usuario creado
     */

    public User createUser(String id, String username, String email, UserRole role) {
        User user = new User(id, username, email, role);
        users.put(id, user);
        return user;
    }
    /**
     * Obtiene un usuario a partir de su identificador.
     * @param id ID del usuario
     * @return usuario correspondiente o null si no existe
     */

    public User getUserById(String id) {
        return users.get(id);
    }
    /**
     * Obtiene la lista completa de usuarios registrados.
     * @return lista de usuarios
     */

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    /**
     * Actualiza el rol de un usuario existente.
     * @param userId identificador del usuario
     * @param newRole nuevo rol a asignar
     */

    public void updateUserRole(String userId, UserRole newRole) {
        User user = users.get(userId);
        if (user != null) {
            user.setRole(newRole);
        }
    }
    /**
     * Elimina un usuario del sistema.
     * @param userId identificador del usuario a eliminar
     */

    public void deleteUser(String userId) {
        users.remove(userId);
    }
}
