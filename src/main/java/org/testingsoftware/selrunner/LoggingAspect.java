package org.testingsoftware.selrunner;
 
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
 
@Aspect
public class LoggingAspect {
    //private static final transient Logger logger = Logger.getLogger(LoggingAspect.class);

    // activate logging for all public methods
    @Pointcut("execution(public * AbstractRunner.*(..)) && !execution(public * AbstractRunner.*Abort(..))")
    public void logging() {}
 
    @Around("logging()")
    public Object logging(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Signature sig = thisJoinPoint.getSignature();
    	Object[] args = thisJoinPoint.getArgs();
    	String location = sig.getName() + "(" + Arrays.toString(args) + ")";
    	//Utilities util = new Utilities();
    	//util.format("yyyy.MM.dd kk:mm:ss.SSS");
    	//String timestamp = util.today();
    	String timestamp = "ts here";
        try {
            System.out.println(timestamp + " [" + location + "]");
            return thisJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(timestamp + " [" + location + "] failed\n" + e);
            throw new RuntimeException("[" + location + "] failed\n", e);
        }
    }
}