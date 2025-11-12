package controller;
import java.util.*;
import model.User;
import model.enums.UserRole;

public class AuthenticationController {
    private final Map<String, User> users;

    public AuthenticationController() {
        this.users = new HashMap<>();
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        users.put("admin1", new User("admin1", "admin1", "admin@ega.edu", UserRole.ADMIN));
        users.put("editor1", new User("editor1", "editor1", "editor@ega.edu", UserRole.EDITOR));
        users.put("visitor1", new User("visitor1", "visitor1", "visitor@ega.edu", UserRole.VISITOR));
    }

    public User login(String username) {
        return users.getOrDefault(username, null);
    }

    public User createUser(String id, String username, String email, UserRole role) {
        User user = new User(id, username, email, role);
        users.put(id, user);
        return user;
    }

    public User getUserById(String id) {
        return users.get(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void updateUserRole(String userId, UserRole newRole) {
        User user = users.get(userId);
        if (user != null) {
            user.setRole(newRole);
        }
    }

    public void deleteUser(String userId) {
        users.remove(userId);
    }
}
