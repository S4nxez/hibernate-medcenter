package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.Doctor;
import org.example.hibernatemedcentercrud.dao.repositories.DoctorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {
    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DoctorRepositoryImpl(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> list;
        try {
            em = jpautil.getEntityManager();
            list = em.createNamedQuery("Doctor.getAll", Doctor.class)
                    .getResultList();
        } finally {
            if (em != null) em.close();
        }
        return list;
    }

    @Override
    public Doctor getById(int idDoctor) {
        Doctor doctor;
        try {
            em = jpautil.getEntityManager();
            doctor = em.find(Doctor.class, idDoctor);
        } finally {
            if (em != null) em.close();
        }
        return doctor;
    }
}
