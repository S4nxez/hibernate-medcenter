package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.Credential;
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

    public Patient get(Patient patient) {
        if (patient.getId() != 0) {
            patient = getByID(patient.getId());
        } else if (patient.getName() != null) {
            patient = getByName(patient.getName());
        }
        return patient;
    }

    private Patient getByID(int id) {
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

    public int add(Patient patient, Credential credential) {
        em = jpautil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(patient);
            if (credential != null)
                em.persist(credential);
            tx.commit();
        } catch (PersistenceException pe) {
            if (tx != null && tx.isActive())
                tx.rollback();
            log.error("Supplier does not exist");
            return 0;
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            log.error("Undefined error", e);
            return 0;
        } finally {
            if (em != null) em.close();
        }
        return patient.getId();
    }

    @Override
    public void update(Patient patientDatabase, Credential credential) {
        return ;
    }

    @Override
    public void delete(int idDelete, boolean confirm) {
        //With cascade.REMOVE
        if (confirm) {

            em = jpautil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try {
                //Reattach the object before removing
                em.remove(em.merge(getByID(idDelete)));
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                e.printStackTrace();

            } finally {
                if (em != null) em.close();
            }
        }
    }
}
