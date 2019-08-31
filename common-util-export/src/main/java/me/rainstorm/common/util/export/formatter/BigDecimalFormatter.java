package me.rainstorm.common.util.export.formatter;

import me.rainstorm.common.util.export.entity.ColMata;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author baochen1.zhang
 * @date 2019.08.31
 */
public class BigDecimalFormatter extends AbstractFieldFormatter<BigDecimal> {
    private static int defaultPrecision = 2;
    private static RoundingMode defaultRoundingMode = RoundingMode.HALF_UP;
    private static boolean defaultIsDigit = true;

    @Override
    public Class<BigDecimal> getValueType() {
        return BigDecimal.class;
    }

    @Override
    protected boolean isDigit() {
        return true;
    }

    @Override
    public String apply(ColMata colMata, BigDecimal bigDecimal) {
        int precision = colMata.getPrecision();
        RoundingMode roundingMode = colMata.getRoundingMode();
        if (colMata.isTypeAutoJudge()) {
            precision = defaultPrecision;
            roundingMode = defaultRoundingMode;
        }
        return bigDecimal.setScale(precision, roundingMode).toString();
    }
}
