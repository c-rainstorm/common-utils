package me.rainstorm.common.util.export.formatter;

import me.rainstorm.common.util.export.anno.ExportField;

public interface FormatterHelper<Input> extends FieldFormatter<Input> {

    /**
     * @return 待转换类型的 class
     */
    Class<Input> getValueType();

    /**
     * 判断是否输出为数字
     *
     * @param exportField
     * @return
     */
    boolean isDigit(ExportField exportField);
}
