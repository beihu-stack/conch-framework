package com.nabob.conch.sample.bootenhance.bbeanfactory;

import com.nabob.conch.sample.bootenhance.cbeanInstantiation.MyInstantiationAwareBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Adam
 * @since 2024/8/7
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("==================这是我自定义的BeanFactoryPostProcessor====================");
//        configurableListableBeanFactory.addBeanPostProcessor();
//        configurableListableBeanFactory.getBeanDefinition()
//        configurableListableBeanFactory.getBean()
//        configurableListableBeanFactory.getBeanNamesIterator()
//        configurableListableBeanFactory.getBeanDefinitionCount()
//        configurableListableBeanFactory.getConversionService()

//        configurableListableBeanFactory.getBeansWithAnnotation()

        configurableListableBeanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
    }

}
