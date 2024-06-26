package com.dawidhr.ComanyGamesRestApi.v1.repository;

import com.dawidhr.ComanyGamesRestApi.v1.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
