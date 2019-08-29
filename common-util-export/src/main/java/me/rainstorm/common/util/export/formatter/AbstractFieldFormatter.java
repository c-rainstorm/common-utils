package me.rainstorm.common.util.export.formatter;

import lombok.Data;
import me.rainstorm.common.util.export.entity.ColMata;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Data
public abstract class AbstractFieldFormatter<T> implements FieldFormatter<T> {
    protected ColMata colMata;
}
