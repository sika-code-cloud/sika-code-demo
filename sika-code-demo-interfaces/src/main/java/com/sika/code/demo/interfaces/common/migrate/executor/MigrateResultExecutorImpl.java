package com.sika.code.demo.interfaces.common.migrate.executor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sika.code.demo.interfaces.common.constant.MigrateTypeEnum;
import com.sika.code.migrate.constant.MigrateConstant;
import com.sika.code.migrate.executor.MigrateResultExecutor;
import com.sika.code.migrate.pojo.MigrateRuleResult;
import com.sika.code.migrate.util.SpringMVCUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
public class MigrateResultExecutorImpl implements MigrateResultExecutor {
    private static Set<String> igs = Sets.newHashSet("msgNo", "");
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void execute(MigrateRuleResult ruleResult) {
        Map<String, Object> contextMap = getContextMap();
        String requestId = MapUtil.getStr(contextMap, MigrateConstant.REQUEST_ID_KEY);
        String flowResource = MapUtil.getStr(contextMap, MigrateConstant.FLOW_RESOURCE_KEY);
        String migrateType = MapUtil.getStr(contextMap, MigrateConstant.MIGRATE_TYPE_KEY);
        String bodyFrom = MapUtil.getStr(contextMap, "bodyFrom");
        if (MigrateTypeEnum.isContrast(migrateType)) {
            String key = "request_id:" + requestId;
            log.info("请求ID为：{}", requestId);
            String result = stringRedisTemplate.opsForValue().get(key);
            log.info("当前请求中的结果为：{}", result);
            if (StrUtil.isEmpty(result)) {
                // 将当前结果缓存
                String resultTemp = buildNeedContract(ruleResult.getResult());
                stringRedisTemplate.opsForValue().set(key, resultTemp);
                stringRedisTemplate.expire(key, 60L, TimeUnit.SECONDS);
            } else {
                String paramBody = buildNeedContract(ruleResult.getResult());
                log.info("对比结果：{}，bodyFrom:{},result:{}, body:{}", result.equals(paramBody), bodyFrom, result, paramBody);
                stringRedisTemplate.delete(key);
            }
        }
    }

    private Map<String, Object> getContextMap() {
        Map<String, Object> objectMap = Maps.newLinkedHashMap();
        objectMap.putAll(readRequestHeaderParamToMap());
        HttpServletRequest request = SpringMVCUtil.getRequest();
        List<String> attributeNames = Collections.list(request.getAttributeNames());
        for (String name : attributeNames) {
            objectMap.put(name, request.getAttribute(name));
        }
        return objectMap;
    }

    private Map<String, String> readRequestHeaderParamToMap() {
        HttpServletRequest request = SpringMVCUtil.getRequest();
        Map<String, String> retMap = Maps.newLinkedHashMap();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            retMap.put(headerName, CollUtil.getFirst(headerValues));
        }
        return retMap;
    }

    public String buildNeedContract(Object result) {
        if (result == null) {
            log.info("结果为空");
            return null;
        }
        String tempStr = null;
        if (result instanceof String) {
            tempStr = result.toString();
        } else {
            tempStr = JSONObject.toJSONString(result);
        }
        JSONObject jsonObject = JSONObject.parseObject(tempStr);
        for (String ig : igs) {
            jsonObject.remove(ig);
        }
        return jsonObject.toJSONString();
    }


}
