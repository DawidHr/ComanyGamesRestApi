package com.dawidhr.ComanyGamesRestApi.v1.controller;

import com.dawidhr.ComanyGamesRestApi.v1.model.entity.Company;
import com.dawidhr.ComanyGamesRestApi.v1.model.response.CompanyList;
import com.dawidhr.ComanyGamesRestApi.v1.model.response.CompanyNotFoundException;
import com.dawidhr.ComanyGamesRestApi.v1.model.response.ErrorResponse;
import com.dawidhr.ComanyGamesRestApi.v1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping(value = "/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(CompanyNotFoundException companyNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(companyNotFoundException.getMessage());
        errorResponse.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public List<CompanyList> gatAllCompanies() {
        return companyService.getAllCompanyNamesAndIds();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") long id) {
        Company company = companyService.getCompanyById(id);
        return company;
    }

    @PostMapping("/")
    public void createCompany(@RequestBody Company company) {
        companyService.save(company);
        return;
    }

    @GetMapping("/{id}/games")
    public Set<String> getCompanyGames(@PathVariable("id") long id) {
        Set<String> games = companyService.getGames(id);
        return games;
    }

}
