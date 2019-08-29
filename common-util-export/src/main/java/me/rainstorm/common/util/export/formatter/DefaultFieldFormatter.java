package me.rainstorm.common.util.export.formatter;

import org.apache.commons.lang3.ObjectUtils;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
public class DefaultFieldFormatter extends AbstractFieldFormatter<Object> {

    @Override
    public String apply(Object o) {
        return ObjectUtils.defaultIfNull(o, "").toString();
    }
}
