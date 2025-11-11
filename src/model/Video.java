package model;
import java.time.LocalDateTime;


public class Video extends Content {
    private String videoUrl;
    private int duration;
    private String resolution;

    public Video(String id, String title, String description, User author) {
        super(id, title, description, author);
        this.videoUrl = "";
        this.duration = 0;
        this.resolution = "HD";
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        this.updatedAt = LocalDateTime.now();
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[VIDEO] %s | Duración: %d min | Resolución: %s | %s",
                title, duration, resolution, state.getDisplayName());
    }
}
