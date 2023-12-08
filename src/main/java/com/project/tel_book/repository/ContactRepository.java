package com.project.tel_book.repository;

import com.project.tel_book.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact findByName(String name);

    Contact findByPhoneNumber(String phoneNumber);
}