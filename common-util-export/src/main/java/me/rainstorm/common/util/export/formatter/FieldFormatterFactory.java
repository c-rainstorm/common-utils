package me.rainstorm.common.util.export.formatter;

import lombok.NonNull;
import me.rainstorm.common.util.export.exception.FieldFormatterDuplicateException;
import me.rainstorm.common.util.export.exception.FieldFormatterNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
public class FieldFormatterFactory {
    private static Map<Class, FieldFormatter> formatterCacheMap = new HashMap<>(32);

    public static FieldFormatter get(@NonNull Class kls) {
        if (formatterCacheMap.containsKey(kls)) {
            return formatterCacheMap.get(kls);
        }
        throw new FieldFormatterNotFoundException("valueType: " + kls.getName());
    }

    public synchronized static void register(@NonNull Class kls, @NonNull FieldFormatter fieldFormatter) {
        if (formatterCacheMap.containsKey(kls)) {
            throw new FieldFormatterDuplicateException(String.format("valueType: %s, exist: %s, new: %s ",
                    kls.getName(), formatterCacheMap.get(kls).getClass().getName(), fieldFormatter.getClass().getName()));
        }
        formatterCacheMap.put(kls, fieldFormatter);
    }
}
