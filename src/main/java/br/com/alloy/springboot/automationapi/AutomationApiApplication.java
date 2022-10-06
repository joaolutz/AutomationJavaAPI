package br.com.alloy.springboot.automationapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomationApiApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false"); //Adding this so that automation can work properly
		SpringApplication.run(AutomationApiApplication.class, args);
	}

}
