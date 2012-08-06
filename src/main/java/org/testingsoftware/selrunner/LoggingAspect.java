package org.testingsoftware.selrunner;
 
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
 
@Aspect
public class LoggingAspect {
 
    @Pointcut("execution(* AbstractRunner.*(..))")
    public void logging() {}
 
    @Around("logging()")
    public Object logging(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Signature sig = thisJoinPoint.getSignature();
    	Object[] args = thisJoinPoint.getArgs();
    	String location = sig.getName() + "(" + Arrays.toString(args) + ")";
        try {
            return thisJoinPoint.proceed();
        } catch (Exception e) {
            throw new RuntimeException(location + " failed\n" + e);
        }
    }
}
