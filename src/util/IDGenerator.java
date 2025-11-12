package util;

import java.util.UUID;

/**
 * Utilidad para la generación de identificadores únicos.
 * Proporciona métodos para generar IDs con prefijos personalizados
 * o IDs simples utilizando UUID (Identificador Único Universal).
 * 
 * @author Carlos
 * @version 1.0
 */
public class IDGenerator {
    
    /**
     * Genera un identificador único con un prefijo personalizado.
     * Combina el prefijo proporcionado con los primeros 8 caracteres
     * de un UUID generado aleatoriamente.
     * 
     * @param prefix Prefijo personalizado para el identificador
     * @return String con el formato {@code prefix_xxxxxxxx}
     * @throws NullPointerException si el prefijo es null
     */
    public static String generate(String prefix) {
        return prefix + "_" + UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * Genera un identificador único simple sin prefijo.
     * Retorna los primeros 8 caracteres de un UUID generado aleatoriamente.
     * 
     * 
     * @return String de 8 caracteres hexadecimales que representan un identificador único
     */
    public static String generateSimple() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}