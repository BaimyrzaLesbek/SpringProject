package com.project.tel_book.service;

import com.project.tel_book.domain.model.Contact;
import com.project.tel_book.repository.ContactRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    void testSaveContact() {
        // Mocking
        Contact contactToSave = new Contact("Baimyrza", "+7 (747) 5809660", LocalDate.now());
        when(contactRepository.save(any(Contact.class))).thenReturn(contactToSave);

        // Test
        Contact savedContact = contactService.saveContact(new Contact("Baimyrza", "+7 (747) 5809660", LocalDate.now()));

        // Verify
        verify(contactRepository, times(1)).save(any(Contact.class));
        assertEquals(contactToSave, savedContact);
    }

    @Test
    void testFindByName() {
        // Mocking
        String name = "John Doe";
        Contact expectedContact = new Contact("Baimyrza", "+7 (747) 5809660", LocalDate.now());
        when(contactRepository.findByName(name)).thenReturn(expectedContact);

        // Test
        Contact foundContact = contactService.findByName(name);

        // Verify
        verify(contactRepository, times(1)).findByName(name);
        assertEquals(expectedContact, foundContact);
    }

    @Test
    void testFindByPhoneNumber() {
        // Mocking
        String phoneNumber = "+7 (747) 5809660";
        Contact expectedContact = new Contact("Baimyrza", "+7 (747) 5809660", LocalDate.now());
        when(contactRepository.findByPhoneNumber(phoneNumber)).thenReturn(expectedContact);

        // Test
        Contact foundContact = contactService.findByPhoneNumber(phoneNumber);

        // Verify
        verify(contactRepository, times(1)).findByPhoneNumber(phoneNumber);
        assertEquals(expectedContact, foundContact);
    }
}