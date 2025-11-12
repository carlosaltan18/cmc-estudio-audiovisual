package model;
import java.time.LocalDateTime;

public class Image extends Content{
    private String imageUrl;
    private int width;
    private int height;
    private double fileSize;

    public Image(String id, String title, String description, User author) {
        super(id, title, description, author);
        this.imageUrl = "";
        this.width = 0;
        this.height = 0;
        this.fileSize = 0.0;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setDimensions(int width, int height, double fileSize) {
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[IMAGEN] %s | ResoluciÃ³n: %dx%d | TamaÃ±o: %.2f MB | %s",
                title, width, height, fileSize, state.getDisplayName());
    }
}
