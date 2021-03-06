package top.huzhurong.aop.other;

import top.huzhurong.aop.annotation.Transactional;

/**
 * @author chenshun00@gmail.com
 * @since 2018/9/2
 */
public class TestService {

    private TestDao testDao = new TestDao();

    /**
     * 测试事务回滚
     */
    @Transactional
    public void addTest(Test test) {
        testDao.addTest(test);
        Test testById = testDao.getTestById(1);
        testById.setName("transaction测试");
        testDao.updatetestById(testById);
        int i = 10 / 0;
    }

}
