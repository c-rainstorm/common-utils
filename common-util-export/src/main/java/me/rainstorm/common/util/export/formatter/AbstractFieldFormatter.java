package me.rainstorm.common.util.export.formatter;

import lombok.Data;
import me.rainstorm.common.util.export.anno.ExportField;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Data
public abstract class AbstractFieldFormatter<Input> implements FormatterHelper<Input> {

    @Override
    public boolean isDigit(ExportField exportField) {
        if (exportField.typeAutoJudge()) {
            return isDigit();
        }
        return exportField.isDigit();
    }

    protected boolean isDigit() {
        return false;
    }

}
