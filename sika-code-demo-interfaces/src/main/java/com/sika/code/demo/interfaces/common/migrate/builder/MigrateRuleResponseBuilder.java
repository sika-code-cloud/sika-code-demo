package com.sika.code.demo.interfaces.common.migrate.builder;

import com.google.common.collect.Maps;
import com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleRequest;
import com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <pre>
 *  规则响应构建者
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:16
 */
@AllArgsConstructor
@Data
public class MigrateRuleResponseBuilder {
    private MigrateRuleRequest ruleRequest;

    public MigrateRuleResponse build() {
        MigrateRuleResponse ruleResponse = new MigrateRuleResponse()
                .setMatch(false)
                .setExtraParam(Maps.newLinkedHashMap());

        return ruleResponse;
    }
}
