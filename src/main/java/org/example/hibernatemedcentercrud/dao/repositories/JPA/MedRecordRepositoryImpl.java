package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.MedRecord;
import org.example.hibernatemedcentercrud.dao.repositories.MedRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedRecordRepositoryImpl implements MedRecordRepository {
    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public MedRecordRepositoryImpl(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }

    @Override
    public List<MedRecord> getAll(int idPatient) {
        List<MedRecord> list;
        try {
            list = jpautil.getEntityManager().createNamedQuery("hql_get_record_by_patient")
                    .setParameter("patientId", idPatient)
                    .getResultList();
        } finally {
            if (em != null) em.close();
        }
        return list;
    }

    @Override
    public int add(MedRecord medRecord) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(MedRecord medRecord) {

    }
}
