package com.annotationtest;

import com.com.service.AnnotationAnalysis;
import com.dao.MyAnnotation;
import com.dao.Student;
import org.junit.jupiter.api.Test;

public class annotationTest {
    @Test
    public void annotationTest(){
        Student student = new Student();
        student.setAge(30);
        student.setName("fanbin");
        String checkResult = AnnotationAnalysis.validate(student);
        System.out.println(checkResult);
    }
}

