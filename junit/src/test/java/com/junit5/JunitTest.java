package com.junit5;

import com.util.demo.Caluculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JunitTest {
    @Test
    public void test(){
        assertEquals(2,2,"预期结果不符");
    }

    @Test
    public void addTest() throws InterruptedException {
        ArrayList<Executable> assertList = new ArrayList<Executable>();

       int result = Caluculator.add(1,2);
       System.out.println("打印结果："+result);
//       assertEquals(3,result);
        assertList.add(()-> assertEquals(3,result));
       int result1 = Caluculator.add(1,-1);
       System.out.println("打印结果："+result1);
//       assertEquals(1,result1);
        assertList.add(()-> assertEquals(0,result1));
       int result2 = Caluculator.add(1,-2);
       System.out.println("打印结果："+result2);
       //fail();
//       assertEquals(1,result2);
        assertList.add(()-> assertEquals(-1,result2));
      /*  使用asserAll可以把所有的断言放到一起，错误的地方单独不影响其他方法测试案例的执行；
        但是还是不太方便，还能再改进外层方法中用一个数组来存储断言结果，用assertAll方法输出结果。
      */

/*       assertAll("计算结果校验！",
               ()-> {assertEquals(3,result);},
               ()-> assertEquals(0,result1),
               ()->assertEquals(-1,result2)
       );*/

        assertAll("jiguo",assertList.stream());


    }

    @Test
    public void test1(){
//        String string = new String();
       String string =  System.currentTimeMillis() + "";

        System.out.println("打印截取内容："+string.substring(0,11));
    }


}
