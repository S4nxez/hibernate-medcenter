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

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;

    public Credential(String userName, String password, int id){
        this.id = id;
        this.username = userName;
        this.password = password;
    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
