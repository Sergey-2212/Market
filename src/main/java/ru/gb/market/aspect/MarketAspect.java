package ru.gb.market.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class MarketAspect {

    private Long startedAt;
    @Pointcut("@annotation(Timer)")
    public void pointcutAnnotation() {

    }

    @Pointcut("@annotation(TimerAround)")
    public void aroundPointcutAnnotation() {

    }

    @Pointcut("@annotation(LogMethodsArgs)")
    public void showAllMethodsProceeding() {

    }

    @Before("showAllMethodsProceeding()")
    public void logAllMethodsArgs(JoinPoint point) {
        log.debug("Executed method - {}", point);
        String type = "";
        if(point.getArgs().length > 0) {
            for(Object arg : point.getArgs()) {
                type = arg.getClass().getSimpleName();
                log.debug(String.format(
                        "Arg.type - %s. Value - %s", type, arg));
            }
        }
    }


    @Before("pointcutAnnotation()")
    public void checkSrartedAtTime(JoinPoint point) {
        String arg = point.getArgs()[0].toString();
        log.info("Аргумент вызываемого метода = {}.", arg);
        startedAt = System.currentTimeMillis();
    }

    @After("pointcutAnnotation()")
    public void checkExecutionTime(JoinPoint point) {
        //log.info("After");
        log.info(String.format(
                "Время выполнения метода составило %d ms", System.currentTimeMillis() - startedAt
        ));
        startedAt = 0L;
    }

    //ради эксперимента сделал отдельный Pointcut c отдельной аннотацией.
    @Around("aroundPointcutAnnotation()")
    public Object aroundAnnotarionExample(ProceedingJoinPoint point) {
            Long startedAt = System.currentTimeMillis();
        try {
            Object[] args = point.getArgs();
            Object proceed = point.proceed(args);
            log.info("Around. Успешно! Выполнение метода заняло: {} ms.",System.currentTimeMillis() - startedAt);
            return proceed;
        } catch (Throwable e) {
            log.info(String.format("Во время исполнения метода %s возникла ошиюка %s", point.getKind(),e.getMessage()));
            log.info("Around. Неуспешно! Выполнение метода заняло: {} ms.",System.currentTimeMillis() - startedAt);
            return null;
        }
    }



}
