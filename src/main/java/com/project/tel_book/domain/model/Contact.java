package com.project.tel_book.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @NotBlank(message = "Name is mandatory")
    private String name;

    @lombok.Getter
    @Setter
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\+7\\ d{3}\\-d{3}\\-d{4}", message = "Phone number must be in the format +7 XXX-XXX-XXXX")
    private String phoneNumber;

    @CreationTimestamp
    private LocalDate dateOfCreate;
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

    public LocalDate getDateOfCreate() {
        return this.dateOfCreate;
    }

}
