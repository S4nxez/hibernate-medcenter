package org.example.hibernatemedcentercrud.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "patients")
@NamedQueries({@NamedQuery(name = "hql_get_patient_by_name", query = "FROM Patient WHERE  name = :name")})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String phone;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Credential credential;

    public Patient(String name, LocalDate birthDate, String phone, Credential credential) {
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.credential = credential;
    }
}
