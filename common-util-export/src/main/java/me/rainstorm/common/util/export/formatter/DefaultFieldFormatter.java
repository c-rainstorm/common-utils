package me.rainstorm.common.util.export.formatter;

import me.rainstorm.common.util.export.entity.ColMata;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
public class DefaultFieldFormatter extends AbstractFieldFormatter<Object> {

    @Override
    public Class<Object> getValueType() {
        return Object.class;
    }

    @Override
    public String apply(ColMata colMata, Object o) {
        return ObjectUtils.defaultIfNull(o, "").toString();
    }
}
