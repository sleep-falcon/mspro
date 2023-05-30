package middleware.methodext;

import middleware.methodext.annotation.DoMethodExt;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class DoMethodExtPoint {

    private Logger logger = LoggerFactory.getLogger(DoMethodExtPoint.class);

    @Pointcut("@annotation(middleware.methodext.annotation.DoMethodExt)")
    public void aopPoint() {
    }

    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint jp) throws Throwable {
        // 获取内容
        Method method = getMethod(jp);
        DoMethodExt doMethodExt = method.getAnnotation(DoMethodExt.class);
        // 获取拦截方法名
        String usedMethodName = doMethodExt.method();
        // 功能处理
        //拿到扩展的方法与返回type
        Method methodExt = jp.getTarget().getClass().getMethod(usedMethodName, method.getParameterTypes());
        Class<?> returnType = methodExt.getReturnType();

        // 判断方法返回类型，要求为boolean
        if (!returnType.getName().equals("boolean")) {
            throw new RuntimeException("annotation @DoMethodExt set method：" + usedMethodName + " returnType is not boolean");
        }

        // 拦截判断正常，继续
        boolean invoke = (boolean) methodExt.invoke(jp.getTarget(), jp.getArgs());
        // 返回结果
        return invoke ? jp.proceed() : JSON.parseObject(doMethodExt.returnJson(), method.getReturnType());
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }


}
