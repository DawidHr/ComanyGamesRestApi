package com.dawidhr.ComanyGamesRestApi.v1.bootstrap;

import com.dawidhr.ComanyGamesRestApi.v1.model.entity.Company;
import com.dawidhr.ComanyGamesRestApi.v1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Boot implements CommandLineRunner {

    @Autowired
    public CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Rockstar Games"));
        companies.add(new Company("Warhorse Studios"));
        companies.add(new Company("Ubisoft"));
        companies.add(new Company("Blizzard Entertainment"));
        companies.add(new Company("CD Projekt RED"));
        for (Company company: companies) {
            companyRepository.save(company);
        }
    }
}
