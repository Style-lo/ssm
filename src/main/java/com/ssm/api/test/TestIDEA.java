package com.ssm.api.test;

/**
 * Created by Administrator on 2018-01-13.
 */
public class TestIDEA {
    public static  void main(String[] args){
        System.out.println("测试idea");
        int[] s ={1,5,2,4,9,3,5,4};
        Dog dog = new Dog();
        int age = dog.getAge();
        for (int i : s) {
            System.out.println(i);
            System.out.println("TestIDEA.main");
        }
    }

}
