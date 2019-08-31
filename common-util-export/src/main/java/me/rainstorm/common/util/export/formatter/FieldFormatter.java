package me.rainstorm.common.util.export.formatter;

import me.rainstorm.common.util.export.entity.ColMata;

/**
 * 字段导出格式化器
 */
public interface FieldFormatter<Input> {
    /**
     * 根据元数据信息，将输入转换成特定格式
     *
     * @param colMata 元数据
     * @param input   待转换数据
     * @return 转换结果
     */
    String apply(ColMata colMata, Input input);
}
