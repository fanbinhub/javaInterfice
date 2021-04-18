package com.junit5;

import com.dao.ShellResult;
import com.dao.ShellResultList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/*
* 动态测试方法生成，用@TestFactory注解
* */
public class dynamicTest1 {
    @TestFactory
    Collection<DynamicTest> dynamicTestCollection(){
     return Arrays.asList(
             dynamicTest("lst dynamic test",
                     ()->{ assertTrue(true);
             }),
             dynamicTest("znd dymic test",
                     ()-> assertEquals(4,4))
     );
    }

    /*
    * 加载文件返序列化转为对象重点方法。
    * */
    @Test
    public void entityTest() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());//加载Yaml对象
        //将文件内容转为对象返序列化
        ShellResultList shellResultList = objectMapper.readValue(new File("E:\\junit\\src\\main\\" +
                "resources\\shell_test_result.yml"),ShellResultList.class);
        System.out.println("---" + shellResultList);


    };

    @TestFactory
    public Collection<DynamicTest> dynamicTestCollection1() throws IOException {
        List<DynamicTest> dynamicList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ShellResultList shellResultList = objectMapper.readValue(new File("E:\\junit\\src\\main\\resources\\shell_test_result.yml"),ShellResultList.class);
        System.out.println("---" + shellResultList);
        for(ShellResult ResultList1 : shellResultList.getResultList()){
            dynamicList.add( dynamicTest(ResultList1.getCaseName(),
                    ()->{ assertTrue(ResultList1.isResult());
                    }));
        }
        System.out.println("打印集合中的内容" + dynamicList);
        return dynamicList;
    }

}
