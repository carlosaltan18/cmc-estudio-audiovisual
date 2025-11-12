package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad para el registro de eventos y mensajes de la aplicación.
 * Proporciona métodos para registrar mensajes de información, error, éxito y depuración.
 * Cada mensaje incluye un timestamp automático en formato yyyy-MM-dd HH:mm:ss.
 * 
 * @author Carlos
 * @version 1.0
 */
public class Logger {
    /**
     * Formateador de fecha y hora para los mensajes del log.
     * Formato: yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Registra un mensaje de información.
     * Imprime en la consola un mensaje con el prefijo [INFO] y timestamp actual.
     * Se utiliza para eventos normales de la aplicación.
     * 
     * @param message Mensaje informativo a registrar
     */
    public static void info(String message) {
        System.out.println("[INFO] " + LocalDateTime.now().format(formatter) + " - " + message);
    }

    /**
     * Registra un mensaje de error.
     * Imprime en la consola un mensaje con el prefijo [ERROR] y timestamp actual.
     * Se utiliza para eventos de error durante la ejecución de la aplicación.
     * 
     * @param message Mensaje de error a registrar
     */
    public static void error(String message) {
        System.out.println("[ERROR] " + LocalDateTime.now().format(formatter) + " - " + message);
    }

    /**
     * Registra un mensaje de éxito.
     * Imprime en la consola un mensaje con el prefijo [SUCCESS] y timestamp actual.
     * Se utiliza para confirmar operaciones exitosas en la aplicación.
     * 
     * @param message Mensaje de éxito a registrar
     */
    public static void success(String message) {
        System.out.println("[SUCCESS] " + LocalDateTime.now().format(formatter) + " - " + message);
    }

    /**
     * Registra un mensaje de depuración.
     * Imprime en la consola un mensaje con el prefijo [DEBUG] y timestamp actual.
     * Se utiliza para propósitos de depuración y análisis detallado del flujo de la aplicación.
     * 
     * @param message Mensaje de depuración a registrar
     */
    public static void debug(String message) {
        System.out.println("[DEBUG] " + LocalDateTime.now().format(formatter) + " - " + message);
    }
}
