package com.project.tel_book.repository;

import com.project.tel_book.domain.model.Contact;
import jakarta.validation.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact findByName(String name);

    Contact findByPhoneNumber(String phoneNumber);


}