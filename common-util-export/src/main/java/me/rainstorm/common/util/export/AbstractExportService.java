package me.rainstorm.common.util.export;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.rainstorm.common.util.export.entity.RowMata;
import me.rainstorm.common.util.export.exception.ExportColumnEmptyException;
import me.rainstorm.common.util.export.util.ThreadLocalSimpleDateFormat;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */

@Data
@Slf4j
public abstract class AbstractExportService<E> implements ExportService<E> {
    protected ThreadLocalSimpleDateFormat threadLocalSimpleDateFormat = new ThreadLocalSimpleDateFormat();
    protected RowMata rowMata;
    protected int totalNum;

    public AbstractExportService(@NonNull Class<E> kls) throws ExportColumnEmptyException {
        rowMata = RowMata.parse(kls);
        totalNum = 0;
    }

    @Override
    public void close() throws Exception {
        threadLocalSimpleDateFormat.close();
        log.info("threadLocalSimpleDateFormat removed.");
    }
}
