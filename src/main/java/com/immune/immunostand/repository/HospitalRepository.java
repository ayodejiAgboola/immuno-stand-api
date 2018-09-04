package com.immune.immunostand.repository;

import com.immune.immunostand.model.Hospital;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HospitalRepository extends CrudRepository<Hospital, String> {
    Hospital findById(String id);
}
