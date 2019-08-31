package me.rainstorm.common.util.export.excel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.rainstorm.common.util.export.exception.ExportColumnEmptyException;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

/**
 * 基于 apache SXSSF 组件
 * <p>
 * 支持超大数据量导出
 *
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Slf4j
public class XLSXExportService<E> extends AbstractExcelExportService<E> {
    private static final int SHEET_HEAD_ROW_NUM = 1;
    private static final int EXCEL_XLSX_SINGLE_SHEET_DATA_SIZE = 1000000;

    public XLSXExportService(@NonNull Class<E> kls, @NonNull String sheetNamePrefix, @NonNull OutputStream outputStream, int sheetSize) throws ExportColumnEmptyException {
        super(kls, Math.max(Math.min(sheetSize, EXCEL_XLSX_SINGLE_SHEET_DATA_SIZE), 1) + SHEET_HEAD_ROW_NUM, sheetNamePrefix, outputStream);

        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
        sxssfWorkbook.setCompressTempFiles(true);
        setWorkbook(sxssfWorkbook);
    }

    public XLSXExportService(@NonNull Class<E> kls, @NonNull String sheetNamePrefix, @NonNull OutputStream outputStream) throws ExportColumnEmptyException {
        this(kls, sheetNamePrefix, outputStream, EXCEL_XLSX_SINGLE_SHEET_DATA_SIZE);
    }

    public XLSXExportService(@NonNull Class<E> kls, @NonNull File exportFile, int sheetSize) throws FileNotFoundException, ExportColumnEmptyException {
        this(kls, exportFile.getName(), new FileOutputStream(exportFile), sheetSize);
    }

    public XLSXExportService(@NonNull Class<E> kls, @NonNull File exportFile) throws FileNotFoundException, ExportColumnEmptyException {
        this(kls, exportFile.getName(), new FileOutputStream(exportFile));
    }

    public XLSXExportService(@NonNull Class<E> kls, @NonNull String exportFile) throws FileNotFoundException, ExportColumnEmptyException {
        this(kls, Paths.get(exportFile).toFile());
    }
}
