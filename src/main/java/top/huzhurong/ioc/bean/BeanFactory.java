package top.huzhurong.ioc.bean;

import java.util.Set;

/**
 * bean factory ， create bean to context or receive bean form context
 *
 * @author luobo.cs@raycloud.com
 * @since 2018/9/6
 */
public interface BeanFactory {

    Object getBean(String name);

    <T> T getBean(Class<T> tClass);

    <T> T getBean(String name, Class<T> tClass);

    boolean register(Set<ClassInfo> classInfoSet);
}
