package model;

import java.time.LocalDateTime;

public class Article extends Content {
    private String content;
    private int wordCount;

    public Article(String id, String title, String description, User author) {
        super(id, title, description, author);
        this.content = "";
        this.wordCount = 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        calculateWordCount();
        this.updatedAt = LocalDateTime.now();
    }

    public int getWordCount() {
        return wordCount;
    }

    private void calculateWordCount() {
        this.wordCount = content.trim().isEmpty() ? 0 : content.trim().split("\\s+").length;
    }

    @Override
    public String toString() {
        return String.format("[ARTÍCULO] %s | Palabras: %d | %s", title, wordCount, state.getDisplayName());
    }
}
