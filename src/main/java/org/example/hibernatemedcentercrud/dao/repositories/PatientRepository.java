package org.example.hibernatemedcentercrud.dao.repositories;


import org.example.hibernatemedcentercrud.dao.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository {
    List<Patient> getAll();

    int add(Patient patient);

    void update(Patient patientDatabase);

    void delete(int idDelete, boolean confirm);
}