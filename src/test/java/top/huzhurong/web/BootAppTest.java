package top.huzhurong.web;

import org.junit.Before;
import org.junit.Test;
import top.huzhurong.ioc.SpringContext;
import top.huzhurong.ioc.annotation.EnableConfiguration;

/**
 * @author chenshun00@gmail.com
 * @since 2018/9/21
 */
@EnableConfiguration
public class BootAppTest {

    private SpringContext springContext;

    @Before
    public void before() {
        springContext = new SpringContext(BootAppTest.class);
        System.out.println("context init finished! " + BootAppTest.class);
    }

    @Test
    public void testStart() {
        springContext.start();
    }

}
