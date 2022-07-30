package com.sika.code.demo.domain.common.batch.standard.item.writer;

import com.sika.code.demo.domain.common.batch.standard.builder.writerdata.BaseWriterDataBuilder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/18 20:50
 */
@Data
@Accessors(chain = true)
public class JdbcBatchItemWriterSupport extends JdbcBatchItemWriter<Map<String, Object>> {
    private BaseWriterDataBuilder<Map<String, Object>> dataBuilder;

    @Override
    public void write(List<? extends Map<String, Object>> items) throws Exception {
        super.write(dataBuilder.build(items));
    }
}
