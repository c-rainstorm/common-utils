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
@Slf4j
public class FieldFormatterFactory {
    private static Map<Class, FormatterHelper> formatterCacheMap = new HashMap<>(32);

    static {
        Reflections reflections = new Reflections(FieldFormatterFactory.class.getPackage().getName());
        Set<Class<? extends FormatterHelper>> classes = reflections.getSubTypesOf(FormatterHelper.class);
        classes.forEach(x -> {
            try {
                if (Modifier.isAbstract(x.getModifiers())) {
                    log.info("{} is abstract class, skipped.", x.getName());
                    return;
                }

                ClassLoader classLoader = FieldFormatterFactory.class.getClassLoader();
                log.info("classLoader: {}; loadClass: {}", classLoader, x.getName());
                classLoader.loadClass(x.getName());

                FormatterHelper formatterHelper = x.newInstance();

                register(formatterHelper.getValueType(), formatterHelper);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    public static FieldFormatter get(@NonNull Class kls) {
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
