//package com.teachmeskills.pack;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class MainApp {
//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("my-config.xml");
//
//        SpringHelloWorld bean = (SpringHelloWorld) context.getBean("aaa");
////        SpringHelloWorld bean = context.getBean("aaa", SpringHelloWorld.class);
//        bean.getMessage();
//        bean.getInnerClass().setInnerMessage();
//
//        //business logic
//
//        bean.setMessage("New");
//        bean.getMessage();
//    }
//}
