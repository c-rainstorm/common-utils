package me.rainstorm.common.util.export.formatter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.rainstorm.common.util.export.exception.FieldFormatterDuplicateException;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@SuppressWarnings("unchecked")
@Slf4j
public class FieldFormatterFactory {
    private static Map<Class, FormatterHelper> formatterCacheMap = new HashMap<>(32);

    static {
        Reflections reflections = new Reflections(FieldFormatterFactory.class.getPackage().getName());
        Set<Class<? extends FormatterHelper>> classes = reflections.getSubTypesOf(FormatterHelper.class);
        classes.forEach(formatter -> {
            try {
                if (Modifier.isAbstract(formatter.getModifiers())) {
                    log.info("{} is abstract class, skipped.", formatter.getName());
                    return;
                }

                FormatterHelper formatterHelper = formatter.newInstance();
                Set<Class> klsSet = formatterHelper.getValueTypes();

                klsSet.forEach(valueType -> {
                    register(valueType, formatterHelper);
                });

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    public static <T> FieldFormatter<T> get(@NonNull Class<T> kls) {
        if (formatterCacheMap.containsKey(kls)) {
            return formatterCacheMap.get(kls);
        }
        return formatterCacheMap.get(Object.class);
    }

    public synchronized static void register(@NonNull Class kls, @NonNull FormatterHelper fieldFormatter) {
        if (formatterCacheMap.containsKey(kls)) {
            throw new FieldFormatterDuplicateException(String.format("valueType: %s, exist: %s[%s], new: %s[%s]", kls.getName(),
                    formatterCacheMap.get(kls).getClass().getName(), formatterCacheMap.get(kls).getClass().getClassLoader(),
                    fieldFormatter.getClass().getName(), fieldFormatter.getClass().getClassLoader()));
        }
        formatterCacheMap.put(kls, fieldFormatter);
        log.info(String.format("valueType: %s, new: %s[%s]", kls.getName(),
                fieldFormatter.getClass().getName(), fieldFormatter.getClass().getClassLoader()));
    }
}
