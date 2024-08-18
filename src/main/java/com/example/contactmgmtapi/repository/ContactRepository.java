package com.example.contactmgmtapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contactmgmtapi.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
