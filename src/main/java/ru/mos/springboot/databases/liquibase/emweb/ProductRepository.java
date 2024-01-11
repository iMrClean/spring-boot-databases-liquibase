package ru.mos.springboot.databases.liquibase.emweb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
