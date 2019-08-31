package me.rainstorm.common.util.export.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import me.rainstorm.common.util.export.anno.ExportField;
import me.rainstorm.common.util.export.formatter.FieldFormatter;

import java.math.RoundingMode;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Builder
@Data
public class ColMata implements Comparable<ColMata> {
    /**
     * @see ExportField#exportOrder()
     */
    private int order;

    /**
     * 字段属性名
     */
    private String property;

    /**
     * @see ExportField#head()
     */
    private String head;

    /**
     * @see ExportField#datePattern()
     */
    private String datePattern;

    /**
     * @see ExportField#typeAutoJudge()
     */
    private boolean typeAutoJudge;

    /**
     * @see ExportField#isDigit()
     */
    private boolean isDigit;

    /**
     * @see ExportField#roundingMode()
     */
    private RoundingMode roundingMode;

    /**
     * @see ExportField#precision()
     */
    private int precision;

    /**
     * value 格式化器
     */
    private FieldFormatter fieldFormatter;

    /**
     * 根据order和property排序，order相同按property的字典序
     *
     * @param colMata 待比较的 head
     * @return 比较结果
     */
    @Override
    public int compareTo(@NonNull ColMata colMata) {
        int orderCompare = Integer.compare(order, colMata.order);
        return orderCompare != 0 ? orderCompare : property.compareTo(colMata.property);
    }
}
