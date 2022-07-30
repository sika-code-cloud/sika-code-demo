package com.sika.code.demo.domain.common.batch.standard.builder.item.processor;

import com.sika.code.demo.domain.common.batch.core.builder.BaseItemProcessorBuilder;
import com.sika.code.demo.domain.common.batch.standard.bean.common.BatchBean;
import com.sika.code.demo.domain.common.batch.standard.item.processor.StandardProcessor;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 18:29
 */
public class StandardProcessorBuilder implements BaseItemProcessorBuilder<Map<String, Object>, Map<String, Object>> {
    @Override
    public ItemProcessor<Map<String, Object>, Map<String, Object>> build(BatchBean batchBean) {
        return new StandardProcessor().setBatchBean(batchBean);
    }
}
