package Day_2026_05_13.interceptor;

import Day_2026_05_13.pojo.Result;
import Day_2026_05_13.utils.JwtUtils;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle.....");




        String jwt = request.getHeader("token");

        if (!StringUtils.hasLength(jwt)) {
            log.info("token是空的");
            serviceForNotLogin(response);
            return false;
        }

        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            log.info("JWT错误");
            e.printStackTrace();
            serviceForNotLogin(response);
            return false;
        }

        log.info("确认已登陆， 放行-----");
        log.info("确认已登陆， 请求");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion.....");
    }


    private static void serviceForNotLogin(HttpServletResponse response) throws IOException {

        Result error = Result.error("NOT_LOGIN");
        String notLogin = JSONObject.toJSONString(error);
        response.getWriter().write(notLogin);
    }
}
