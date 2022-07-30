package com.sika.code.demo.domain.common.batch.core.listener;

import org.springframework.batch.core.ItemReadListener;


public interface BaseItemReadListener<T> extends ItemReadListener<T> {
    @Override
    default void beforeRead() {

    }

    @Override
    default void afterRead(T t) {

    }

    @Override
    default void onReadError(Exception e) {

    }
}
