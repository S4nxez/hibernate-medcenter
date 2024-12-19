package org.example.hibernatemedcentercrud.dao.repositories;


import org.example.hibernatemedcentercrud.dao.model.Medication;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository {
    List<Medication> getAll();
}

