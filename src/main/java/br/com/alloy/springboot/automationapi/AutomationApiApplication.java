package br.com.alloy.springboot.automationapi;

import java.awt.Desktop;
import java.io.IOException;
import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomationApiApplication {
	
	@Value("${server.port}")
    private int port;
	
	Logger logger = LoggerFactory.getLogger(AutomationApiApplication.class);

	public static void main(String[] args) {
		//Adding this so that automation can work properly
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(AutomationApiApplication.class, args);
	}
	
	@PostConstruct
	public void init() throws IOException {
		final String hostName = InetAddress.getLocalHost().getHostName();
		final String hostAddress = "http://" + hostName + ":" + port;
		logger.info("Host Address: " + hostAddress);
		Desktop.getDesktop().browse(java.net.URI.create(hostAddress));
	}

}
