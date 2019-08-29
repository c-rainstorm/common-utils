package me.rainstorm.common.util.export.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.common.util.export.anno.ExportField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AllInOneClass extends CommonClass {
    @ExportField(exportOrder = 3, head = "intType")
    private int intType;

    @ExportField(exportOrder = 4)
    private boolean boolType;

    @ExportField(exportOrder = 6)
    private Date java5Date;

    @ExportField(datePattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime java8DateTime;

    @ExportField
    private LocalDate java8Date;

    @ExportField
    private BigDecimal bigDecimal;

    @ExportField
    private Integer integer;
}
