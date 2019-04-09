package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopDemo {
    public static void main(String[] args) {
        ApplicationContext act =  new ClassPathXmlApplicationContext("aop.xml");
        Calculator calculator = (Calculator) act.getBean("calculatorImpl");
//        int result = calculator.add(5,5);
        int result = calculator.div(5,0);
        System.out.println(result);
    }
}

interface Calculator{
    int add(int a, int b);

    int div(int a, int b);
}

class CalculatorImpl implements Calculator{

    public int add(int a, int b) {
        System.out.println("executing add method..");
        int result = a + b;
        return result;
    }

    public int div(int a, int b) {
        System.out.println("executing div method..");
        int result = a / b;
        return result;
    }
}
