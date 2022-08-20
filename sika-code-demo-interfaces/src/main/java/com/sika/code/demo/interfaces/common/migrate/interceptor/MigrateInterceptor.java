package com.sika.code.demo.interfaces.common.migrate.interceptor;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.JSON;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.google.common.collect.Maps;
import com.sika.code.demo.interfaces.common.migrate.builder.MigrateForestRequestBuilder;
import com.sika.code.demo.interfaces.common.migrate.builder.MigrateRequestRuleBuilder;
import com.sika.code.demo.interfaces.common.migrate.constant.MigrateTypeEnum;
import com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleRequest;
import com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleResponse;
import com.sika.code.demo.interfaces.common.migrate.rule.MigrateRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <pre>
 *  迁移拦截器
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/8/8 12:50
 */
@Component
@Slf4j
public class MigrateInterceptor implements HandlerInterceptor {
    @Value("${server.port}")
    private int port;
    @Autowired
    private MigrateRule migrateRule;

    /**
     * preHandle:预先处理请求的方法 。相当于总开关 参数：Object handler：被拦截的控制器对象（Mycontroller）
     * 返回值：Boolean   当为真时，请求正确，可以被Controller处理，程序正常执行。
     * 当为假时，请求不能被处理，控制器方法不会执行。请求到此截止。
     * 特点：1.预处理方法的执行时间：在控制器方法之前先执行的. 2.可以对请求做处理，可以做登陆检查，全限的判断，统计数据等等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            log.info("进入拦截器{}", MigrateInterceptor.class.getName());
            log.info("进入拦截器的request为:{},请求方式为：{}，response:{}", request.getClass(), request.getMethod(), response.getClass());
            log.info("进入拦截器的requesthead为:{},请求方式为：{}，response:{}", request.getHeaderNames(), request.getMethod(), response.getClass());
            log.info("进入拦截器的getParameterMap为:{}", JSON.toJSONString(request.getParameterMap()));
            return doPreHandler(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return true;
    }

    /**
     * postHandler：后处理方法 参数：Object handler：被拦截的控制器对象(MyController)
     * ModelAndView mv:控制器方法的返回值（请求的执行结果）
     * 特点：1.在控制器方法之后执行的。  2.能获取到控制器方法的执行结果。可以修改原来的执行结果
     * 可以修改数据，也可以修改视图。   3.可以做对请求的二次处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("进入postHandle");
    }

    /**
     * afterCompletion：最后执行的方法  参数：Object handler：被拦截的控制器对象(MyController) Exception ex：异常对象
     * 特点：1.在请求处理完成后执行的，请求处理完成的标志 是视图处理完成，对试图执行forward操作后
     * 2.可以做程序最后要做的工作，释放内存，清理临时变量
     * 3.方法的执行条件：1）当前的拦截器它的preHandler方法必须执行。 2）preHandler必须返回true
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    protected boolean doPreHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 构建规则请求对象
        MigrateRuleRequest ruleRequest = new MigrateRequestRuleBuilder(request, response).build();
        log.info("迁移规则请求类：{}", JSON.toJSONString(ruleRequest));
        // 通过迁移规则-获取迁移规则响应
        MigrateRuleResponse ruleResponse = migrateRule.match(ruleRequest);
        log.info("迁移规则响应类：{}", JSON.toJSONString(ruleResponse));
        // 切换规则没有匹配-则当前请求不需要执行迁移
        if (ruleResponse == null || BooleanUtil.isFalse(ruleResponse.getMatch())) {
            return true;
        }
        ForestRequest<?> forestRequest = new MigrateForestRequestBuilder(ruleRequest, ruleResponse).build();
        // 命中匹配规则-则执行流量迁移规则
        if (MigrateTypeEnum.SWITCH.getType().equals(ruleResponse.getMigrateType())) {
            flowSwitching(ruleRequest, forestRequest);
            // 执行流量切换
            return false;
        } else if (MigrateTypeEnum.CONTRAST.getType().equals(ruleResponse.getMigrateType())) {
            // 执行流量对比
            flowContrast(forestRequest);
        } else if (MigrateTypeEnum.DOUBLE_WRITE.getType().equals(ruleResponse.getMigrateType())) {
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
        forestRequest.async().execute();
    }

    // 流量比对
    public void flowContrast(ForestRequest<?> forestRequest) {
        forestRequest.async().execute();
    }

    public void flowSwitching(MigrateRuleRequest ruleRequest, ForestRequest<?> forestRequest) throws IOException {
        HttpServletResponse response = ruleRequest.getResponse();
        ForestResponse<?> forestResponse = forestRequest.execute(ForestResponse.class);
        buildResponse(forestResponse, response);
        IoUtil.writeUtf8(response.getOutputStream(), true, forestResponse.getResult());
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
