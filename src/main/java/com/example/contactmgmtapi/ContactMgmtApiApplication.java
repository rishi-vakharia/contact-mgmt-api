package com.example.contactmgmtapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.contactmgmtapi.model.Contact;
import com.example.contactmgmtapi.service.ContactService;

@SpringBootApplication
public class ContactMgmtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactMgmtApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ContactService	contactService) {
		return (args) -> {
			// insertDummyData(contactService); //only once
		};
	}

	public void insertDummyData(ContactService contactService) {
		contactService.addContact(new Contact("A", "X", "5641258954"));
		contactService.addContact(new Contact("B", "Y", "5621489563"));
		contactService.addContact(new Contact("C", "Z", "2156387459"));
	}
}
