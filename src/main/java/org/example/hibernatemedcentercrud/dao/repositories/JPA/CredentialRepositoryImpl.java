package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.Credential;
import org.example.hibernatemedcentercrud.dao.repositories.CredentialRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CredentialRepositoryImpl implements CredentialRepository {
    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public CredentialRepositoryImpl(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }

    @Override
    public List<Credential> getAll() {
        List<Credential> list;
        try {
            em = jpautil.getEntityManager();
            list = em.createNamedQuery("Credential.getAll", Credential.class).getResultList();
        } finally {
            if (em != null) em.close();
        }

        return list;
    }
}
