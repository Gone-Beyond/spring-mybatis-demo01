package Day_2026_05_13.aop;

import Day_2026_05_13.mapper.OperateLogMapper;
import Day_2026_05_13.pojo.OperateLog;
import Day_2026_05_13.utils.JwtUtils;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.LogManager;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;


    @Around("@annotation(Day_2026_05_13.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String jwt = request.getHeader("token");

        Claims claims = JwtUtils.parseJwt(jwt);

        Integer operateUser = (Integer) claims.get("id");

        LocalDateTime now = LocalDateTime.now();

        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        String returnValue = JSONObject.toJSONString(result);

        long costTime = end - begin;


        OperateLog operateLog = new OperateLog(
                null, operateUser, now, className, methodName, methodParams, returnValue, costTime
        );

        operateLogMapper.insert(operateLog);

        return result;
    }

}
