package model;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Report {
    private final String id;
    private final String type;
    private final LocalDateTime generatedAt;
    private Map<String, Object> data;

    public Report(String id, String type) {
        this.id = id;
        this.type = type;
        this.generatedAt = LocalDateTime.now();
        this.data = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public Map<String, Object> getData() {
        return new HashMap<>(data);
    }

    public void setData(Map<String, Object> data) {
        this.data = new HashMap<>(data);
    }

    @Override
    public String toString() {
        return String.format("Reporte: %s | Generado: %s", type, generatedAt);
    }
}
