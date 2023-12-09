package com.project.tel_book.controller;

import com.project.tel_book.model.Contact;
import com.project.tel_book.service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/byName/{name}")
    public Contact findByName(@PathVariable String name) {
        return contactService.findByName(name);
    }

    @GetMapping("/byPhoneNumber/{phoneNumber}")
    public Contact findByPhoneNumber(@PathVariable String phoneNumber) {
        return contactService.findByPhoneNumber(phoneNumber);
    }

    @PostMapping("/add")
    public Contact saveContact(@Valid @RequestBody Contact contact) {
        if (!contact.getPhoneNumber().matches("\\+7 \\(\\d{3}\\) \\d{7}")) {
            throw new ValidationException("Phone number must be in the format +7 (XXX) XXXXXXX");
        }
        return contactService.saveContact(contact);
    }
    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}