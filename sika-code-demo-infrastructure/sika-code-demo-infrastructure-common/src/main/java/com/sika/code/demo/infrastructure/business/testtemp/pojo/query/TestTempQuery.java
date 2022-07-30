package com.sika.code.demo.infrastructure.business.testtemp.pojo.query;

import com.sika.code.core.base.pojo.query.PageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 测试tem表 查询类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:57:57
 */
@Getter
@Setter
public class TestTempQuery extends PageQuery<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 创建人标识
     */
    private String createBy;
    /**
     * 最后更新人标识
     */
    private String updateBy;
    /**
     * 编号
     */
    private String testNo;
    /**
     * 流程名称
     */
    private String testName;
    /**
     * 记录日期
     */
    private LocalDate recordDate;
    private List<Long> ids;
}
