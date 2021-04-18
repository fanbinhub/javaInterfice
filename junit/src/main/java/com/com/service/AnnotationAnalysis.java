package com.com.service;

import com.dao.MyAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class AnnotationAnalysis {
    public static String validate(Object obj){
        // 反射获取注解
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for(Field field : fields){
            MyAnnotation myAnnotation = field.getAnnotation(MyAnnotation.class);
            if(null!= myAnnotation){
                int min = myAnnotation.min();
                System.out.println("打印反射类中获得的min属性：" + min);
                int max = myAnnotation.max();
                System.out.println("打印反射类中获得的max属性：" + max);

                try {
                    Type genericType = field.getGenericType();
                    if(genericType.getTypeName().equals("int") ||
                            genericType.getTypeName().equals("java.lang.Integer")){
                        field.setAccessible(true);
                        int num = field.getInt(obj);
                        System.out.println("打印num：" + num);
                        if(num < min || num > max){
                            return myAnnotation.message();
                        }
                    }
                    if(genericType.getTypeName().equals("java.lang.String")){
                        System.out.println("进入类型判断string");
                        field.setAccessible(true);
                        String str = (String)field.get(obj);
                        System.out.println("打印String类型的参数"+str);
                        if(null == str){
                            str = "";
                        }
                        int length = str.length();
                        if(length < min || length > max){
                            System.out.println("姓名输入长度不合法");
                            return myAnnotation.message();
                        }
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Success";
    }

}
