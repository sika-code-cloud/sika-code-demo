package com.sika.code.demo.interfaces.common.migrate.executor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.JSON;
import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.google.common.collect.Maps;
import com.sika.code.core.base.constant.BaseTypeEnum;
import com.sika.code.demo.interfaces.common.constant.MigrateTypeEnum;
import com.sika.code.migrate.builder.MigrateForestRequestBuilder;
import com.sika.code.migrate.builder.MigrateRequestRuleBuilder;
import com.sika.code.migrate.constant.MigrateConstant;
import com.sika.code.migrate.executor.MigrateRequestExecutor;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.sika.code.migrate.rule.MigrateRequestRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  迁移结果规则实现类
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/21 13:09
 */
@Slf4j
@Component
public class MigrateRequestExecutorImpl implements MigrateRequestExecutor {

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

            Map<String, List<String>> head = readRequestHeaderParamToMap(request);
            String migrateType = CollUtil.getFirst(head.get(MigrateConstant.MIGRATE_TYPE_KEY));

            if (BaseTypeEnum.notExist(migrateType, com.sika.code.demo.interfaces.common.constant.MigrateTypeEnum.class)) {
                log.info("不支持的迁移类型【{}】", migrateType);
                return true;
            }
            ForestRequest<?> forestRequest = Forest.post("http://");
            // 命中匹配规则-则执行流量迁移规则
            if (com.sika.code.demo.interfaces.common.constant.MigrateTypeEnum.isSwitch(migrateType)) {
                flowSwitching(forestRequest);
                // 执行流量切换
            } else if (com.sika.code.demo.interfaces.common.constant.MigrateTypeEnum.isContrast(migrateType)) {
                // 执行流量对比
                flowContrast(forestRequest);
            } else if (MigrateTypeEnum.isDoubleWrite(migrateType)) {
                // 执行流量双写
                flowDoubleWriter(forestRequest);
            }
            return true;
        }

        protected void logResponse(HttpServletResponse response) {
            log.info("response.getHeader:{}", JSON.toJSONString(response.getHeaderNames()));
            Map<String, String> heads = Maps.newHashMap();
            for (String headerName : response.getHeaderNames()) {
                heads.put(headerName, response.getHeader(headerName));
            }
            log.info("response.getHeaderValue:{}", JSON.toJSONString(heads));
        }

        // 流量双写逻辑
        public void flowDoubleWriter(ForestRequest<?> forestRequest) {
            log.info("命中流量双写逻辑");
        }

        // 流量比对
        public void flowContrast(ForestRequest<?> forestRequest) {
            log.info("命中流量对比逻辑");

        }

        public void flowSwitching(ForestRequest<?> forestRequest) throws IOException {
            // 命中流量切换逻辑
            log.info("命中流量切换逻辑");
        }

        private Map<String, List<String>> readRequestHeaderParamToMap(HttpServletRequest request) {
            Map<String, List<String>> retMap = Maps.newLinkedHashMap();
            List<String> headerNames = Collections.list(request.getHeaderNames());
            for (String headerName : headerNames) {
                List<String> headerValues = Collections.list(request.getHeaders(headerName));
                retMap.put(headerName, headerValues);
            }
            return retMap;
        }

        protected void buildResponse(ForestResponse<?> forestResponse, HttpServletResponse response) {
            log.info("forestResponse:{}", JSON.toJSONString(forestResponse.getHeaders()));
            log.info("forestResponse.getResult:{}", JSON.toJSONString(forestResponse.getResult()));
            for (Map.Entry<String, String> header : forestResponse.getHeaders().entrySet()) {
                response.setHeader(header.getKey(), header.getValue());
            }
            logResponse(response);
        }

}
