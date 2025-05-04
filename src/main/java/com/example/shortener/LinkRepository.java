package com.example.shortener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, String> {
    // Métodos personalizados si son necesarios
}