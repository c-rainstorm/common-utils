package me.rainstorm.common.util.export.formatter.impl;

import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.formatter.AbstractFieldFormatter;
import me.rainstorm.common.util.export.formatter.FieldFormatter;
import me.rainstorm.common.util.export.formatter.FieldFormatterFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.31
 */
public class DoubleFormatter extends AbstractFieldFormatter<Number> {

    @Override
    protected boolean isDigit() {
        return true;
    }

    @Override
    public Set<Class> getValueTypes() {
        Set<Class> kls = new HashSet<>();
        kls.add(Float.class);
        kls.add(Float.TYPE);
        kls.add(Double.class);
        kls.add(Double.TYPE);
        return kls;
    }

    @Override
    public String apply(ColMata colMata, Number number) {
        FieldFormatter<BigDecimal> decimalFieldFormatter = FieldFormatterFactory.get(BigDecimal.class);

        if (Objects.isNull(number)) {
            return "";
        }

        return decimalFieldFormatter.apply(colMata, new BigDecimal(String.valueOf(number)));
    }
}
