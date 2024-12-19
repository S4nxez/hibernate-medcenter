package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import org.example.hibernatemedcentercrud.dao.model.Payment;
import org.example.hibernatemedcentercrud.dao.repositories.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Override
    public List<Payment> getAll() {
        return List.of();
    }
}
