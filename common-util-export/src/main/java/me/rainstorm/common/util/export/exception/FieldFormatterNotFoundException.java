package me.rainstorm.common.util.export.exception;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
public class FieldFormatterNotFoundException extends RuntimeException {

    public FieldFormatterNotFoundException() {
    }

    public FieldFormatterNotFoundException(String message) {
        super(message);
    }

    public FieldFormatterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldFormatterNotFoundException(Throwable cause) {
        super(cause);
    }

    public FieldFormatterNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
