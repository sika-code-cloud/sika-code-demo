package com.sika.code.demo.infrastructure.business.user.pojo.query;

import com.sika.code.core.base.pojo.query.PageQuery;
import com.sika.code.db.sharding.annotation.Sharding;
import lombok.Data;

/**
 * OrderQuery
 *
 * @author : daiqi
 * @date : 2023-08-25
 */
@Data
public class OrderQuery extends PageQuery<Long> {

    /** 银盛订单号 */
    @Sharding
    private String orderNo;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
