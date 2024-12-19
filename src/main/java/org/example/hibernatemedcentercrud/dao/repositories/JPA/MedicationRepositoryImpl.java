package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import org.example.hibernatemedcentercrud.dao.model.Medication;
import org.example.hibernatemedcentercrud.dao.repositories.MedicationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicationRepositoryImpl implements MedicationRepository {

    @Override
    public List<Medication> getAll() {
        return List.of();
    }
}
