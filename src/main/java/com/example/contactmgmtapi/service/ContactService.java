package com.example.contactmgmtapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.contactmgmtapi.model.Contact;
import com.example.contactmgmtapi.repository.ContactRepository;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    
    }

    public Contact addContact(Contact contact){
        return contactRepository.save(contact);
    }

    public boolean existsById(long id){
        return contactRepository.existsById(id);
    }

    public Optional<Contact> getContactById(long id){
        return contactRepository.findById(id);
    }

    public Contact updateContact(Contact contact){
        return contactRepository.save(contact);
    }

    public void deleteContact(long id){
        contactRepository.deleteById(id);
    }
}
