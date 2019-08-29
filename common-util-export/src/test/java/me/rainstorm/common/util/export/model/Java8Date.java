package me.rainstorm.common.util.export.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.rainstorm.common.util.export.anno.ExportField;

import java.time.LocalDateTime;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Java8Date extends CommonClass {
    @ExportField(exportOrder = 3, head = "生日", datePattern = "yyyy-MM-dd")
    private LocalDateTime birthday;
}
