package me.rainstorm.common.util.export.entity;

import lombok.extern.slf4j.Slf4j;
import me.rainstorm.common.util.export.exception.ExportColumnEmptyException;
import me.rainstorm.common.util.export.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Slf4j
public class RowMataTest {
    @Test
    public void emptyTest() throws ExportColumnEmptyException {
        RowMata rowMata = RowMata.parse(Empty.class);

        log.info(rowMata.toString());

        assert CollectionUtils.isEmpty(rowMata.getColMataList());
    }

    @Test
    public void commonClass() throws ExportColumnEmptyException {
        RowMata rowMata = RowMata.parse(CommonClass.class);

        log.info(rowMata.toString());

        assert CollectionUtils.isNotEmpty(rowMata.getColMataList());
        assert rowMata.getColMataList().size() == 2;
    }

    @Test
    public void Java5Date() throws ExportColumnEmptyException {
        RowMata rowMata = RowMata.parse(Java5Date.class);

        log.info(rowMata.toString());

        assert CollectionUtils.isNotEmpty(rowMata.getColMataList());
        assert rowMata.getColMataList().size() == 3;
    }

    @Test
    public void Java8Date() throws ExportColumnEmptyException {
        RowMata rowMata = RowMata.parse(Java8Date.class);

        log.info(rowMata.toString());

        assert CollectionUtils.isNotEmpty(rowMata.getColMataList());
        assert rowMata.getColMataList().size() == 3;
    }

    @Test
    public void BigDecimal() throws ExportColumnEmptyException {
        RowMata rowMata = RowMata.parse(BigDecimalClass.class);

        log.info(rowMata.toString());

        assert CollectionUtils.isNotEmpty(rowMata.getColMataList());
        assert rowMata.getColMataList().size() == 3;
    }

    @Test
    public void AllInOneClass() throws ExportColumnEmptyException {
        RowMata rowMata = RowMata.parse(AllInOneClass.class);

        log.info(rowMata.toString());

        assert CollectionUtils.isNotEmpty(rowMata.getColMataList());
        assert rowMata.getColMataList().size() == 9;
    }
}
