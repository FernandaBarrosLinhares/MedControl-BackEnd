package br.senai.labmedicine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabmedicineApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/api");
		SpringApplication.run(LabmedicineApplication.class, args);
	}

}
