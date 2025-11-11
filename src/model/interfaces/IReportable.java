package model.interfaces;

import java.util.Map;

/**
 * Interfaz que define el contrato para objetos que pueden generar reportes.
 * Permite extraer datos estructurados para análisis y reportes.
 */
public interface IReportable {

    /**
     * Genera un reporte en formato de texto legible
     * @return String con el reporte formateado
     */
    String generateReport();

    /**
     * Obtiene los datos del objeto en formato de mapa clave-valor
     * Útil para exportación o procesamiento de datos
     * @return Map con los datos relevantes del objeto
     */
    Map<String, Object> getReportData();

    /**
     * Obtiene un resumen breve del objeto para reportes
     * @return String con resumen de una línea
     */
    default String getSummary() {
        return "Resumen no disponible";
    }
}
