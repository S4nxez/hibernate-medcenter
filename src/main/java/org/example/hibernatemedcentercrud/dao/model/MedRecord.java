package org.example.hibernatemedcentercrud.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "medical_records")
@NamedQueries({@NamedQuery(name = "hql_get_record_by_patient",
        query = "FROM MedRecord WHERE  patient.id = :patientId")})
public class MedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor Doctor;

    @Column
    private String diagnosis;

    @Column(name = "admission_date")
    private LocalDate date;

    @OneToMany(mappedBy = "medRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications;
}

