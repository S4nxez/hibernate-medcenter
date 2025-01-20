package org.example.hibernatemedcentercrud.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "patient_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Payment.getAll", query = "FROM Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate date;
}