package com.sika.code.demo.domain.common.batch.standard.bean.processor;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * <p>
 *  基础处理器Bean
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/12 11:18
 */
@Data
public class BaseProcessorBean {
    // 输入和输出的映射关系
    private Integer builderType;
    private LinkedHashMap<String, String> readerProcessorMapper;
    private LinkedHashSet<String> listenerClassNames;
}
