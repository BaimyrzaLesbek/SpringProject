package com.project.tel_book.service;

import com.project.tel_book.domain.model.Contact;
import com.project.tel_book.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

    public List<Contact> getAllContactsWithPagination(int page, int size){
        PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Contact> contactPage = contactRepository.findAll(pageable);
        return (List<Contact>) contactPage;
    }

    public Optional<Contact> getContactById(Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact;
    }

    public Contact findByName(String name) {
        return contactRepository.findByName(name);
    }
//
    public Contact findByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(Integer id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("Contact not found with id: " + id));
        contactRepository.deleteById(id);
    }
}