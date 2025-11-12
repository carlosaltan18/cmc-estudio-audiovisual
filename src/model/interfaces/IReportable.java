package model.interfaces;

import java.util.Map;

/**
 * Interfaz que define el contrato para objetos que pueden generar reportes.
 * Permite extraer datos estructurados para análisis y reportes.
 */
public interface IReportable {
    /**
     * Genera un reporte en formato de texto del objeto.
     *
     * @return Una representación textual del reporte con información relevante
     */
    String generateReport();

    /**
     * Obtiene los datos del objeto en formato estructurado para reportes.
     * El mapa contiene pares clave-valor con la información relevante del objeto.
     *
     * @return Un mapa con los datos estructurados del objeto
     */
    Map<String, Object> getReportData();
}
