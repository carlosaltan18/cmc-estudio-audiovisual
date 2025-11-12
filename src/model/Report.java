package model;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/**
 * Representa un reporte generado dentro del sistema.
 * Contiene información general como tipo, fecha y datos asociados.
 */

public class Report {
    private final String id;
    private final String type;
    private final LocalDateTime generatedAt;
    private Map<String, Object> data;
    /**
     * Constructor que crea un reporte con un identificador y tipo específicos.
     * @param id identificador único del reporte
     * @param type tipo o título del reporte
     */

    public Report(String id, String type) {
        this.id = id;
        this.type = type;
        this.generatedAt = LocalDateTime.now();
        this.data = new HashMap<>();
    }
    /**
     * Obtiene el ID del reporte.
     * @return ID del reporte
     */

    public String getId() {
        return id;
    }
    /**
     * Obtiene el tipo del reporte.
     * @return tipo del reporte
     */

    public String getType() {
        return type;
    }
    /**
     * Obtiene la fecha y hora de generación del reporte.
     * @return fecha de generación
     */

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    /**
     * Devuelve los datos asociados al reporte.
     * @return mapa con los datos del reporte
     */

    public Map<String, Object> getData() {
        return new HashMap<>(data);
    }
    /**
     * Establece o reemplaza los datos del reporte.
     * @param data mapa con los datos a almacenar
     */

    public void setData(Map<String, Object> data) {
        this.data = new HashMap<>(data);
    }
    /**
     * Devuelve una representación legible del reporte.
     * @return cadena con el tipo y fecha del reporte
     */

    @Override
    public String toString() {
        return String.format("Reporte: %s | Generado: %s", type, generatedAt);
    }
}
