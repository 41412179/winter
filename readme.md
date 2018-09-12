### 如何运行

*   maven
*   jdk1.8
*   安装lombok

具体的运行测试可以在 `top.huzhurong.aop.core.TestUse` 中找到，have a try  😊

### 申明式事务

*   事务的处理步骤
    *   begin/start;(开启事务)
    *   sql operation;(sql操作,curd)
    *   exception(异常rollback)
    *   success(commit)

> 如上即是事务的完整处理步骤，而sql操作当中又可以细分为多次操作(子事务)，这样就可以解决保存点的问题，
每一个子事务都是完整的可以不影响父事务，`innodb的平板事务模型`.

#### 如何实现申明式事务处理

添加 `TransactionAdvisor` 切面拦截实现了申明式事务，事务的原子操作面向的其实 `java.sql.Connection`，
即只要通过控制`多个Service`之间的`Connection`是一个即可实现事务的完整操作。

### bean实例化处理顺序(Spring大致实现顺序)

0、创建bean实例

1、创建bean,eg:test(实例化)

2、调用aware接口实现aware(beanFactory)注入

3、beanPostProcessor init前置处理 BeanPostProcessorsBeforeInitialization

4、前置处理完成之后，上下文容器context(ioc 容器)注入

5、处理InitializingBean后置实现 afterPropertiesSet,自定义 init 方法，init方法在后边

6、beanPostProcessor init后置处理 BeanPostProcessorsAfterInitialization,bean对象可能在这里包装成代理


>ps:实力不够，所以在Spring上进行一次变种，极大的简化了这一层面的操作

### 问题
  
1、使用 `cglib` 和 `jdk proxy` 的时候，两者的实现动态代理的原理是不一致的，`cglib` 使用继承父类的的形式展开，这样我们就可以通过向上
转型(cast)将该代理(子类)转型为父类。而 `jdk proxy` 则采取了另一种形式，实现相同的接口，这就是为什么 `jdk proxy` 一定需要接口的原因，如果没有接口那么就代理
失败，正式因为通过实现接口的方式导致了它只能转型为实现的接口，如果转型为子类的那么就会出现，  `$proxy0 cast xxx.xxx.xx` 的异常信息，
可以到这里看看 `jdk proxy` 生成的 `$proxy0` -----> [$proxy0](https://git.io/fAKeS)  