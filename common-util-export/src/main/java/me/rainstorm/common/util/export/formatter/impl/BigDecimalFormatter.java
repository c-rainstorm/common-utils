package me.rainstorm.common.util.export.formatter.impl;

import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.formatter.AbstractFieldFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.31
 */
public class BigDecimalFormatter extends AbstractFieldFormatter<BigDecimal> {

    @Override
    public Set<Class> getValueTypes() {
        Set<Class> kls = new HashSet<>();
        kls.add(BigDecimal.class);
        return kls;
    }

    @Override
    protected boolean isDigit() {
        return true;
    }

    @Override
    public String apply(ColMata colMata, BigDecimal bigDecimal) {
        if (Objects.isNull(bigDecimal)) {
            return "";
        }

        int precision = colMata.getPrecision();
        RoundingMode roundingMode = colMata.getRoundingMode();
        if (colMata.isTypeAutoJudge()) {
            precision = 2;
            roundingMode = RoundingMode.HALF_UP;
        }
        return bigDecimal.setScale(precision, roundingMode).toString();
    }
}
