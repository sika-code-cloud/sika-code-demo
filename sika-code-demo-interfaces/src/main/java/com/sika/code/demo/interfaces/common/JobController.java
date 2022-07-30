package com.sika.code.demo.interfaces.common;

import com.sika.code.demo.domain.common.batch.standard.bean.common.BatchBean;
import com.sika.code.demo.domain.common.batch.standard.context.StandardContext;
import com.sika.code.demo.domain.common.batch.standard.entity.StandardBatchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 10:18
 */
@RestController
public class JobController {
    @Autowired
    protected StandardBatchEntity standardEntity;

    @RequestMapping("/custDoJob/anon")
    public void custDoJob(@RequestBody BatchBean batchBean) {
        try {
            StandardContext standardContext = new StandardContext();
            standardContext.setBatchBean(batchBean);
            standardEntity.execute(standardContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/doRead/anon")
    public void doRead() throws IOException {
        ClassPathResource source = new ClassPathResource("data/user.csv");
        BufferedReader reader = new BufferedReader(new FileReader(source.getFile().getAbsolutePath()));
        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        System.out.println("读取文件的数量为：" + count);

    }
}