package org.example.hibernatemedcentercrud.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "doctors")
@NamedQueries({
    @NamedQuery(name = "Doctor.getAll", query = "FROM Doctor")
})
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "phone")
    private String phone;

    public Doctor(int id, String name, String specialization, String phone) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
    }
}