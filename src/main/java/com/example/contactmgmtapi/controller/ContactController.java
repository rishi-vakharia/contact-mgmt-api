package com.example.contactmgmtapi.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.contactmgmtapi.exception.ResourceNotFoundException;
import com.example.contactmgmtapi.model.Contact;
import com.example.contactmgmtapi.service.ContactService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @GetMapping("")
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @PostMapping("")
    public Contact addContact(@Valid @RequestBody Contact contact){
        return contactService.addContact(contact);
    }
    
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable long id){
        return contactService.getContactById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
    }
    
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable long id, @Valid @RequestBody Contact contact){
        if(contactService.existsById(id)==false){
            throw new ResourceNotFoundException("Contact not found with id: " + id);
        }
        contact.setId(id);
        return contactService.updateContact(contact);
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable long id){
        if(contactService.existsById(id)==false){
            throw new ResourceNotFoundException("Contact not found with id: " + id);
        }
        contactService.deleteContact(id);
        return "Contact deleted successfully!";
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining("\n"));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }    
}
