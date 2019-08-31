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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

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
    public void functionTest3() throws Exception {
        doExport(1, 10, "functionTest-s3", 3);
    }

    @Test
    public void functionTest5() throws Exception {
        doExport(1, 10, "functionTest-s5", 5);
    }

    @Test
    public void functionTest10() throws Exception {
        doExport(1, 10, "functionTest-s10", 10);
    }

    @Test
    public void functionTest11() throws Exception {
        doExport(1, 10, "functionTest-s11", 11);
    }

    @Test
    public void functionTest_1() throws Exception {
        doExport(1, 10, "functionTest-s-1", -1);
    }

    @Test
    public void functionTestSheetNum() throws Exception {
        doExport(10, 100000, "functionTest-s-1", 10);
    }

    @Test
    public void functionTestTotal10000_1000() throws Exception {
        //2984ms  rowAccessWindowSize 100
        //2982ms  rowAccessWindowSize 1000
        doExport(1000, 10000, "functionTest-total-1W-1000", 1000000, this::buildDataList);
    }

    @Test
    public void functionTestTotal10000_2000() throws Exception {
        //3087ms rowAccessWindowSize 100
        //2964ms rowAccessWindowSize 1000
        doExport(2000, 10000, "functionTest-total-1W-2000", 1000000, this::buildDataList);
    }

    @Test
    public void functionTestTotal10000_3000() throws Exception {
        //3186ms rowAccessWindowSize 1000
        doExport(3000, 10000, "functionTest-total-1W-3000", 1000000, this::buildDataList);
    }

    @Test
    public void functionTestTotal100000_1000() throws Exception {
        //10135ms rowAccessWindowSize 100
        //10055ms  rowAccessWindowSize 1000
        doExport(1000, 100000, "functionTest-total-10W-1000", 1000000, this::buildDataList);
    }

    @Test
    public void functionTestTotal100000_2000() throws Exception {
        //10155ms rowAccessWindowSize 100
        //10567ms rowAccessWindowSize 1000
        doExport(2000, 100000, "functionTest-total-10W-2000", 1000000, this::buildDataList);
    }

    @Test
    public void functionTestTotal100000_3000() throws Exception {
        //10457ms rowAccessWindowSize 100
        //10620ms rowAccessWindowSize 1000
        doExport(3000, 100000, "functionTest-total-10W-3000", 1000000, this::buildDataList);
    }

    @Test
    public void functionTestTotal1000000_1000() throws Exception {
        //78706ms rowAccessWindowSize 100
        doExport(1000, 1000000, "functionTest-total-100W-1000", 1000000, this::buildDataList);
    }

    @Test
    public void functionTestEmpty() throws Exception {
        doExport(1, 10, "functionTestEmpty", 1000, this::buildEmptyList);
    }

    private void doExport(int sizePreExport, int totalSize, String sheetNamePrefix, int sheetSize) throws Exception {
        doExport(sizePreExport, totalSize, sheetNamePrefix, sheetSize, this::buildDataList);
    }

    private void doExport(int sizePreExport, int totalSize, String sheetNamePrefix, int sheetSize, Function<Integer, List<AllInOneClass>> supplier) throws Exception {
        File exportFile = Paths.get(EXPORT_DIR, sheetNamePrefix + "-" +
                DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYYMMDDHHMMSS).format(LocalDateTime.now()) + ".xlsx").toFile();
        log.info("file name:" + exportFile.getAbsolutePath());
        try (ExportService<AllInOneClass> exportService = new XLSXExportService<>(AllInOneClass.class, exportFile, sheetSize)) {
            long total = 0;
            for (int i = 0; i < totalSize; i += sizePreExport) {
                List<AllInOneClass> dataList = supplier.apply(sizePreExport);
                long start = System.currentTimeMillis();
                exportService.append(dataList);
                total += (System.currentTimeMillis() - start);
                log.info("导出 {} 条数据耗时 {}ms", i + sizePreExport, total);
            }
        }
    }

    private List<AllInOneClass> buildEmptyList(int sizePreExport) {
        List<AllInOneClass> dataList = new ArrayList<>(sizePreExport);
        for (int i = 0; i < sizePreExport; i++) {
            dataList.add(new AllInOneClass());
        }
        return dataList;
    }

    private List<AllInOneClass> buildDataList(int sizePreExport) {
        List<AllInOneClass> dataList = new ArrayList<>(sizePreExport);
        for (int i = 0; i < sizePreExport; i++) {
            dataList.add(buildAllInOneClass());
        }
        return dataList;
    }

    private AllInOneClass buildAllInOneClass() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        AllInOneClass data = new AllInOneClass();
        data.setCharRow('x');
        data.setCharacter('x');
        data.setByteRaw((byte) random.nextInt());
        data.setByteType((byte) random.nextInt());
        data.setShortRaw((short) random.nextInt());
        data.setShortType((short) random.nextInt());
        data.setIntRaw(random.nextInt());
        data.setIntType(random.nextInt());
        data.setLongRaw(random.nextLong());
        data.setLongType(random.nextLong());
        data.setFloatRaw(random.nextFloat());
        data.setFloatType(random.nextFloat());
        data.setDoubleRaw(random.nextDouble());
        data.setDoubleType(random.nextDouble());
        data.setBigDecimal(new BigDecimal(random.nextDouble()));
        data.setBoolRaw(false);
        data.setBoolType(false);
        data.setJava5Date(new Date());
        data.setJava8DateTime(LocalDateTime.now());
        data.setJava8Date(LocalDate.now());
        data.setJava8Time(LocalTime.now());
        data.setBigDecimal(BigDecimal.valueOf(random.nextInt() * random.nextDouble()));
        data.setUsername(UUID.randomUUID().toString());
        data.setPassword(UUID.randomUUID().toString());
        return data;
    }

}
