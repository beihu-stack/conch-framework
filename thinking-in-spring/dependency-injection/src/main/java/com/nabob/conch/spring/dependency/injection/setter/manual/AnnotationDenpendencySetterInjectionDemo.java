package ltd.beihu.spring.dependency.injection.setter.manual;

import ltd.beihu.spring.dependency.injection.setter.UserHolder;
import ltd.beihu.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Annotation 方式注入
 *
 * @author Adam
 * @date 2020/4/10
 */
public class AnnotationDenpendencySetterInjectionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDenpendencySetterInjectionDemo.class);

        // XML方式读取BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder.toString());

        applicationContext.close();
    }

    /**
     * Setter
     * @param user
     * @return
     */
    @Bean
    @Primary
    public UserHolder userHolder1(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

    /**
     * Constructor
     * @param user
     * @return
     */
    @Bean
    public UserHolder userHolder2(User user) {
        return new UserHolder(user);
    }
}
