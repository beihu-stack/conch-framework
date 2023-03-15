package ltd.beihu.sample.dynamic;

import ltd.beihu.sample.User;
import ltd.beihu.sample.advice.agentv2.RpcLog;
import ltd.beihu.sample.advice.agentv2.SelfRpcLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @author Adam
 * @since 2023/3/3
 */
@Component
@RpcLog
@SelfRpcLog
public class DynamicSpringBeanFactory implements BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    public boolean createBean(String name) {
        beanFactory.registerSingleton(name, new User(name, 1));
        return true;
    }

    public Collection<User> getUsers() {
        Map<String, User> beansOfType = beanFactory.getBeansOfType(User.class);
        return beansOfType.values();
    }

    public void testVoid() {
        System.out.println("我的void方法");
    }
}
