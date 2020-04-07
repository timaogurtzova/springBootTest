package com.hellocat.springBootTest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class PostProxyRunnerListener {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        String[] namesBeanDefinition = applicationContext.getBeanDefinitionNames();
        for (String name : namesBeanDefinition) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            /* Если бин декларировался через JavaConfig, то в BeanDefinition для таких бинов нет информации о том,
            из какого бин класса*/
            if (originalClassName == null) {
                continue;
            }
            try {
                Class<?> originalClass = Class.forName(originalClassName);
                Method[] methods = originalClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostProxy.class)) { //сейчас нужно вытащить метод из текущего класса (это мб proxy)
                        Object bean = applicationContext.getBean(name);
                        Method invokeNow = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        invokeNow.invoke(bean);
                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }
}
