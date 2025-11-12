package model;

import java.time.LocalDateTime;
import java.util.*;
import model.interfaces.*;
import model.enums.ContentState;
import model.enums.UserRole;
/**
 * Clase base abstracta para todos los tipos de contenido del sistema.
 * Implementa las interfaces de publicación, búsqueda, reportes y edición.
 */

public class Content implements IPublishable, ISearchable, IReportable, IEditableByRole  {
    protected String id;
    protected String title;
    protected String description;
    protected User author;
    protected ContentState state;
    protected List<Category> categories;
    protected List<Tag> tags;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected LocalDateTime publishedAt;
    /**
     * Constructor protegido que inicializa los campos básicos del contenido.
     * @param id identificador único
     * @param title título del contenido
     * @param description descripción breve
     * @param author autor del contenido
     */

    protected Content(String id, String title, String description, User author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.state = ContentState.DRAFT;
        this.categories = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public User getAuthor() {
        return author;
    }

    public ContentState getState() {
        return state;
    }

    public void setState(ContentState state) {
        this.state = state;
        this.updatedAt = LocalDateTime.now();
    }
    /**
     * Publica el contenido si se encuentra en borrador.
     */

    @Override
    public void publish() {
        if (state == ContentState.DRAFT) {
            this.state = ContentState.PUBLISHED;
            this.publishedAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }
    }
    /**
     * Despublica el contenido si está publicado.
     */

    @Override
    public void unpublish() {
        if (state == ContentState.PUBLISHED) {
            this.state = ContentState.DRAFT;
            this.publishedAt = null;
            this.updatedAt = LocalDateTime.now();
        }
    }
    /**
     * Agrega una categoría al contenido, si no está ya asociada.
     * @param category categoría a agregar
     */

    public void addCategory(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
            updatedAt = LocalDateTime.now();
        }
    }
    /**
     * Elimina una categoría del contenido.
     * @param category categoría a eliminar
     */

    public void removeCategory(Category category) {
        categories.remove(category);
        updatedAt = LocalDateTime.now();
    }
    /**
     * Devuelve las categorías asociadas al contenido.
     * @return lista de categorías
     */

    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }
    /**
     * Agrega una etiqueta (tag) al contenido.
     * @param tag etiqueta a agregar
     */

    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
            updatedAt = LocalDateTime.now();
        }
    }
    /**
     * Elimina una etiqueta del contenido.
     * @param tag etiqueta a eliminar
     */

    public void removeTag(Tag tag) {
        tags.remove(tag);
        updatedAt = LocalDateTime.now();
    }
    /**
     * Devuelve las etiquetas asociadas al contenido.
     * @return lista de etiquetas
     */

    public List<Tag> getTags() {
        return new ArrayList<>(tags);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
    /**
     * Busca una palabra clave en los campos del contenido.
     * @param keyword palabra clave
     * @return true si se encuentra, false en caso contrario
     */

    @Override
    public boolean search(String keyword) {
        return getSearchableFields().stream()
                .anyMatch(field -> field.toLowerCase().contains(keyword.toLowerCase()));
    }
    /**
     * Retorna los campos del contenido que pueden ser buscados.
     * @return lista de campos indexables
     */

    @Override
    public List<String> getSearchableFields() {
        return Arrays.asList(title, description, author.getUsername());
    }
    /**
     * Genera un reporte resumido del contenido.
     * @return cadena con detalles del contenido
     */

    @Override
    public String generateReport() {
        return String.format("Contenido: %s | Tipo: %s | Autor: %s | Estado: %s",
                title, this.getClass().getSimpleName(), author.getUsername(), state.getDisplayName());
    }
    /**
     * Obtiene los datos estructurados del contenido para un reporte.
     * @return mapa con información clave
     */

    @Override
    public Map<String, Object> getReportData() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("title", title);
        data.put("type", this.getClass().getSimpleName());
        data.put("author", author.getUsername());
        data.put("state", state.getDisplayName());
        data.put("createdAt", createdAt);
        data.put("categories", categories.size());
        data.put("tags", tags.size());
        return data;
    }
    /**
     * Verifica si un usuario tiene permisos de edición sobre el contenido.
     * @param user usuario a verificar
     * @return true si puede editar, false en caso contrario
     */

    @Override
    public boolean canEdit(User user) {
        return user.getRole() == UserRole.ADMIN || user.getId().equals(author.getId());
    }
    /**
     * Verifica si un usuario tiene permisos de eliminación sobre el contenido.
     * @param user usuario a verificar
     * @return true si puede eliminar, false en caso contrario
     */

    @Override
    public boolean canDelete(User user) {
        return user.getRole() == UserRole.ADMIN;
    }
    /**
     * Actualiza el título y descripción del contenido.
     * @param title nuevo título
     * @param description nueva descripción
     */

    public void update(String title, String description) {
        setTitle(title);
        setDescription(description);
    }
}
