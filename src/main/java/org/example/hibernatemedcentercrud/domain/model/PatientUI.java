package org.example.hibernatemedcentercrud.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.hibernatemedcentercrud.dao.model.Credential;
import org.example.hibernatemedcentercrud.dao.model.Patient;

import java.time.LocalDate;

@AllArgsConstructor
@Data

public class PatientUI {
    private int id;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private int paid;
    private String userName;
    private String password;

    public Patient toPatient() {
        return new Patient(this.id, this.name, this.birthDate, this.phone,
                new Credential(this.userName,this.password, 0));
    }
}

