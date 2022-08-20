package com.sika.code.demo.interfaces.common.migrate.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.sika.code.demo.interfaces.common.migrate.builder.MigrateRuleResponseBuilder;
import com.sika.code.demo.interfaces.common.migrate.constant.MigrateTypeEnum;
import com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleRequest;
import com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleResponse;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  迁移规则实现类
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 14:51
 */
@Component
public class MigrateRuleImpl implements MigrateRule {
    private static final String ROOT_URL = "http://localhost:8082";
    private static final String FLOW_RESOURCE_KEY = "ak-uc-application-flow-resource";
    private static final String FLOW_RESOURCE = "UC2.0";
    private static final String REQUEST_ID_KEY = "ak-uc-application-request-id";
    private static final String MIGRATE_TYPE_KEY = "ak-uc-application-migrate-type";
    @Resource
    protected FlowExecutor flowExecutor;

    @Override
    public MigrateRuleResponse match(MigrateRuleRequest ruleRequest) {
        MigrateRuleResponse ruleResponse = new MigrateRuleResponseBuilder(ruleRequest).build();
        if (matchRule(ruleRequest, ruleResponse)) {
            buildMigrateType(ruleRequest, ruleResponse);
            buildExtraParam(ruleResponse);
            buildUrl(ruleRequest, ruleResponse);
        }
        return ruleResponse;
    }

    private boolean matchRule(MigrateRuleRequest ruleRequest, MigrateRuleResponse ruleResponse) {
        // 匹配规则链
        // 1. 黑名单URL匹配
        // 2. 全量规则匹配
        // 3. EVN_KEY规则匹配
        // 4. 公司ID规则匹配
        // 5. 白名单规则匹配
        LiteflowResponse response = flowExecutor.execute2Resp("migrateRule", ruleRequest, ruleResponse);
        if (response.isSuccess()) {
            return ruleResponse.getMatch();
        } else {
            return false;
        }
    }

    private void buildMigrateType(MigrateRuleRequest ruleRequest, MigrateRuleResponse ruleResponse) {
        String migrateTypeStr = CollUtil.getFirst(ruleRequest.getRequestHeadParam().get("migrate-type"));
        if (StrUtil.isNotBlank(migrateTypeStr)) {
            ruleResponse.setMigrateType(Integer.parseInt(migrateTypeStr));
        }
    }

    private void buildUrl(MigrateRuleRequest ruleRequest, MigrateRuleResponse ruleResponse) {
        HttpServletRequest servletRequest = ruleRequest.getRequest();
        ruleResponse.setRedirectUrl(ROOT_URL + servletRequest.getRequestURI());
    }

    protected void buildExtraParam(MigrateRuleResponse ruleResponse) {
        ruleResponse.putExtraParam(FLOW_RESOURCE_KEY, FLOW_RESOURCE);
        ruleResponse.putExtraParam(REQUEST_ID_KEY, IdUtil.objectId());
        if (ruleResponse.getMigrateType() != null) {
            ruleResponse.putExtraParam(MIGRATE_TYPE_KEY, ruleResponse.getMigrateType().toString());
        }
    }
}
