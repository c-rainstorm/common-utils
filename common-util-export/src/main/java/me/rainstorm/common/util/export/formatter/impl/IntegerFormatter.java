package me.rainstorm.common.util.export.formatter.impl;

import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.formatter.AbstractFieldFormatter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.31
 */
public class IntegerFormatter extends AbstractFieldFormatter<Number> {

    @Override
    protected boolean isDigit() {
        return true;
    }

    @Override
    public Set<Class> getValueTypes() {
        Set<Class> kls = new HashSet<>();
        kls.add(Byte.TYPE);
        kls.add(Byte.class);
        kls.add(Short.TYPE);
        kls.add(Short.class);
        kls.add(Integer.TYPE);
        kls.add(Integer.class);
        return kls;
    }

    @Override
    public String apply(ColMata colMata, Number number) {
        return Objects.isNull(number) ? "" : number.toString();
    }
}
