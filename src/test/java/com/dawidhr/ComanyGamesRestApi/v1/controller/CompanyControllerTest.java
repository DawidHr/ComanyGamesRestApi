package com.dawidhr.ComanyGamesRestApi.v1.controller;

import com.dawidhr.ComanyGamesRestApi.v1.model.entity.Company;
import com.dawidhr.ComanyGamesRestApi.v1.model.response.CompanyNotFoundException;
import com.dawidhr.ComanyGamesRestApi.v1.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CompanyControllerTest {
    @InjectMocks
    CompanyController companyController;
    @Mock
    CompanyService companyService;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void gatAllCompaniesTest() throws Exception {
        when(companyService.getAllCompanyNamesAndIds()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCompanyByIdTest() throws Exception {
        when(companyService.getCompanyById(anyLong())).thenReturn(new Company());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCompanyByIdCompanyNotFoundTest() throws Exception {
        when(companyService.getCompanyById(anyLong())).thenThrow(CompanyNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies/2"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCompanyGamesTest() throws Exception {
        when(companyService.getGames(anyLong())).thenReturn(new HashSet<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies/1/games"))
                .andExpect(status().isOk());
    }

    @Test
    public void createCompanyTest() throws Exception {
        when(companyService.findCompanyByName(anyString())).thenReturn(new Company());
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/companies")
                        .param("name", "Test name")
                        .param("companyUrl", "http://test.pl")
                        .param("companyLogoUrl", "http://test.pl")
                        .param("description", "Testtt"))
                .andExpect(status().is4xxClientError());
    }
}