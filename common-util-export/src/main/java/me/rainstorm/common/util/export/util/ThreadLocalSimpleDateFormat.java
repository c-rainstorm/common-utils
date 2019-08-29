package me.rainstorm.common.util.export.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Slf4j
public class ThreadLocalSimpleDateFormat implements AutoCloseable {
    private ThreadLocal<Map<String, SimpleDateFormat>> simpleDateFormat = ThreadLocal.withInitial(() -> new HashMap<>(10));

    public SimpleDateFormat get(String pattern) {
        return simpleDateFormat.get().computeIfAbsent(pattern, SimpleDateFormat::new);
    }

    @Override
    public void close() {
        simpleDateFormat.remove();
        log.info("removed.");
    }
}
