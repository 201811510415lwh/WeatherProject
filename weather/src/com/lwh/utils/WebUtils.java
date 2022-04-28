package com.lwh.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    /**
     * 把Map中的值注入到对应的JavaBean属性中。
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean( Map value, T bean ){
        try {
            System.out.println("注入之前：" + bean);
            /**
             * 把所有请求的参数都注入到user对象中
             */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * @description: 将字符串转换成为int类型的数据
     * @author LinWeiHeng
     * @date: 2021/12/2-15:54
     * @Param str: 
     * @Param defaultValue:
     * @return: int
     */
    public static int parseInt(String str, int defaultValue){

        try {
            if (str != null && str != ""){
                return Integer.parseInt(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultValue;

    }

    /**
     * @description: 将字符串转换成为double类型的数据
     * @author LinWeiHeng
     * @date: 2021/12/2-15:54
     * @Param str:
     * @Param defaultValue:
     * @return: int
     */
    public static double parseDouble(String str, double defaultValue){

        try {
            if (str != null && str != ""){
                return Double.parseDouble(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultValue;

    }

}
