package com.example.springboot.repositories;

import com.example.springboot.models.FormModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<FormModel, Long> {
    long countByIsDefault(boolean isDefault);
}
