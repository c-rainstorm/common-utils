package me.rainstorm.common.util.export.model;

import lombok.Data;
import me.rainstorm.common.util.export.anno.ExportField;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Data
public class CommonClass {
    @ExportField(exportOrder = 1, head = "用户名")
    private String username;
    @ExportField(exportOrder = 2, head = "密码")
    private String password;
}
