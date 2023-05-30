package middleware.hystrix;

import middleware.hystrix.annotation.DoHystrix;
import middleware.hystrix.valve.ValveService;
import middleware.hystrix.valve.impl.HystrixValveImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;


@Aspect
@Component
public class DoHystrixPoint {
     Logger logger = LoggerFactory.getLogger(DoHystrixPoint.class);

    @Pointcut("@annotation(middleware.hystrix.annotation.DoHystrix)")
    public void aopPoint() {
    }

//    @Autowired
//    @Qualifier("hystrixValveImpl")
//    private ValveService valveServices;
    @Resource(name = "hystrixValveImpl")
    private ValveService valveService;

    @Around("aopPoint() && @annotation(doGovern)")
    public Object doRouter(ProceedingJoinPoint jp, DoHystrix doGovern) throws Throwable {
        Method method = getMethod(jp);
        return valveService.access(jp,method, doGovern, jp.getArgs());
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

}
