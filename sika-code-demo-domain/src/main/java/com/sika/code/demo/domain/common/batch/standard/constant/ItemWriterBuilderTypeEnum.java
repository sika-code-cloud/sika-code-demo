package com.sika.code.demo.domain.common.batch.standard.constant;

import com.sika.code.demo.domain.common.batch.core.builder.BaseItemWriterBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.writer.StandardFlatItemWriterBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.writer.StandardJdbcBatchItemWriterBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.writer.StandardMultiResourceItemWriterBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.writer.StandardRedisBatchItemWriterBuilder;
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
public enum ItemWriterBuilderTypeEnum implements BaseTypeEnum<Integer> {
    FLAT(10, StandardFlatItemWriterBuilder.class, "平面文件写入构建器"),
    MULTI_RESOURCE(15, StandardMultiResourceItemWriterBuilder.class, "多个平面文件写入构建器"),
    JDBC(20, StandardJdbcBatchItemWriterBuilder.class, "JDBC写入构建器"),
    REDIS(30, StandardRedisBatchItemWriterBuilder.class, "Redis写入构建器"),
    ;
    private final Integer type;
    private final Class<? extends BaseItemWriterBuilder> itemWriterBuilderCLass;
    private final String desc;
}