package ua.ithillel.tripplanner.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* ua.ithillel.tripplanner.service..*.*(..))")
    private void serviceMethod() {}

    @Pointcut("execution(* ua.ithillel.tripplanner.repo..*.*(..))")
    private void repoMethod() {}

    @Pointcut("execution(* ua.ithillel.tripplanner.controller..*.*(..))")
    private void controllerMethod() {}

    @Around(value = "serviceMethod() || repoMethod() || controllerMethod()")
    public Object logAroundMethod(ProceedingJoinPoint pjp) throws Throwable{
        final Signature signature = pjp.getSignature();
        log.debug("Calling {} with args: {}", signature, Arrays.toString(pjp.getArgs()));

        final Object result = pjp.proceed();

        log.debug("Method {} returned {}", pjp.getSignature(), result);

        return result;
    }

    @AfterThrowing(value = "serviceMethod() || repoMethod()", throwing = "e")
    public void logMethodAfterThrown(Exception e) {
        log.error("Exception in service method: {}", e.getMessage());
    }
}
