package com.hins.sm.utils;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

public class CommonUtils {

    public static <T> T toBean(Map map, Class<T> clazz) {
        try {
			/*
			 * 1. 通过参数clazz创建实例
			 * 2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
            T bean = clazz.newInstance();
            ConvertUtils.register(new DataConverterUtils(), java.util.Date.class);
            BeanUtils.populate(bean, map);
            return bean;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 产生随机ID
     * @return
     */
    public static String uuid() {

        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
