package Day_2026_05_13.filter;

import Day_2026_05_13.pojo.Result;
import Day_2026_05_13.utils.JwtUtils;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();

        if (url.contains("login")) {
            log.info("登录不拦截");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String jwt = request.getHeader("token");

        if (!StringUtils.hasLength(jwt)) {
            log.info("token是空的");
            serviceForNotLogin(response);
            return;
        }

        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            log.info("JWT错误");
            e.printStackTrace();
            serviceForNotLogin(response);
            return;
        }

        log.info("确认已登陆， 放行-----");
        log.info("确认已登陆， 请求");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("确认已登陆， 响应");


    }

    private static void serviceForNotLogin(HttpServletResponse response) throws IOException {

        Result error = Result.error("NOT_LOGIN");
        String notLogin = JSONObject.toJSONString(error);
        response.getWriter().write(notLogin);
    }
}
