package org.example.hibernatemedcentercrud.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "patients")
@NamedQueries({@NamedQuery(name = "hql_get_patient_by_name", query = "FROM Patient")})
public class  Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;
    @Column
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate birthDate;
    @Column
    private String phone;

//    private Credential credential;

    public Patient(LocalDate birthDate, Credential credential, int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
  //      this.credential = credential;
        this.phone = phone;
    }
}
