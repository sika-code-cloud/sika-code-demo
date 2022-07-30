package com.sika.code.demo.domain.common.batch.standard.bean.common;

import com.sika.code.demo.domain.common.batch.standard.bean.writer.BaseWriterBean;

/**
 * <pre>
 *  写Bean
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/6/16 23:11
 */
public class ItemWriterBean<T extends BaseWriterBean> extends ItemBean<T> {
    @Override
    public T buildBeanObj() {
        T writerBean = super.buildBeanObj();
        writerBean.build();
        return writerBean;
    }
}
