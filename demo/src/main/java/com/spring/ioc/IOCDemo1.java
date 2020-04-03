package com.spring.ioc;

import com.spring.pj.CarOperation;
import com.spring.pj.impl.BMW;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-03-11 13:59
 */

public class IOCDemo1 {

    @Test
    public void demo1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        AbstractBeanDefinition carOperation = new RootBeanDefinition(CarOperation.class,0,true);
        AbstractBeanDefinition bmw = new RootBeanDefinition(BMW.class,0,true);
        factory.registerBeanDefinition("op",carOperation);
        factory.registerBeanDefinition("bmw", bmw);

        //set方法注入
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("car",bmw));
        carOperation.setPropertyValues(propertyValues);

        //取bean
        CarOperation op = (CarOperation) factory.getBean("op");
        op.operate();


    }


}
