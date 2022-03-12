package br.com.deyson.vuttr.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggerAspect.class);


    @Around("execution(* br.com.deyson.vuttr..*(..)))")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();


        final StopWatch stopWatch = new StopWatch();

        final String fullyQualifyMethodName = String.format("%s.%s", methodSignature.getDeclaringType().getSimpleName(),methodSignature.getName());
        LOGGER.info("Entering in method: {}", fullyQualifyMethodName);

        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        LOGGER.info("VUTTR API - Execution time of {} :: {} ms", fullyQualifyMethodName, stopWatch.getTotalTimeMillis());

        return result;
    }

}
