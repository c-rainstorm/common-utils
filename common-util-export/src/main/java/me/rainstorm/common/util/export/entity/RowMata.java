package me.rainstorm.common.util.export.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.rainstorm.common.util.export.anno.ExportField;
import me.rainstorm.common.util.export.exception.ExportColumnEmptyException;
import me.rainstorm.common.util.export.formatter.DefaultFieldFormatter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Getter
@ToString
public class RowMata {
    private List<ColMata> colMataList;

    private RowMata(List<ColMata> colMataList) {
        this.colMataList = colMataList;
    }

    public static RowMata parse(Class<?> kls) throws ExportColumnEmptyException {
        Class<?> origin = kls;

        List<Field> fieldList = new ArrayList<>(50);
        while (!kls.equals(Object.class)) {
            fieldList.addAll(Arrays.asList(kls.getDeclaredFields()));
            kls = kls.getSuperclass();
        }

        List<ColMata> colMataList = fieldList.stream()
                .filter(field -> Objects.nonNull(field.getAnnotation(ExportField.class)))
                .map(RowMata::buildColMata).sorted()
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(colMataList)) {
            throw new ExportColumnEmptyException(origin.getName());
        }

        return new RowMata(colMataList);
    }

    private static ColMata buildColMata(Field field) {
        @NonNull ExportField exportField = field.getAnnotation(ExportField.class);

        return ColMata.builder()
                .order(exportField.exportOrder())
                .property(field.getName())
                .head(StringUtils.defaultIfBlank(exportField.head(), field.getName()))
                .dataPattern(exportField.datePattern())
                .fieldFormatter(new DefaultFieldFormatter())
                .cellType(CellType.STRING).build();
    }
}
