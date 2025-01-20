package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.Payment;
import org.example.hibernatemedcentercrud.dao.repositories.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public PaymentRepositoryImpl(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> list;
        try {
            em = jpautil.getEntityManager();
            list = em.createNamedQuery("Payment.getAll", Payment.class)
                    .getResultList();
            return list;
        } finally {
            if (em != null) em.close();
        }
    }
}
