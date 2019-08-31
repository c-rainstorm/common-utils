package me.rainstorm.common.util.export.formatter.impl;

import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.formatter.AbstractFieldFormatter;
import me.rainstorm.common.util.export.util.DateTimeFormatterUtil;
import me.rainstorm.common.util.export.util.ThreadLocalSimpleDateFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.31
 */
public class DateFormatter extends AbstractFieldFormatter<Date> {
    private static ThreadLocalSimpleDateFormat threadLocalSimpleDateFormat = new ThreadLocalSimpleDateFormat();

    @Override
    public Set<Class> getValueTypes() {
        Set<Class> kls = new HashSet<>();
        kls.add(Date.class);
        return kls;
    }

    @Override
    public String apply(ColMata colMata, Date date) {
        if (Objects.isNull(date)) {
            return "";
        }

        String pattern = colMata.getDatePattern();
        if (colMata.isTypeAutoJudge()) {
            pattern = DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS;
        }

        return threadLocalSimpleDateFormat.get(pattern).format(date);
    }
}
