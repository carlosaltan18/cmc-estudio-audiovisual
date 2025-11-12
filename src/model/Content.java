package model;

import java.time.LocalDateTime;
import java.util.*;
import model.interfaces.*;
import model.enums.ContentState;
import model.enums.UserRole;

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

    @Override
    public void publish() {
        if (state == ContentState.DRAFT) {
            this.state = ContentState.PUBLISHED;
            this.publishedAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }
    }

    @Override
    public void unpublish() {
        if (state == ContentState.PUBLISHED) {
            this.state = ContentState.DRAFT;
            this.publishedAt = null;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void addCategory(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
            updatedAt = LocalDateTime.now();
        }
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        updatedAt = LocalDateTime.now();
    }

    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
            updatedAt = LocalDateTime.now();
        }
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        updatedAt = LocalDateTime.now();
    }

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

    @Override
    public boolean search(String keyword) {
        return getSearchableFields().stream()
                .anyMatch(field -> field.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public List<String> getSearchableFields() {
        return Arrays.asList(title, description, author.getUsername());
    }

    @Override
    public String generateReport() {
        return String.format("Contenido: %s | Tipo: %s | Autor: %s | Estado: %s",
                title, this.getClass().getSimpleName(), author.getUsername(), state.getDisplayName());
    }

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

    @Override
    public boolean canEdit(User user) {
        return user.getRole() == UserRole.ADMIN || user.getId().equals(author.getId());
    }

    @Override
    public boolean canDelete(User user) {
        return user.getRole() == UserRole.ADMIN;
    }

    public void update(String title, String description) {
        setTitle(title);
        setDescription(description);
    }
}
