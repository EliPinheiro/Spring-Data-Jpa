package com.eli.eli_food.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eli.eli_food.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

      
        
    
}
