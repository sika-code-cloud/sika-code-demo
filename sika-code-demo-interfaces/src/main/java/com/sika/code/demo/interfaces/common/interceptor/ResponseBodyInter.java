package com.sika.code.demo.interfaces.common.interceptor;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
public class ResponseBodyInter implements ResponseBodyAdvice {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1,
                                  MediaType arg2, Class arg3, ServerHttpRequest arg4,
                                  ServerHttpResponse arg5) {
        log.info("获取{}", JSON.toJSONString(body));
        SaHolder.getStorage().set("result", body);
        return body;
    }

    @Override
    public boolean supports(MethodParameter arg0, Class arg1) {
        return true;
    }

}