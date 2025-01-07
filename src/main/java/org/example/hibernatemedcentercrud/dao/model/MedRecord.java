package org.example.hibernatemedcentercrud.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "medical_records")
@NamedQueries({@NamedQuery(name = "hql_get_record_by_patient",
        query = "FROM MedRecord WHERE  idPatient = :patientId")})
public class MedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int id;
    @Column(name = "patient_id")
    private int idPatient;
    @Column(name = "doctor_id")
    private int idDoctor;
    @Column
    private String diagnosis;
    @Column(name = "admission_date")
    private LocalDate date;
    @Transient
    private List<Medication> medications;

    public MedRecord(int id, int idPatient, int idDoctor, String diagnosis, LocalDate date, List<Medication> medications) {
        this.id = id;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.diagnosis = diagnosis;
        this.date = date;
        this.medications = medications;
    }
}

