package vu.hellospring;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	 /**
	  * 
	  * @param args
	  */
	 public static void main(String[] args) {
	      ApplicationContext  context = new ClassPathXmlApplicationContext("Beans.xml");

	      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

	      obj.getMessage();
	      ((ClassPathXmlApplicationContext) context).close();
	   }
}
