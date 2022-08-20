package com.sika.code.demo.interfaces.common.migrate.constant;

import com.sika.code.core.base.constant.BaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 *  迁移类型枚举
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 18:33
 */
@Getter
@AllArgsConstructor
public enum MigrateTypeEnum implements BaseTypeEnum<Integer> {
    DOUBLE_WRITE(10,"流量双写"),
    CONTRAST(20,"流量对比"),
    SWITCH(30,"流量切换"),
    ;
    private final Integer type;
    private final String desc;
}