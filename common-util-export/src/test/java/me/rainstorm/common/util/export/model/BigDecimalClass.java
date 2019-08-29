package me.rainstorm.common.util.export.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.common.util.export.anno.ExportField;

import java.math.BigDecimal;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BigDecimalClass extends CommonClass {
    @ExportField(exportOrder = 3, head = "账户余额")
    private BigDecimal balance;
}
