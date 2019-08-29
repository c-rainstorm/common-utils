package me.rainstorm.common.util.export;

import java.util.List;

/**
 * 文件导出接口
 *
 * @author chen
 */
public interface ExportService<E> extends AutoCloseable {

    /**
     * 追加数据到报表
     *
     * @param data 待追加的数据
     */
    void append(List<E> data) throws Exception;
}
