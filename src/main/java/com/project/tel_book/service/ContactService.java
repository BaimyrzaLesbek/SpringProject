package com.project.tel_book.service;

import com.project.tel_book.domain.model.Contact;
import com.project.tel_book.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public List<Contact> getAllContacts(){
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

    public Page<Contact> getAllContactsWithPagination(int page, int size){
        return contactRepository.findAll(PageRequest.of(page, size));
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
        Contact savedContact = contactRepository.save(contact);


        com.project.tel_book.events.DataChangeEvent event = new com.project.tel_book.events.DataChangeEvent("ContactSaved", "Contact with ID " + savedContact.getId() + " saved");
        kafkaTemplate.send("kafka", event.getEventType(), event.toString());

        return savedContact;
    }

    public void deleteContact(Integer id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("Contact not found with id: " + id));
        contactRepository.deleteById(id);
    }
}