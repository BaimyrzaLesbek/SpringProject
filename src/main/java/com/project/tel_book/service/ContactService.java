package com.project.tel_book.service;

import com.project.tel_book.model.Contact;

import jakarta.validation.ValidationException;
public interface ContactService {

    Contact findByName(String name);

    Contact findByPhoneNumber(String phoneNumber);

    Contact saveContact(Contact contact) throws ValidationException;
}
