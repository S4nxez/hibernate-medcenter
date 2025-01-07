package org.example.hibernatemedcentercrud.domain.services;

import org.example.hibernatemedcentercrud.dao.model.Doctor;
import org.example.hibernatemedcentercrud.dao.repositories.DoctorRepository;
import org.example.hibernatemedcentercrud.domain.model.DoctorUI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorUI> getAll() {

        List<Doctor> doctors= doctorRepository.getAll();
        List<DoctorUI> doctorUIs = doctors.stream()
                .map(doctor -> new DoctorUI(doctor.getId(), doctor.getName()))
                .toList();
        return doctorUIs;
    }
}
