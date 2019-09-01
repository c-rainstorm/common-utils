# common-utils
项目中可以使用的通用工具

## 报表导出工具

```java
private void doExport(int sizePreExport, int totalSize, String sheetNamePrefix, int sheetSize, Function<Integer, List<AllInOneClass>> supplier) throws Exception {
    File exportFile = Paths.get(EXPORT_DIR, sheetNamePrefix + "-" +
            DateTimeFormatterUtil.get(DateTimeFormatterUtil.YYYYMMDDHHMMSS).format(LocalDateTime.now())
             + ".xlsx").toFile();
    log.info("file name:" + exportFile.getAbsolutePath());
    try (ExportService<AllInOneClass> exportService = new XLSXExportService<>(AllInOneClass.class, exportFile)) {
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
```