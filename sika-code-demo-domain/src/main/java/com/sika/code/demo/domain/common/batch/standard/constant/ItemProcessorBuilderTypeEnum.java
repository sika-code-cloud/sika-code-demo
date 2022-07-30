package com.sika.code.demo.domain.common.batch.standard.constant;

import com.sika.code.demo.domain.common.batch.core.builder.BaseItemProcessorBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.processor.StandardProcessorBuilder;
import com.sika.code.core.base.constant.BaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 读取器构建者枚举
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 18:09
 */
@Getter
@AllArgsConstructor
public enum ItemProcessorBuilderTypeEnum implements BaseTypeEnum<Integer> {
    STANDARD(10, StandardProcessorBuilder.class, "标准处理构建器");
    private final Integer type;
    private final Class<? extends BaseItemProcessorBuilder> itemProcessorBuilderClass;
    private final String desc;
}