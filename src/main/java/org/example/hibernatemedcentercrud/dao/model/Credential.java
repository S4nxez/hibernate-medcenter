package org.example.hibernatemedcentercrud.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_login")
@NamedQueries({
        @NamedQuery(name = "Credential.getAll", query = "FROM Credential ")
})
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "doctor_id")
    private Integer doctorId;

    public Credential(String userName, String password, int id){
        this.id = id;
        this.username = userName;
        this.password = password;
    }
}
