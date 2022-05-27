package com.example.javaeefirst.helpers;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.concurrent.TimeUnit;

@Interceptor
@TimedExecution
public class ExecutionTimeInterceptor {
    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception{
        long start = System.nanoTime();
        Object obj = ctx.proceed();
        long end = System.nanoTime();
        long durationInMs = TimeUnit.NANOSECONDS.toMillis(end - start);
        System.out.println(ctx.getMethod().getDeclaringClass() + " " + ctx.getMethod().getName() + ": " + durationInMs);
        return obj;
    }
}
