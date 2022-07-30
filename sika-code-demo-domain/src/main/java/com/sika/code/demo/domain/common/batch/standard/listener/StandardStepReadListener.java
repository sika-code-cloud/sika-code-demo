package com.sika.code.demo.domain.common.batch.standard.listener;

import com.sika.code.demo.domain.common.batch.core.listener.BaseItemReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
public class StandardStepReadListener implements BaseItemReadListener<Map<String, Object>> {
    @Override
    public void beforeRead() {
//        log.info("开始执行读------");
    }

    @Override
    public void afterRead(Map<String, Object> t) {
//        log.info("执行读后------");
    }

    @Override
    public void onReadError(Exception e) {

    }
}
