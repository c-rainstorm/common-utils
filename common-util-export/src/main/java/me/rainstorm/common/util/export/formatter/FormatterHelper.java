package me.rainstorm.common.util.export.formatter;

import me.rainstorm.common.util.export.anno.ExportField;

import java.util.Set;

public interface FormatterHelper<Input> extends FieldFormatter<Input> {

    /**
     * @return 待转换类型的 class
     */
    Set<Class> getValueTypes();

    /**
     * 判断是否输出为数字
     *
     * @param exportField
     * @return
     */
    boolean isDigit(ExportField exportField);
}
