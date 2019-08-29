package me.rainstorm.common.util.export.exception;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
public class ExportColumnEmptyException extends Exception {

    public ExportColumnEmptyException() {
    }

    public ExportColumnEmptyException(String message) {
        super(message);
    }

    public ExportColumnEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExportColumnEmptyException(Throwable cause) {
        super(cause);
    }

    public ExportColumnEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
