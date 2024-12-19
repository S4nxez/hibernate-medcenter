package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import org.example.hibernatemedcentercrud.dao.model.Doctor;
import org.example.hibernatemedcentercrud.dao.repositories.DoctorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {
    @Override
    public List<Doctor> getAll() {
        return List.of();
    }
}
