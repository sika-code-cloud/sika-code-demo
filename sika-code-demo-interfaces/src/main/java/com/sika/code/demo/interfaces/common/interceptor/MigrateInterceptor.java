package com.sika.code.demo.interfaces.common.interceptor;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.google.common.collect.Maps;
import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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

    /**
     * preHandle:预先处理请求的方法 。相当于总开关 参数：Object handler：被拦截的控制器对象（Mycontroller）
     * 返回值：Boolean   当为真时，请求正确，可以被Controller处理，程序正常执行。
     * 当为假时，请求不能被处理，控制器方法不会执行。请求到此截止。
     * 特点：1.预处理方法的执行时间：在控制器方法之前先执行的. 2.可以对请求做处理，可以做登陆检查，全限的判断，统计数据等等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            log.info("从body中读取的数据为{}", sb);
            log.info("进入拦截器{}", MigrateInterceptor.class.getName());

            //从session中获取user的信息
            UserDTO user = JSONObject.parseObject(JSON.toJSONString(StpUtil.getSessionByLoginId("10001").get("user")), UserDTO.class);
            List<UserDTO> users = JSONArray.parseArray(JSON.toJSONString(StpUtil.getSessionByLoginId("10001").get("userList")), UserDTO.class);
            log.info("users:{}", users);
            //判断用户是否登录
            if (request.getHeader("switch") != null) {
                boolean flag = true;
                String url = createRedirectUrl(request, "http://localhost:8082", "");
                ForestRequest<?> forestRequest = Forest.post(url);
                if (request.getHeader("switch").equals("1")) {
                    ForestResponse forestResponse = buildResponse(request, response, forestRequest);
                    IoUtil.writeUtf8(response.getOutputStream(), true, forestResponse.getResult());
                    flag = false;
                } else if (request.getHeader("switch").equals("2")) {
                    forestRequest.async();
                    ForestResponse forestResponse = buildResponse(request, response, forestRequest);
                } else if (request.getHeader("switch").equals("3")) {
                    forestRequest.async();
                    ForestResponse forestResponse = buildResponse(request, response, forestRequest);
                }
                return flag;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return true;
    }

    protected ForestResponse buildResponse(HttpServletRequest request, HttpServletResponse response, ForestRequest<?> forestRequest) throws IOException {
        ForestResponse forestResponse = parseRequestHeader(request, forestRequest).addBody(parseRequestBody(request)).execute(ForestResponse.class);
        log.info("forestResponse:{}", JSON.toJSONString(forestResponse.getHeaders()));
        log.info("forestResponse.getResult:{}", JSON.toJSONString(forestResponse.getResult()));
        for (Map.Entry<String, String> header : forestResponse.getHeaders().entrySet()) {
            response.setHeader(header.getKey(), header.getValue());
        }
        logResponse(response);
        return forestResponse;
    }

    private RequestEntity createRequestEntity(HttpServletRequest request, String url) throws URISyntaxException, IOException {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        MultiValueMap<String, String> headers = parseRequestHeader(request);
        byte[] body = parseRequestBody(request);
        return new RequestEntity<>(body, headers, httpMethod, new URI(url));
    }

    private byte[] parseRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    private String createRedirectUrl(HttpServletRequest request, String routeUrl, String prefix) {
        String queryString = request.getQueryString();
        return routeUrl + request.getRequestURI().replace(prefix, "") +
                (queryString != null ? "?" + queryString : "");
    }

    private Map<String, Object> parseResponseHeader(HttpServletResponse response) {
        Map<String, Object> mapret = Maps.newLinkedHashMap();
        for (String headerName : response.getHeaderNames()) {
            mapret.put(headerName, response.getHeader(headerName));
        }
        return mapret;
    }
    private <T> ForestRequest<T> parseRequestHeader(HttpServletRequest request, ForestRequest<T> forestRequest) {
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                forestRequest.addHeader(headerName, headerValue);
            }
        }
        return forestRequest;
    }

    private HttpHeaders parseRequestHeader(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                headers.add(headerName, headerValue);
            }
        }
        return headers;
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
        if (request.getHeader("switch").equals("3")) {
            String url = "http://localhost:8082/auth/response";
            Map<String, Object> map = Maps.newHashMap();
            map.put("body", SaHolder.getStorage().get("result"));
            map.put("head", parseResponseHeader(response));

            Forest.post(url).contentTypeJson()
                    .addBody(map).execute();

        }
    }

    protected void logResponse(HttpServletResponse response) {
        log.info("response.getHeader:{}", JSON.toJSONString(response.getHeaderNames()));
        Map<String, String> heads = Maps.newHashMap();
        for (String headerName : response.getHeaderNames()) {
            heads.put(headerName, response.getHeader(headerName));
        }
        log.info("response.getHeaderValue:{}", JSON.toJSONString(heads));
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

    // 流量双写逻辑
    public void flowDoubleWriter(HttpServletRequest request, HttpServletResponse response, Object handler) {

    }

    // 流量比对
    public void flowComparison(HttpServletRequest request, HttpServletResponse response, Object handler) {

    }

    public void flowSwitching(HttpServletRequest request, HttpServletResponse response, Object handler) {

    }
}
