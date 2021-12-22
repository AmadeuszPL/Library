package com.amadeusz.library.infrastructure.repository.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "library_members")
public class LibraryMemberEntity {

    @Id
    @GeneratedValue
    @Column(name = "libraryMemberId", updatable = false, nullable = false)
    private UUID id;
    private String login;
    private String password;
    private String personName;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String personEmail;
    private String personTelephoneNumber;
    private int totalBooksCheckedOut;
    private BigDecimal fine;
    private long creditCardNumber;
    private String cardTypeToString;

}
