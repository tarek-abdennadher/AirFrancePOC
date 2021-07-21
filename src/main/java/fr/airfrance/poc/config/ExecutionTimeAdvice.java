package fr.airfrance.poc.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     Create execution time advice to log execution time by annotation. This feature is useful when log debug level is disabled on logging aspect
 * </p>
 */
@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class ExecutionTimeAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeAdvice.class);

    /**
     * <p>
     *     This method log inputs arguments, outputs and execution time
     * </p>
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(fr.airfrance.poc.config.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        LOGGER.info("Class Name: "+ point.getSignature().getDeclaringTypeName() +". Method Name: "+ point.getSignature().getName()
                + ". Time taken for Execution is : " + (endtime-startTime) +"ms");
        return object;
    }
}
