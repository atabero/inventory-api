package org.atabero.inventory.model.enums;

/**
 * Enum que representa el estado de una operación o proceso.
 * <p>
 * Puede indicar si la operación fue exitosa o si ocurrió un error.
 * </p>
 */
public enum OperationStatus {

    /**
     * La operación se completó exitosamente.
     */
    SUCCESS,

    /**
     * La operación falló o ocurrió un error.
     */
    ERROR;
}
