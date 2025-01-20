package org.example.hibernatemedcentercrud.dao.repositories;


import org.example.hibernatemedcentercrud.dao.model.Doctor;

import java.util.List;

public interface DoctorRepository {
    List<Doctor> getAll();

    Doctor getById(int idDoctor);
}
