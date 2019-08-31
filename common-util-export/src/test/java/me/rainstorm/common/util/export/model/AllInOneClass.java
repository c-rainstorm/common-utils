package me.rainstorm.common.util.export.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.common.util.export.anno.ExportField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AllInOneClass extends CommonClass {
    @ExportField(exportOrder = 3)
    private char charRow;
    @ExportField(exportOrder = 4)
    private Character character;
    @ExportField(exportOrder = 5)
    private byte byteRaw;
    @ExportField(exportOrder = 6)
    private Byte byteType;
    @ExportField(exportOrder = 7)
    private short shortRaw;
    @ExportField(exportOrder = 8)
    private Short shortType;
    @ExportField(exportOrder = 9)
    private int intRaw;
    @ExportField(exportOrder = 10)
    private Integer intType;
    @ExportField(exportOrder = 11)
    private long longRaw;
    @ExportField(exportOrder = 12)
    private Long longType;
    @ExportField(exportOrder = 13)
    private float floatRaw;
    @ExportField(exportOrder = 14)
    private Float floatType;
    @ExportField(exportOrder = 15)
    private double doubleRaw;
    @ExportField(exportOrder = 16)
    private Double doubleType;
    @ExportField(exportOrder = 17)
    private BigDecimal bigDecimal;
    @ExportField(exportOrder = 18)
    private boolean boolRaw;
    @ExportField(exportOrder = 19)
    private Boolean boolType;
    @ExportField(exportOrder = 20)
    private Date java5Date;
    @ExportField(exportOrder = 21)
    private LocalDateTime java8DateTime;
    @ExportField(exportOrder = 22)
    private LocalDate java8Date;
    @ExportField(exportOrder = 23)
    private LocalTime java8Time;
}
