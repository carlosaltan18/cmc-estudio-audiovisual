package model;
import java.time.LocalDateTime;

/**
 * Representa un contenido de tipo video dentro del sistema.
 * Incluye información sobre duración, resolución y URL del video.
 */

public class Video extends Content {
    private String videoUrl;
    private int duration;
    private String resolution;
    /**
     * Constructor de la clase Video.
     * @param id identificador único
     * @param title título del video
     * @param description descripción breve
     * @param author autor del contenido
     */

    public Video(String id, String title, String description, User author) {
        super(id, title, description, author);
        this.videoUrl = "";
        this.duration = 0;
        this.resolution = "HD";
    }
    /**
     * Obtiene la URL del video.
     * @return URL del video
     */

    public String getVideoUrl() {
        return videoUrl;
    }
    /**
     * Define la URL del video y actualiza la fecha de modificación.
     * @param videoUrl nueva URL
     */

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        this.updatedAt = LocalDateTime.now();
    }
    /**
     * Obtiene la duración del video.
     * @return duración en minutos
     */

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        this.updatedAt = LocalDateTime.now();
    }
    /**
     * Obtiene la resolución del video.
     * @return resolución (por ejemplo, "HD", "4K")
     */

    public String getResolution() {
        return resolution;
    }
    /**
     * Define la resolución del video y actualiza la fecha de modificación.
     * @param resolution nueva resolución
     */

    public void setResolution(String resolution) {
        this.resolution = resolution;
        this.updatedAt = LocalDateTime.now();
    }
    /**
     * Devuelve una representación textual del video.
     * @return cadena con título, duración, resolución y estado
     */

    @Override
    public String toString() {
        return String.format("[VIDEO] %s | Duracion: %d min | Resolucion: %s | %s",
                title, duration, resolution, state.getDisplayName());
    }
}
