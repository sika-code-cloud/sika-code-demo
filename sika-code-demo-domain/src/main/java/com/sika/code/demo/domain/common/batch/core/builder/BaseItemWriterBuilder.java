package com.sika.code.demo.domain.common.batch.core.builder;

import com.sika.code.demo.domain.common.batch.standard.bean.common.BatchBean;
import org.springframework.batch.item.ItemWriter;

/**
 * <p>
 * 基础写构建起
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 17:52
 */
public interface BaseItemWriterBuilder<O> {
    ItemWriter<O> build(BatchBean batchBean);
}
