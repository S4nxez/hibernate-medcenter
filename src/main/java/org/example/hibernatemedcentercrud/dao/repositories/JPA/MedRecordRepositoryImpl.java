package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.MedRecord;
import org.example.hibernatemedcentercrud.dao.model.Medication;
import org.example.hibernatemedcentercrud.dao.repositories.MedRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
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
        em = jpautil.getEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            if (medRecord.getPatient() != null && medRecord.getPatient().getId() == 0) {
                em.persist(medRecord.getPatient());
            }

            em.persist(medRecord);

            List<Medication> medications = new ArrayList<>(medRecord.getMedications());

            for (Medication medication : medications) {
                medication.setMedRecord(medRecord);
                if (medication.getId() == 0) {
                    em.persist(medication);
                } else {
                    em.merge(medication);
                }
            }

            tx.commit();
            return medRecord.getId();

        } catch (PersistenceException pe) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Failed to persist MedRecord", pe);
            return 0;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Undefined error", e);
            return 0;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    @Override
    public void delete(int id) {
        em = jpautil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            MedRecord medRecord = em.find(MedRecord.class, id);
            em.remove(medRecord);

            tx.commit();
        } catch (PersistenceException pe) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Failed to delete MedRecord", pe);
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            log.error("Undefined error", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void update(MedRecord medRecord) {
        em = jpautil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            MedRecord managedRecord = em.merge(medRecord);
            List<Medication> existingMedications = new ArrayList<>(managedRecord.getMedications());
            List<Medication> newMedications = new ArrayList<>(medRecord.getMedications());

            for (Medication existingMedication : existingMedications) {
                if (!newMedications.contains(existingMedication)) {
                    em.remove(existingMedication);
                }
            }
            for (Medication medication : newMedications) {
                medication.setMedRecord(managedRecord);
                if (medication.getId() == 0) { 
                    em.persist(medication);
                } else {
                    em.merge(medication);
                }
            }

            tx.commit();
        } catch (PersistenceException pe) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            log.error("Failed to update MedRecord", pe);
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            log.error("Undefined error", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
