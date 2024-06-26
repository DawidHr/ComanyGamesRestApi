package com.dawidhr.ComanyGamesRestApi.v1.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;
    private String name;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "modification_date")
    private Timestamp modificationDate;
    @Column(name = "company_url")
    private String companyUrl;
    @Column(name = "company_logo_url")
    private String companyLogoUrl;
    private String description;
    private Set<String> games = new HashSet<>();

    public Company(String name) {
        this.name = name;
    }
}
