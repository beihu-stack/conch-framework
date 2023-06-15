package ltd.beihu.spring.dependency.injection.constructor.manual;

import ltd.beihu.spring.dependency.injection.setter.UserHolderApi;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * API Constructor 依赖注入
 *
 * @author Adam
 * @date 2020/4/10
 */
public class ApiDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        // 创建 ApplicationContext 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        applicationContext.register(ApiDependencyConstructorInjectionDemo.class);
        applicationContext.refresh();

        // XML方式读取BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");

        // 自建 BeanDefinition
        BeanDefinition userHolderApiBeanDefinition = buildUserHolderApiBeanDefinition();
        // 注册
        applicationContext.registerBeanDefinition("userHolderApi", userHolderApiBeanDefinition);

        UserHolderApi userHolderApi = applicationContext.getBean(UserHolderApi.class);
        System.out.println(userHolderApi.toString());

        applicationContext.close();
    }

    private static BeanDefinition buildUserHolderApiBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolderApi.class);
        // api 方式
        beanDefinitionBuilder.addConstructorArgReference("user");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}
