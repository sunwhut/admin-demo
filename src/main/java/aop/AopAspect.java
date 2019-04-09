package aop;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.List;

public class AopAspect {
    public void doBefore(JoinPoint joinPoint){
        String methodName =  joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("do before, method name : " + methodName + " ,args: " + args);
    }

    public void doAfter(){
        System.out.println("do after...");
    }

    public void doThrow(JoinPoint joinPoint, Exception ex){
        String methodName =  joinPoint.getSignature().getName();
        System.out.println("do throw, method name: " + methodName);
    }
}
