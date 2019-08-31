package me.rainstorm.common.util.export.formatter.impl;

import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.formatter.AbstractFieldFormatter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
public class DefaultFieldFormatter extends AbstractFieldFormatter<Object> {

    @Override
    public Set<Class> getValueTypes() {
        Set<Class> kls = new HashSet<>();
        kls.add(Object.class);
        return kls;
    }

    @Override
    public String apply(ColMata colMata, Object o) {
        return ObjectUtils.defaultIfNull(o, "").toString();
    }
}
