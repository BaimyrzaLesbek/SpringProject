package com.project.tel_book.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\+7 \\(\\d{3}\\) \\d{7}", message = "Phone number must be in the format +7 (XXX) XXXXXXX")
    private String phoneNumber;
    @CreationTimestamp
    private LocalDate dateOfCreate;
    // Constructors, getters, and setters
    private Contact() {};

    public Contact(String name, String phoneNumber, LocalDate now) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfCreate = now;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public LocalDate getDateOfCreate() {
        return this.dateOfCreate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
