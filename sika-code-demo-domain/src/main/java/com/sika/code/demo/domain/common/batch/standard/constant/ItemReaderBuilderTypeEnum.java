package com.sika.code.demo.domain.common.batch.standard.constant;

import com.sika.code.demo.domain.common.batch.core.builder.BaseItemReaderBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.reader.StandardFlatItemReaderBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.reader.StandardJdbcCursorItemReaderBuilder;
import com.sika.code.demo.domain.common.batch.standard.builder.item.reader.StandardJdbcPagingItemReaderBuilder;
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
public enum ItemReaderBuilderTypeEnum implements BaseTypeEnum<Integer> {
    FLAT(10, StandardFlatItemReaderBuilder.class, "平面文件读取构建器"),
    JDBC_CURSOR(20, StandardJdbcCursorItemReaderBuilder.class, "jdbc游标读取构建器"),
    JDBC_PAGING(25, StandardJdbcPagingItemReaderBuilder.class, "jdbc分页读取构建器");
    private final Integer type;
    private final Class<? extends BaseItemReaderBuilder> itemReaderBuilderClass;
    private final String desc;
}