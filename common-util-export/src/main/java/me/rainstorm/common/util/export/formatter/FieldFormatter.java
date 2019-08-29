package me.rainstorm.common.util.export.formatter;

import java.util.function.Function;

/**
 * 字段导出格式化器
 */
public interface FieldFormatter<T> extends Function<T, String> {
}
