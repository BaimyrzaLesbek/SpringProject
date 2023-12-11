package com.project.tel_book.controller;

import com.project.tel_book.domain.model.Contact;
import com.project.tel_book.service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContactsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<Contact> contacts = contactService.getAllContactsWithPagination(page,size);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/id")
    public ResponseEntity<Contact> getContactById(@PathVariable Integer id){
        try {
            Contact contact = contactService.getContactById(id)
                    .orElseThrow( () -> new IllegalArgumentException("Contact not found with ID: "+ id) );
            return ResponseEntity.ok(contact);
        }
        catch (Exception e) {
            return ResponseEntity.noContent().header("Content-Length", "0").build();
        }
    }

    @PostMapping("/add")
    public Contact saveContact(@Valid @RequestBody Contact contact) {
        if (!contact.getPhoneNumber().matches("\\+7 \\(\\d{3}\\) \\d{7}")) {
            throw new ValidationException("Phone number must be in the format +7 (XXX) XXXXXXX");
        }
        return contactService.saveContact(contact);
    }

    @GetMapping("/byName/{name}")
    public Contact findByName(@PathVariable String name) {
        return contactService.findByName(name);
    }

    @GetMapping("/byPhoneNumber/{phoneNumber}")
    public Contact findByPhoneNumber(@PathVariable String phoneNumber) {
        return contactService.findByPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        try {
            contactService.deleteContact(id);
        }
        catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
        return ResponseEntity.noContent().build();
    }



}