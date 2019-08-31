package me.rainstorm.common.util.export.excel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.rainstorm.common.util.export.AbstractExportService;
import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.exception.ExportColumnEmptyException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Slf4j
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractExcelExportService<E> extends AbstractExportService<E> {
    private static final int SHEET_NAME_PREFIX_LEN = 25;

    /**
     * EXCEL 工作簿
     */
    protected Workbook workbook;

    /**
     * 输出流
     */
    protected OutputStream outputStream;

    /**
     * 单个sheet的大小
     */
    protected int sheetSize;
    /**
     * sheet名前缀，
     */
    protected String sheetNamePrefix;


    public AbstractExcelExportService(@NonNull Class<E> kls, int sheetSize, String sheetNamePrefix, OutputStream outputStream) throws ExportColumnEmptyException {
        super(kls);
        this.sheetSize = sheetSize;
        this.sheetNamePrefix = StringUtils.left(sheetNamePrefix, SHEET_NAME_PREFIX_LEN);
        this.outputStream = outputStream;
    }


    @Override
    public void append(List<E> dataList) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (CollectionUtils.isEmpty(dataList)) {
            log.info("导出数据集为空，直接退出");
            return;
        }

        Sheet currentSheet;
        int currentNumberOfSheet = workbook.getNumberOfSheets();
        if (currentNumberOfSheet == 0) {
            currentSheet = createSheet(currentNumberOfSheet);
        } else {
            currentSheet = workbook.getSheetAt(currentNumberOfSheet - 1);
            if (currentSheet.getLastRowNum() + 1 >= sheetSize) {
                currentSheet = createSheet(currentNumberOfSheet);
            }
        }

        int curRow = currentSheet.getLastRowNum();

        for (E data : dataList) {
            if (curRow + 1 >= sheetSize) {
                currentSheet = createSheet(currentNumberOfSheet);
                curRow = currentSheet.getLastRowNum();
            }
            appendRow(currentSheet, ++curRow, ++totalNum, data);
        }
    }

    private Sheet createSheet(int currentNumberOfSheet) {
        Sheet sheet = workbook.createSheet(sheetNamePrefix + "_" + currentNumberOfSheet);

        // 创建 Head
        Row head = sheet.createRow(0);
        int headCol = 0;
        head.createCell(headCol++).setCellValue("序号");

        for (ColMata colMata : rowMata.getColMataList()) {
            head.createCell(headCol++).setCellValue(colMata.getHead());
        }

        return sheet;
    }

    @SuppressWarnings("unchecked")
    private void appendRow(Sheet currentSheet, int curRow, int currentRecordNum, E dataObject) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        log.debug("exporting: {}", dataObject.toString());
        int curCol = 0;
        Row row = currentSheet.createRow(curRow);
        row.createCell(curCol++).setCellValue(currentRecordNum);
        for (ColMata colMata : rowMata.getColMataList()) {
            //设置单元格的内容
            Cell cell = row.createCell(curCol++);

            Object value = PropertyUtils.getProperty(dataObject, colMata.getProperty());

            String formatted = colMata.getFieldFormatter().apply(colMata, value);
            if (colMata.isDigit()) {
                cell.setCellValue(Double.parseDouble(formatted));
            } else {
                cell.setCellValue(formatted);
            }
        }
    }

    @Override
    public void close() throws Exception {
        super.close();

        if (workbook != null && outputStream != null) {
            workbook.write(outputStream);
            outputStream.close();
            workbook = null;
            outputStream = null;
        }

        log.info("closed.");
    }
}
