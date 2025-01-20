package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.Credential;
import org.example.hibernatemedcentercrud.dao.model.MedRecord;
import org.example.hibernatemedcentercrud.dao.model.Patient;
import org.example.hibernatemedcentercrud.dao.repositories.PatientRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class PatientHibDAO implements PatientRepository {
    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public PatientHibDAO(JPAUtil hu) {
        this.jpautil = hu;
    }

    public List<Patient> getAll() {
        List<Patient> list;
        try {
            em = jpautil.getEntityManager();
            list = em.createQuery("from Patient", Patient.class).getResultList();
        } finally {
            if (em != null) em.close();
        }

        return list;
    }


    public Patient getById(int id) {
        Patient patient;
        em = jpautil.getEntityManager();
        try {
            patient = em.find(Patient.class, id);
        } finally {
            if (em != null) em.close();
        }
        return patient;
    }

    private Patient getByName(String name) {
        Patient patient;
        em = jpautil.getEntityManager();
        try {
            patient = em
                    .createNamedQuery("hql_get_patient_by_name", Patient.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            if (em != null) em.close();
        }

        return patient;
    }

    public int add(Patient patient) {
        em = jpautil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            if (patient.getCredential() != null && patient.getCredential().getId() == null) {
                em.persist(patient.getCredential());
            } else {
                em.merge(patient.getCredential());
            }
            em.persist(patient);

            tx.commit();
            return patient.getId();
        } catch (PersistenceException pe) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.error("Failed to persist Patient", pe);
            return 0;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.error("Undefined error", e);
            return 0;
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void update(Patient patientDatabase, Credential credential) {

    }

    @Override
    public void delete(int idDelete, boolean confirm) {
        if (confirm) {
            em = jpautil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try {
                // Buscar el paciente
                Patient patient = em.find(Patient.class, idDelete);

                if (patient != null) {
                    // Buscar y eliminar los registros médicos asociados a este paciente
                    List<MedRecord> medRecords = em.createQuery("FROM MedRecord WHERE patient.id = :patientId", MedRecord.class)
                            .setParameter("patientId", idDelete)
                            .getResultList();

                    for (MedRecord medRecord : medRecords) {
                        em.remove(medRecord);
                    }

                    em.remove(patient);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                log.error("Failed to delete Patient", e);
            } finally {
                if (em != null) em.close();
            }
        }
    }

}
