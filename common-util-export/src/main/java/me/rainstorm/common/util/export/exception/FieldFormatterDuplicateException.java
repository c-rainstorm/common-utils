package me.rainstorm.common.util.export.exception;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
public class FieldFormatterDuplicateException extends RuntimeException {

    public FieldFormatterDuplicateException() {
    }

    public FieldFormatterDuplicateException(String message) {
        super(message);
    }

    public FieldFormatterDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldFormatterDuplicateException(Throwable cause) {
        super(cause);
    }

    public FieldFormatterDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
