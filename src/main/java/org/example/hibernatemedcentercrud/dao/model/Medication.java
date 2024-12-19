package org.example.hibernatemedcentercrud.dao.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medication {
    private int id;
    private String medicationName;
    private int medRecordId;
}