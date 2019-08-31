package me.rainstorm.common.util.export.formatter.impl;

import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.formatter.AbstractFieldFormatter;
import me.rainstorm.common.util.export.util.DateTimeFormatterUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.31
 */
public class Java8DateFormatter extends AbstractFieldFormatter<TemporalAccessor> {

    @Override
    public Set<Class> getValueTypes() {
        Set<Class> kls = new HashSet<>();
        kls.add(LocalDateTime.class);
        kls.add(LocalDate.class);
        kls.add(LocalTime.class);
        return kls;
    }

    @Override
    public String apply(ColMata colMata, TemporalAccessor localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return "";
        }

        String pattern = colMata.getDatePattern();
        if (colMata.isTypeAutoJudge()) {
            if (localDateTime instanceof LocalTime) {
                pattern = DateTimeFormatterUtil.HH_MM_SS;
            } else if (localDateTime instanceof LocalDate) {
                pattern = DateTimeFormatterUtil.YYYY_MM_DD;
            } else {
                pattern = DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS;
            }
        }

        return DateTimeFormatterUtil.get(pattern).format(localDateTime);
    }
}
