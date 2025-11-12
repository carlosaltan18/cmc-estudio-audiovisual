package model.interfaces;

import java.util.Map;

/**
 * Interfaz que define el contrato para objetos que pueden generar reportes.
 * Permite extraer datos estructurados para anÃ¡lisis y reportes.
 */
public interface IReportable {
    String generateReport();
    Map<String, Object> getReportData();
}
