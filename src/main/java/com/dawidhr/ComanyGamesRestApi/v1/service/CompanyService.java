package com.dawidhr.ComanyGamesRestApi.v1.service;

import com.dawidhr.ComanyGamesRestApi.v1.model.entity.Company;
import com.dawidhr.ComanyGamesRestApi.v1.model.response.CompanyList;
import com.dawidhr.ComanyGamesRestApi.v1.model.response.CompanyNotFoundException;
import com.dawidhr.ComanyGamesRestApi.v1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;



    public List<CompanyList> getAllCompanyNamesAndIds() {
        List<Company> companies = companyRepository.findAll();
        return prepareCompaniesAndIds(companies);
    }

    public Company getCompanyById(long id) throws CompanyNotFoundException {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
           return company.get();
        }
        throw new CompanyNotFoundException("Company with id = "+id+" not found");
    }

    public Set<String> getGames(long id) throws CompanyNotFoundException {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get().getGames();
        }
        throw new CompanyNotFoundException("Company with id = "+id+" not found");
    }

    private List<CompanyList> prepareCompaniesAndIds(List<Company> companies) {
        List<CompanyList> companyLists = new ArrayList<>();
        companies.forEach(company -> companyLists.add(new CompanyList(company.getCompanyId(), company.getName())));

        return companyLists;
    }

    public void save(Company company) {
        companyRepository.save(company);
    }

    public Company findCompanyByName(String name) {
        return companyRepository.findAll().stream().filter(company -> {return company.getName().equals(name);}).findFirst().orElse(null);
    }

}
