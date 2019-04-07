package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

public class HystrixDemo {
    public static void main(String[] args) {
        HelloWorldCommand command = new HelloWorldCommand("helloworld");
        String result = command.execute();
        System.out.println("thread: " + Thread.currentThread().getName() + ", result: " + result);
    }
}

class HelloWorldCommand extends HystrixCommand<String>{
    private String name;

    public HelloWorldCommand(String name){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorldCommand"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        this.name = name;
    }

    protected String run() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1000);
        return "hystrix thread: " + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return name + " executed failed";
    }
}
