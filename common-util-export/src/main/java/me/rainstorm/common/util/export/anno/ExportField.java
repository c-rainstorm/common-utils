package me.rainstorm.common.util.export.anno;

import me.rainstorm.common.util.export.util.DateTimeFormatterUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.math.RoundingMode;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标记一个字段为导出字段，可以指定字段顺序和
 *
 * @author chen
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface ExportField {
    /**
     * @return 导出顺序，越小越靠前, order 相同使用属性名按字典序排
     */
    int exportOrder() default Integer.MAX_VALUE;

    /**
     * 默认开启类型自动判断，合适的类型转换为合适的导出格式
     * <p>
     * 数字类型 byte、int、double、float、BigDecimal 默认按数字格式导出，四舍五入，保留两位小数
     * Date             - yyyy-MM-dd HH:mm:ss
     * LocalDate        - yyyy-MM-dd
     * LocalDateTime    - yyyy-MM-dd HH:mm:ss
     * <p>
     * 如果要覆盖默认配置，则将该字段标记为 false，并配置其他字段即可
     * 数字类型：isDigit + roundingMode + precision 有效
     * Date、LocalDate、LocalDateTime: datePattern 有效
     *
     * @return 是否导出为数字
     */
    boolean typeAutoJudge() default true;

    /**
     * @return 字段对应的表头，不填默认是字段名
     */
    String head() default "";

    /**
     * 若字段是日期类型，导出的格式，其他类型无效
     *
     * @return 日期格式
     */
    String datePattern() default DateTimeFormatterUtil.YYYY_MM_DD;

    /**
     * 是否导出数字类型，默认否
     *
     * @return 是否导出为数字
     */
    boolean isDigit() default false;

    /**
     * double、float、BigDecimal 的舍入类型，默认四舍五入
     *
     * @return 舍入类型
     */
    RoundingMode roundingMode() default RoundingMode.HALF_UP;

    /**
     * double、float、BigDecimal 的默认精度，保留两位小数
     *
     * @return 舍入精度
     */
    int precision() default 2;
}
