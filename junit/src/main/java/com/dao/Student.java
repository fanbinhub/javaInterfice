package com.dao;

public class Student {
    @MyAnnotation(min=18,max=50,message="年龄必须在18岁到50岁之间，包含18岁和50岁")
    private int age ;

    @Myisnull
    @MyAnnotation(min=2,max=5,message="姓名最少2位，最多5位")
    private String name ;

    boolean sex ;

    protected double score ;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", score=" + score +
                '}';
    }
}
