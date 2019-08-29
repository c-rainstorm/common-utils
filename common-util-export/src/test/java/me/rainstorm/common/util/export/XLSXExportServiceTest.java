package me.rainstorm.common.util.export;

import lombok.extern.slf4j.Slf4j;
import me.rainstorm.common.util.export.excel.XLSXExportService;
import me.rainstorm.common.util.export.model.AllInOneClass;
import me.rainstorm.common.util.export.util.DateTimeFormatterUtil;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Slf4j
public class XLSXExportServiceTest {
    private static final String EXPORT_DIR = "/tmp/export/";

    @Test
    public void dateTimeFormatterProcessLocalDate() {
        log.info(DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYY_MM_DD).format(LocalDate.now()));
        log.info(DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYYMMDD).format(LocalDate.now()));
        log.info(DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM).format(LocalDate.now()));
    }

    @Test
    public void functionTest() throws Exception {
        doExport(1, 10, "functionTest");
    }

    private void doExport(int sizePreExport, int totalSize, String sheetNamePrefix) throws Exception {

        File exportFile = Paths.get(EXPORT_DIR, sheetNamePrefix + "-" +
                DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYYMMDDHHMMSS).format(LocalDateTime.now()) + ".xlsx").toFile();
        log.info("file name:" + exportFile.getAbsolutePath());
        try (ExportService<AllInOneClass> exportService = new XLSXExportService<>(AllInOneClass.class, exportFile)) {
            for (int i = 0; i < totalSize; i += sizePreExport) {
                List<AllInOneClass> dataList = buildDataList(sizePreExport);
                long start = System.currentTimeMillis();
                exportService.append(dataList);
                log.info("导出 {} 条数据耗时 {}ms", dataList.size(), System.currentTimeMillis() - start);
            }
        }
    }

    private List<AllInOneClass> buildDataList(int sizePreExport) {
        List<AllInOneClass> dataList = new ArrayList<>(sizePreExport);
        for (int i = 0; i < sizePreExport; i++) {
            dataList.add(buildAllInOnClass());
        }
        return dataList;
    }

    private AllInOneClass buildAllInOnClass() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        AllInOneClass data = new AllInOneClass();
        data.setIntType(random.nextInt());
        data.setBoolType(false);
        data.setJava5Date(new Date());
        data.setJava8DateTime(LocalDateTime.now());
        data.setJava8Date(LocalDate.now());
        data.setBigDecimal(BigDecimal.valueOf(random.nextInt() * random.nextDouble()));
        data.setInteger(random.nextInt());
        data.setUsername(UUID.randomUUID().toString());
        data.setPassword(UUID.randomUUID().toString());
        return data;
    }

}
