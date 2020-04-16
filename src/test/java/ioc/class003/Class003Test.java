package ioc.class003;

import ioc.class003.car.Audi;
import ioc.class003.car.Buick;
import ioc.class003.human.Human;
import ioc.class003.human.LiSi;
import ioc.class003.human.ZhangSan;
import org.junit.Before;
import org.junit.Test;

public class Class003Test {
    private IoCContainer ioCContainer = new IoCContainer();

    @Before
    public void before(){
        ioCContainer.setBean(Audi.class, "audi");
        ioCContainer.setBean(Buick.class, "buick");
        ioCContainer.setBean(ZhangSan.class, "zhangsan", "audi");
        ioCContainer.setBean(LiSi.class, "lisi", "buick");
    }

    @Test
    public void test(){
        Human zhangsan = (Human) ioCContainer.getBean("zhangsan");
        zhangsan.goHome();
        Human lisi = (Human) ioCContainer.getBean("lisi");
        lisi.goHome();
    }
}
